package letterBatchOO;

public class ProductList extends Letter{

	private String productName;
	private Double price;
	
	public ProductList(String type, String companyName, String name, Double price) {
		super(type, companyName);
		this.productName = name;
		this.price = price;
		// TODO Auto-generated constructor stub
	}

	public String isValid() {
		// TODO Auto-generated method stub
		return null;
	}

}
