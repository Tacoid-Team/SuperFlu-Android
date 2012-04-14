package com.tacoid.superflu;

import com.badlogic.gdx.Game;

public class SuperFlu extends Game {

	@Override
	public void create() {
		GameScreen gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

}
