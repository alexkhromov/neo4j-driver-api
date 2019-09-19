package db.benchmark.neo4j.connection;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.drivers.bolt.driver.BoltDriver;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import static org.neo4j.driver.v1.AuthTokens.basic;
import static org.neo4j.driver.v1.GraphDatabase.driver;

public class Connection {

    private final SessionFactory sessionFactory;

    private Connection(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static Connection buildHttpConnectionMN() {

        Configuration configuration = new Configuration.Builder()
                .uri("http://neo4j:Neo4j@localhost:7474")
                .build();

        SessionFactory sessionFactory = new SessionFactory(configuration, "db.benchmark.neo4j.entity");

        return new Connection(sessionFactory);
    }

    public static Connection buildBoltConnectionMN() {

        BoltDriver boltDriver = new BoltDriver(driver("bolt://localhost:7687", basic("neo4j", "Neo4j")));

        SessionFactory sessionFactory = new SessionFactory(boltDriver, "db.benchmark.neo4j.entity");

        return new Connection(sessionFactory);
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void closeSession() {
        sessionFactory.close();
    }
}