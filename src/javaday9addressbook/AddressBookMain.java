package javaday9addressbook;
import java.util.*;

class AddressBook {
	 String firstName, lastName, address, city, state,email;
	    long phoneNo;
	    int zip;
	    public void setDetails(){
	        firstName = "Javul";
	        lastName = "Mulla";
	        address = "Raibag";
	        city ="Belagavi";
	        state = "Karnataka";
	        zip = 123456;
			phoneNo = 987654321;
	        email = "javulmulla8@gmail.com";
	    }
	    public void printDetails(){

	        System.out.println("\nFirst Nmae : " + firstName  + "\n\nLast Name : " + lastName + "\n\nAddress : " + address + "\n\nCity : " + city + "\n\nState : " + state + "\n\nZip : " + zip + "\n\nPhone Number : " + phoneNo + "\n\nE-mail : " + email); 
	    }
}
public class AddressBookMain {
public static void main(String[] args) {
	System.out.println("Welcome to AddressBook System");
	AddressBook address = new AddressBook();
    address.setDetails();
    address.printDetails();
}
}
