import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
public class Bank implements Serializable {
	private String name;
	private ArrayList<IAccount> accounts;
	// add to the Bank class an arraylist of ModelListener
	private ArrayList<ModelListener> listeners;
	// an arraylist of integers
	private ArrayList<Integer> history;
	private File file;
	
	public Bank(String name) {
		this.name = name;
		// When a bank is created, it has an arraylist of accounts but the arraylist is empty
		accounts = new ArrayList<IAccount>();
		listeners = new ArrayList<ModelListener>();
		// This arraylist must be initialized to contain only one value: zero 
		history = new ArrayList<Integer>();
		history.add(0);
		// initialize the file instance variable to be a File object for a binary file
		file = new File(name+".bin");
		if(file.exists()) {
			// read all the information from this binary file
			try {
				FileInputStream fi = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fi);
				// put it into the corresponding arraylists
				accounts = (ArrayList<IAccount>)in.readObject();
				history = (ArrayList<Integer>)in.readObject();
				in.close();
				fi.close();
			} catch(IOException e) {
				System.err.println(e.getMessage());
				System.exit(1);
			} catch(ClassNotFoundException e) {
				// This only happens if the ArrayList class does not exist in the Java software used to read the binary file
				System.err.println(e.getMessage());
				System.exit(1);
			}
		}
	}
	
	// add the account to the arraylist of accounts for the bank
	public void addAccount(IAccount account) {
		accounts.add(account);
		// call the totalMoney method and add the result to the history arraylist
		history.add(totalMoney());
		// Counter changed so notify the listeners
		notifyListeners();
	}
	// return as result the total amount of money in all the bank accounts of the bank
	public int totalMoney() {
		int sumOfMoney = 0;
		for(int i = 0; i < accounts.size(); i++) {
			sumOfMoney += accounts.get(i).getMoney();
		}
		return sumOfMoney;
	}
	// return as result the amount of money currently stored in the bank account that belongs to that customer
	public int getMoney(String name) throws UnknownCustomerException {
		for(int j = 0; j < accounts.size(); j++) {
			if(accounts.get(j).getName().equals(name)) {
				return accounts.get(j).getMoney();
			}
		}
		// If the customer does not have a bank account in the bank then the getMoney method must throw an UnknownCustomerException with the message 
		throw new UnknownCustomerException("Customer " + name + " unknown");
	}
	// withdraw that amount of money from the amount of money currently stored in the bank account that belongs to that customer
	public void withdraw(String name, int amount) throws NotEnoughMoneyException, UnknownCustomerException {
		int n = 0;
		for(int j = 0; j < accounts.size(); j++) {
			if(accounts.get(j).getName().equals(name)) {
				accounts.get(j).withdraw(amount);
				n = 1;
				break;
			}
		}
		// If the customer does not have a bank account in the bank then the withdraw method must throw an UnknownCustomerException with the message
		if(n == 0) {
			throw new UnknownCustomerException("Customer " + name + " unknown");
		}
		// call the totalMoney method and add the result to the history arraylist
		history.add(totalMoney());
		// Counter changed so notify the listeners
		notifyListeners();
	}
	public void addListener(ModelListener l) {
		// add it to the arraylist of listeners
		listeners.add(l);
	}
	public void notifyListeners() {
		for(ModelListener l: listeners) {
			// Tell the listener that something changed
			l.update();
		}
	}
	// return as result the arraylist of integers which is the bank¡¯s history
	public ArrayList<Integer> getHistory(){
		return history;
	}
	public void saveData() {
		// use object serialization to save into the binary file the accounts and history arraylists of the bank
		try {
			FileOutputStream fo = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fo);
			out.writeObject(accounts);
			out.writeObject(history);
			out.close();
			fo.close();
		} catch(IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public static void testBank() {
		// testing the constructor
		Bank b = new Bank("bank for test");
		System.out.println(b.totalMoney() == 0);
		CreditAccount ca1 = new CreditAccount("Alex", 3000);
		CreditAccount ca2= new CreditAccount("Amanda", -1000);
		// testing the addAccount method, the totalMoney method and the getMoney method
		b.addAccount(ca1);
		b.addAccount(ca2);
		System.out.println(b.totalMoney() == 2000);
		StudentAccount sa = null;
		try {
			// Throw an exception
			sa = new StudentAccount("Alice", -1000);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage().equals("Cannot create student account with negative amount of money"));
		}
		System.out.println(b.totalMoney() == 2000);
		try {
			sa = new StudentAccount("Alice", 3000);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		}
		b.addAccount(sa);
		System.out.println(b.totalMoney() == 5000);
		try {
			// Throw an exception
			System.out.println(b.getMoney("Bob"));
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage().equals("Customer Bob unknown"));
		}
		try {
			System.out.println(b.getMoney("Alex") == 3000);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(b.getMoney("Amanda") == -1000);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(b.getMoney("Alice") == 3000);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		// testing the withdraw method
		try {
			// Throw an exception
			b.withdraw("Bob", 5000);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage().equals("Customer Bob unknown"));
		}
		try {
			b.withdraw("Alex", 1000);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(b.getMoney("Alex") == 2000);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(b.totalMoney() == 4000);
		try {
			b.withdraw("Alex", 3000);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		// the amount of money in the credit account become negative
		try {
			System.out.println(b.getMoney("Alex") == -1000);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(b.totalMoney() == 1000);
		try {
			b.withdraw("Alice", 1000);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(b.getMoney("Alice") == 2000);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(b.totalMoney() == 0);
		try {
			// Throw an exception
			b.withdraw("Alice", 3000);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage().equals("Cannot withdraw 3000 yuan from account, only 2000 yuan is available"));
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		// withdraw negative money
		try {
			b.withdraw("Alice", -3000);
		} catch (NotEnoughMoneyException e) {
			System.out.println(e.getMessage());
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(b.getMoney("Alice") == 5000);
		} catch (UnknownCustomerException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(b.totalMoney() == 3000);
		// testing the getHistory method
		System.out.println(b.getHistory().get(0) == 0);
		System.out.println(b.getHistory().get(1) == 3000);
		System.out.println(b.getHistory().get(2) == 2000);
		System.out.println(b.getHistory().get(3) == 5000);
		System.out.println(b.getHistory().get(4) == 4000);
		System.out.println(b.getHistory().get(5) == 1000);
		System.out.println(b.getHistory().get(6) == 0);
		System.out.println(b.getHistory().get(7) == 3000);
		// testing the addListener method
		ViewCreate l1 = new ViewCreate(b, new ControllerCreate(b));
		ViewGetMoney l2 = new ViewGetMoney(b, new ControllerGetMoney(b));
		ViewHistory l3 = new ViewHistory(b, new ControllerHistory(b));
		ViewSimple l4 = new ViewSimple(b, new ControllerSimple(b));
		ViewWithdraw l5 = new ViewWithdraw(b, new ControllerWithdraw(b));
		b.addListener(l1);
		b.addListener(l2);
		b.addListener(l3);
		b.addListener(l4);
		b.addListener(l5);
		System.out.println(b.listeners.get(0) == l1);
		System.out.println(b.listeners.get(1) == l2);
		System.out.println(b.listeners.get(2) == l3);
		System.out.println(b.listeners.get(3) == l4);
		System.out.println(b.listeners.get(4) == l5);
		// please delete the binary file before you do this
		// otherwise the Bank constructor that you use in the tests will read the account data from the binary file
		// which will change the results of some of the tests
	}
}
