package db.benchmark.neo4j.service;

import db.benchmark.neo4j.connection.Connection;
import lombok.Builder;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;

import static java.util.Collections.emptyMap;

@Builder
public class SearchService {

    private Connection connection;

    public Result search(String query) {

        /*"MATCH (c:Controller)-[:HAS_LOCATION]->(l:LocationArea)-[:HAS_CELL]->(ce:Cell)" +
                "-[:HAS_SECTOR]->(s:Sector{type:'4G', azimuth:'0'}) return c.name, count(s) as count"*/

        /*"MATCH (a:Friend) - [:FRIEND_OF] - (b) - [:FRIEND_OF] - (c), (a) - [:FRIEND_OF] - (c)" +
                "WHERE a.name = 'Michelle1' AND id(b) < id(c) " +
                "RETURN id(b) AS FRIEND_OF_ID, id(c) AS FRIEND_TO_ID, " +
                "b.name AS FRIEND_OF_NAME, c.name AS FRIEND_TO_NAME"*/

        Session session = connection.getSession();
        Result result = session.query(query, emptyMap());
        session.clear();

        return result;
    }
}