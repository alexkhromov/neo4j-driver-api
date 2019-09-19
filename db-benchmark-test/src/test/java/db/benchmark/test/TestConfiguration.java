package db.benchmark.test;

import java.io.InputStream;
import java.util.Properties;

public class TestConfiguration {

    private static final Properties CONFIGURATION_HOLDER = new Properties();

    static {
        try (InputStream is = TestConfiguration.class.getClassLoader().getResourceAsStream("configuration.properties")) {
            CONFIGURATION_HOLDER.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String NEO4J_USER = CONFIGURATION_HOLDER.getProperty("neo4j.user");
    public static final String NEO4J_PASSWORD = CONFIGURATION_HOLDER.getProperty("neo4j.password");
    public static final String NEO4J_HTTP_URI = CONFIGURATION_HOLDER.getProperty("neo4j.http.uri");
    public static final String NEO4J_BOLT_URI = CONFIGURATION_HOLDER.getProperty("neo4j.bolt.uri");
    public static final String NEO4J_MN_ENTITY_PACKAGE = CONFIGURATION_HOLDER.getProperty("neo4j.mn.entity.package");
    public static final String NEO4J_FN_ENTITY_PACKAGE = CONFIGURATION_HOLDER.getProperty("neo4j.fn.entity.package");
}