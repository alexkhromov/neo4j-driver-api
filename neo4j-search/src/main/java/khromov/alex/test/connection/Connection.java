package khromov.alex.test.connection;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Connection {

    private final SessionFactory sessionFactory;

    public Connection() {

        Configuration configuration = new Configuration.Builder()
                .uri( "http://neo4j:Neo4j@localhost:7474" )
                .build();

        sessionFactory = new SessionFactory( configuration, "test.entity" );
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}