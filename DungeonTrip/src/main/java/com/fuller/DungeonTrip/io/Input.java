package com.fuller.DungeonTrip.io;

import java.util.Arrays;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.fuller.DungeonTrip.Refs;

public class Input {
	private long window;
	
	private Boolean[] keys;
	
	private static Vector2f mousePos = new Vector2f();
	private static double[] x = new double[1], y = new double[1];
	private static int[] winWidth = new int[1], winHeight = new int[1];
	
	public Input(long win)
	{
		if(Refs.debug)
		{
			System.out.println("Input Init Start.");
		}
		window = win;
		
		keys = new Boolean[GLFW.GLFW_KEY_LAST];
		Arrays.fill(keys, Boolean.FALSE);
		
		boolean present = GLFW.glfwJoystickPresent(GLFW.GLFW_JOYSTICK_1);
		
		if(present)
		{
			System.out.println("Controller 1 is present!");
		}
		
		if(Refs.debug)
		{
			System.out.println("Input Init End.");
		}
	}
	
	public boolean isKeyDown(int key)
	{
		return GLFW.glfwGetKey(window, key) == 1;
	}
	
	public boolean isKeyPressed(int key)
	{
		return (isKeyDown(key) && !keys[key]);
	}
	
	public boolean isKeyReleased(int key)
	{
		return (!isKeyDown(key) && keys[key]);
	}
	
	public boolean isMouseButtonDown(int button)
	{
		return GLFW.glfwGetMouseButton(window, button) == 1;
	}
	
	public Vector2f getMousePosition() {
		GLFW.glfwGetCursorPos(window, x, y);
		
		GLFW.glfwGetWindowSize(window, winWidth, winHeight);
		
		mousePos.set((float) x[0] - (winWidth[0] / 2.0f), -((float) y[0] - (winHeight[0] / 2.0f)));
		
		return mousePos;
	}
	
	public void update()
	{
		for(int i = 0; i < GLFW.GLFW_KEY_LAST; i++)
		{
			if(GLFW.glfwGetKeyName(i, -1) != null)
			{
				keys[i] = isKeyDown(i);
			}
		}
	}

}
