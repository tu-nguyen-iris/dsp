package com.example.demo.Entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "employees")
@Table(name = "employees")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employees {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id",referencedColumnName = "jobId")
    private Jobs jobId;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id",referencedColumnName = "departmentId")
    private Departments departmentId;

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
    private String email;

    @Column
    @Getter
    @Setter
    private String phoneNumber;

    @Column
    @Getter
    @Setter
    private Date hireDate;

    @Column
    @Getter
    @Setter
    private BigDecimal salary;


}
