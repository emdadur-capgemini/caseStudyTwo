package LetterBatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	
	File file = new File("/home/regen/git/caseStudyTwo/LetterBatch/resources/COMPANYX.txt"); 
	  
	public Reader() {
		
		
	}
	
	public void readFile() throws Exception{
		
	  // We need to provide file path as the parameter: 
	  // double backquote is to avoid compiler interpret words 
	  // like \test as \t (ie. as a escape sequence) 
	  
	  BufferedReader br = new BufferedReader(new FileReader(file)); 
	  
	  String st; 
	  Confirmation conf;
	  while ((st = br.readLine()) != null) {
		  
		  //splitLine(st);
		  
		  
		  String[] split = st.split("[|]"); 
		   
			switch(split[0]) {
			
			case "1":
				conf = new Confirmation(split[1],split[2],split[3],split[4]);
				addContacts(conf,split[1]);
				System.out.println(conf.getContactNameList());
				System.out.println(conf.getContactNumberList());
				
				Writer w = new Writer(conf);
				
				break;
			case "2":
				
				break;
			case "3":
				
				break;
			case "3A":
				
				break;
			default:
				break;
			}
			System.out.println();
		  
		  
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
	
	public void splitLine(String str) {
		
		String[] split = str.split("[|]"); 
		   
		switch(split[0]) {
		
		case "1":
			Confirmation conf = new Confirmation(split[1],split[2],split[3],split[4]);
			break;
		case "1A":
			
			break;
		case "2":
			
			break;
		case "3":
			
			break;
		case "3A":
			
			break;
		default:
			break;
		}
		System.out.println();
	}

}
