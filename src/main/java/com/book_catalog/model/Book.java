package com.book_catalog.model;

import jakarta.persistence.Table;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Table(name = "books")
public class Book extends BaseEntity {
    private String name;
    private String isbnNumber;
    private String author;
    private Date publishedDate;
    private Double price;
    private String type;

    public Book() {
    }

    public Book(String name, String isbnNumber, String author, Date publishedDate, Double price, String type) {
        this.name = name;
        this.isbnNumber = isbnNumber;
        this.author = author;
        this.publishedDate = publishedDate;
        this.price = price;
        this.type = type;
    }
}
