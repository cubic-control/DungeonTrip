package com.fuller.DungeonTrip.render;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import com.fuller.DungeonTrip.MasterHelper;
import com.fuller.DungeonTrip.Refs;
import com.fuller.DungeonTrip.Window;
import com.fuller.DungeonTrip.assets.Assets;
import com.fuller.DungeonTrip.entities.EntityPlayer;
import com.fuller.DungeonTrip.world.TileRenderer;
import com.fuller.DungeonTrip.world.World;

public class RenderMaster {
	static Shader shader;
	public static Camera camera;
	public static World world;
	public static EntityPlayer player;
	
	public static void init()
	{
		if(Refs.debug)
		{
			System.out.println("Render Master Init Start.");
		}
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		camera = new Camera(640, 480);
		camera.setPosition(new Vector3f(0, 0, 0));
		MasterHelper.objects.add(camera);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		Assets.initAsset();
		TileRenderer.createRender();
		
		shader = new Shader("shader");
		world = new World("exampleLevel2");
		world.calculateView(Window.getInstance());
		MasterHelper.objects.add(world);
		player = new EntityPlayer();
		MasterHelper.objects.add(player);
		
		if(Refs.debug)
		{
			System.out.println("Render Master Init End.");
		}
	}
	
	public static void render()
	{
		world.render(TileRenderer.getInstance(), shader, camera);
		player.render(shader, camera);
	}

}
