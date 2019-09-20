package db.benchmark.mysql.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Properties;

public class Connection {

    private final SessionFactory sessionFactory;

    private Connection(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static Connection buildConnection(String uri, String user, String password, List<Class<?>> classes) {

        Properties props = new Properties();
        props.setProperty("hibernate.connection.url", uri);
        props.setProperty("hibernate.connection.username", user);
        props.setProperty("hibernate.connection.password", password);
        props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

        Configuration configuration = new Configuration().addProperties(props);
        classes.forEach(configuration::addAnnotatedClass);

        return new Connection(configuration.buildSessionFactory());
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}