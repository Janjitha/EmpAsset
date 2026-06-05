package com.springboot.JobPortal.dto;

import com.springboot.JobPortal.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterReqDto(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotNull
        Role role,
        String companyName,
        String name,
        String email,
        String resumeSummary
) {
}