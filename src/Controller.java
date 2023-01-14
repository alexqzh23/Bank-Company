// Controller class is the superclass of all controllers
public class Controller {
	protected Bank m;
	public Controller(Bank m) {
		this.m = m;
	}
	protected void shutdown() {
		m.saveData(); // Tell the model to save its data
		System.exit(0); // Normal exit
	}
}
