package com.springboot.JobPortal.service;

import com.springboot.JobPortal.dto.BookReqDto;
import com.springboot.JobPortal.mapper.BookMapper;
import com.springboot.JobPortal.model.Author;
import com.springboot.JobPortal.model.Book;
import com.springboot.JobPortal.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorService authorService;
    public void addBook(BookReqDto dto,int authorId){
        Author author = authorService.getById(authorId);
        Book book = bookMapper.mapDtoToEntity(dto);
        book.setAuthor(author);
        bookRepository.save(book);

    }
}
