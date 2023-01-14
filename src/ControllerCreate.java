// ControllerCreate class extends Controller
public class ControllerCreate extends Controller{
	public ControllerCreate(Bank m) {
		super(m);
	}
	public String create(String name, String amount, int type) {
		try {
			// transform the amount of money from a string to an integer
			int money = Integer.parseInt(amount);
			// create an object from the correct class and add the new account object to the bank
			if(type == 0) {
				m.addAccount(new CreditAccount(name, money));
			}
			else {
				m.addAccount(new StudentAccount(name, money));
			}
			// If no exception occurs then return the empty string
			return "";
		} catch (NotEnoughMoneyException | NumberFormatException e) {
			// return as result the error message from the exception object
			return e.getMessage();
		}
	}
}
