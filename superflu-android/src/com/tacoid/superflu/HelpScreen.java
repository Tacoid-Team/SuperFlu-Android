package com.tacoid.superflu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HelpScreen implements Screen, InputProcessor {
	private static final int VIRTUAL_WIDTH = 1024;
	private static final int VIRTUAL_HEIGHT = 576;
	private static HelpScreen instance = null;
	private int current = 0;
	private Texture aide[] = new Texture[4];
	private SpriteBatch batch;
	private TextureRegion backgroundTextureRegion;

	private HelpScreen() {
		SuperFlu superflu = SuperFlu.getInstance();
		aide[0] = superflu.manager.get("images/aide1.png", Texture.class);
		aide[1] = superflu.manager.get("images/aide2.png", Texture.class);
		aide[2] = superflu.manager.get("images/aide3.png", Texture.class);
		aide[3] = superflu.manager.get("images/aide4.png", Texture.class);
		batch = new SpriteBatch();
		Texture backgroundTexture = superflu.manager.get("images/fond_carte.png", Texture.class);
		backgroundTextureRegion = new TextureRegion(backgroundTexture, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
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
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(backgroundTextureRegion, 0, 0);
		batch.draw(aide[current], 0, 0);
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}
	
	public static HelpScreen getInstance() {
		if (instance == null) {
			instance  = new HelpScreen();
		}
		return instance;
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		current++;
		if (current >= 4) {
			current = 0;
			SuperFlu.getInstance().setScreen(MainMenuScreen.getInstance());
		}
		return true;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
