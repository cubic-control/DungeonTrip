package com.fuller.DungeonTrip;

import org.lwjgl.glfw.GLFW;

import com.fuller.DungeonTrip.render.RenderMaster;

public class TickMaster {
	
	public static void init()
	{
		
	}
	
	public static void tick()
	{
		if(Refs.window.input.isKeyDown(GLFW.GLFW_KEY_ESCAPE))
		{
			Refs.window.close();
		}
		if(Refs.window.input.isKeyPressed(GLFW.GLFW_KEY_SPACE))
		{
			System.out.println("Space Print");
		}
		if(Refs.window.input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_1))
		{
			System.out.println("click");
		}
		RenderMaster.player.update(0, Refs.window, RenderMaster.camera, RenderMaster.world);
		RenderMaster.world.correctCamera(RenderMaster.camera, Refs.window);
	}

}