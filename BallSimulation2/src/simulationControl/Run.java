package simulationControl;

//if size less than two just display two
//TODO: Advanced changes to simulation
//TODO: Add replay feature --> see if actually reproducible
public class Run{


	private static Constants nextConstants = Constants.DEFAULT_CONSTANTS;
	public static void main(String[] args) throws InterruptedException {
		while(true) {
			new Simulation(nextConstants).run();
		}
	}
	
	public static void setNextConstants(Constants next) {
		nextConstants = next;
	}
}
