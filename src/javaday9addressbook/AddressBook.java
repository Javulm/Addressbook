package javaday9addressbook;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AddressBook {
    private List<Contact> addressBook;

    private static Map<String, List<Contact>> addressBookSystem = new HashMap<>();

    public Map<String, List<Contact>> getAddressBookSystem() {
        return addressBookSystem;
    }

    public List<Contact> getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(List<Contact> addressBook) {
        this.addressBook = addressBook;
    }

    public void addContact(Contact contact) {
        addressBook.add(contact);
    }

    public static void addAddressBookToSystem(String addressBookName, List<Contact> addressBook) {
        addressBookSystem.put(addressBookName, addressBook);
    }

    public static boolean isPresentAddressBook(String addressBookName) {
        Predicate<String> isPresent = x -> x.equals(addressBookName);
        List<String> nameList = addressBookSystem.keySet().stream().filter(isPresent).collect(Collectors.toList());
        return nameList.size() != 0;
    }

    public static Contact addContact(Scanner input) {
        System.out.println("Enter First Name: ");
        String firstName = input.next();
        System.out.println("Enter Last Name: ");
        String lastName = input.next();
        System.out.println("Enter Address: ");
        String address = input.next();
        System.out.println("Enter City: ");
        String city = input.next();
        System.out.println("Enter State: ");
        String state = input.next();
        System.out.println("Enter Zip Code: ");
        int zip = input.nextInt();
        input.nextLine();
        System.out.println("Enter Phone Number: ");
        long phoneNumber = input.nextLong();
        input.nextLine();
        System.out.println("Enter Email ID: ");
        String email = input.nextLine();
        return new Contact(firstName, lastName, address, city, state, zip, email, phoneNumber);
    }
    public static boolean editContactDetails(String addressBookName, String personName, Scanner input) {
        for (Map.Entry<String, List<Contact>> mapEntries : addressBookSystem.entrySet()) {
            String adBookName = mapEntries.getKey();
            List<Contact> addressBook = mapEntries.getValue();
            if (adBookName.equals(addressBookName)) {
                for (Contact contact : addressBook) {
                    String name = contact.getFirstName() + " " + contact.getLastName();
                    if (name.equals(personName)) {
                        addressBook.remove(contact);
                        Contact contact1 = addContact(input);
                        addressBook.add(contact1);
                        addAddressBookToSystem(addressBookName, addressBook);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean deleteContactDetails(String addressBookName, String personName) {
        for (Map.Entry<String, List<Contact>> mapEntries : addressBookSystem.entrySet()) {
            String adBookName = mapEntries.getKey();
            List<Contact> addressBook = mapEntries.getValue();
            if (adBookName.equals(addressBookName)) {
                for (Contact contact : addressBook) {
                    String name = contact.getFirstName() + " " + contact.getLastName();
                    if (name.equals(personName)) {
                        addressBook.remove(contact);
                        addAddressBookToSystem(addressBookName, addressBook);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void showAddressBookSystem() {
        sortedByName();
        if (addressBookSystem.size() == 0)
            System.out.println("There is no address book in the system.");
        else {
            addressBookSystem.entrySet().stream().forEach(book -> {
                System.out.println("The contact details of the " + book.getKey() + ":");
                if (book.getValue().size() == 0)
                    System.out.println("Sorry, there is no contact in the " + book.getKey() + ".");
                else
                    book.getValue().stream().forEach(System.out::println);
            });
        }
    }
    public static void showAddressBook(String addressBookName) {
        addressBookSystem.entrySet().stream().forEach(book -> {
            if (book.getKey().equals(addressBookName)) {
                if (book.getValue().size() == 0)
                    System.out.println("Sorry, there is no contact details in the " + addressBookName + ".");
                else {
                    System.out.println("The contact details of the " + addressBookName + ":");
                    book.getValue().stream().forEach(System.out::println);
                }
            }
        });
    }
    public static Map<String, List<String>> searchPersonByCityOrState(String cityOrState) {
        Map<String, List<String>> personsInCityOrState = new HashMap<>();
        for (Map.Entry<String, List<Contact>> book : addressBookSystem.entrySet()) {
            List<String> contactList = book.getValue().stream()
                    .filter(contact -> contact.getCity().equals(cityOrState)
                            || contact.getState().equals(cityOrState))
                    .map(contact -> contact.getFirstName() + " " + contact.getLastName())
                    .collect(Collectors.toList());
            personsInCityOrState.put(book.getKey(), contactList);
        }
        return personsInCityOrState;
    }

    public static void sortByOption() {
        Scanner input = new Scanner(System.in);
        System.out.println("1. By first name");
        System.out.println("2. By city");
        System.out.println("3. By state");
        System.out.println("4. By zip");
        System.out.println("5. Back");
        System.out.print("Your choice: ");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                Map<String, List<Contact>> personNameSortedMap = sortedByPersonName();
                if (personNameSortedMap.size() == 0)
                    System.out.println("Sorry, there is no any details.");
                else {
                    System.out.println("The address books are sorted by first name:");
                    printSortedMap(personNameSortedMap);
                }
                break;
            case 2:
                Map<String, List<Contact>> personCitySortedMap = sortedByCity();
                if (personCitySortedMap.size() == 0)
                    System.out.println("Sorry, there is no any details.");
                else {
                    System.out.println("The address books are sorted by city:");
                    printSortedMap(personCitySortedMap);
                }
                break;
            case 3:
                Map<String, List<Contact>> personStateSortedMap = sortedByState();
                if (personStateSortedMap.size() == 0)
                    System.out.println("Sorry, there is no any details.");
                else {
                    System.out.println("The address books are sorted by State:");
                    printSortedMap(personStateSortedMap);
                }
                break;
            case 4:
                Map<String, List<Contact>> personZipSortedMap = sortedByZip();
                if (personZipSortedMap.size() == 0)
                    System.out.println("Sorry, there is no any details.");
                else {
                    System.out.println("The address books are sorted by Zip:");
                    printSortedMap(personZipSortedMap);
                }
                break;
            case 5:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }
    }
    public static void sortedByName() {
        Map<String, List<Contact>> personNameSortedMap = new HashMap<>();
        for (Map.Entry<String, List<Contact>> book : addressBookSystem.entrySet()) {
            List<Contact> personsInCity = book.getValue().stream().sorted(Comparator.comparing(Contact::getFirstName))
                    .collect(Collectors.toList());
            personNameSortedMap.put(book.getKey(), personsInCity);
        }
        addressBookSystem = personNameSortedMap;
    }
    public static Map<String, List<Contact>> sortedByPersonName() {
        Map<String, List<Contact>> personNameSortedMap = new HashMap<>();
        for (Map.Entry<String, List<Contact>> me : addressBookSystem.entrySet()) {
            List<Contact> personsName = me.getValue().stream()
                    .sorted(Comparator.comparing(Contact::getFirstName)).collect(Collectors.toList());
            personNameSortedMap.put(me.getKey(), personsName);
        }
        return personNameSortedMap;
    }
    public static Map<String, List<Contact>> sortedByCity() {
        Map<String, List<Contact>> personCitySortedMap = new HashMap<>();
        for (Map.Entry<String, List<Contact>> book : addressBookSystem.entrySet()) {
            List<Contact> personsInCity = book.getValue().stream()
                    .sorted(Comparator.comparing(Contact::getCity)).collect(Collectors.toList());
            personCitySortedMap.put(book.getKey(), personsInCity);
        }
        return personCitySortedMap;
    }
    public static Map<String, List<Contact>> sortedByState() {
        Map<String, List<Contact>> personStateSortedMap = new HashMap<>();
        for (Map.Entry<String, List<Contact>> book : addressBookSystem.entrySet()) {
            List<Contact> personsInState = book.getValue().stream()
                    .sorted(Comparator.comparing(Contact::getState)).collect(Collectors.toList());
            personStateSortedMap.put(book.getKey(), personsInState);
        }
        return personStateSortedMap;
    }
    public static Map<String, List<Contact>> sortedByZip() {
        Map<String, List<Contact>> personZipSortedMap = new HashMap<>();
        for (Map.Entry<String, List<Contact>> book : addressBookSystem.entrySet()) {
            List<Contact> personsInZip = book.getValue().stream().sorted(Comparator.comparingInt(Contact::getZip))
                    .collect(Collectors.toList());
            personZipSortedMap.put(book.getKey(), personsInZip);
        }
        return personZipSortedMap;
    }
    public static void printSortedMap(Map<String, List<Contact>> sortedMap) {
        if (sortedMap.size() == 0)
            System.out.println("There is no address book in the system.");
        else {
            sortedMap.entrySet().stream().forEach(me -> {
                System.out.println("The contact details of the " + me.getKey() + ":");
                if (me.getValue().size() == 0)
                    System.out.println("Sorry, there is no contact in the " + me.getKey() + ".");
                else
                    me.getValue().stream().forEach(System.out::println);
            });
        }
    }
    public static void countPersonByCityOrState(String cityOrState) {
        Map<String, List<String>> personCityStateMap = searchPersonByCityOrState(cityOrState);
        personCityStateMap.entrySet().stream().forEach(me -> {
            System.out.println("The number of persons reside in the " + cityOrState + " is " + me.getValue().size()
                    + ", as given in the " + me.getKey() + ".");
        });
        int count = personCityStateMap.values().stream().map(List::size).reduce(0, Integer::sum);
        System.out.println("There are total " + count + " persons in the " + cityOrState + ".");
    }

}