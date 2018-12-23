package org.atlasgl.coreapi.render;

import org.atlasgl.coreapi.utils.State;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.util.HashMap;

public class Display {

    private int width, height;
    private String title;
    private long window = 0;
    private Vector3f backgroundColor = new Vector3f(0.0f, 0.0f, 0.0f);
    private HashMap<Integer, State> keyStates = new HashMap<Integer, State>();

    public Display(int w, int h, String title) {

        this.width = w;
        this.height = h;
        this.title = title;

    }

    public boolean create() {

        if (!GLFW.glfwInit()) {

            System.err.println("\u001B[31m" + "[ERROR][CoreAPI][Display] Can't init GLFW");
            System.exit(0);
            return false;

        } else {

            System.out.println("\u001B[32m" + "[INFO][CoreAPI][Display] GLFW initialized !");

        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);


        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        if (window != 0) {

            System.out.println("\u001B[32m" + "[INFO][CoreAPI][Display] Window created !");

        } else {

            System.err.println("\u001B[31m" + "[ERROR][CoreAPI][Display] Window not created !");
            System.exit(0);
            return false;

        }

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        GLFW.glfwSetWindowPos(window, (videoMode.width() / 2 - width / 2), (videoMode.height() / 2 - height / 2));
        GLFW.glfwShowWindow(window);

        GLFW.glfwSetKeyCallback(window, (windowRef, key, scancode, action, mods) -> {

            State keyState = keyStates.get(key);

            if (keyState == null) {

                keyState = new State(key);
                keyStates.put(key, keyState);

            }

            boolean pressed = (action == GLFW.GLFW_PRESS);
            boolean down = (action == GLFW.GLFW_REPEAT);
            boolean released = (action == GLFW.GLFW_RELEASE);

            keyState.scancode = scancode;
            keyState.pressed = pressed;
            keyState.down = down | pressed;
            keyState.released = released;

        });

        return true;

    }

    public long getWindow() {

        return window;

    }

    public void setBackgroundColor(float r, float g, float b) {

        backgroundColor = new Vector3f(r, g, b);

    }

    public Vector3f getBackgroundColor() {

        return backgroundColor;

    }

    public HashMap<Integer, State> getKeyStates() {

        return keyStates;

    }

}
