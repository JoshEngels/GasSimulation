package main;

public class Constants {

	
	public static final int xMax = 1200;
	public static final int yMax = 600;

	public final static Ball HorizontalWall = new Ball(-1, -1, 0, 0, 0);
	public final static Ball VerticalWall = new Ball(-1, -1, 0, 0, 0);
	
	public final static int frameRate = 60;
	public final static int millasecondsPerFrame = 1000 / frameRate;
}
