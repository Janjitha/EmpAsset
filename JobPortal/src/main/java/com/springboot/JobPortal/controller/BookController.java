package com.springboot.JobPortal.controller;

import com.springboot.JobPortal.dto.BookReqDto;
import com.springboot.JobPortal.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping("/add/{authorId}")
    public void addBook(
        @Valid @RequestBody BookReqDto dto,
                @PathVariable int authorId){
        bookService.addBook(dto,authorId);
    }
}
