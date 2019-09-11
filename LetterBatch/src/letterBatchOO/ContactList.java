package letterBatchOO;

public class ContactList extends Letter{

	private String contactName;
	private String contactNumber;
	
	public ContactList(String type, String companyName, String contactName, String contactNumber) {
		super(type, companyName);
		this.contactName = contactName;
		this.contactNumber = contactNumber;
		// TODO Auto-generated constructor stub
		
	}

	public String isValid() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
