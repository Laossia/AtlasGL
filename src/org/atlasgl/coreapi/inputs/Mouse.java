package org.atlasgl.coreapi.inputs;

import org.atlasgl.coreapi.render.Display;
import org.lwjgl.glfw.GLFW;

public class Mouse {

    public static final int
            BUTTON_1 = 0,
            BUTTON_2 = 1,
            BUTTON_3 = 2,
            BUTTON_4 = 3,
            BUTTON_5 = 4,
            BUTTON_6 = 5,
            BUTTON_7 = 6,
            BUTTON_8 = 7,
            BUTTON_LAST = BUTTON_8,
            BUTTON_LEFT = BUTTON_1,
            BUTTON_RIGHT = BUTTON_2,
            BUTTON_MIDDLE = BUTTON_3;

    private Display display;
    private boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    public Mouse(Display dis) {

        this.display = dis;

    }

    public void update() {

        for (int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) buttons[i] = isMouseDown(i);

    }

    public boolean isMouseDown(int mouseButton) {

        return GLFW.glfwGetMouseButton(display.getWindow(), mouseButton) == 1;

    }

    public boolean isMousePressed(int mouseButton) {

        return isMouseDown(mouseButton) && !buttons[mouseButton];

    }

    public boolean isMouseReleased(int mouseButton) {

        return !isMouseDown(mouseButton) && buttons[mouseButton];

    }

}
