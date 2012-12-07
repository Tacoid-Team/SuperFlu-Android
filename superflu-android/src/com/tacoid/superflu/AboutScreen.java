package com.tacoid.superflu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class AboutScreen implements Screen, InputProcessor {
	private static final int VIRTUAL_WIDTH = 1024;
	private static final int VIRTUAL_HEIGHT = 576;
	private static AboutScreen instance = null;
	
	private Stage stage;

	private Texture credits;
	private SuperFlu superflu;
	
	private class Dna extends Image {
		
		private List<TextureRegion> dna = new ArrayList<TextureRegion>();
		private Animation animation;
		private float stateTime;
		
		public Dna() {
			// TODO Auto-generated method stub
			for (int i = 1; i <= 10; i++) {
				dna.add(new TextureRegion(superflu.manager.get("images/dna" + i +".png", Texture.class)));
			}
			
			animation = new Animation(0.05f, dna);
			stateTime = 0f;
		}
		
		@Override
		public void draw(SpriteBatch batch, float alpha) {
			stateTime += Gdx.graphics.getDeltaTime();
			TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
			batch.draw(currentFrame, 500, -80);
		}
	}
	
	private AboutScreen() {
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		superflu = SuperFlu.getInstance();
		credits = superflu.manager.get("images/credits.png", Texture.class);
		
		Texture backgroundTexture = superflu.manager.get("images/fond_carte.png", Texture.class);
		TextureRegion backgroundTextureRegion = new TextureRegion(backgroundTexture, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		Image imgBackground = new Image(backgroundTextureRegion);
		imgBackground.touchable = false;
		stage.addActor(imgBackground);
		
		stage.addActor(new Image(credits));
		stage.addActor(new Dna());
	}
	
	public static AboutScreen getInstance() {
		if (instance == null) {
			instance = new AboutScreen();
		}
		return instance;
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
	public void resize(int width, int height) {
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

	@Override
	public boolean keyDown(int key) {
		if (key == Keys.BACK) {
			SuperFlu.getInstance().setScreen(MainMenuScreen.getInstance());
			return true;
		}
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
		SuperFlu.getInstance().setScreen(MainMenuScreen.getInstance());
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
		return false;
	}

}
