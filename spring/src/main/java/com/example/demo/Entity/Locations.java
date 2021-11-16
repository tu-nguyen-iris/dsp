package com.example.demo.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "locations")
public class Locations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long locationId;

    @Column
    @Setter
    @Getter
    private String stressAddress;

    @Column
    @Setter
    @Getter
    private String postalCode;

    @Column
    @Setter
    @Getter
    private String city;

    @Column
    @Setter
    @Getter
    private String stateProvince;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @Setter
    @Getter
    private Countries countryId;


    @Setter
    @Getter
    @OneToMany(mappedBy = "locationId")
    private List<Departments> departmentsList;
}
