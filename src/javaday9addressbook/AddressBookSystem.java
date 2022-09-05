package javaday9addressbook;

import java.util.*;
import java.util.stream.Collectors;

class AddressBookSystem {
//	create ArrayList object to store the contact details.
	public static ArrayList<ContactDetails> contactDetailsList = new ArrayList<>();
	public static Map<String, ContactDetails> nameHashMap = new HashMap<String, ContactDetails>();
	/*
	 * create object for map,object name is nameHashMap
	 * store the city of person in this object
	 */
	public static Map<String, ContactDetails> cityHashMap = new HashMap<String, ContactDetails>();
	/*
	 * create object for map,object name is StateHashMap
	 * store the State of person in this object
	 */
	public static Map<String, ContactDetails> stateHashMap = new HashMap<String, ContactDetails>();
	/*
	 * create a method name as addContact,this is parameterized method
	 * this method is boolean type that means their output is true or false
	 *
	 * @param contact in contactlist
	 * @return true
	 */
	 public boolean addContact(ContactDetails contact) {
        List<ContactDetails> checkByName = searchByName(contact.getFirstName());
        for (ContactDetails equalName : checkByName)
            if (equalName.equals(contact))
                return false;
        contactDetailsList.add(contact);
        return true;
    }
	/*
	 * create a method name as searchByName this is parametrized method
	 *
	 * @param name of person in contactlist
	 * @return search name
	 */
	public List<ContactDetails> searchByName(String name) {
		/*
		 * collection list of element
		 * stream and lambda for find filter given name from arraylist
		 */
		return contactDetailsList.stream().filter(person -> person.getFirstName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
	}
	/*
	 * create a method name as searchByCity in this method we search the pesron is their city name
	 *
	 * @param city person
	 * @return person
	 */
	public List<ContactDetails> searchByCity(String city) {
		return contactDetailsList.stream().filter(person -> person.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());
	}

	/*
	 * create a method name as searchByState in this method we search the person is their State name
	 *
	 * @param state person
	 * @return person
	 */
	public List<ContactDetails> searchByState(String state) {
		return contactDetailsList.stream().filter(person -> person.getState().equalsIgnoreCase(state))
				.collect(Collectors.toList());
	}
//	method to show all contact details in the addressbook.
	public void display(){
		System.out.println(contactDetailsList);
	}
	/*
	 * Method to view person
	 * @param nameHashMap
	 */
	public static void viewByName(Map<String, ContactDetails> nameHashMap) {
		nameHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
	}

	public static void viewByCity(Map<String, ContactDetails> cityHashMap) {
		cityHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
	}

	public static void viewByState(Map<String, ContactDetails> stateHashMap) {
		stateHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
	}
	/*
	 * create a method name as editContact this is parameterized method
	 * @param current details.
	 * @return editing new data
	 */
public boolean editContact(ContactDetails current, ContactDetails edit) {
	if (!contactDetailsList.contains(current))
		return false;
	/*
	 * remove current data in contact list
	 */
	contactDetailsList.remove(current);
	/*
	 * add the new data in contactList
	 */
	contactDetailsList.add(edit);
	/*
	 * both are ture then return true
	 */
	return true;
}
	/*
	 * create a method for delete contact, this is parameterized method
	 *
	 * @param contacts in contact list
	 * @return delete contact
	 */
	public boolean deleteContact(ContactDetails contacts) {
		contactDetailsList.remove(contacts);
		return true;
	}
	/*
	 * for showing output details
	 *
	 * @return result
	 */
	@Override
	public String toString() {
		/*
		 * if contact list is empty then return no contacts found
		 */
		if (contactDetailsList.isEmpty())
			return "No contacts found!";
		String result = new String();
		/*
		 * using for loop check the condition and search the contactList in specific position
		 * then update
		 */
		for (int i = 0; i < contactDetailsList.size(); i++) {
			result += " " + contactDetailsList.get(i);
		}
		return result;
	}
	/*
	 * create a method name as readContact
	 * method for adding details
	 *
	 * @return firstName, lastName, email, phoneNumber, City, Address, Zip, State
	 */
	public static ContactDetails readContact() {
		/*
		 * create a scanner class object
		 * scanner is used for getting input from user
		 */
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter First Name: ");
		String firstName = sc.nextLine();
		System.out.print("Enter Last Name: ");
		String lastName = sc.nextLine();
		System.out.print("Enter Address: ");
		String address = sc.nextLine();
		System.out.print("Enter City: ");
		String city = sc.nextLine();
		System.out.print("Enter State: ");
		String state = sc.nextLine();
		System.out.print("Enter Zip Code: ");
		int zip = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Phone Number: ");
		long phoneNumber = sc.nextLong();
		sc.nextLine();
		System.out.print("Enter Email ID: ");
		String email = sc.nextLine();
		return new ContactDetails(firstName, lastName, address, city, state, zip, phoneNumber, email);
	}
	/*
	 * create a method name as addressBookOptions
	 * method for show option for contacts
	 *
	 * @param addressBook show contacts
	 */
	public static void addressBookOptions(AddressBookSystem addressBook) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			/*
			 * choose option for what u want
			 */
			System.out.println("\n-------------------------- Address Book Contact Option --------------------------");
			System.out.println("1. Add contact details");
			System.out.println("2. Edit contact details");
			System.out.println("3. Delete contact details");
			System.out.println("4. Show contacts details");
			System.out.println("5. Back to main menu");
			System.out.print("Enter Your choice: ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1:
					/*
					 * call addcontact with passing method readcontact
					 */
					if (addressBook.addContact(readContact()))
						System.out.println("Contact Added Successfully....!");
					else
						System.out.println("Contact Already Exist....!");
					break;
				case 2:
					System.out.println("Enter First name to edit contact: ");
					String name = sc.nextLine();
					/*
					 * list of equal first name
					 */
					List<ContactDetails> equalName = addressBook.searchByName(name);
					/*
					 * if not match found
					 */
					if (equalName.isEmpty())
						System.out.println("Data Not Found....!");
					/*
					 * if only one equal match found
					 */
					else if (equalName.size() == 1) {
						/*
						 * call edit method with name and method
						 */
						addressBook.editContact(equalName.get(0), readContact());
						System.out.println("Contact data modified....!");
					} else {
						/*
						 * if more than one firstname match found in equal name list
						 */
						equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
						System.out.println("Enter index to edit : ");
						int i = sc.nextInt();
						sc.nextLine();
						addressBook.editContact(equalName.get(i), readContact());
						System.out.println("Contact Modified....!");
					}
					break;
				case 3:
					System.out.println("Enter First name to delete contact: ");
					name = sc.nextLine();
					equalName = addressBook.searchByName(name);
					if (equalName.isEmpty())
						System.out.println("Data Not Found.....!");
					else if (equalName.size() == 1) {
						addressBook.deleteContact(equalName.get(0));
						System.out.println("Contact data deleted....!");
					} else {
						equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
						System.out.println("Enter an index to delete");
						int index = sc.nextInt();
						sc.nextLine();
						addressBook.deleteContact(equalName.get(index));
						System.out.println("Contact data deleted....!");
					}
					break;
				case 4:
					/*
					 * call tostring method for showing details
					 */
					System.out.println(addressBook.toString());
					break;
				case 5:
					return;
				default:
					System.out.println("Invalid Choice!");
					break;
			}
		}
	}
	/**
	 * create a method name as searchByOptions
	 */
	public void searchByOptions() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1. By name");
		System.out.println("2. By city");
		System.out.println("3. By state");
		System.out.println("4. Back");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
			case 1:
				System.out.println("Enter name: ");
				String name = sc.nextLine();
				contactDetailsList.forEach(book -> searchByName(name).forEach(System.out::println));
				break;
			case 2:
				System.out.println("Enter city: ");
				String city = sc.nextLine();
				contactDetailsList.forEach(book -> searchByCity(city).forEach(System.out::println));
				break;
			case 3:
				System.out.println("Enter state: ");
				String state = sc.nextLine();
				contactDetailsList.forEach(book -> searchByState(state).forEach(System.out::println));
				break;
			case 4:
				return;
			default:
				System.out.println("INVALID CHOICE!");
		}
	}
	public void viewByOption(Map<String, AddressBookSystem> addressBookMap) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1. View By name");
		System.out.println("2. View By city");
		System.out.println("3. View By state");
		System.out.println("4. Back");
		System.out.print("Enter Your choice: ");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
			case 1:
				viewByName(nameHashMap);
				break;
			case 2:
				viewByCity(cityHashMap);
				break;
			case 3:
				viewByState(stateHashMap);
				break;
			case 4:
				return;
			default:
				System.out.println("INVALID CHOICE!");
		}
	}
	/*
	 * create a method name as countByOption
	 * this method to count element by option
	 */
	public void countByOption() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1. Count City ");
		System.out.println("2. Count State");
		System.out.println("3. Back ");
		System.out.println("Enter Your Choice : ");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
			case 1:
				Map<String, Long> countCity = contactDetailsList.stream()
						.collect(Collectors.groupingBy(e -> e.getCity(), Collectors.counting()));
				System.out.println(countCity + "\n");
				break;
			case 2:
				Map<String, Long> countState = contactDetailsList.stream()
						.collect(Collectors.groupingBy(e -> e.getState(), Collectors.counting()));
				System.out.println(countState + "\n");
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid Option");
		}
	}
}
