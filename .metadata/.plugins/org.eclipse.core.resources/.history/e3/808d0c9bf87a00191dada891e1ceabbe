package com.fuller.DungeonTrip;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
	Logger logger;
	
	public Log(String time)
	{
		logger = Logger.getLogger(Log.class.getName());
		FileHandler fh;
		
		try
		{
			File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
			
			fh = new FileHandler(jarDir.getAbsolutePath() + "/logs/" + time);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			
			logger.info("Log Test");
		}
		catch(SecurityException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
