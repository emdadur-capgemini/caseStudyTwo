package letterBatchOO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import LetterBatch.Contacts;

public class ConfirmationLetter extends Letter implements LetterInterface{
	
	private String address;
	private String postcode;
	private String contactName;
	private ArrayList<ContactList> contactListArray = new ArrayList<>();
	private Iterator<ContactList> contactsIterator;
	
	public ConfirmationLetter( int line, String type, String companyName,String address, String postcode, String contactName) {
		super(line, type, companyName);
		this.address = address;
		this.postcode = postcode;
		this.contactName = contactName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateLetter() {
		

		File source = new File("/home/regen/git/caseStudyTwo/LetterBatch/resources/confirmation.txt");
        dest = new File("/home/regen/git/caseStudyTwo/LetterBatch/resources/output/"+super.companyName+".txt");
        try {
			super.copyFileUsingStream(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
		}
	
		
		replaceSelected("<<address1>>",this.address);
		replaceSelected("<<postcode>>", this.postcode);
		replaceSelected("<<system.today>>", ("\t"+letterDate));
		replaceSelected("<<letterName>>", this.contactName);
		
		 
		replaceSelectedContacts(contactListArray);
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public String isValid() {
		
		String subErrorMsg = "";
		if (checkCompanyName() == false) 
			super.errorMsg = super.errorMsg.concat("Company Name");
		
		if (!((address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)"))||((address.matches("\\d+-([a-zA-Z]+|[a-zA-Z]+-[a-zA-Z]+)")))))
			super.errorMsg = super.errorMsg.concat(", Address");

		if (!(postcode.matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$")|| (postcode.matches("^[a-z]{1,2}[0-9R][0-9a-z]? [0-9][abd-hjlnp-uw-z]{2}$"))))
			super.errorMsg = super.errorMsg.concat(", Post Code");

		if (checkLetterContactName(contactName) == false)
			super.errorMsg = super.errorMsg.concat(", Letter Contact Name");
		
		contactsIterator = contactListArray.iterator();
		while(contactsIterator.hasNext()) {
			
			subErrorMsg = subErrorMsg.concat(contactsIterator.next().isValid());
			
		}

		if (super.errorMsg.equals("")) {
			if(!subErrorMsg.equals("")) 
				return subErrorMsg;
			else
					return "";
			}
		else {
			super.errorMsg = "\nline " + super.lineCount + ": error with " + super.errorMsg + subErrorMsg;
			//System.out.println(super.errorMsg);
			return super.errorMsg;
		}
		
	}

//	public ArrayList<ContactList> getContactList() {
//		return contactListArray;
//	}

	public void addContact(int line,String type, String companyName, String name, String number) {
		
		ContactList cont = new ContactList(line, type,companyName , name, number);
		contactListArray.add(cont);
		
	}

	
	
	
public static void replaceSelectedContacts(ArrayList<ContactList> contacts) {
		
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
		    if(!contacts.isEmpty()) {	                      		
	        		
	            	inputStr = inputStr.replace("<<group.contacts>>\n"+"  <<contactName>>    <<contactNumber>>", sb.toString());
        
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
	
	
}
