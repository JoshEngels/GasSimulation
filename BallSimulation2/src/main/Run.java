package main;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

//if size less than two just display two

public class Run extends JPanel{

	public static long originalStart = 0;
	public static void main(String[] args) throws InterruptedException {

		while(true) {
			Constants.reset.set(false);

			ArrayList<Ball> temp = new ArrayList<Ball>();

			outer:
				for(int i = 0; i < Constants.NUM_BALLS.intValue(); i++){

					Ball b = new Ball(Constants.MAX_RADIUS + Math.random() * (Constants.X_MAX.intValue() - Constants.MAX_RADIUS * 2), 
							Constants.MAX_RADIUS  + Math.random() * (Constants.Y_MAX_SIM.intValue() - Constants.MAX_RADIUS * 2),
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
			display.setPreferredSize(new Dimension(Constants.X_MAX.intValue(), Constants.Y_MAX_SIM.intValue()));

			StatisticsDisplay display2 = new StatisticsDisplay();
			display2.setImage(firstImage);
			display2.setPreferredSize(new Dimension(Constants.X_MAX.intValue(), Constants.Y_MAX_GRAPH));

			JPanel display3 = new ButtonDisplay();
			//display3.setPreferredSize(new Dimension(Constants.X_MAX, Constants.Y_MAX_GRAPH));

			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			mainPanel.add(display);
			if(Constants.PLAY_COLOR_WAR) {
				mainPanel.add(display2);
			}
			mainPanel.add(display3);
			//mainPanel.add(new JTextField(50));
			mainPanel.setVisible(true);
			mainPanel.setBackground(Color.white);

			JFrame ballFrame = new JFrame();
			ballFrame.add(mainPanel);
			ballFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ballFrame.setVisible(true);
			ballFrame.pack();
			//ballFrame.setLocation(100, 100);

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

				if(Constants.reset.get()) {
					break;
				}

			}
			ballFrame.dispose();


		}

	}
}
