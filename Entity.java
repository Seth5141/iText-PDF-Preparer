//Abstract class extended by Customer. Includes all the methods I want for it. (Preparer was going to extend this class as well)
public abstract class Entity {
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
}
