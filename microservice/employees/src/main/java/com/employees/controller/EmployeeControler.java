package com.employees.controller;

import com.employees.config.ResponseModified;
import com.employees.dto.employee.EmployeeDto;
import com.employees.dto.employee.EmployeeDtoDetail;
import com.employees.services.Employees.EmployeeSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/employees")
@CrossOrigin(origins = "http://localhost:8081")
public class EmployeeControler {

    @Autowired
    private EmployeeSevices employeeSevices;

    @GetMapping
    public ResponseEntity getAllProductByName(@RequestParam(value = "page") Integer page, @RequestParam(value = "name") String name) {
        try {
            Pageable pageable = PageRequest.of((page - 1 < 0 ? page : page - 1), 10);
            Map<String, Object> response;
            if (name == null || name.equals("")) {
                response = employeeSevices.findAll(pageable);
                ResponseModified res = new ResponseModified(1, "SUCCESS", response);
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                response = employeeSevices.findAllEmployeeByName(pageable, name);
                ResponseModified res = new ResponseModified(1, "SUCCESS", response);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModified(0, "ERROR", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getDetailEmployee(@PathVariable Long id) {
        try {
            EmployeeDtoDetail data = employeeSevices.getDetailEmployees(id);
            Map<String, Object> res = new HashMap<>();
            if (data == null) {
                return new ResponseEntity<>(new ResponseModified(0, "EMPLOYEES NOT FOUND!", null), HttpStatus.OK);
            } else {
                res.put("detail", data);
                return new ResponseEntity<>(new ResponseModified(1, "SUCCESS", res), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModified(0, "ERROR", null), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteEmployee(@RequestParam(name = "employee_id") Long employee_id) {
        try {
            boolean result = employeeSevices.deleteByEmployeeId(employee_id);
            if (result) {
                return new ResponseEntity<>(new ResponseModified(1, "DELETE SUCCESS", null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseModified(0, "ERROR", null), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            ResponseModified res = employeeSevices.addAnEmployee(employeeDto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity editEmployees(@RequestBody EmployeeDto employeeDto) {
        try {
            ResponseModified result = employeeSevices.editEmployee(employeeDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModified(0, "ERROR", null), HttpStatus.BAD_REQUEST);
        }
    }
}
