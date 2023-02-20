package com.br.pironi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.pironi.model.Book;


public interface BookRepository extends JpaRepository<Book , Long>{

}
