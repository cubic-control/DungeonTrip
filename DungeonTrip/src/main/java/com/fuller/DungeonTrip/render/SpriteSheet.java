package com.fuller.DungeonTrip.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.stb.STBImage;

public class SpriteSheet {
	
	private final List<STBImage> sprites;

    public SpriteSheet(List<STBImage> sprites) {
        this.sprites = new ArrayList<>(sprites);
    }

    public int count() {
        return sprites.size();
    }

    public STBImage getSprite(double progress) {
        int frame = (int) (count() * progress);
        return sprites.get(frame);
    }

}
