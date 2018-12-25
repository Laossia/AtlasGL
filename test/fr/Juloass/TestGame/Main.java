package fr.Juloass.TestGame;

import org.atlasgl.coreapi.Core;
import org.atlasgl.coreapi.inputs.Keyboard;
import org.atlasgl.coreapi.inputs.Mouse;
import org.atlasgl.coreapi.render.Image;
import org.atlasgl.coreapi.render.Texture;
import org.atlasgl.coreapi.render.models.Entity;
import org.atlasgl.coreapi.render.models.Model;
import org.atlasgl.coreapi.render.scene.Background;
import org.atlasgl.coreapi.render.scene.Scene;
import org.atlasgl.coreapi.shader.Shader;
import org.atlasgl.coreapi.shader.Shader3D;
import org.atlasgl.coreapi.utils.GameListener;
import org.atlasgl.coreapi.utils.annotations.InjectDefaultShader;
import org.atlasgl.coreapi.utils.annotations.InjectKeyboard;
import org.atlasgl.coreapi.utils.annotations.InjectMouse;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Main implements GameListener {

	@InjectKeyboard
	public Keyboard keyboard;
	@InjectMouse
	public Mouse mouse;
	@InjectDefaultShader
	public Shader defaultShader;

	public static void main(String[] args) {

		new Main();

	}

	private Scene mainMenu;
	private Background b;
	private Texture bt;
	
	private Texture texture; private Shader3D shader; private Model model;
	private Entity entity;

	public Main() {

		Core core = new Core(this, "Test Game", " v1.0.001b", 60, 30);

		bt = new Texture("/textures/background.png");
		b = new Background(bt, defaultShader);
		mainMenu = new Scene(0);
		mainMenu.setBackground(b);

		Image i = Image.loadImage("res/textures/bricks.png");
		core.getDisplay().setIcon(i);
		
		i = Image.loadImage("res/textures/cursors/Grey.png");
		core.getDisplay().setCursor(i);
		
		texture = new Texture("/textures/bricks.png");;
		shader = new Shader3D();
		model = new Model(

				new float[] {

						-0.5f, 0.5f, 0.0f, // TOP LEFT
						0.5f, 0.5f, 0.0f, // TOP RIGHT
						0.5f, -0.5f, 0.0f, // BOTTOM R
						-0.5f, -0.5f, 0.0f, // BOTTOM L

				}, new float[] {

						0, 0,
						1, 0,
						1, 1,
						0, 1

				},

				new int[] {

						0, 1, 2, 2, 3, 0

				});
		
		float time = (float) Math.cos(Math.toRadians(GLFW.glfwGetTime()*100));
		entity = new Entity(model, texture, shader, new Vector3f(time, 0f, 0f), new Vector3f(time, -time, 0f), new Vector3f(time, -time, 1.0f));

		core.start();

	}

	@Override
	public void update() {

		// Update something

	}

	@Override
	public void render() {

		// Draw some things
		
		mainMenu.render();
		
		entity.setScale(new Vector3f(1f, 1f, 1f));
		entity.setRotation(new Vector3f(0, 0, 0));
		entity.setPosition(new Vector3f(0, 0f, 0.4f));
		entity.render();

	}

	@Override
	public void end() {


	}

}
