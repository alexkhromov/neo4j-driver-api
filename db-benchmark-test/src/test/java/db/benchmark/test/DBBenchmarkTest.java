package db.benchmark.test;

import db.benchmark.mysql.service.SearchService;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static db.benchmark.mysql.connection.Connection.buildConnectionFN;
import static db.benchmark.mysql.connection.Connection.buildConnectionMN;
import static db.benchmark.neo4j.connection.Connection.*;
import static db.benchmark.test.TestConfiguration.*;
import static db.benchmark.test.TestQuery.NEO4J_QUERY_FN;
import static db.benchmark.test.TestQuery.NEO4J_QUERY_MN;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Mode.Throughput;

public class DBBenchmarkTest {

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

    @State(Scope.Thread)
    public static class MySqlStateMN {

        SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            searchService = SearchService
                    .builder()
                    .connection(buildConnectionMN())
                    .build();
        }
    }

    @State(Scope.Thread)
    public static class MySqlStateFN {

        SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            searchService = SearchService
                    .builder()
                    .connection(buildConnectionFN())
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
                    .connection(buildHttpConnection(NEO4J_HTTP_URI, NEO4J_MN_ENTITY_PACKAGE))
                    .build();
        }
    }

    @State(Scope.Thread)
    public static class Neo4jHttpStateFN {

        db.benchmark.neo4j.service.SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            searchService = db.benchmark.neo4j.service.SearchService
                    .builder()
                    .connection(buildHttpConnection(NEO4J_HTTP_URI, NEO4J_FN_ENTITY_PACKAGE))
                    .build();
        }
    }

    @State(Scope.Thread)
    public static class Neo4jBoltStateMN {

        db.benchmark.neo4j.connection.Connection connection;
        db.benchmark.neo4j.service.SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            connection = buildBoltConnection(NEO4J_BOLT_URI, NEO4J_USER, NEO4J_PASSWORD, NEO4J_MN_ENTITY_PACKAGE);
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

    @State(Scope.Thread)
    public static class Neo4jBoltStateFN {

        db.benchmark.neo4j.connection.Connection connection;
        db.benchmark.neo4j.service.SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            connection = buildBoltConnection(NEO4J_BOLT_URI, NEO4J_USER, NEO4J_PASSWORD, NEO4J_FN_ENTITY_PACKAGE);
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

    @Benchmark
    @Group("MN")
    @OutputTimeUnit(SECONDS)
    public void mysqlSearchMN(MySqlStateMN state) {
        state.searchService.searchMN();
    }

    @Benchmark
    @Group("MN")
    @OutputTimeUnit(SECONDS)
    public void neo4jHttpSearchMN(Neo4jHttpStateMN state) {
        state.searchService.search(NEO4J_QUERY_MN);
    }

    @Benchmark
    @Group("MN")
    @OutputTimeUnit(SECONDS)
    public void neo4jBoltSearchMN(Neo4jBoltStateMN state) {
        state.searchService.search(NEO4J_QUERY_MN);
    }

    @Benchmark
    @Group("FN")
    @OutputTimeUnit(SECONDS)
    public void mysqlSearchFN(MySqlStateFN state) {
        state.searchService.searchFN();
    }

    @Benchmark
    @Group("FN")
    @OutputTimeUnit(SECONDS)
    public void neo4jHttpSearchFN(Neo4jHttpStateFN state) {
        state.searchService.search(NEO4J_QUERY_FN);
    }

    @Benchmark
    @Group("FN")
    @OutputTimeUnit(SECONDS)
    public void neo4jBoltSearchFN(Neo4jBoltStateFN state) {
        state.searchService.search(NEO4J_QUERY_FN);
    }
}