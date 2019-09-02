package khromov.alex.test;

import khromov.alex.test.connection.Connection;
import khromov.alex.test.service.SearchService;

public class Application {

    public static void main( String[] args ) {

        SearchService searchService = new SearchService( new Connection() );

        searchService.search();
    }
}