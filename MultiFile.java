import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

//This class is a Jpanel that opens when a multiple customer file is uploaded. it then creates enough
//labels and fields to enter the necessary information for each address.
public class MultiFile extends JPanel {
	ArrayList<String []> customerList;
	private ArrayList<LabelAndField> customerNames;
	private FileHandler fileCreator;
	private JButton CreateButton;
	GridLayout ThisGrid;
	JPanel myGrid;
	JScrollPane myScroll;
	private Customer myCust;
	private Inverter myInvert;
	private Module myMod;
	private ExtraData mydata;
	private Preparer myPrep;
	JPanel topGrid;
	
	public MultiFile(File inputFile) throws IOException
	
	{	
		fileCreator = new FileHandler(inputFile);
		customerList = fileCreator.makeArrays();
		customerNames = new ArrayList<LabelAndField>();
		for( int i = 0; i<customerList.size(); i++)
		{	
			String name = (customerList.get(i))[1];
			LabelAndField myLabel = new LabelAndField(name);
			customerNames.add(myLabel);
		}
		topGrid = new JPanel(new GridLayout(1, 2));
		topGrid.add(new JLabel("Address"));
		topGrid.add(new JLabel("Account Number"));
		ThisGrid = new GridLayout(customerNames.size()+1,1);
		myGrid = new JPanel(ThisGrid);
		setLayout(new BorderLayout(40, 20));
		
		myGrid.add(topGrid);
		for( int i = 0; i<customerNames.size(); i++)
		{
			myGrid.add(customerNames.get(i));
		}
		myScroll = new JScrollPane(myGrid);
		myScroll.setPreferredSize(new Dimension(400, 400));
		CreateButton = new JButton("Create Files");
		CreateButton.addActionListener(new createNewFile());
		add(myScroll, BorderLayout.NORTH);
		add(CreateButton, BorderLayout.SOUTH);
		
	}



//creates the new pdf files for each customer in the offerpad csv file.
private class createNewFile implements ActionListener {
	
	String homedir = System.getProperty("user.home");
	String delim = System.getProperty("file.separator");
	File myPath;
	File[] myFiles;
	
	
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i<customerList.size(); i++)
		{	
		myPrep = AppStart.Preparers.pickPreparer("Elevation Solar LLC.");
		myCust = new Customer(customerList.get(i)[0], customerList.get(i)[1], customerList.get(i)[2], customerList.get(i)[3], customerList.get(i)[4], customerList.get(i)[5],
				customerList.get(i)[6]);
		myInvert = AppStart.Inverters.getInverter(customerList.get(i)[9]);
		myMod = AppStart.Modules.pickModule(customerList.get(i)[14], customerList.get(i)[13], customerList.get(i)[11], customerList.get(i)[12], customerList.get(i)[10]);
		mydata = new ExtraData(customerList.get(i)[8], "0", customerList.get(i)[7], customerNames.get(i).getText(), myMod,
		myInvert,1.2, customerList.get(i)[15]);
		myPath = new File (homedir + delim + "Dropbox (Elevation Solar)" + delim + "Utility and Permiting" + delim +  "Application File" + delim + "Template Documents" + delim + "APS Application" + delim + "Offer Pad Deals");  
		myFiles = myPath.listFiles(new FilenameFilter() {
			public boolean accept(File peoplePath, String name) {
				return name.toLowerCase().endsWith(".pdf");
				
			}
		});
		
		for (int j = 0; j<myFiles.length; j++)
		{
			try {
				PDFHandler.createfile(myFiles[j], myCust, myInvert, mydata, myPrep, myMod);
			} catch (DocumentException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
			
		}
			
	
		
	}
}	
}


