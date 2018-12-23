package org.atlasgl.coreapi.utils;

public class State {

    public int scancode, key;
    public boolean pressed, down, released;

    public State(int key) {

        this.key = key;

    }


}
