package org.atlasgl.coreapi.utils;

import org.atlasgl.coreapi.Core;
import org.atlasgl.coreapi.render.Display;
import org.lwjgl.glfw.GLFW;

public class GameLoop {

    private final boolean RENDER_TIME;
    private final double UPS;
    private final double FPS;
    private boolean running;
    private Core core;
    private Display display;

    public GameLoop(Core inst, int ups, int fps, boolean rendertime, Display dis) {

        core = inst;
        this.UPS = ups;
        this.FPS = fps;
        this.RENDER_TIME = rendertime;
        this.display = dis;

    }

    public void start() {

        running = true;

        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (running && !GLFW.glfwWindowShouldClose(display.getWindow())) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {

                pollEvents();
                update();
                ticks++;
                deltaU--;

            }

            if (deltaF >= 1) {

                render();
                frames++;
                deltaF--;

            }

            if (System.currentTimeMillis() - timer > 1000) {

                if (RENDER_TIME) {

                    System.out.println("\u001B[33m[INFO][CoreAPI][GameLoop] UPS : " + ticks + " | FPS : " + frames);

                }

                frames = 0;
                ticks = 0;
                timer += 1000;

            }

        }

    }

    private void pollEvents() {

        core.pollEvents();

    }

    private void update() {

        core.update();

    }

    private void render() {

        GLFW.glfwSwapBuffers(display.getWindow());
        core.render();

    }

    public void stop() {

        running = false;

    }

}
