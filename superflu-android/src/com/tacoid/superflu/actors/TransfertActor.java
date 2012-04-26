package com.tacoid.superflu.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tacoid.superflu.entities.Transfert;

public class TransfertActor extends Actor{
	
	private final Transfert transfert;
	
	public TransfertActor(Transfert transfert) {
		this.transfert = transfert;
	}
		
	@Override
	public void draw(SpriteBatch arg0, float arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Actor hit(float arg0, float arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
