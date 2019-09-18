package db.benchmark.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
@Data
public class Controller {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship( type = "HAS_LOCATION" )
    private List< LocationArea > locationAreas;
}