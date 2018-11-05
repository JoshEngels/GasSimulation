package main;

import simulation.Constants;
import simulation.Simulation;

//if size less than two just display two
//TODO: Advanced changes to simulation
//TODO: Add replay feature --> see if actually reproducible
public class Run{

	public static void main(String[] args) throws InterruptedException {
		Simulation s = new Simulation(Constants.DEFAULT_CONSTANTS);
		s.run();
	}
}
