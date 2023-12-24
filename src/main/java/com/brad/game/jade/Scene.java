package com.brad.game.jade;

import java.util.ArrayList;
import java.util.List;

import com.brad.game.renderer.Renderer;

public abstract class Scene {

	protected Renderer renderer = new Renderer();
	protected Camera camera;
	protected List<GameObject> gameObjects = new ArrayList<>();
	private boolean isRunning = false;

	public Scene() {
	}

	public abstract void update( float dt );

	public void init() {
	}

	public void start() {
		for ( GameObject go : gameObjects ) {
			go.start();
			this.renderer.add( go );
		}
		isRunning = true;
	}

	/*
	====================
	addGameObjectToScene
	 handle adding game object while game is running.
	====================
	*/
	public void addGameObjectToScene( GameObject go ) {
		if ( !isRunning ) {
			gameObjects.add( go );
		} else {
			gameObjects.add( go );
			go.start();
			this.renderer.add( go );
		}
	}

	public Camera getCamera() {
		return this.camera;
	}
}
