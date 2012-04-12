package com.tacoid.superflu.entities;

import java.util.ArrayList;

/**
 * Cette classe représente une usine. C'est une ville qui possède la propriété de pouvoir produire des vaccins et traitements.
 *
 */
public class Usine extends Ville{

	private ArrayList<Traitement> traitements = new ArrayList<Traitement>();

	private ArrayList<Vaccin> vaccins = new ArrayList<Vaccin>();
	private int productionRateVaccins = 2;
	private int productionRateTraitements = 10;

	public Usine(Zone zone, String nom, int x, int y) {
		super(zone, nom, x, y);
		this.retireHabitantsSains(this.getHabitantsSains());
	}

	public void produit(int populationInfectee) {
		//System.out.println("usine " + nom + " produit");
		productionRateTraitements = (int) (5 + 0.2 * Math.sqrt(populationInfectee));
		productionRateVaccins = (int) (Math.sqrt(populationInfectee));

		for (Traitement traitement : traitements) {
			ajouteStockTraitement(traitement, productionRateTraitements);
		}
		for (Vaccin vaccin : vaccins) {
			ajouteStockVaccin(vaccin, productionRateVaccins);
		}
	}

	public int getProductionRateVaccins() {
		return productionRateVaccins;
	}

	public void setProductionRate(int productionRateVaccins) {
		this.productionRateVaccins = productionRateVaccins;
	}

	public void ajouteVaccin(Vaccin vaccin) {
		vaccins.add(vaccin);
	}

	public void ajouteTraitement(Traitement traitement) {
		traitements.add(traitement);
	}

	public ArrayList<Traitement> getTraitements() {
		return traitements;
	}

	public ArrayList<Vaccin> getVaccins() {
		return vaccins;
	}
}