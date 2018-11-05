package display;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import simulationControl.Constants;
import simulationControl.Simulation;

public class SetupPanel extends JPanel implements ActionListener{

	JTextField numberField;
	JButton enter;
	Simulation simulationInstance;
	JTextField[] inputFields;
	
	private void setup(JTextField field, String name) {
		JLabel label = new JLabel(name);
		label.setLabelFor(field);
		add(label);
		add(field);
	}
	
	public SetupPanel(Simulation s){
		simulationInstance = s;
		setLayout(new GridLayout(0,2));
		String[] names = Constants.getNames();
		inputFields = new JTextField[names.length];
		
		for(int i = 0; i < names.length; i++) {
			inputFields[i] = new JTextField();
			setup(inputFields[i], "New " + names[i] + ":");
		}
		
		
		enter = new JButton("Reset With New Stats");
		enter.addActionListener(this);
		add(enter);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	//	simulationInstance.setCostants(new Constants(Integer.parseInt(inputFields[0].getText())
		//		, ));
	}
	//ability to set as constant forever
}
