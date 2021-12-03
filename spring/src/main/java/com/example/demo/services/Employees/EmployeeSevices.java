package com.example.demo.services.Employees;

import com.example.demo.Entity.Employees;
import com.example.demo.config.ResponseModified;
import com.example.demo.dto.employee.EmployeeDto;
import com.example.demo.dto.employee.EmployeeDtoDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface EmployeeSevices {
    Map<String, Object> findAllEmployeeByName(Pageable pageable, String name);

    Map<String, Object> findAll(Pageable pageable);

    boolean deleteByEmployeeId(Long Id);

    EmployeeDtoDetail getDetailEmployees(Long id);

    ResponseModified addAnEmployee(EmployeeDto employeeDto);

    ResponseModified editEmployee(EmployeeDto employeeDto);

}