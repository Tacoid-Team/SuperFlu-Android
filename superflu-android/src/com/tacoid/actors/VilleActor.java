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
		villeTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("data/ville.png")), 24, 24);
		this.x = ville.getX();
		this.y = ville.getY();
		this.width = 24;
		this.height = 24;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(villeTextureRegion, x - width / 2, 544 - y - height/2);
	}

	@Override
	public Actor hit(float x, float y) {
		System.out.println(x + " " + y);
		return this;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		return true;
	}
}
