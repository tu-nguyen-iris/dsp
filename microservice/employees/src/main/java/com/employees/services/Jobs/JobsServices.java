package com.employees.services.Jobs;

import com.employees.dto.JobDto.JobDtoDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobsServices {
    List<JobDtoDetail> getAllJob();
}
