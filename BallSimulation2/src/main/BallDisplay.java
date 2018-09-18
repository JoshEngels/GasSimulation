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
			g.setColor(b.color);
			if(!Constants.PLAY_COLOR_WAR) {
				g.setColor(Color.BLACK);
				g.drawOval((int)(b.pos.x - b.radius), (int)(b.pos.y - b.radius), 2 * (int)b.radius, 2 * (int)b.radius);

			}
			else {
				g.fillOval((int)(b.pos.x - b.radius), (int)(b.pos.y - b.radius), 2 * (int)b.radius, 2 * (int)b.radius);
			}
		}
		g.setColor(Color.BLACK);
		double simulationTime = (int)(time * 1000) / 1000.0;
		double actualTime = (System.currentTimeMillis() - Run.originalStart) / 1000.0; 
		g.drawString("Simulation Time: " + actualTime + " Seconds", 5, 12);
		g.drawString("Actual Time: " + simulationTime + " Seconds", 5, 27);
		g.drawString("Slow Down Factor: " + Math.round(((actualTime / simulationTime) * 1000)) / 1000.0, 5, 42);
		g.drawString("Collisions Per Second: " + BallSimulation.iterator * 1000 / (System.currentTimeMillis() - Run.originalStart), 5, 57);


	}


}
