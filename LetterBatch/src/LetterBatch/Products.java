package LetterBatch;

public class Products {

	private String companyName;
	private String productName;
	private Double price;
	
	public Products(String name, Double price) {
		
		this.productName = name;
		this.price = price;
	}
	public Products(String companyName, String name, Double price) {
		
		this.companyName = companyName;
		this.productName = name;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public Double getPrice() {
		return price;
	}
}
