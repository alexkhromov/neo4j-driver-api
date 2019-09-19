package db.benchmark.test;

import db.benchmark.mysql.service.SearchService;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static db.benchmark.mysql.connection.Connection.buildMNConnection;
import static db.benchmark.neo4j.connection.Connection.buildHttpConnectionMN;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Mode.Throughput;

public class DBBenchmarkTest {

    @State(Scope.Thread)
    public static class MySqlStateMN {

        SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            searchService = SearchService
                    .builder()
                    .connection(buildMNConnection())
                    .build();
        }
    }

    @State(Scope.Thread)
    public static class Neo4jHttpStateMN {

        db.benchmark.neo4j.service.SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            searchService = db.benchmark.neo4j.service.SearchService
                    .builder()
                    .connection(buildHttpConnectionMN())
                    .build();
        }
    }

    @State(Scope.Thread)
    public static class Neo4jBoltStateMN {

        db.benchmark.neo4j.connection.Connection connection;
        db.benchmark.neo4j.service.SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            connection = db.benchmark.neo4j.connection.Connection.buildBoltConnectionMN();
            searchService = db.benchmark.neo4j.service.SearchService
                    .builder()
                    .connection(connection)
                    .build();
        }

        @TearDown(Level.Trial)
        public void doTearDown() {
            connection.closeSession();
        }
    }

    @Test
    public void runJmhBenchmark() throws RunnerException {

        Options options = new OptionsBuilder()
                .include(DBBenchmarkTest.class.getSimpleName())
                .warmupIterations(1)
                .measurementIterations(2)
                .mode(Throughput)
                .mode(AverageTime)
                .forks(1)
                .build();

        new Runner(options).run();
    }

    @Benchmark
    @Group("MN")
    @OutputTimeUnit(SECONDS)
    public void mysqlSearchMN(MySqlStateMN state) {
        state.searchService.search();
    }

    @Benchmark
    @Group("MN")
    @OutputTimeUnit(SECONDS)
    public void neo4jHttpSearchMN(Neo4jHttpStateMN state) {
        state.searchService.search();
    }

    @Benchmark
    @Group("MN")
    @OutputTimeUnit(SECONDS)
    public void neo4jBoltSearchMN(Neo4jBoltStateMN state) {
        state.searchService.search();
    }
}