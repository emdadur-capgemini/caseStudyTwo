package letterBatchOO;

public class DiscountLetter extends Letter implements LetterInterface{

	private String contactName;
	private double discount = 0;
	
	
	public DiscountLetter(String type, String companyName, String contName, double disc) {
		super(type, companyName);
		this.contactName = contName;
		this.discount = disc;
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


}
