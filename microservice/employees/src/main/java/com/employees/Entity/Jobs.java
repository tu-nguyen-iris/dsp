package com.employees.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")

public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long jobId;

    @Column
    @Setter
    @Getter
    private String jobTitle;

    @OneToMany(mappedBy = "jobId")
    @Setter
    @Getter
    private List<Employees> employeesList = new ArrayList<>();

    @Column
    @Setter
    @Getter
    private BigDecimal minSalary;

    @Column
    @Setter
    @Getter
    private BigDecimal maxSalary;


}
