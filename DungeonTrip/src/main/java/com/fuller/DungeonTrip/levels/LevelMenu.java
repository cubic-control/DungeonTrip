package com.fuller.DungeonTrip.levels;

import com.fuller.DungeonTrip.gui.GuiMenu;
import com.fuller.DungeonTrip.io.Window;
import com.fuller.DungeonTrip.render.RenderMaster;

public class LevelMenu extends Level{

	@Override
	public void initLevel() {
		GuiMenu gui = new GuiMenu(Window.getInstance());
		RenderMaster.gui = gui;
		levelObjects.add(gui);
	}

}
