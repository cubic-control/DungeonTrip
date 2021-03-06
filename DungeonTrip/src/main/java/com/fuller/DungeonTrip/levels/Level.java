package com.fuller.DungeonTrip.levels;

import java.util.ArrayList;

import com.fuller.DungeonTrip.BaseObject;
import com.fuller.DungeonTrip.render.RenderMaster;

public abstract class Level extends BaseObject{
	public boolean paused = false;
	
	public ArrayList<BaseObject> levelObjects = new ArrayList<BaseObject>();
	
	public static void loadLevel(Level level)
	{
		RenderMaster.level.destroy();
		RenderMaster.level = level;
		level.initLevel();
	}
	
	@Override
	public void update()
	{
		for(BaseObject object: levelObjects)
		{
			if(!paused)
			{
				object.update();
			}
			else if(object.isContinueous())
			{
				object.update();
			}
		}
	}
	
	@Override
	public void render()
	{
		for(BaseObject object: levelObjects)
		{
			object.render();
		}
	}
	
	public abstract void initLevel();
	
	public void destroy()
	{
		levelObjects = null;
		System.gc();
	}

}
