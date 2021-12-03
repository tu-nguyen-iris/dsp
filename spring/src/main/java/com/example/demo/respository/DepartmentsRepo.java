package com.example.demo.respository;

import com.example.demo.Entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentsRepo extends JpaRepository<Departments, Long> {

    List<Departments> findAll();

}
