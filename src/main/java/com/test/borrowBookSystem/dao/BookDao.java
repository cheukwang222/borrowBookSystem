package com.test.borrowBookSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.borrowBookSystem.repository.BookRepository;

@Component
public class BookDao {

    private final BookRepository bookRepository;

    @Autowired
    public BookDao(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

}
