package com.fuller.DungeonTrip.entities;

import com.fuller.DungeonTrip.io.Window;
import com.fuller.DungeonTrip.render.Camera;
import com.fuller.DungeonTrip.render.Texture;
import com.fuller.DungeonTrip.world.World;

public class EntityLiving extends Entity{

	public EntityLiving(Texture texture, Transform transform) {
		super(texture, transform);
	}

	@Override
	public void update(float delta, Window window, Camera camera, World world) {}

}