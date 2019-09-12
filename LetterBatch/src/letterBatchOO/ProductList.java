package letterBatchOO;

public class ProductList extends Letter{

	private String productName;
	public String getProductName() {
		return productName;
	}

	public Double getPrice() {
		return price;
	}

	private Double price;
	
	public ProductList(int line, String type, String companyName, String name, Double price) {
		super(line, type, companyName);
		this.productName = name;
		this.price = price;
		// TODO Auto-generated constructor stub
	}

	public String isValid() {
		
		if (checkCompanyName() == false)
			super.errorMsg = super.errorMsg.concat("company name ");
		
		if (productName.length()<3 && productName.length()>50)
			super.errorMsg = super.errorMsg.concat("product name ");
		
		//if (price <0) {
		if ((price <0)||(price*100 != (int)(price*100)))
			super.errorMsg = super.errorMsg.concat("price ");
			
				
		

		if (super.errorMsg.equals("")) {
			return "";
			}
		else {
			super.errorMsg = "\nline " + super.lineCount + ": error with " + super.errorMsg;
			return super.errorMsg;
		}
	}

}
