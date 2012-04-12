package com.tacoid.superflu;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SuperFlu implements ApplicationListener, InputProcessor {
	private static final int VIRTUAL_WIDTH = 1024;
	private static final int VIRTUAL_HEIGHT = 544; // XXX: Très plat :);
	private static final float ASPECT_RATIO = (float) VIRTUAL_WIDTH
			/ (float) VIRTUAL_HEIGHT;
	private Camera camera;

	private SpriteBatch spriteBatch;
	private Texture backgroundTexture;
	private TextureRegion villeTextureRegion;
	private TextureRegion backgroundTextureRegion;
	private Sprite spriteAvion;
	private Stage stage;
	private int x = 0;
	private int y = 0;

	@Override
	public void create() {
		camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		
		stage = new Stage(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, true);
		villeTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("data/ville.png")), 24, 24);
		Image img = new Image(villeTextureRegion);
		img.x = 100;
		img.y = 100;
		img.width = 24;
		img.height = 24;
		img.originX = 0;
		img.originY = 0;
		stage.addActor(img);

		Gdx.input.setInputProcessor(this);

		spriteBatch = new SpriteBatch();
		backgroundTexture = new Texture(
				Gdx.files.internal("data/fond_carte.png"));
		backgroundTextureRegion = new TextureRegion(backgroundTexture, 1024,
				544);

		Texture textureAvion = new Texture(Gdx.files.internal("data/avion.png"));
		spriteAvion = new Sprite(new TextureRegion(textureAvion, 48, 48));
	}

	@Override
	public void resume() {

	}

	@Override
	public void resize(int width, int height) {
		// calculate new viewport
		float aspectRatio = (float) width / (float) height;
		float scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);

		if (aspectRatio > ASPECT_RATIO) {
			scale = (float) height / (float) VIRTUAL_HEIGHT;
			crop.x = (width - VIRTUAL_WIDTH * scale) / 2f;
		} else if (aspectRatio < ASPECT_RATIO) {
			scale = (float) width / (float) VIRTUAL_WIDTH;
			crop.y = (height - VIRTUAL_HEIGHT * scale) / 2f;
		} else {
			scale = (float) width / (float) VIRTUAL_WIDTH;
		}

		float w = (float) VIRTUAL_WIDTH * scale;
		float h = (float) VIRTUAL_HEIGHT * scale;

		// set viewport
		Gdx.gl.glViewport((int) crop.x, (int) crop.y, (int) w, (int) h);
	}

	public void renderBackground() {
		spriteBatch.begin();
		spriteBatch.disableBlending();
		spriteBatch.draw(backgroundTextureRegion, 0, VIRTUAL_HEIGHT - 544);
		spriteBatch.end();
	}

	@Override
	public void render() {
		// Update model
		x = (x + 1) % 500;

		// ----------

		// update camera
		camera.update();
		camera.apply(Gdx.gl10);

		// clear previous frame
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderBackground();
		spriteBatch.begin();
		spriteBatch.enableBlending();
		spriteBatch.draw(spriteAvion, x, y);
		spriteBatch.end();
		
		stage.draw();
	}

	@Override
	public void pause() {

	}

	@Override
	public void dispose() {

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
	public boolean touchDown(int x, int y, int arg2, int arg3) {
		this.x = x;
		this.y = VIRTUAL_HEIGHT - y; // XXX: Ne marche pas tout à fait, j'ai testé ça vite fait c'est tout.
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