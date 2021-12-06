package io.dev.employees.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
public class Regions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long regionId;

    @Column
    @Getter
    @Setter
    private String regionName;

    @Setter
    @Getter
    @OneToMany(mappedBy = "regionId")
    private List<Countries> countriesList;


}
