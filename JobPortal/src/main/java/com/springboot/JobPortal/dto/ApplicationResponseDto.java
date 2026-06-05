package com.springboot.JobPortal.dto;

import java.time.Instant;

public record ApplicationResponseDto(
        int applicationId,
        Instant appliedAt,
        String jobTitle,
        String companyName
) {
}
