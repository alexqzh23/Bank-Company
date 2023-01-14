import java.awt.Font;

import javax.swing.JLabel;
//ViewSimple class extends View<Controller> and implements the ModelListener interface
public class ViewSimple extends View<Controller> implements ModelListener{
	private JLabel label;
	
	public ViewSimple(Bank m, Controller c) {
		super(m, c);
		this.setTitle("View Simple");
		this.setSize(360, 180);
		this.setLocationRelativeTo(null);
		// create a JLabel object, stores it in the label instance variable of the ViewSimple class
		label = new JLabel();
		label.setFont(new Font(Font.DIALOG, Font.PLAIN, 23));
		// initialize it to display the total amount of money in all the bank accounts of the bank
		update();
		this.add(label);
		this.setVisible(true);
	}
	// update the text of the label as necessary so that the label always displays the current value of the total amount of money in all the bank accounts of the bank
	@Override
	public void update() {
		label.setText("total money: " + m.totalMoney());
	}
}
