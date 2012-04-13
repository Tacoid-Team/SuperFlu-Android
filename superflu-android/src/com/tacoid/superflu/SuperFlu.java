package com.tacoid.superflu;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SuperFlu implements ApplicationListener, InputProcessor {
	private static final int VIRTUAL_WIDTH = 1024;
	private static final int VIRTUAL_HEIGHT = 544; // XXX: Très plat :);

	private Texture backgroundTexture;
	private TextureRegion villeTextureRegion;
	private TextureRegion backgroundTextureRegion;
	private Stage stage;
	private int x = 0;
	private int y = 520;

	@Override
	public void create() {
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
	
		backgroundTexture = new Texture(
				Gdx.files.internal("data/fond_carte.png"));
		backgroundTextureRegion = new TextureRegion(backgroundTexture, 1024,
				544);
		stage.addActor(new Image(backgroundTextureRegion));
		
		villeTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("data/ville.png")), 24, 24);
		Image img = new Image(villeTextureRegion);
		img.x = 1000;
		img.y = 520;
		stage.addActor(img);

		Gdx.input.setInputProcessor(this);
		
		Texture textureAvion = new Texture(Gdx.files.internal("data/avion.png"));
		stage.addActor(new Image(new TextureRegion(textureAvion, 48, 48))); //XXX: En fait un vrai actor et pas juste une image !
		//XXX: D'ailleurs une question : Est-ce qu'on fait de l'entity ville un actor ou est-ce qu'on fait une classe VilleActor qui contient une référence vers l'entity associée ?
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
	public void render() {
		GL10 gl = Gdx.graphics.getGL10();

		// Update model
		x = (x + 1) % VIRTUAL_WIDTH;

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