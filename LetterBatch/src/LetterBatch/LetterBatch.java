package LetterBatch;

import java.nio.file.Path;
import java.nio.file.Paths;

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
}
