import java.io.File;
import java.io.Serializable;


//This class represents the attributes of various utility companies.
public class UtilityCompany implements Serializable{
	private static final long serialVersionUID = 5L;
	transient private static String homedir = System.getProperty("user.home"); 
	transient private static String delim = System.getProperty("file.separator");
	transient final static File productionPath = new File(homedir + delim + "Dropbox" //where objects will be serialized
	+ delim + "Utility and Permiting" + delim + "Application File" + delim + "Utility Companies");
	private String Name;
	private double rebateAmount;
	private String storageFileName;
	private String templateFileName;
	
	public UtilityCompany(String Name, double rebateAmount, String templateFileName)
	{
		this.Name=Name;
		this.rebateAmount=rebateAmount;
		this.storageFileName = productionPath.getAbsolutePath() + delim + Name + ".data";
		this.templateFileName=templateFileName;
	}
	
	public String getName()
	{
		return Name;
	}
	
	public double getrebateAmount()
	{
		return rebateAmount;
	}
	
	public static File getProductionPath()
	{
		return productionPath;
	}
	public String getStorageFileName()
	{
		return storageFileName;
	}
	public String getTemplateFileName()
	{
		return templateFileName;
	}
	
	public String toString()
	{
		return Name;
	}
}
