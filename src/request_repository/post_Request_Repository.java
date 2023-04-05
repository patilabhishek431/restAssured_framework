package request_repository;

import java.io.IOException;
import java.util.ArrayList;

import common_method.get_data;

public class post_Request_Repository {

	public static String baseurl() 
	{
		String baseuri = "https://reqres.in";
		return baseuri;
	}
	
	public static String resource()
	{
		String resourse = "/api/users";
		return resourse;
	}
	
	public static String Post_request_tc1() throws IOException 
	{
		ArrayList<String> data = get_data.getdataexcel("POST_DATA", "tc_1");
		String Name = data.get(2);
		String Job = data.get(3);
		String requestBody = "{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return requestBody;
	}
}
