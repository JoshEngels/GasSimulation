package main;

import static main.PhysicalVector2D.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestingStuffOut extends JPanel{

	public static final int xMax = 400;
	public static final int yMax = 400;
	public static final double tolerance = 0.0000001;
	
	//TODO: Cleanup code
	//TODO: Make time better

	public static void main(String[] args) throws InterruptedException {
	
		
		Ball one = new Ball(108.0,100.0,-2.0,1.0,8.0);
		Ball two = new Ball(316.00,124,0.0,-2.0,8.0);

		
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BallDisplay temp = new BallDisplay();
		temp.add(one);
		temp.setPreferredSize(new Dimension(xMax, yMax));
		temp.add(two);
		for(int i = 0; i < 300; i++){
			temp.add(new Ball((Math.random() * 392) + 4, (Math.random() * 392) + 4, (Math.random() * 4) - 2, (Math.random() * 4) - 2, 4));
		}
		frame.add(temp);
		frame.pack();
		frame.setVisible(true);
		frame.repaint();

		TestingStuffOut temp2 = new TestingStuffOut();
		BallSimulation sim = new BallSimulation(temp.getBalls());
		
		for(int i = 0; ; i++){
			temp2.update(sim, temp, 0.1);
			frame.repaint(); //Displays all balls
			Thread.sleep(1); //TODO: replace with generating future collision.
		}




	}

	double time = 0;
	public void update(BallSimulation sim, BallDisplay temp, double dt){
		ArrayList<Ball> balls = temp.getBalls();
		ArrayList<Collision> collisions = sim.getAllCollisions();
		while(collisions.size() == 0 || collisions.get(collisions.size() - 1).absoluteTime < time + dt){
			sim.setNextCollisionPoint();

		}
		CollisionLogic.update(dt, balls, time, collisions);
		time += dt;
	}
	
	//TODO: Fix all of the adding balls to list problems
	/*private void add(Ball one) {
		balls.add(one);
		collisions = new ArrayList<Collision>();
		balls.forEach(ball -> futurePositions.add(ball.copy()));
	}*/

	/**
	 * Called automatically in update if there are no more collision points, can be called manualy whenever, even in a 
	 * different thread. If no collisions in the future then simply make a new collision object with t = double max value and 
	 * two null balls.
	 */
	
	




}
