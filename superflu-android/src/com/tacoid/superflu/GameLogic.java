package com.tacoid.superflu;

import com.tacoid.superflu.entities.Carte;
import com.tacoid.superflu.entities.Zone;

public class GameLogic {
	private final int POURCENTAGE_ECHEC = 1;
	private final int POURCENTAGE_PANDEMIC = 5;
	
	private int populationInfectee = 0;
	private int populationMondiale = 0;
	private int populationMorte = 0;
	
	private Carte carte;
	
	private void updatePopulation() {
		populationInfectee = 0;
		populationMondiale = 0;
		populationMorte = 0;
		
		for (Zone zone : carte.getZones()) {
			populationInfectee += zone.getPopulationInfectee();
			populationMondiale += zone.getPopulation();
			populationMorte += zone.getPopulationMorte();
		}
	}
	
	private void creerEpidemie() {
		
	}
	
	public void update(float delta) {
		updatePopulation();
	}
	
	public boolean isPandemic() {
		return populationInfectee * 100 / POURCENTAGE_PANDEMIC > populationMondiale;
	}
}
