package simulationControl;

import java.awt.Toolkit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import simulationLogic.Ball;

public class Constants {

	

	public final static Ball HORIZONTAL_WALL = new Ball(-1, -1, 0, 0, 0);
	public final static Ball VERTICAL_WALL = new Ball(-1, -1, 0, 0, 0);
	public final static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	public final static Constants DEFAULT_CONSTANTS = new Constants(
			SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2, SCREEN_HEIGHT / 4,
			1000, 500, 0, 400, 3, 10);

	
	public Constants(int xMax, int yMaxSim, int yMaxGraph, int frameRate, int numBalls, double areaMassDependence,
			double maxVel, double minRadius, double maxRadius){
			X_MAX = xMax;
			Y_MAX_SIM = yMaxSim;
			Y_MAX_GRAPH = yMaxGraph;
			FRAME_RATE = frameRate;
			MILLASECONDS_PER_FRAME = 1000 / FRAME_RATE;
			NUM_BALLS = numBalls;
			AREA_MASS_DEPENDENCE = areaMassDependence;
			MAX_VELOCITY = maxVel;
			MIN_RADIUS = minRadius;
			MAX_RADIUS = maxRadius;

	}
	
	public final int X_MAX;
	public final int Y_MAX_SIM;
	public final int Y_MAX_GRAPH;

	public final int FRAME_RATE;
	public final int MILLASECONDS_PER_FRAME;

	public final int NUM_BALLS;
	
	public double AREA_MASS_DEPENDENCE;
	public double MAX_VELOCITY;
	public double MIN_RADIUS;
	public double MAX_RADIUS;
	
	public static String[] getNames() {
		return new String[] {"Width of Simulation (Pixels)", "Height of Simulation (Pixels)",
		        "Height of Graph (Pixels)", "Frame Rate (Higher Recommended)", 
		        "Number of Random Balls", "D = Area Mass Dependece (M = A^D)",
		        "Maximum Velocity (Pixels per Second)",
		        "Minimum Radius (Pixels)", "Maximum Radius (Pixels)"};
	}

}
