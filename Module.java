
import java.io.Serializable;
import java.io.File;
import java.text.DecimalFormat;

//A serializeable class that represents the attributes of different solar panels.
public class Module implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	transient private static String homedir = System.getProperty("user.home");
	transient private static String delim = System.getProperty("file.separator");
	transient final static File productionPath = new File(homedir + delim + "Dropbox" + delim + "Utility and Permiting" + delim + "Application File" + delim + "Modules");
	protected double Watts;
	protected String Token;
	protected String Brand;
	protected String Number;
	protected String NumberBrandQty;
	protected String ArrayTilt;
	protected String ArrayAzimuth;
	protected String quantity;
	protected String DCkiloWatts;
	protected String Racking;
	protected String ptcRating;
	protected double ptcrate;
	protected double DCkWatts;
	protected int Quantity;
	protected String fileName;
	protected final String space = " ";
	protected static DecimalFormat myFormat = new DecimalFormat("##.####");
	//constructor uses values input in ModuleEditor class.
	public Module(String Token, String Watts, String Brand, String Number, String ptcRating ) {
		this.Watts = Double.parseDouble(Watts);
		this.Brand = Brand;
		this.Number = Number;
		this.ptcRating = ptcRating;
		this.ptcrate = Double.parseDouble(ptcRating);
		this.Token = Token;
		this.fileName = productionPath.getAbsolutePath() + delim + Token + ".data";
	}
	//sets the racking as obtained from the csv file
	public void setRacking(String Racking)
	{
		this.Racking = Racking;
	}
	//sets a string and integer version of module quantity as input from user.
	public void setQuantity(String Quantity)
	{	
		this.quantity = Quantity; 
		this.Quantity = Integer.parseInt(Quantity);
	}
	//sets the azimuth as obtained from the csv file
	public void setAzimuth(String Azimuth)
	{
		ArrayAzimuth = Azimuth;
	}
	//tilt obtained from csv file
	public void setTilt(String Tilt)
	{
		ArrayTilt = Tilt;
	}
	//Creates a string used in installation agreements 
	public void setNumberBrandQty()
	{
		NumberBrandQty = Brand + " " + Number + " " + "(Qty. " + quantity + ")";
	}
	//sets a string and double version of DCkilowatts.
	public void setDCkiloWatts()
	{
		DCkWatts = Double.parseDouble(myFormat.format(Watts * Quantity));
		DCkiloWatts = myFormat.format(Watts * Quantity);
	}
	
	
	//myriad self explanatory getter methods. 
	public String getBrand() {
		return Brand;
	}

	public String getNumber() {
		return Number;
	}

	public String getNumberBrandQty() {
		return NumberBrandQty;
	}

	public String getArrayTilt() {
		return ArrayTilt;
	}

	public String getArrayAzimuth() {
		return ArrayAzimuth;
	}

	public String getQuantityString() {
		return quantity;
	}
	public double getDCkWatts()
	{
		return DCkWatts;
	}

	public String getDCkiloWatts() {
		return DCkiloWatts;
	}

	public String getRacking() {
		return Racking;
	}

	public String getptcRating() {
		return ptcRating;
	}

	public double getptcrate() {
		return ptcrate;
	}

	public int getQuantityInt() {
		return Quantity;
	}
	public String getFileName() {
		return fileName;
	}
	public String getWatts() {
		return Integer.toString((int)(Watts*1000));
	}
	public String toString(){
		return Token;
	}
}
