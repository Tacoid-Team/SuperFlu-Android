package com.tacoid.superflu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.tacoid.superflu.entities.Carte;
import com.tacoid.superflu.entities.Transfert;
import com.tacoid.superflu.entities.Ville;
import com.tacoid.superflu.entities.Zone;

public class GameLogic {
	private final int POURCENTAGE_ECHEC = 1;
	private final int POURCENTAGE_PANDEMIC = 5;

	private int populationInfectee = 0;
	private int populationMondiale = 0;
	private int populationMorte = 0;
	
	private List<Transfert> transferts;
	private Transfert newTransfert;
	
	private Carte carte;
	
	private long time;

	public GameLogic() {
		transferts = Collections.synchronizedList(new ArrayList<Transfert>());
		newTransfert = null;
	}

	public enum EtatJeu {
		WAIT, EN_COURS, GAGNE, PERDU
	};
	private EtatJeu etat = EtatJeu.WAIT;
	
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	
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
		Random rand = new Random();
		
		int i = rand.nextInt(6);
		Zone randZone = carte.getZones().get(i);
		
		int j = rand.nextInt(randZone.getVilles().size());
		Ville randVille = randZone.getVilles().get(j);
		
		randVille.ajouteHabitantsInfectes(2000);
	}
	
	public void update(int delta) {
		/* Increase game time */
		time += delta;
		
		if (this.etat == EtatJeu.WAIT) {
			creerEpidemie();
			this.etat = EtatJeu.EN_COURS;
		} else if (this.etat == EtatJeu.EN_COURS) {
			updatePopulation();
			this.carte.update(delta);
			
			if(newTransfert != null) {
				transferts.add(newTransfert);
				newTransfert = null;
			}

			Iterator<Transfert> it = transferts.iterator();
			while (it.hasNext()) {
				Transfert t = it.next();
				t.update(delta);
				if (time > t.getTempsArrivee()) {
					t.finish();
					it.remove();
				}
			}
			
			if (this.populationMorte > (this.populationMondiale + this.populationMorte)*POURCENTAGE_ECHEC) {
				this.etat = EtatJeu.PERDU;
			}
		}
	}
	
	public void addTransfert(Transfert transfert) {
		newTransfert = transfert;
	}
	
	public boolean isPandemic() {
		return populationInfectee * 100 / POURCENTAGE_PANDEMIC > populationMondiale;
	}
	
	public int getPopulationInfectee() {
		return populationInfectee;
	}

	public int getPopulationMondiale() {
		return populationMondiale;
	}

	public int getPopulationMorte() {
		return populationMorte;
	}

	public long getTime() {
		return time;
	}
}
