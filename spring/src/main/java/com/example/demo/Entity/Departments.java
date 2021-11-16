package com.example.demo.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private List<Employees> employeesList = new ArrayList<>();

    @Column
    @Getter
    @Setter
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @Getter
    @Setter
    private Locations locationId;
}
