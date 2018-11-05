package simulationControl;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import display.BallDisplay;
import display.ButtonDisplay;
import display.Image;
import display.SetupPanel;
import display.StatisticsDisplay;
import simulationLogic.Ball;
import simulationLogic.BallSimulation;

//TODO: Add ability to put in seeds
//buttons: restart, random (with same stats), create

/**
 * A simulation that runs and displays. All simulations have constant parameters.
 * In the simulation display, one can reset to restart the simulation or open up the simulation creation menu.
 * @author Joshua Engels
 * 
 */
public class Simulation{

	public Constants constants;
	public Simulation(Constants c){
		constants = c;
	}

	private static ArrayList<Ball> createRandomBalls(int numBalls, double minRadius, double maxRadius, int xMax, int yMax, double maxVel){
		long generatorStartTime = System.currentTimeMillis();
		ArrayList<Ball> balls = new ArrayList<Ball>();			
		outer:
			while(balls.size() < numBalls){

				if(System.currentTimeMillis() - generatorStartTime > 1000) {
					System.out.println("Only could produce " + balls.size() + " balls" );
					System.out.println(balls.size());
					break;
				}

				Ball b = new Ball(Math.random() * xMax, 
						Math.random() * yMax,
						maxVel * (Math.random() - 0.5),
						maxVel * (Math.random() - 0.5), 
						minRadius + (int)((maxRadius - minRadius) * Math.random()));

				//replace with method in simulation
				if(b.getRadius() > b.getPos().x || b.getRadius() + b.getPos().x > xMax) {
					continue;
				}
				if(b.getRadius() > b.getPos().y || b.getRadius() + b.getPos().y > yMax) {
					continue;
				}

				for(Ball c : balls) {
					if(c.intersects(b)) {
						continue outer;
					}
				}
				balls.add(b);
			}

		return balls;
	}


	public void run() throws InterruptedException{
		ArrayList<Ball> balls = createRandomBalls(constants.NUM_BALLS, constants.MIN_RADIUS, constants.MAX_RADIUS,
				constants.X_MAX, constants.Y_MAX_SIM, constants.MAX_VELOCITY);
		BallSimulation simulator = new BallSimulation(balls, constants.X_MAX, constants.Y_MAX_SIM, constants.AREA_MASS_DEPENDENCE);

		Image firstImage = simulator.getNextImage(0);


		//All the frame stuff should be in the display package
		BallDisplay display = new BallDisplay();
		display.setImage(firstImage);
		display.setPreferredSize(new Dimension(constants.X_MAX, constants.Y_MAX_SIM));

		StatisticsDisplay display2 = new StatisticsDisplay(constants.X_MAX, constants.Y_MAX_GRAPH);
		display2.setImage(firstImage);
		display2.setPreferredSize(new Dimension(constants.X_MAX, constants.Y_MAX_GRAPH));

		ButtonDisplay display3 = new ButtonDisplay();
		display3.setPreferredSize(new Dimension(constants.X_MAX, constants.Y_MAX_GRAPH / 8));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(display);
		mainPanel.add(display2);
		mainPanel.add(display3);
		//mainPanel.add(new JTextField(50));
		mainPanel.setVisible(true);
		mainPanel.setBackground(Color.white);

		JFrame ballFrame = new JFrame();
		ballFrame.add(mainPanel);
		ballFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ballFrame.setVisible(true);
		ballFrame.pack();
		ballFrame.setResizable(false);

		long startTime = System.currentTimeMillis();
		display2.setStartTime(startTime);
		long simulationTime = startTime;
		while(true) {

			//Make collision number part of the image
			Image next = simulator.getNextImage(constants.MILLASECONDS_PER_FRAME / 1000.0);

			display.setImage(next);
			display2.setImage(next);
			display2.setNumCollisions(simulator.getNumCollisions());
			ballFrame.repaint();


			simulationTime += constants.MILLASECONDS_PER_FRAME;
			long elapsedRealTime = System.currentTimeMillis() - startTime;

			if((simulationTime - startTime) > elapsedRealTime) {
				Thread.sleep((simulationTime - startTime) - elapsedRealTime);
			}

			if(display3.reset() || display3.changeStats()) {
				break;
			}

		}

		ballFrame.dispose();

		System.out.println("here");
		if(display3.changeStats()) {
			JFrame setupFrame = new JFrame("Stat Change");
			setupFrame.add(new SetupPanel(this));
			//setupFrame.add(mainPanel);
			setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setupFrame.setVisible(true);
			setupFrame.pack();
			setupFrame.setResizable(false);

			constantsSet = false;
			synchronized(this) {
				while(!constantsSet) {
					wait();
				}
			}

			setupFrame.dispose();

		}
	}

	private boolean constantsSet = false;
	public void setCostants(Constants c) {
		synchronized(this) {
			Run.setNextConstants(c);
			constantsSet = true;
			notifyAll();
		}
	}


}
