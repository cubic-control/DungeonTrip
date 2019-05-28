package com.fuller.DungeonTrip.world;

public class Tile {
	public static Tile tiles[] = new Tile[256];
	public static byte not = 0;
	
	public static final Tile _NULL_ = new Tile("null").setSolid();
	public static final Tile plains = new Tile("plains");
	public static final Tile sahara = new Tile("sahara");
	public static final Tile tundra = new Tile("tundra");
	public static final Tile hills = new Tile("hills");
	public static final Tile dawn = new Tile("dawn");
	public static final Tile white = new Tile("white");
	// Convert above values to static array and init them later.
		// Use this when adding DLC (Separate Package)
	
	
	private byte id;
	private boolean solid;
	private String texture;
	
	public Tile(String texture)
	{
		this.id = not;
		not++;
		this.texture = texture;
		this.solid = false;
		
		if(tiles[id] != null)
		{
			throw new IllegalStateException("Tiles at: [" + id + "] is already being used!");
		}
		tiles[id] = this;
	}
	
	public Tile setSolid() {this.solid = true; return this;}
	
	public boolean isSolid() {return solid;}
	
	public byte getId() {return id;}
	
	public String getTexture() {return texture;}

}
