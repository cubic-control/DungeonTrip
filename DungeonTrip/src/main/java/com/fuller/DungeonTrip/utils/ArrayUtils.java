package com.fuller.DungeonTrip.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayUtils {
	
	public static void removeItem(ArrayList<?> array, Object item)
	{
		Iterator<?> iterate = array.iterator();
		
		while(iterate.hasNext())
		{
			if(iterate.next() == item)
			{
				iterate.remove();
			}
		}
	}

}
