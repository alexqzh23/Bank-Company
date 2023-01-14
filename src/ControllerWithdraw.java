// ControllerWithdraw class extends Controller
public class ControllerWithdraw extends Controller{
	public ControllerWithdraw(Bank m) {
		super(m);
	}
	public String withdraw(String name, String amount) {
		try {
			// transform the amount of money from a string to an integer
			int money = Integer.parseInt(amount);
			// withdraw the amount of money from the amount of money currently stored in the bank account that belongs to the customer with the correct name
			m.withdraw(name, money);
			// If no exception occurs then return the empty string
			return "";
		} catch (NotEnoughMoneyException | UnknownCustomerException | NumberFormatException e) {
			// return as result the error message from the exception object
			return e.getMessage();
		}
	}
}
