package com.example.demo.services.Jobs;

import com.example.demo.Entity.Jobs;
import com.example.demo.dto.JobDto.JobDtoDetail;
import com.example.demo.respository.JobsRepo;
import com.example.demo.services.Jobs.JobsServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class jobSerImplements implements JobsServices {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JobsRepo jobsRepo;

    @Override
    public List<JobDtoDetail> getAllJob() {
        try {
            List<Jobs> jobsList = jobsRepo.findAll();
            List<JobDtoDetail> result = new ArrayList<>();
            for (Jobs jobs : jobsList) {
                result.add(modelMapper.map(jobs, JobDtoDetail.class));
            }
            return result;
        } catch (DataAccessException e) {
            System.out.println(e);
        }
        return null;
    }
}
