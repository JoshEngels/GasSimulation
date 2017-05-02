package main;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BallDisplay extends JPanel{


	private ArrayList<Ball> balls = new ArrayList<Ball>();
	public void setBalls(ArrayList<Ball> balls){
		this.balls = balls;
	}
	
	

	@Override
	public void paintComponent(Graphics g){
		for(Ball b : balls){
			g.drawOval((int)(b.getPos().x - b.getRadius()), (int)(b.getPos().y - b.getRadius()), 2 * (int)b.getRadius(), 2 * (int)b.getRadius());
		}
	}



	public void add(Ball ball) {
		balls.add(ball);
	}
	
	public ArrayList<Ball> getBalls(){
		return balls;
	}
}
