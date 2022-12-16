package com.springboot.h2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok annotations
//annotation helps to generate toString(), equals(), hashcode(), getter(), setter()
@Data
//annotation helps to generate a no-argument constructor
@NoArgsConstructor
//annotation helps to generate a constructor with 1 parameter for each field in the class
@AllArgsConstructor
//annotation helps to implement the builder design pattern
//usage can be seen in BookService.java
@Builder
//spring stereotype annotation
@Component
@Table(name = "books")
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String author;
    String genre;
    String publisher;
    int quantity;
}
