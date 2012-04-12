package com.tacoid.superflu.entities;

public class Usine extends Ville{

	private int productionRateVaccins = 2;
	private int productionRateTraitements = 10;

	public Usine(Zone zone, String nom, int x, int y) {
		super(zone, nom, x, y);
		this.retireHabitantsSains(this.getHabitantsSains());
	}

	public void produit(int populationInfectee) {
		productionRateTraitements = (int) (5 + 0.2 * Math.sqrt(populationInfectee));
		productionRateVaccins = (int) (Math.sqrt(populationInfectee));

		ajouteStockTraitement(productionRateTraitements);
		ajouteStockVaccin(productionRateVaccins);
	}

	public int getProductionRateVaccins() {
		return productionRateVaccins;
	}

	public void setProductionRate(int productionRateVaccins) {
		this.productionRateVaccins = productionRateVaccins;
	}
}