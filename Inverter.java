import java.io.File;
import java.io.Serializable;
//A serializable class objects of which represent different solar inverters.
public class Inverter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String key;
	protected String brand;
	protected String Number;
	protected String InverterNameBrandQty; //A string value combining name brand and quantity for use in ES installation agreements.
	protected String efficiency;
	protected double Efficiency;
	protected String Amperage;
	protected String ACKiloWatts;
	protected String quantity;
	transient private static String homedir = System.getProperty("user.home");
	transient private static String delim = System.getProperty("file.separator");
	transient final static File productionPath = new File(homedir + delim + "Dropbox" + delim +
			"Utility and Permiting" + delim + "Application File" + delim  + "Inverter"); 
	//The above variable is the path for the folder in which serialized inverters are stored.
	public Inverter(String key, String brand, String Number, String efficiency, String Amperage, String ACKiloWatts)
	{
		this.key = key;
		this.brand = brand;
		this.Number = Number;
		this.efficiency = efficiency;
		this.Efficiency = Double.parseDouble(efficiency);
		this.Amperage = Amperage;
		this.ACKiloWatts = ACKiloWatts;
		quantity = "1";
		InverterNameBrandQty = brand + " " + Number + " " + "(Qty. 1)";
	}

	//returns Salesforce key of inverter
	public String getKey()
	{
		return key;
	}
	//returns brand of inverter
	public String getBrand() {
		return brand;
	}
	//returns model number of inverter
	public String getNumber() {
		return Number;
	}
	//returns specialized string containing name, brand, and quantity.
	public String getInverterNameBrandQty() {
		return InverterNameBrandQty;
	}
	//returns the string value of efficiecy
	public String getefficiencyString() {
		return efficiency;
	}
	//returns the double representation of effiency
	public double getefficiencyDouble() {
		return Efficiency;
	}
	//returns the Amperage rating of the inverter.
	public String getAmperage() {
		return Amperage;
	}
	//returns the AC kiloWatt production of the inverter
	public String getACKiloWatts() {
		return ACKiloWatts;
	}
	//Sets a new quantity of inverters.
	public void setQuantity(String quantity)
	{
		this.quantity = quantity;
		this.InverterNameBrandQty = this.brand + " " + this.Number + " " + "(qty. " + quantity +")";
		
	}
	//to string simply returns the Salesforce key of the inverter
	public String toString()
	{
		return key;
	}
}
