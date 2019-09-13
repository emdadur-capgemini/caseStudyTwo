package letterBatchOO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Letter {

	final protected String type;
	protected String companyName;
	protected String errorMsg = "";
	protected int lineCount;

	static File dest;

	// date details
	static DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	static Date today = Calendar.getInstance().getTime();
	static String letterDate = df.format(today);

	public Letter(int line, String type, String companyName) {

		this.lineCount = line;
		this.type = type;
		this.companyName = companyName;

	}

	public String getType() {
		return type;
	}

	public String getCompanyName() {
		return companyName;
	}

	protected boolean checkCompanyName() {
		if (companyName.length() < 3 || companyName.length() > 20) {
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkLetterContactName(String letterContactNme) {

		if (letterContactNme.length() < 1 || letterContactNme.length() > 15) {
			return false;
		} else {
			return true;
		}

	}

	public static void replaceSelected(String replaceWith, String type) {
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

			// replace lines in the string
			if (!type.isEmpty()) {
				inputStr = inputStr.replace(replaceWith, type);
			}
			
			// write the new string with contents to new file
			FileOutputStream fileOut = new FileOutputStream(dest);

			fileOut.write(inputStr.getBytes());
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Problem reading file.");
		}
	}

	protected static void copyFileUsingStream(File source, File dest) throws IOException {

		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);

			// conversion for copy
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {

			// output
			is.close();
			os.close();
		}
	}

}
