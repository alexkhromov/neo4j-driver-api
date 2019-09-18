package db.benchmark.neo4j.connection;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Connection {

    private final SessionFactory sessionFactory;

    private Connection(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static Connection buildMNConnection() {

        Configuration configuration = new Configuration.Builder()
                .uri("http://neo4j:Neo4j@localhost:7474")
                .build();

        SessionFactory sessionFactory = new SessionFactory(configuration, "db.benchmark.neo4j.entity");

        return new Connection(sessionFactory);
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}