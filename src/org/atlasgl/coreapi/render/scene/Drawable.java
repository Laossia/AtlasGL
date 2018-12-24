package org.atlasgl.coreapi.render.scene;

import org.atlasgl.coreapi.render.Texture;
import org.atlasgl.coreapi.render.models.Model;
import org.atlasgl.coreapi.shader.Shader;

public interface Drawable {
	
	public Shader getShader();
	public Texture getTexture();
	public Model getModel();
	
	default public void render() {
		
		if(getShader() != null) getShader().bind();
		if(getTexture() != null) getTexture().bind(0);
		if(getModel() != null) getModel().render();
		
	}

}
