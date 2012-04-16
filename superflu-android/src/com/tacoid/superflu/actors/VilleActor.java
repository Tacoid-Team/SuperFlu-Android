package com.tacoid.superflu.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tacoid.superflu.entities.Ville;

public class VilleActor extends Actor {

	private TextureRegion villeTextureRegion;
	private Ville ville;
	private ImmediateModeRenderer10 renderer;

	public VilleActor(Ville ville) {
		this.ville = ville;
		if (ville.isUsine()) {
			villeTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("data/usine.png")), 25, 20);
			this.width = 25;
			this.height = 20;
		} else {
			villeTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("data/ville.png")), 20, 20);
			this.width = 20;
			this.height = 20;
		}
		this.x = ville.getX();
		this.y = 544 - ville.getY();
		
		renderer = new ImmediateModeRenderer10();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Gdx.gl11.glDisable(GL10.GL_TEXTURE_2D);
		Gdx.gl11.glPushMatrix();
		Gdx.gl11.glTranslatef(x, y, 0);

		// On dessine la barre de contamination
		Gdx.gl11.glColor4f(0, 0, 0, 1);
		Gdx.gl11.glLineWidth(6);
		renderer.begin(GL10.GL_LINES);
		{
			renderer.vertex( -width/2 - 1, -height/2 - 3, 0);
			renderer.vertex( width/2 + 1, -height/2 - 3, 0);
		}
		renderer.end();
		
		float p = (float)ville.getPourcentageInfectes()/100.0f;
		Gdx.gl11.glColor4f(p, 1-p, 0, 1);
		Gdx.gl11.glLineWidth(4);
		renderer.begin(GL10.GL_LINES);
		{
			renderer.vertex( -width/2, -height/2 - 3, 0);
			renderer.vertex( -width/2 + p*width , -height/2 - 3, 0);
		}
		renderer.end();
		
		
		Gdx.gl11.glPopMatrix();
		Gdx.gl11.glEnable(GL10.GL_TEXTURE_2D);
		batch.draw(villeTextureRegion, x - width / 2, y - height/2);
	}

	@Override
	public Actor hit(float x, float y) {
		return x > -width/2 && x < width/2 && y > -height/2 && y < height/2 ? this : null;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		System.out.println("touch down !" + ville.getNom());
		return true;
	}
}
