package com.tacoid.superflu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HelpScreen implements Screen, InputProcessor {
	private static final int VIRTUAL_WIDTH = 1024;
	private static final int VIRTUAL_HEIGHT = 576;
	private static HelpScreen instance = null;
	
	private Stage stage;
	private int current = 0;
	private Image aide[] = new Image[4];
	private SuperFlu superflu;

	private HelpScreen() {
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		superflu = SuperFlu.getInstance();
		Texture backgroundTexture = superflu.manager.get("images/fond_carte.png", Texture.class);
		TextureRegion backgroundTextureRegion = new TextureRegion(backgroundTexture, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		Image imgBackground = new Image(backgroundTextureRegion);
		imgBackground.touchable = false;
		stage.addActor(imgBackground);
		
		aide[0] = new Image(superflu.manager.get("images/aide1.png", Texture.class));
		aide[1] = new Image(superflu.manager.get("images/aide2.png", Texture.class));
		aide[2] = new Image(superflu.manager.get("images/aide3.png", Texture.class));
		aide[3] = new Image(superflu.manager.get("images/aide4.png", Texture.class));
		stage.addActor(aide[current]);
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
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {
		stage.setViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, false);
		stage.getCamera().position.set(VIRTUAL_WIDTH/2, VIRTUAL_HEIGHT/2, 0);
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
	public boolean keyDown(int key) {
		if (key == Keys.BACK) {
			stage.removeActor(aide[current]);
			current--;
			if (current < 0) {
				SuperFlu.getInstance().setScreen(MainMenuScreen.getInstance());
				current = 0;
			}
			stage.addActor(aide[current]);
			return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char key) {
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
		stage.removeActor(aide[current]);
		if (current < 3) {
			current++;
		} else {
			current = 0;
			SuperFlu.getInstance().setScreen(MainMenuScreen.getInstance());
		}
		stage.addActor(aide[current]);
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
