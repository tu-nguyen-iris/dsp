package io.dev.employees.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Countries {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long countryId;

    @Column
    @Setter
    @Getter
    private String country_name;

    @Setter
    @Getter
    @OneToMany(mappedBy = "locationId")
    private List<Locations> locationList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @Setter
    @Getter
    private Regions regionId;


}
