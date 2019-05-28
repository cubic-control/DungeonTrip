package com.fuller.DungeonTrip;

import java.net.URL;

public class Refs {
	public static final String tex = "/assets/textures/";
	public static final String snd = "/assets/sounds/";
	public static boolean debug = false;
	public static boolean dev = false;
	
	public static String getFile(String texture)
	{
		String result;
		
		URL url = Refs.class.getResource(texture);
		
		if(url == null)
		{
			System.out.println("URL was NULL!" 
					+ System.lineSeparator() 
					+ "Moving to alternative loading method.");
			return "./src/main/resources/" + texture;
		}
		result = url.getPath();
		
		// Fixes "/C:/"
		if(result.startsWith("/"))
		{
			result = result.substring(1, result.length());
		}
		
		if(result.contains(".jar!"))
		{
			// Not sure what to do here.
			// Currently removing the first half of the string
			// with fruitless results.
			result = result.substring(result.indexOf(".jar!") + 5, result.length());
			// I also attempted alterations of this method; still fruitless.
		}
		System.out.println(result);
		
		return result;
	}

}
