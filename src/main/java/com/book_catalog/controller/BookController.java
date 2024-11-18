package com.book_catalog.controller;

import com.book_catalog.dto.request.BookDto;
import com.book_catalog.dto.request.UpdateBookDto;
import com.book_catalog.dto.response.ApiResponse;
import com.book_catalog.exception.ResourceNotFoundException;
import com.book_catalog.model.Book;
import com.book_catalog.service.BookService;
import com.book_catalog.validation.ValidId;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {
    private BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // creates a book
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

    // gets all books
    @GetMapping()
    public ResponseEntity getBooks() {
        Iterable<Book> books = bookService.getBooks();
        ApiResponse<Object> response =  ApiResponse.builder()
                .message("Books successfully retrieved.")
                .success(true)
                .data(books)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // updates a single book
    @PutMapping("/{id}")
    public ResponseEntity updateBook(@ValidId @PathVariable Long id,
                                     @Valid @RequestBody UpdateBookDto bookDto) throws ParseException {
        Book book = bookService.getBook(id);
        if (book == null) {
            throw new ResourceNotFoundException("Book not found.");
        }
        Book updatedBook = bookService.updatebook(bookDto, book);
        ApiResponse<Object> response =  ApiResponse.builder()
                .message("Book successfully updated.")
                .success(true)
                .data(updatedBook)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // deletes a single book
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@ValidId @PathVariable Long id) {
        Book book = bookService.getBook(id);
        if (book == null) {
            throw new ResourceNotFoundException("Book not found.");
        }
        bookService.deleteBook(id);
        ApiResponse<Object> response = ApiResponse.builder()
                .message("Book Successfully deleted.")
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
