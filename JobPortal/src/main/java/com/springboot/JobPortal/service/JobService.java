package com.springboot.JobPortal.service;

import com.springboot.JobPortal.dto.CreateJobReqDto;
import com.springboot.JobPortal.dto.JobResponseDto;
import com.springboot.JobPortal.exception.ResourceNotFoundException;
import com.springboot.JobPortal.mapper.JobMapper;
import com.springboot.JobPortal.model.Employer;
import com.springboot.JobPortal.model.Job;
import com.springboot.JobPortal.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final EmployerService employerService;
    private final JobMapper jobMapper;
    public void addJob(CreateJobReqDto dto, String username){
        Employer employer = employerService.getByUsername(username);
        Job job = jobMapper.mapDtoToEntity(dto);
        job.setEmployer(employer);
        jobRepository.save(job);
    }

    public List<JobResponseDto> getAllJobs(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Job> pages = jobRepository.findAll(pageable);
        return pages.getContent()
                .stream()
                .map(jobMapper::mapEntityToDto)
                .toList();
    }
    public Job getById(int jobId){
        return jobRepository.findById(jobId).orElseThrow(() ->
                new ResourceNotFoundException("Invalid Job Id"));
    }
}