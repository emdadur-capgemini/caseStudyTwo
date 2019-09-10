package LetterBatch;

public class Discount {
	
	private String companyName;
	private String contactName;
	private double discount = 0;
	
	public Discount(String compName, String contName, double disc) {
		
		this.companyName = compName;
		this.contactName = contName;
		this.discount = disc;
		
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public double getDiscount() {
		return discount;
	}

}
