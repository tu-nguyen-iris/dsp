package com.example.demo.services.Jobs;

import com.example.demo.dto.JobDto.JobDtoDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobsServices {
    List<JobDtoDetail> getAllJob();
}
