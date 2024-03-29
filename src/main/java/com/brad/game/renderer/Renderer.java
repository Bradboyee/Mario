package com.brad.game.renderer;


import com.brad.game.jade.GameObject;
import com.brad.game.jade.component.SpriteRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1000;
    private List<RenderBatch> batches;

    public Renderer() {
        this.batches = new ArrayList<>();
    }

    public void add(GameObject go) {
        SpriteRenderer spr = go.getComponent(SpriteRenderer.class);
        if (spr != null) {
            add(spr);
        }
    }

    private void add(SpriteRenderer sprite) {
        boolean added = false;
        for (RenderBatch batch : batches) {
        	// add when has room and same z index.
            if (batch.hasRoom() && batch.zIndex() == sprite.gameObject.zIndex() ) {
            	Texture texture = sprite.getTexture();
            	if( texture == null || batch.hasTexture( texture ) || batch.hasTextureRoom() ) {
					batch.addSprite( sprite );
					added = true;
					break;
            	}
            }
        }

        if (!added) {
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.gameObject.zIndex() );
            newBatch.start();
            batches.add(newBatch);
            newBatch.addSprite(sprite);
            
            //every time when create new batch sort z index.
            Collections.sort( batches );
        }
    }

    public void render() {
        for (RenderBatch batch : batches) {
            batch.render();
        }
    }
}
