package com.fuller.DungeonTrip;

import java.util.Arrays;

import org.lwjgl.glfw.GLFW;

public class Input {
	private long window;
	
	private Boolean[] keys;
	
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
