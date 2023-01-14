import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
// ViewWithdraw class extends View<ControllerWithdraw>
public class ViewWithdraw extends View<ControllerWithdraw>{
	private JTextField t1;
	private JTextField t2;
	public ViewWithdraw(Bank m, ControllerWithdraw c) {
		super(m, c);
		this.setTitle("View Withdraw");
		this.setSize(360, 180);
		this.setLocation(950, 150);
		// Use a grid layout manager to position the three components
		this.setLayout(new GridLayout(3, 1));
		t1 = new JTextField("Type a customer name here");
		t2 = new JTextField("Type an amount of money here");
		JButton b = new JButton("Withdraw");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// read the name of the customer that was typed in the text field
				String name = t1.getText();
				// read the amount of money that was typed in the second text field
				String amount = t2.getText();
				// return a string as result
				String str = c.withdraw(name, amount);
				// If the string returned by the withdraw method of the controller is different from the empty string "" then this string must be displayed back to the user using a message dialog
				if(str != "") {
					JOptionPane.showMessageDialog(rootPane, str, "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		this.add(t1);
		this.add(t2);
		this.add(b);
		this.setVisible(true);
	}
	@Override
	public void update() {
		
	}
}
