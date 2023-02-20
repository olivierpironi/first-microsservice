package com.br.pironi.cambioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.pironi.cambioservice.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long>{

	Cambio findByFromAndTo(String from, String to);
}
