package com.employees.services.Employees;

import com.employees.config.ResponseModified;
import com.employees.dto.employee.EmployeeDto;
import com.employees.dto.employee.EmployeeDtoDetail;
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
