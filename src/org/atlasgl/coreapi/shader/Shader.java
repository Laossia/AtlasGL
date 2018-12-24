package org.atlasgl.coreapi.shader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Shader {

    private int vertexShaderID, fragmentShaderID, programID;

    private String fragmentFile, vertexFile;

    public Shader(String vertexFile, String fragmentFile) {

        this.vertexFile = vertexFile + ".vs";
        this.fragmentFile = fragmentFile + ".fs";

    }

    public void create() {

        programID = GL20.glCreateProgram();

        vertexShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexShaderID, readFile(vertexFile));
        GL20.glCompileShader(vertexShaderID);

        if (GL20.glGetShaderi(vertexShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {

            System.err.println("\u001B[31m" + "[ERROR][AtlasGL][Shader] Vertex Shader - " + GL20.glGetShaderInfoLog(vertexShaderID));

        }

        fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShaderID, readFile(fragmentFile));
        GL20.glCompileShader(fragmentShaderID);

        if (GL20.glGetShaderi(fragmentShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {

            System.err.println("\u001B[31m" + "[ERROR][AtlasGL][Shader] FragmentVertex Shader - " + GL20.glGetShaderInfoLog(fragmentShaderID));

        }

        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);

        GL20.glLinkProgram(programID);

        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {

            System.err.println("\u001B[31m" + "[ERROR][AtlasGL][Shader] Program Linking - " + GL20.glGetProgramInfoLog(programID));

        }

        GL20.glValidateProgram(programID);

        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {

            System.err.println("\u001B[31m" + "[ERROR][AtlasGL][Shader] Program Validating - " + GL20.glGetProgramInfoLog(programID));

        }


    }

    public abstract void bindAllAttributes();

    public void bindAttribute(int index, String location) {

        GL20.glBindAttribLocation(programID, index, location);

    }

    public void bind() {

        GL20.glUseProgram(programID);

    }

    public void remove() {

        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);

    }

    private String readFile(String file) {

        BufferedReader reader = null;
        StringBuilder string = new StringBuilder();

        try {

            reader = new BufferedReader(new FileReader("res/shaders/" + file));
            String line;
            while ((line = reader.readLine()) != null) {

                string.append(line).append("\n");

            }

        } catch (IOException e) {

            System.err.println("[ERROR][AtlasGL][Shader] Couldn't find file !");

        }

        return string.toString();

    }

}
