package main;
import static main.PhysicalVector2D.*;

public class Ball {

	private PhysicalVector2D vel;
	private PhysicalVector2D pos;
	private double radius;
	
	public Ball(double xPos, double yPos, double xVel, double yVel, double radius) {
		this.radius = radius;
		vel = new PhysicalVector2D(xVel, yVel);
		pos = new PhysicalVector2D(xPos, yPos);

	}

	public PhysicalVector2D getPos(){
		return pos;
	}
	
	public PhysicalVector2D getVel(){
		return vel;
	}

	public double getRadius() {
		return radius;
	}
	
	public void update(double time){
		pos = add(pos, multiply(vel, time));
	}
	
	public void setVelocity(PhysicalVector2D vel){
		this.vel = vel;
	}
}
