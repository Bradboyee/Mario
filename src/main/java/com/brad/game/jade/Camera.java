package com.brad.game.jade;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
	private Matrix4f projectionMatrix, viewMatrix;
	private Vector2f position;

	public Camera(Vector2f position) {
		this.position = position;
		projectionMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
		adjustProjection();
	}

	// when the window is change
	public void adjustProjection() {
		projectionMatrix.identity();
		projectionMatrix.ortho(0.0f, 32.0f * 40f, 0.0f, 32.0f * 21.0f, 0, 100.0f);
	}

	public Matrix4f getViewMatrix() {
		Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
		Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
		this.viewMatrix.identity();
		this.viewMatrix = viewMatrix.lookAt(new Vector3f(this.position.x, this.position.y, 20.0f),
				cameraFront.add(this.position.x, this.position.y, 0.0f), cameraUp);
		return this.viewMatrix;
	}

	public Matrix4f getProjectionMatrix() {
		return this.projectionMatrix;
	}
}
