package com.book_catalog.service;

import com.book_catalog.dto.request.BookDto;
import com.book_catalog.dto.request.UpdateBookDto;
import com.book_catalog.exception.BadRequestException;
import com.book_catalog.model.Book;
import com.book_catalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public class BookService {
    BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // creates a book
    public Book createBook(BookDto bookDto) {
        Book book = new Book(
                bookDto.getName(),
                bookDto.getIsbnNumber(),
                bookDto.getAuthor(),
                bookDto.getPublishDate(), bookDto.getPrice(), bookDto.getType());
        return bookRepository.save(book);
    }

    // gets all book
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    // gets a single book by id
    public Book getBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseGet(() -> null);
    }

    // update a single book
    public Book updatebook(UpdateBookDto bookDto, Book book) throws ParseException {
        boolean canUpdate = false;
        if (bookDto.getAuthor() != null) {book.setAuthor(bookDto.getAuthor()); canUpdate = true;}
        if (bookDto.getName() != null) {book.setName(bookDto.getName()); canUpdate = true;}
        if (bookDto.getIsbnNumber() != null) {book.setIsbnNumber(bookDto.getIsbnNumber()); canUpdate = true;}
        if (bookDto.getPrice() != null) {book.setPrice(bookDto.getPrice()); canUpdate = true;}
        if (bookDto.getType() != null) {book.setType(bookDto.getType()); canUpdate = true;}
        if (bookDto.getPublishDate() != null) {
            book.setPublishDate(bookDto.getPublishDate());
            canUpdate = true;
        }
        if (!canUpdate) {
            throw new BadRequestException("Request body is empty.");
        }
        return bookRepository.save(book);
    }

    // delete a single book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
