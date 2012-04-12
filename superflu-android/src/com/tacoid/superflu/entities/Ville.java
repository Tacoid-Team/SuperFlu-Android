package com.tacoid.superflu.entities;

import java.util.ArrayList;
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

	private ArrayList<StockVaccin> stocksVaccins = new ArrayList<StockVaccin>();
	private ArrayList<StockTraitement> stocksTraitements = new ArrayList<StockTraitement>();

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

	public void ajouteStockVaccin(Vaccin vaccin, int quantite) {
		boolean ajoute = false;

		for (StockVaccin sv : stocksVaccins) {
			if (sv.getVaccin() == vaccin) {
				sv.ajouteStock(quantite);
				ajoute = true;
				break;
			}
		}
		if (!ajoute) {
			stocksVaccins.add(new StockVaccin(quantite, vaccin));
		}
	}

	public void retireStockVaccin(Vaccin vaccin, int quantite) {

		for (StockVaccin sv : stocksVaccins) {
			if (sv.getVaccin() == vaccin) {
				sv.retireStock(quantite);
				break;
			}
		}
	}

	public void ajouteStockTraitement(Traitement traitement, int quantite) {
		boolean ajoute = false;

		for (StockTraitement st : stocksTraitements) {
			if (st.getTraitement() == traitement) {
				st.ajouteStock(quantite);
				ajoute = true;
				break;
			}
		}
		if (!ajoute) {
			stocksTraitements.add(new StockTraitement(quantite, traitement));
		}
	}

	public void retireStockTraitement(Traitement traitement, int quantite) {

		for (StockTraitement st : stocksTraitements) {
			if (st.getTraitement() == traitement) {
				st.retireStock(quantite);
				break;
			}
		}
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
		//System.out.println("Update !");
		float transmission = 0.015f;
		float perteImmunite = 0.0001f;
		float mortalite = 0.00005f;

		if (getHabitants() > 0) {

			// Infection :
			/*System.out.println("Infection :");*/
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
			/*System.out.println("nouveaux : " + nouveauxHabitantsInfectes + " infectés (total) " + habitantsInfectes);*/

			// Utilisation des traitements :
			/*System.out.println("Traitements :");*/
			if (stocksTraitements.size() > 0) {
				if (habitantsInfectes > stocksTraitements.get(0).getStock()) {
					habitantsInfectes -= stocksTraitements.get(0).getStock();
					habitantsImmunises += stocksTraitements.get(0).getStock();
					stocksTraitements.get(0).retireStock(stocksTraitements.get(0).getStock());
					/*System.out.println("Utilise : " + stocksTraitements.get(0).getStock());*/
				} else {
					stocksTraitements.get(0).retireStock(habitantsInfectes);
					habitantsImmunises += habitantsInfectes;
					habitantsInfectes = 0;
					/*System.out.println("Utilise : " + habitantsInfectes);*/
				}
			}

			// Perte immunité :
			/*System.out.println("Perte immunité : ");*/
			habitantsImmunises -= (int) (habitantsImmunises * perteImmunite);
			/*System.out.println(habitantsImmunises);*/

			// Utilisation des vaccins (sur les personnes saines) :
			/*System.out.println("Utilisation vaccins : ");*/
			if (stocksVaccins.size() > 0) {
				int nouveauxHabitantsImmunises = Math.min(habitantsSains, stocksVaccins.get(0).getStock());
				stocksVaccins.get(0).retireStock(nouveauxHabitantsImmunises);
				habitantsSains -= nouveauxHabitantsImmunises;
				habitantsImmunises += nouveauxHabitantsImmunises;
				/*System.out.println("immunisés : " + habitantsImmunises);*/
			}

			/*System.out.println("Mortalité :");*/
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
			/*System.out.println("morts " + habitantsMorts);*/
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


	public ArrayList<StockVaccin> getStocksVaccins() {
		return stocksVaccins;
	}

	public ArrayList<StockTraitement> getStocksTraitements() {
		return stocksTraitements;
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