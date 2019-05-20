package com.fuller.DungeonTrip;

import org.lwjgl.opengl.GL11;

public class GLHelper {
	
	public static void color4(int r, int g, int b, int a)
	{
		GL11.glColor4i(r, g, b, a);
	}
	
	public static void color4(float r, float g, float b, float a)
	{
		GL11.glColor4f(r, g, b, a);
	}
	
	public static void color4(double r, double g, double b, double a)
	{
		GL11.glColor4d(r, g, b, a);
	}
	
	public static void color4(short r, short g, short b, short a)
	{
		GL11.glColor4s(r, g, b, a);
	}
	
	public static void color4(byte r, byte g, byte b, byte a)
	{
		GL11.glColor4b(r, g, b, a);
	}

}
