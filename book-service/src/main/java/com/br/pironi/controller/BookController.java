package com.br.pironi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pironi.model.Book;
import com.br.pironi.proxy.CambioProxy;
import com.br.pironi.repository.BookRepository;

import response.Cambio;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	Environment enviroment;
	
	@Autowired
	BookRepository repository;
	
	@Autowired
	CambioProxy proxy;

	@GetMapping("/{id}/{currency}")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("") );
		Cambio cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		var port = enviroment.getProperty("local.server.port");
		book.setEnviroment(port);
		book.setPrice(cambio.getConvertedValue());
		return book;
	}
}
