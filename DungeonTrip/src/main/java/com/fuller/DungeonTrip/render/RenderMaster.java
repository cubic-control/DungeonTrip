package com.fuller.DungeonTrip.render;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import com.fuller.DungeonTrip.Refs;
import com.fuller.DungeonTrip.entities.EntityPlayer;
import com.fuller.DungeonTrip.world.Tile;
import com.fuller.DungeonTrip.world.TileRenderer;
import com.fuller.DungeonTrip.world.World;

public class RenderMaster {
	static Shader shader;
	public static Camera camera;
	static TileRenderer tiles;
	public static World world;
	public static EntityPlayer player;
	
	public static void init()
	{
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		shader = new Shader("shader");
		camera = new Camera(640, 480);
		camera.setPosition(new Vector3f(0, 0, 0));
		tiles = new TileRenderer();
		world = new World();
		world.setTile(Tile.test2, 5, 0);
		world.setTile(Tile.test2, 6, 0);
		world.setTile(Tile.test2, 7, 0);
		world.setTile(Tile.test2, 7, 1);
		world.setTile(Tile.test2, 7, 2);
		world.setTile(Tile.test2, 63, 63);
		player = new EntityPlayer();
	}
	
	public static void render()
	{
		world.render(tiles, shader, camera, Refs.window);
		player.render(shader, camera);
	}

}
