import java.io.Serializable;

// class StudentAccount extends Account
public class StudentAccount extends Account {

	public StudentAccount(String name, int money) throws NotEnoughMoneyException {
		super(name, money);
		// If the amount of money given as argument is strictly less than zero then the constructor must throw a NotEnoughMoneyException with the message
		if(money < 0) {
			throw new NotEnoughMoneyException("Cannot create student account with negative amount of money");
		}
	}
	// subtract the amount of money given as argument to the method from the amount of money currently stored in the account
	@Override
	public void withdraw(int amount) throws NotEnoughMoneyException {
		// If the amount to withdraw is too big then the amount of money in the student account must not change and the withdraw method must throw a NotEnoughMoneyException with the message
		if(getMoney() - amount < 0) {
			throw new NotEnoughMoneyException("Cannot withdraw "+amount+" yuan from account, only "+getMoney()+" yuan is available");
		}
		setMoney(getMoney() - amount);
	}
	public static void testStudentAccount() {
		// testing the 'if' test in the constructor, the getName method and the getMoney method
		StudentAccount sa = null;
		try {
			sa = new StudentAccount("Alex", -100);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage() == "Cannot create student account with negative amount of money");
		}
		try {
			sa = new StudentAccount("Alex", 666);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(sa.getName() == "Alex");
		System.out.println(sa.getMoney() == 666);
		// testing the setMoney method
		sa.setMoney(999);
		System.out.println(sa.getMoney() == 999);
		// testing the 'if' test in the withdraw method
		try {
			sa.withdraw(1000);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage().equals("Cannot withdraw 1000 yuan from account, only 999 yuan is available")); 
		}
		try {
			sa.withdraw(333);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage()); 
		}
		System.out.println(sa.getMoney() == 666);
	}
}
