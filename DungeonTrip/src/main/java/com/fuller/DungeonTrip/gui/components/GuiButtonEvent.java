package com.fuller.DungeonTrip.gui.components;

import com.fuller.DungeonTrip.io.Input;
import com.fuller.DungeonTrip.utils.ScalableVector2f;

public class GuiButtonEvent extends GuiButton{

	public GuiButtonEvent(ScalableVector2f position, ScalableVector2f scale, String id) {
		super(position, scale, id);
	}
	
	@Override
	public void update(Input input)
	{
		super.update(input);
		
		switch(selectedState)
		{
		case STATE_IDLE:
			eventIdle();
			break;
		case STATE_SELECTED:
			eventSelected();
			break;
		case STATE_CLICKED:
			eventClicked();
			break;
		}
	}
	
	protected void eventIdle() {}
	protected void eventSelected() {}
	protected void eventClicked() {
		hide();
	}

}
