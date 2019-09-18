package db.benchmark.mysql.connection;

import db.benchmark.mysql.entity.Cell;
import db.benchmark.mysql.entity.Controller;
import db.benchmark.mysql.entity.LocationArea;
import db.benchmark.mysql.entity.Sector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Connection {

    private final SessionFactory sessionFactory;

    private Connection(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static Connection buildMNConnection() {

        Properties props = new Properties();
        props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mn");
        props.setProperty("hibernate.connection.username", "root");
        props.setProperty("hibernate.connection.password", "root");
        props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

        SessionFactory sessionFactory = new Configuration()
                .addProperties(props )
                .addAnnotatedClass(Controller.class)
                .addAnnotatedClass(LocationArea.class)
                .addAnnotatedClass(Cell.class)
                .addAnnotatedClass(Sector.class)
                .buildSessionFactory();

        return new Connection(sessionFactory);
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}