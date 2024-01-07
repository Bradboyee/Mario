package com.brad.game.jade;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.brad.game.jade.component.SpriteRenderer;
import com.brad.game.jade.util.AssetPool;

public class LevelEditorScene extends Scene {

	public LevelEditorScene() {
	}

	@Override
	public void init() {
		this.camera = new Camera(new Vector2f(-250, 0));

		GameObject obj1 = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
		obj1.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/images/testImage.png")));
		this.addGameObjectToScene(obj1);

		GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
		obj2.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/images/testImage2.png")));
		this.addGameObjectToScene(obj2);

		loadResources();
		
		loadResources();
	}

	/*
	====================
	loadResource
	 create AssetPool for shader.
	====================
	*/
	private void loadResources() {
		AssetPool.getShader( "assets/shaders/default.glsl" );
	}

	@Override
	public void update( float dt ) {
//		System.out.printf( "\nFPS : %f", 1.0f/dt );
		
		for ( GameObject go : this.gameObjects ) {
			go.update( dt );
		}
		
		this.renderer.render();
	}
}
