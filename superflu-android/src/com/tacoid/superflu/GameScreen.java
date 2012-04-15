package com.tacoid.superflu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tacoid.actors.VilleActor;
import com.tacoid.actors.ZoneActor;
import com.tacoid.superflu.entities.Usine;
import com.tacoid.superflu.entities.Ville;
import com.tacoid.superflu.entities.Zone;

public class GameScreen implements Screen {
	private static final int VIRTUAL_WIDTH = 1024;
	private static final int VIRTUAL_HEIGHT = 544; // XXX: Très plat :);

	private Stage stage;
	private Group groupZones;
	private Group groupVilles;

	public GameScreen(SuperFlu superflu) {
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		groupZones = new Group();
		groupVilles = new Group();
	
		Texture backgroundTexture = new Texture(Gdx.files.internal("data/fond_carte.png"));
		TextureRegion backgroundTextureRegion = new TextureRegion(backgroundTexture, 1024, 544);
		Image imgBackground = new Image(backgroundTextureRegion);
		imgBackground.touchable = false;
		stage.addActor(imgBackground);
		
		Texture carteTexture = new Texture(Gdx.files.internal("data/carte.png"));
		TextureRegion carteTextureRegion = new TextureRegion(carteTexture, 1024, 544);
		Image carte = new Image(carteTextureRegion);
		carte.touchable = false;
		stage.addActor(carte);
		
		stage.addActor(groupZones);
		stage.addActor(groupVilles);
	
		createEntities();
	}

	@Override
	public void resume() {

	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, false);
		stage.getCamera().position.set(VIRTUAL_WIDTH/2, VIRTUAL_HEIGHT/2, 0);

	}

	@Override
	public void render(float delta) {
		GL10 gl = Gdx.graphics.getGL10();

		// Update model

		// clear previous frame
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	@Override
	public void pause() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	
	private void createEntities() {
		for (int i = 1; i <= 6; i++) {
			Zone zone = createZone(i);
			groupZones.addActor(new ZoneActor(zone));
		}
	}

	private Zone createZone(int id) {
		final String nom; // Nom de la zone;		
		final String filepath = "zones/zone" + id + ".data";
		boolean first = true;

		try {
			BufferedReader buff = Gdx.files.internal(filepath).reader(1024);
			String line;

			nom = buff.readLine();
			
			// Creation de la zone.
			Zone zone = new Zone(id, nom);

			while ((line = buff.readLine()) != null) {
				String tab[] = line.split(" ");

				if (tab.length == 3) {
					tab[0] = tab[0].replace('_', ' ');
					if (first) {
						Usine usine = new Usine(zone, tab[0],
								Integer.valueOf(tab[1]),
								Integer.valueOf(tab[2]));
						
						groupVilles.addActor(new VilleActor(usine));
						zone.addVille(usine);
					} else {
						Ville ville = new Ville(zone, tab[0],
								Integer.valueOf(tab[1]),
								Integer.valueOf(tab[2]));
						
						groupVilles.addActor(new VilleActor(ville));	
						zone.addVille(ville);
					}
				} else {
					System.err.println("Erreur lecture " + filepath
							+ "continue quand même...");
				}
			}

			buff.close();
			return zone;
		} catch (FileNotFoundException e) {
			System.err.println("Fichier " + filepath
					+ " introuvable ! Aucune ville chargée pour cette zone.");
		} catch (IOException e) {
			System.err.println("Erreur à la lecture de " + filepath + ".");
		}
		
		return null;
	}
	
}