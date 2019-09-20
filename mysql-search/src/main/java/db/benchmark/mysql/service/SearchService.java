package db.benchmark.mysql.service;

import db.benchmark.mysql.connection.Connection;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

@AllArgsConstructor
public class SearchService {

    private Connection connection;

    public Object search(String query) {

        Session session = connection.getSession();
        Object result = session.createNativeQuery(query).list();
        session.close();

        return result;
    }
}