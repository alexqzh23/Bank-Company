import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
// View class is the superclass of all views
public abstract class View<T extends Controller> extends JFrame implements ModelListener {
	protected Bank m;
	protected T c;
	public View(Bank m, T c) {
		this.m = m;
		this.c = c;
		// register itself with the model
		m.addListener(this);
		// hide the frame when the user clicks on the ¡°close¡± button
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				c.shutdown(); // Controller decides meaning
			}
		});
	}
	@Override
	public abstract void update();
}
