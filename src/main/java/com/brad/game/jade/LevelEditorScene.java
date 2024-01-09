package com.brad.game.jade;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.brad.game.jade.component.Sprite;
import com.brad.game.jade.component.SpriteRenderer;
import com.brad.game.jade.component.SpriteSheet;
import com.brad.game.jade.util.AssetPool;

public class LevelEditorScene extends Scene {

	private GameObject obj1;
	private SpriteSheet spriteSheet;
	public LevelEditorScene() {
	}

	@Override
	public void init() {
		loadResources();
		
		spriteSheet = AssetPool.getSpriteSheet( "assets/images/spritesheet.png" );
		
		this.camera = new Camera(new Vector2f(-250, 0));

		obj1 = new GameObject( "Object 1", new Transform( new Vector2f( 100, 100 ), new Vector2f( 256, 256 ) ) );
		obj1.addComponent( new SpriteRenderer( spriteSheet.getSprite( 0 )  ) );
		this.addGameObjectToScene( obj1 );

		GameObject obj2 = new GameObject( "Object 2", new Transform( new Vector2f( 400, 100 ), new Vector2f( 256, 256 ) ) );
		obj2.addComponent( new SpriteRenderer( spriteSheet.getSprite( 10 )  ) );
		this.addGameObjectToScene( obj2 );

	}

	/*
	====================
	loadResource
	 create AssetPool for shader.
	====================
	*/
	private void loadResources() {
		AssetPool.getShader( "assets/shaders/default.glsl" );
		
		AssetPool.addSpriteSheet( 
				"assets/images/spritesheet.png", 
				new SpriteSheet( AssetPool.getTexture( "assets/images/spritesheet.png" ), 16, 16, 26, 0 ) );
	}

	private int spriteIndex = 0;
	private float spriteFlipTime = 0.2f;
	private float spriteFlipTimeLeft = 0.0f;
	@Override
	public void update( float dt ) {
		System.out.println( dt );
		spriteFlipTimeLeft -= dt;
		if ( spriteFlipTimeLeft <= 0 ) {
			spriteFlipTimeLeft = spriteFlipTime;
			spriteIndex++;
			if (spriteIndex > 4 ) {
				spriteIndex = 0;
			}
			obj1.getComponent( SpriteRenderer.class ).setSprite( spriteSheet.getSprite( spriteIndex ) );
		}
		
		obj1.transform.position.x += 10* dt;
//		System.out.printf( "\nFPS : %f", 1.0f/dt );
		
		for ( GameObject go : this.gameObjects ) {
			go.update( dt );
		}
		
		this.renderer.render();
	}
}
