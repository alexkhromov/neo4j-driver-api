package db.benchmark.mysql.service;

import db.benchmark.mysql.connection.Connection;
import lombok.Builder;
import org.hibernate.Session;

import java.util.List;

@Builder
public class SearchService {

    private Connection connection;

    public List search() {

        Session session = connection.getSession();
        List< ? > result = session.createQuery(

                "SELECT c.name, COUNT( s ) FROM Sector AS s " +
                "INNER JOIN s.cell AS ce INNER JOIN ce.locationArea AS la INNER JOIN la.controller AS c " +
                "WHERE s.type = '4G' AND s.azimuth = '0' " +
                "GROUP BY c.name"

                ).list();
        session.close();

        return result;
    }
}