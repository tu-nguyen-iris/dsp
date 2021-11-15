package com.example.demo.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Departments {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @OneToMany(mappedBy = "departmentId")
    @Getter
    @Setter
    private Set<Employees> employees = new HashSet<>();

    @Column
    @Getter
    @Setter
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "location_id")
    @Getter
    @Setter
    private Locations locationId;
}
