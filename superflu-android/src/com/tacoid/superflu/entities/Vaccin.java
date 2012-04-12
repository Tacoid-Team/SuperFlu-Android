package com.tacoid.superflu.entities;

public class Vaccin{

	private Virus virus;

	public Vaccin(Virus virus) {
		this.virus = virus;
	}

	public Virus getVirus() {
		return virus;
	}
}