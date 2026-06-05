package com.springboot.JobPortal.controller;

import com.springboot.JobPortal.dto.ApplicationResponseDto;
import com.springboot.JobPortal.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    @PostMapping("/applications")
    public void applyJob(@RequestParam int jobId, Principal principal){
        String email = principal.getName();
        applicationService.applyJob(jobId, email);
    }
    @GetMapping("/myapplications")
    public List<ApplicationResponseDto> getMyApplications(Principal principal,
                                                          @RequestParam int page,
                                                          @RequestParam int size){
        String email = principal.getName();
        return applicationService.getMyApplications(email, page, size);
    }
}