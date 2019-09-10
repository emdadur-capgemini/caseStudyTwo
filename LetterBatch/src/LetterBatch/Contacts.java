package LetterBatch;

public class Contacts {

	private String contactName;
	private String contactNumber;
	private String companyName;

	public Contacts(String name, String number) {
		
		this.contactName = name;
		this.contactNumber = number;
	}

	public Contacts(String compName, String name, String number) {
		
		this.companyName = compName;
		this.contactName = name;
		this.contactNumber = number;
	}
 
	public String getCompanyName() {
		return companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}
}
