package com.fuller.DungeonTrip.levels;

import org.joml.Vector3f;

import com.fuller.DungeonTrip.MasterHelper;
import com.fuller.DungeonTrip.entities.EntityPlayer;
import com.fuller.DungeonTrip.entities.Transform;
import com.fuller.DungeonTrip.gui.GuiExample;
import com.fuller.DungeonTrip.gui.GuiPauseMenu;
import com.fuller.DungeonTrip.io.Window;
import com.fuller.DungeonTrip.render.RenderMaster;
import com.fuller.DungeonTrip.world.World;

public class LevelExample extends Level{

	@Override
	public void initLevel() {
		World world = new World("house");
		RenderMaster.world = world;
		world.calculateView(Window.getInstance());
		levelObjects.add(world);
		
		Transform transform = new Transform();
		transform.scale = new Vector3f(16, 16, 1);
		transform.pos = new Vector3f(10, -10, 0);
		
		RenderMaster.player = new EntityPlayer(transform);
		MasterHelper.addObject(RenderMaster.player);
		
		GuiExample gui = new GuiExample(Window.getInstance());
		RenderMaster.gui = gui;
		levelObjects.add(gui);
		
		GuiPauseMenu guiPauseMenu = new GuiPauseMenu(Window.getInstance());
		levelObjects.add(guiPauseMenu);
	}

}
