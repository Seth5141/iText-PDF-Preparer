import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

//Class similar to inverter editor. Allows the user to store and modify different modules for use in filling out forms.
public class ModuleEditor extends JPanel {
	private LabelAndField SFID;
	private LabelAndField Brand;
	private LabelAndField Name;//see LabelAndField class included in package
	private LabelAndField ptcRating;
	private LabelAndField Watts;
	private JButton addit;
	private JLabel feedback;
	private JTextArea Objectpage;
	private JList NameList;
	private JPanel FormPanel;
	private JScrollPane myScroll;
	Vector<Module> Names;
	public ModuleEditor(ArrayList mylist)
	{	FormPanel = new JPanel();
		FormPanel.setLayout(new GridLayout(7,1));
		SFID = new LabelAndField("SalesforceID:");
		Brand = new LabelAndField("Module Brand:");
		Name = new LabelAndField("Module Name:");
		ptcRating = new LabelAndField("PTC Rating:");
		Watts = new LabelAndField("Wattage (e.g. .260):");
		addit = new JButton("Create/Update:");
		addit.addActionListener(new CreateButton());
		feedback = new JLabel("");
		FormPanel.add(SFID);
		FormPanel.add(Brand);
		FormPanel.add(Name);
		FormPanel.add(ptcRating);
		FormPanel.add(Watts);
		FormPanel.add(addit); 
		FormPanel.add(feedback);
		Names = new Vector();
		for(int i= 0; i< mylist.size(); i++)
		{	
			Module thisModule = (Module) mylist.get(i);
			Names.addElement(thisModule); 
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
	//called from GUI. returns a module if it is found, or lets the user know that the desired object was not found.
	public Module pickModule(String name, String qty, String Azimuth, String Tilt, String Racking)
	{
		for(int i = 0; i < Names.size(); i++)
		{
			if (Names.get(i).toString().equals(name))
			{	
				Module myMod = Names.get(i);
				myMod.setQuantity(qty);
				myMod.setAzimuth(Azimuth);
				myMod.setTilt(Tilt);
				myMod.setDCkiloWatts();
				myMod.setRacking(Racking);
				myMod.setNumberBrandQty();
				return myMod;
			}	
		}
		GUI.instructions.setText("module not identified");
		return Names.get(0);
	}
	//creates a new module from the current values of the label and fields on the pane.
	private class CreateButton implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			try {
				Module thisModule = new Module(SFID.getText(), Watts.getText(), Brand.getText(), Name.getText(), ptcRating.getText());
				Serializer.Serialize(thisModule, thisModule.getFileName());
				Names.add(thisModule);
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
	//displays all of the data on the gui panel and makes it editable.
	private class NameSelector implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			Module thisModule = (Module) NameList.getSelectedValue();
			SFID.setText(thisModule.toString());
			Brand.setText(thisModule.getBrand());
			Name.setText(thisModule.getNumber());
			ptcRating.setText(thisModule.getptcRating());
			Watts.setText(thisModule.getWatts());
			
		}
		
	}

}



