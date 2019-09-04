package khromov.alex.test.service;

import khromov.alex.test.connection.Connection;
import org.apache.commons.lang3.time.StopWatch;
import org.neo4j.ogm.model.Result;

import static java.lang.System.out;
import static java.util.Collections.emptyMap;

public class SearchService {

    private Connection connection;

    public SearchService( Connection connection ) {
        this.connection = connection;
    }

    public void search() {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Result result = connection.getSession().query(

                "MATCH (c:Controller)-[:HAS_LOCATION]->(l:LocationArea)-[:HAS_CELL]->(ce:Cell)" +
                "-[:HAS_SECTOR]->(s:Sector{type:'4G', azimuth:'0'}) return c.name, count(s) as count",

                //"MATCH (c:Controller)-[:HAS_LOCATION]->(l:LocationArea)-[:HAS_CELL]->(ce:Cell)" +
                //"-[:HAS_SECTOR]->(s:Sector ) return count(s) as count",

                emptyMap() );
        stopWatch.stop();

        out.println( stopWatch.toString() );
        result.forEach( out::println );
    }
}