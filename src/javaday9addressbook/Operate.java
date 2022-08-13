package javaday9addressbook;

import java.util.Scanner;

class Operate {
	public void editContact(AddressBook contact) {

		String address;
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter the First name for update Address : ");
		String editName = sc.nextLine();
		if (editName.equals(contact.getFirstName())) {
			System.out.print("\nEnter your new Address : ");
			address = sc.nextLine();
			contact.setAddress(address);
		} else {
			System.out.print("\ncouldn't find the contact..");
		}
		sc.close();
	}
	public AddressBook deleteContact(AddressBook contact) {

		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter the First name for delete : ");
		String deleteName = sc.nextLine();
		if (deleteName.equals(contact.getFirstName())) {
			AddressBook detail = new AddressBook();
			System.out.print("\nYour contact deleted..");
			return detail;
		} else {
			System.out.print("\ncouldn't find the contact..");
		}
		sc.close();
		return contact;
	}

	public void showContact(AddressBook contact) {

		System.out.print("\n-----------------");
		System.out.print("\nFirst Name  : " + contact.getFirstName());
		System.out.print("\nLast Name   : " + contact.getLastName());
		System.out.print("\nAddress     : " + contact.getAddress());
		System.out.print("\nCity        : " + contact.getCity());
		System.out.print("\nState       : " + contact.getState());
		System.out.print("\nPone Number : " + contact.getPhoneNo());
		System.out.print("\nE-mail      : " + contact.getEmail());
		System.out.print("\nZip         : " + contact.getZip());
	}
}