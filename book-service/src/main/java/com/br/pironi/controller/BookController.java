package com.br.pironi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pironi.model.BookDetails;
import com.br.pironi.service.BookService;

import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired
	BookService service;

	@Operation(summary = "Find a specific book by a id")
	@GetMapping("/{id}/{currency}")
	@Retry(name = "findBook", fallbackMethod = "requestFailed")
	public ResponseEntity<BookDetails> findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		return ResponseEntity.ok().body(service.searchBook(id, currency));
	}

	private ResponseEntity<BookDetails> requestFailed(Long id,  String currency, Throwable t) {
		return ResponseEntity.badRequest().body(new BookDetails());
	}
}
