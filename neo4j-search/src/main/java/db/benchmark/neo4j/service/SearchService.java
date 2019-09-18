package db.benchmark.neo4j.service;

import db.benchmark.neo4j.connection.Connection;
import lombok.Builder;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;

import static java.util.Collections.emptyMap;

@Builder
public class SearchService {

    private Connection connection;

    public Result search() {

        Session session = connection.getSession();
        Result result = session.query(

                "MATCH (c:Controller)-[:HAS_LOCATION]->(l:LocationArea)-[:HAS_CELL]->(ce:Cell)" +
                "-[:HAS_SECTOR]->(s:Sector{type:'4G', azimuth:'0'}) return c.name, count(s) as count",

                //"MATCH (c:Controller)-[:HAS_LOCATION]->(l:LocationArea)-[:HAS_CELL]->(ce:Cell)" +
                //"-[:HAS_SECTOR]->(s:Sector ) return count(s) as count",

                emptyMap() );
        session.clear();

        return result;
    }
}