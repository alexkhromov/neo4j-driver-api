package db.benchmark.neo4j.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@NodeEntity
@Data
@ToString( exclude = "cell" )
@EqualsAndHashCode( exclude = "cell" )
public class Sector {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String type;
    private String azimuth;

    @Relationship( type = "HAS_SECTOR", direction = INCOMING )
    private Cell cell;
}