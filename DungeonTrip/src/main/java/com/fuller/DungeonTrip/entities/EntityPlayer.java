package com.fuller.DungeonTrip.entities;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.fuller.DungeonTrip.collision.AABB;
import com.fuller.DungeonTrip.collision.Collision;
import com.fuller.DungeonTrip.io.Window;
import com.fuller.DungeonTrip.render.Camera;
import com.fuller.DungeonTrip.render.Texture;
import com.fuller.DungeonTrip.world.World;

public class EntityPlayer extends EntityLiving{
	
	static float speed = 0.45f;
	
	public EntityPlayer(Transform transform)
	{
		super(new Texture("tiles"), transform);
		
		bounding_box = new AABB(new Vector2f(transform.pos.x, transform.pos.y), new Vector2f(1, 1));
	}
	
	public void update(float delta, Window window, Camera camera, World world)
	{
		Vector2f movement = new Vector2f();
		
		if(Window.getInstance().getInput().isKeyDown(GLFW.GLFW_KEY_A)) movement.add(new Vector2f(-speed, 0));
		if(Window.getInstance().getInput().isKeyDown(GLFW.GLFW_KEY_D)) movement.add(new Vector2f(speed, 0));
		if(Window.getInstance().getInput().isKeyDown(GLFW.GLFW_KEY_W)) movement.add(new Vector2f(0, speed));
		if(Window.getInstance().getInput().isKeyDown(GLFW.GLFW_KEY_S)) movement.add(new Vector2f(0, -speed));
		move(movement);
		
		AABB[] boxes = new AABB[25];
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				boxes[i + j * 5] = world.getTileBoundingBox(
						(int)(((transform.pos.x / 2) + 0.5f) - (5/2)) + i,
						(int)(((-transform.pos.y / 2) + 0.5f) - (5/2) + j));
			}
		}
		
		AABB box = null;
		
		for(int i = 0; i < boxes.length; i++)
		{
			if(boxes[i] != null)
			{
				if(box == null) box = boxes[i];
				
				Vector2f length1 = box.getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
				Vector2f length2 = boxes[i].getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
				
				if(length1.lengthSquared() > length2.lengthSquared())
				{
					box = boxes[i];
				}
			}
		}
		
		if(box != null)
		{
			Collision data = bounding_box.getCollision(box);
			
			if(data.isIntersecting)
			{
				bounding_box.correctPosition(box, data);
				transform.pos.set(bounding_box.getCenter(), 0);
			}
			
			for(int i = 0; i < boxes.length; i++)
			{
				if(boxes[i] != null)
				{
					if(box == null) box = boxes[i];
					
					Vector2f length1 = box.getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
					Vector2f length2 = boxes[i].getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
					
					if(length1.lengthSquared() > length2.lengthSquared())
					{
						box = boxes[i];
					}
				}
			}
			
			data = bounding_box.getCollision(box);
			
			if(data.isIntersecting)
			{
				bounding_box.correctPosition(box, data);
				transform.pos.set(bounding_box.getCenter(), 0);
			}
		}
		
		camera.getPosition().lerp(transform.pos.mul(-world.getScale(), new Vector3f()), 0.1f);
		//camera.setPosition(transform.pos.mul(-world.getScale(), new Vector3f()));
	}
}
