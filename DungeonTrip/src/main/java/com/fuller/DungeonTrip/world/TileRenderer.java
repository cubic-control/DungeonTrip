package com.fuller.DungeonTrip.world;

import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.fuller.DungeonTrip.Refs;
import com.fuller.DungeonTrip.assets.Assets;
import com.fuller.DungeonTrip.render.Camera;
import com.fuller.DungeonTrip.render.Model;
import com.fuller.DungeonTrip.render.Shader;
import com.fuller.DungeonTrip.render.Texture;

public class TileRenderer {
	private HashMap<String, Texture> tile_textures;
	private Model model;
	
	private static TileRenderer instance;
	
	public TileRenderer()
	{
		if(Refs.debug)
		{
			System.out.println("TileRenderer Init.");
		}
		tile_textures = new HashMap<String, Texture>();
		
		model = Assets.getModel();
		
		for(int i = 0; i < Tile.tiles.length; i++)
		{
			if(Tile.tiles[i] != null)
			{
				if(!tile_textures.containsKey(Tile.tiles[i].getTexture()))
				{
					String tex = Tile.tiles[i].getTexture();
					tile_textures.put(tex, new Texture(tex));
				}
			}
		}
	}
	
	public void renderTile(Tile tile, int x, int y, Shader shader, Matrix4f world, Camera camera)
	{
		shader.bind();
		
		if(tile_textures.containsKey(tile.getTexture()))
		{
			tile_textures.get(tile.getTexture()).bind(0);
		}
		Matrix4f tile_pos = new Matrix4f().translate(new Vector3f(x*2, y*2, 0));
		Matrix4f target = new Matrix4f();
		
		camera.getProjection().mul(world, target);
		target.mul(tile_pos);
		
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", target);
		
		model.render();
	}
	
	public static void createRender()
	{
		if(Refs.debug)
		{
			System.out.println("TileRenderer: createRender");
		}
		instance = new TileRenderer();
	}
	
	public static TileRenderer getInstance()
	{
		return instance;
	}

}
