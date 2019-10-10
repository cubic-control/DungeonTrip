package com.fuller.DungeonTrip.gui;

import org.joml.Vector2f;

import com.fuller.DungeonTrip.BaseObject;
import com.fuller.DungeonTrip.Refs;
import com.fuller.DungeonTrip.Window;
import com.fuller.DungeonTrip.io.Input;
import com.fuller.DungeonTrip.render.Camera;
import com.fuller.DungeonTrip.render.Shader;
import com.fuller.DungeonTrip.render.TileSheet;

public class Gui extends BaseObject{
	private Shader shader;
	private Camera camera;
	private TileSheet sheet;
	
	private GuiButton temporary;
	
	public Gui(Window window) {
		if(Refs.debug)
		{
			System.out.println("Gui Init.");
		}
		shader = new Shader("gui");
		camera = new Camera(window.getWidth(), window.getHeight());
		sheet = new TileSheet("gui", 9);
		temporary = new GuiButton(new Vector2f(-32, -32), new Vector2f(96, 96));
	}
	
	public void resizeCamera(Window window) {
		camera.setProjection(window.getWidth(), window.getHeight());
	}
	
	public void update(Input input) {
		temporary.update(input);
	}
	
	public void render() {
		shader.bind();
		temporary.render(camera, sheet, shader);
	}

	@Override
	public void update() {
		if(Window.getInstance().hasResized())
		{
			resizeCamera(Window.getInstance());
		}
		update(Window.getInstance().getInput());
	}
}