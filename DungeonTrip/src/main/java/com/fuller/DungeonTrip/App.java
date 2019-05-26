package com.fuller.DungeonTrip;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.lwjgl.glfw.GLFW;

public class App
{
	//public static Log log;
	
    public static void main( String[] args )
    {
    	OutputRedirector.redirect();
    	
    	String time = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
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
        		// Set To False When Released
        		Refs.dev = true;
        		System.out.println("DevMode Active.");
        	}
        	if(args[i].contains("-fullscreen"))
        	{
        		window.setFullscreen(true);
        	}
        	if(args[i].contains("-debug"))
        	{
        		Refs.debug = true;
        		System.out.println("Debug Active.");
        	}
        }
        
        window.init();
<<<<<<< HEAD
        Refs.window = window;
=======
>>>>>>> update
        
        loop.run(window);
    }
    
}
