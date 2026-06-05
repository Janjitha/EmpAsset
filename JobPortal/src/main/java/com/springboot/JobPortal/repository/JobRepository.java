package com.springboot.JobPortal.repository;

import com.springboot.JobPortal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByEmployerId(int employerId);

//    @Query("""
//            select j
//            from Job j
//            where j.employer.id=?1
//            """)
//    List<Job> getJobsByEmployer(int employerId);
}
