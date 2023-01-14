// ControllerGetMoney class extends Controller
public class ControllerGetMoney extends Controller{
	public ControllerGetMoney(Bank m) {
		super(m);
	}
	// get the amount of money currently stored in the bank account that belongs to that customer
	public String getMoney(String name) {
		try {
			Integer money = m.getMoney(name);
			// transform the integer result of the getMoney method of the bank into a string
			String str = Integer.toString(money);
			// return that string as result (to the view)
			return str;	
		} catch (UnknownCustomerException e) {
			// return as result the error message from the exception object
			return e.getMessage();
		}
	}
}
