package com.springboot.JobPortal.repository;

import com.springboot.JobPortal.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
