package com.stepanov.graphql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stepanov.graphql.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByAuthors_Name(String name);
}
