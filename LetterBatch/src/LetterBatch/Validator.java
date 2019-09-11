package LetterBatch;

public class Validator {


	private static String ValRegistration (String companyType, String companyName, String address, String postCode, String letterContactNme){
		String error = "Error with ";
		String cType = "1";

		if (!companyType.equals(cType))
			error = error+"Company type";

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


	private static String ValCompDetails (String companyType, String companyName, String contactName, String contactNumber){
		String error = "Error with ";
		String cType = "1A";

		if (!companyType.equals(cType))
			error = error+"Company type";

		if (CompanyName(companyName) == false)
			error = error+"company name";
		if (contactName.length()<1 || contactName.length()>20)
			error = error +"Contact Name";
		if (!contactNumber.matches("(\\(?\\+44\\)?\\s?(1|2|3|7|8)\\d{3}|\\(?(01|02|03|07|08)\\d{3}\\)?)\\s?\\d{3}\\s?\\d{3}|(\\(?\\+44\\)?\\s?(1|2|3|5|7|8)\\d{2}|\\(?(01|02|03|05|07|08)\\d{2}\\)?)\\s?\\d{3}\\s?\\d{4}|(\\(?\\+44\\)?\\s?(5|9)\\d{2}|\\(?(05|09)\\d{2}\\)?)\\s?\\d{3}\\s?\\d{3}"))
			error = error +"Contact Number";
		if (error.equals("Error with ")) {
			return "Correct";}
		else return error;

	}

	private static String ValDiscountRate (String companyType, String companyName, String letterContactNme, double discountRate){

		String error = "Error with ";
		String cType = "2";

		if (!companyType.equals(cType))
			error = error+"Company type";

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

	private static String ValInvoiceHeader (String companyType, String companyName, String letterContactNme){

		String error = "Error with ";
		String cType = "3";

		if (!companyType.equals(cType))
			error = error+"Company type";

		if (CompanyName(companyName) == false)
			error = error+"company name ";

		if (LetterContactName(letterContactNme) == false)
			error = error +"Letter Contact Name";

		if (error.equals("Error with ")) {
			return "Correct";}
		else return error;

	}

	private static String ValInvoice (String companyType, String companyName, String productName, double price){

		String error = "Error with ";
		String cType = "3A";

		if (!companyType.equals(cType))
			error = error+"Company type";

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

		System.out.println(ValRegistration("1", "firdows", "1 telford street","tf1 6sp", "firdows"));
		System.out.println(ValCompDetails("1A", "firdows", "1 telford street", "0121 456 9213"));
		System.out.println(ValDiscountRate("2", "capgemini", "fi", 6));
		System.out.println(ValInvoiceHeader("3", "FIDOWS","firdows"));
		System.out.println(ValInvoice("3A","firdows","seldge hammer", 1));

	}


}