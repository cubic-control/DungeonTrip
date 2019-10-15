package com.fuller.DungeonTrip.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import com.fuller.DungeonTrip.Refs;

public class RUtils {
	
	public static ByteBuffer loadImage(String fileName, IntBuffer w, IntBuffer h, IntBuffer comp, int channels) throws IOException
	{
		if(Refs.debug)
		{
			System.out.println("Loading Image: " + fileName);
		}
		ByteBuffer image;
		
		InputStream imageFile = RUtils.class.getResourceAsStream(fileName);
		
		if(imageFile == null)
		{
			imageFile = RUtils.class.getResourceAsStream("null");
		}
		
		byte[] imageData = IOUtils.toByteArray(imageFile);
		
		imageFile.close();
		
		ByteBuffer imageBuffer = BufferUtils.createByteBuffer(imageData.length);
		imageBuffer.put(imageData);
        imageBuffer.flip();
		
		image = STBImage.stbi_load_from_memory(imageBuffer, w, h, comp, channels);
		
		if(image == null)
		{
			throw new RuntimeException("Failed to load image: " + fileName  + System.lineSeparator() + STBImage.stbi_failure_reason());
		}
		return image;
	}

}
