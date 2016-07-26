import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

//See module and inverter editor classes. this class behaves in the same way.
public class PreparerEditor extends JPanel {
	transient private static String delim = System.getProperty("file.separator");
	private LabelAndField Name;
	private LabelAndField ShortName;
	private LabelAndField StreetAddress;
	private LabelAndField City;
	private LabelAndField State;
	private LabelAndField ZipCode;
	private LabelAndField LicenseNumber;
	private LabelAndField PhoneNumber;
	private LabelAndField EmailAddress;
	private JButton addit;
	private JLabel feedback;
	private JList NameList;
	private JPanel FormPanel;
	private JScrollPane myScroll;
	Vector<Preparer> Names;
	public PreparerEditor(ArrayList mylist)
	{	FormPanel = new JPanel();
		FormPanel.setLayout(new GridLayout(11,1));
		Name = new LabelAndField("Company Name:");
		ShortName = new LabelAndField("Shortened Name:");
		StreetAddress = new LabelAndField("Street Address:");
		City = new LabelAndField("City:");
		State = new LabelAndField("State");
		ZipCode = new LabelAndField("Zip Code");
		LicenseNumber = new LabelAndField("License Number:");
		PhoneNumber = new LabelAndField("Phone Number:");
		EmailAddress = new LabelAndField("Email Address:");
		addit = new JButton("Create/Update");
		addit.addActionListener(new CreateButton());
		feedback = new JLabel("");
		FormPanel.add(Name);
		FormPanel.add(ShortName);
		FormPanel.add(StreetAddress);
		FormPanel.add(City);
		FormPanel.add(State);
		FormPanel.add(ZipCode);
		FormPanel.add(LicenseNumber);
		FormPanel.add(PhoneNumber); 
		FormPanel.add(EmailAddress);
		FormPanel.add(addit);
		FormPanel.add(feedback);
		Names = new Vector();
		for(int i= 0; i< mylist.size(); i++)
		{	
			Preparer ThisPreparer = (Preparer) mylist.get(i);
			Names.addElement(ThisPreparer); 
		}
		NameList = new JList(Names);
		myScroll = new JScrollPane(NameList);
		NameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		NameList.addListSelectionListener(new NameSelector());
		myScroll.setPreferredSize(new Dimension(200,400));
		FormPanel.setPreferredSize(new Dimension(500,400));
		FormPanel.setAlignmentX(LEFT_ALIGNMENT);
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, FormPanel,myScroll );
		add(sp);
	}
	public Preparer pickPreparer(String Name)
	{
		for(int i = 0; i < Names.size(); i++)
		{
			if (Names.get(i).getShortName().equals(Name))
			{
				return Names.get(i);
			}	
		}
		return Names.get(0);
	}
	
	private class CreateButton implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			try {
				Preparer ThisPreparer = new Preparer(Name.getText(), ShortName.getText(), StreetAddress.getText(), City.getText(), State.getText(), ZipCode.getText(), LicenseNumber.getText(), PhoneNumber.getText(), EmailAddress.getText());
				Serializer.Serialize(ThisPreparer, Preparer.productionPath + delim + ThisPreparer.getName() + ".data");
				Names.add(ThisPreparer);
				NameList.updateUI();
				feedback.setText("You Did It!!");
			} 
			catch (NumberFormatException e1) {
				feedback.setText("Please enter integer values for age and id");
			} catch (FileNotFoundException e1) {
				feedback.setText("Sorry, we couldn't find the file");
			}catch (IOException e1) {
				feedback.setText(e1.getMessage());
			}
			
		}
		
	}
	private class NameSelector implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			Preparer ThisPreparer = (Preparer) NameList.getSelectedValue();
			Name.setText(ThisPreparer.getName());
			ShortName.setText(ThisPreparer.getShortName());
			StreetAddress.setText(ThisPreparer.getStreetAddress());
			City.setText(ThisPreparer.getCity());
			State.setText(ThisPreparer.getState());
			ZipCode.setText(ThisPreparer.getZipCode());
			LicenseNumber.setText(ThisPreparer.getLicencenum());
			PhoneNumber.setText(ThisPreparer.getWorkPhoneNumber());
			EmailAddress.setText(ThisPreparer.getEmail());
			
		}
		
	}

}



