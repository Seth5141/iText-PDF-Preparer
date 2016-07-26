//This class manipulates the input phone number (which is typically raw, and formats it as (###) (###)-(####);
public class StringManipulator {
	//NumberGenerate parses all of the numbers from the input phone number and stores them.
	public static char[] NumberGenerate(String input)
	{
	int nine=57;
	int zero=48;
		char [] myCharSet = new char[input.length()];
		int j = 0;
		for(int i = 0; i<input.length(); i++)
		{
			char c = input.charAt(i);
			int r = c;
			if(r>nine || r<zero)
			{
				
			}
			else
			{
				myCharSet[j]= c;
				j ++;
			}
				
		}
		return myCharSet;
	}
	//PhoneNumberCreator adds in the formatting for the customer.
	public static String PhoneNumberCreator(char[] numberSet)
	{
		char[] phoneChar = new char[] {'(',numberSet[0], numberSet[1], numberSet[2], ')', ' ', numberSet[3], numberSet[4], numberSet[5], '-', numberSet[6], numberSet[7], numberSet[8], numberSet[9]};
		String phoneString = new String(phoneChar);
		return phoneString;
	}
}
