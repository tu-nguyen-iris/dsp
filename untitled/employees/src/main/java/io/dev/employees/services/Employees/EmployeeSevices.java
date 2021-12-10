package io.dev.employees.services.Employees;

import io.dev.employees.config.ResponseModified;
import io.dev.employees.dto.employee.EmployeeDto;
import io.dev.employees.dto.employee.EmployeeDtoDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EmployeeSevices {
    Map<String, Object> findAllEmployeeByName(Pageable pageable, String name);

    Map<String, Object> findAll(Pageable pageable);

    boolean deleteByEmployeeId(Long Id);

    EmployeeDtoDetail getDetailEmployees(Long id);

    ResponseModified addAnEmployee(EmployeeDto employeeDto);

    ResponseModified editEmployee(EmployeeDto employeeDto);

    boolean delMultiEmployees(List<Long> lst_employees);
}
