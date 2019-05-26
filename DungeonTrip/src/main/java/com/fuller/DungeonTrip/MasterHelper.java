package com.fuller.DungeonTrip;

import java.util.ArrayList;

import org.lwjgl.opengl.GL15;

public class MasterHelper {
	
	public static ArrayList<BaseObject> objects = new ArrayList<BaseObject>();
	
	public static void update()
	{
		for(BaseObject object: objects)
		{
			object.update();
		}
		
		if(Window.getInstance().hasResized())
		{
			GL15.glViewport(0, 0, Window.getInstance().getWidth(), Window.getInstance().getHeight());
		}
	}
	
	public static void render()
	{
		for(BaseObject object: objects)
		{
			object.render();
		}
	}

}
