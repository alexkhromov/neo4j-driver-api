package db.benchmark.neo4j.service;

import db.benchmark.neo4j.connection.Connection;
import lombok.AllArgsConstructor;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;

import static java.util.Collections.emptyMap;

@AllArgsConstructor
public class SearchService {

    private Connection connection;

    public Result search(String query) {

        Session session = connection.getSession();
        Result result = session.query(query, emptyMap());
        session.clear();

        return result;
    }
}