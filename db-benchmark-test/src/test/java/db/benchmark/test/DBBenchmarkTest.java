package db.benchmark.test;

import db.benchmark.mysql.service.SearchService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static db.benchmark.mysql.connection.Connection.buildConnection;
import static db.benchmark.neo4j.connection.Connection.buildBoltConnection;
import static db.benchmark.neo4j.connection.Connection.buildHttpConnection;
import static db.benchmark.test.TestConfiguration.*;
import static db.benchmark.test.TestQuery.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Mode.Throughput;

public class DBBenchmarkTest {

    @ParameterizedTest
    @ValueSource(classes = {MNTestGroup.class, FNTestGroup_1.class, FNTestGroup_2.class, FNTestGroup_3.class})
    public void runJmhBenchmark(Class<?> testGroup) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(testGroup.getSimpleName())
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

            searchService = new SearchService(
                    buildConnection(MYSQL_URI_MN, MYSQL_USER, MYSQL_PASSWORD, MYSQL_CLASSES_MN));
        }
    }

    @State(Scope.Thread)
    public static class MySqlStateFN {

        SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            searchService = new SearchService(
                    buildConnection(MYSQL_URI_FN, MYSQL_USER, MYSQL_PASSWORD, MYSQL_CLASSES_FN));
        }
    }

    @State(Scope.Thread)
    public static class Neo4jHttpStateMN {

        db.benchmark.neo4j.service.SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            searchService = new db.benchmark.neo4j.service.SearchService(
                    buildHttpConnection(NEO4J_HTTP_URI, NEO4J_MN_ENTITY_PACKAGE));
        }
    }

    @State(Scope.Thread)
    public static class Neo4jHttpStateFN {

        db.benchmark.neo4j.service.SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            searchService = new db.benchmark.neo4j.service.SearchService(
                    buildHttpConnection(NEO4J_HTTP_URI, NEO4J_FN_ENTITY_PACKAGE));
        }
    }

    @State(Scope.Thread)
    public static class Neo4jBoltStateMN {

        db.benchmark.neo4j.connection.Connection connection;
        db.benchmark.neo4j.service.SearchService searchService;

        @Setup(Level.Trial)
        public void doSetup() {

            connection = buildBoltConnection(NEO4J_BOLT_URI, NEO4J_USER, NEO4J_PASSWORD, NEO4J_MN_ENTITY_PACKAGE);
            searchService = new db.benchmark.neo4j.service.SearchService(connection);
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
            searchService = new db.benchmark.neo4j.service.SearchService(connection);
        }

        @TearDown(Level.Trial)
        public void doTearDown() {
            connection.closeSession();
        }
    }

    public static class MNTestGroup {

        @Benchmark
        @Group("MN")
        @OutputTimeUnit(SECONDS)
        public void mysqlSearchMN(MySqlStateMN state) {
            state.searchService.search(MYSQL_QUERY_MN);
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
    }

    public static class FNTestGroup_1 {

        @Benchmark
        @Group("FN_1")
        @OutputTimeUnit(SECONDS)
        public void mysqlSearchFN(MySqlStateFN state) {
            state.searchService.search(MYSQL_QUERY_FN_1);
        }

        @Benchmark
        @Group("FN_1")
        @OutputTimeUnit(SECONDS)
        public void neo4jHttpSearchFN(Neo4jHttpStateFN state) {
            state.searchService.search(NEO4J_QUERY_FN_1);
        }

        @Benchmark
        @Group("FN_1")
        @OutputTimeUnit(SECONDS)
        public void neo4jBoltSearchFN(Neo4jBoltStateFN state) {
            state.searchService.search(NEO4J_QUERY_FN_1);
        }
    }

    public static class FNTestGroup_2 {

        @Benchmark
        @Group("FN_2")
        @OutputTimeUnit(SECONDS)
        public void mysqlSearchFN(MySqlStateFN state) {
            state.searchService.search(MYSQL_QUERY_FN_2);
        }

        @Benchmark
        @Group("FN_2")
        @OutputTimeUnit(SECONDS)
        public void neo4jHttpSearchFN(Neo4jHttpStateFN state) {
            state.searchService.search(NEO4J_QUERY_FN_2);
        }

        @Benchmark
        @Group("FN_2")
        @OutputTimeUnit(SECONDS)
        public void neo4jBoltSearchFN(Neo4jBoltStateFN state) {
            state.searchService.search(NEO4J_QUERY_FN_2);
        }
    }

    public static class FNTestGroup_3 {

        @Benchmark
        @Group("FN_3")
        @OutputTimeUnit(SECONDS)
        public void mysqlSearchFN(MySqlStateFN state) {
            state.searchService.search(MYSQL_QUERY_FN_3);
        }

        @Benchmark
        @Group("FN_3")
        @OutputTimeUnit(SECONDS)
        public void neo4jHttpSearchFN(Neo4jHttpStateFN state) {
            state.searchService.search(NEO4J_QUERY_FN_3);
        }

        @Benchmark
        @Group("FN_3")
        @OutputTimeUnit(SECONDS)
        public void neo4jBoltSearchFN(Neo4jBoltStateFN state) {
            state.searchService.search(NEO4J_QUERY_FN_3);
        }
    }
}