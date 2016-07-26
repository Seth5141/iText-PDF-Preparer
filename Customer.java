//The basic class for storing customer information. A customer is instantiated each time a csv report is read
//(see GUI subclass createnewfile appx. line 138).
public class Customer extends Entity {
	String CityStateZip;
	String CustomerAdd;
	static String comma = ",";

	public Customer(String Name, String StreetAddress, String City, String State, String ZipCode, String Email,
			String Phone) {
		this.Name = Name;
		this.ShortName = Name;
		this.StreetAddress = StreetAddress;
		this.City = City;
		this.State = State;
		this.ZipCode = ZipCode;
		this.WorkPhoneNumber = StringManipulator.PhoneNumberCreator(StringManipulator.NumberGenerate(Phone));
		CellPhoneNumber = WorkPhoneNumber;
		EmailAddress = Email;
		FullAddress = this.StreetAddress + space + this.City + comma + space + this.State + space + this.ZipCode;
		CityStateZip = this.City + comma + space + this.State + space + this.ZipCode;
		CustomerAdd = this.Name + space + FullAddress;
	}

	public String getCityStateZip() {
		return CityStateZip;
	}

	public String getCustomerAdd() {
		return CustomerAdd;
	}

}
