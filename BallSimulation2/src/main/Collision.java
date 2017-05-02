package main;

public class Collision{
	final Ball b1;
	final Ball b2;
	final double absoluteTime;

	Collision(Ball b1, Ball b2, double absoluteTime){
		this.b1 = b1;
		this.b2 = b2;
		this.absoluteTime = absoluteTime;
	}
}
