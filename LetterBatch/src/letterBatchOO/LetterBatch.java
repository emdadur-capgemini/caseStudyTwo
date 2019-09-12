package letterBatchOO;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LetterBatch {
	public static void main (String[] args) {
		//??get directory from Polling class
		Path directory = Paths.get("/home/regen/git/caseStudyTwo/LetterBatch/resources/COMPANYX.txt");
        
		Reader inputFile = new Reader(directory);
		if(inputFile.isValidFile())
			inputFile.generateAllLetters();
		}
	
}

