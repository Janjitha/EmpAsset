package com.springboot.JobPortal.service;

import com.springboot.JobPortal.dto.RegisterReqDto;
import com.springboot.JobPortal.enums.Role;
import com.springboot.JobPortal.model.Employer;
import com.springboot.JobPortal.model.JobSeeker;
import com.springboot.JobPortal.model.User;
import com.springboot.JobPortal.repository.EmployerRepository;
import com.springboot.JobPortal.repository.JobSeekerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final EmployerRepository employerRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final PasswordEncoder passwordEncoder;
    public void register(RegisterReqDto dto){
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role());
        user = userService.save(user);
        if(dto.role() == Role.EMPLOYER){
            Employer employer = new Employer();
            employer.setCompanyName(dto.companyName());
            employer.setUser(user);
            employerRepository.save(employer);
        }
        if(dto.role() == Role.SEEKER){
            JobSeeker jobSeeker = new JobSeeker();
            jobSeeker.setName(dto.name());
            jobSeeker.setEmail(dto.email());
            jobSeeker.setPassword(passwordEncoder.encode(dto.password()));
            jobSeeker.setResumeSummary(dto.resumeSummary());
            jobSeekerRepository.save(jobSeeker);
        }
    }
}