package com.test.borrowBookSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.borrowBookSystem.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
