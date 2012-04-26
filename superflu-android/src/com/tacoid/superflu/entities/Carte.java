package com.tacoid.superflu.entities;

import java.util.ArrayList;
import java.util.Arrays;

import com.tacoid.superflu.GameLogic;

public class Carte implements Entity {

	private static double TAUX_MIGRATION = 200;
	
	private static final int longueur_courbe = 100;
	private static int courbe_morts[] = null;
	private static int courbe_infectes[] = null;
	private static int courbe_vaccines[] = null;
	private static int dephasage_courbes;

	@Override
	public String toString() {
		return "Carte [courbe_infectes=" + Arrays.toString(courbe_infectes)
				+ ", courbe_morts=" + Arrays.toString(courbe_morts)
				/* + ", courbe_pop=" + Arrays.toString(courbe_pop) */
				+ ", courbe_vaccines=" + Arrays.toString(courbe_vaccines)
				+ ", dephasage_courbes=" + dephasage_courbes + "]";
	}

	private ArrayList<Zone> zones;

	private GameLogic gameLogic;

	private int counter = 10;

	public Carte(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		zones = new ArrayList<Zone>();
		courbe_morts = new int[longueur_courbe];
		courbe_infectes = new int[longueur_courbe];
		courbe_vaccines = new int[longueur_courbe];
		dephasage_courbes = 0;

		for (int i = 0; i < longueur_courbe; i++) {
			/* courbe_pop[i] = 0; */
			courbe_morts[i] = 0;
			courbe_infectes[i] = 0;
			courbe_vaccines[i] = 0;
		}
	}
	
	public void addZone(Zone zone) {
		zones.add(zone);
	}

	public ArrayList<Zone> getZones() {
		return zones;
	}

	public void update(int delta) {
		if (counter >= 10) {
			for (Zone zone : zones) {
				zone.update(counter * delta);
			}
			migrations(counter * delta);
			counter = 0;
		} else {
			counter++;
		}
	}
	
	
	
	private void migrations(int delta) {
		for (Zone z1 : zones) {
			for (Ville v1 : z1.getVilles()) {
				if(v1.isUsine()) continue;
				if (v1.getHabitants() == 0) continue;
				double taux_sain = (double)v1.getHabitantsSains() / v1.getHabitants();
				double taux_infection = (double)v1.getHabitantsInfectes() / v1.getHabitants();
				double taux_immunisation = (double)v1.getHabitantsImmunises() / v1.getHabitants();
				for (Zone z2 : zones) {
					for (Ville v2 : z2.getVilles()) {
						if(v2.isUsine()) continue;
						if (v1 == v2) continue;
						double d = Ville.distance_carre(v1, v2);

						double flux = Math.floor((Math.random() * v1.getHabitants() * TAUX_MIGRATION) / (d + 3 * this.gameLogic.getPopulationInfectee()) * delta/1000.0f);
						double flux_sain = Math.floor(taux_sain * flux);
						double flux_infecte = Math.floor(taux_infection * flux);
						double flux_immunise = Math.floor(taux_immunisation * flux);
	
						/* Mise à jour de la population saine */
						if (v1.getHabitantsSains() >= flux_sain) {
							v2.ajouteHabitantsSains((int)flux_sain);
							v1.retireHabitantsSains((int)flux_sain);
						} else {
							v2.ajouteHabitantsSains(v1.getHabitantsSains());
							v1.retireHabitantsSains(v1.getHabitantsSains());
						}

						/* Mise à jour de la population infectée */
						if (v1.getHabitantsInfectes() >= flux_infecte) {
							v2.ajouteHabitantsInfectes((int)flux_infecte);
							v1.retireHabitantsInfectes((int)flux_infecte);
						} else {
							v2.ajouteHabitantsInfectes(v1.getHabitantsInfectes());
							v1.retireHabitantsInfectes(v1.getHabitantsInfectes());
						}

						/* Mise à jour de la population immunisée */
						if (v1.getHabitantsImmunises() >= flux_immunise) {
							v2.ajouteHabitantsImmunises((int)flux_immunise);
							v1.retireHabitantsImmunises((int)flux_immunise);
						} else {
							v2.ajouteHabitantsImmunises(v1.getHabitantsImmunises());
							v1.retireHabitantsImmunises(v1.getHabitantsImmunises());
						}
					}
				}
			}
		}
	}
}