package com.springboot.JobPortal.service;

import com.springboot.JobPortal.exception.ResourceNotFoundException;
import com.springboot.JobPortal.model.Employer;
import com.springboot.JobPortal.repository.EmployerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;
    public Employer getById(int employerId){
        return employerRepository.findById(employerId).orElseThrow(() ->
                new ResourceNotFoundException("Invalid Employer Id"));
    }
    public Employer getByUsername(String username){
        return employerRepository.findByUserUsername(username);
    }
}