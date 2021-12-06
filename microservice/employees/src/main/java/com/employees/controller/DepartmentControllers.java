package com.employees.controller;

import com.employees.services.Departments.DepartmentServices;
import com.employees.config.ResponseModified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentControllers {
    @Autowired
    private DepartmentServices departmentServices;

    @GetMapping
    public ResponseEntity getAlldepartments() {
        try {
            Map<String, Object> result = new HashMap<>();
            result.put("data", departmentServices.getAllDepartments());
            return new ResponseEntity<>(new ResponseModified(1, "SUCCESS", result), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModified(0, "SUCCESS", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
