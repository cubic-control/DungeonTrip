package com.fuller.DungeonTrip.io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class OutputRedirector {
	
	public static void redirect()
	{
		try
		{
			System.setOut(outputFile("log.txt"));
			System.setErr(outputFile("err.txt"));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	protected static PrintStream outputFile(String name) throws FileNotFoundException
	{
		return new PrintStream(new BufferedOutputStream(new FileOutputStream(name)), true);
	}

}
