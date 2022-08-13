package javaday9addressbook;
import javaday9addressbook.AddressBook;
import java.util.*;
public class AddressBookMain {
	public static void main(String[] args) {
		System.out.println("Welcome In Address Book System Program ");
		String firstName, lastName, address, city, state, email;
		int zip;
		long phoneNo;
		Scanner sc = new Scanner(System.in);
		AddressBook contact = new AddressBook();
		System.out.print("Enter First Name  : ");
		firstName = sc.nextLine();
		contact.setFirstName(firstName);
		System.out.print("\nEnter Last Name  : ");
		lastName = sc.nextLine();
		contact.setLastName(lastName);
		System.out.print("\nEnter E-mail  : ");
		email = sc.nextLine();
		contact.setEmail(email);
		System.out.print("\nEnter Address  : ");
		address = sc.nextLine();
		contact.setAddress(address);
		System.out.print("\nEnter City  : ");
		city = sc.nextLine();
		contact.setCity(city);
		System.out.print("\nEnter State  : ");
		state = sc.nextLine();
		contact.setState(state);
		System.out.print("\nEnter Phone Number  : ");
		phoneNo = sc.nextLong();
		contact.setPhoneNo(phoneNo);
		System.out.print("\nEnter Zip  : ");
		zip = sc.nextInt();
		contact.setZip(zip);
		
		Operate operate = new Operate();
		operate.showContact(contact);
		System.out.print("\nDo you want to edit? press Y / N : ");
		char editOption = sc.next().charAt(0);
		if (editOption == 'Y') {
			operate.editContact(contact);
		}
		operate.showContact(contact);
		sc.close();
	}
}
