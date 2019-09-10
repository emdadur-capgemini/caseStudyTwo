package LetterBatch;

import java.util.ArrayList;

public class Confirmation {
	private String companyName;
	private String address;
	private String postcode;
	private String contactName;
	private ArrayList<String> contactNameA = new ArrayList<String>();
	private ArrayList<String> contactNumberA = new ArrayList<String>();
	private ArrayList<Contacts> contactList = new ArrayList<Contacts>();
	
	
	public Confirmation(String companyName, String address, String postcode, String contactName) {
		
		this.companyName = companyName;
		this.address = address;
		this.postcode = postcode;
		this.contactName = contactName;
		//System.out.println(this.companyName + this.address + this.postcode + this.contactName);
	}
	
	public String getAddress() {
		return address;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getContactName() {
		return contactName;
	}

	public ArrayList<String> getContactNameList() {
		return contactNameA;
	}

	public ArrayList<String> getContactNumberList() {
		return contactNumberA;
	}

	public String getCompanyName() {
		
		return companyName;
	}
	
	public ArrayList<Contacts> getContactList() {
		return contactList;
	}

	public void addContact(String name, String number) {
		
		contactNameA.add(name);
		contactNumberA.add(number);
		Contacts cont = new Contacts(name, number);
		contactList.add(cont);
		
	}
	
	
	
	
	
	
}
