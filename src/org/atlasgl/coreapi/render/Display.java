package org.atlasgl.coreapi.render;

import java.util.HashMap;

import org.atlasgl.coreapi.utils.State;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Display {

	private int width, height;
    private String title;
    private long window = 0;
    private Vector3f backgroundColor = new Vector3f(0.0f, 0.0f, 0.0f);
    private HashMap<Integer, State> keyStates = new HashMap<Integer, State>();
    
    private GLFWImage cursorBuffer;
    private GLFWImage.Buffer iconBuffer;

    public Display(int w, int h, String title) {

        this.width = w;
        this.height = h;
        this.title = title;
        cursorBuffer = null;
        iconBuffer = null;

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
        
        if(cursorBuffer != null) {
        	
        	long cursor = GLFW.glfwCreateCursor(cursorBuffer, 0, 0);
        	GLFW.glfwSetCursor(window, cursor);
        	
        }
        
        if(iconBuffer != null) {
        	
        	GLFW.glfwSetWindowIcon(window, iconBuffer);
        	
        }

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
    
    public void setCursor(org.atlasgl.coreapi.render.Image i) {
		
    	GLFWImage iconImage = GLFWImage.malloc(); 
    	iconBuffer = GLFWImage.malloc(1);
    	
    	iconImage.set(i.getWidth(), i.getHeight(), i.getImage());
    	iconBuffer.put(0, iconImage);
    	
    	updateSettings();
    	
	}


	public void setIcon(org.atlasgl.coreapi.render.Image i) {
		
		cursorBuffer = GLFWImage.malloc();   	
    	cursorBuffer.set(i.getWidth(), i.getHeight(), i.getImage());
		
    	updateSettings();
    	
	}
	
	public void updateSettings() {
		
		if(cursorBuffer != null) {
        	
        	long cursor = GLFW.glfwCreateCursor(cursorBuffer, 0, 0);
        	GLFW.glfwSetCursor(window, cursor);
        	
        }
        
        if(iconBuffer != null) {
        	
        	GLFW.glfwSetWindowIcon(window, iconBuffer);
        	
        }
		
	}

}
