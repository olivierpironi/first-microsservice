package com.br.pironi.cambioservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.pironi.cambioservice.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long>{

	Optional<Cambio> findByFromAndTo(String from, String to);
}
