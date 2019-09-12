package letterBatchOO;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;


public class Reader {
	
	Path path;
	//ArrayList of type LetterInterface class used to store different letters
	ArrayList<LetterInterface> letter = new ArrayList<>();
	//Iterator used to iterate letter objects
	Iterator<LetterInterface> letterIterator;
	private String error = "";
	private int lineCount;
	/**
	 * When called it initialises object and calls a subroutine to read
	 * the file line by line and store all created objects in a ArrayList of
	 * type LetterInterface class. 
	 * 
	 * @param path final variable type Path that contains location of file
	 */
	public Reader(Path path) {
		this.path = path;
		this.readFile();
	}

	/**
	 * Subroutine that reads the whole file line by line. It populates an ArrayList of
	 * type LetterInterface class containing entries for all letters that have to be validated
	 * and generated to letters. 
	 * 
	 * When adding new letter add a case to the switch statement, if the letter contains a sublist
	 * a case needs to be added to the second switch. .
	 */
	private void readFile() {

		lineCount = 0;
		//tries to read file at specified path, have already been validated
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {

			String currentLine = null;
			// ArrayList of strings storing entries of type 1A and 3A to be dealt later
			ArrayList<String> skipped = new ArrayList<>();
			ArrayList<Integer> skippedLineNumber = new ArrayList<>();
			//read the file line by line
			while ((currentLine = reader.readLine()) != null) {

				lineCount++;
				//split the string on every '|' character found, stored in array
				String[] split = currentLine.split("[|]");

				//first element of the array contains the type of letter
				switch (split[0]) {
				//TODO check that there aren't more elements than expected
				case "1":

					//add an ArrayList element of type ConfirmationLetter
					letter.add(new ConfirmationLetter(lineCount, split[0], split[1], split[2], split[3], split[4]));

					System.out.println(split[0] + "  " + split[1] + "  " + split[2] + "  " + split[3]);
					break;

				case "1A":
					//add to ArrayList to deal with after all letters are initialised
					skipped.add(currentLine);
					skippedLineNumber.add(lineCount);
					break;
				case "2":
					//add an ArrayList element of type DiscountLetter
					letter.add(new DiscountLetter(lineCount, split[0], split[1], split[2], Double.valueOf(split[3])));

					System.out.println(split[0] + "  " + split[1] + "  " + split[2] + "  " + split[3]);

					break;
				case "3":
					//add an ArrayList element of type InvoiceLetter
					letter.add(new InvoiceLetter(lineCount, split[0], split[1], split[2]));

					System.out.println(split[0] + "  " + split[1] + "  " + split[2]);

					break;
				case "3A":

					//add to ArrayList to deal with after all letters are initialised
					skipped.add(currentLine);
					skippedLineNumber.add(lineCount);

					break;
				default:
					//TODO detect that an invalid type was detected
					//idea have a string = "" and if it is wrong add to this string, then concatenate it with the string from the other validating functions
					System.out.println("Invalid type of letter");
					break;
				}

			}

			//temporary object used to store currently read array element 
			LetterInterface tempLetter;	
			ListIterator<String> iteratorSkipped = skipped.listIterator();
			ListIterator<Integer> iteratorSkippedLineNumber = skippedLineNumber.listIterator();
			ListIterator<LetterInterface> iteratorLetters;
			//iterate through the skipped lines
			while (iteratorSkipped.hasNext()) {
				String temp = iteratorSkipped.next();

				//split the string on every '|' character found, stored in array
				String[] split = temp.split("[|]");
				switch (split[0]) {

				case "1A":
					//iterates through the letters until it finds the letter matching
					//the same type and same company name, then it adds it to the list 
					//of contacts
					//TODO catch if no company found that matches, probably a boolean variable required
					ConfirmationLetter tempContactList;
					iteratorLetters = letter.listIterator();
					while (iteratorLetters.hasNext()) {
						tempLetter = iteratorLetters.next();
						if (tempLetter.getType().equals("1") && tempLetter.getCompanyName().equals(split[1])) {
							tempContactList = (ConfirmationLetter) (tempLetter);
							tempContactList.addContact((int)(iteratorSkippedLineNumber.next()),split[0],split[1], split[2], split[3]);
							iteratorLetters.set((LetterInterface) (tempContactList));
							System.out.println(
									"success:" + split[0] + "  " + split[1] + "  " + split[2] + "  " + split[3]);

							break;
						}
					}

					break;
				case "3A":
					//iterates through the letters until it finds the letter matching
					//the same type and same company name, then it adds it to the list 
					//of products
					//TODO catch if no company found that matches, probably a boolean variable required
					
					InvoiceLetter tempInvoice;
					iteratorLetters = letter.listIterator();
					while (iteratorLetters.hasNext()) {
						tempLetter = iteratorLetters.next();
						if (tempLetter.getType().equals("3") && tempLetter.getCompanyName().equals(split[1])) {
							tempInvoice = (InvoiceLetter) (tempLetter);
							tempInvoice.addProduct((int)(iteratorSkippedLineNumber.next()),split[0],split[1],split[2], Double.valueOf(split[3]));
							iteratorLetters.set((LetterInterface) (tempInvoice));
							System.out.println(
									"success:" + split[0] + "  " + split[1] + "  " + split[2] + "  " + split[3]);
							break;
						}
					}

					break;
				default:
					//TODO catch if type nor recognised, should never go here as it is already catched before it reaches here 
					System.out.println("No type" + split[0]);

					break;
				}

			}

		} catch (IOException ex) {
			System.out.println("All files read.");
		}

	}
	
	/**
	 * When called this subroutine iterates all letter objects and checks if they
	 * contain valid data with reference to the customer requirements.
	 * 
	 * @return returns true if all entries in the file are valid, else it returns true
	 */
	public boolean isValidFile() {
		letterIterator = this.letter.iterator();
		//iterate all letter objects generated from the file
		while(letterIterator.hasNext()) {
			//check if the object contains valid data,
			//if yes empty string is returned,
			//if no error message is returned
			error = error.concat(letterIterator.next().isValid());
			
		}
		
		if(this.error.equals(""))
			return true;
		else {
						
			try {
				
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/regen/git/caseStudyTwo/LetterBatch/resources/error/"+path.getFileName().toString() + ".log.txt"), "utf-8"));		    
			    writer.write(error);
		        writer.close();
	    
			} catch (IOException ex) {
			    // Report
				System.out.print(ex.getMessage());
			} 
			
			System.out.println(error);
			//TODO call function to print error file and pass in the error message
			return false;
		}
	}
	
	/**
	 * When called this subroutine iterates all letter objects and 
	 * generates letters for them.
	 */
	public void generateAllLetters() {
		
		letterIterator = this.letter.iterator();
		//iterate all letter objects generated from the file
		while(letterIterator.hasNext()) {
			
			letterIterator.next().generateLetter();
			
		}
	}

}
