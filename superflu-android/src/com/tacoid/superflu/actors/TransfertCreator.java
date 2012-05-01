package com.tacoid.superflu.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tacoid.superflu.GameScreen;
import com.tacoid.superflu.entities.Transfert;

public class TransfertCreator extends Actor {
	
	/* Singleton Instance */
	static TransfertCreator instance = null;
	
	ImmediateModeRenderer10 renderer;
	
	private float start_x = 0;
	private float start_y = 0;
	private float end_x = 0;
	private float end_y = 0;
	
	private boolean enabled = false;
	
	Texture arrow;
	float tex_offset = 0.0f;

	private float norm;

	private float ortho_x;

	private float ortho_y;
	
	private VilleActor dest_ville = null;
	private VilleActor start_ville = null;
	
	private static final float target_size = 30.0f;

	public static TransfertCreator getInstance() {
        if (null == instance) {
            instance = new TransfertCreator();
        }
        return instance;
    }
	
	private TransfertCreator() {
		this.x = 0;
		this.y = 0;
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
		renderer =  new ImmediateModeRenderer10();
		arrow = new Texture(Gdx.files.internal("images/transfert_arrow.png"));
    }
	
	private void drawTarget() {
        if(dest_ville != null) {
	        Gdx.gl11.glDisable(GL11.GL_TEXTURE_2D);
	        Gdx.gl11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	        
	        renderer.begin(GL11.GL_LINE_LOOP);
	        {
	        	renderer.vertex(dest_ville.x + target_size , dest_ville.y, 0);
	        	renderer.vertex(dest_ville.x, dest_ville.y + target_size , 0);
	        	renderer.vertex(dest_ville.x - target_size , dest_ville.y, 0);
	        	renderer.vertex(dest_ville.x, dest_ville.y- target_size , 0);
	        }
	        renderer.end();
	        
	        Gdx.gl11.glEnable(GL10.GL_TEXTURE_2D);
        }	
	}
	
	private void drawTransfertBar() {
		int l = 6;
		int rayon = 20;
		
		float x = start_x + rayon*(end_x-start_x)/norm;
		float y = start_y + rayon*(end_y-start_y)/norm;
		
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
                renderer.vertex( x-l*ortho_x, y-l*ortho_y,0);
                renderer.texCoord(0, norm/(l*2)-tex_offset);
                renderer.vertex( end_x-l*ortho_x, end_y-l*ortho_y, 0);
                renderer.texCoord(1, -tex_offset);
                renderer.vertex( x+l*ortho_x, y+l*ortho_y,0);
                renderer.texCoord(1,norm/(l*2)-tex_offset);
                renderer.vertex( end_x+l*ortho_x, end_y+l*ortho_y, 0);
        }
        renderer.end();
        tex_offset += 0.1f;
        if(tex_offset >= 16.0f)
        	tex_offset = 0.0f;
        
        Gdx.gl11.glEnable(GL10.GL_TEXTURE_2D);
        Gdx.gl11.glDisable(GL11.GL_BLEND);
        Gdx.gl11.glPopMatrix();	
	}
	
	@Override
	public void draw(SpriteBatch arg0, float arg1) {
		if(enabled) {
			drawTransfertBar();
			drawTarget();
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
		
		start_ville = (VilleActor) GameScreen.getInstance().getGroupVilles().hit(start_x, start_y);
		System.out.println(start_ville.getVille());
	}
	
	public void updateTarget(float x, float y) {
		end_x = x;
		end_y = y;
		
		norm = (float) Math.sqrt((end_x-start_x)*(end_x-start_x)+(end_y-start_y)*(end_y-start_y));
		ortho_x = (end_y-start_y)/norm;
		ortho_y = -(end_x-start_x)/norm;
		
		/*XXX: Je compare des r�f�rences, c'est peut �tre pas top... */
		if((VilleActor) GameScreen.getInstance().getGroupVilles().hit(end_x, end_y) != start_ville) {
			dest_ville = (VilleActor) GameScreen.getInstance().getGroupVilles().hit(end_x, end_y);
		}
	}
	
	public void disable() {
		if(start_ville != null && dest_ville != null) {
			GameScreen.getInstance().getGameLogic().addTransfert(new Transfert(start_ville.getVille(), 
																			   dest_ville.getVille(), 
																			   start_ville.getVille().getStockTraitements(), 
																			   0, 
																			   GameScreen.getInstance().getGameLogic().getTime()));
		}
		start_ville = null;
		dest_ville = null;
		enabled = false;
	}

}
