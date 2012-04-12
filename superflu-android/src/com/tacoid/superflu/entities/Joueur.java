package com.tacoid.superflu.entities;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Joueur {

	private List<Zone> zones = new ArrayList<Zone>();
	private int score;
	private Socket socket;
	private String pseudo;

	public Joueur(String pseudo) {
		this.pseudo = pseudo;
	}

	public Joueur() {
		this("");
	}

	public void setZone(List<Zone> zone) {
		this.zones = zone;
	}

	public List<Zone> getZones() {
		return zones;
	}

	public int getScore() {
		return score;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}
}