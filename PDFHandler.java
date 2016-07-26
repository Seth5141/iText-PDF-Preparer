import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.*;
//This is the main class that creates the new filled pdf forms for our customers. it utilizes the itext library 
//to fill in formfields in pdf. 

public class PDFHandler {

	public static void createfile(File inputfile, Customer myCust, Inverter myInvert, ExtraData mydata, Preparer myPrep,
			Module myMod) throws DocumentException, IOException {
		String[] LastFirst;
		LastFirst = myCust.getName().split(" ");
		String homeDir = System.getProperty("user.home");
		String separator = System.getProperty("file.separator");
		String INPUTFILENAME = inputfile.getPath();
		String inputFileNameShort = inputfile.getName();
		String OUTPUTFILENAME;
		inputFileNameShort = inputFileNameShort.substring(0, inputFileNameShort.lastIndexOf('.'));
		if(myCust.getName().contains("Offer"))//some error handling for offerpad customers.
		{
			OUTPUTFILENAME = homeDir + separator + "Dropbox" + separator + "Utility and Permiting" + separator + "Multi File" +
					separator + myCust.getStreetAddress() + " " + "(" + myCust.getState() + ")" + separator + inputFileNameShort + ".pdf";	
		}
		else
		{
			OUTPUTFILENAME = homeDir + separator + "Dropbox (Elevation Solar)" + separator + "Utility and Permiting" + separator + "1 Utility Submissions" + 
			separator +LastFirst[(LastFirst.length-1)] + ", " + LastFirst[0] + " " + "(" + myCust.getState() + ")" + separator + inputFileNameShort + "-" + myCust.getName() + ".pdf";
		}
		File outfile = new File(OUTPUTFILENAME);
		if (!outfile.getParentFile().exists())//make sure the directories exist.
		{
			boolean mkdirSts = outfile.getParentFile().mkdirs();
			GUI.instructions.setText(String.format("%s creating Files for %s", mkdirSts ? "Success" : "Failure", myCust.getName()));
		}
		
		
		//The stamper basically copies the reader's contents into a new pdf. 
		//then, thanks to itext, I can easily fill the formfields with what i need.
		//The string literals are all the names in the pdf forms that need filled.
		PdfReader reader = new PdfReader(INPUTFILENAME);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(OUTPUTFILENAME));
		AcroFields fields = stamper.getAcroFields();
		fields.setGenerateAppearances(true);
		fields.setField("Customer Name", myCust.getName());
		fields.setField("Account Number", mydata.getAccountNumber());
		fields.setField("Customer Street Address", myCust.getStreetAddress());
		fields.setField("Customer City", myCust.getCity());
		fields.setField("Customer Zip Code", myCust.getZipCode());
		fields.setField("Customer Phone", myCust.getCellNumber());
		fields.setField("Customer E-mail", myCust.getEmail());
		fields.setField("Preparer Name", myPrep.getName());
		fields.setField("License Number", myPrep.getLicencenum());
		fields.setField("Preparer Street Address", myPrep.getStreetAddress());
		fields.setField("Preparer City", myPrep.getCity());
		fields.setField("Preparer Zip Code", myPrep.getZipCode());
		fields.setField("Preparer Phone#", myPrep.getWorkPhoneNumber());
		fields.setField("Preparer E-mail", myPrep.getEmail());
		fields.setField("Module Brand", myMod.getBrand());
		fields.setField("Module Number", myMod.getNumber());
		fields.setField("Module qty", myMod.getQuantityString());
		fields.setField("ptc rating", myMod.getptcRating());
		fields.setField("Array Azimuth", myMod.getArrayAzimuth());
		fields.setField("Inverter Brand", myInvert.getBrand());
		fields.setField("Inverter Number", myInvert.getNumber());
		fields.setField("Inverter Efficiency", myInvert.getefficiencyString());
		fields.setField("DC kiloWatts", myMod.getDCkiloWatts() + "kW DC");
		fields.setField("Complete Date", mydata.getCompleteDate());
		fields.setField("Rebate Amount", mydata.getRebateAmount());
		fields.setField("Customer Address", myCust.getFullAddress());
		fields.setField("Array Tilt degrees", myMod.getArrayTilt());
		fields.setField("Customer + Customer Address", myCust.getCustomerAdd());
		fields.setField("Preparer Address", myPrep.getFullAddress());
		fields.setField("AC kiloWatt", myInvert.getACKiloWatts());
		fields.setField("AC Export", mydata.getACExport());
		fields.setField("Amperage Rating", myInvert.getAmperage());
		fields.setField("Customer City, State, Zip", myCust.getCityStateZip());
		fields.setField("Inverter Name + Brand + Quantity", myInvert.getInverterNameBrandQty());
		fields.setField("Module Name + Brand + Quantity", myMod.getNumberBrandQty());
		fields.setField("Racking", myMod.getRacking());
		fields.setField("System Production", mydata.getProduction());
		fields.setField("System Cost", mydata.getCost());
		fields.setField("Date", mydata.getTodayDate());
		fields.setField("Module Watts", myMod.getWatts() + " " + "Watts");
		fields.setField("Price Per Watt", mydata.getPricePerWatt());
		stamper.close();
	}
}
