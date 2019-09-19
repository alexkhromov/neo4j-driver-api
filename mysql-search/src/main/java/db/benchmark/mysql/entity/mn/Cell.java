package db.benchmark.mysql.entity.mn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CELL")
@Data
@ToString(exclude = "locationArea")
@EqualsAndHashCode(exclude = "locationArea")
public class Cell {

    @Id
    @GeneratedValue
    @Column(name = "CELL_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "LOCATION_AREA_ID", nullable = false)
    private LocationArea locationArea;

    @OneToMany(mappedBy = "cell")
    private List<Sector> sectors;
}