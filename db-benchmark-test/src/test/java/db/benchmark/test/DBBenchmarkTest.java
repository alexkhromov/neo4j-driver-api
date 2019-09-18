package db.benchmark.test;

import db.benchmark.mysql.service.SearchService;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static db.benchmark.mysql.connection.Connection.buildMNConnection;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Mode.Throughput;

public class DBBenchmarkTest {

    @State(Scope.Thread)
    public static class BenchmarkState {

        SearchService mysqlSearchService;
        db.benchmark.neo4j.service.SearchService neo4jSearchService;

        @Setup(Level.Trial)
        public void doSetup() {

            mysqlSearchService = SearchService
                    .builder()
                    .connection(buildMNConnection())
                    .build();

            neo4jSearchService = db.benchmark.neo4j.service.SearchService
                    .builder()
                    .connection(db.benchmark.neo4j.connection.Connection.buildMNConnection())
                    .build();
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
    @OutputTimeUnit(SECONDS)
    public void mysqlMNSearch(BenchmarkState state) {
        state.mysqlSearchService.search();
    }

    @Benchmark
    @OutputTimeUnit(SECONDS)
    public void neo4jMNSearch(BenchmarkState state) {
        state.neo4jSearchService.search();
    }
}