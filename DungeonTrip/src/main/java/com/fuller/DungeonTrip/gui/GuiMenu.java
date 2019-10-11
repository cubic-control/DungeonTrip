package com.fuller.DungeonTrip.gui;

import java.util.ArrayList;

import org.joml.Vector2f;

import com.fuller.DungeonTrip.gui.components.Gui;
import com.fuller.DungeonTrip.gui.components.GuiButton;
import com.fuller.DungeonTrip.gui.components.GuiButtonEvent;
import com.fuller.DungeonTrip.io.Window;
import com.fuller.DungeonTrip.levels.Level;
import com.fuller.DungeonTrip.levels.LevelExample;
import com.fuller.DungeonTrip.render.TileSheet;
import com.fuller.DungeonTrip.utils.ScalableVector2f;

public class GuiMenu extends Gui{

	public GuiMenu(Window window) {
		super(window);
		sheet = new TileSheet("gui", 9);
		
		buttons = new ArrayList<GuiButton>();
		
		GuiButtonEvent play = new GuiButtonEvent(new ScalableVector2f(new Vector2f(0, 0)), new ScalableVector2f(new Vector2f(56, 32)), "Play") {
			@Override
			protected void eventClicked()
			{
				Level.loadLevel(new LevelExample());
			}
		};
		GuiButtonEvent exit = new GuiButtonEvent(new ScalableVector2f(new Vector2f(0, -208)), new ScalableVector2f(new Vector2f(56, 32)), "Exit") {
			@Override
			protected void eventClicked()
			{
				Window.getInstance().close();
			}
		};
		
		buttons.add(play);
		buttons.add(exit);
	}

}
