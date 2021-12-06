package io.dev.employees.services.Departments;


import io.dev.employees.Entity.Departments;
import io.dev.employees.dto.Departments.DepartmentsDto;
import io.dev.employees.respository.DepartmentsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentsImpl implements DepartmentServices {
    @Autowired
    private DepartmentsRepo departmentsRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DepartmentsDto> getAllDepartments() {
        List<DepartmentsDto> result = new ArrayList<>();
        List<Departments> ders = departmentsRepo.findAll();
        for (Departments department : ders) {
            result.add(modelMapper.map(department, DepartmentsDto.class));
        }
        return result;
//        return null;
    }
}
