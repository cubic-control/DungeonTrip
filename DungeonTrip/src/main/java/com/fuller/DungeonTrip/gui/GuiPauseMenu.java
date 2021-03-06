package com.fuller.DungeonTrip.gui;

import java.util.ArrayList;

import org.joml.Vector2f;

import com.fuller.DungeonTrip.gui.components.Gui;
import com.fuller.DungeonTrip.gui.components.GuiButton;
import com.fuller.DungeonTrip.gui.components.GuiButtonEvent;
import com.fuller.DungeonTrip.io.Window;
import com.fuller.DungeonTrip.levels.Level;
import com.fuller.DungeonTrip.levels.LevelMenu;
import com.fuller.DungeonTrip.render.RenderMaster;
import com.fuller.DungeonTrip.render.TileSheet;
import com.fuller.DungeonTrip.utils.ScalableVector2f;

public class GuiPauseMenu extends Gui{

	public GuiPauseMenu(Window window) {
		super(window);
		sheet = new TileSheet("gui", 9);
		
		buttons = new ArrayList<GuiButton>();
		
		GuiButtonEvent play = new GuiButtonEvent(new ScalableVector2f(new Vector2f(0, 0)), new ScalableVector2f(new Vector2f(56, 32)), "Resume") {
			@Override
			protected void eventClicked()
			{
				hideAllButtons();
				RenderMaster.level.paused = false;
			}
		};
		GuiButtonEvent exit = new GuiButtonEvent(new ScalableVector2f(new Vector2f(0, -208)), new ScalableVector2f(new Vector2f(56, 32)), "Main Menu") {
			@Override
			protected void eventClicked()
			{
				Level.loadLevel(new LevelMenu());
			}
		};
		
		buttons.add(play);
		buttons.add(exit);
	}

}
