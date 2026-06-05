package com.springboot.JobPortal.service;

import com.springboot.JobPortal.dto.ApplicationResponseDto;
import com.springboot.JobPortal.mapper.ApplicationMapper;
import com.springboot.JobPortal.model.Application;
import com.springboot.JobPortal.model.Job;
import com.springboot.JobPortal.model.JobSeeker;
import com.springboot.JobPortal.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobService jobService;
    private final JobSeekerService jobSeekerService;
    private final ApplicationMapper applicationMapper;
    public void applyJob(int jobId, String email){
        // jobseeker - job - application - save
        JobSeeker jobSeeker = jobSeekerService.getByEmail(email);
        Job job = jobService.getById(jobId);
        Application application = new Application();
        application.setJob(job);
        application.setJobSeeker(jobSeeker);
        applicationRepository.save(application);
    }
    public List<ApplicationResponseDto> getMyApplications(String email, int page, int size){
        JobSeeker jobSeeker = jobSeekerService.getByEmail(email);
        Pageable pageable = PageRequest.of(page, size);
        Page<Application> pages = applicationRepository.findByJobSeekerId(jobSeeker.getId(), pageable); // ← CHANGE findAll to findByJobSeekerId
        return pages.getContent()
                .stream()
                .map(applicationMapper::mapEntityToDto)
                .toList();
    }

}