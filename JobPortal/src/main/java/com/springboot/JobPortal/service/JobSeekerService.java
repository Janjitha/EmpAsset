package com.springboot.JobPortal.service;

import com.springboot.JobPortal.exception.ResourceNotFoundException;
import com.springboot.JobPortal.model.JobSeeker;
import com.springboot.JobPortal.repository.JobSeekerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobSeekerService {
    private final JobSeekerRepository jobSeekerRepository;
    public JobSeeker getById(int jobSeekerId){
        return jobSeekerRepository.findById(jobSeekerId).orElseThrow(() ->
                new ResourceNotFoundException("Invalid JobSeeker Id"));
    }
    public JobSeeker getByEmail(String email){
        return jobSeekerRepository.findByEmail(email);
    }
}