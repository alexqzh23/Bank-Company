import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
// HistoryPanel class extends JPanel
public class HistoryPanel extends JPanel{
	private Bank m;
	public HistoryPanel(Bank m) {
		this.m = m;
	}
	private int historyMax(ArrayList<Integer> a) {
		// return as result the maximum number in the arraylist
		int max = a.get(0);
		for(int m = 1; m < a.size(); m++){
			if(a.get(m) > max){
				max = a.get(m);
			}
		}
		return max;
	}
	private int historyMin(ArrayList<Integer> a) {
		// return as result the minimum number in the arraylist
		int min = a.get(0);
		for(int n = 1; n < a.size(); n++){
			if(a.get(n) < min){
				min = a.get(n);
			}
		}
		return min;
	}
	private int historyRange(ArrayList<Integer> a) { 
		int difference = historyMax(a) - historyMin(a);
		if(difference < 100) {
			// return as result 100 if the difference between the man and min of the integers in the arraylist is strictly less than 100
			return 100;
		}
		else {
			// return as result the difference between the max and min of the integers in the arraylist
			return difference;
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ArrayList<Integer> history = m.getHistory();
		int min = historyMin(history);
		int range = historyRange(history);
		int maxX = getWidth() - 1;
		int maxY = getHeight() - 1;
		int zero = maxY + min * maxY / range;
		g.setColor(Color.BLUE);
		// this blue line represents the horizontal ¡°zero¡± axis
		g.drawLine(0, zero, maxX, zero);
		g.setColor(Color.RED);
		if(history.size() == 1) {
			int x = 0;
			int y = zero - history.get(0) * maxY / range;
			// if there is only one value in the arraylist then just draw a rectangle of size 1 by 1 at position (x, y)
			g.drawRect(x, y, 1, 1);
		} 
		else {
			for(int i = 1; i < history.size(); i++) {
				int startX = 10 * (i - 1);
				int startY = zero - history.get(i - 1) * maxY / range;
				int EndX = 10 * (i);
				int EndY = zero - history.get(i) * maxY / range;
				// Draw red lines between all the points (x, y)
				g.drawLine(startX, startY, EndX, EndY);
			}
		}
	}
}
