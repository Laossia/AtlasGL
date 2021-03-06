package org.atlasgl.coreapi.render;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Texture {

	private int textureObject;
	private int width;
	private int height;

	public Texture(String filename) {

		try {

			if(getClass().getResource(filename) == null) {

				System.err.println("\u001B[31m[INFO][AtlasGL][Texture] Texture " + getClass().getResource(filename) + " not found !");
				return;

			}

			InputStream in = new FileInputStream(getClass().getResource(filename).getFile());
			
			PNGDecoder decoder = new PNGDecoder(in);

			ByteBuffer buf = ByteBuffer.allocateDirect(4*decoder.getWidth()*decoder.getHeight());
			decoder.decode(buf, decoder.getWidth()*4, Format.RGBA);

			buf.flip();

			textureObject = glGenTextures();

			glBindTexture(GL_TEXTURE_2D, textureObject);

			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);

		} catch (NullPointerException | IOException e) {

			if(e instanceof URISyntaxException) {

				System.err.println("\u001B[31m[INFO][AtlasGL][Texture] URI " + filename + " isnt valid !");
				e.printStackTrace();

			} else {

				System.err.println("\u001B[31m[INFO][AtlasGL][Texture] Texture " + filename + " not found !");
				e.printStackTrace();

			}

		}

	}

	public void Texture1(String filename) {

		BufferedImage bufferedImage;

		try {

			if(getClass().getResource(filename) == null) {

				System.err.println("\u001B[31m[INFO][AtlasGL][Texture] Texture " + getClass().getResource(filename) + " not found !");
				return;

			}

			URI file = getClass().getResource(filename).toURI();
			bufferedImage = ImageIO.read(new File(file));
			width = bufferedImage.getWidth();
			height = bufferedImage.getHeight();

			int[] pixels_raw = bufferedImage.getRGB(0, 0, width, height, null, 0, width);
			System.out.println(bufferedImage.getRGB(0, 0, width, height, null, 0, width).length);

			ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);

			for (int i = 0; i < width; i++) {

				for (int j = 0; j < height; j++) {

					// pixels_raw is 2304000
					int pixel = pixels_raw[i * width + j];
					pixels.put((byte) ((pixel >> 16) & 0xFF)); // RED
					pixels.put((byte) ((pixel >> 8) & 0xFF));  // GREEN
					pixels.put((byte) (pixel & 0xFF));		   // BLUE
					pixels.put((byte) ((pixel >> 24) & 0xFF)); // ALPHA

				}

			}

			pixels.flip();

			textureObject = glGenTextures();

			glBindTexture(GL_TEXTURE_2D, textureObject);

			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);

		} catch (NullPointerException | IOException | URISyntaxException e) {

			if(e instanceof URISyntaxException) {

				System.err.println("\u001B[31m[INFO][AtlasGL][Texture] URI " + filename + " isnt valid !");
				e.printStackTrace();

			} else {

				System.err.println("\u001B[31m[INFO][AtlasGL][Texture] Texture " + filename + " not found !");
				e.printStackTrace();

			}

		}

	}

	@Override
	protected void finalize() throws Throwable {

		glDeleteTextures(textureObject);
		super.finalize();

	}

	public void bind(int sampler) {

		if (sampler >= 0 && sampler <= 31) {

			glActiveTexture(GL_TEXTURE0 + sampler);
			glBindTexture(GL_TEXTURE_2D, textureObject);

		}

	}

	public int getTextureID() {

		return textureObject;

	}

}