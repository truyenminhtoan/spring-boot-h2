package com.springboot.h2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.h2.entity.Book;
import com.springboot.h2.entity.BookDto;
import com.springboot.h2.exception.EntityNotFound;
import com.springboot.h2.repo.BookRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//spring stereotype annotation
@Service
public class BookService {

    @Autowired
    BookRepository repository;

    //get total books count
    public long getBooksCount() {
        return repository.count();
    }

    //save new book
    public void save(final BookDto dto) {
        final Book b = createBookBuilder(dto).build();
        repository.save(b);
    }

    //get all books
    public List<Book> getBooks() {
        final Iterable<Book> allBooks = repository.findAll();
        return StreamSupport.stream(allBooks.spliterator(), false).collect(Collectors.toList());
    }

    //get book by id
    public Book getBookById(final Long id) throws EntityNotFound {
        return repository.findById(id).orElseThrow(EntityNotFound::new);
    }

    //update book by id
    public void update(final Long id, final BookDto dto) throws EntityNotFound {
        getBookById(id);
        final Book bookToBeUpdated = createBookBuilder(dto).id(id).build();
        repository.save(bookToBeUpdated);
    }

    //delete book by id
    public void delete(final Long id) throws EntityNotFound {
        getBookById(id);
        repository.deleteById(id);
    }

    //get books count by genre
    public long getCountByGenre(final String genre) {
        return repository.countByGenre(genre);
    }

    //get all books by genre
    public List<Book> getBooksByGenre(final String genre) {
        return repository.findAllByGenre(genre);
    }

    //helper method
    private Book.BookBuilder createBookBuilder(final BookDto dto) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .genre(dto.getGenre())
                .publisher(dto.getPublisher())
                .quantity(dto.getQuantity());
    }
}
