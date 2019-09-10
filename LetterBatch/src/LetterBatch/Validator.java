package LetterBatch;

public class Validator {


	public static String ValRegistration (String companyName, String address, String postCode, String letterContactNme){
		String error = "Error with ";
		if (CompanyName(companyName) == false)
			error = error+"company name";

		if (!((address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)"))||((address.matches("\\d+-([a-zA-Z]+|[a-zA-Z]+-[a-zA-Z]+)")))))
			error = error +"Address";

		if (!(postCode.matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$")|| (postCode.matches("^[a-z]{1,2}[0-9R][0-9a-z]? [0-9][abd-hjlnp-uw-z]{2}$"))))
			error = error +"Post Code";

		if (LetterContactName(letterContactNme) == false)
			error = error +"Letter contact name";

		if (error.equals("Error with ")) {
			return "Correct";}
		else return error;


	}


	public static String ValCompDetails (String companyName, String contactName, String contactNumber){

		String error = "Error with ";
		if (CompanyName(companyName) == false)
			error = error+"company name";
		if (contactName.length()<1 || contactName.length()>20)
			error = error +"Contact Name";
		if (contactNumber.matches("^[0-9 ]+") && contactNumber.length()>14)
			error = error +"Contact Number";

		if (error.equals("Error with ")) {
			return "Correct";}
		else return error;

	}

	public static String ValDiscountRate (String companyName, String letterContactNme, double discountRate){

		String error = "Error with ";

		if (CompanyName(companyName) == false)
			error = error+"company name";

		if (LetterContactName(letterContactNme) == false)
			error = error +"Letter Contact Name";

		if (discountRate <1 && discountRate>50)
			error = error +"Discount Rate";

		if (error.equals("Error with ")) {
			return "Correct";}
		else return error;


	}

	public static String ValInvoiceHeader (String companyName, String letterContactNme){
		
		String error = "Error with ";

		if (CompanyName(companyName) == false)
			error = error+"company name ";

		if (LetterContactName(letterContactNme) == false)
			error = error +"Letter Contact Name";
		
		if (error.equals("Error with ")) {
			return "Correct";}
		else return error;
		
	}

	public static String ValInvoice (String companyName, String productName, double price){

		String error = "Error with ";

		if (CompanyName(companyName) == false)
			error = error+"company name ";
		
		if (productName.length()<3 && productName.length()>50)
			error = error+"product name ";
		
		//if (price <0) {
		if ((price <0)||(price*100 != (int)(price*100)))
				error = error+"price ";
			
				
		
		
		if (error.equals("Error with ")) {
			return "Correct";}
		else return error;
		
	}

	private static boolean CompanyName (String companyName){
		if (companyName.length()<3 || companyName.length()>20) {
			return false;
		}else {
			return true;
		}
	}

	private static boolean LetterContactName(String letterContactNme){
		if (letterContactNme.length()<1 || letterContactNme.length()>15) {
			return false;
		}else {
			return true;
		}

	}

	


	public static void main(String []args) {


		System.out.println(ValRegistration("firdows", "1 telford street","ws10 8ur", "firdows"));
		System.out.println(ValCompDetails("firdows", "1 telford street", "0121526878"));
		System.out.println(ValDiscountRate("s", "fi", 6));
		System.out.println(ValInvoiceHeader("",""));
		System.out.println(ValInvoice("firdows","seldge hammer", 1));

		


	}


}