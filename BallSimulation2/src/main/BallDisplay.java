package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BallDisplay extends JPanel{


	private Image image;
	public void setImage(Image balls){
		this.image = balls;
	}
	
	

	@Override
	public void paintComponent(Graphics g){
		double time = image.time;
		for(DumbBall b : image.dumbBalls){
			g.setColor(new Color(b.red, b.green, b.blue));
			g.fillOval((int)(b.pos.x - b.radius), (int)(b.pos.y - b.radius), 2 * (int)b.radius, 2 * (int)b.radius);
		}
		g.drawString("Time: " + time, 5, 12);
	}


}
