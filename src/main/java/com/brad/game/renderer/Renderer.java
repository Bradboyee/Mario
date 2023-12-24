package com.brad.game.renderer;

import java.util.ArrayList;
import java.util.List;

import com.brad.game.jade.GameObject;
import com.brad.game.jade.component.SpriteRenderer;

public class Renderer {
	private final int MAX_BATCH_SIZE = 1000;
	private List<RenderBatch> batchs;
	
	public Renderer() {
		this.batchs = new ArrayList<>();
	}
	
	/*
	====================
	add
	 find the SpriteRenderer in the gameObject and add call addSpriteRenderer function.
	====================
	*/
	public void add( GameObject go ) {
		SpriteRenderer spr = go.getComponent( SpriteRenderer.class );
		if ( spr != null ) {
			addSpriteRenderer( spr );
		}
	}

	/*
	====================
	addSpriteRenderer
	 add spriteRenderer to batches 
	 if has space add it but if no space just create new batch and add it.
	====================
	 */
	private void addSpriteRenderer( SpriteRenderer spr ) {
		boolean added = false;
		for ( RenderBatch b : this.batchs ) {
			if ( b.hasRoom() ) {
				b.addSprite( spr );
				added = true;
				break;
			}
		}
			
		if ( !added ) {
			RenderBatch newBatch = new RenderBatch( MAX_BATCH_SIZE );
			newBatch.start();
			this.batchs.add( newBatch );
			newBatch.addSprite( spr );
		}
	}
	
	/*
	====================
	render
	 render the list of batch.
	====================
	 */
	public void render() {
		for ( RenderBatch batch : batchs ) {
			batch.render();
		}
	}
}
