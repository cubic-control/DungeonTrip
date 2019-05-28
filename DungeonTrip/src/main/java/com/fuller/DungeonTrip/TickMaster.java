package com.fuller.DungeonTrip;

import org.lwjgl.glfw.GLFW;


public class TickMaster {
	
	public static void init()
	{
		if(Refs.debug)
		{
			System.out.println("Tick Master Init Start.");
		}
		if(Refs.debug)
		{
			System.out.println("Tick Master Init End.");
		}
	}
	
	public static void tick()
	{
		if(Window.getInstance().input.isKeyDown(GLFW.GLFW_KEY_ESCAPE))
		{
			Window.getInstance().close();
		}
		if(Window.getInstance().input.isKeyPressed(GLFW.GLFW_KEY_SPACE))
		{
			System.out.println("Space Print");
		}
		if(Window.getInstance().input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_1))
		{
			System.out.println("click");
		}
		MasterHelper.update();
	}

}
