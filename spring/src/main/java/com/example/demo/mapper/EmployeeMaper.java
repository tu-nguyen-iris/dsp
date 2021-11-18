package com.example.demo.mapper;

import com.example.demo.Entity.Employees;
import com.example.demo.dto.employee.EmployeeDto;
import com.example.demo.dto.employee.EmployeeDtoDetail;
import com.example.demo.respository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMaper {
    @Autowired
    private EmployeeRepo employeeRepo;

    public EmployeeDto toEmployeeDto(Employees employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setHireDate(employee.getHireDate());
        employeeDto.setJobId(employee.getJobId().getJobId());
        employeeDto.setDepartmentId(employee.getDepartmentId().getDepartmentId());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }

    /**
     * using orm query
     *
     * @param employee
     * @return
     */
    public EmployeeDtoDetail toEmployeeDetailDto(Employees employee) {
        EmployeeDtoDetail employeeDto = new EmployeeDtoDetail();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setHireDate(employee.getHireDate());
        employeeDto.setJobTitle(employee.getJobId().getJobTitle());
        employeeDto.setJobId(employee.getJobId().getJobId());
        employeeDto.setDepartmentId(employee.getDepartmentId().getDepartmentId());
        employeeDto.setDepartmentName(employee.getDepartmentId().getDepartmentName());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }
}
