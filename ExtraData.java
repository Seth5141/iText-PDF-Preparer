import java.text.DecimalFormat;

//Includes all of the data needed to complete the forms that is not consistent from customer to customer.
public class ExtraData {
	private String RebateAmount; //Total rebate amount
	private String Cost; //total pv system cost
	private String Production; //production in kWh of system
	private String CompleteDate; //estimated date of completion
	private int[] values; //intermediate value when creating complete date
	private String pricePerWatt; //price per watt of the system
	String todayDate; //date pulled from salesforce equals the day that financing was completed
	private String AccountNumber; //utility account number
	private String ACExport; //The amount of AC electricity to be pulled to grid
	private Module module;
	private Inverter inverter;
	private static DecimalFormat myFormat = new DecimalFormat("$#0,000"); //Format for total system cost
	private static DecimalFormat myFormatcents = new DecimalFormat("#0.00"); //format for pricePerWatt
	public ExtraData(String Cost, String Production, String financeDate, String AccountNumber, Module module,
			Inverter inverter, double RebateAmount, String UtilityCompany) {
		this.Cost = myFormat.format(Double.parseDouble(Cost));
		this.Production = addComma(Production) + " " + "kWh";
		this.module = module;
		this.inverter = inverter;
		this.AccountNumber = AccountNumber;
		this.RebateAmount = "$" + calcRebateString(RebateAmount);
		values = parseString(financeDate);
		CompleteDate = getDate(values, UtilityCompany);
		todayDate = financeDate;
		ACExport = calcACExport(inverter, module);
		this.pricePerWatt = myFormatcents.format((Double.parseDouble(Cost)/module.getDCkWatts())/1000);

	}
	private static String addComma(String inputString) //productioncomes in unformatted. the comma is added back in here
	{													//for a professional look.
	if(inputString.length() > 3)
		{
		int trimStart = inputString.length() - 3;
		String partString = inputString.substring(trimStart);
		String firstPart = inputString.substring(0, trimStart);
		return firstPart + "," + partString;
		}
	else 
		return inputString;
	}

	//rebate amount is calculated from the utility companies base rebate amount * the ptc rating of the panel
	private int calcRebateAmount(double rebateAmount) {
		int amount = (int) (this.module.getQuantityInt() * this.module.getptcrate()
				* this.inverter.getefficiencyDouble() * rebateAmount);
		return amount;
	}
	
	//converts the rebate amount to a string to insert into pdf.
	private String calcRebateString(double rebateAmount) {
		String amount = Integer.toString(calcRebateAmount(rebateAmount));
		return amount;
	}

	//parses the finance verification complete date and creates an integer array
	private int[] parseString(String date) {
		int[] stuff = new int[4];
		String[] values = date.split("/");
		for (int i = 0; i < values.length; i++) {
			stuff[i] = Integer.parseInt(values[i]);
		}
		return stuff;
	}

	//integer array is manipulated to compute estimated complete date, and the new date is returned.
	//APS date is three months from finance complete date. Other utility company dates are two months out.
	private String getDate(int[] data, String UtilComp) {
		if (UtilComp.equals("APS - Arizona Public Service"))
		{
			if (data[0] > 9)
			{
			data[2] += 1;
			data[0] -= 9;
			} 		
			else 
			{	
			data[0] += 3;
			}
		}	
		else
		{
			
			if (data[0] > 10)
			{
			data[2] += 1;
			data[0] -= 10;
			} 		
			else {
			data[0] += 2;
			}
		}
		String date = data[0] + "/" + data[1] + "/" + data[2];
		return date;
	}
	//AC Export is calculated from the lesser of (power rating of the inverter * efficiency) and total DC kilowatts of the combined modules.
	private String calcACExport(Inverter inverter, Module module)
	{	
		DecimalFormat twobit = new DecimalFormat("##.##");
		double wattschecker = (module.getDCkWatts()<Double.parseDouble(inverter.getACKiloWatts()))?module.getDCkWatts():Double.parseDouble(inverter.getACKiloWatts());
		return twobit.format(wattschecker * inverter.getefficiencyDouble());
		
	}
	//myriad self-explanatory getter methods.
	public String getACExport()
	{
		return ACExport;
	}

	public String getCost() {
		return Cost;
	}

	public String getProduction() {
		return Production;
	}

	public String getTodayDate() {
		return todayDate;
	}

	public String getCompleteDate() {
		return CompleteDate;
	}

	public String getRebateAmount() {
		return RebateAmount;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}
	
	public String getPricePerWatt() {
		return pricePerWatt;
	}
	
}
