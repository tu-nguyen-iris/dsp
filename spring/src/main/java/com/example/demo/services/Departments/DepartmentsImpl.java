package com.example.demo.services.Departments;


import com.example.demo.Entity.Departments;
import com.example.demo.dto.Departments.DepartmentsDto;
import com.example.demo.respository.DepartmentsRepo;
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
