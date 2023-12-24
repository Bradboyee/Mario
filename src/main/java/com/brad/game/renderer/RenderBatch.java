package com.brad.game.renderer;

import com.brad.game.jade.Window;
import com.brad.game.jade.component.SpriteRenderer;
import static org.lwjgl.opengl.GL30.*;

import org.joml.Vector4f;


public class RenderBatch {
	
	//	Vertex
	// 	==========================================
	// 	Position			Color
	// 	float, float		float, float, float, float
	// 	==========================================
	
	private final int 				POS_SIZE = 2;
	private final int 				COL_SIZE = 4;
	private final int 				VERTEX_SIZE = 6;
	
	private final int 				POS_OFFSET = 0;
	private final int 				COL_OFFSET = POS_OFFSET + POS_SIZE * Float.BYTES;
	private final int 				VERTEX_SIZE_BYTES = VERTEX_SIZE * Float.BYTES;

	private SpriteRenderer[] 		sprites;
	private int 					numSprites;
	private boolean 				hasRoom;
	private float[] 				verticies;

	private int 					vaoID; 
	private int 					vboID; 
	private int 					maxBatchSize;
	private Shader 					shader;
	
	public RenderBatch(int maxBatchSize) {
		this.shader 		= new Shader( "assets/shaders/default.glsl" );
		this.shader.compile();
		this.sprites 		= new SpriteRenderer[ maxBatchSize ];
		this.maxBatchSize 	= maxBatchSize;
		//	4 vertices quad
		this.verticies 		= new float[ maxBatchSize * VERTEX_SIZE * 4 ];
		this.numSprites 	= 0;
		this.hasRoom 		= true;
	}
	
	public void start() {
		vaoID = glGenVertexArrays();
		// 	Generate and bind a Vertex Array Object
		glBindVertexArray(vaoID);

		// 	Allocate space of vertices
		vboID = glGenBuffers();
		glBindBuffer( GL_ARRAY_BUFFER, vboID );
		glBufferData( GL_ARRAY_BUFFER, verticies.length * Float.BYTES, GL_DYNAMIC_DRAW );

		// 	Create and Upload indices buffer
		int eboID = glGenBuffers();
		int[] indices = generateIndices();
		glBindBuffer( GL_ELEMENT_ARRAY_BUFFER, eboID );
		glBufferData( GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW );

		// 	Enable Attribute pointer
		// 	Position
		glVertexAttribPointer( 0, POS_SIZE, GL_FLOAT, false, VERTEX_SIZE_BYTES, POS_OFFSET );
		glEnableVertexAttribArray (0 );
		// 	Color
		glVertexAttribPointer( 1, COL_SIZE, GL_FLOAT, false, VERTEX_SIZE_BYTES, COL_OFFSET );
		glEnableVertexAttribArray( 1 );
		
	}
	
	
	/*
	====================
	render
	 re buffer all data every frame
	====================
	*/
	public void render() {
		glBindBuffer( GL_ARRAY_BUFFER, vboID );
		glBufferSubData( GL_ARRAY_BUFFER, 0, verticies );
		
		//	use shader
		shader.use();
		shader.uploadMat4f("uProjection", Window.getScene().getCamera().getProjectionMatrix());
		shader.uploadMat4f("uView", Window.getScene().getCamera().getViewMatrix());
		
		glBindVertexArray( vaoID );
		glEnableVertexAttribArray( 0 );
		glEnableVertexAttribArray( 1 );
		
		glDrawElements(GL_TRIANGLES, this.numSprites * 6, GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray( 0 );
		glDisableVertexAttribArray( 1 );
		glBindVertexArray( 0 );
		
		shader.detach();
	}

	/*
	================
	generateIndices
	
	  generate indices for index buffers
	================
	*/
	private int[] generateIndices() {
		//	6 indices per quad (triangle : 3)
		int[] elements 		= new int[ maxBatchSize * 6 ];
		for (int i = 0; i < maxBatchSize; i++) {
			loadElementIndicies( elements, i );
		}
		return elements;
	}

	/*
	================
	loadElementIndicies
	
	  modify the element to match the index to indices
	================
	*/
	private void loadElementIndicies( int[] elements, int index ) {
		//	1 quad = 1 triangle = 6 ( index buffer )
		int offsetArrayIndex = 6 * index;
		//	1 quad use 4 index in vertex array
		int offset = 4 * index;
		//	3, 2, 0, 0, 2, 1	next	7, 6, 4, 4, 6, 5
		elements[ offsetArrayIndex + 0 ] = offset + 3;
		elements[ offsetArrayIndex + 1 ] = offset + 2;
		elements[ offsetArrayIndex + 2 ] = offset + 0;
		
		elements[ offsetArrayIndex + 3 ] = offset + 0;
		elements[ offsetArrayIndex + 4 ] = offset + 2;
		elements[ offsetArrayIndex + 5 ] = offset + 1;
	}
	
	/*
	================
	addSprite
	 add sprite and increase the number of sprite
	================
	*/
	public void addSprite(SpriteRenderer spr) {
		int index 				= this.numSprites;
		this.sprites[index] 	= spr;
		this.numSprites++;;
		
		//	add properties to local vertices;
		loadVertexProperties(index);
		
		if( numSprites >= this.maxBatchSize ) {
			this.hasRoom = false;
		}
	}
	
	/*
	================
	loadVertexProperties
	 load position and color to vertex array
	================
	*/
	private void loadVertexProperties( int index ) {
		SpriteRenderer sprite = this.sprites[ index ];
		
		//	Find index offset within array ( 4 vertices for per sprite )
		int offset = index * 4 * VERTEX_SIZE;
		
		Vector4f color = sprite.getColor();
		
		//	Add vertices with the appropriate properties
		float xAdd 	= 1.0f;
		float yAdd 	= 1.0f;
		
		for ( int i = 0; i < 4; i++ ) {
			if( i == 1 ) {
				yAdd = 0.0f;
			} else if ( i == 2 ) {
				xAdd = 0.0f;
			} else if ( i == 3 ) {
				yAdd = 1.0f;
			}
			
			//	load position to vertices
			verticies[ offset + 0 ] 	= sprite.gameObject.transform.position.x + ( xAdd * sprite.gameObject.transform.scale.x );
			verticies[ offset + 1 ] 	= sprite.gameObject.transform.position.y + ( yAdd * sprite.gameObject.transform.scale.y );
			// 	load color
			verticies[ offset + 2 ] 	= color.x;
			verticies[ offset + 3 ] 	= color.y;
			verticies[ offset + 4 ] 	= color.z;
			verticies[ offset + 5 ] 	= color.w;
			//	load next index
			offset += VERTEX_SIZE;
		}
	}
	
	/*
	================
	getHasRoom
	 hasRoom : have space
	================
	*/
	public boolean hasRoom() {
		return this.hasRoom;
	}
}