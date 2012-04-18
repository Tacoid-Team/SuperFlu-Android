package com.tacoid.superflu;

import com.badlogic.gdx.Game;

public class SuperFlu extends Game {

	@Override
	public void create() {
		MainMenuScreen menuScreen = new MainMenuScreen(this);
		setScreen(menuScreen);
	}

}
