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
	
	public void run() throws InterruptedException{
		while(true) {

			ArrayList<Ball> temp = new ArrayList<Ball>();			
			
			long generatorStartTime = System.currentTimeMillis();
			outer:
				while(temp.size() < constants.NUM_BALLS){

					if(System.currentTimeMillis() - generatorStartTime > 1000) {
						System.out.println("Only could produce " + temp.size() + " balls" );
						System.out.println(temp.size());
						break;
					}
					
					Ball b = new Ball(Math.random() * constants.X_MAX, 
							Math.random() * constants.Y_MAX_SIM,
							constants.MAX_VELOCITY * (Math.random() - 0.5),
							constants.MAX_VELOCITY * (Math.random() - 0.5), 
							constants.MIN_RADIUS + (int)((constants.MAX_RADIUS - constants.MIN_RADIUS) * Math.random()));

					//replace with method in simulation
					if(b.getRadius() > b.getPos().x || b.getRadius() + b.getPos().x > constants.X_MAX) {
						continue;
					}
					if(b.getRadius() > b.getPos().y || b.getRadius() + b.getPos().y > constants.Y_MAX_SIM) {
						continue;
					}
					
					for(Ball c : temp) {
						if(c.intersects(b)) {
							continue outer;
						}
					}
					temp.add(b);
				}


			//temp.add(new Ball(100, 100, -100, 100, 10));
			BallSimulation simulator = new BallSimulation(temp, constants.X_MAX, constants.Y_MAX_SIM, constants.AREA_MASS_DEPENDENCE);
			Image firstImage = simulator.getNextImage(0);

			BallDisplay display = new BallDisplay();
			display.setImage(firstImage);
			display.setPreferredSize(new Dimension(constants.X_MAX, constants.Y_MAX_SIM));

			StatisticsDisplay display2 = new StatisticsDisplay(constants.X_MAX, constants.Y_MAX_GRAPH);
			display2.setImage(firstImage);
			display2.setPreferredSize(new Dimension(constants.X_MAX, constants.Y_MAX_GRAPH));

			//JPanel display3 = new ButtonDisplay();
			//display3.setPreferredSize(new Dimension(constants.X_MAX, constants.Y_MAX_GRAPH / 4));

			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			mainPanel.add(display);
			mainPanel.add(display2);
			//mainPanel.add(display3);
			//mainPanel.add(new JTextField(50));
			mainPanel.setVisible(true);
			mainPanel.setBackground(Color.white);

			JFrame ballFrame = new JFrame();
			ballFrame.add(mainPanel);
			ballFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ballFrame.setVisible(true);
			ballFrame.pack();
			ballFrame.setResizable(false);
			//ballFrame.setLocation(100, 100);

			//problem
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


			}


		}
	}
	
}
