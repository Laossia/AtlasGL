package fr.Juloass.TestGame;

import org.atlasgl.coreapi.Core;
import org.atlasgl.coreapi.inputs.Keyboard;
import org.atlasgl.coreapi.inputs.Mouse;
import org.atlasgl.coreapi.render.Image;
import org.atlasgl.coreapi.render.Texture;
import org.atlasgl.coreapi.render.scene.Background;
import org.atlasgl.coreapi.render.scene.Scene;
import org.atlasgl.coreapi.shader.Shader;
import org.atlasgl.coreapi.utils.GameListener;
import org.atlasgl.coreapi.utils.annotations.InjectDefaultShader;
import org.atlasgl.coreapi.utils.annotations.InjectKeyboard;
import org.atlasgl.coreapi.utils.annotations.InjectMouse;

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
	
	public Main() {

		Core core = new Core(this, "Test Game", " v1.0.001b", 60, 30);
		
		bt = new Texture("/textures/background.png");
		b = new Background(bt, defaultShader);
		mainMenu = new Scene(0);
		mainMenu.setBackground(b);
		
		Image i = Image.loadImage("res/textures/bricks.png");
		
		core.getDisplay().setCursor(i);
		core.getDisplay().setIcon(i);
		
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

	}

	@Override
	public void end() {
		

	}

}
