package com.tacoid.superflu.entities;

public class StockTraitement extends Stock{

	private Traitement traitement;

	public StockTraitement(int stock, Traitement traitement) {
		super(stock, 20000);
		this.traitement = traitement;
	}

	public Traitement getTraitement() {
		return traitement;
	}

	public void setTraitement(Traitement t) {
		this.traitement = t;
	}
}