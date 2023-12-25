package com.brad.game.jade;

import com.brad.game.jade.component.SpriteRenderer;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class LevelEditorScene extends Scene {

	public LevelEditorScene() {
	}

	@Override
	public void init() {
		camera = new Camera( new Vector2f() );
		
		int xOffset = 10;
		int yOffset = 10;

		float width = (float)(600 - xOffset * 2);
		float height = (float)(300 - yOffset * 2);
		float sizeX = width / 100.0f;
		float sizeY = height / 100.0f;
		
		for ( int y = 0; y < 100; y++ ) {
			for ( int x = 0; x < 100; x++ ) {
				float xPos = xOffset + ( x + sizeX );
				float yPos = yOffset + ( y + sizeY );
				GameObject go = new GameObject( String.format( "Game Obj %s, %s", x, y ), new Transform( new Vector2f( xPos, yPos ), new Vector2f(sizeX, sizeY) ) );
				go.addComponent( new SpriteRenderer( new Vector4f( xPos / width, yPos / height, 1, 1 ) ) );
				this.addGameObjectToScene( go );
			}
		}
	}

	@Override
	public void update( float dt ) {
		System.out.printf( "\nFPS : %f", 1.0f/dt );
		
		for ( GameObject go : this.gameObjects ) {
			go.update( dt );
		}
		
		this.renderer.render();
	}
}
