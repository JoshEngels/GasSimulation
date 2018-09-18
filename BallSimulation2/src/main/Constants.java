package main;

public class Constants {

	
	public static final int X_MAX = 650;
	public static final int Y_MAX_SIM = 500;
	public static final int Y_MAX_GRAPH = 150;


	public final static Ball HORIZONTAL_WALL = new Ball(-1, -1, 0, 0, 0);
	public final static Ball VERTICAL_WALL = new Ball(-1, -1, 0, 0, 0);
	
	public final static int FRAME_RATE = 1000;
	public final static int MILLASECONDS_PER_FRAME = 1000 / FRAME_RATE;

	public final static int NUM_RANDOM_BALLS = 500;
	
	public final static double AREA_MASS_DEPENDENCE = 0;
	public final static double MAX_VELOCITY = 400.0;
	public final static int MIN_RADIUS = 3;
	public final static int MAX_RADIUS = 10;
	public final static boolean PLAY_COLOR_WAR = true;
}
