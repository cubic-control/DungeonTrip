package com.fuller.DungeonTrip;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {
	protected long window;
	protected int width, height;
	protected String title;
	protected boolean fullscreen;
	protected Input input;
	
	public static void setCallbacks()
	{
		GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
	}
	
	public Window(int width, int height, String title)
	{
		setSize(width, height);
		setFullscreen(false);
		this.title = title;
	}
	
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void init()
	{
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		
		window = GLFW.glfwCreateWindow(width, height, title, fullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);
		
		if(window == 0)
		{
			GLFW.glfwTerminate();
			throw new IllegalStateException("Failed to create window");
		}
		if(!fullscreen)
		{
			GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
			GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
		}
		
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		//GL11.glClearColor(1.0f, 1.0f, 0.0f, 1.0f);
        //GL11.glEnable(GL11.GL_DEPTH_TEST);
        System.out.println("GLFW Version: " + GLFW.glfwGetVersionString());
        System.out.println("OpenGL Version: " + GL11.glGetString(GL11.GL_VERSION));
		
		GLFW.glfwShowWindow(window);
		
		input = new Input(window);
	}
	
	public void close()
	{
		GLFW.glfwSetWindowShouldClose(window, true);
	}
	
	public void destroy()
	{
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
	}
	
	public boolean shouldClose()
	{
		return GLFW.glfwWindowShouldClose(window);
	}
	
	public void update()
	{
		input.update();
		GLFW.glfwPollEvents();
	}

	public long getWindow() {return window;}

	public int getWidth() {return width;}

	public int getHeight() {return height;}

	public String getTitle() {return title;}
	
	public boolean getFullScreen() {return fullscreen;}
	
	public void setFullscreen(boolean full) {fullscreen = full;}
	
	public Input getInput() {return input;}
	
}