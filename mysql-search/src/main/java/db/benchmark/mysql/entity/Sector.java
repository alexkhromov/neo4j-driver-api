package db.benchmark.mysql.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table( name = "SECTOR" )
@Data
@ToString( exclude = "cell" )
@EqualsAndHashCode( exclude = "cell" )
public class Sector {

    @Id
    @GeneratedValue
    @Column( name = "SECTOR_ID", unique = true, nullable = false )
    private Long id;

    @Column( name = "NAME", nullable = false )
    private String name;

    @Column( name = "TYPE", nullable = false )
    private String type;

    @Column( name = "AZIMUTH", nullable = false )
    private String azimuth;

    @ManyToOne
    @JoinColumn( name = "CELL_ID", nullable = false )
    private Cell cell;
}