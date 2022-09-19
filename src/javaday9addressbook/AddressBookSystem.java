package javaday9addressbook;

import java.io.IOException;
import java.util.*;

public class AddressBookSystem {
    public static void main(String[] args) {
        AddressBook addressBookMain = new AddressBook();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Address Book System");
            System.out.println("1. Create new Address Book \n" +
                    "2. Edit existing Address Book \n" +
                    "3. Delete contact details of the address book\n" +
                    "4. Show all Address Books \n" +
                    "5. Show particular address book by its name\n" +
                    "6. Search Contact Details by city or state\n" +
                    "7. sort contact details \n" +
                    "8. Count contacts by city or state \n" +
                    "9. Sort option  \n" +
                    "10. Write data in file.\n" +
                    "11. Read data from file.\n" +
                    "12. Exit");
            System.out.print("Enter Your choice: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter how many Number of Address Book to be added:");
                    int noOfAddressBook = input.nextInt();
                    for (int i = 0; i < noOfAddressBook; i++) {
                        System.out.println("Enter the name of the Address Book");
                        input.nextLine();
                        String addressBookName = input.nextLine();
                        System.out.println(
                                "Enter the number of person's details to be added in address book: " + addressBookName);
                        int noOfPerson = input.nextInt();
                        List<Contact> phoneBook = new ArrayList<>();
                        addressBookMain.setAddressBook(phoneBook);
                        for (int j = 0; j < noOfPerson; j++) {
                            System.out.println("Enter the details of the Person");
                            Contact contact = AddressBook.addContact(input);
                            addressBookMain.addContact(contact);
                            String name = contact.getFirstName() + " " + contact.getLastName();
                            System.out.println("The details of the " + name + " is added to the Address Book: "
                                    + addressBookName + " successfully.");
                        }

                        List<Contact> addressBook = addressBookMain.getAddressBook();
                        AddressBook.addAddressBookToSystem(addressBookName, addressBook);
                        System.out.println("Address Book: " + addressBookName + " is successfully added to the system.");
                    }
                    break;
                case 2:
                    System.out.println("Enter the name of the address book of which contact details to be edited:");
                    input.nextLine();
                    String addressBookName = input.nextLine();
                    if (AddressBook.isPresentAddressBook(addressBookName)) {
                        System.out.println("Enter the first name of the person whose details to be edited:");
                        String personName = input.nextLine();
                        if (AddressBook.editContactDetails(addressBookName, personName, input))
                            System.out.println("The contact details of the " + personName + " from " + addressBookName
                                    + " is edited.");
                        else
                            System.out.println("Sorry, the contact details of the " + personName + " is not found in "
                                    + addressBookName);
                    } else
                        System.out.println("Sorry, the address book: " + addressBookName
                                + " is not found in the system.");
                    break;
                case 3:
                    System.out.println("Enter the name of the address book from which person's details to be deleted:");
                    String addressBookName1 = input.nextLine();
                    if (AddressBook.isPresentAddressBook(addressBookName1)) {
                        System.out.println("Enter the name of the person whose details to be deleted:");
                        String personName = input.nextLine();
                        if (AddressBook.deleteContactDetails(addressBookName1, personName))
                            System.out.println("The contact details of the " + personName + " from " + addressBookName1
                                    + " is deleted.");
                        else
                            System.out.println("Sorry, the contact details of the " + personName + " is not found in "
                                    + addressBookName1 + ". We can't proceed to delete.");
                    } else
                        System.out.println("Sorry, the address book: " + addressBookName1
                                + " is not found in the system. We can't proceed to delete.");
                    break;
                case 4:
                    AddressBook.showAddressBookSystem();
                    break;
                case 5:
                    System.out.println("Enter the name of the address book:");
                    input.nextLine();
                    String addressBookName2 = input.nextLine();
                    if (AddressBook.isPresentAddressBook(addressBookName2))
                        AddressBook.showAddressBook(addressBookName2);
                    else
                        System.out.println("Sorry, Address Book: " + addressBookName2 + " is not present in the system.");
                    break;
                case 6:
                    System.out.println("Enter the state/city name to search the contacts:");
                    input.nextLine();
                    String cityOrStateName = input.nextLine();
                    Map<String, List<String>> personsInCityOrState = AddressBook.searchPersonByCityOrState(cityOrStateName);
                    if (personsInCityOrState.size() == 0)
                        System.out.println("Sorry, there is no person in the " + cityOrStateName + ".");
                    else {
                        System.out.println("The list of persons in the " + cityOrStateName + ":");
                        System.out.println(personsInCityOrState);
                    }
                    break;
                case 7:
                    System.out.println("Welcome to the sorting data alphabetically");
                    AddressBook.sortByOption();
                    break;
                case 8:
                    System.out.println("Enter the state/city name to count the contacts:");
                    input.nextLine();
                    String cityOrState = input.nextLine();
                    Map<String, List<String>> personInCityOrState = AddressBook.searchPersonByCityOrState(cityOrState);
                    if (personInCityOrState.size() == 0)
                        System.out.println("Sorry, there is no person in the " + cityOrState + ".");
                    else {
                        AddressBook.countPersonByCityOrState(cityOrState);
                    }
                    break;
                case 9:
                    AddressBook.sortByOption();
                    break;
                case 10:
                    try {
                        AddressBookFileIO.writeToFile(addressBookMain.getAddressBookSystem());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 11:
                    try {
                        AddressBookFileIO.readFromFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 12:
                    input.close();// for closing the program
                    return;
                default:
                    System.out.println("You Entered Invalid Choice....!");
                    break;
            }
        }
    }
}
