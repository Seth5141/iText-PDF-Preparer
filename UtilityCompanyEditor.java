import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


//See ModuleEditor and InverterEditor This class is the same.
public class UtilityCompanyEditor extends JPanel{
	private LabelAndField SFID;
	private LabelAndField RebateAmount;
	private LabelAndField templateFileName;
	private JButton addit;
	private JLabel feedback;
	private JList NameList;
	private JPanel FormPanel;
	private JScrollPane myScroll;
	Vector<UtilityCompany> Names;
	public UtilityCompanyEditor (ArrayList mylist)
	{	FormPanel = new JPanel();
		FormPanel.setLayout(new GridLayout(7,1));
		SFID = new LabelAndField("SalesforceID:");
		RebateAmount = new LabelAndField("Rebate Amount:");
		templateFileName = new LabelAndField("Template File Name");
		addit = new JButton("Create/Update:");
		addit.addActionListener(new CreateButton());
		feedback = new JLabel("");
		FormPanel.add(SFID);
		FormPanel.add(RebateAmount);
		FormPanel.add(templateFileName);
		FormPanel.add(addit); 
		FormPanel.add(feedback);
		Names = new Vector();
		for(int i= 0; i< mylist.size(); i++)
		{	
			UtilityCompany thisUtilityCompany = (UtilityCompany) mylist.get(i);
			Names.addElement(thisUtilityCompany); 
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
	public UtilityCompany pickUtilityCompany(String name)
	{
		for(int i = 0; i < Names.size(); i++)
		{
			if (Names.get(i).toString().equals(name))
			{	
				UtilityCompany myUtility = Names.get(i);
				
				return myUtility;
			}	
		}
		GUI.instructions.setText("Inverter or module not identified");
		return Names.get(0);
	}
	
	private class CreateButton implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			try {
				UtilityCompany thisUtility = new UtilityCompany(SFID.getText(), Double.parseDouble(RebateAmount.getText()), templateFileName.getText());
				Names.add(thisUtility);
				NameList.updateUI();
				Serializer.Serialize(thisUtility, thisUtility.getStorageFileName());
				feedback.setText("You Did It!!");
			} 
			catch (NumberFormatException e1) {
				feedback.setText("Please enter integer values for age and id");
			} catch (FileNotFoundException e1) {
				feedback.setText("Sorry, we couldn't find the file");
			} catch (IOException e1) {
			feedback.setText(e1.getMessage());
			}
			
		}
		
	}
	private class NameSelector implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			UtilityCompany thisUtility = (UtilityCompany) NameList.getSelectedValue();
			SFID.setText(thisUtility.toString());
			RebateAmount.setText(Double.toString(thisUtility.getrebateAmount()));
			templateFileName.setText(thisUtility.getTemplateFileName());
			
		}
		
	}

}



