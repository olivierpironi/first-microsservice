package com.br.pironi.cambioservice.model;

public class CambioDetails extends Cambio{
	
	private static final long serialVersionUID = 1L;

	public CambioDetails(Cambio cambio) {
		super(cambio.getId(), cambio.getFrom(), cambio.getTo(), cambio.getConversionFactor(), cambio.getConvertedValue(), cambio.getEnviroment());
	}

	public CambioDetails() {
	}
}
