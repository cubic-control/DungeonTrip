package com.fuller.DungeonTrip;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.lwjgl.glfw.GLFW;

public class App
{
	//public static Log log;
	
    public static void main( String[] args )
    {
    	// Create Log Before This point.
    	String time = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    	//log = new Log(time);
    	System.out.println("New initialization on: " + time);
    	
        if(!GLFW.glfwInit())
        {
        	throw new IllegalStateException("Failed to initialize GLFW");
        }
        Window.setCallbacks();
        // Create Classes Here
        Window window = new Window(640, 480, "Dungeon Trip");
        GameLoop loop = new GameLoop();
        
        // Perform settings and args here
        for(int i = 0; i< args.length; i++)
        {
        	if(args[i].contains("-dev"))
        	{
        		// Turn Dev Mode On
        	}
        	if(args[i].contains("-fullscreen"))
        	{
        		window.setFullscreen(true);
        	}
        	if(args[i].contains("-debug"))
        	{
        		// Use Extra Log Prints For Debugging
        	}
        }
        // Initialize Classes Here
        window.init();
        Refs.window = window;
        
        loop.run(window);
    }
    
}