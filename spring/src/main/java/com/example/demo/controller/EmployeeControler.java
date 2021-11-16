package com.example.demo.controller;
import com.example.demo.config.ResponseModified;
import com.example.demo.dto.employee.EmployeeDto;
import com.example.demo.dto.employee.EmployeeDtoDetail;
import com.example.demo.services.EmployeeSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/employees")
@CrossOrigin(origins = "http://localhost:8080")
public class EmployeeControler {

    @Autowired
    private EmployeeSevice employeeSevice;

    @GetMapping
    public ResponseEntity getAllProductByName(@RequestParam(value = "page") Integer page, @RequestParam(value = "name") String name) {
        try {
           Pageable pageable = PageRequest.of((page - 1 < 0 ? page : page - 1), 10);
            Map<String, Object> response;
            if (name == null || name.equals("")) {
                response = employeeSevice.findAll(pageable);
                ResponseModified res = new ResponseModified(1, "SUCCESS", response);
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                response = employeeSevice.findAllEmployeeByName(pageable, name);
                ResponseModified res = new ResponseModified(1, "SUCCESS", response);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(new ResponseModified(0, "ERROR", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getDetailEmployee(@PathVariable Long id){
            try{
                EmployeeDtoDetail data = employeeSevice.getDetailEmployees(id);
                Map<String,Object> res = new HashMap<>();
                if(data == null){
                    return new ResponseEntity<>(new ResponseModified(0, "EMPLOYEES NOT FOUND!", null),HttpStatus.OK);
                }else {
                    res.put("detail",data);
                    return new ResponseEntity<>(new ResponseModified(1, "SUCCESS", res),HttpStatus.OK);
                }


            }catch (Exception e){
                return new ResponseEntity<>(new ResponseModified(0, "ERROR", null),HttpStatus.EXPECTATION_FAILED);
            }
    }


    @DeleteMapping
    public ResponseEntity deleteEmployee(@RequestParam(name = "employee_id") Long employee_id) {
        try {
            boolean result = employeeSevice.deleteByEmployeeId(employee_id);
            if (result) {
                return new ResponseEntity<>(new ResponseModified(1, "DELETE SUCCESS",null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseModified(0, "ERROR",null), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            ResponseModified res = employeeSevice.addAnEmployee(employeeDto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
