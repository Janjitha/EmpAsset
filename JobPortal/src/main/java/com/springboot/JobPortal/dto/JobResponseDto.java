package com.springboot.JobPortal.dto;

public record JobResponseDto(
        int jobId,
        String title,
        String location,
        double salary,
        String companyName
) {
}
