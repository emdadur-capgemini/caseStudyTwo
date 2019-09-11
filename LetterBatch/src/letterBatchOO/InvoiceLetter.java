package letterBatchOO;

import java.util.ArrayList;


public class InvoiceLetter extends Letter implements LetterInterface{

	String contactName;
	ArrayList<ProductList> productList = new ArrayList<>();
	
	public InvoiceLetter(String type, String companyName, String contName) {
		super(type, companyName);
		this.contactName = contName;
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

	public ArrayList<ProductList> getProductList() {
		return productList;
	}

	public void addProduct(String name, Double price){
		
		ProductList prod = new ProductList(super.getType(),super.getCompanyName(),name, price);
		productList.add(prod);
		
	}


}
