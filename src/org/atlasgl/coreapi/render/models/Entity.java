package org.atlasgl.coreapi.render.models;

import org.atlasgl.coreapi.render.Texture;
import org.atlasgl.coreapi.render.scene.Drawable;
import org.atlasgl.coreapi.shader.Shader;
import org.atlasgl.coreapi.shader.Shader3D;
import org.atlasgl.coreapi.utils.maths.MatrixMaths;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Entity implements Drawable {
	
	private Texture texture;
	private Vector3f position, rotation, scale;
	private Model model;
	private Shader3D shader;
	
	public Entity(Model model, Texture texture, Shader3D shader, Vector3f position, Vector3f rotation, Vector3f scale) {
		
		this.model = model;
		this.texture = texture;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.shader = shader;
		
	}
	
	public Matrix4f getTransformationMatrix() {
		
		return MatrixMaths.createTransformationMatrix(position, rotation, scale);
		
	}
	
	@Override
	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		
		this.model = model;
		
	}
	
	@Override
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	@Override
	public void render() {
		
		getTexture().bind(0);
		getShader().bind();
		getModel().render();
		
	}

	@Override
	public Shader getShader() {
		
		shader.setUniform("transformation", getTransformationMatrix());
		return shader;
		
	}
	
}