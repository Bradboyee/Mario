package com.brad.game.jade.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.brad.game.jade.component.SpriteSheet;
import com.brad.game.renderer.Shader;
import com.brad.game.renderer.Texture;

public class AssetPool {
	private static Map<String, Shader> shader = new HashMap<>();
	private static Map<String, Texture> textures = new HashMap<>();
	private static Map<String, SpriteSheet> spritesSheets = new HashMap<>();
	
	/*
	====================
	getShader
	====================
	*/
	public static Shader getShader( String resourceName ) {
		File file 	= new File( resourceName );
		
		if ( shader.containsKey( file.getAbsolutePath() ) ) {
			return shader.get( file.getAbsolutePath() );
		} else {
			Shader shader = new Shader( resourceName );
			shader.compile();
			AssetPool.shader.put( file.getAbsolutePath() , shader);
			return shader;
		}
	}
	
	/*
	====================
	getTexture
	====================
	*/
	public static Texture getTexture( String resourceName ) {
		File file 	= new File( resourceName );
		
		if ( textures.containsKey( file.getAbsolutePath() ) ) {
			return textures.get( file.getAbsolutePath() );
		} else {
			Texture texture = new Texture( resourceName );
			AssetPool.textures.put( file.getAbsolutePath() , texture);
			return texture;
		}
	}
	
	public static void addSpriteSheet( String resource, SpriteSheet spriteSheet ) {
		File file = new File( resource );
		
		if ( !spritesSheets.containsKey( file.getAbsolutePath() ) ) {
			AssetPool.spritesSheets.put( file.getAbsolutePath(), spriteSheet );
		}
	}
	
	public static SpriteSheet getSpriteSheet(String resourceName ) {
		File file = new File( resourceName );
		if ( !spritesSheets.containsKey( file.getAbsolutePath() ) ) {
			assert false : "Error : Tried to access spritesheets : " + resourceName + "and it has not been added to asset pool.";
		}
		return AssetPool.spritesSheets.getOrDefault( file.getAbsolutePath(), null );
	}
}
