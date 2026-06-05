package com.springboot.JobPortal.mapper;

import com.springboot.JobPortal.dto.ApplicationResponseDto;
import com.springboot.JobPortal.model.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    public ApplicationResponseDto mapEntityToDto(Application application){
        return new ApplicationResponseDto(
                application.getId(),
                application.getAppliedAt(),
                application.getJob().getTitle(),
                application.getJob().getEmployer().getCompanyName());
    }
}