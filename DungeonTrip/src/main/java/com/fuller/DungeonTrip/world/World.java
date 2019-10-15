package com.fuller.DungeonTrip.world;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import com.fuller.DungeonTrip.BaseObject;
import com.fuller.DungeonTrip.Refs;
import com.fuller.DungeonTrip.collision.AABB;
import com.fuller.DungeonTrip.io.Window;
import com.fuller.DungeonTrip.render.Camera;
import com.fuller.DungeonTrip.render.RenderMaster;
import com.fuller.DungeonTrip.render.Shader;
import com.fuller.DungeonTrip.utils.RUtils;

public class World extends BaseObject{
	private int viewX, viewY;
	private byte[] tiles;
	private AABB[] bounding_boxes;
	//private List<Entity> entities;
	private int width, height;
	private int scale;
	
	private Matrix4f world;
	
	public World(String world)
	{
		if(Refs.debug)
		{
			System.out.println("World Init.");
		}
		
		try {
			loadWorld(world);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//ByteBuffer entity_sheet = STBImage.stbi_load(Refs.res + "assets/levels/" + world + "_entity.png", w, h, comp, 4);
		
		
		//STBImage.stbi_image_free(entity_sheet);
	}
	
	private void loadWorld(String world) throws IOException
	{
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		IntBuffer comp = BufferUtils.createIntBuffer(1);
		
		ByteBuffer mapTiles = RUtils.loadImage(Refs.lvl + world + "_tiles.png", w, h, comp, 4);
		//ByteBuffer entityTiles = RUtils.loadImage(Refs.lvl + world + "_entities.png", w, h, comp, 4);
		
		if(mapTiles == null /*|| entityTiles == null*/)
		{
			throw new RuntimeException("Failed to load level file!" + System.lineSeparator() + STBImage.stbi_failure_reason());
		}
		
		width = w.get();
		height = h.get();
		scale = 16;
		
		this.world = new Matrix4f().setTranslation(new Vector3f(0));
		this.world.scale(scale);
		
		tiles = new byte[width * height];
		bounding_boxes = new AABB[width * height];
		//entities = new ArrayList<>();
		
		//Transform transform;
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				int red = (mapTiles.get((x + y * width) * 4)) & 0xFF;
				//int entityIndex = (entityTiles.get((x + y * width) * 4)) & 0xFF;
				Tile t;
				
				try
				{
					t = Tile.tiles[red];
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					t = null;
				}
				
				if(t != null)
				{
					setTile(t, x, y);
				}
				
				//if(entityIndex > 0)
				//{
					//transform = new Transform();
					//transform.pos.x = x * 2;
					//transform.pos.y = -y * 2;
					
					//switch(entityIndex)
					//{
					//case 1:
						//EntityPlayer player = new EntityPlayer(transform);
						//entities.add(player);
						//RenderMaster.camera.getPosition().set(transform.pos.mul(-scale, new Vector3f()));
						//break;
					//default:
						//break;
					//}
				//}
			}
		}
		STBImage.stbi_image_free(mapTiles);
	}
	
	public World()
	{
		width = 64;
		height = 64;
		scale = 16;
		
		tiles = new byte[width * height];
		bounding_boxes = new AABB[width * height];
		
		world = new Matrix4f().setTranslation(new Vector3f(0));
		world.scale(scale);
	}
	
	public void calculateView(Window window)
	{
		viewX = (window.getWidth() / (scale * 2)) + 4;
		viewY = (window.getHeight() / (scale * 2)) + 4;
	}
	
	public Matrix4f getWorldMatrix()
	{
		return world;
	}
	
	public void render(TileRenderer render, Shader shader, Camera camera)
	{
		int posX = (int)camera.getPosition().x / (scale * 2);
		int posY = (int)camera.getPosition().y / (scale * 2);
		
		for(int i = 0; i < viewX; i++)
		{
			for(int j = 0; j < viewY; j++)
			{
				Tile t = getTile(i - posX - (viewX / 2) + 1, j + posY - (viewY / 2));
				
				if(t != null)
				{
					render.renderTile(t, i - posX - (viewX / 2) + 1, -j - posY + (viewY / 2), shader, world, camera);
				}
			}
		}
		/*
		for (Entity entity : entities) {
			entity.render(shader, camera);
		}
		*/
	}
	/*
	public void update(float delta, Window window, Camera camera) {
		for (Entity entity : entities) {
			entity.update(delta, window, camera, this);
		}
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).collideWithTiles(this);
			for (int j = i + 1; j < entities.size(); j++) {
				entities.get(i).collideWithEntity(entities.get(j));
			}
			entities.get(i).collideWithTiles(this);
		}
	}
	*/
	public void correctCamera(Camera camera, Window window)
	{
		Vector3f pos = camera.getPosition();
		
		int w = -width * scale * 2;
		int h = height * scale * 2;
		
		if(pos.x > -(window.getWidth() / 2) + scale)
		{
			pos.x = -(window.getWidth() / 2) + scale;
		}
		if(pos.x < w + (window.getWidth() / 2) + scale)
		{
			pos.x = w + (window.getWidth() / 2) + scale;
		}
		
		if(pos.y < (window.getHeight() / 2) - scale)
		{
			pos.y = (window.getHeight() / 2) - scale;
		}
		if(pos.y > h - (window.getHeight() / 2) - scale)
		{
			pos.y = h - (window.getHeight() / 2) - scale;
		}
	}
	
	public void setTile(Tile tile, int x, int y)
	{
		if((x + y * width) == 2079)
		{
			System.out.println("Deadly Number Found: Ignoring.");
			return; // Don't use this space.
		}
		tiles[x + y * width] = tile.getId(); //TODO: ERROR HERE
		
		if(tile.isSolid())
		{
			bounding_boxes[x + y * width] = new AABB(new Vector2f(x*2, -y*2), new Vector2f(1, 1));
		}
		else
		{
			bounding_boxes[x + y * width] = null;
		}
	}
	
	public Tile getTile(int x, int y)
	{
		try
		{
			return Tile.tiles[tiles[x + y * width]];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}
	
	public AABB getTileBoundingBox(int x, int y)
	{
		try
		{
			return bounding_boxes[x + y * width];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}

	public int getScale() {
		return scale;
	}

	@Override
	public void update() {
		if(Window.getInstance().hasResized())
		{
			this.calculateView(Window.getInstance());
		}
		this.correctCamera(RenderMaster.camera, Window.getInstance());
	}

	@Override
	public void render() {
		this.render(TileRenderer.getInstance(), RenderMaster.shader, RenderMaster.camera);
	}
}
