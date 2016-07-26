import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.itextpdf.text.DocumentException;

//Extends Jpanel. This panel allows for input of the customers information and selection of a csv report from salesforce
//to parse and input into the preselected pdf files.
public class GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JLabel instructions;
	private JLabel PreparerName;
	private JLabel Cust2;
	private File selectedCSV;
	private JButton openDialog; //opens the dialog to select a csv report to parse.
	private JButton MakeConversion; //triggers the creation of the desired pdf files.
	private JTextField secCust;
	private JComboBox prepName;
	private String[] selectPreparer;
	private String[] getValues;
	private GridLayout thisGrid;
	private JPanel myGrid;
	private Customer myCust;
	private Inverter myInvert;
	private Preparer myPrep;
	private UtilityCompany myUtil;
	private Module myMod;
	private ExtraData mydata;
	private JButton openOfferpad; //opens dialog to select a csv report containing multiple offerpad customers.
	public GUI()

	{	
		instructions = new JLabel("Please select a CSV file and a pdf, then press create.");
		PreparerName = new JLabel("Preparer Name: ");
		selectPreparer = new String[] { "Preparer 1", "Preparer 2", "Preparer 3"};
		prepName = new JComboBox(selectPreparer);
		openDialog = new JButton("Select CSV");
		openDialog.addActionListener(new openFilesearch());
		MakeConversion = new JButton("Make New File");
		MakeConversion.addActionListener(new createNewFile());
		setLayout(new BorderLayout(40, 20));
		Cust2 = new JLabel("Second Customer:");
		secCust = new JTextField();
		myGrid = new JPanel();
		thisGrid = new GridLayout(3, 2);
		thisGrid.setHgap(20);
		thisGrid.setVgap(50);
		myGrid.setLayout(thisGrid);
		myGrid.setPreferredSize(new Dimension(500, 400));
		instructions.setForeground(Color.BLUE);
		add(instructions, BorderLayout.NORTH);
		openOfferpad = new JButton("Select OP CSV");
		openOfferpad.addActionListener(new OfferPadSearch());
		myGrid.add(PreparerName);
		myGrid.add(prepName);
		myGrid.add(Cust2);
		myGrid.add(secCust);
		myGrid.add(openDialog);
		myGrid.add(openOfferpad);

		add(myGrid, BorderLayout.CENTER);
		add(MakeConversion, BorderLayout.SOUTH);

	}
	
	// child of openDialog button 
	private class openFilesearch implements ActionListener {
		private JFileChooser fileFinder = new JFileChooser(System.getProperty("user.dir"));

		//gets selected csv file and then sends it to CSVCheck to create a new customer
		public void actionPerformed(ActionEvent e) {
			int returnVal = fileFinder.showDialog(null, "choose");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				selectedCSV = fileFinder.getSelectedFile();
				FileHandler CSVCheck;
				try {
					CSVCheck = new FileHandler(selectedCSV);
					getValues = CSVCheck.makeArray();//creates new array of values from the csv to create the customer.
					instructions.setText("created");
				} catch (IOException e1) {
					instructions.setText("Please select a Valid File");
				}
			}

		}
	}
	
	//child of openOfferpad button
	private class OfferPadSearch implements ActionListener {
		private JFileChooser filechooser = new JFileChooser(System.getProperty("user.home"));

		//when the proper file is selected, a new offerpad tab is created in the window.
		public void actionPerformed(ActionEvent e) {
			int returnVal = filechooser.showDialog(null, "choose");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				selectedCSV = filechooser.getSelectedFile();
				try {
					MultiFile myMultiFile = new MultiFile(selectedCSV);
					AppStart.myTabs.addTab("OfferPad", myMultiFile);
					AppStart.myTabs.updateUI();
					instructions.setText("Good Job!");
				} catch (IOException e1) {
					instructions.setText("Please select a Valid File");
				}
			}

		}
	}

	//child of MakeConversion button
	private class createNewFile implements ActionListener {
		
		String homedir = System.getProperty("user.home");
		String delim = System.getProperty("file.separator");
		String custFile;
		String UtilFile;
		File myPath;
		File[] myFiles;
		
		//Creates new customer, inverter, module, preparer, and extra data objects from the array of data from the csv.
		//Then creates a new folder in which to store the created pdf files.
		public void actionPerformed(ActionEvent e) {
			myPrep = AppStart.Preparers.pickPreparer((String) prepName.getSelectedItem());
			myCust = new Customer(getValues[0], getValues[1], getValues[2], getValues[3], getValues[4], getValues[5],
					getValues[6]);
			myInvert = AppStart.Inverters.getInverter(getValues[9]);
			myMod = AppStart.Modules.pickModule(getValues[14], getValues[13], getValues[11], getValues[12], getValues[10]);
			myUtil = AppStart.Utilities.pickUtilityCompany(getValues[15]);
			mydata = new ExtraData(getValues[8], getValues[17], getValues[7], getValues[16], myMod,
				myInvert, myUtil.getrebateAmount(), getValues[15]);
			
			try {
				if (secCust.getText().equals(""))
					custFile = "One Customer"; 
				else
					custFile = "Two Customer";
				
				UtilFile = myUtil.getTemplateFileName();
				
				myPath = new File (homedir + delim + "Dropbox (Elevation Solar)" + delim + "Utility and Permiting" + delim +  "Application File" + delim + "Template Documents" + delim + UtilFile + delim + custFile);  
				myFiles = myPath.listFiles(new FilenameFilter() {				//create an array of pdf files for editing with the pdf writers
					public boolean accept(File peoplePath, String name) {
						return name.toLowerCase().endsWith(".pdf");
					}
				});
				if (custFile.equals("One Customer")) //create files using proper pdf writer (i.e. one customer, or two customer)
					for (int i = 0; i<myFiles.length; i++)
					{
						PDFHandler.createfile(myFiles[i], myCust, myInvert, mydata, myPrep, myMod); 
					}
				else 
					for (int i = 0; i<myFiles.length; i++)
					{	
						TwoCustPDF.createfile(myFiles[i], myCust, secCust.getText(), myInvert, mydata, myPrep, myMod);
					}
				//if(!instructions.getText().equals("Inverter or module not identified"))
				//instructions.setText("You Did it!");
				
			} catch (DocumentException e1) {
				instructions.setText("There Was a document Error");
				e1.printStackTrace();
			} catch (IOException e1) {
				instructions.setText("There Was an io Error");
				e1.printStackTrace();
			}
			
		}
		
	}

}
