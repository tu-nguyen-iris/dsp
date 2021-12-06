package com.employees.services.Departments;

import com.employees.dto.Departments.DepartmentsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentServices {
    List<DepartmentsDto> getAllDepartments();
}
