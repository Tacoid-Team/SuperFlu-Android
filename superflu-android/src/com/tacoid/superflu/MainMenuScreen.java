package com.tacoid.superflu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainMenuScreen implements Screen {
	private static final int VIRTUAL_WIDTH = 1024;
	private static final int VIRTUAL_HEIGHT = 576;
	
	private Stage stage;
	final private SuperFlu superflu;

	private class PlayMono extends Actor {

		private TextureRegion region;

		public PlayMono() {
			Texture texture = new Texture(Gdx.files.internal("images/jouer.png"));
			region = new TextureRegion(texture, 303, 79);
			x = 361;
			y = 248;
			width = 303;
			height = 79;
		}
		
		@Override
		public void draw(SpriteBatch batch, float alpha) {
			batch.draw(region, x, y);
		}

		@Override
		public Actor hit(float x, float y) {
			return x >= 0 && x <= width && y >= 0 && y <= height ? this : null;
		}
		
		@Override
		public boolean touchDown(float x, float y, int pointer) {
			superflu.setScreen(new GameScreen(superflu));
			return true;
		}
	}
	
	public MainMenuScreen(SuperFlu superflu) {
		this.superflu = superflu;
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		
		Texture backgroundTexture = new Texture(Gdx.files.internal("images/fond_carte.png"));
		TextureRegion backgroundTextureRegion = new TextureRegion(backgroundTexture, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		Image imgBackground = new Image(backgroundTextureRegion);
		imgBackground.touchable = false;
		stage.addActor(imgBackground);
		
		Texture titleTexture = new Texture(Gdx.files.internal("images/superflu.png"));
		TextureRegion titleTextureRegion = new TextureRegion(titleTexture, 815, 175);
		Image imgTitle = new Image(titleTextureRegion);
		imgTitle.touchable = false;
		imgTitle.x = 105;
		imgTitle.y = 360;
		stage.addActor(imgTitle);
		
		stage.addActor(new PlayMono());
		
		Gdx.input.setInputProcessor(stage);
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
		// TODO Auto-generated method stub
		
	}

}
