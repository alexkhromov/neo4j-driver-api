package db.benchmark.mysql.service;

import db.benchmark.mysql.connection.Connection;
import lombok.Builder;
import org.hibernate.Session;

import java.util.List;

@Builder
public class SearchService {

    private Connection connection;

    public List searchMN() {

        Session session = connection.getSession();
        List<?> result = session.createQuery(

                "SELECT c.name AS NAME, COUNT(s) AS COUNT FROM Sector AS s " +
                "INNER JOIN s.cell AS ce INNER JOIN ce.locationArea AS la INNER JOIN la.controller AS c " +
                "WHERE s.type = '4G' AND s.azimuth = '0' " +
                "GROUP BY c.name"

                ).list();
        session.close();

        return result;
    }

    public List searchFN() {

        Session session = connection.getSession();
        List<?> result = session.createNativeQuery(

                "WITH GIVEN_ID AS (SELECT ID FROM FRIEND AS F WHERE F.NAME = 'Michelle1'), " +
                "ALL_FRIENDS_IDS AS ( " +
                    "SELECT FS1.FRIEND_TO AS FRIEND_ID FROM FRIENDS AS FS1 " +
                    "WHERE FS1.FRIEND_OF = (SELECT ID FROM GIVEN_ID) " +
                        "UNION ALL " +
                    "SELECT FS2.FRIEND_OF AS FRIEND_ID FROM FRIENDS AS FS2 " +
                    "WHERE FS2.FRIEND_TO = (SELECT ID FROM GIVEN_ID) " +
                ") " +
                "SELECT FF.FRIEND_OF AS FRIEND_OF_ID, FF.FRIEND_TO AS FRIEND_TO_ID, " +
                "F_OF.NAME AS FRIEND_OF_NAME, F_TO.NAME AS FRIEND_TO_NAME FROM FRIENDS FF " +
                "INNER JOIN FRIEND AS F_OF ON F_OF.ID = FF.FRIEND_OF " +
                "INNER JOIN FRIEND AS F_TO ON F_TO.ID = FF.FRIEND_TO " +
                "WHERE FF.FRIEND_OF IN (SELECT * FROM ALL_FRIENDS_IDS) " +
                "AND FF.FRIEND_TO IN (SELECT * FROM ALL_FRIENDS_IDS)"

        ).list();
        session.close();

        return result;
    }
}