package javaday9addressbook;

import java.util.*;
import java.util.stream.Collectors;

class AddressBookSystem {
    //	create ArrayList object to store the contact details.
    static Scanner sc = new Scanner(System.in);
    static List<Contacts> contactList = new ArrayList<>();

    public static Map<String, List<String>> viewByCity(String city) {
        Map<String, List<String>> cityMap = new HashMap<>();
        for (Map.Entry<String, List<Contacts>> me : AddressBook.addressBooks.entrySet()) {
            List<String> contactInCiy = me.getValue().stream().filter(contactsList -> contactsList.getCity().equals(city)).map(cp -> cp.getFirstName() + " " + cp.getLastName()).collect(Collectors.toList());
            cityMap.put(me.getKey(), contactInCiy);
        }
        return cityMap;
    }

    public static Map<String, List<String>> viewByState(String state) {
        Map<String, List<String>> stateMap = new HashMap<>();
        for (Map.Entry<String, List<Contacts>> me : AddressBook.addressBooks.entrySet()) {
            List<String> contactInCiy = me.getValue().stream().filter(contactPerson -> contactPerson.getCity().equals(state)).map(cp -> cp.getFirstName() + " " + cp.getLastName()).collect(Collectors.toList());
            stateMap.put(me.getKey(), contactInCiy);
        }
        return stateMap;
    }
    public static void sortByName() {
        AddressBook.addressBooks.keySet().forEach(key -> AddressBook.addressBooks.get(key).stream().sorted(Comparator.comparing(Contacts::getFirstName)).forEach(System.out::println));
    }
    public static void sortByCity() {
        AddressBook.addressBooks.keySet().forEach(key -> AddressBook.addressBooks.get(key).stream().sorted(Comparator.comparing(Contacts::getCity)).forEach(System.out::println));
    }

    public static void sortByState() {
        AddressBook.addressBooks.keySet().forEach(key -> AddressBook.addressBooks.get(key).stream().sorted(Comparator.comparing(Contacts::getState)).forEach(System.out::println));
    }

    public static void sortByZip() {
        AddressBook.addressBooks.keySet().forEach(key -> AddressBook.addressBooks.get(key).stream().sorted(Comparator.comparing(Contacts::getZip)).forEach(System.out::println));
    }

