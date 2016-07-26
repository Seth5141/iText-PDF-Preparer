import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
//Deserialize class will deserialize all of the serialized objects in a folder provided by the user. (for this program, 
// each serializeable class has a filepath in which to store each object.)
public class deserialize {
	transient private static String delim = System.getProperty("file.separator");
	
	static private FileInputStream PeopleInput;
	static private ObjectInputStream FileInput;
	static private String[] peopleString;

	public static ArrayList<Object> deserializePeople(File myPath) throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Object> mylist = new ArrayList<Object>();

		peopleString = myPath.list(new FilenameFilter() {
			public boolean accept(File peoplePath, String name) {
				return name.toLowerCase().endsWith(".data"); //The string array only accepts names ending with.data.
															 //Initially system files were being placed in the array and causing errors.
			}

		});
		if (peopleString != null)
		{
		for (int i = 0; i < peopleString.length; i++) {

			PeopleInput = new FileInputStream(myPath.getPath() + delim + peopleString[i]);
			FileInput = new ObjectInputStream(PeopleInput);
			mylist.add(FileInput.readObject());
		}

		return mylist;
		}
		else 
		{
			return mylist;
		}
	}
}
