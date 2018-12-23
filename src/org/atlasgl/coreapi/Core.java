package org.atlasgl.coreapi;

import java.lang.reflect.Field;

import org.atlasgl.coreapi.inputs.Keyboard;
import org.atlasgl.coreapi.inputs.Mouse;
import org.atlasgl.coreapi.render.Display;
import org.atlasgl.coreapi.render.Renderer;
import org.atlasgl.coreapi.utils.GameListener;
import org.atlasgl.coreapi.utils.GameLoop;
import org.atlasgl.coreapi.utils.ModuleManager;
import org.atlasgl.coreapi.utils.annotations.InjectKeyboard;
import org.atlasgl.coreapi.utils.annotations.InjectMouse;
import org.atlasgl.coreapi.utils.annotations.InjectRenderer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Core {

    private String name, version;
    private int fps, ups;

    private ModuleManager mm;
    private GameLoop gameloop;
    private Display display;

    private Keyboard keyboard;
    private Mouse mouse;
    private Renderer renderer;

    private GameListener updateListener;
    private GameListener renderListener;

    private Class<? extends GameListener> game;

    public Core(GameListener gamelistener, String n, String v, int fps, int ups) {

        this.name = n;
        this.version = v;
        this.fps = fps;
        this.ups = ups;

        this.setRenderListener(gamelistener);
        this.setUpdateListener(gamelistener);
        game = gamelistener.getClass();

        display = new Display(1280, 720, name + " | " + version);
        gameloop = new GameLoop(this, this.ups, this.fps, true, display);
        mm = new ModuleManager();

        display.create();

        keyboard = new Keyboard(display);
        mouse = new Mouse(display);
        renderer = new Renderer();

        initializeChildVariables(gamelistener);

    }

    private void initializeChildVariables(GameListener gamelistener) {

        for (Field field : game.getDeclaredFields()) {

            if (field.isAnnotationPresent(InjectKeyboard.class)) {

                try {

                    field.set(gamelistener, keyboard);

                } catch (IllegalArgumentException | IllegalAccessException e) {

                    e.printStackTrace();

                }

            } else if (field.isAnnotationPresent(InjectMouse.class)) {

                try {

                    field.set(gamelistener, mouse);

                } catch (IllegalArgumentException | IllegalAccessException e) {

                    e.printStackTrace();

                }

            } else if (field.isAnnotationPresent(InjectRenderer.class)) {

                try {

                    field.set(gamelistener, renderer);

                } catch (IllegalArgumentException | IllegalAccessException e) {

                    e.printStackTrace();

                }

            }

        }

    }

    public void start() {

        mm.start();
        gameloop.start();

    }

    public void stop() {

        GLFW.glfwTerminate();
        gameloop.stop();
        mm.stop();

    }

    public void pollEvents() {

        GLFW.glfwPollEvents();

    }

    public void update() {

        // Updating things ...
        updateListener.update();

        // Must be updated last
        keyboard.update();
        mouse.update();

    }

    public void render() {

        GL11.glClearColor(display.getBackgroundColor().x, display.getBackgroundColor().y, display.getBackgroundColor().z, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        renderListener.render();
        // Rendering things

    }

    public void setUpdateListener(GameListener listener) {

        this.updateListener = listener;

    }

    public void setRenderListener(GameListener listener) {

        this.renderListener = listener;

    }

}
