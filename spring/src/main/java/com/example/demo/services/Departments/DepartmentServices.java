package com.example.demo.services.Departments;

import com.example.demo.dto.Departments.DepartmentsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentServices {
    List<DepartmentsDto> getAllDepartments();
}
