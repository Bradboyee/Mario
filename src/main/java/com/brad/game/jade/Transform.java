package com.brad.game.jade;

import org.joml.Vector2f;

public class Transform {

	public Vector2f position;
	public Vector2f scale;

	public Transform() {
		init( new Vector2f(), new Vector2f() );
	}

	public Transform( Vector2f position ) {
		init( position, new Vector2f() );
	}

	public Transform( Vector2f position, Vector2f scale ) {
		init( position, scale );
	}

	public void init( Vector2f position, Vector2f scale ) {
		this.position = position;
		this.scale = scale;
	}
	/*
	====================
	copy
	 create new copy Transform Object for lastTransform reference.
	====================
	*/
	public Transform copy() {
		return new Transform( new Vector2f( this.position ), new Vector2f( this.scale ) );
	}
	/*
	====================
	copy
	 the object that use this method will be modified by the object that are passed.
	====================
	*/
	public void copy( Transform t ) {
		t.position.set(this.position);
		t.scale.set( this.scale);
	}
	
	/*
	====================
	equals
	 override equals method passing object check position and scale is the same.
	====================
	*/
	@Override
	public boolean equals(Object o) {
		if ( o == null ) return false;
		if ( ! ( o instanceof Transform ) ) return false;
		Transform t = ( Transform ) o;
		return t.position.equals( this.position ) && t.scale.equals( this.scale );
	}
}
