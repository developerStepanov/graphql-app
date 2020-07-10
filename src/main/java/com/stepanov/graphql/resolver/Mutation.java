package com.stepanov.graphql.resolver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.stepanov.graphql.model.Author;
import com.stepanov.graphql.model.Book;
import com.stepanov.graphql.repository.AuthorRepository;
import com.stepanov.graphql.repository.BookRepository;

@Component
public class Mutation implements GraphQLMutationResolver {
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	
	private final Logger logger = LoggerFactory.getLogger(Mutation.class);
	
	@Autowired
	public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}
	
	public Author saveAuthor(String name, List<String> titles) {
		Set<Book> books = new HashSet<Book>();
		
		for(String title : titles) {
			books.add(new Book(title));
		}
		
		Author author = new Author(name);
		author.setBooks(books);
		
		authorRepository.save(author);
		
		logger.info("Success. Save!");
		return author;
	}
	
	public Book saveBook(String title, List<String> names) {
		Set<Author> authors = new HashSet<Author>();
		
		for(String name : names) {
			authors.add(new Author(name));
		}
		
		Book book = new Book(title);
		book.setAuthors(authors);
		
		bookRepository.save(book);
		
		logger.info("Success. Save!");
		return book;
	}
}
