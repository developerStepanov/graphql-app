package com.stepanov.graphql;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.stepanov.graphql.model.Author;
import com.stepanov.graphql.model.Book;
import com.stepanov.graphql.repository.AuthorRepository;
import com.stepanov.graphql.repository.BookRepository;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InsertTests {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Container
	public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
	.withPassword("")
	.withUsername("postgres");

	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
	}

	@Test
	public void insertBookAndAuthor() {
		Book book = new Book("La La Land");
		
		Author author = new Author("Max", new HashSet<Book>(Arrays.asList(book)));
		authorRepository.save(author);
		
		List<Book> retrivedBooks = bookRepository.findByAuthors_Name("Max");
		
		Assert.assertEquals(retrivedBooks.get(0).getTitle(), "La La Land");
	}
	
	@Test
	public void insertBookCheckAmount() {
		Book book1 = new Book("La La Land");
		Book book2 = new Book("BlackBook");
		
		Author author = new Author("Max", new HashSet<Book>(Arrays.asList(book1, book2)));
		authorRepository.save(author);
		
		List<Book> retrivedBooks = bookRepository.findAll();
		
		Assert.assertEquals(retrivedBooks.size(), 2);
	}
}
