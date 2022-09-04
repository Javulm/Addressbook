package javaday9addressbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class AddressBookSystem {
//	create ArrayList object to store the contact details.
	static ArrayList<ContactDetails> contactDetailsList = new ArrayList<>();
//	create hashmap object to store all arraylist contacts as address book with unique name.
	static HashMap<String, ArrayList<ContactDetails>> contactMap = new HashMap<>();
	static AddressBookSystem details= new AddressBookSystem();

//	 method to add contact details in the addressbook.
	public void addContactDetails(){
//		object of Contact details Class.
		ContactDetails contact = new ContactDetails();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the First Name of person:");
		contact.setFirstName(sc.next());
		System.out.println("Enter the Last Name of person:");
		contact.setLastName(sc.next());
		System.out.println("Enter the Address Name of person:");
		contact.setAddress(sc.next());
		System.out.println("Enter the City Name of person:");
		contact.setCity(sc.next());
		System.out.println("Enter the State Name of person:");
		contact.setState(sc.next());
		System.out.println("Enter the Email of person:");
		contact.setEmail(sc.next());
		System.out.println("Enter the Zip code of person:");
		contact.setZip(sc.next());
		System.out.println("Enter the Phone number of person:");
		contact.setPhoneNo(sc.next());
		contactDetailsList.add(contact);
		System.out.println(contact);
	}
//	method to show all contact details in the addressbook.
	public void display(){
		System.out.println(contactDetailsList);
	}
//	method to edit contact details in the addressbook.
	public void editContact() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the First name to update Contact details : ");
		String editName = sc.next();
		for (int i = 0; i < contactDetailsList.size(); i++) {
			if (contactDetailsList.get(i).getFirstName().equals(editName)) {
				System.out.println("Select from below to change: ");
				System.out.println(
						"\n1.First Name\n2.Last Name\n3.Address\n4.city\n5.State\n6.Email\n7.Zip\n8.Phone number");
				int select = sc.nextInt();
				switch (select) {
					case 1:
						System.out.println("Enter first name");
						contactDetailsList.get(i).setFirstName(sc.next());
						break;
					case 2:
						System.out.println("Enter Last name");
						contactDetailsList.get(i).setLastName(sc.next());
						break;
					case 3:
						System.out.println("Enter address");
						contactDetailsList.get(i).setAddress(sc.next());
						break;
					case 4:
						System.out.println("Enter city");
						contactDetailsList.get(i).setCity(sc.next());
						break;
					case 5:
						System.out.println("Enter state");
						contactDetailsList.get(i).setState(sc.next());
						break;
					case 6:
						System.out.println("Enter email");
						contactDetailsList.get(i).setEmail(sc.next());
						break;
					case 7:
						System.out.println("Enter Zip");
						contactDetailsList.get(i).setZip(sc.next());
						break;
					case 8:
						System.out.println("Enter phone number");
						contactDetailsList.get(i).setPhoneNo(sc.next());
						break;
				}
				System.out.println("Edited contact list is: ");
				System.out.println(contactDetailsList);
			} else
				System.out.println("Enter valid First name");
		}
	}
	public void deleteContact() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Confirm the first name of the person to delete contact ");
		String deleteName = sc.nextLine();
		for (int i = 0; i < contactDetailsList.size(); i++) {
			if (contactDetailsList.get(i).getFirstName().equals(deleteName)) {
				contactDetailsList.remove(i);
				System.out.println("List After removing" + contactDetailsList);
			} else {
				System.out.println("Enter valid first name");
			}
		}
	}
	public void duplicateCheck() {
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Enter first name");
		String firstName= sc1.next();
		/*
		 * for loop is used check the condition if condition is true
		 * then element at the specified position in this list in contactDetailsList all store in contactName
		 */
		for (int k = 0; k < contactDetailsList.size(); k++) {
			String contactName = contactDetailsList.get(k).getFirstName();
			/*
			 * if 1st name in address book is present equal to firstname in contactName
			 * then print this person is already present otherwise
			 */
			if (firstName.equals(contactName)) {
				System.out.println("This Person is Already Present");
			} else {
				System.out.println("You can Add this Person");
				addContactDetails();
				break;
			}
		}
	}
	public void createAddressBook() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1.Create new address book.\n2.Edit existing address book.\n3.Display all address books.\n4.exit");
			int option= sc.nextInt();
			if (option== 4) {
				System.out.println("Exited");
				break;
			}
			switch (option) {
				case 1:
					System.out.println("Enter the name of address book: ");
					String address_name = sc.next();
					if (contactMap.containsKey(address_name)) {
						System.out.println("Address book name exist, Enter different name");
						break;
					}
					ArrayList<ContactDetails> new_address_book = new ArrayList<>();
					contactDetailsList = new_address_book;
					while (true) {
						int choose;
						System.out.println("1.Add details.\n2.Edit details.\n3.Delete contact.\n4.Display contact.\n5.Exit");
						choose = sc.nextInt();
						if (choose == 5) {
							System.out.println("Exited");
							break;
						}
						switch (choose) {
							case 1:
								details.duplicateCheck();
								break;
							case 2:
								details.editContact();
								break;
							case 3:
								details.deleteContact();
								break;
							case 4:
								details.display();
								break;
							default:
								System.out.println("please select correct option");
								break;
						}
						contactMap.put(address_name, contactDetailsList);
					System.out.println(contactMap);
					}
					break;
				case 2:
					System.out.println("Enter the name of address book: ");
					String oldAddressName = sc.next();
					if (contactMap.containsKey(oldAddressName)){
						ArrayList<ContactDetails> old_Address_Book = new ArrayList<>();
						contactDetailsList = old_Address_Book;
						contactDetailsList = contactMap.get(oldAddressName);
						while (true) {
							System.out.println("1.Add details.\n2.Edit details.\n3.Delete contact.\n4.Display contact.\n5.Exit");
							int choose = sc.nextInt();
							if (choose == 5) {
								System.out.println("Exited");
								break;
							}
							switch (choose) {
								case 1:
									details.duplicateCheck();
									break;
								case 2:
									details.editContact();
									break;
								case 3:
									details.deleteContact();
									break;
								case 4:
									details.display();
									break;
								default:
									System.out.println("Enter correct option");
									break;
							}
							contactMap.put(oldAddressName, contactDetailsList);
							System.out.println(contactMap);
						}
					} else {
						System.out.println("Enter valid address book name");
					}
					break;
				case 3:
					System.out.println(contactMap);
					break;
				default:
					System.out.println("Enter valid option");
			}
		}
	}
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book System Program ");
		details.createAddressBook();
	}
}
