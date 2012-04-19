package com.tacoid.superflu;
import com.badlogic.gdx.backends.jogl.JoglApplication;

public class SuperFluDesktop {
	public static void main(String[] argv) {
		//new JoglApplication(new SuperFlu(), "SuperFlu", 800, 480, false);
		new JoglApplication(SuperFlu.getInstance(), "SuperFlu", 1024, 600, false);

	}
}