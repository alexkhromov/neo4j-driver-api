package db.benchmark.mysql.connection;

import db.benchmark.mysql.entity.fn.Friend;
import db.benchmark.mysql.entity.fn.Friends;
import db.benchmark.mysql.entity.mn.Cell;
import db.benchmark.mysql.entity.mn.Controller;
import db.benchmark.mysql.entity.mn.LocationArea;
import db.benchmark.mysql.entity.mn.Sector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Connection {

    private final SessionFactory sessionFactory;

    private Connection(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static Connection buildConnectionMN() {

        Properties props = new Properties();
        props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/MN");
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

    public static Connection buildConnectionFN() {

        Properties props = new Properties();
        props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/FN");
        props.setProperty("hibernate.connection.username", "root");
        props.setProperty("hibernate.connection.password", "root");
        props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

        SessionFactory sessionFactory = new Configuration()
                .addProperties(props )
                .addAnnotatedClass(Friend.class)
                .addAnnotatedClass(Friends.class)
                .buildSessionFactory();

        return new Connection(sessionFactory);
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}