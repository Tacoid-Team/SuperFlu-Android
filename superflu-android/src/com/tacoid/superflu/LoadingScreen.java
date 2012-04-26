package com.tacoid.superflu;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen implements Screen {
	private static final int VIRTUAL_WIDTH = 1024;
	private static final int VIRTUAL_HEIGHT = 576;
	private static LoadingScreen instance = null;
	private SuperFlu superflu;
	private BitmapFont font;
	private SpriteBatch spriteBatch;
	
	public LoadingScreen() {
		this.superflu = SuperFlu.getInstance();
		this.font = new BitmapFont();
		this.spriteBatch = new SpriteBatch();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (superflu.manager.update()) {
			superflu.setScreen(MainMenuScreen.getInstance());
		}
		NumberFormat nf = new DecimalFormat("0.00"); 
		String loadMsg = "Loading " + nf.format(superflu.manager.getProgress() * 100) + "%";
		spriteBatch.begin();
		font.draw(spriteBatch, loadMsg, VIRTUAL_WIDTH / 2 - font.getBounds(loadMsg).width / 2, VIRTUAL_HEIGHT / 2);
		spriteBatch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public static LoadingScreen getInstance() {
		if (instance == null) {
			instance = new LoadingScreen();
		}
		return instance;
	}

}
