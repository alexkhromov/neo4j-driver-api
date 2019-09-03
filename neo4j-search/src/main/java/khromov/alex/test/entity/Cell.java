package khromov.alex.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@NodeEntity
@Data
@ToString( exclude = "locationArea" )
@EqualsAndHashCode( exclude = "locationArea" )
public class Cell {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship( type = "HAS_CELL", direction = INCOMING )
    private LocationArea locationArea;

    @Relationship( type = "HAS_SECTOR" )
    private List< Sector > sectors;
}