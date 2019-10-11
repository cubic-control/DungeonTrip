package com.fuller.DungeonTrip.levels;

import com.fuller.DungeonTrip.MasterHelper;
import com.fuller.DungeonTrip.entities.EntityPlayer;
import com.fuller.DungeonTrip.gui.GuiExample;
import com.fuller.DungeonTrip.gui.GuiPauseMenu;
import com.fuller.DungeonTrip.io.Window;
import com.fuller.DungeonTrip.render.RenderMaster;
import com.fuller.DungeonTrip.world.World;

public class LevelExample extends Level{

	@Override
	public void initLevel() {
		World world = new World("exampleLevel2");
		RenderMaster.world = world;
		world.calculateView(Window.getInstance());
		levelObjects.add(world);
		
		RenderMaster.player = new EntityPlayer();
		MasterHelper.addObject(RenderMaster.player);
		
		GuiExample gui = new GuiExample(Window.getInstance());
		RenderMaster.gui = gui;
		levelObjects.add(gui);
		
		GuiPauseMenu guiPauseMenu = new GuiPauseMenu(Window.getInstance());
		levelObjects.add(guiPauseMenu);
	}

}
