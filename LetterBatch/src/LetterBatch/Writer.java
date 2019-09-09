package LetterBatch;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.util.Pair;

public class Writer {
	
	//Confirmation details
	static String letterName = "Capgemini team";
	static String address1 = "123 Telford, Capgemini";
	static String postcode = "TF1 1AA";
	static DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	static Date today = Calendar.getInstance().getTime();        
	static String letterDate = df.format(today);

	
	////group of contacts
	String contactName = "John Snow";
	String contactNumber = "0763636373";
	
//	public static void main(String []args) {
//		
//		//Assuming given array of contacts
//		ArrayList<String> myList = new ArrayList<String>();
//		ArrayList<String> myList1 = new ArrayList<String>();
//
//		myList.add("John");
//		myList1.add("073247432");
//		
//		myList.add("Bill");
//		myList1.add("0735435353");
//		
//		//System.out.println(myList1.get(0));
//		
//		for(int i =0; i<myList.size();i++) {
//			
//			System.out.println(myList.get(i)+myList1.get(i));
//			
//			
//		}
		
//		replaceSelected("<<address1>>", address1);
//		replaceSelected("<<postcode>>", postcode);
//		replaceSelected("<<system.today>>", letterDate);
//		replaceSelected("<<letterName>>", letterName);
		//replaceSelected("<<contactName>>", address1);
		//replaceSelected("<<contactNumber>>", address1);
		
		
//	}
	
	public static void replaceSelected(String replaceWith, String type) {
	    try {
	        // input the file content to the StringBuffer "input"
	        BufferedReader file = new BufferedReader(new FileReader("/home/regen/Desktop/confirmation.txt"));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;

	        while ((line = file.readLine()) != null) {
	            inputBuffer.append(line);
	            inputBuffer.append('\n');
	        }
	        file.close();
	        String inputStr = inputBuffer.toString();

	        System.out.println(inputStr); // display the original file 

	        // replace lines in the string 
	        if (!type.isEmpty()) {
	            inputStr = inputStr.replace(replaceWith, type); 
	        } 

	        // display the new file 
	        System.out.println("----------------------------------\n" + inputStr);

	        // write the new string with contents to new file
	        FileOutputStream fileOut = new FileOutputStream("/home/regen/Desktop/test.txt");
	        FileOutputStream fileOut1 = new FileOutputStream("/home/regen/Desktop/confirmation.txt");
	        
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();
	        
	        fileOut1.write(inputStr.getBytes());
	        fileOut1.close();
	        

	    } catch (Exception e) {
	        System.out.println("Problem reading file.");
	    }
	}
	

}
