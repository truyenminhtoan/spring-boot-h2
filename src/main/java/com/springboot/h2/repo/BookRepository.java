package com.springboot.h2.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.h2.entity.Book;

import java.util.List;

//spring annotation
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    long countByGenre(String genre);

    List<Book> findAllByGenre(String genre);
}
