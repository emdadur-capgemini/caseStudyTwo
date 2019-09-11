package LetterBatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Reader {
	
	File file = new File("/home/regen/git/caseStudyTwo/LetterBatch/resources/COMPANYX.txt"); 
	Validator validation;
	public Reader() {
		
		
	}
	
	
	public boolean validateData() throws Exception {
		

	  BufferedReader br = new BufferedReader(new FileReader(file)); 
	  
	  String st; 
	  Confirmation conf;
	  Discount disc;
	  Invoice inv;
	  Products prod;
	  Contacts cont;
	  String msg = "";
	  int counter = 0;
	  while ((st = br.readLine()) != null) {
		  counter++;
		  
		  String[] split = st.split("[|]"); 
		   
			switch(split[0]) {
			
			case "1":
				conf = new Confirmation(split[1],split[2],split[3],split[4]);
				if(Validator.ValRegistration(conf.getCompanyName(),conf.getAddress(), conf.getPostcode(), conf.getContactName()) != "Correct")
				msg = msg + Validator.ValRegistration(conf.getCompanyName(),conf.getAddress(), conf.getPostcode(), conf.getContactName()) + " on line " + counter  +"\n";
				break;
			case "1A":
				cont = new Contacts(split[1],split[2],split[3]);
				if(Validator.ValCompDetails(cont.getCompanyName(),cont.getContactName(),cont.getContactNumber()) != "Correct")
				msg = msg + Validator.ValCompDetails(cont.getCompanyName(),cont.getContactName(),cont.getContactNumber()) + " on line " + counter  +"\n";
				
				break;
			case "2":
				disc = new Discount(split[1],split[2],Double.valueOf(split[3]));
				if(Validator.ValDiscountRate(split[1],split[2],Double.valueOf(split[3])) != "Correct")
				msg = msg + Validator.ValDiscountRate(split[1],split[2],Double.valueOf(split[3])) + " on line " + counter  +"\n";
				
				break;
			case "3":
				inv = new Invoice(split[1],split[2]);
				if(Validator.ValInvoiceHeader(split[1],split[2]) != "Correct")
				msg = msg + Validator.ValInvoiceHeader(split[1],split[2]) + " on line " + counter  +"\n";
				
				break;
			case "3A":
				prod = new Products(split[1],split[2],Double.valueOf(split[3]));
				if(Validator.ValInvoice(split[1],split[2],Double.valueOf(split[3])) != "Correct")
				msg = msg + Validator.ValInvoice(split[1],split[2],Double.valueOf(split[3])) + " on line " + counter  +"\n";
				
				break;
			
			default:
				break;
			}
	  
	  
	  }
		if(msg == "") {
			System.out.println("File is valid");
			return true;
			}
		else {
			System.out.println(msg);
			return false;
		}
		
	}
	
	public void readFile() throws Exception{
		
	  // We need to provide file path as the parameter: 
	  // double backquote is to avoid compiler interpret words 
	  // like \test as \t (ie. as a escape sequence) 
	  
	  BufferedReader br = new BufferedReader(new FileReader(file)); 
	  
	  String st; 
	  Confirmation conf = null;
	  Discount disc;
	  Invoice inv;
	  
	  Writer w = new Writer();

	  while ((st = br.readLine()) != null) {
		  
		  //splitLine(st);
		  
		  
		  String[] split = st.split("[|]"); 
		   
			switch(split[0]) {
			
			case "1":
				conf = new Confirmation(split[1],split[2],split[3],split[4]);
				addContacts(conf,split[1]);
			
				
				//w.Confirmation(conf);

				break;
			case "2":
				disc = new Discount(split[1],split[2],Double.valueOf(split[3]));
				
				//w.Discount(disc);
				
				break;
			case "3":
				inv = new Invoice(split[1],split[2]);
				addContacts(inv,split[1]);
				
				//w.Invoice(inv);
			
				
				break;
			default:
				break;
			}

		  
		  
	  }
	  
		
	}
	
	public void addContacts(Invoice inv, String company) throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		   
		  String st; 
		  while ((st = br.readLine()) != null) {
			  
			  String[] split = st.split("[|]");
			  //System.out.println(split[0] + split[1] + company);
			  if(split[0].equals("3A") && split[1].equals(company))
				  //System.out.println(split[2] + split[3]);
				  inv.addProduct(split[2], Double.valueOf(split[3]));
			  else
				  continue;
		  }
		
	}
	
	public void addContacts(Confirmation conf, String company) throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  String st; 
		  while ((st = br.readLine()) != null) {
			  
			  String[] split = st.split("[|]");
			  //System.out.println(split[0] + split[1] + company);
			  if(split[0].equals("1A") && split[1].equals(company))
				  //System.out.println(split[2] + split[3]);
				  conf.addContact(split[2], split[3]);
			  else
				  continue;
		  }
		
	}
	

}
