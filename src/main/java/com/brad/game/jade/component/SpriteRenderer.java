package com.brad.game.jade.component;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.brad.game.jade.Component;
import com.brad.game.jade.Transform;
import com.brad.game.renderer.Texture;

public class SpriteRenderer extends Component {

	private Vector4f color;
	private Sprite sprite;
	private Transform lastTransform;
	private boolean isDirty = false;

	public SpriteRenderer( Vector4f color ) {
		this.color 		= color;
		this.sprite 	= new Sprite( null );
		isDirty = true;
	}
	
	public SpriteRenderer( Sprite sprite ) {
		this.sprite 	= sprite;
		this.color 		= new Vector4f( 1.0f, 1.0f, 1.0f, 1.0f );
		isDirty = true;
	}

	@Override
	public void start() {
		//	keep the transform.
		this.lastTransform = gameObject.transform.copy();
	}

	@Override
	public void update(float dt) {
		//	if not equals transform.
		if ( !this.lastTransform.equals( this.gameObject.transform ) ) {
			//	edit lastTransform transform by gameObject's transform value reference.
			this.gameObject.transform.copy( this.lastTransform );
			isDirty  = true;
		}
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
	/*
	====================
	setSprite
	 set new sprite and dirty to true.
	====================
	*/
	public void setSprite( Sprite sprite ) {
		this.sprite = sprite;
		this.isDirty = true;
	}
	/*
	====================
	setColor
	 set new color and dirty to true.
	====================
	*/
	public void setColor( Vector4f color ) {
		if ( ! ( this.color.equals( color ) ) ) {
			this.color.set( color );
			this.isDirty = true;
		}
	}
	
	public boolean isDirty() {
		return this.isDirty;
	}
	
	public void setClean() {
		this.isDirty = false;
	}
}
