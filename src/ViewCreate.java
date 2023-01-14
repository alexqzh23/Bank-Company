import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
// ViewCreate class extends View<ControllerCreate>
public class ViewCreate extends View<ControllerCreate>{
	private JTextField t1;
	private JTextField t2;
	private JComboBox<String> cb;
	public ViewCreate(Bank m, ControllerCreate c) {
		super(m, c);
		this.setTitle("View Create");
		this.setSize(360, 180);
		this.setLocation(230, 480);
		// Use a grid layout manager to position the four components
		this.setLayout(new GridLayout(4, 1));
		t1 = new JTextField("Type a new customer name here");
		t2 = new JTextField("Type an amount of money here");
		// The combo box offers only two menu options
		cb = new JComboBox<String>(new String[]{"credit account", "student account"});
		JButton b = new JButton("Create");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// read the name of the customer that was typed in the text field
				String name = t1.getText();
				// read the amount of money that was typed in the second text field
				String amount = t2.getText();
				// read which menu option was selected in the combo box
				int type = cb.getSelectedIndex();
				// return a string as result
				String str = c.create(name, amount, type);
				// If the string returned by the withdraw method of the controller is different from the empty string "" then this string must be displayed back to the user using a message dialog
				if(str != "") {
					JOptionPane.showMessageDialog(rootPane, str, "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		this.add(t1);
		this.add(t2);
		this.add(cb);
		this.add(b);
		this.setVisible(true);
	}
	@Override
	public void update() {
		
	}
}
