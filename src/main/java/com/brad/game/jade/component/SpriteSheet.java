package com.brad.game.jade.component;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.brad.game.renderer.Texture;

public class SpriteSheet {
	private Texture texture;
	private List<Sprite> sprites;
	
	public SpriteSheet( Texture texture, int spriteWidth, int spriteHeight, int spriteNumbers, int spacing ) {
		this.texture = texture;
		this.sprites = new ArrayList<>();
		int currentX = 0;
		int currentY = texture.getHeight() - spriteHeight;
		//	the number is 0 - 1
		// y 0 start from the bottom. ?
		for ( int i = 0; i < spriteNumbers; i++ ) {
			float leftX = currentX / ( float ) texture.getWidth();
			float rightX = ( currentX + spriteWidth ) / ( float ) texture.getWidth();
			float topY = ( currentY + spriteHeight ) / ( float ) texture.getHeight();
			float bottomY = currentY / ( float ) texture.getHeight();
			
			Vector2f[] texCoords = new Vector2f[] {
				new Vector2f( rightX, topY ), 
				new Vector2f( rightX, bottomY ), 
				new Vector2f( leftX, bottomY ),
				new Vector2f( leftX, topY ), 	
			};
			
			Sprite sprite = new Sprite( this.texture, texCoords );
			this.sprites.add( sprite );
			
			currentX += spriteWidth + spacing;
			if ( currentX >= texture.getWidth() ) {
				currentX = 0;
				currentY -= spriteHeight + spacing;
			}
		}
	}
	
	public Sprite getSprite(int index) {
		return this.sprites.get( index );
	}
}
