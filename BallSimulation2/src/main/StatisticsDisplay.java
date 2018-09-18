package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

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
		
		//100 by 100
		int windowWidth = Constants.X_MAX;
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
		
	}
}
