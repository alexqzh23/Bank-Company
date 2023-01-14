import java.io.Serializable;
// class CreditAccount extends Account
public class CreditAccount extends Account {

	public CreditAccount(String name, int money) {
		super(name, money);
	}
	// subtract the amount of money given as argument to the method from the amount of money currently stored in the account
	@Override
	public void withdraw(int amount) {
		setMoney(getMoney() - amount);
	}
	public static void testCreditAccount() {
		// testing the constructor, the getName method and the getMoney method
		CreditAccount ca = new CreditAccount("Alex", 666);
		System.out.println(ca.getName() == "Alex");
		System.out.println(ca.getMoney() == 666);
		// testing the setMoney method
		ca.setMoney(999);
		System.out.println(ca.getMoney() == 999);
		// testing the withdraw method
		ca.withdraw(333);
		System.out.println(ca.getMoney() == 666);
		ca.withdraw(-333);
		System.out.println(ca.getMoney() == 999);
	}
}
