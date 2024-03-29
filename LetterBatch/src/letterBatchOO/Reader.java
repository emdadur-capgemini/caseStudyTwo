package letterBatchOO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Reader {

	Path path;
	// ArrayList of type LetterInterface class used to store different letters
	ArrayList<LetterInterface> letter = new ArrayList<>();
	// Iterator used to iterate letter objects
	Iterator<LetterInterface> letterIterator;
	private String error = "";
	private int lineCount;

	/**
	 * When called it initialises object and calls a subroutine to read the file
	 * line by line and store all created objects in a ArrayList of type
	 * LetterInterface class.
	 * 
	 * @param path
	 *            final variable type Path that contains location of file
	 */
	public Reader(Path path) {
		this.path = path;
		this.readFile();
	}

	/**
	 * Subroutine that reads the whole file line by line. It populates an ArrayList
	 * of type LetterInterface class containing entries for all letters that have to
	 * be validated and generated to letters.
	 * 
	 * When adding new letter add a case to the switch statement, if the letter
	 * contains a sublist a case needs to be added to the second switch. .
	 */
	private void readFile() {

		lineCount = 0;
		// tries to read file at specified path, have already been validated
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {

			String currentLine = null;
			// ArrayList of strings storing entries of type 1A and 3A to be dealt later
			ArrayList<String> skipped = new ArrayList<>();
			ArrayList<Integer> skippedLineNumber = new ArrayList<>();
			// read the file line by line
			while ((currentLine = reader.readLine()) != null) {

				lineCount++;
				// split the string on every '|' character found, stored in array
				String[] split = currentLine.split("[|]");

				// first element of the array contains the type of letter
				switch (split[0]) {
				case "1":

					if (split.length != 5) {
						error = error.concat("\nline " + lineCount + ": unexpected amount of fields");
						break;
					}
					// add an ArrayList element of type ConfirmationLetter
					letter.add(new ConfirmationLetter(lineCount, split[0], split[1], split[2], split[3], split[4]));
					break;

				case "1A":
					if (split.length != 4) {
						error = error.concat("\nline " + lineCount + ": unexpected amount of fields");
						break;
					}
					// add to ArrayList to deal with after all letters are initialised
					skipped.add(currentLine);
					skippedLineNumber.add(lineCount);
					break;
				case "2":
					if (split.length != 4) {
						error = error.concat("\nline " + lineCount + ": unexpected amount of fields");
						break;
					}
					// add an ArrayList element of type DiscountLetter
					try {
						letter.add(
								new DiscountLetter(lineCount, split[0], split[1], split[2], Double.valueOf(split[3])));
					} catch (NumberFormatException ex) {
						error = error.concat("\nline " + lineCount + ": discount procentage should be a number");
						break;
					}

					break;
				case "3":
					if (split.length != 3) {
						error = error.concat("\nline " + lineCount + ": unexpected amount of fields");
						break;
					}
					// add an ArrayList element of type InvoiceLetter
					letter.add(new InvoiceLetter(lineCount, split[0], split[1], split[2]));

					break;
				case "3A":
					if (split.length != 4) {
						error = error.concat("\nline " + lineCount + ": unexpected amount of fields");
						break;
					}
					// add to ArrayList to deal with after all letters are initialised
					skipped.add(currentLine);
					skippedLineNumber.add(lineCount);

					break;
				default:
					error = error.concat("\nline " + lineCount + ": invalid letter type");
					break;
				}

			}

			// temporary object used to store currently read array element
			LetterInterface tempLetter;
			ListIterator<String> iteratorSkipped = skipped.listIterator();
			ListIterator<Integer> iteratorSkippedLineNumber = skippedLineNumber.listIterator();
			ListIterator<LetterInterface> iteratorLetters;
			// iterate through the skipped lines
			while (iteratorSkipped.hasNext()) {
				String temp = iteratorSkipped.next();

				boolean companyMatched = false;
				// split the string on every '|' character found, stored in array
				String[] split = temp.split("[|]");
				switch (split[0]) {

				case "1A":
					// iterates through the letters until it finds the letter matching
					// the same type and same company name, then it adds it to the list
					// of contacts
					ConfirmationLetter tempContactList;
					iteratorLetters = letter.listIterator();
					while (iteratorLetters.hasNext()) {
						tempLetter = iteratorLetters.next();
						if (tempLetter.getType().equals("1") && tempLetter.getCompanyName().equals(split[1])) {
							tempContactList = (ConfirmationLetter) (tempLetter);
							tempContactList.addContact((int) (iteratorSkippedLineNumber.next()), split[0], split[1],
									split[2], split[3]);
							iteratorLetters.set((LetterInterface) (tempContactList));
							companyMatched = true;
							break;
						}
					}

					break;
				case "3A":
					// iterates through the letters until it finds the letter matching
					// the same type and same company name, then it adds it to the list
					// of products
					InvoiceLetter tempInvoice;
					iteratorLetters = letter.listIterator();
					while (iteratorLetters.hasNext()) {
						tempLetter = iteratorLetters.next();
						if (tempLetter.getType().equals("3") && tempLetter.getCompanyName().equals(split[1])) {
							tempInvoice = (InvoiceLetter) (tempLetter);
							tempInvoice.addProduct((int) (iteratorSkippedLineNumber.next()), split[0], split[1],
									split[2], Double.valueOf(split[3]));
							iteratorLetters.set((LetterInterface) (tempInvoice));
							companyMatched = true;
							break;
						}
					}

					break;
				default:
					// catches if type not recognised, should never go here as it is already
					// cached before it reaches here
					error = error.concat("\nline " + (int) (iteratorSkippedLineNumber.next()) + ": invalid letter type");
					break;
				}
				if (!companyMatched) {
					error = error.concat("\nline " + (int) (iteratorSkippedLineNumber.next()) + ": company " + split[1]
							+ " does not exist in this file");
					iteratorSkippedLineNumber.remove();
				}

			}

		} catch (IOException ex) {
			System.out.println("All files read.");
		}

	}

	/**
	 * When called this subroutine iterates all letter objects and checks if they
	 * contain valid data with reference to the customer requirements. If there is
	 * an error in the file (taking into account previous checks) a log file is
	 * created in the error directory and the original file is moved to the error
	 * directory
	 * 
	 * @return returns true if all entries in the file are valid, else it returns
	 *         true
	 */
	public boolean isValidFile() {
		letterIterator = this.letter.iterator();
		// iterate all letter objects generated from the file
		while (letterIterator.hasNext()) {
			// check if the object contains valid data,
			// if yes empty string is returned,
			// if no error message is returned
			error = error.concat(letterIterator.next().isValid());

		}

		if (this.error.equals(""))
			return true;
		else {

			try {

				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("/home/regen/git/caseStudyTwo/LetterBatch/resources/error/"
								+ path.getFileName().toString().substring(0, path.getFileName().toString().length() - 4)
								+ "-log.txt"),
						"utf-8"));
				writer.write(error);
				writer.close();

			} catch (IOException ex) {
				// Report
				System.out.print(ex.getMessage());
			}
			try {
				Files.move(path,
						Paths.get("/home/regen/git/caseStudyTwo/LetterBatch/resources/error/" + path.getFileName()),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
			}
			return false;
		}
	}

	/**
	 * When called this subroutine iterates all letter objects and generates letters
	 * for them. After generating letters the original file is moved to the archive
	 * folder
	 */
	public void generateAllLetters() {

		letterIterator = this.letter.iterator();
		// iterate all letter objects generated from the file
		while (letterIterator.hasNext()) {

			letterIterator.next().generateLetter();

		}

		try {
			Files.move(path,
					Paths.get("/home/regen/git/caseStudyTwo/LetterBatch/resources/archive/" + path.getFileName()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		}

	}

}
