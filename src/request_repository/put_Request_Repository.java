package request_repository;

import java.io.IOException;
import java.util.ArrayList;

import common_method.get_data;

public class put_Request_Repository {
	
	public static String baseuri() {
	String baseuri = "https://reqres.in";
	return baseuri;
	}
	
	public static String resource() {
		String resourse = "/api/users/2";
		return resourse;
	}
	
	public static String Put_request_tc1() throws IOException {
		ArrayList<String> data = get_data.getdataexcel("PUT_DATA", "Tc_1");
		String Name = data.get(2);
		String Job = data.get(3);
		String requestbody = "{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return requestbody;
	}
}
