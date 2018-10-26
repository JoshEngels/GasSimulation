package display;

import java.util.ArrayList;

public class Image {

	public final ArrayList<DumbBall> dumbBalls;
	public final double time;
	public Image(ArrayList<DumbBall> a, double time){
		dumbBalls = a;
		this.time = time;
	}
}
