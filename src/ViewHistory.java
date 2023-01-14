// ViewHistory class extends View<ControllerHistory>
public class ViewHistory extends View<Controller> {
	public ViewHistory(Bank m, Controller c) {
		super(m, c);
		this.setTitle("View History");
		this.setSize(360, 180);
		this.setLocation(950, 480);
		HistoryPanel hp = new HistoryPanel(m);
		this.add(hp);
		this.setVisible(true);
	}
	@Override
	public void update() {
		repaint();
	}
}
