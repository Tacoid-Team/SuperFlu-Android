package com.tacoid.superflu.actors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tacoid.superflu.GameLogic;

public class GlobalStatsActor extends Actor {

	private GameLogic gameLogic;
	private BitmapFont font;

	public GlobalStatsActor(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		font = new BitmapFont();
	}
	
	@Override
	public void draw(SpriteBatch batch, float alpha) {
		// XXX: Afficher en millions ?  (/ 1000 + "M")
		font.draw(batch, "Pop. totale : " + gameLogic.getPopulationMondiale(), 10, 350);
		font.draw(batch, "Pop. malade : " + gameLogic.getPopulationInfectee(), 10, 336);
		font.draw(batch, "Morts : " + gameLogic.getPopulationMorte(), 10, 322);
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
