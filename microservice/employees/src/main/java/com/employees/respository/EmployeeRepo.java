package com.employees.respository;

import com.employees.Entity.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EmployeeRepo extends JpaRepository<Employees, Long> {

    @Query(value = "SELECT * FROM employees WHERE first_name like %:name% or last_name like %:name%", nativeQuery = true)
    Page<Employees> getAllWithName(Pageable pageable, @Param(value = "name") String name);

    //    @Transactional
//    Object save(Employees employees);
    Page<Employees> findAll(Pageable pageable);

    Employees getEmployeesByEmployeeId(Long id);

    //    @Query(value = "")
//    void addEmployee (Employees employees);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employees WHERE employee_id = ?1", nativeQuery = true)
    void deleteEmployees(Long Id);

}
