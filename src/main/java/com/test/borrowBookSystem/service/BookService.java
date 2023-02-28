package com.test.borrowBookSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.borrowBookSystem.repository.BookRepository;
import com.test.borrowBookSystem.exception.BookNotAvailableException;
import com.test.borrowBookSystem.model.Book;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    public Book addBook(Book book){
        return bookRepository.saveAndFlush(book);
    }

    public Book updateBook(Long id, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setDescription(book.getDescription());
            existingBook.setBorrowed(book.isBorrowed());
            existingBook.setPublishDate(book.getPublishDate());
            return bookRepository.save(existingBook);
        }
        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book borrowBook(long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (!book.isBorrowed()) {
                book.setBorrowed(true);
                return bookRepository.save(book);
            }
        } 
        throw new BookNotAvailableException("Book not available");
    }
}
