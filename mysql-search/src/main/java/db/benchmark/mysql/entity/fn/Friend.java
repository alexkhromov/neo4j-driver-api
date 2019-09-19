package db.benchmark.mysql.entity.fn;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FRIEND")
@Data
public class Friend {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "friendOf")
    private List<Friends> friendsOf;

    @OneToMany(mappedBy = "friendTo")
    private List<Friends> friendsTo;
}