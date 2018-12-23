package org.atlasgl.coreapi.shader;

public class BasicShader extends Shader {

    private static final String FILE = "basic";

    public BasicShader() {

        super(FILE, FILE);

    }

    @Override
    public void bindAllAttributes() {

        super.bindAttribute(0, "vertices");

    }

}
