package db.benchmark.mysql.entity.fn;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class FriendId implements Serializable {

    @Column(name = "FRIEND_OF")
    private Long friendOf;

    @Column(name = "FRIEND_TO")
    private Long friendTo;
}