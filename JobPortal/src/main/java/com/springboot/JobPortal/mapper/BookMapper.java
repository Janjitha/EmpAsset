package com.springboot.JobPortal.mapper;

import com.springboot.JobPortal.dto.BookReqDto;
import com.springboot.JobPortal.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book mapDtoToEntity(BookReqDto dto){
        Book book = new Book();
        book.setTitle(dto.title());
        book.setSummary(dto.summary());
        return  book;
    }
}
