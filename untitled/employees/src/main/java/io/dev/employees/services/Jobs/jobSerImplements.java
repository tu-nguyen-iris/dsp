package io.dev.employees.services.Jobs;

import io.dev.employees.Entity.Jobs;
import io.dev.employees.dto.JobDto.JobDtoDetail;
import io.dev.employees.respository.JobsRepo;
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
