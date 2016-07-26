//Seed point of program, contains the main method
import java.awt.Container;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UnsupportedLookAndFeelException;

public class AppStart {
	public static ModuleEditor Modules;//all classes extending JPanel
	public static InverterEditor Inverters;
	public static PreparerEditor Preparers;
	public static UtilityCompanyEditor Utilities;
	public static JTabbedPane myTabs;
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		JFrame frame = new JFrame("Utility Submissions");
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = frame.getContentPane();
		myTabs = new JTabbedPane();
		ArrayList modVector = deserialize.deserializePeople(Module.productionPath);//deserializes all previously stored modules, 
		ArrayList InvertVector = deserialize.deserializePeople(Inverter.productionPath);//	Inverters, Preparers, and utilities at startup
		ArrayList PrepVector = deserialize.deserializePeople(Preparer.productionPath);
		ArrayList UtilVector = deserialize.deserializePeople(UtilityCompany.getProductionPath());
		Modules = new ModuleEditor(modVector);
		Inverters = new InverterEditor(InvertVector);
		Preparers = new PreparerEditor(PrepVector);
		Utilities = new UtilityCompanyEditor(UtilVector);
		myTabs.add("Application Creator", new GUI()); //initial tabs on the GUI.
		myTabs.add("Module Editor", Modules);
		myTabs.add("Inverter Editor", Inverters);
		myTabs.add("Preparer Editor",Preparers);
		myTabs.add("Utility Company Editor", Utilities);
		content.add(myTabs);
		frame.setSize(750, 500);
		frame.setVisible(true);

	}
}
