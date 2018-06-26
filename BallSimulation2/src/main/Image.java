package main;

import java.util.ArrayList;

public class Image {

	ArrayList<DumbBall> dumbBalls = new ArrayList<DumbBall>();
	double time;
	Image(ArrayList<DumbBall> a, double time){
		dumbBalls = a;
		this.time = time;
	}
}
