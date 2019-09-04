package khromov.alex.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "CONTROLLER" )
@Data
public class Controller {

    @Id
    @GeneratedValue
    @Column( name = "CONTROLLER_ID", unique = true, nullable = false )
    private Long id;

    @Column( name = "NAME", nullable = false )
    private String name;

    @OneToMany( mappedBy = "controller" )
    private List< LocationArea > locationAreas;
}