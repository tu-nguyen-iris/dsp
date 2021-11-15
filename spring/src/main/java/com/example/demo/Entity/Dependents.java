package com.example.demo.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dependents")
public class Dependents {

    @OneToOne
    @JoinColumn(name="employee_id")
    private Employees employeeId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Getter
    private long dependentId;

    @Column
    @Getter
    @Setter
    private String firstName;

    @Column
    @Getter
    @Setter
    private String lastName;

    @Column
    @Getter
    @Setter
    private String relationship;


}
