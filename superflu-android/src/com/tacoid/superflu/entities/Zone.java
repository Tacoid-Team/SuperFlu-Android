package com.tacoid.superflu.entities;

import java.util.ArrayList;

/**
 * Cette classe represente une zone. Cette zone contient une liste de villes.
 */
public class Zone {
	private int id;
	private String nom;
	private ArrayList<Ville> villes = new ArrayList<Ville>();

	private int population;
	private int populationInfectee;
	private int populationMorte;

	public Zone(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	public void addVille(Ville ville) {
		this.villes.add(ville);
	}
	
	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getPopulationInfectee() {
		return populationInfectee;
	}

	public int getPopulationMorte() {
		return populationMorte;
	}

	public String getNom() {
		return nom;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Ville> getVilles() {
		return villes;
	}
	
	public void update(float delta) {		
		for (Ville ville : villes) {
			ville.update(delta * 10);
		}
		updatePopulation();
	}
	
	private void updatePopulation() {
		populationInfectee = 0;
		population = 0;
		populationMorte = 0;
		
		for (Ville ville : villes) {
			populationInfectee += ville.getHabitantsInfectes();
			population += ville.getHabitants();
			populationMorte += ville.getHabitantsMorts();
		}
	}
}