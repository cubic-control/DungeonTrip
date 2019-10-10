package com.fuller.DungeonTrip.gui;

import org.joml.Vector2f;

import com.fuller.DungeonTrip.io.Window;

public class GuiMainMenu extends Gui{

	public GuiMainMenu(Window window) {
		super(window);
		
		GuiButtonEvent start = new GuiButtonEvent(new Vector2f(-32, -16), new Vector2f(32, 32), "Start") {
			@Override
			protected void eventClicked() {
				for(GuiButton button:buttons)
				{
					button.visible = false;
				}
			}
		};
		
		GuiButtonEvent end = new GuiButtonEvent(new Vector2f(-32, -16), new Vector2f(32, 32), "End") {
			@Override
			protected void eventClicked() {
				Window.getInstance().close();
			}
		};
		
		buttons.add(start);
		buttons.add(end);
	}

}
