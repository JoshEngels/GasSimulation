package display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import simulationControl.Constants;

public class BallDisplay extends JPanel{
	//private BufferedImage paintImage = new BufferedImage(Constants.X_MAX.intValue(), Constants.Y_MAX_GRAPH, BufferedImage.TYPE_3BYTE_BGR);


	private Image image;
	public void setImage(Image balls){
		this.image = balls;
	}




	@Override
	public void paintComponent(Graphics g){
		for(DumbBall b : image.dumbBalls){
			g.setColor(b.color);
			g.fillOval((int)(b.pos.x - b.radius), (int)(b.pos.y - b.radius), 2 * (int)b.radius, 2 * (int)b.radius);
		}


	}


}
