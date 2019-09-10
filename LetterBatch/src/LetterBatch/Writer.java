package LetterBatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.util.Pair;

public class Writer {
	
	// details
	static DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	static Date today = Calendar.getInstance().getTime();        
	static String letterDate = df.format(today);
    static File dest;

	
	//group of products
	ArrayList<String> prodNameList = new ArrayList<String>();
	ArrayList<Double> prodCostList = new ArrayList<Double>();
	
	//group of contacts
	ArrayList<Contacts> contList = new ArrayList<Contacts>();
 
	public void Confirmation(Confirmation name) throws IOException {
		
		File source = new File("/home/regen/Desktop/confirmation.txt");
        dest = new File("/home/regen/Desktop/"+name.getCompanyName()+".txt");
        copyFileUsingStream(source, dest);
	
		contList = name.getContactList();
		
		replaceSelected("<<address1>>",name.getAddress());
		replaceSelected("<<postcode>>", name.getPostcode());
		replaceSelected("<<system.today>>", ("\t"+letterDate));
		replaceSelected("<<letterName>>", name.getContactName());
		
		
		System.out.println("check this: "+ contList);
		replaceSelectedContacts(contList);
		
	}
	
	public void Discount(Discount disc) throws IOException {
		
		
		
		File source = new File("/home/regen/Desktop/discount.txt");
        dest = new File("/home/regen/Desktop/"+disc.getCompanyName()+".txt");
        copyFileUsingStream(source, dest);
        
        
		replaceSelected("<<system.today>>", ("\t  "+letterDate));
		replaceSelected("<<letterName>>", disc.getContactName());
		
		String discountAsString = Double.toString(disc.getDiscount());

		replaceSelected("<<discount>>", discountAsString);
		
	}
	
	public void Invoice(Invoice invo) throws IOException {	
		
		File source = new File("/home/regen/Desktop/invoice.txt");
        dest = new File("/home/regen/Desktop/"+invo.getCompanyName()+".txt");
        copyFileUsingStream(source, dest);
        
        prodNameList = invo.getProductNameList();
        prodCostList = invo.getPriceList();
        
		replaceSelected("<<system.today>>", ("\t  "+letterDate));
		replaceSelected("<<letterName>>", invo.getContactName());

		//String discountAsString = Double.toString(invo.getPriceList());
		ArrayList<String> dummy = new ArrayList<String>();
		
		String[] s = new String[invo.getPriceList().size()];
		
		for (int i = 0; i < s.length; i++) {
		    s[i] = String.valueOf(invo.getPriceList().size());
		    dummy.add(s[i]);
		}
		
		//replaceSelectedArray(prodNameList, dummy);

		
	}

	
	public static void replaceSelectedContacts(ArrayList<Contacts> contacts) {
		
        try {
        	
	        // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader(dest));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
        	
			while ((line = file.readLine()) != null) {
			    inputBuffer.append(line);
			    inputBuffer.append('\n');
			}
			
			file.close();
		    String inputStr = inputBuffer.toString();
		    

		    
		    //Converts given array to string for place holder replacement
		    StringBuilder sb = new StringBuilder();
		    for (int i = 0; i<contacts.size();i++)
		    {
		        sb.append(contacts.get(i).getContactName());
		        
		        //formatting if names are too large
		        if(contacts.get(i).getContactName().length()<14) {
		        	
		        	sb.append("\t");
		        	
		        }
		        
		        //formatting to have details next to each other
		        sb.append("\t");                    
		        sb.append(contacts.get(i).getContactNumber());
		        sb.append("\n");
		        sb.append("  ");

		    }
		    
		    //if statement used to distinguish between confirmation and invoice group details, replace placeholder..
		    if(inputStr.contains("<<group.contacts>>")) {	                      		
	        		
	            	inputStr = inputStr.replace("<<group.contacts>>\n"+"  <<contactName>>    <<contactNumber>>", sb.toString());
        
		    } else {
		    	
		    	inputStr = inputStr.replace("<<group.products>>\n" +"  <<productName>>    <<productCost>>", sb.toString());
		    	
		    }

	        FileOutputStream fileOut = new FileOutputStream(dest);
	        
	        // write the new string with contents to existing file
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void replaceSelected(String replaceWith, String type) {
	    try {
	        // input the file content to the StringBuffer "input"
	        BufferedReader file = new BufferedReader(new FileReader(dest));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;

	        while ((line = file.readLine()) != null) {
	            inputBuffer.append(line);
	            inputBuffer.append('\n');
	        }
	        file.close();
	        String inputStr = inputBuffer.toString();

	       // System.out.println(inputStr); // display the original file 

	        // replace lines in the string 
	        if (!type.isEmpty()) {
	            inputStr = inputStr.replace(replaceWith, type); 
	        } 

	        // display the new file 
	       // System.out.println("----------------------------------\n" + inputStr);

	        // write the new string with contents to new file
	        FileOutputStream fileOut = new FileOutputStream(dest);
	        	        
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();
	        

	    } catch (Exception e) {
	        System.out.println("Problem reading file.");
	    }
	}
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
		
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        
	        //conversion for copy
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	    	
	    	//output
	        is.close();
	        os.close();
	    }
	}
	

}
