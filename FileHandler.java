import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//class contains methods to create either one array from a single customer CSV report or an array of arrays from a multiple customer report.
//each single customer file comes in with a header line showing the names of each variable. The next line contains the actual information
//from the customer.
public class FileHandler {

	private FileReader inputfile;
	private String getText;
	private String[] someVals;
	File testFile;

	public FileHandler(File pathNameread) throws IOException {

		inputfile = new FileReader(pathNameread);
		testFile = pathNameread;

	}
// called from GUI used to create a list of string values used in instantiating the customer, and setting other data values.
	public String[] makeArray() throws IOException {

		Scanner filscan = new Scanner(testFile, "UTF-8");
		String trash = filscan.nextLine();  //remove the header line of the csv file
		getText = filscan.nextLine();

		getText.trim();
		someVals = getText.split("\",\""); //Values are split based on our crm's csv file format.

		for (int i = 0; i < someVals.length; i++) {
			someVals[i] = someVals[i].replace("\"", "");

		}


		inputfile.close();
		filscan.close();
		return someVals;
	}
	//this method creates an array list of string arrays for use in offerpad class. Called from OfferPad.
	public ArrayList<String []> makeArrays() throws IOException
	{
		
		Scanner filscan = new Scanner(testFile, "UTF-8");
		String trash = filscan.nextLine();  //remove the header line of the csv file
		ArrayList<String []> CustomerList = new ArrayList<String []>();
		while(filscan.hasNextLine())
		{
		getText = filscan.nextLine();

		getText.trim();
		someVals = getText.split("\",\""); 
		CustomerList.add(someVals);
		}
		inputfile.close();
		filscan.close();
		return CustomerList;
	
		
	
		
		}
}
