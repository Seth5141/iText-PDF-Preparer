import java.io.*;

//Serializer class takes an object and serializes it into the file provided as an argument to Serialize.
public class Serializer {
	private static FileOutputStream fileout;
	private static ObjectOutputStream Objectout;
	
	public static void Serialize(Object object, String myFile)throws FileNotFoundException, IOException
	{
		File theFile = new File(myFile);
		theFile.getParentFile().mkdirs();
		fileout = new FileOutputStream(myFile);
		Objectout = new ObjectOutputStream(fileout);
		Objectout.writeObject(object);
		Objectout.close();
	}

}
