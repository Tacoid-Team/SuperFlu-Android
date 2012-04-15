package com.tacoid.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tacoid.superflu.entities.Ville;

public class VilleActor extends Actor {

	private TextureRegion villeTextureRegion;
	private Ville ville;

	public VilleActor(Ville ville) {
		this.ville = ville;
		if (ville.isUsine()) {
			villeTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("data/usine.png")), 25, 20);
			this.width = 25;
			this.height = 20;
		} else {
			villeTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("data/ville.png")), 24, 24);
			this.width = 24;
			this.height = 24;
		}
		this.x = ville.getX();
		this.y = 544 - ville.getY();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
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
