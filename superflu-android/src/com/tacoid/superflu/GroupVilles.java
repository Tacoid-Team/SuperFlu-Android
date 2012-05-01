package com.tacoid.superflu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class GroupVilles extends Group {
	
	private float dist(float x1, float y1, float x2, float y2) {
		return (x1 - x2) * (x1 - x2) + (y2 - y1) * (y2 - y1);
	}
	@Override
	public Actor hit(float x, float y) {
		Actor min = null;
		float dmin = 0;
		float rmax = 5000;
		for (Actor c : children) {
			float d = dist(x, y, c.x, c.y);
			if (d < rmax && (min == null || d < dmin)) {
				dmin = d;
				min = c;
			}
		}
		return min;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer) {
		Actor actor = hit(x, y);
		lastTouchedChild = actor;
		
		if (actor != null) {
			if (actor.touchDown(x, y, pointer)) {
				if (stage != null && stage.getTouchFocus(pointer) == null) stage.setTouchFocus(actor, pointer);	
			}
		
			return true;
		}
		return false;
	}
}
