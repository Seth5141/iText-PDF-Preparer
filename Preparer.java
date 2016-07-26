import java.io.File;
import java.io.Serializable;
//contains the information about different application preparers who represent the company.
public class Preparer implements Serializable {
	
	private static final long serialVersionUID = 3L;
	transient private static String homedir = System.getProperty("user.home");
	transient private static String delim = System.getProperty("file.separator");
	transient final static File productionPath = new File(homedir + delim + "Dropbox" + delim + "Utility and Permiting" + delim + "Application File" + delim + "Preparer");
	protected String Name;
	protected String ShortName;
	protected String StreetAddress;
	protected String City;
	protected String State;
	protected String ZipCode;
	protected String LicenseNumber;
	protected String WorkPhoneNumber;
	protected String CellPhoneNumber;
	protected String EmailAddress;
	protected final String space = " ";
	protected String FullAddress;
	
	public Preparer(String Name, String ShortName, String StreetAddress, String City, String State, String ZipCode, String LicenseNumber, String PhoneNumber, String EmailAddress) 
	{	
		
		this.Name = Name;
		this.ShortName = ShortName;
		this.StreetAddress = StreetAddress;
		this.City = City;
		this.State = State;
		this.ZipCode = ZipCode;
		this.LicenseNumber = LicenseNumber;
		this.CellPhoneNumber = PhoneNumber;
		this.WorkPhoneNumber = PhoneNumber;
		this.EmailAddress = EmailAddress;
		this.FullAddress = StreetAddress + " " + City + " " + ", " + ZipCode;
		
	}
	

	public String getName() {
		return Name;
	}

	public String getStreetAddress() {
		return StreetAddress;
	}

	public String getCity() {
		return City;
	}

	public String getState() {
		return State;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public String getLicencenum() {
		return LicenseNumber;
	}

	public String getWorkPhoneNumber() {
		return WorkPhoneNumber;
	}

	public String getCellNumber() {
		return CellPhoneNumber;
	}

	public String getEmail() {
		return EmailAddress;
	}

	public String getFullAddress() {
		return FullAddress;
	}

	public String getShortName() {
		return ShortName;
	}
	public String toString()
	{
		return ShortName;
	}

}
