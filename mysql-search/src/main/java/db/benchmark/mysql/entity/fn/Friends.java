package db.benchmark.mysql.entity.fn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDS")
@Data
@ToString(exclude = {"friendOf", "friendTo"})
@EqualsAndHashCode(exclude = {"friendOf", "friendTo"})
public class Friends {

    @EmbeddedId
    private FriendId friendId;

    @ManyToOne
    @MapsId("FRIEND_OF")
    @JoinColumn(name = "FRIEND_OF", nullable = false)
    private Friend friendOf;

    @ManyToOne
    @MapsId("FRIEND_TO")
    @JoinColumn(name = "FRIEND_TO", nullable = false)
    private Friend friendTo;
}