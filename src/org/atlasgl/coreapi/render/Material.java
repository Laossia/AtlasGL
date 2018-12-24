package org.atlasgl.coreapi.render;

import org.lwjgl.opengl.GL15;

public class Material {
	
	private int textureID;
	
	public Material(Texture texture) {
		
		textureID = texture.getTextureID();
		
	}
	
	public int getTextureID() {
		
		return textureID;
		
	}
	
	public void remove() {
		
		GL15.glDeleteTextures(textureID);
		
	}

}
