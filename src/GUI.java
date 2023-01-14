
public class GUI {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Bank b = new Bank("UIC Bank");
				Controller c1 = new Controller(b);
				ViewSimple v1 = new ViewSimple(b, c1);
				ControllerGetMoney c2 = new ControllerGetMoney(b);
				ViewGetMoney v2 = new ViewGetMoney(b, c2);
				ControllerWithdraw c3 = new ControllerWithdraw(b);
				ViewWithdraw v3 = new ViewWithdraw(b, c3);
				ControllerCreate c4 = new ControllerCreate(b);
				ViewCreate v4 = new ViewCreate(b, c4);
				Controller c5 = new Controller(b);
				ViewHistory v5 = new ViewHistory(b, c5);
			}
		});
	}
}
