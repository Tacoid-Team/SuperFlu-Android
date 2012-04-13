package com.tacoid.superflu;
import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.tacoid.superflu.SuperFlu;

public class SuperFluDesktop {
	public static void main(String[] argv) {
		//new JoglApplication(new SuperFlu(), "SuperFlu", 800, 480, false);
		new JoglApplication(new SuperFlu(), "SuperFlu", 1024, 600, false);

	}
}