package LetterBatch;

public class Contacts {

	private String contactName;
	private String contactNumber;
	
	public Contacts(String name, String number) {
		
		this.contactName = name;
		this.contactNumber = number;
	}
 
	public String getContactName() {
		return contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}
}
