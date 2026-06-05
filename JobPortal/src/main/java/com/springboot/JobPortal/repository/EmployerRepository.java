package com.springboot.JobPortal.repository;

import com.springboot.JobPortal.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer,Integer> {
    Employer findByUserUsername(String username);

}