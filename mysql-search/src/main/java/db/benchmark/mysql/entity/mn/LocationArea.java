package db.benchmark.mysql.entity.mn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LOCATION_AREA")
@Data
@ToString(exclude = "controller")
@EqualsAndHashCode(exclude = "controller")
public class LocationArea {

    @Id
    @GeneratedValue
    @Column(name = "LOCATION_AREA_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "CONTROLLER_ID", nullable = false)
    private Controller controller;

    @OneToMany(mappedBy = "locationArea")
    private List<Cell> cells;
}