package com.stepanov.graphql.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stepanov.graphql.repository.AuthorRepository;
import com.stepanov.graphql.repository.BookRepository;

@RestController
public class Rest {
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@RequestMapping("/")
    public String index() {
		return "Hello!\nCheck my Testing task.";
    }
}
