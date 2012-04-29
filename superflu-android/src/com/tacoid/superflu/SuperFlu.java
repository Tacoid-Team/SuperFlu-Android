package com.tacoid.superflu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class SuperFlu extends Game {

	private static SuperFlu instance = null;
	public AssetManager manager;
	
	private SuperFlu() {}
	
	public static SuperFlu getInstance() {
		if (instance == null) {
			instance = new SuperFlu();
		}
		return instance;
	}
	
	@Override
	public void create() {
		setScreen(LoadingScreen.getInstance());
		
		loadAssets();
	}

	private void loadAssets() {
		manager = new AssetManager();
		
		manager.load("images/about.png", Texture.class);
		manager.load("images/aide.png", Texture.class);
		manager.load("images/aide1.png", Texture.class);
		manager.load("images/aide2.png", Texture.class);
		manager.load("images/aide3.png", Texture.class);
		manager.load("images/aide4.png", Texture.class);
		manager.load("images/avion.png", Texture.class);
		manager.load("images/carte_zone1.png", Texture.class);
		manager.load("images/carte_zone2.png", Texture.class);
		manager.load("images/carte_zone3.png", Texture.class);
		manager.load("images/carte_zone4.png", Texture.class);
		manager.load("images/carte_zone5.png", Texture.class);
		manager.load("images/carte_zone6.png", Texture.class);
		manager.load("images/carte.png", Texture.class);
		manager.load("images/exit.png", Texture.class);
		manager.load("images/fond_carte.png", Texture.class);
		manager.load("images/HL_usine.png", Texture.class);
		manager.load("images/HL_ville.png", Texture.class);
		manager.load("images/infected.png", Texture.class);
		manager.load("images/infected2.png", Texture.class);
		manager.load("images/options.png", Texture.class);
		manager.load("images/superflu.png", Texture.class);
		manager.load("images/transfert_arrow.png", Texture.class);
		manager.load("images/usine.png", Texture.class);
		manager.load("images/ville.png", Texture.class);
		manager.load("images/mono.png", Texture.class);
		manager.load("images/multi.png", Texture.class);
		manager.load("music/soft.mp3", Music.class);
		manager.load("music/hard.mp3", Music.class);
	}
	
}
