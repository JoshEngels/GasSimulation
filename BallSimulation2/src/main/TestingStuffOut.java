package main;

import static main.PhysicalVector2D.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestingStuffOut extends JPanel{

	//TODO: Cleanup code
	
	public static void main(String[] args) throws InterruptedException {
		Ball one = new Ball(108.0,8.0,0.0,1.0,8.0);
		Ball two = new Ball(116.00,124,0.0,-1.0,8.0);

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TestingStuffOut temp = new TestingStuffOut();
		temp.add(one);
		temp.add(two);
		frame.add(temp);

		frame.setVisible(true);
		frame.repaint();
		
		temp.setNextCollisionPoint();

		for(int i = 0; i < 1000; i++){
			temp.update(0.1);
			frame.repaint();
			Thread.sleep(1); //TODO: replace with generating future collision.
			System.out.println(temp.officialTime);
		}
		



	}


	ArrayList<Ball> balls = new ArrayList<Ball>();
	private void add(Ball one) {
		balls.add(one);
	}

	/**
	 * Called automatically in update if there are no more collision points, can be called manualy whenever, even in a 
	 * different thread. If no collisions in the future then simply make a new collision object with t = double max value and 
	 * two null balls.
	 */
	public void setNextCollisionPoint(){
		//TODO: USE FUTURE STATE OF BALLS
		double currentTime = getOfficialTime();
		Ball ball1 = null;
		Ball ball2 = null;
		double shortestTime = Double.MAX_VALUE;
		for(Ball b1 : balls){
			for(Ball b2 : balls){
				if(b1 != b2){
					double time = getMinCollisionTime(b1, b2);
					if(time < shortestTime && time > 0){
						shortestTime = time;
						ball1 = b1;
						ball2 = b2;
					}
				}
			}
		}
		collisions.add(new Collision(ball1, ball2, shortestTime + currentTime));
		
		//TODO: Add collisions with walls
	}
	
	private class Collision{
		final Ball b1;
		final Ball b2;
		final double absoluteTime;
		
		Collision(Ball b1, Ball b2, double absoluteTime){
			this.b1 = b1;
			this.b2 = b2;
			this.absoluteTime = absoluteTime;
		}
	}
	
	ArrayList<Collision> collisions = new ArrayList<Collision>();
	public void update(double dt){
		double currentTime = officialTime;
		double endTime = officialTime + dt;
		
		while(true){
			if(collisions.size() == 0){
				setNextCollisionPoint();
			}
			Collision next = collisions.get(0);
			
			if(next.absoluteTime <= endTime){
				moveAllBalls(next.absoluteTime - currentTime);
				currentTime = next.absoluteTime;
				collision(next.b1, next.b2);
				collisions.remove(0);
			}
			else{
				break;
			}
		}
		
		moveAllBalls(endTime - currentTime);
		officialTime = endTime;
		//TODO: Make it not possible to mess this part up
		

	}
	
	private void moveAllBalls(double dt){
		for(Ball b : balls){
			b.update(dt);
		}
	}
	
	/**
	 * All balls are currently displayed and stored at official time
	 * @return
	 */
	private double officialTime = 0;
	/**
	 * All balls are currently displayed and stored at official time
	 * @return
	 */
	public double getOfficialTime(){
		return officialTime;
	}
	
	

	@Override
	public void paintComponent(Graphics g){
		for(Ball b : balls){
			g.drawOval((int)(b.getPos().x - b.getRadius()), (int)(b.getPos().y - b.getRadius()), 2 * (int)b.getRadius(), 2 * (int)b.getRadius());
		}
	}
	

	public double getMinCollisionTime(Ball one, Ball two){

		double j = one.getVel().x - two.getVel().x;
		double k = one.getPos().x - two.getPos().x;
		double m = one.getVel().y - two.getVel().y;
		double n = one.getPos().y - two.getPos().y;
		double totalD = one.getRadius() + two.getRadius();
		double posibilityOne = quadratic(j * j + m * m, 2 * j * k + 2 * m *n, k * k + n * n - totalD * totalD, false);
		double posibilityTwo = quadratic(j * j + m * m, 2 * j * k + 2 * m *n, k * k + n * n - totalD * totalD, true);

		if(Double.isNaN(posibilityOne) && Double.isNaN(posibilityTwo)){
			return Double.MAX_VALUE; //Will never collide
		}
		return posibilityOne < posibilityTwo? posibilityOne : posibilityTwo;
	}


	public static double quadratic(double a, double b, double c, boolean positive){
		return (-b + (positive? 1 : -1) * Math.sqrt(b * b - 4 * a * c)) / (2 * a);
	}

	public void collision(Ball one, Ball two){
		assert(/*distance = 0*/ true);
		PhysicalVector2D negChange1 = calculateUnweightedChange(sub(one.getPos(), two.getPos()), sub(one.getVel(), two.getVel()));
		PhysicalVector2D negChange2 = calculateUnweightedChange(sub(two.getPos(), one.getPos()), sub(two.getVel(), one.getVel()));
		one.setVelocity(sub(one.getVel(), negChange1));
		two.setVelocity(sub(two.getVel(), negChange2));
	}

	public PhysicalVector2D calculateUnweightedChange(PhysicalVector2D posChange, PhysicalVector2D velChange){
		//see https://en.wikipedia.org/wiki/Elastic_collision
		return multiply(posChange, dot(velChange, posChange) / Math.pow(posChange.magnitude(),2));
	}



}
