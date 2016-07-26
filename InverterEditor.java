import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

//A GUI panel that allows one to edit the information stored in each inverter object. 
//The array list is the list of deserialized inverters(see AppStart Class)
public class InverterEditor extends JPanel {
	transient private static String delim = System.getProperty("file.separator");
	private LabelAndField SFID;
	private LabelAndField Brand;
	private LabelAndField Name;
	private LabelAndField efficiency;
	private LabelAndField Amperage;
	private LabelAndField ACKiloWatts;
	private JButton addit;
	private JLabel feedback;
	private JList NameList;
	private JPanel FormPanel;
	private JScrollPane myScroll;
	Vector<Inverter> Names;
	public InverterEditor(ArrayList mylist)
	{	FormPanel = new JPanel();
		FormPanel.setLayout(new GridLayout(8,1));
		SFID = new LabelAndField("Salesforce ID:");
		Brand = new LabelAndField("Inverter Brand:");
		Name = new LabelAndField("Inverter Name:");
		efficiency = new LabelAndField("Inverter Efficiency (e.g. .975):");
		Amperage = new LabelAndField("Amperage:");
		ACKiloWatts = new LabelAndField("AC KiloWatts:");
		addit = new JButton("Create/Update");
		addit.addActionListener(new CreateButton());
		feedback = new JLabel("");
		FormPanel.add(SFID);
		FormPanel.add(Brand);
		FormPanel.add(Name);
		FormPanel.add(efficiency);
		FormPanel.add(Amperage);
		FormPanel.add(ACKiloWatts);
		FormPanel.add(addit); 
		FormPanel.add(feedback);
		Names = new Vector();
		for(int i= 0; i< mylist.size(); i++)
		{	
			Inverter ThisInverter = (Inverter) mylist.get(i);
			Names.addElement(ThisInverter); 
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
	//called from GUI. returns an inverter if it is found, or lets the user know that the desired object was not found.
	public Inverter getInverter(String Name)
	{
		for(int i = 0; i < Names.size(); i++)
		{
			if (Names.get(i).getKey().equals(Name))
			{
				return Names.get(i);
			}	
		}
		GUI.instructions.setText("Inverter not identified");
		return Names.get(0);
	}
	
	private class CreateButton implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			try {
				Inverter ThisInverter = new Inverter(SFID.getText(), Brand.getText(), Name.getText(), efficiency.getText(), Amperage.getText(), ACKiloWatts.getText());
				Serializer.Serialize(ThisInverter, Inverter.productionPath + delim + ThisInverter.toString() + ".data");
				Names.add(ThisInverter);
				NameList.updateUI();
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
			Inverter ThisInverter = (Inverter) NameList.getSelectedValue();
			SFID.setText(ThisInverter.toString());
			Brand.setText(ThisInverter.getBrand());
			Name.setText(ThisInverter.getNumber());
			efficiency.setText(ThisInverter.getefficiencyString());
			Amperage.setText(ThisInverter.getAmperage());
			ACKiloWatts.setText(ThisInverter.getACKiloWatts());
		}
		
	}

}



