package com.brad.game.jade.component;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.brad.game.jade.Component;
import com.brad.game.renderer.Texture;

public class SpriteRenderer extends Component {

	private Vector4f color;
	private Sprite sprite;

	public SpriteRenderer( Vector4f color ) {
		this.color 		= color;
		this.sprite 	= new Sprite( null );
	}
	
	public SpriteRenderer( Sprite sprite ) {
		this.sprite 	= sprite;
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
		return this.sprite.getTexture();
	}

	public Vector2f[] getTexCoords() {
		return this.sprite.getTexCoords();
	}
}