    private static int isNamePresent(String name, List<Contacts> contactList) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getFirstName().equals(name))
                return i;
        }
        return -1;
    }

    public static List<Contacts> addContact() {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter First Name to add contact:");
        String firstName = sc1.next();
        int list = isNamePresent(firstName, contactList);
        if (list == -1) {
            System.out.println("Enter Last Name: ");
            String lastName = sc.nextLine();
            System.out.println("Enter Address: ");
            String address = sc.nextLine();
            System.out.println("Enter City: ");
            String city = sc.nextLine();
            System.out.println("Enter State: ");
            String state = sc.nextLine();
            System.out.println("Enter Zip Code: ");
            int zip = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Phone Number: ");
            long phoneNumber = sc.nextLong();
            sc.nextLine();
            System.out.println("Enter Email ID: ");
            String email = sc.nextLine();
            Contacts contactInfo = new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email);
            contactList.add(contactInfo);
            contactList = contactList.stream().sorted(Comparator.comparing(Contacts::getFirstName)).collect(Collectors.toList());
        } else {
            System.out.println("contact already exists");
        }
        return contactList;
    }

    public static void editContact() {
        System.out.println("Enter the First name to update Contact details : ");
        String editName = sc.next();
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getFirstName().equals(editName)) {
                System.out.println("Select from below to change: ");
                System.out.println(
                        "\n1.First Name\n2.Last Name\n3.Address\n4.city\n5.State\n6.Email\n7.Zip\n8.Phone number");
                int select = sc.nextInt();
                switch (select) {
                    case 1:
                        System.out.println("Enter first name");
                        contactList.get(i).setFirstName(sc.next());
                        break;
                    case 2:
                        System.out.println("Enter Last name");
                        contactList.get(i).setLastName(sc.next());
                        break;
                    case 3:
                        System.out.println("Enter address");
                        contactList.get(i).setAddress(sc.next());
                        break;
                    case 4:
                        System.out.println("Enter city");
                        contactList.get(i).setCity(sc.next());
                        break;
                    case 5:
                        System.out.println("Enter state");
                        contactList.get(i).setState(sc.next());
                        break;
                    case 6:
                        System.out.println("Enter email");
                        contactList.get(i).setEmail(sc.next());
                        break;
                    case 7:
                        System.out.println("Enter Zip");
                        contactList.get(i).setZip(sc.nextInt());
                        break;
                    case 8:
                        System.out.println("Enter phone number");
                        contactList.get(i).setPhoneNo(sc.nextLong());
                        break;
                }
                System.out.println("Edited contact list is: ");
                System.out.println(contactList);
            } else
                System.out.println("Enter valid First name");
        }
    }

    public static void deleteContact() {
        System.out.println("Confirm the first name of the person to delete the contact");
        String name2 = sc.next();
        int list2 = isNamePresent(name2, contactList);
        if (list2 == -1)
            System.out.println("contact not found");
        else {
            contactList.remove(list2);
            System.out.println("Deleted Successfully");
        }
    }

    public static void showContact() {
        System.out.println("Confirm the first name of the person to display the contact");
        String name3 = sc.next();
        int list2 = isNamePresent(name3, contactList);
        if (list2 == -1) {
            System.out.println("All contact -> " + contactList.size());
            System.out.println(contactList);
        } else {
            System.out.println("Contact not found");
        }
    }
    public static void createAddressBook(Map<String, List<Contacts>> addressBooks) {
        System.out.println("Enter Name of new Address Book: ");
        String bookName = sc.next();
        if (addressBooks.containsKey(bookName))
            System.out.println("Book already exists");
        else {
            Scanner sc1 = new Scanner(System.in);
            while (true) {
                System.out.println("\n-------------------------- Address Book Contact Option --------------------------");
                System.out.println("1. Add contact details");
                System.out.println("2. Edit contact details");
                System.out.println("3. Delete contact details");
                System.out.println("4. Show contact details");
                System.out.println("5. Back to main menu");
                System.out.print("Enter Your choice: ");
                int choice = sc1.nextInt();
                switch (choice) {
                    case 1:
                        addressBooks.put(bookName, contactList);
                        break;
                    case 2:
                        AddressBookSystem.editContact();
                        break;
                    case 3:
                        AddressBookSystem.deleteContact();
                        break;
                    case 4:
                        AddressBookSystem.showContact();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid Choice!");
                        break;
                }
            }
        }
    }
    public static void editAddressBook(Map<String, List<Contacts>> addressBooks){
        System.out.println(addressBooks.keySet());
        System.out.println("Enter Name of Address Book: ");
        String bookName = sc.next();
        List<Contacts> contactList = new ArrayList<>();
        if (addressBooks.containsKey(bookName))
            while (true) {
                System.out.println("\n-------------------------- Address Book Contact Option --------------------------");
                System.out.println("1. Add contact details");
                System.out.println("2. Edit contact details");
                System.out.println("3. Delete contact details");
                System.out.println("4. Show contacts details");
                System.out.println("5. Back to main menu");
                System.out.print("Enter Your choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        List<Contacts> multipleContacts = addContact();
                        addressBooks.put(bookName, multipleContacts);

                        break;
                    case 2:
                        AddressBookSystem.editContact();
                        break;
                    case 3:
                        AddressBookSystem.deleteContact();
                        break;
                    case 4:
                        AddressBookSystem.showContact();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid Choice!");
                        break;
                }
            }else
            System.out.println("Book not found");
    }
    public static void searchByOptions() {
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
                AddressBook.addressBooks.keySet().forEach(key -> AddressBook.addressBooks.get(key).stream().filter(contactInfo -> name.equalsIgnoreCase(contactInfo.getFirstName())).forEach(System.out::println));
                break;
            case 2:
                System.out.println("Enter city: ");
                String city = sc.nextLine();
                AddressBook.addressBooks.keySet().forEach(key -> AddressBook.addressBooks.get(key).stream().filter(contactInfo -> city.equalsIgnoreCase(contactInfo.getCity())).forEach(System.out::println));
                break;
            case 3:
                System.out.println("Enter state: ");
                String state = sc.nextLine();
                AddressBook.addressBooks.keySet().forEach(key -> AddressBook.addressBooks.get(key).stream().filter(contactInfo -> state.equalsIgnoreCase(contactInfo.getState())).forEach(System.out::println));
                break;
            case 4:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }
    }

    public static void viewByOption(Map<String, List<Contacts>> addressBooks) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. View By city");
        System.out.println("2. View By state");
        System.out.println("3. Back");
        System.out.print("Enter Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Enter the city name to view the persons:");
                sc.nextLine();
                String city = sc.nextLine();
                Map<String, List<String>> cityMap = AddressBookSystem.viewByCity(city);
                if (cityMap.size() == 0)
                    System.out.println("Sorry, there is no any details.");
                break;
            case 2:
                System.out.println("Enter the state name to view the persons:");
                sc.nextLine();
                String state = sc.nextLine();
                Map<String, List<String>> stateMap = AddressBookSystem.viewByState(state);
                if (stateMap.size() == 0)
                    System.out.println("Sorry, there is no any details.");
                break;
            case 3:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }
    }

    /*
     * create a method name as countByOption
     * this method to count element by option
     */
    public static void countByOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Count City ");
        System.out.println("2. Count State");
        System.out.println("3. Back ");
        System.out.println("Enter Your Choice : ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                Map<String, Long> countCity = contactList.stream()
                        .collect(Collectors.groupingBy(e -> e.getCity(), Collectors.counting()));
                System.out.println(countCity + "\n");
                break;
            case 2:
                Map<String, Long> countState = contactList.stream()
                        .collect(Collectors.groupingBy(e -> e.getState(), Collectors.counting()));
                System.out.println(countState + "\n");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    public static void sortByOption() {
        System.out.println("1. By first name");
        System.out.println("2. By city");
        System.out.println("3. By state");
        System.out.println("4. By zip");
        System.out.println("5. Back");
        System.out.print("Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                AddressBookSystem.sortByName();
                break;
            case 2:
                AddressBookSystem.sortByCity();
                break;
            case 3:
                AddressBookSystem.sortByState();
                break;
            case 4:
                AddressBookSystem.sortByZip();
                break;
            case 5:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }
    }
}
