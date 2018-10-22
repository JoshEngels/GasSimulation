package main;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonDisplay extends JPanel implements ActionListener{

	private JTextField numberField;
	private JTextField xField;
	private JTextField yField;
	//private JButton reset;

	ButtonDisplay(){
		super();
		setLayout(new GridLayout(0,4));
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		numberField = new JTextField(5);
		JLabel numberName = new JLabel("New Number of Balls:");
		numberName.setLabelFor(numberField);
		add(numberName);
		add(numberField);


		xField = new JTextField(5);
		JLabel xName = new JLabel("New X Dimension:");
		xName.setLabelFor(xField);
		add(xName);
		add(xField);

		yField = new JTextField(5);
		JLabel yName = new JLabel("New Y Dimension:");
		yName.setLabelFor(yField);
		add(yName);
		add(yField);


		JButton reset = new JButton("Reset");
		reset.addActionListener(this);
		add(reset);




	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//This is ok because all done after the window renders. Will end up killing own thread after boolean set, but thats ok
		String newNum = numberField.getText();
		String newX = xField.getText();
		String newY = yField.getText();
		try {
			int num = Integer.parseInt(newNum);
			if(num > 0 & num <= 1000) {
				Constants.NUM_BALLS.set(num);
			}
		}
		catch(Exception er){}
		try {
			int x = Integer.parseInt(newX);
			if(x > 0 && x < Toolkit.getDefaultToolkit().getScreenSize().width) {
				
			}
		}
		catch(Exception er){}
		try {
			int y = Integer.parseInt(newY);
		}
		catch(Exception er){}
		
		Constants.reset.set(true);
	}

}

