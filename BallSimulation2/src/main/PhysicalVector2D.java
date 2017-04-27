package main;

public class PhysicalVector2D {

	
	public double x;
	public double y;
	
	public PhysicalVector2D(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double magnitude(){
		return Math.sqrt(x * x + y * y);
	}
	
	public static PhysicalVector2D add(PhysicalVector2D one, PhysicalVector2D two){
		return new PhysicalVector2D(one.x + two.x, one.y + two.y);
	}
	
	public static PhysicalVector2D sub(PhysicalVector2D one, PhysicalVector2D two){
		return new PhysicalVector2D(one.x - two.x, one.y - two.y);
	}
	
	public static PhysicalVector2D multiply(PhysicalVector2D one, double scalar){
		return new PhysicalVector2D(one.x * scalar, one.y * scalar);
	}

	public static double dot(PhysicalVector2D one, PhysicalVector2D two){
		return one.x * two.x + one.y * two.y;
	}
	
	
}
