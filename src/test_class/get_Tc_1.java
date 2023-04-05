package test_class;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_method_get_api;
import common_method.common_method_utilities;
import io.restassured.path.json.JsonPath;
import request_repository.get_Request_Repository;


public class get_Tc_1 {
	@Test
	public static void orchestrator() throws IOException {
		
		int response_statuscode = 0;
		String responsebody = "";
		
		String baseuri = get_Request_Repository.baseuri();
		String resource = get_Request_Repository.resource();
		String request_body = get_Request_Repository.Get_request_Tc1();
		
		for (int i=0; i<5; i++) {
			response_statuscode = common_method_get_api.responsestatuscode_extractor(baseuri, resource, request_body);
			if (response_statuscode==200) 
			{
				responsebody = common_method_get_api.responsebody_extractor(baseuri, resource, request_body);
				responsebody_validator(responsebody);
				common_method_utilities.evedence_file_creater("GET_TC_1", request_body, responsebody);
				System.out.println();
				break;
			}
			else
			{
				System.out.println("Correct response code is not found in itration no :" +i);
			}
		}
		Assert.assertEquals(response_statuscode, 200);
	}
	
	public static void responsebody_validator(String responsebody) {
		
		JsonPath jsp = new JsonPath(responsebody);
		
		int count = jsp.getInt("data.size()");
		
		int exp_ID[] = {7,8,9,10,11,12};
        String exp_EMAIL[] = {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in",
        		"george.edwards@reqres.in","rachel.howell@reqres.in"};
		String exp_FNAME[] = {"Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"};
		String exp_LNAME[] = {"Lawson", "Ferguson", "Funke","Fields","Edwards","Howell"};
		String exp_AVATAR[] = {"https://reqres.in/img/faces/7-image.jpg", "https://reqres.in/img/faces/8-image.jpg",
				"https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg",
				"https://reqres.in/img/faces/11-image.jpg", "https://reqres.in/img/faces/12-image.jpg"};
		
		System.out.println("Below response is shown for Get request :");
		for (int i=0; i<count; i++)
		{
			// Declare expected parameters in object
			int res_id = exp_ID[i];
			String res_email = exp_EMAIL[i];
			String res_fname = exp_FNAME[i];
			String res_lname = exp_LNAME[i];
			String res_avatar = exp_AVATAR[i];
			
			// Extract response body parameters
			int ID = jsp.get("data["+i+"].id");
			String EMAIL = jsp.getString("data["+i+"].email");
			String FNAME = jsp.getString("data["+i+"].first_name");
			String LNAME = jsp.getString("data["+i+"].last_name");
			String AVATAR = jsp.getString("data["+i+"].avatar");
			
            System.out.println("running index :"+i+" \nId is: " +ID+ "\nEmail is : " +EMAIL+ "\nFirst name is : " +FNAME+ 
            		"\nLast name is : " +LNAME+ "\navatar is : " +AVATAR+ "\n");
            
			// validate response body parameter
			Assert.assertEquals(ID, res_id);
			Assert.assertEquals(EMAIL, res_email);
			Assert.assertEquals(FNAME, res_fname);
			Assert.assertEquals(LNAME, res_lname);
			Assert.assertEquals(AVATAR, res_avatar);
		}
	}
}
