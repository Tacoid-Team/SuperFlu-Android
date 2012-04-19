package com.tacoid.superflu.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tacoid.superflu.SuperFlu;
import com.tacoid.superflu.entities.Zone;

public class ZoneActor extends Actor {

	private Zone zone;
	private TextureRegion zoneTextureRegion;
	
	public ZoneActor(Zone zone) {
		this.zone = zone;
		SuperFlu superflu = SuperFlu.getInstance();
		zoneTextureRegion = new TextureRegion(superflu.manager.get("images/carte_zone" + zone.getId() + ".png", Texture.class), 1024, 544);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(0, 0.3f, 0, 0);
		batch.draw(zoneTextureRegion, 0, 0);
		batch.setColor(Color.WHITE);
	}

	@Override
	public Actor hit(float arg0, float arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
