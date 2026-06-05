package com.springboot.JobPortal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateJobReqDto(
    @NotBlank
    String title,
    @NotBlank
    String description,
    @NotBlank
    String location,
    @NotNull
    double salary
){

}
