package org.atlasgl.coreapi.loaders;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	/**
	 * Loads an image (BufferedImage), can be any widely used image extension.
	 * If you'd like to use a regular image, please see {@link #loadImage()}
	 * 
	 * TODO: 
	 * 		- Caching system, load the image to memory so it doesn't need to be loaded every time.
	 * 		- Make sure the extension can be supported and used by the image
	 * 
	 * @param path - path to the image (this/is/an/examplepath/image.png)
	 * @return - null if all else fails
	 */
	public static BufferedImage loadBufferedImage(String path) {
		
		try {
			
			System.out.println(String.format("\u001B[33m" + "[INFO][AtlasGL][ImageLoader] Loading buffered image %s succeded!", path));
			return ImageIO.read(ImageLoader.class.getResource(path));
			
		} catch (IOException e) {
			
			System.err.println(String.format("\u001B[31m" + "[ERROR][AtlasGL][ImageLoader] Loading buffered image %s failed !", path));
			e.printStackTrace();
			System.exit(1);
			
		}
		
		return null;
		
	}

	/**
	 * Loads an image, can be any widely used image extension. 
	 * If you'd like to use a buffered image, please see {@link #loadBufferedImage()}
	 * 
	 * TODO: 
	 * 		- Caching system, load the image to memory so it doesn't need to be loaded every time.
	 * 		- Make sure the extension can be supported and used by the image
	 * 
	 * @param path - path to the image (this/is/an/examplepath/image.png)
	 * @return - null if all else fails
	 */
	public static Image loadImage(String path) {
		
		try {
			
			System.out.println(String.format("\u001B[33m" + "[INFO][AtlasGL][ImageLoader] Loading image %s succeded!", path));
			return ImageIO.read(ImageLoader.class.getResource(path));
			
		} catch (IOException e) {
			
			System.err.println(String.format("\u001B[31m" + "[ERROR][AtlasGL][ImageLoader] Loading image %s failed!", path));
			e.printStackTrace();
			System.exit(1);
			
		}
		
		return null;
		
	}

}
