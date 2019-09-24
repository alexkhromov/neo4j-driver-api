package db.benchmark.test;

import java.io.InputStream;
import java.util.Properties;

public class TestQuery {

    private static final Properties QUERY_HOLDER = new Properties();

    static {
        try (InputStream is = TestQuery.class.getClassLoader().getResourceAsStream("queries.properties")) {
            QUERY_HOLDER.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String NEO4J_QUERY_MN = QUERY_HOLDER.getProperty("neo4j.query.mn");
    public static final String MYSQL_QUERY_MN = QUERY_HOLDER.getProperty("mysql.query.mn");

    public static final String NEO4J_QUERY_FN_1 = QUERY_HOLDER.getProperty("neo4j.query.fn.1");
    public static final String MYSQL_QUERY_FN_1 = QUERY_HOLDER.getProperty("mysql.query.fn.1");

    public static final String NEO4J_QUERY_FN_2 = QUERY_HOLDER.getProperty("neo4j.query.fn.2");
    public static final String MYSQL_QUERY_FN_2 = QUERY_HOLDER.getProperty("mysql.query.fn.2");

    public static final String NEO4J_QUERY_FN_3 = QUERY_HOLDER.getProperty("neo4j.query.fn.3");
    public static final String MYSQL_QUERY_FN_3 = QUERY_HOLDER.getProperty("mysql.query.fn.3");

    public static final String NEO4J_QUERY_FN_4 = QUERY_HOLDER.getProperty("neo4j.query.fn.4");
    public static final String MYSQL_QUERY_FN_4 = QUERY_HOLDER.getProperty("mysql.query.fn.4");

    public static final String NEO4J_QUERY_FN_5 = QUERY_HOLDER.getProperty("neo4j.query.fn.5");
    public static final String MYSQL_QUERY_FN_5 = QUERY_HOLDER.getProperty("mysql.query.fn.5");

    public static final String NEO4J_QUERY_FN_6 = QUERY_HOLDER.getProperty("neo4j.query.fn.6");
    public static final String MYSQL_QUERY_FN_6 = QUERY_HOLDER.getProperty("mysql.query.fn.6");
}