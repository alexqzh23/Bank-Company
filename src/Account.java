import java.io.Serializable;
// Account class implements IAccount
public abstract class Account implements IAccount, Serializable{
	// indicate the name of the customer for the bank account
	private String name;
	// indicate the amount of money which is currently available in the account
	private int	money;
	
	public Account(String name, int money) {
		this.name = name;
		this.money = money;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMoney() {
		return money;
	}
	// only subclasses of the Account class can use the setMoney method
	protected void setMoney(int money) {
		this.money = money;
	}
	// to withdraw (subtract) the amount of money given as argument to the method from the amount of money currently stored in the account
	public abstract void withdraw(int amount) throws NotEnoughMoneyException;
	public static void testAccount() {
	
	}
}
