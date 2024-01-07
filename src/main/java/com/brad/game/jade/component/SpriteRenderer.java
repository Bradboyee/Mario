package com.brad.game.jade.component;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.brad.game.jade.Component;
import com.brad.game.renderer.Texture;

public class SpriteRenderer extends Component {

	private Texture texture;
	private Vector4f color;

	public SpriteRenderer( Vector4f color ) {
		this.color 		= color;
		this.texture 	= null;
	}
	
	public SpriteRenderer( Texture texture ) {
		this.texture 	= texture;
		this.color 		= new Vector4f( 1.0f, 1.0f, 1.0f, 1.0f );
	}

	@Override
	public void start() {
	}

	@Override
	public void update(float dt) {
	}
	
	/*
	====================
	getColor
	====================
	*/
	public Vector4f getColor() {
		return this.color;
	}
	
	/*
	====================
	getTexture
	====================
	*/
	public Texture getTexture() {
		return this.texture;
	}

	public Vector2f[] getTexCoords() {
		Vector2f[] texCoords = { 
				new Vector2f( 1.0f, 1.0f ), 
				new Vector2f( 1.0f, 0.0f ), 
				new Vector2f( 0.0f, 0.0f ),
				new Vector2f( 0.0f, 1.0f ), 
				};
		return texCoords;
	}
}
