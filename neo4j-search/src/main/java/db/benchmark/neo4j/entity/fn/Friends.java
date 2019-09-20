package db.benchmark.neo4j.entity.fn;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "FRIEND_OF")
@Data
public class Friends {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Friend friendOf;

    @EndNode
    private Friend friendTo;
}