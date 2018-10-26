package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.Constants;
import main.DumbBall;
import main.Image;

public class BallDisplay extends JPanel{
    private BufferedImage paintImage = new BufferedImage(Constants.X_MAX.intValue(), Constants.Y_MAX_GRAPH, BufferedImage.TYPE_3BYTE_BGR);


	private Image image;
	public void setImage(Image balls){
		this.image = balls;
	}



	
	@Override
	public void paintComponent(Graphics g){
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
		

	}


}
