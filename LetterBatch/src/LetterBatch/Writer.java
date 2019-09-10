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
    static String a;

	
	////group of contacts
	ArrayList<String> contNameList = new ArrayList<String>();
	ArrayList<String> contNoList = new ArrayList<String>();

 
	public Writer(Confirmation name) throws IOException {
		
		File source = new File("/home/regen/Desktop/confirmation.txt");
        dest = new File("/home/regen/Desktop/"+name.getCompanyName()+".txt");

        copyFileUsingStream(source, dest);
        
		contNameList = name.getContactNameList();
		contNoList = name.getContactNumberList();
        //System.out.println("This is array: " + contNameList);
        //System.out.println("This is array: " + contNoList);

        
		
		replaceSelected("<<address1>>",name.getAddress());
		replaceSelected("<<postcode>>", name.getPostcode());
		replaceSelected("<<system.today>>", ("\t"+letterDate));
		replaceSelected("<<letterName>>", name.getContactName());
		
//        for(int i = 0; i<contNameList.size(); i++) {
//        	
//        	//System.out.println("contNameList: " + contNameList.get(i));
//        	replaceSelected("<<contactName>>", contNameList.get(i));
//        	
//        }
		
		replaceSelectedArray(contNameList, contNoList);
		
	}
	
	public static void replaceSelectedArray(ArrayList<String> name, ArrayList<String> number) {
		
        try {
        	
            BufferedReader file = new BufferedReader(new FileReader(dest));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
        	
			while ((line = file.readLine()) != null) {
			    inputBuffer.append(line);
			    inputBuffer.append('\n');
			}
			
			file.close();
		    String inputStr = inputBuffer.toString();
		    
		    StringBuilder sb = new StringBuilder();
		    for (int i = 0; i<name.size();i++)
		    {
		        sb.append(name.get(i));
		        
		        if(name.get(i).length()<14) {
		        	
		        	sb.append("\t");
		        	
		        }
		        
		        sb.append("\t");
		        sb.append(number.get(i));
		        sb.append("\n");
		        sb.append("  ");

		    }
		    
//		    StringBuilder sb1 = new StringBuilder();
//		    for (String s1 : number)
//		    {
//		        sb1.append(s1);
//		        sb1.append("\n");
//		    }
		    
	        if (!name.isEmpty() && !number.isEmpty()) {
	                      		
	        		
	            	inputStr = inputStr.replace("<<group.contacts>>\n"+"  <<contactName>>    <<contactNumber>>", sb.toString());
	            	//inputStr = inputStr.replace("<<contactNumber>>", sb1.toString());
	            
	        } 
	        
            
	        

        FileOutputStream fileOut = new FileOutputStream(dest);
        
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

	        System.out.println(inputStr); // display the original file 

	        // replace lines in the string 
	        if (!type.isEmpty()) {
	            inputStr = inputStr.replace(replaceWith, type); 
	        } 

	        // display the new file 
	        System.out.println("----------------------------------\n" + inputStr);

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
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	

}
