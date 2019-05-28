package com.fuller.DungeonTrip;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.fuller.DungeonTrip.io.Input;

public class Window {
	protected long window;
	protected int width, height;
	protected String title;
	protected boolean fullscreen;
	protected boolean hasResized;
	private GLFWWindowSizeCallback windowSizeCallback;
	protected Input input;
	
	private static Window instance;
	
	public static void setCallbacks()
	{
		GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
	}
	
	private void setLocalCallbacks()
	{
		windowSizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int newWidth, int newHeight) {
				width = newWidth;
				height = newHeight;
				hasResized = true;
			}
		};
		GLFW.glfwSetWindowSizeCallback(window, windowSizeCallback);
	}
	
	public Window(int width, int height, String title)
	{
		setSize(width, height);
		setFullscreen(false);
		this.title = title;
		hasResized = false;
	}
	
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void init()
	{
		if(Refs.debug)
		{
			System.out.println("Window Init Start.");
		}
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
		setLocalCallbacks();
		
		instance = this;
		
		if(Refs.debug)
		{
			System.out.println("Window Init End.");
		}
	}
	
	public void close()
	{
		if(Refs.debug)
		{
			System.out.println("Window Closing.");
		}
		GLFW.glfwSetWindowShouldClose(window, true);
	}
	
	public void destroy()
	{
		if(Refs.debug)
		{
			System.out.println("Window Terminating.");
		}
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
	}
	
	public void cleanUp()
	{
		Callbacks.glfwFreeCallbacks(window);
	}
	
	public boolean shouldClose()
	{
		return GLFW.glfwWindowShouldClose(window);
	}
	
	public void update()
	{
		hasResized = false;
		input.update();
		GLFW.glfwPollEvents();
	}

	public long getWindow() {return window;}

	public int getWidth() {return width;}

	public int getHeight() {return height;}

	public String getTitle() {return title;}
	
	public boolean hasResized() {return hasResized;}
	
	public boolean getFullScreen() {return fullscreen;}
	
	public void setFullscreen(boolean full) {fullscreen = full;}
	
	public Input getInput() {return input;}
	
	public static Window getInstance() {return instance;}
}
