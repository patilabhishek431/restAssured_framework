package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class common_method_utilities {
	
	public static void evedence_file_creater(String filename,String request,String response) throws IOException
	{
		File newtextfile = new File("C:\\Users\\patil\\Desktop\\Softwear testing course\\RestAssured_evidence\\" + filename + ".txt");
		if (newtextfile.createNewFile())
		{
		FileWriter datawriter = new FileWriter(newtextfile);
		datawriter.write("Request body is : \n" + request + "\n\n");
		datawriter.write("Response body is : \n" + response );
		datawriter.close();
		System.out.println("Request and Response body data sucessfully saved in :" + newtextfile.getName());
		}
		else
		{
			System.out.println(newtextfile.getName()+ " This File already exists, take a backup of it.");
		}
	}
}
