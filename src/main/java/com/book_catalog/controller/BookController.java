package com.book_catalog.controller;

import com.book_catalog.dto.request.BookDto;
import com.book_catalog.dto.response.ApiResponse;
import com.book_catalog.model.Book;
import com.book_catalog.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/books")
@Validated
public class BookController {
    private BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity createBook(@Valid @RequestBody BookDto bookDto) {
        Book book = bookService.createBook(bookDto);
        ApiResponse<Object> response =  ApiResponse.builder()
                .message("Book successfully added")
                .success(true)
                .data(book)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
