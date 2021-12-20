package io.dev.employees.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "employees")
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", referencedColumnName = "jobId")
    private Jobs jobId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "departmentId")
    private Departments departmentId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private Date hireDate;

    @Column
    private BigDecimal salary;

}
