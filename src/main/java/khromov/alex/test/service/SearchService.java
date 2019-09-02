package khromov.alex.test.service;

import khromov.alex.test.connection.Connection;
import khromov.alex.test.entity.Sector;
import org.neo4j.ogm.cypher.query.Pagination;

import java.util.Collection;

public class SearchService {

    private Connection connection;

    public SearchService( Connection connection ) {
        this.connection = connection;
    }

    public Collection< Sector > search() {

        Collection< Sector > sectors = connection.getSession().loadAll( Sector.class, new Pagination( 0, 25 ) );

        System.out.println( sectors );

        return sectors;
    }
}