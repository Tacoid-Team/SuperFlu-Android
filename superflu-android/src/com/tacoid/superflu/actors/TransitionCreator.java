package com.tacoid.superflu.actors;

import android.opengl.GLSurfaceView.Renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TransitionCreator extends Actor {
	
	/* Singleton Instance */
	static TransitionCreator instance = null;
	
	private float start_x = 0;
	private float start_y = 0;
	private float end_x = 0;
	private float end_y = 0;
	
	private boolean enabled = true;

	public static TransitionCreator getInstance() {
        if (null == instance) {
            instance = new TransitionCreator();
        }
        return instance;
    }
	
	private TransitionCreator() {
		this.x = 0;
		this.y = 0;
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
    }
	
	@Override
	public void draw(SpriteBatch arg0, float arg1) {
		if(enabled) {
			ImmediateModeRenderer10 renderer =  new ImmediateModeRenderer10();
			Gdx.gl11.glDisable(GL10.GL_TEXTURE_2D);
	        Gdx.gl11.glPushMatrix();
	
	        Gdx.gl11.glColor4f(1.0f, 1.0f, 1.0f, 1);
	        Gdx.gl11.glLineWidth(3);
	        renderer.begin(GL10.GL_LINES);
	        {
	                renderer.vertex( start_x, start_y,0);
	                renderer.vertex( end_x, end_y, 0);
	        }
	        renderer.end();
	        
	        Gdx.gl11.glPopMatrix();
	        Gdx.gl11.glEnable(GL10.GL_TEXTURE_2D);
		}
	}

	@Override
	public Actor hit(float arg0, float arg1) {
		return null;
	}

	public void enable(float x, float y) {
		enabled = true;
		System.out.println(x + "," + y);
		start_x = x;
		start_y = y;
		end_x = x;
		end_y = y;
	}
	
	public void updateTarget(float x, float y) {
		end_x = x;
		end_y = y;
	}
	
	public void disable() {
		enabled = false;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
