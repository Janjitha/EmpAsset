package com.springboot.JobPortal.controller;

import com.springboot.JobPortal.dto.CreateJobReqDto;
import com.springboot.JobPortal.dto.JobResponseDto;
import com.springboot.JobPortal.service.JobService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor
public class JobController {

    private final JobService jobService;
    @PostMapping
    public void addJob(@Valid @RequestBody CreateJobReqDto dto, Principal principal){
        String username = principal.getName();
        jobService.addJob(dto,username);
    }

    @GetMapping
    public List<JobResponseDto> getAllJobs(@RequestParam int page, @RequestParam int size){
        return jobService.getAllJobs(page,size);
    }
}