package com.employees.respository;

import com.employees.Entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentsRepo extends JpaRepository<Departments, Long> {

    List<Departments> findAll();

}
