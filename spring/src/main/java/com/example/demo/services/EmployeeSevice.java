package com.example.demo.services;
import com.example.demo.Entity.Employees;
import com.example.demo.config.ResponseModified;
import com.example.demo.dto.EmployeeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface EmployeeSevice {
    Map<String, Object> findAllEmployeeByName(Pageable pageable, String name);

    Map<String, Object> findAll(Pageable pageable);

    boolean deleteByEmployeeId(Long Id);
    EmployeeDto getDetailEmployees(Long id);

    ResponseModified addAnEmployee(EmployeeDto employeeDto );
}
