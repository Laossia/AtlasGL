package org.atlasgl.coreapi.render;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public class Image {
	
	private ByteBuffer image;
	private int width, height;
	
	Image(int w, int h, ByteBuffer im) {
		
		this.image = im;
		this.width = w;
		this.height = h;
		
	}
	
	public static Image loadImage(String path) {
		
		ByteBuffer image;
		int width, height;
		try(MemoryStack stack = MemoryStack.stackPush()) {
			
			IntBuffer comp = stack.mallocInt(1);
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			
			image = STBImage.stbi_load(path, w, h, comp, 4);
			if(image == null) {
				
				System.err.println("\u001B" + "[31m[ERROR][AtlasGL][Image] Could not load Image : " + path);
				
			}
			
			width = w.get();
			height = h.get();	
			
		} 	
		
		return new Image(width, height, image);
		
	}

	public int getWidth() {
		
		return width;
		
	}
	
	public int getHeight() {
		
		return height;
		
	}

	public ByteBuffer getImage() {
		
		return image;
		
	}
	

}
