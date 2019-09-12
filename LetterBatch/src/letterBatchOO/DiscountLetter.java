package letterBatchOO;

import java.io.File;
import java.io.IOException;

public class DiscountLetter extends Letter implements LetterInterface{

	private String contactName;
	private double discount = 0;
	
	
	public DiscountLetter(int line, String type, String companyName, String contName, double disc) {
		super(line, type, companyName);
		this.contactName = contName;
		this.discount = disc;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateLetter() {
		

		
		File source = new File("/home/regen/git/caseStudyTwo/LetterBatch/resources/discount.txt");
        dest = new File("/home/regen/git/caseStudyTwo/LetterBatch/resources/output/"+super.companyName+".txt");
        try {
			copyFileUsingStream(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not found!");
		}
        
        
		replaceSelected("<<system.today>>", ("\t  "+letterDate));
		replaceSelected("<<letterName>>", this.contactName);
		
		String discountAsString = Double.toString(this.discount);

		replaceSelected("<<discount>>", discountAsString);
		// TODO Auto-generated method stub
		
	}

	@Override
	public String isValid() {
		
		if (checkCompanyName() == false)
			super.errorMsg = super.errorMsg.concat("company name");

		if (checkLetterContactName(contactName) == false)
			super.errorMsg = super.errorMsg.concat("Letter Contact Name");

		if (discount <1 || discount>50)
			super.errorMsg = super.errorMsg.concat("Discount Rate");

		
		if (super.errorMsg.equals(""))
			return "";
			
		else {
			super.errorMsg = "\nline " + super.lineCount + ": error with " + super.errorMsg;
			System.out.println(super.errorMsg);
			return super.errorMsg;
		}

	}


}
