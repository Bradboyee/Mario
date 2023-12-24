package com.brad.game.jade;

public abstract class Component {

	public GameObject gameObject;

	public abstract void update(float dt);

	public void start() {
	}
}
