package com.springboot.h2.bootstrap;

import com.github.javafaker.Faker;
import com.springboot.h2.entity.BookDto;
import com.springboot.h2.service.BookService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//lombok annotation for logger
@Slf4j
//spring stereotype annotation
@Component
public class DefaultBooksLoader implements CommandLineRunner {

    @Autowired
    BookService service;
//    @Autowired
    Faker faker = new Faker();

    @Override
    public void run(String... args) throws Exception {
        final long count = service.getBooksCount();
        if (count == 0) {
            log.info("Saving the default books into the db");
            for (int x = 0; x < 10; x++) {
                persist();
            }
        } else {
            log.info("{} books are already present in the db", count);
        }
    }

    private void persist() {
        final BookDto dto = create();
        service.save(dto);
    }

    private BookDto create() {
        return BookDto.builder()
                .title(faker.book().title())
                .author(faker.book().author())
                .genre(faker.book().genre())
                .publisher(faker.book().publisher())
                .quantity(faker.number().randomDigitNotZero())
                .build();
    }
}
