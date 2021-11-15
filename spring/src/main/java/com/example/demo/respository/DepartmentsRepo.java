package com.example.demo.respository;

import com.example.demo.Entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepo extends JpaRepository<Departments,Long> {

}
