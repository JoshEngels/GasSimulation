package main;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Run extends JPanel{

	//TODO: Turn into applet
	public static void main(String[] args) throws InterruptedException {

		ArrayList<Ball> temp = new ArrayList<Ball>();
		for(int i = 0; i < 100; i++){
			temp.add(new Ball((Math.random() * 350) + 4, (Math.random() * 350) + 4, 400 * (Math.random() - 0.5),  400 * (Math.random() - 0.5), (int) (10 * Math.random())));
		}
		BallSimulation simulator = new BallSimulation(temp);
		
		BallDisplay display = new BallDisplay();
		display.setImage(simulator.getNextImage(0));
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(display);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.pack();
		
		while(true) {
			long startTime = System.currentTimeMillis();
			display.setImage(simulator.getNextImage(Constants.millasecondsPerFrame / 1000.0));
			frame.repaint();
			long elapsedTime = System.currentTimeMillis() - startTime;
			if(elapsedTime < Constants.millasecondsPerFrame) {
				Thread.sleep(Constants.millasecondsPerFrame - elapsedTime);
			}
			System.out.println(elapsedTime);
		}
		
		
		
	}
}
