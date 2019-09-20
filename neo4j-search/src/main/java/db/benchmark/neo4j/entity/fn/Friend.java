package db.benchmark.neo4j.entity.fn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.UNDIRECTED;

@NodeEntity
@Data
@ToString(exclude = "friends")
@EqualsAndHashCode(exclude = "friends")
public class Friend {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "FRIEND_OF", direction = UNDIRECTED)
    private List<Friends> friends;
}