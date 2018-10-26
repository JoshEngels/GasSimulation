package display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

import main.BallSimulation;
import main.Constants;
import main.DumbBall;
import main.Image;
import main.Run;

public class StatisticsDisplay extends JPanel{

	private Image image;
	private int numBalls;
	public void setImage(Image balls){
		this.image = balls;
		this.numBalls = balls.dumbBalls.size();
	}
	
	HashMap<Color, ArrayList<Integer>> history = new HashMap<>(); 
	@Override
	public void paintComponent(Graphics g){
		
		
		
		int maxEntries = 0;
		
		HashMap<Color, Integer> counts = new HashMap<Color, Integer>();
		for(DumbBall ball : image.dumbBalls) {
			Color current = ball.color;
			if(counts.containsKey(current)) {
				counts.put(current, counts.get(current) + 1);
			}
			else {
				counts.put(current, 1);
			}
		}
		for(Entry<Color, Integer> count : counts.entrySet()) {
			ArrayList<Integer> toAdd = new ArrayList<Integer>();
			if(history.containsKey(count.getKey())) {
				toAdd = history.get(count.getKey());
			}
			else {
				history.put(count.getKey(), toAdd);
			}
			toAdd.add(count.getValue());
			if(toAdd.size() > maxEntries) {
				maxEntries = toAdd.size();
			}
		}
		
		int windowWidth = Constants.X_MAX.intValue();
		int windowHeight = Constants.Y_MAX_GRAPH;
		for(Entry<Color, ArrayList<Integer>> singleHistory : history.entrySet()) {
			g.setColor(singleHistory.getKey());
			for(int i = 0; i < singleHistory.getValue().size() - 1; i++) {
				g.drawLine((i * windowWidth / maxEntries), 
						windowHeight - singleHistory.getValue().get(i) * windowHeight / numBalls, 
						(i + 1) * windowWidth / maxEntries, 
						windowHeight - singleHistory.getValue().get(i + 1) * windowHeight / numBalls);
			}
		}
		
		double time = image.time;
		g.setColor(Color.BLACK);
		double simulationTime = (int)(time * 1000) / 1000.0;
		double actualTime = (System.currentTimeMillis() - Run.originalStart) / 1000.0; 
		g.drawString("Simulation Time: " + actualTime + " Seconds", 5, 12);
		g.drawString("Actual Time: " + simulationTime + " Seconds", 5, 27);
		g.drawString("Slow Down Factor: " + Math.round(((actualTime / simulationTime) * 1000)) / 1000.0, 5, 42);
		g.drawString("Collisions Per Second: " + BallSimulation.iterator * 1000 / (System.currentTimeMillis() - Run.originalStart), 5, 57);

		
	}
}
