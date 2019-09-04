package khromov.alex.test.service;

import khromov.alex.test.connection.Connection;
import org.apache.commons.lang3.time.StopWatch;
import org.hibernate.Session;

import java.util.List;

import static java.lang.System.out;

public class SearchService {

    private Connection connection;

    public SearchService( Connection connection ) {
        this.connection = connection;
    }

    public void search() {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Session session = connection.getSession();
        List< ? > result = session.createQuery(

                "SELECT c.name, COUNT( s ) FROM Sector AS s " +
                "INNER JOIN s.cell AS ce INNER JOIN ce.locationArea AS la INNER JOIN la.controller AS c " +
                "WHERE s.type = '4G' AND s.azimuth = '0' " +
                "GROUP BY c.name"

                ).list();
        session.close();

        stopWatch.stop();

        out.println( stopWatch.toString() );
        result.forEach( row -> System.out.println( ( ( Object [] )row )[ 0 ] + " " + ( ( Object [] )row )[ 1 ] ) );
    }
}