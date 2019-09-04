package khromov.alex.test.connection;


import khromov.alex.test.entity.Cell;
import khromov.alex.test.entity.Controller;
import khromov.alex.test.entity.LocationArea;
import khromov.alex.test.entity.Sector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Connection {

    private final SessionFactory sessionFactory;

    public Connection() {

            Properties props = new Properties();
            props.setProperty( "hibernate.connection.url", "jdbc:mysql://localhost:3306/mn" );
            props.setProperty( "hibernate.connection.username", "root" );
            props.setProperty( "hibernate.connection.password", "root" );
            props.setProperty( "dialect", "org.hibernate.dialect.MySQLDialect" );

            this.sessionFactory = new Configuration()
                    .addProperties( props )
                    .addAnnotatedClass( Controller.class )
                    .addAnnotatedClass( LocationArea.class )
                    .addAnnotatedClass( Cell.class )
                    .addAnnotatedClass( Sector.class )
                    .buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}