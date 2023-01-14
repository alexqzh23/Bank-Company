import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
// ViewGetMoney class extends View<ControllerGetMoney>
public class ViewGetMoney extends View<ControllerGetMoney>{
	private JTextField t;
	public ViewGetMoney(Bank m, ControllerGetMoney c) {
		super(m, c);
		this.setTitle("View Money");
		this.setSize(360, 180);
		this.setLocation(230, 150);
		// Use a grid layout manager to position the two components
		this.setLayout(new GridLayout(2, 1));
		t = new JTextField("Type a customer name here");
		JButton b = new JButton("Tell me the money");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When the user then clicks on the button, the action listener of the button must read the name of the customer that was typed in the text field
				String name = t.getText();
				// return a string as result
				String money = c.getMoney(name);
				// display it back to the user
				JOptionPane.showMessageDialog(rootPane, money, "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.add(t);
		this.add(b);
		this.setVisible(true);
	}
	@Override
	public void update() {
		
	}
}
