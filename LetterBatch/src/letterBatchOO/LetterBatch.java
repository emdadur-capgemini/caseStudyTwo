package letterBatchOO;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LetterBatch {
	public static void main (String[] args) {

		//get directory from Polling class
		Path directory = Paths.get("/home/regen/git/caseStudyTwo/LetterBatch/resources/input");
        Polling poller;
		try {
			poller = new Polling(directory);
			poller.processEvents();
		} catch (IOException e) {
			// TODO Auto-generated catch block.
			System.out.println("Input directory not found");
		}
	
		
		Reader inputFile = new Reader(directory);
		if(inputFile.isValidFile()) 
			inputFile.generateAllLetters();
		}
	
}

