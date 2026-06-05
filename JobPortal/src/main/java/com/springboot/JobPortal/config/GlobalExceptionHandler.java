package com.springboot.JobPortal.config;

import com.springboot.JobPortal.exception.ResourceNotFoundException;
import com.springboot.JobPortal.utility.ResponseUtility;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private ResponseUtility responseUtility;

    //handler method
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseUtility> handleResourceNotFoundException(
            ResourceNotFoundException e
    ) {
        logger.error("Error in fetching user details {}", e.getMessage());
        responseUtility.setMessage(e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(responseUtility);
    }
}