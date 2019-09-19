package db.benchmark.neo4j.entity.mn;

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
@ToString(exclude = "controller")
@EqualsAndHashCode(exclude = "controller")
public class LocationArea {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "HAS_LOCATION", direction = INCOMING)
    private Controller controller;

    @Relationship(type = "HAS_CELL")
    private List<Cell> cells;
}