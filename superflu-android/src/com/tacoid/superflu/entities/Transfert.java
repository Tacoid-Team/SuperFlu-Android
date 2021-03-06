package com.tacoid.superflu.entities;

import com.tacoid.superflu.GameScreen;
import com.tacoid.superflu.actors.TransfertActor;

public class Transfert implements Entity {
	
	private final static float VITESSE = 0.2f;

	private final Ville depart;
	private final Ville arrivee;
	private final long temps_depart; // ms

	private final long temps_arrivee; // ms
	private final boolean direct;
	
	private final int stock_traitement;
	private final int stock_vaccin;
	
	private final TransfertActor actor;

	public Transfert(Ville depart, Ville arrivee, int traitement, int vaccin, long temps_depart) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.temps_depart = temps_depart;
		stock_vaccin = vaccin;
		stock_traitement = traitement;
		
		depart.retireStockTraitement(stock_traitement);
		depart.retireStockVaccin(stock_vaccin);

		int d1 = Ville.distance_carre_sens1(depart, arrivee);
		int d2 = Ville.distance_carre_sens2(depart, arrivee);

		this.direct = d1 <= d2;

		if (direct) {
			this.temps_arrivee = temps_depart + (long)(Math.sqrt(d1) / VITESSE);
		} else {
			this.temps_arrivee = temps_depart + (long)(Math.sqrt(d2) / VITESSE);
		}
		
		actor = new TransfertActor(this);
		GameScreen.getInstance().addTransfert(actor);
	}
	
	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
	}
	
	public void finish() {
		arrivee.ajouteStockTraitement(stock_traitement);
		depart.ajouteStockTraitement(stock_vaccin);
		
		GameScreen.getInstance().removeTransfert(actor);
	}

	
	public static float getVitesse() {
		return VITESSE;
	}

	public Ville getDepart() {
		return depart;
	}

	public Ville getArrivee() {
		return arrivee;
	}

	public boolean isDirect() {
		return direct;
	}
	
	public long getTempsDepart() {
		return temps_depart;
	}

	public long getTempsArrivee() {
		return temps_arrivee;
	}
}
