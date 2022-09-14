package javaday9addressbook;
import java.util.*;

public class AddressBook {
    static Map<String, List<Contacts>> addressBooks = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//          create object for  AddressBookSystem class the object name as addressBook
        while (true) {
            System.out.println("\nWelcome to Address Book System");
            System.out.println("1. Create new Address Book \n" +
                    "2. Edit existing Address Book \n" +
                    "3. Show Address Books \n" +
                    "4. Search Contact Details  \n" +
                    "5. sort contact details \n" +
                    "6. Count contacts \n" +
                    "7. Sort contacts \n" +
                    "8. Write data in file.\n" +
                    "9. Read data from file.\n" +
                    "10. Exit");
            System.out.print("Enter Your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    AddressBookSystem.createAddressBook(addressBooks);
                    break;
                case 2:
                    AddressBookSystem.editAddressBook(addressBooks);
                    break;
                case 3:
                    int i = 1;
                    for (String book : addressBooks.keySet()) {
                        System.out.println(i + ". " + book);
                        i++;
                    }
                    break;
                case 4:
                    System.out.println("Welcome to the search option:");
                    AddressBookSystem.searchByOptions();
                    break;
                case 5:
                    System.out.println("Welcome to view By Option:");
                    AddressBookSystem.viewByOption(addressBooks);
                    break;
                case 6:
                    System.out.println("Welcome to the counter");
                    AddressBookSystem.countByOption();
                    break;
                case 7:
                    System.out.println("Welcome to the sorting data alphabetically");
                    AddressBookSystem.sortByOption();
                    break;
                case 8:
                    AddressBookFileIO.writeData(addressBooks);
                    break;
                case 9:
                    AddressBookFileIO.readData();
                    break;
                case 10:
                    sc.close();// for closing the program
                    return;
                default:
                    System.out.println("You Entered Invalid Choice....!");
                    break;
            }
        }
    }
}
