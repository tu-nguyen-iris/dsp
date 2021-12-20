package io.dev.employees.controller;

import io.dev.employees.config.ResponseModified;
import io.dev.employees.config.SystemMessage;
import io.dev.employees.services.Departments.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins = "http://localhost:8762")
public class DepartmentControllers {
    @Autowired
    private DepartmentServices departmentServices;

    @GetMapping
    public ResponseEntity getAlldepartments() {
        try {
            Map<String, Object> result = new HashMap<>();
            result.put("data", departmentServices.getAllDepartments());
            return new ResponseEntity<>(new ResponseModified(1, SystemMessage.HANDLE_SUCCESS, result), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModified(0, SystemMessage.HAS_AN_ERROR, null), HttpStatus.OK);
        }
    }
}
