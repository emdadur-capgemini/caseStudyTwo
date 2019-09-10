package LetterBatch;

public class Validator {


	private static boolean ValRegistration (String companyName, String address, String postCode, String letterContactNme){
		//(String companyName, String address, , String )
		if (CompanyName(companyName) == true){
			if (((address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)"))||((address.matches("\\d+-([a-zA-Z]+|[a-zA-Z]+-[a-zA-Z]+)"))))){
				if ((postCode.matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$")|| (postCode.matches("^[a-z]{1,2}[0-9R][0-9a-z]? [0-9][abd-hjlnp-uw-z]{2}$")))){
					if (LetterContactName(letterContactNme) == true){
						//System.out.println("letter contact name not correct");
						return true;
					}else { 
						System.out.println("Wrong");
						return false;}

				}else { 
					System.out.println("Wrong");
					return false;}

			} else { 
				System.out.println("Wrong");
				return false;}
		} else { 
			System.out.println("Wrong");
			return false;}
	}

	//
	private static boolean ValCompDetails (String companyName, String contactName, String contactNumber){
		if (CompanyName(companyName) == true){
			if (contactName.length()>1 || contactName.length()<20) {
				if (contactNumber.length()<14) {
					return true;

				}else return false;
			}else return false;
		}else return false;

	}
	
	private static boolean ValDiscountRate (String companyName, String letterContactNme, double discountRate){
		if (CompanyName(companyName) == true){
			if (LetterContactName(letterContactNme) == true) {
				if (discountRate >1 && discountRate<50) {
					return true;
				}else return false;
			}else return false;

		}else return false;

	}

	private static boolean ValInvoiceHeader (String companyName, String letterContactNme){
		if (CompanyName(companyName) == true){
			if (LetterContactName(letterContactNme) == true) {
				return true;
			}else return false;
		}else return false;
	}
	
	private static boolean ValInvoice (String companyName, String productName, double price){
		if (CompanyName(companyName) == true){
			if (productName.length()>3 && productName.length()<50) {
				if (price >0) {
					if (price*100 == (int)(price*100)) {
						return true;
					}else return false;
					
					
				}else return false;
			}else return false;
		}else return false; 
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

	private static boolean checknumber(String number) {
		if (number.matches("[0-9 ]") && number.length()<14) {
			return true;
		}else return false;
	}
//"[0-9]+" 


	public static void main(String []args) {

		//System.out.println(ValRegistration("firdows", "1 telford street","ws10 8ur", "firdows"));
		//System.out.println(ValCompDetails("firdows", "1 telford street", "0121 526 6878"));
		//System.out.println(ValDiscountRate("s", "fi", 6));
		//System.out.println(ValInvoiceHeader("",""));
		//System.out.println(ValInvoice("firdows","seldge hammer", 23.12));
		System.out.println(checknumber("0121 832 7392"));


	}


}

//if (contactNumber.matches("\\d{4}+\\s+\\d{3}+\\s+\\d{4}")&&(contactNumber.length()< 14)){