package org.atlasgl.coreapi.render.scene;

import org.atlasgl.coreapi.render.Texture;
import org.atlasgl.coreapi.render.models.Model;
import org.atlasgl.coreapi.shader.Shader;

public class Background implements Drawable {

	private Texture texture;
	private Shader shader;
	private Model model;

	public Background(Texture t, Shader s) {
		
		texture = t;
		shader = s;
		model = new Model(

				new float[] {

						-1f, 1f, 0f, // TOP LEFT
						1f, 1f, 0f, // TOP RIGHT
						1f, -1f, 0f, // BOTTOM R
						-1f, -1f, 0f, // BOTTOM L

				}, new float[] {

						0, 0,
						1, 0,
						1, 1,
						0, 1

				},

				new int[] {

						0, 1, 2, 2, 3, 0

				});


	}

	@Override
	public Shader getShader() {
		
		return shader;
		
	}

	@Override
	public Texture getTexture() {
		
		return texture;
		
	}

	@Override
	public Model getModel() {
		
		return model;
		
	}

}
