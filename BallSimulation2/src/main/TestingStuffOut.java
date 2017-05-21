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
	public static final double tolerance = 0.00001;
	
	public final static Ball HorizontalWall = new Ball(-1, -1, 0, 0, 0);
	public final static Ball VerticalWall = new Ball(-1, -1, 0, 0, 0);

	
	//TODO: Cleanup code
	//TODO: Make time better

	public static void main(String[] args) throws InterruptedException {
	
		
		Ball one = new Ball(109.0,100.0,-20.0,10.0,10.0);
		Ball two = new Ball(316.00,124,20.0,-20.0,10.0);
		Ball three = new Ball(316.00,144,20.0,-20.0,80.0);
		
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BallDisplay temp = new BallDisplay();
		temp.add(one);
		temp.setPreferredSize(new Dimension(xMax, yMax));
		//temp.add(two);
		//temp.add(three);
		for(int i = 0; i < 300; i++){
			temp.add(new Ball((Math.random() * 392) + 4, (Math.random() * 392) + 4, (Math.random() * 8) - 4, (Math.random() * 8) - 4, 4));
		}
		frame.add(temp);
		frame.pack();
		frame.setVisible(true);
		frame.repaint();

		TestingStuffOut temp2 = new TestingStuffOut();
		BallSimulation sim = new BallSimulation(temp.getBalls());
		
		double total = 0;
		for(int i = 0; ; i++){
			long startTime = System.currentTimeMillis();
			temp2.update(sim, temp, .3);
			frame.repaint(); //Displays all balls
			//Thread.sleep(1); //TODO: replace with generating future collision.
			
			
			while(sim.getAllCollisions().size() < max && (System.currentTimeMillis() - startTime) < 10){
				sim.setNextCollisionPoint();
			}
			if(i == 0){
				System.out.println(sim.getAllCollisions().size());
			}
			long sleep = System.currentTimeMillis() - startTime < 10? 10 - (System.currentTimeMillis() - startTime): 0;
			Thread.sleep(sleep);
			//System.out.println(sim.getAllCollisions().size());
		}




	}

	double time = 0;
	static double max = 1000;
	double count = 0;
	public void update(BallSimulation sim, BallDisplay temp, double dt){
		ArrayList<Ball> balls = temp.getBalls();
		ArrayList<Collision> collisions = sim.getAllCollisions();
		while(collisions.size() == 0 || collisions.get(collisions.size() - 1).absoluteTime < time + dt){
			sim.setNextCollisionPoint();
			if(collisions.size() > max){
				max++;
			}
		}
		//System.out.println(collisions);
		count += CollisionLogic.update(dt, balls, time, collisions);
		time += dt;
		System.out.println(count);
	}
	
	




}
