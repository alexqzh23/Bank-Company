import java.util.InputMismatchException;
import java.util.Scanner;
// used to run the interactive textbased interface of the program
public class CLI {
	// read input from the standard input stream
	private static Scanner input = new Scanner(System.in);
	// return another string as result
	private static String readLine(String string) {
		// print the string argument on the screen
		System.out.print(string);
		// read a whole line of text from the user of the program
		String line = input.nextLine();
		// return the text as result
		return line;
	}
	// return a positive integer as result
	private static int readPosInt(String string) {
		while(true) {
			try {
				// print the string argument on the screen
				System.out.print(string);
				// If the user types something which is not an integer, then the nextInt method of the scanner will throw an InputMismatchException
				int number = input.nextInt();
				// read the single newline character that comes from the user pressing the Enter key on the keyboard after typing the integer
				input.nextLine();
				if(number >= 0) {
					return number;
				}
				else{
					System.out.println("Positive integers only!");
				}
			} catch (InputMismatchException e) {
				// print the error message
				System.out.println("You must type an integer!");
				// read (and ignore) the wrong input typed by the user of the program
				input.nextLine();
			}
		}
	}
	
	public static void main(String[] args) {
		// create a single Bank object
		Bank b = new Bank("UIC Bank");
		while(true) {
			String name = "Philippe";
			int money = 0;
			// print the menu and read the integer typed by the user
			int action = readPosInt("Type an action (total:1 add:2 list:3 withdraw:4 deposit:5 quit:6): ");
			switch(action) {
				// print on the screen the total amount of money currently stored in the bank object for "UIC Bank".
				case 1: System.out.println("Total amount of money in the bank: " + b.totalMoney()); 
				break; 
				case 2: // ask the user the type of account
						int type = readPosInt("Type the account type (credit:1 student:2): ");
						if(type != 1 && type != 2) {
							System.out.println("Unknown type of account!");
							break;
						}
						// ask the user the name of the customer
						name = readLine("Enter the name of the customer: ");
						// ask the user the initial amount of money that the customer puts into the account when the account is created
						money = readPosInt("Enter the initial amount of money: ");
						// create the correct account, add it to the bank, and print an information message for the user
						if(type == 1) {
							b.addAccount(new CreditAccount(name, money));
							System.out.println("Credit account for " + name + " with " + money + " yuan has been added");
						}
						else {
							try {
								b.addAccount(new StudentAccount(name, money));
								System.out.println("Student account for " + name + " with " + money + " yuan has been added");
							} catch (NotEnoughMoneyException e) {
								// print the error message
								System.out.println("BUG! This must never happen!");
								// immediately terminating the program
								System.exit(1);
							}
						}
				break; 
				case 3: // ask the user to type the name of a customer
						name = readLine("Enter the name of the customer: ");
						// If the name of the customer is wrong (the bank does not have an account for this customer) then an UnknownCustomerException exception will be thrown by the Bank object
						try {
							money = b.getMoney(name);
							// print the amount of money which is currently in the account of this user
							System.out.println(name + " has " + money + " yuan in the bank");
						} catch (UnknownCustomerException e) {
							// print the error message from the exception object
							System.out.println(e.getMessage());
						}
				break;
				case 4:	// ask the user to type the name of a customer
						name = readLine("Enter the name of the customer: ");
						// ask the user to type an amount of money to withdraw
						money = readPosInt("Enter the amount of money to withdraw: ");
						// withdraw the amount of money from the customer's bank account
						try {
							b.withdraw(name, money);
						} catch (NotEnoughMoneyException | UnknownCustomerException e) {
							// print the error message from the exception object
							System.out.println(e.getMessage());
						}
				break; 
				case 5: // ask the user to type the name of a customer
						name = readLine("Enter the name of the customer: ");
						// ask the user to type an amount of money to deposit
						money = readPosInt("Enter the amount of money to deposit: ");
						// deposit the amount of money into the customer's bank account
						try {
							b.withdraw(name, -money);
						} catch (UnknownCustomerException e) {
							// print the error message from the exception object
							System.out.println(e.getMessage());
						} catch (NotEnoughMoneyException e) {
							// print the error message
							System.out.println("BUG! This must never happen!");
							// immediately terminating the program
							System.exit(1);
						} 
				break; 
				case 6: b.saveData();
						// print a "Goodbye!" message
						System.out.println("Goodbye!");
						// terminate the program
						System.exit(0);
				break;
				// If the user types an integer which is not between 1 and 6, then the program must print an error message
				default: System.out.println("Unknown action!");
			}
		}
	}
}
