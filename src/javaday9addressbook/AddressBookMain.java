package javaday9addressbook;
import java.util.*;
public class AddressBookMain {
public static void main(String[] args) {
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

     sc.close();

     System.out.print("\nFirst Name  : " + contact.getFirstName());
     System.out.print("\n\nLast Name   : " + contact.getLastName());
     System.out.print("\n\nAddress     : " + contact.getAddress());
     System.out.print("\n\nCity        : " + contact.getCity());
     System.out.print("\n\nState       : " + contact.getState());
     System.out.print("\n\nPhone Number : " + contact.getPhoneNo());
     System.out.print("\n\nE-mail      : " + contact.getEmail());
     System.out.print("\n\nZip         : " + contact.getZip());
}
}
