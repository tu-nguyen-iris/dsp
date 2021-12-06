package com.employees.controller;

import com.employees.config.ResponseModified;
import com.employees.dto.JobDto.JobDtoDetail;
import com.employees.services.Jobs.JobsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/job")
@CrossOrigin(origins = "http://localhost:8081")
public class JobController {

    @Autowired
    private JobsServices jobsServices;

    @GetMapping
    public ResponseEntity getAlljob() {
        try {
            List<JobDtoDetail> res = jobsServices.getAllJob();
            Map<String, Object> response = new HashMap<>();
            response.put("data", res);
            return new ResponseEntity<>(new ResponseModified(1, "SUCCESS", response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModified(0, "ERROR", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
