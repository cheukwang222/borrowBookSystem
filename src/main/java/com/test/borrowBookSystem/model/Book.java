package com.test.borrowBookSystem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private boolean borrowed;
    private String description;
    private Date publishDate;

    public Book(String title, String author, Boolean borrowed, String description, Date publishDate) {
        this.title = title;
        this.author = author;
        this.borrowed = borrowed;
        this.description = description;
        this.publishDate = publishDate;
    }

    public Book() {
    }
}
