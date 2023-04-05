package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_method_post_api;
import common_method.common_method_utilities;
import io.restassured.path.json.JsonPath;
import request_repository.post_Request_Repository;


public class post_Tc_1 {
	@Test
	public static void orchestrator() throws IOException
	{
		int response_statuscode = 0;
		String response_body = "";

		String base_uri = post_Request_Repository.baseurl();
		String Resource = post_Request_Repository.resource();
		String req_body = post_Request_Repository.Post_request_tc1();
		
		for (int i=0 ; i<5 ; i++)
		{
			response_statuscode = common_method_post_api.responsestatuscode_extractor(base_uri, Resource, req_body);
			if (response_statuscode == 201)
			{
				response_body = common_method_post_api.responsebody_extractor(base_uri, Resource, req_body);
				responsebody_validator(response_body);
				common_method_utilities.evedence_file_creater("POST_TC_1",req_body,response_body);
				System.out.println();
				break;
			}
			else 
			{
				System.out.println("Correct response code is not found in itration no :" +i);
			}
		}
		Assert.assertEquals(response_statuscode, 201);
}
	
	public static void responsebody_validator(String response_body) 
	{
		// create JSON path object to extract response body parameters
		JsonPath jsp = new JsonPath(response_body);

		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String date = jsp.getString("createdAt");
		String res_date = date.substring(0, 10);
		String act_date = LocalDate.now().toString();

		System.out.println("Below response is shown for Post request :");
		System.out.println("name - " + res_name + "\njob - " + res_job + "\nid - " + res_id + "\ncreatedAt - " + date + "\nexpected date - "+res_date+ "\nactual date - " + act_date + "\n");

		// validate response body parameter
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "leader");
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_date, act_date);
	}
}