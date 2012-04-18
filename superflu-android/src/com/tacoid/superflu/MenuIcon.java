package com.tacoid.superflu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class MenuIcon extends Actor {

	private TextureRegion region;
	private BitmapFont font;
	private String text;
	
	protected MenuIcon(String image, String text, int width, int height) {
		this.text = text;
		Texture texture = new Texture(Gdx.files.internal(image));
		region = new TextureRegion(texture, width, height);
		font = new BitmapFont();
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(SpriteBatch batch, float alpha) {
		batch.draw(region, x, y);
		font.draw(batch, text, x - 70, y + width / 2 + 6);
	}

	@Override
	public abstract Actor hit(float x, float y);
	
}