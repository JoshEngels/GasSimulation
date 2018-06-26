package main;

/**
 * Enough to display and thats it
 * Immutable
 * @author Josh
 *
 */
public class DumbBall {

	public final PhysicalVector2D pos;
	public final double radius;
	DumbBall(PhysicalVector2D pos, double radius){
		this.pos = pos;
		this.radius = radius;
	}
}
