package com.fuller.DungeonTrip.render;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import com.fuller.DungeonTrip.MasterHelper;
import com.fuller.DungeonTrip.Refs;
import com.fuller.DungeonTrip.assets.Assets;
import com.fuller.DungeonTrip.entities.EntityPlayer;
import com.fuller.DungeonTrip.gui.components.Gui;
import com.fuller.DungeonTrip.io.Window;
import com.fuller.DungeonTrip.levels.Level;
import com.fuller.DungeonTrip.levels.LevelBlank;
import com.fuller.DungeonTrip.levels.LevelMenu;
import com.fuller.DungeonTrip.world.TileRenderer;
import com.fuller.DungeonTrip.world.World;

public class RenderMaster {
	public static Shader shader;
	public static Camera camera;
	public static World world;
	public static EntityPlayer player;
	public static Gui gui;
	public static Level level;
	
	public static void init()
	{
		if(Refs.debug)
		{
			System.out.println("Render Master Init Start.");
		}
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		camera = new Camera(Window.getInstance().getWidth(), Window.getInstance().getHeight());
		camera.setPosition(new Vector3f(0, 0, 0));
		MasterHelper.addObject(camera);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		Assets.initAsset();
		TileRenderer.createRender();
		
		shader = new Shader("shader");
		
		level = new LevelBlank();
		
		Level.loadLevel(new LevelMenu());
		
		if(Refs.debug)
		{
			System.out.println("Render Master Init End.");
		}
	}
	
	public static void render()
	{
		level.render();
		MasterHelper.render();
	}

}
