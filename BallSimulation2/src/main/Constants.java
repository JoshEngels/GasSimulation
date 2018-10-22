package main;

import java.awt.Toolkit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

	
	//Toolkit.getDefaultToolkit().getScreenSize().width
	
	public static AtomicInteger X_MAX = new AtomicInteger(500);
	public static AtomicInteger Y_MAX_SIM = new AtomicInteger(400);
	public static final int Y_MAX_GRAPH = 150;


	public final static Ball HORIZONTAL_WALL = new Ball(-1, -1, 0, 0, 0);
	public final static Ball VERTICAL_WALL = new Ball(-1, -1, 0, 0, 0);
	
	public final static int FRAME_RATE = 1000;
	public final static int MILLASECONDS_PER_FRAME = 1000 / FRAME_RATE;

	public static AtomicInteger NUM_BALLS = new AtomicInteger(200);
	
	public final static double AREA_MASS_DEPENDENCE = 0;
	public final static double MAX_VELOCITY = 400.0;
	public final static int MIN_RADIUS = 3;
	public final static int MAX_RADIUS = 10;
	public final static boolean PLAY_COLOR_WAR = true;
	
	public static AtomicBoolean reset = new AtomicBoolean(false);
	

}
