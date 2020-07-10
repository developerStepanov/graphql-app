package com.stepanov.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.stepanov.graphql.model.Book;
import com.stepanov.graphql.repository.BookRepository;

@Component
public class Query implements GraphQLQueryResolver {
	private BookRepository bookRepository;
	
	@Autowired
	public Query(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	public Iterable<Book> getBooksByAuthor(String name) {
		return bookRepository.findByAuthors_Name(name);
	}
}
