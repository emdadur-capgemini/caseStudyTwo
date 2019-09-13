package letterBatchOO;

public class ContactList extends Letter {

	private String contactName;
	private String contactNumber;

	public String getContactName() {
		return contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public ContactList(int line, String type, String companyName, String contactName, String contactNumber) {
		super(line, type, companyName);
		this.contactName = contactName;
		this.contactNumber = contactNumber;

	}

	public String isValid() {

		if (checkCompanyName() == false)
			super.errorMsg = super.errorMsg.concat("Company name, ");
		if (contactName.length() < 1 || contactName.length() > 20)
			super.errorMsg = super.errorMsg.concat("Contact Name, ");
		if (!contactNumber.matches(
				"(\\(?\\+44\\)?\\s?(1|2|3|7|8)\\d{3}|\\(?(01|02|03|07|08)\\d{3}\\)?)\\s?\\d{3}\\s?\\d{3}|(\\(?\\+44\\)?\\s?(1|2|3|5|7|8)\\d{2}|\\(?(01|02|03|05|07|08)\\d{2}\\)?)\\s?\\d{3}\\s?\\d{4}|(\\(?\\+44\\)?\\s?(5|9)\\d{2}|\\(?(05|09)\\d{2}\\)?)\\s?\\d{3}\\s?\\d{3}"))
			super.errorMsg = super.errorMsg.concat("Contact Number, ");

		if (super.errorMsg.equals(""))
			return "";
		else {
			super.errorMsg = "\nline " + super.lineCount + ": error with " + super.errorMsg;
			return super.errorMsg;
		}
	}

}
