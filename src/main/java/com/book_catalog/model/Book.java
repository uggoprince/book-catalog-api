package com.book_catalog.model;

import jakarta.persistence.Table;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Book extends BaseEntity {
    private String name;
    private String isbnNumber;
    private String author;
    private Date date;
    private Double price;
    private String type;

    public Book() {
    }

    public Book(String name, String isbnNumber, String author, Date date, Double price, String type) {
        this.name = name;
        this.isbnNumber = isbnNumber;
        this.author = author;
        this.date = date;
        this.price = price;
        this.type = type;
    }
}
