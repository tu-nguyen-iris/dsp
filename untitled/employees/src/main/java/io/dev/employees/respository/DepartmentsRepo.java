package io.dev.employees.respository;

import io.dev.employees.Entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentsRepo extends JpaRepository<Departments, Long> {

    List<Departments> findAll();

}
