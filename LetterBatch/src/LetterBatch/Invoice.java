package LetterBatch;

import java.util.ArrayList;

public class Invoice {
	String companyName;
	String contactName;
	ArrayList<String> productNameList = new ArrayList<String>();
	ArrayList<Double> priceList = new ArrayList<Double>();
	

	public Invoice(String compName, String contName) {
		
		this.companyName = compName;
		this.contactName = contName;
		
	}

	public void addProduct(String name, Double price){
		
		this.productNameList.add(name);
		this.priceList.add(price);
		
	}

	public String getCompanyName() {
		return companyName;
	}


	public String getContactName() {
		return contactName;
	}


	public ArrayList<String> getProductNameList() {
		return productNameList;
	}


	public ArrayList<Double> getPriceList() {
		return priceList;
	}
	
	
	
}
