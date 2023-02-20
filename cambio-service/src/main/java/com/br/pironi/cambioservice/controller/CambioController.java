package com.br.pironi.cambioservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pironi.cambioservice.model.CambioDetails;
import com.br.pironi.cambioservice.service.CambioService;

import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cambio endpoints")
@RestController
@RequestMapping("/cambio-service")
public class CambioController {
	
	@Autowired
	private CambioService service;
	
	@Operation(summary = "Convert cambio type.")
	@GetMapping(value = "/{amount}/{from}/{to}")
	@Retry(name = "getCambio", fallbackMethod = "requestFailed")
	public ResponseEntity<CambioDetails> getCambio(@PathVariable("amount") BigDecimal amount, @PathVariable("from") String from, @PathVariable("to") String to) {
		return ResponseEntity.ok().body(service.convert(from,to, amount));
	}
	
	private ResponseEntity<CambioDetails> requestFailed(BigDecimal amount,  String from, String to, Throwable t) {
		return ResponseEntity.badRequest().body(new CambioDetails());
	}
	
}
