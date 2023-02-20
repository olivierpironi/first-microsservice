package com.br.pironi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.br.pironi.exception.BookNotFound;
import com.br.pironi.model.Book;
import com.br.pironi.model.BookDetails;
import com.br.pironi.proxy.CambioProxy;
import com.br.pironi.repository.BookRepository;

import response.Cambio;

@Service
public class BookService {

	@Autowired
	Environment enviroment;
	
	@Autowired
	CambioProxy proxy;
	
	@Autowired
	BookRepository repository;
	
	public BookDetails searchBook(Long id, String currency) {
		Book book = repository.findById(id).orElseThrow(BookNotFound::new);
		Cambio cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		var port = enviroment.getProperty("local.server.port");
		book.setEnviroment("Book port: " + port + " Cambio port: " + cambio.getEnviroment());
		book.setPrice(cambio.getConvertedValue());
		return new BookDetails(book);
	}

}
