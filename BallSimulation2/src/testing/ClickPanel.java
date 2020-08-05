package testing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ClickPanel extends JPanel implements MouseMotionListener, MouseListener {

	private int currentX = 0;
	private int currentY = 0;
	private int radius;
	private boolean clicked;
	List<Circle> circles = new ArrayList<>();
	
	public ClickPanel () {
		addMouseMotionListener(this);
		this.addMouseListener(this);
		resetCircle();
	}

	private void resetCircle() {
		radius = (int)(Math.random() * 5) + 5;
		clicked = false;
	}

	public void mouseMoved(MouseEvent e) {
		currentX = e.getX();
		currentY = e.getY();
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//NOOP
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Font font = new Font("Helvetica", Font.BOLD, 175);
		g.setFont(font);
		g.drawString("howdy!", 30, 225);
		g.setColor(Color.orange);
		if (clicked) {
			circles.add(new Circle(radius, currentX, currentY));
			resetCircle();
			System.out.print(radius + "," + currentX + "," + currentY + ",");
		}
		for (Circle c : circles) {
			g.fillOval(c.x - c.radius, c.y - c.radius, 2 * c.radius, 2 * c.radius);
		}
		g.fillOval(currentX - radius, currentY - radius, 2 * radius, 2 * radius);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clicked = true;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// NOOP		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// NOOP		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// NOOP
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// NOOP
	}
	
	private static class Circle {
		
		private int radius;
		private int x;
		private int y;

		private Circle(int radius, int x, int y) {
			this.radius = radius;
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Stat Change");
		frame.add(new ClickPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.setResizable(true);
		frame.setSize(700, 400);
	}

}