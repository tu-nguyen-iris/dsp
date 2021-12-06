package io.dev.employees.services.Departments;

import io.dev.employees.dto.Departments.DepartmentsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentServices {
    List<DepartmentsDto> getAllDepartments();
}
