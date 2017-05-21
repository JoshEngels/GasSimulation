package main;

import java.util.ArrayList;

public class BallSimulation {
	private ArrayList<Ball> actualBalls;
	private ArrayList<Ball> fakeBalls = new ArrayList<Ball>();
	private double simulationTime = 0;
	ArrayList<Collision> collisions = new ArrayList<Collision>();

	BallSimulation(ArrayList<Ball> balls){
		this.actualBalls = balls;
		actualBalls.forEach(ball -> fakeBalls.add(ball.copy()));
	}
	
	
	double count = 0;
	public void setNextCollisionPoint(){
		//count++;
		//System.out.println(count);
		Ball ball1 = null;
		Ball ball2 = null;
		double shortestTime = Double.MAX_VALUE;
		boolean wallCollision = false;
		for(Ball b1 : fakeBalls){
			for(Ball b2 : fakeBalls){
				if(b1 != b2){
					double time = getMinCollisionTime(b1, b2);
					if(time < shortestTime && time > 0){
						shortestTime = time;
						ball1 = b1;
						ball2 = b2;
						wallCollision = false;
					}
				}
			}

			
			double xTime = getXWallTime(b1);
			double yTime = getYWallTime(b1);
			double wallCollisionTime =  xTime < yTime? xTime : yTime;
			if(wallCollisionTime < shortestTime){			
				shortestTime = wallCollisionTime;
				ball1 = b1;
				ball2 = wallCollisionTime == xTime? TestingStuffOut.HorizontalWall: TestingStuffOut.VerticalWall;
				wallCollision = true;
			}
		}
		collisions.add(new Collision(actualBalls.get(fakeBalls.indexOf(ball1)), wallCollision? ball2 : actualBalls.get(fakeBalls.indexOf(ball2)), 
				shortestTime + simulationTime));
		//Simulated collisions
		CollisionLogic.update(fakeBalls, new Collision(ball1, ball2, shortestTime + simulationTime), simulationTime);

		simulationTime += shortestTime;

	}
	
	public ArrayList<Collision> getAllCollisions(){
		return collisions; //TODO: Fix permission issues
	}
	
	public static double getMinCollisionTime(Ball one, Ball two){

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

	private static double getXWallTime(Ball b1){
		PhysicalVector2D pos = b1.getPos();
		PhysicalVector2D vel = b1.getVel();
		double tx;
		if(vel.x == 0){
			tx = Double.POSITIVE_INFINITY;
		}
		else if(vel.x < 0){
			tx = (pos.x - b1.getRadius()) / -vel.x;
		}
		else{
			tx = (TestingStuffOut.xMax - b1.getRadius() - pos.x) / vel.x;
		}
		return tx;
	}
	
	private static double getYWallTime(Ball b1) {
		PhysicalVector2D pos = b1.getPos();
		PhysicalVector2D vel = b1.getVel();
		double ty;
		if(vel.y == 0){
			ty = Double.POSITIVE_INFINITY;
		}
		else if(vel.y < 0){
			ty = (pos.y - b1.getRadius()) / -vel.y;
		}
		else{
			ty = (TestingStuffOut.yMax - b1.getRadius() - pos.y) / vel.y;
		}
		return ty;
	}


}
