package fr.Juloass.TestGame;

import org.atlasgl.coreapi.Core;
import org.atlasgl.coreapi.inputs.Keyboard;
import org.atlasgl.coreapi.inputs.Mouse;
import org.atlasgl.coreapi.render.Model;
import org.atlasgl.coreapi.render.Renderer;
import org.atlasgl.coreapi.shader.BasicShader;
import org.atlasgl.coreapi.utils.GameListener;
import org.atlasgl.coreapi.utils.annotations.InjectKeyboard;
import org.atlasgl.coreapi.utils.annotations.InjectMouse;
import org.atlasgl.coreapi.utils.annotations.InjectRenderer;

public class Main implements GameListener {

	private Model model;
	private BasicShader basicshader;

	@InjectKeyboard
	public Keyboard keyboard;
	@InjectMouse
	public Mouse mouse;
	@InjectRenderer
	public Renderer renderer;

	public static void main(String[] args) {

		new Main();

	}

	public Main() {

		Core core = new Core(this, "Test Game", " v1.0.001b", 1080, 720);

		model = new Model(new float[] {

				-0.5f, 0.5f, 0.0f, // TOP LEFT
				0.5f, 0.5f, 0.0f, // TOP RIGHT
				-0.5f, -0.5f, 0.0f, // BOTTOM LEFT
				0.5f, -0.5f, 0.0f, // BOTTOM RIGHT

		},

				new int[] {

						0, 1, 2, 2, 1, 3

				});

		basicshader = new BasicShader();
		basicshader.create();
		model.create();
		core.start();

	}

	@Override
	public void update() {

		// Update something

	}

	@Override
	public void render() {

		// Draw some things

		basicshader.bind();
		renderer.render(model);

	}

}
