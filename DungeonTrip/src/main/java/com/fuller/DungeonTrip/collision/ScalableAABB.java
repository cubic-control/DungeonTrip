package com.fuller.DungeonTrip.collision;

import org.joml.Vector2f;

import com.fuller.DungeonTrip.utils.ScalableVector2f;

public class ScalableAABB{

	private ScalableVector2f center, half_extent;
	
	public ScalableAABB(ScalableVector2f center, ScalableVector2f half_extent)
	{
		this.center = center;
		this.half_extent = half_extent;
	}
	
	public Collision getCollision(ScalableAABB box2)
	{
		Vector2f distance = box2.center.getVector().sub(center.getVector(), new Vector2f());
		distance.x = (float)Math.abs(distance.x);
		distance.y = (float)Math.abs(distance.y);
		
		distance.sub(half_extent.getVector().add(box2.half_extent.getVector(), new Vector2f()));
		
		return new Collision(distance, distance.x < 0 && distance.y < 0);
	}
	
	public Collision getCollision(Vector2f point)
	{
		Vector2f distance = point.sub(center.getVector());
		distance.x = Math.abs(distance.x);
		distance.y = Math.abs(distance.y);
		
		distance.sub(half_extent.getVector());
		
		return new Collision(distance, distance.x < 0 && distance.y < 0);
	}
	
	public void correctPosition(ScalableAABB box2, Collision data)
	{
		Vector2f correction = box2.center.getVector().sub(center.getVector(), new Vector2f());
		
		if(data.distance.x > data.distance.y)
		{
			if(correction.x > 0)
			{
				center.getVector().add(data.distance.x, 0);
			}
			else
			{
				center.getVector().add(-data.distance.x, 0);
			}
		}
		else
		{
			if(correction.y > 0)
			{
				center.getVector().add(0, data.distance.y);
			}
			else
			{
				center.getVector().add(0, -data.distance.y);
			}
		}
	}

	public ScalableVector2f getCenter() {return center;}

	public ScalableVector2f getHalfExtent() {return half_extent;}

}
