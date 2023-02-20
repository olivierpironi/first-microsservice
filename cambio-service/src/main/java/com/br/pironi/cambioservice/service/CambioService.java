package com.br.pironi.cambioservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.br.pironi.cambioservice.exception.ConversaoImpossivelException;
import com.br.pironi.cambioservice.model.CambioDetails;
import com.br.pironi.cambioservice.repository.CambioRepository;

@Service
public class CambioService {
	
	@Autowired
	private Environment enviroment;

	@Autowired
	private CambioRepository repository;

	public CambioDetails convert(String from, String to, BigDecimal amount) {
		var cambio = repository.findByFromAndTo(from, to).orElseThrow(ConversaoImpossivelException::new);
		var port = enviroment.getProperty("local.server.port");
		BigDecimal conversionFactor = cambio.getConversionFactor();
		BigDecimal convertedValue = conversionFactor.multiply(amount);
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
		cambio.setEnviroment(port);
		return new CambioDetails(cambio);
	}
	
	
}
