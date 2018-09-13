package main;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

//if size less than two just display two

public class Run extends JPanel{

	public static long originalStart = 0;
	public static void main(String[] args) throws InterruptedException {

		System.out.println("start");
		ArrayList<Ball> temp = new ArrayList<Ball>();

		outer:
		for(int i = 0; i < 500; i++){
			Ball b = new Ball((Math.random() * 1150) + 11, (Math.random() * 550) + 11, 300 * (Math.random() - 0.5),  300 * (Math.random() - 0.5), 3 + (int)(7 * Math.random()));
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
		frame.setPreferredSize(new Dimension(Constants.xMax + 15, Constants.yMax + 40));
		frame.pack();

		
		originalStart = System.currentTimeMillis();
		long simulationTime = System.currentTimeMillis();
		//int frameCount = 0;
		while(true) {
			//frameCount++;
	
		

			
			display.setImage(simulator.getNextImage(Constants.millasecondsPerFrame / 1000.0));
			frame.repaint();
			
			simulationTime += Constants.millasecondsPerFrame;

			//System.out.println((simulationTime - originalStart)+ " " + (System.currentTimeMillis() - originalStart));
			//System.out.println(elapsedTime);
			if((simulationTime - originalStart) > (System.currentTimeMillis() - originalStart)) {
				Thread.sleep((simulationTime - originalStart) - (System.currentTimeMillis() - originalStart));
			}
			//System.out.println(System.currentTimeMillis() - originalStart);
			//System.out.println(1000 * frameCount / (double)(System.currentTimeMillis() - originalStart)  + " " + 1000 * simulator.iterator / (double)(System.currentTimeMillis() - originalStart));
		}



	}
}
