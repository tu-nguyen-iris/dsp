package io.dev.employees.services.Jobs;

import io.dev.employees.dto.JobDto.JobDtoDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobsServices {
    List<JobDtoDetail> getAllJob();
}
