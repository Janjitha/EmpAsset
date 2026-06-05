package com.springboot.JobPortal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JobSeeker {
    //candidate who applies for the job details
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @Column(length = 255)
    private String resumeSummary;
}
