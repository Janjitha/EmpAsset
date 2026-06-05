package com.springboot.JobPortal.service;

import com.springboot.JobPortal.exception.ResourceNotFoundException;
import com.springboot.JobPortal.model.Author;
import com.springboot.JobPortal.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AuthorService {
    private final AuthorRepository authorRepository;
    public  Author getById(int authorId){
        return authorRepository.findById(authorId).orElseThrow(()->
                new ResourceNotFoundException("Invalid id"));
    }

}
