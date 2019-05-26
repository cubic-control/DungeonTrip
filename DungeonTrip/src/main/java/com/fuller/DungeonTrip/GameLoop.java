package com.fuller.DungeonTrip;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

<<<<<<< HEAD
=======
import com.fuller.DungeonTrip.assets.Assets;
>>>>>>> update
import com.fuller.DungeonTrip.render.RenderMaster;

public class GameLoop {
	boolean isPaused;
	Window window;
	
	//private int fps = 60;
	//private int frameCount = 0;
	
	public void run(Window window)
	{
		if(Refs.debug)
		{
			System.out.println("Game Loop Start.");
		}
		isPaused = false;
		this.window = window;
		/*
		new Thread()
		{
		    public void run()
		    {
		        try
		        {
		            Thread.sleep(Long.MAX_VALUE);
		        }
		        catch(Exception exc) {}
		    }
		}.start();
		
		Thread loop = new Thread()
				{
			public void run()
			{
				gameLoop();
			}
				};
		loop.start();
		*/
		init();
		gameLoop();
	}
	
	public void init()
	{
		if(Refs.debug)
		{
			System.out.println("Master Init Start.");
		}
		TickMaster.init();
		RenderMaster.init();
		
		if(Refs.debug)
		{
			System.out.println("Master Init End.");
		}
	}
	
	private void gameLoop()
	{
		final double GAME_HERTZ = 30.0; // Updates Per Second
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		final int MAX_UPDATES_BEFORE_RENDER = 5; // Lower for smoothing frames and less accuracy
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();
		
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
		
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		
		while(!window.shouldClose())
		{
			double now = System.nanoTime();
			int updateCount = 0;
			
			if(!isPaused)
			{
				while(now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER)
				{
					tick();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
				}
				
				if(now - lastUpdateTime > TIME_BETWEEN_UPDATES)
	            {
	               lastUpdateTime = now - TIME_BETWEEN_UPDATES;
	            }
				
				//float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
	            render();
	            lastRenderTime = now;
	            
	            int thisSecond = (int) (lastUpdateTime / 1000000000);
	            
	            if(thisSecond > lastSecondTime)
	            {
	               //System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
	               //fps = frameCount;
	               //frameCount = 0;
	               lastSecondTime = thisSecond;
	            }
	            
	            while(now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
	            {
	               Thread.yield();
	               
	               try
	               {
	            	   Thread.sleep(1);
	               }
	               catch(Exception e){} 
	               
	               now = System.nanoTime();
	            }
			}
		}
<<<<<<< HEAD
=======
		Assets.deleteAsset();
		MasterHelper.objects.clear();
>>>>>>> update
		window.destroy();
	}
	
	private void tick()
	{
		window.update();
		TickMaster.tick();
	}
	
	private void render()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		RenderMaster.render();
		
		GLFW.glfwSwapBuffers(window.getWindow());
	}
}
