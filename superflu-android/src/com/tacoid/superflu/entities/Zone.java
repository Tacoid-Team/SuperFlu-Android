package com.tacoid.superflu.entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Cette classe represente une zone. Cette zone contient une liste de villes.
 */
public class Zone {

	private int id;
	private String nom;
	private ArrayList<Ville> villes = new ArrayList<Ville>();
	private Usine usine;

	private int population;
	private int populationInfectee;
	private int populationMorte;

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

	public Zone(int id) {
		this.id = id;
		chargeVilles();
	}

	public String getNom() {
		return nom;
	}

	public Usine getUsine() {
		return usine;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Ville> getVilles() {
		return villes;
	}
	
	private void chargeVilles() {
		String filepath = "ressources/zones/zone" + id + ".data";

		try {
			BufferedReader buff = new BufferedReader(new InputStreamReader(
					getClass().getClassLoader().getResourceAsStream(filepath)));
			String line;

			nom = buff.readLine();

			while ((line = buff.readLine()) != null) {
				String tab[] = line.split(" ");

				if (tab.length == 3) {
					tab[0] = tab[0].replace('_', ' ');
					if (usine == null) {
						usine = new Usine(this, tab[0],
								Integer.valueOf(tab[1]),
								Integer.valueOf(tab[2]));
					} else {
						Ville ville = new Ville(this, tab[0],
								Integer.valueOf(tab[1]),
								Integer.valueOf(tab[2]));
						villes.add(ville);
					}
				} else {
					System.err.println("Erreur lecture " + filepath
							+ "continue quand m�me...");
				}
			}

			buff.close();
		} catch (FileNotFoundException e) {
			System.err.println("Fichier " + filepath
					+ " introuvable ! Aucune ville charg�e pour cette zone.");
		} catch (IOException e) {
			System.err.println("Erreur � la lecture de " + filepath + ".");
		}
	}
}