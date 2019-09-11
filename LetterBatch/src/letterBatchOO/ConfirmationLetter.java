package letterBatchOO;

import java.util.ArrayList;

import LetterBatch.Contacts;

public class ConfirmationLetter extends Letter implements LetterInterface{
	
	private String address;
	private String postcode;
	private String contactName;
	private ArrayList<ContactList> contactListArray = new ArrayList<>();
	
	public ConfirmationLetter(String type, String companyName, String address, String postcode, String contactName) {
		super(type, companyName);
		this.address = address;
		this.postcode = postcode;
		this.contactName = contactName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateLetter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String isValid() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ContactList> getContactList() {
		return contactListArray;
	}

	public void addContact(String name, String number) {
		
		ContactList cont = new ContactList(super.getType(),super.getCompanyName(), name, number);
		contactListArray.add(cont);
		
	}

	
	
	
}
