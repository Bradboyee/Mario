package com.brad.game.jade.component;

import org.joml.Vector2f;

import com.brad.game.renderer.Texture;

public class Sprite {
	private Texture texture;
	private Vector2f[] texCoords;

	public Sprite( Texture texture ) {
		this.texture = texture;
		this.texCoords = new Vector2f[] { 
										new Vector2f( 1.0f, 1.0f ), 
										new Vector2f( 1.0f, 0.0f ), 
										new Vector2f( 0.0f, 0.0f ),
										new Vector2f( 0.0f, 1.0f ), 
										};
	}
	
	public Sprite(Texture texture, Vector2f[] texCoord) {
		this.texture = texture;
		this.texCoords = texCoord;
	}
	
	public Texture getTexture() {
		return this.texture;
	}
	
	public Vector2f[] getTexCoords() {
		return this.texCoords;
	}
	
	
}
