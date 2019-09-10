package LetterBatch;

public class LetterBatch {

	public static void main (String[] args) {
		Reader read = new Reader();
		try {
			read.readFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
