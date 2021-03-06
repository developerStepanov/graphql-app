package com.stepanov.graphql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stepanov.graphql.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	List<Author> findByBooks_Title(String title);
}
