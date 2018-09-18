package main;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

//if size less than two just display two

public class Run extends JPanel{

	public static long originalStart = 0;
	public static void main(String[] args) throws InterruptedException {

		ArrayList<Ball> temp = new ArrayList<Ball>();

		int count = 0;
		outer:
			for(int i = 0; i < Constants.NUM_RANDOM_BALLS; i++){
				System.out.println(++count);
				
				Ball b = new Ball(Constants.MAX_RADIUS + Math.random() * (Constants.X_MAX - Constants.MAX_RADIUS * 2), 
						Constants.MAX_RADIUS  + Math.random() * (Constants.Y_MAX_SIM - Constants.MAX_RADIUS * 2),
						Constants.MAX_VELOCITY * (Math.random() - 0.5),
						Constants.MAX_VELOCITY * (Math.random() - 0.5), 
						Constants.MIN_RADIUS + (int)((Constants.MAX_RADIUS - Constants.MIN_RADIUS) * Math.random()));

				for(Ball c : temp) {
					if(c.intersects(b)) {
						i--;
						continue outer;
					}
				}
				temp.add(b);
			}

		//temp.add(new Ball(100, 100, -100, 100, 10));
		BallSimulation simulator = new BallSimulation(temp);
		Image firstImage = simulator.getNextImage(0);

		BallDisplay display = new BallDisplay();
		display.setImage(firstImage);
		display.setPreferredSize(new Dimension(Constants.X_MAX, Constants.Y_MAX_SIM));

		StatisticsDisplay display2 = new StatisticsDisplay();
		display2.setImage(firstImage);
		display2.setPreferredSize(new Dimension(Constants.X_MAX, Constants.Y_MAX_GRAPH));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(display);
		if(Constants.PLAY_COLOR_WAR) {
			mainPanel.add(display2);
		}
		mainPanel.setVisible(true);
		mainPanel.setBackground(Color.white);

		JFrame ballFrame = new JFrame();
		ballFrame.add(mainPanel);
		ballFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ballFrame.setVisible(true);
		ballFrame.pack();

		originalStart = System.currentTimeMillis();
		long simulationTime = System.currentTimeMillis();
		while(true) {

			Image next = simulator.getNextImage(Constants.MILLASECONDS_PER_FRAME / 1000.0);

			display.setImage(next);

			if(Constants.PLAY_COLOR_WAR) {
				display2.setImage(next);
			}
			ballFrame.repaint();


			simulationTime += Constants.MILLASECONDS_PER_FRAME;
			long elapsedRealTime = System.currentTimeMillis() - originalStart;

			if((simulationTime - originalStart) > elapsedRealTime) {
				Thread.sleep((simulationTime - originalStart) - elapsedRealTime);
			}
		}



	}
}
