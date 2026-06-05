package com.springboot.JobPortal.repository;

import com.springboot.JobPortal.model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerRepository extends JpaRepository<JobSeeker,Integer> {
    JobSeeker findByEmail(String email);
    //select * from job_seeker where email=?1
}