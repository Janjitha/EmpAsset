package com.springboot.JobPortal.mapper;

import com.springboot.JobPortal.dto.CreateJobReqDto;
import com.springboot.JobPortal.dto.JobResponseDto;
import com.springboot.JobPortal.model.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    public Job mapDtoToEntity(CreateJobReqDto dto){
        Job job = new Job();
        job.setTitle(dto.title());
        job.setDescription(dto.description());
        job.setLocation(dto.location());
        job.setSalary(dto.salary());
        return job;
    }

    public JobResponseDto mapEntityToDto(Job job){
        return new JobResponseDto(
                job.getId(),
                job.getTitle(),
                job.getLocation(),
                job.getSalary(),
                job.getEmployer().getCompanyName()
        );
    }
}
