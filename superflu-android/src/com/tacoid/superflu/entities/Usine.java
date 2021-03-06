package com.tacoid.superflu.entities;

public class Usine extends Ville {

	private int productionRateVaccins = 2;
	private int productionRateTraitements = 10;

	public Usine(Zone zone, String nom, int x, int y) {
		super(zone, nom, x, y);
		this.retireHabitantsSains(this.getHabitantsSains());
	}

	private void produit(int populationInfectee) {
		//XXX: Récupérer la population infectée sans le passer en argument.
		productionRateTraitements = (int) (5 + 0.2 * Math
				.sqrt(populationInfectee));
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
	
	public void update(int delta) {
		//super.update(delta);
		int infectes = 0;
		for(Ville v : this.zone.getVilles()) {
			infectes += v.getHabitantsInfectes();
		}
		produit(infectes); //XXX: populationInfectee.
	}
	
	public boolean isUsine() {
		return true;
	}
}