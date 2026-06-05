package com.springboot.JobPortal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job {
    //showcases job which is being posted
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String location;
    private double salary;
    @ManyToOne
    private Employer employer;
}
