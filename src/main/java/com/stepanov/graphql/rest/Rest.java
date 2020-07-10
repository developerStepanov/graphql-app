package com.stepanov.graphql.rest;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stepanov.graphql.model.Author;
import com.stepanov.graphql.model.Book;
import com.stepanov.graphql.repository.AuthorRepository;
import com.stepanov.graphql.repository.BookRepository;

@RestController
public class Rest {
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@RequestMapping("/")
    public void index() {
//		Book book1 = new Book("La La Land");
//		Book book2 = new Book("BlackBook");
//		
//		Author author1 = new Author("Max", new HashSet<Book>(Arrays.asList(book1, book2)));
//	
//		authorRepository.save(author1);
		
		Author kris = authorRepository.getOne(2L);
		
        Book book1 = new Book("New tales", new HashSet<Author>(Arrays.asList(kris)));
	
		bookRepository.saveAll(Arrays.asList(book1));
		
//		List<Book> books = bookRepository.findAll();
//		
//		return books.get(0).toString();
		
//		List<Book> books = bookRepository.findByAuthors_Name("Kris");
//		
//		return books.get(0).toString();
		
		//authorRepository.deleteById(1L);
    }
}
