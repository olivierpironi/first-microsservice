package com.br.pironi.model;

public class BookDetails extends Book {

	private static final long serialVersionUID = 1L;

	public BookDetails(Book book) {
		super(book.getId(), book.getAuthor(), book.getTitle(), book.getLaunchDate(), book.getPrice(), book.getCurrency(), book.getEnviroment());
	}

	public BookDetails() {
	}

	
}
