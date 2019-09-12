package LetterBatch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class LetterBatch {

	public static void main (String[] args) {
		//Reader read = new Reader();
		try {
	        Path dir = Paths.get("/home/regen/git/caseStudyTwo/LetterBatch/resources/input");
	        new Pooling(dir).processEvents();
	        
			//read.readFile();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void moveFile(Path file, Path destination) {
		
		//take path of a file and put into specific folder
        try {
        	
            Files.move(file, destination, StandardCopyOption.REPLACE_EXISTING);
            //Files.move(Paths.get(sourceFile.getPath()), Paths.get(destFile.getPath()), StandardCopyOption.REPLACE_EXISTING);
            
        } catch (IOException e) {
        	
        }
	    
	}
	
	public static void createLogs(String name, String logs) {
		
//		String name = "errorFileForinput.txt";
//		String logs = "There is an error on line 1";
				
		BufferedWriter writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/regen/git/caseStudyTwo/LetterBatch/resources/error/"+name), "utf-8"));		    
		    writer.write(logs);
	        writer.close();
    
		} catch (IOException ex) {
		    // Report
			System.out.print(ex.getMessage());
		} 
		
		
		
		
	}
}
