package com.springboot.JobPortal.repository;

import com.springboot.JobPortal.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    Page<Application> findByJobSeekerId(int jobSeekerId, Pageable pageable);
}