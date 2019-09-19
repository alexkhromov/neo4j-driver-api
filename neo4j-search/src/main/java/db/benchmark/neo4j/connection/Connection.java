package db.benchmark.neo4j.connection;

import lombok.AllArgsConstructor;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.drivers.bolt.driver.BoltDriver;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import static org.neo4j.driver.v1.AuthTokens.basic;
import static org.neo4j.driver.v1.GraphDatabase.driver;

@AllArgsConstructor
public class Connection {

    private final SessionFactory sessionFactory;

    public static Connection buildHttpConnection(String uri, String entityPackage) {

        Configuration configuration = new Configuration.Builder()
                .uri(uri)
                .build();

        SessionFactory sessionFactory = new SessionFactory(configuration, entityPackage);

        return new Connection(sessionFactory);
    }

    public static Connection buildBoltConnection(String uri, String user, String password, String entityPackage) {

        BoltDriver boltDriver = new BoltDriver(driver(uri, basic(user, password)));

        SessionFactory sessionFactory = new SessionFactory(boltDriver, entityPackage);

        return new Connection(sessionFactory);
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void closeSession() {
        sessionFactory.close();
    }
}