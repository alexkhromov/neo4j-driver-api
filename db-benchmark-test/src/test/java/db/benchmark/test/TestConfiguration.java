package db.benchmark.test;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

public class TestConfiguration {

    private static final Properties CONFIGURATION_HOLDER = new Properties();

    static {
        try (InputStream is = TestConfiguration.class.getClassLoader().getResourceAsStream("configuration.properties")) {
            CONFIGURATION_HOLDER.load(is);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Some test configuration data could not be loaded");
        }
    }

    public static final String NEO4J_USER = CONFIGURATION_HOLDER.getProperty("neo4j.user");
    public static final String NEO4J_PASSWORD = CONFIGURATION_HOLDER.getProperty("neo4j.password");
    public static final String NEO4J_HTTP_URI = CONFIGURATION_HOLDER.getProperty("neo4j.http.uri");
    public static final String NEO4J_BOLT_URI = CONFIGURATION_HOLDER.getProperty("neo4j.bolt.uri");
    public static final String NEO4J_MN_ENTITY_PACKAGE = CONFIGURATION_HOLDER.getProperty("neo4j.entity.mn.package");
    public static final String NEO4J_FN_ENTITY_PACKAGE = CONFIGURATION_HOLDER.getProperty("neo4j.entity.fn.package");

    public static final String MYSQL_USER = CONFIGURATION_HOLDER.getProperty("mysql.user");
    public static final String MYSQL_PASSWORD = CONFIGURATION_HOLDER.getProperty("mysql.password");
    public static final String MYSQL_URI_MN = CONFIGURATION_HOLDER.getProperty("mysql.uri.mn");
    public static final String MYSQL_URI_FN = CONFIGURATION_HOLDER.getProperty("mysql.uri.fn");
    public static final List<Class<?>> MYSQL_CLASSES_MN = getClasses("mysql.classes.mn");
    public static final List<Class<?>> MYSQL_CLASSES_FN = getClasses("mysql.classes.fn");

    private static List<Class<?>> getClasses(String property) {

        return of(CONFIGURATION_HOLDER.getProperty(property).split(","))
                .map(String::trim)
                .map(name -> {
                    try {
                        return Class.forName(name);
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                        throw new RuntimeException("Some test configuration data could not be loaded");
                    }
                })
                .collect(toList());
    }
}