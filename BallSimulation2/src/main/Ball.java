package main;
import static main.PhysicalVector2D.*;

import java.awt.Color;

public class Ball {

	//Make this extend dumbBall?
	private PhysicalVector2D vel;
	private PhysicalVector2D pos;
	private double radius;
	private double time;
	private final int red = (int)(Math.random() * 256);
	private final int green = (int)(Math.random() * 256);
	private final int blue = (int)(Math.random() * 256);
	public Color color = new Color(red, green, blue);
	
	public Ball(double xPos, double yPos, double xVel, double yVel, double radius) {
		this.radius = radius;
		vel = new PhysicalVector2D(xVel, yVel);
		pos = new PhysicalVector2D(xPos, yPos);
		time = 0;
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

	public double getTime() {
		return time;
	}

	public void update(double time){
		pos = add(pos, scale(vel, time));
		this.time += time;
	}

	public void setVelocity(PhysicalVector2D vel){
		this.vel = vel;
	}

	public Ball copy(){
		return new Ball(pos.x, pos.y, vel.x, vel.y, radius);
	}

	public String toString() {
		return "Pos: " + pos.x + " , " + pos.y + ", Vel: " + vel.x + " , " + vel.y;
	}

	public boolean intersects(Ball b) {
		return PhysicalVector2D.sub(b.pos, pos).magnitude() < b.radius + radius;
	}
	
	


}
