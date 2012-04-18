package com.tacoid.superflu.entities;

import java.util.Random;

public class Ville implements Entity {

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
		return (arrivee.x - depart.x) * (arrivee.x - depart.x)
				+ (arrivee.y - depart.y) * (arrivee.y - depart.y);
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

		return (arrivee_x - depart_x) * (arrivee_x - depart_x)
				+ (arrivee.y - depart.y) * (arrivee.y - depart.y);
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

	public void ajouteStockTraitement(int quantite) {
		stockTraitements += quantite;
		if (stockTraitements > stockTraitementsMax) {
			stockTraitements = stockTraitementsMax;
		}
	}

	public String getNom() {
		return nom;
	}

	public int getHabitants() {
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
		return (int) (100 * ((double) habitantsInfectes / getHabitants()));
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
	public void update(float delta) {
		double transmission = 0.025;
		double perteImmunite = 0.00001;
		double mortalite = 0.000005;
		
		this.stockVaccins = (int) Math.round(this.stockVaccins * Math.pow(0.995, Math.floor(delta / 10)));
		this.stockTraitements = (int) Math.round(this.stockTraitements * Math.pow(0.997, Math.floor(delta / 10)));
		
		if (getHabitants() > 0) {
			
			// Infection :
			if (habitantsInfectes > 0) {
				double nouveauxHabitantsInfectes = (habitantsInfectes * transmission * ((double) habitantsSains / getHabitants())) * delta;
				if (nouveauxHabitantsInfectes >= 1) {
					habitantsSains -= nouveauxHabitantsInfectes;
					habitantsInfectes += nouveauxHabitantsInfectes;
				} else {
					if (Math.random() < nouveauxHabitantsInfectes) {
						habitantsSains -= 1;
						habitantsInfectes += 1;
					}
				}
			}

			// Utilisation des traitements :
			if (this.stockTraitements > 0) {
				int nouveauxHabitantsImmunises = Math.min(this.habitantsInfectes, this.stockTraitements);
				this.stockTraitements -= nouveauxHabitantsImmunises;
				this.habitantsInfectes -= nouveauxHabitantsImmunises;
				this.habitantsImmunises += nouveauxHabitantsImmunises;
			}

			// Perte immunité :
			if (this.habitantsImmunises > 0) {
				double perte = habitantsImmunises * perteImmunite * delta;
				if (perte >= 1) {
					this.habitantsImmunises -= perte;
					this.habitantsSains += perte;
				} else {
					if (Math.random() < perte) {
						habitantsSains -= 1;
						habitantsInfectes += 1;
					}
				}
			}

			// Utilisation des vaccins (sur les personnes saines) :
			if (this.stockVaccins > 0) {
				int nouveauxHabitantsImmunises = Math.min(this.habitantsSains, this.stockVaccins);
				this.stockVaccins -= nouveauxHabitantsImmunises;
				this.habitantsSains -= nouveauxHabitantsImmunises;
				this.habitantsImmunises += nouveauxHabitantsImmunises;
			}

			// Mortalité :
			if (this.habitantsInfectes > 0) {
				double nouveauxHabitantsMorts = habitantsInfectes * mortalite * delta;
				if (nouveauxHabitantsMorts >= 1) {
					habitantsInfectes -= nouveauxHabitantsMorts;
					habitantsMorts += nouveauxHabitantsMorts;
				} else {
					if (Math.random() < nouveauxHabitantsMorts) {
						habitantsInfectes -= 1;
						habitantsMorts += 1;
					}
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

	public int getStockVaccins() {
		return stockVaccins;
	}
	
	public int getStockVaccinsMax() {
		return stockVaccinsMax;
	}

	public int getStockTraitements() {
		return stockTraitements;
	}

	public int getStockTraitementsMax() {
		return stockTraitementsMax;
	}
	
	public Zone getZone() {
		return zone;
	}

	@Override
	public String toString() {
		return nom + ":\n" + "-Sains = " + this.habitantsSains + "\n"
				+ "-Malades =" + this.habitantsInfectes + "\n" + "-Morts = "
				+ this.habitantsMorts + "\n";
	}

	public boolean isUsine() {
		return false;
	}
}