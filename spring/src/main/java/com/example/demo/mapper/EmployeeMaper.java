package com.example.demo.mapper;

import com.example.demo.Entity.Employees;
import com.example.demo.dto.EmployeeDto;

public class EmployeeMaper {
    public static EmployeeDto toEmployeeDto(Employees employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setHireDate(employee.getHireDate());
        employeeDto.setManagerId(employee.getManagerId());
        employeeDto.setJobId(employee.getJobId().getJobId());
        employeeDto.setDepartmentId(employee.getDepartmentId().getDepartmentId());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }
}
