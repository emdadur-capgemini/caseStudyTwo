package letterBatchOO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import LetterBatch.Products;


public class InvoiceLetter extends Letter implements LetterInterface{

	String contactName;
	ArrayList<ProductList> productList = new ArrayList<>();
	private Iterator<ProductList> productIterator;
	
	public InvoiceLetter(int line, String type, String companyName, String contName) {
		super(line, type, companyName);
		this.contactName = contName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateLetter() {
		

		File source = new File("/home/regen/git/caseStudyTwo/LetterBatch/resources/templates/invoice.txt");
        dest = new File("/home/regen/git/caseStudyTwo/LetterBatch/resources/output/"+super.companyName+".txt");
        try {
			copyFileUsingStream(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
		}
        
		replaceSelected("<<system.today>>", ("\t  "+letterDate));
		replaceSelected("<<letterName>>", this.contactName);
		
		
		replaceSelectedProducts(this.productList);

		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public String isValid() {
		
		String subErrorMsg = "";
		if (checkCompanyName() == false)
			super.errorMsg = super.errorMsg.concat("company name ");

		if (checkLetterContactName(contactName) == false)
			super.errorMsg = super.errorMsg.concat("Letter Contact Name");
		
		
		productIterator = productList.iterator();
		while(productIterator.hasNext()) {
			
			subErrorMsg = subErrorMsg.concat(productIterator.next().isValid());
			
		}

		
		
		
		if (super.errorMsg.equals("")) {
			if(!subErrorMsg.equals("")) 
				return subErrorMsg;
			else
					return "";
			}
		else {
			super.errorMsg = "\nline " + super.lineCount + ": error with " + super.errorMsg;
			return super.errorMsg;
		}
	}

//	public ArrayList<ProductList> getProductList() {
//		return productList;
//	}

	public void addProduct(int line, String type, String companyName, String name, Double price){
		
		ProductList prod = new ProductList(line, type, companyName, name, price);
		productList.add(prod);
		
	}

	
	

	public static void replaceSelectedProducts(ArrayList<ProductList> products) {
		
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
		    for (int i = 0; i<products.size();i++)
		    {
		        sb.append(products.get(i).getProductName());
		        
		        //formatting if names are too large
		        if(products.get(i).getProductName().length()<14) {
		        	
		        	sb.append("\t");
		        	
		        }
		        
		        //formatting to have details next to each other
		        sb.append("\t");                    
		        sb.append(products.get(i).getPrice());
		        sb.append("\n");
		        sb.append("  ");

		    }
		    
		    if(!products.isEmpty()) { 
		    	
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
	

}
