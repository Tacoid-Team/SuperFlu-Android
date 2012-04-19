package com.tacoid.superflu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class HelpScreen implements Screen {

	private static HelpScreen instance = null;

	private HelpScreen() {}
	
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
	public void render(float arg0) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
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
		// TODO Auto-generated method stub
		
	}
	
	public static HelpScreen getInstance() {
		if (instance == null) {
			instance  = new HelpScreen();
		}
		return instance;
	}

}
