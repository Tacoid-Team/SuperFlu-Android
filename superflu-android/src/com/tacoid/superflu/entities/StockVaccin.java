package com.tacoid.superflu.entities;

public class StockVaccin extends Stock {

	private Vaccin vaccin;

	public StockVaccin(int stock, Vaccin vaccin) {
		super(stock, 500000);
		this.vaccin = vaccin;
	}

	public Vaccin getVaccin() {
		return vaccin;
	}

	public void setVaccin(Vaccin v) {
		this.vaccin = v;
	}
}