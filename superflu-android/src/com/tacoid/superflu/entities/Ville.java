package com.tacoid.superflu.entities;

import java.util.Random;

public class Ville {

	private Zone zone;
	protected String nom;
	protected int x;
	protected int y;
	private int habitantsSains;
	private int habitantsInfectes;
	private int habitantsImmunises;
	private int habitantsMorts;

	private int stockVaccins = 0;
	private int stockVaccinsMax = 500000;
	
	private int stockTraitements = 0;
	private int stockTraitementsMax = 100000;

	public Ville(Zone zone, String nom, int x, int y) {
		Random r = new Random();

		this.zone = zone;
		this.nom = nom;
		this.x = x;
		this.y = y;
		this.habitantsSains = 200000 + r.nextInt(300000);
		this.habitantsInfectes = 0;
		this.habitantsImmunises = 0;
		this.habitantsMorts = 0;
	}

	public static int distance_carre_sens1(Ville depart, Ville arrivee) {
		return (arrivee.x - depart.x) * (arrivee.x - depart.x) + (arrivee.y - depart.y) * (arrivee.y - depart.y);
	}

	public static int distance_carre_sens2(Ville depart, Ville arrivee) {
		int depart_x, arrivee_x;
		if (depart.getX() < arrivee.getX()) {
			depart_x = depart.getX() + 1024;
			arrivee_x = arrivee.getX();
		} else {
			depart_x = depart.getX();
			arrivee_x = arrivee.getX() + 1024;
		}

		return (arrivee_x - depart_x) * (arrivee_x - depart_x) + (arrivee.y - depart.y) * (arrivee.y - depart.y);
	}

	public static int distance_carre(Ville depart, Ville arrivee) {
		int d1 = distance_carre_sens1(depart, arrivee);
		int d2 = distance_carre_sens2(depart, arrivee);
		return Math.min(d1, d2);
	}

	public void ajouteStockVaccin(int quantite) {
		stockVaccins += quantite;
		if (stockVaccins > stockVaccinsMax) {
			stockVaccins = stockVaccinsMax;
		}
	}

	public void retireStockVaccin(int quantite) {
		stockVaccins = Math.max(stockVaccins-quantite,0);
	}

	public void ajouteStockTraitement(int quantite) {
		stockTraitements += quantite;
		if (stockTraitements > stockTraitementsMax) {
			stockTraitements = stockTraitementsMax;
		}
	}

	public void retireStockTraitement(int quantite) {
		stockTraitements = Math.max(stockTraitements-quantite,0);
	}

	public String getNom() {
		return nom;
	}

	public int getHabitants() {
		if (habitantsSains + habitantsImmunises + habitantsInfectes < 0) {
			System.err.println("ERRRRRRRR");
		}
		return habitantsSains + habitantsImmunises + habitantsInfectes;
	}

	public int getHabitantsSains() {
		return habitantsSains;
	}

	public int getHabitantsImmunises() {
		return habitantsImmunises;
	}

	public int getHabitantsInfectes() {
		return habitantsInfectes;
	}

	public int getHabitantsMorts() {
		return habitantsMorts;
	}

	public void ajouteHabitantsInfectes(int habitantsInfectes) {
		this.habitantsInfectes += habitantsInfectes;
	}

	public void ajouteHabitantsImmunises(int habitantsImmunises) {
		this.habitantsImmunises += habitantsImmunises;
	}

	public void ajouteHabitantsSains(int habitantsSains) {
		this.habitantsSains += habitantsSains;
	}

	public void retireHabitantsInfectes(int habitantsInfectes) {
		this.habitantsInfectes -= habitantsInfectes;
	}

	public void retireHabitantsImmunises(int habitantsImmunises) {
		this.habitantsImmunises -= habitantsImmunises;
	}

	public void retireHabitantsSains(int habitantsSains) {
		this.habitantsSains -= habitantsSains;
	}

	public int getPourcentageInfectes() {
		return (int)(100 * ((float)habitantsInfectes / getHabitants()));
	}

	public boolean isMine(Joueur joueur) {
		boolean bool = false;
		if (joueur != null) {
			for (Zone z : joueur.getZones()) {
				if (zone.getId() == z.getId()) {
					bool = true;
					break;
				}
			}
		}
		return bool;
	}

	/**
	 * Mise à jour des données de la ville.
	 */
	public void update() {
		float transmission = 0.015f;
		float perteImmunite = 0.0001f;
		float mortalite = 0.00005f;

		if (getHabitants() > 0) {

			// Infection :
			float nouveauxHabitantsInfectes = (habitantsInfectes * transmission * ((float)habitantsSains / getHabitants()));
			if (nouveauxHabitantsInfectes > 0) {
				habitantsSains -= nouveauxHabitantsInfectes;
				habitantsInfectes += nouveauxHabitantsInfectes;
			} else {
				Random rand = new Random();
				if (rand.nextFloat() < nouveauxHabitantsInfectes) {
					habitantsSains -= 1;
					habitantsInfectes += 1;
				}
			}

			// Utilisation des traitements :
			if (habitantsInfectes > stockTraitements) {
				habitantsInfectes -= stockTraitements;
				habitantsImmunises += stockTraitements;
				retireStockTraitement(stockTraitements);
			} else {
				retireStockTraitement(habitantsInfectes);
				habitantsImmunises += habitantsInfectes;
				habitantsInfectes = 0;
			}

			// Perte immunité :
			habitantsImmunises -= (int) (habitantsImmunises * perteImmunite);

			// Utilisation des vaccins (sur les personnes saines) :
			int nouveauxHabitantsImmunises = Math.min(habitantsSains, stockVaccins);
			retireStockVaccin(nouveauxHabitantsImmunises);
			habitantsSains -= nouveauxHabitantsImmunises;
			habitantsImmunises += nouveauxHabitantsImmunises;

			// Mortalité :
			float nouveauxHabitantsMorts = habitantsInfectes * mortalite;
			if (nouveauxHabitantsMorts > 1) {
				habitantsInfectes -= nouveauxHabitantsMorts;
				habitantsMorts += nouveauxHabitantsMorts;
			} else {
				Random rand = new Random();
				if (rand.nextFloat() < nouveauxHabitantsMorts) {
					habitantsInfectes -= 1;
					habitantsMorts += 1;
				}
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isOnCity(int x, int y) {
		return (x-this.x)*(x-this.x)+(y-this.y)*(y-this.y) < 500;
	}


	public int getStockVaccins() {
		return stockVaccins;
	}

	public int getStockTraitements() {
		return stockTraitements;
	}

	public Zone getZone() {
		return zone;
	}

	@Override
	public String toString() {
		return nom+":\n"
		+"-Sains = "+this.habitantsSains+"\n"
		+"-Malades ="+this.habitantsInfectes+"\n"
		+"-Morts = "+this.habitantsMorts+"\n";
	}
}