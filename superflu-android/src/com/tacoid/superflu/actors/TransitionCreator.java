package com.tacoid.superflu.actors;

import android.opengl.GLSurfaceView.Renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TransitionCreator extends Actor {
	
	/* Singleton Instance */
	static TransitionCreator instance = null;
	
	private float start_x = 0;
	private float start_y = 0;
	private float end_x = 0;
	private float end_y = 0;
	
	private boolean enabled = false;
	
	ImmediateModeRenderer10 renderer;
	
	Texture arrow;
	float tex_offset = 0.0f;

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
		renderer =  new ImmediateModeRenderer10();
		arrow = new Texture(Gdx.files.internal("images/transfert_arrow.png"));
    }
	
	@Override
	public void draw(SpriteBatch arg0, float arg1) {
		float norm = (float) Math.sqrt((end_x-start_x)*(end_x-start_x)+(end_y-start_y)*(end_y-start_y));
		norm = norm/4.0f;
		float ortho_x = (end_y-start_y)/norm;
		float ortho_y = -(end_x-start_x)/norm;
		if(enabled) {
			Gdx.gl11.glPushMatrix();
	
	        Gdx.gl11.glColor4f(1.0f, 1.0f, 1.0f, 1);
	        
	        Gdx.gl11.glLineWidth(3);
	        
	        Gdx.gl11.glEnable(GL11.GL_BLEND);
	        Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
	        arrow.setWrap( TextureWrap.Repeat, TextureWrap.Repeat );
	        arrow.bind();
	        
	        renderer.begin(GL11.GL_TRIANGLE_STRIP);
	        {
	        		renderer.texCoord(0, -tex_offset);
	                renderer.vertex( start_x-ortho_x, start_y-ortho_y,0);
	                renderer.texCoord(0, norm-tex_offset);
	                renderer.vertex( end_x-ortho_x, end_y-ortho_y, 0);
	                renderer.texCoord(1, -tex_offset);
	                renderer.vertex( start_x+ortho_x, start_y+ortho_y,0);
	                renderer.texCoord(1,norm-tex_offset);
	                renderer.vertex( end_x+ortho_x, end_y+ortho_y, 0);
	        }
	        renderer.end();
	        tex_offset += 0.2f;
	        if(tex_offset >= 16.0f)
	        	tex_offset = 0.0f;
	        
	        Gdx.gl11.glPopMatrix();
	        Gdx.gl11.glEnable(GL10.GL_TEXTURE_2D);
	        Gdx.gl11.glDisable(GL11.GL_BLEND);
		}
	}

	@Override
	public Actor hit(float arg0, float arg1) {
		return null;
	}

	public void enable(float x, float y) {
		enabled = true;
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
