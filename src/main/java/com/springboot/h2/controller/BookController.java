package com.springboot.h2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.springboot.h2.entity.Book;
import com.springboot.h2.entity.BookDto;
import com.springboot.h2.exception.EntityNotFound;
import com.springboot.h2.service.BookService;

import java.util.List;

//lombok annotation for logger
@Slf4j
//spring annotations
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService service;

    // HTTP GET URL - http://localhost:9500/api/books
    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooks() {
        log.info("Getting all books from the db");
        return service.getBooks();
    }

    // HTTP GET URL - http://localhost:9500/api/books/<book_genre>
    @GetMapping("/books/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooksByGenre(@PathVariable("genre") final String genre) {
        log.info("Getting books by genre = {} from the db", genre);
        return service.getBooksByGenre(genre);
    }

    // HTTP GET URL - http://localhost:9500/api/book/<book_id>
    @GetMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable("id") final Long id) throws EntityNotFound {
        log.info("Getting book id = {} from the db", id);
        return service.getBookById(id);
    }

    // HTTP DELETE URL - http://localhost:9500/api/book/<book_id>
    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") final Long id) throws EntityNotFound {
        log.info("Delete book id = {} from the db", id);
        service.delete(id);
    }

    // HTTP PUT URL  - http://localhost:9500/api/book/<book_id>
    // Sample request body
    /*
    {
        "author": "J. K. Rowling",
        "genre": "Fantasy Fiction",
        "publisher": "Bloomsbury Publishing",
        "title": "Harry Potter",
        "quantity": 100
    }
    */
    @PutMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") final Long id, @RequestBody final BookDto dto)
            throws EntityNotFound {
        log.info("Updating book id = {} into the db", id);
        service.update(id, dto);
    }

    // HTTP POST URL  - http://localhost:9500/api/book
    // Sample request body
    /*
    {
        "author": "Vasdev Mohi",
        "genre": "Ghazals",
        "publisher": "Central Sahitya Akademi",
        "title": "Cheque book",
        "quantity": 5
    }
    */
    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody final BookDto dto) {
        log.info("Saving new book = {} into the db", dto.toString());
        service.save(dto);
    }

    // HTTP POST URL - http://localhost:9500/api/books/count/<book_genre>
    @GetMapping("/books/count/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public long getCountByGenre(@PathVariable("genre") final String genre) {
        return service.getCountByGenre(genre);
    }
}
