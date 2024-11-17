package com.book_catalog.service;

import com.book_catalog.dto.request.BookDto;
import com.book_catalog.model.Book;
import com.book_catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookDto bookDto) {
        Book book = new Book(
                bookDto.getName(),
                bookDto.getIsbnNumber(),
                bookDto.getAuthor(),
                bookDto.getDate(), bookDto.getPrice(), bookDto.getType());
        return bookRepository.save(book);
    }

    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }
}
