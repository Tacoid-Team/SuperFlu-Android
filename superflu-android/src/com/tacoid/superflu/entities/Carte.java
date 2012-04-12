package com.tacoid.superflu.entities;
import java.util.ArrayList;
import java.util.Arrays;

public class Carte {

	private static final int longueur_courbe = 100;
	private static int courbe_morts[] = null;
	private static int courbe_infectes[] = null;
	private static int courbe_vaccines[] = null;
	private static int dephasage_courbes;


	@Override
	public String toString() {
		return "Carte [courbe_infectes=" + Arrays.toString(courbe_infectes)
				+ ", courbe_morts=" + Arrays.toString(courbe_morts)
				/*+ ", courbe_pop=" + Arrays.toString(courbe_pop)*/
				+ ", courbe_vaccines=" + Arrays.toString(courbe_vaccines)
				+ ", dephasage_courbes=" + dephasage_courbes + "]";
	}

	private ArrayList<Zone> zones = new ArrayList<Zone>();

	public Carte() {
		zones.add(new Zone(1));
		zones.add(new Zone(2));
		zones.add(new Zone(3));
		zones.add(new Zone(4));
		zones.add(new Zone(5));
		zones.add(new Zone(6));

		courbe_morts = new int[longueur_courbe];
		courbe_infectes = new int[longueur_courbe];
		courbe_vaccines = new int[longueur_courbe];
		dephasage_courbes = 0;

		for(int i=0 ; i<longueur_courbe ; i++) {
			/*courbe_pop[i] = 0;*/
			courbe_morts[i] = 0;
			courbe_infectes[i] = 0;
			courbe_vaccines[i] = 0;
		}
	}

	public ArrayList<Zone> getZones() {
		return zones;
	}

}