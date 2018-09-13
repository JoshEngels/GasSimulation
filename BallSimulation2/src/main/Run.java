package main;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Run extends JPanel{

	//TODO: Turn into applet
	public static void main(String[] args) throws InterruptedException {

		//TODO max radius
		System.out.println("start");
		ArrayList<Ball> temp = new ArrayList<Ball>();

		outer:
		for(int i = 0; i < 1001; i++){
			Ball b = new Ball((Math.random() * 1150) + 11, (Math.random() * 550) + 11, 300 * (Math.random() - 0.5),  300 * (Math.random() - 0.5), 3 + (int) (7 * Math.random()));
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

		BallDisplay display = new BallDisplay();
		display.setImage(simulator.getNextImage(0));

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(display);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.pack();

		
		long originalStart = System.currentTimeMillis();
		int frameCount = 0;
		while(true) {
			frameCount++;
	
		

			//System.out.println(simulator.nextBall);
			
			long startTime = System.currentTimeMillis();
			display.setImage(simulator.getNextImage(Constants.millasecondsPerFrame / 1000.0));
			frame.repaint();
			long elapsedTime = System.currentTimeMillis() - startTime;
			if(elapsedTime < Constants.millasecondsPerFrame) {
				Thread.sleep(Constants.millasecondsPerFrame - elapsedTime);
			}
			//System.out.println(1000 * frameCount / (double)(System.currentTimeMillis() - originalStart)  + " " + 1000 * simulator.iterator / (double)(System.currentTimeMillis() - originalStart));
		}



	}
}
