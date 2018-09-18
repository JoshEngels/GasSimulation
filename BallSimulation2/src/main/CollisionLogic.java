package main;

import static main.PhysicalVector2D.dot;
import static main.PhysicalVector2D.scale;
import static main.PhysicalVector2D.sub;

import java.util.ArrayList;


public class CollisionLogic {

	/**
	 * 
	 * @param dt
	 * @param balls
	 * @param currentTime
	 * @param collisions Assumed that this contains all collisions until currentTime + dt
	 */
	public static double update(double dt, ArrayList<Ball> balls, double currentTime, ArrayList<Collision> collisions){
		double endTime = currentTime + dt;
		double count = 0;
		while(collisions.size() != 0){
			Collision next = collisions.get(0);

			if(next.absoluteTime <= endTime){
				moveAllBalls(next.absoluteTime - currentTime, balls);
				currentTime = next.absoluteTime;
				if(next.b2 != Constants.HORIZONTAL_WALL && next.b2 != Constants.VERTICAL_WALL){
					collision(next.b1, next.b2);
				}
				else{
					wallCollision(next.b1, next.b2);
				}
				collisions.remove(0);
				count++;
			}
			else{
				break;
			}
		}

		moveAllBalls(endTime - currentTime, balls);
		return count;

	}
	
	private static void wallCollision(Ball b1, Ball wall) {

		if(wall == Constants.HORIZONTAL_WALL){
			//negative here
			b1.setVelocity(new PhysicalVector2D(b1.getVel().x * -1, b1.getVel().y));
		}
		else{
			b1.setVelocity(new PhysicalVector2D(b1.getVel().x, b1.getVel().y * -1));
		}
	}


	public static void moveAllBalls(double dt, ArrayList<Ball> balls){
		for(Ball b : balls){
			b.update(dt);
		}
	}

	public static void collision(Ball one, Ball two){
		PhysicalVector2D negChange1 = calculateUnweightedChange(sub(one.getPos(), two.getPos()), sub(one.getVel(), two.getVel()));
		PhysicalVector2D negChange2 = calculateUnweightedChange(sub(two.getPos(), one.getPos()), sub(two.getVel(), one.getVel()));
		
		double mass1 = Math.pow(one.getRadius(), Constants.AREA_MASS_DEPENDENCE);
		double mass2 = Math.pow(two.getRadius(), Constants.AREA_MASS_DEPENDENCE);

		one.setVelocity(sub(one.getVel(),  scale(negChange1, 2 * mass2 / (mass1 + mass2))));
		two.setVelocity(sub(two.getVel(),  scale(negChange2, 2 * mass1 / (mass1 + mass2))));
	}

	public static PhysicalVector2D calculateUnweightedChange(PhysicalVector2D posChange, PhysicalVector2D velChange){
		//see https://en.wikipedia.org/wiki/Elastic_collision
		return scale(posChange, dot(velChange, posChange) / Math.pow(posChange.magnitude(),2));
	}

	public static void update(ArrayList<Ball> balls, Collision collision, double currentTime){
		ArrayList<Collision> temp = new ArrayList<Collision>();
		temp.add(collision);
		update(collision.absoluteTime - currentTime, balls, currentTime, temp);
	}


}
