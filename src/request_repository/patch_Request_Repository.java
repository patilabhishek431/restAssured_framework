package request_repository;

import java.io.IOException;
import java.util.ArrayList;

import common_method.get_data;

public class patch_Request_Repository {
	
	public static String baseuri() {
		String baseuri = "https://reqres.in";
		return baseuri;
	}
	
	public static String resource() {
		String resource = "/api/users/2";
		return resource;
	}
	
	public static String Patch_request_tc1() throws IOException {
		ArrayList<String> data = get_data.getdataexcel("PATCH_DATA", "Tc_1");
		String Name = data.get(2);
		String Job = data.get(3);
		String request_body = "{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return request_body;
	}
}
