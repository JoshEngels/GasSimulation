package main;

import static main.PhysicalVector2D.*;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestingStuffOut extends JPanel{

	public static void main(String[] args) throws InterruptedException {
		Ball one = new Ball(108.0,108.0,0.0,1.0,8.0);
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
		Thread.sleep(1000);
		temp.update();
		frame.repaint();
		Thread.sleep(1000);

		for(int i = 0; i < 100; i++){
			one.update(.01);
			two.update(.01);
			frame.repaint();
			Thread.sleep(10);
		}
		
		System.out.println(one.getVel().x +" " + one.getVel().y);
		System.out.println(two.getVel().x +" " + two.getVel().y);


	}


	ArrayList<Ball> balls = new ArrayList<Ball>();
	private void add(Ball one) {
		balls.add(one);
	}

	public void update(){
		Ball ball1 = null;
		Ball ball2 = null;
		double shortestTime = Double.MAX_VALUE;
		for(Ball b1 : balls){
			for(Ball b2 : balls){
				if(b1 != b2){
					double time = getMinCollisionTime(b1, b2);
					if(time < shortestTime){
						shortestTime = time;
						ball1 = b1;
						ball2 = b2;
					}
				}
			}
		}
		for(Ball b : balls){
			b.update(shortestTime);
		}
		collision(ball1, ball2);
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

	@Override
	public void paintComponent(Graphics g){
		for(Ball b : balls){
			g.drawOval((int)(b.getPos().x - b.getRadius()), (int)(b.getPos().y - b.getRadius()), 2 * (int)b.getRadius(), 2 * (int)b.getRadius());
		}
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
		return multiply(posChange, dot(velChange, posChange) / posChange.magnitude());
	}



}
