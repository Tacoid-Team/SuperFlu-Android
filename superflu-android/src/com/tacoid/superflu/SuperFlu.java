package com.tacoid.superflu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.tacoid.superflu.LoadingScreen;

public class SuperFlu extends Game {

	private static SuperFlu instance = null;
	public AssetManager manager;
	private LoadingScreen loadingScreen;
	private boolean loaded;
	
	private SuperFlu() {}
	
	public static SuperFlu getInstance() {
		if (instance == null) {
			instance = new SuperFlu();
		}
		return instance;
	}
	
	@Override
	public void render() {
		if (manager.update()) {
			if (!loaded) {
				getScreen().show();
			}
		} else {
			if (loadingScreen != null) loadingScreen.render(Gdx.graphics.getDeltaTime());
			loaded = false;
		}
		super.render();
	}
	
	@Override
	public void create() {
		loadingScreen = LoadingScreen.getInstance(); 
		setScreen(loadingScreen);
		Gdx.input.setCatchBackKey(true);
		
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
		manager.load("images/credits.png", Texture.class);
		manager.load("images/dna1.png", Texture.class);
		manager.load("images/dna2.png", Texture.class);
		manager.load("images/dna3.png", Texture.class);
		manager.load("images/dna4.png", Texture.class);
		manager.load("images/dna5.png", Texture.class);
		manager.load("images/dna6.png", Texture.class);
		manager.load("images/dna7.png", Texture.class);
		manager.load("images/dna8.png", Texture.class);
		manager.load("images/dna9.png", Texture.class);
		manager.load("images/dna10.png", Texture.class);
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
