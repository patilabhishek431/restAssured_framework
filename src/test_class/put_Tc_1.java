package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_method_put_api;
import common_method.common_method_utilities;
import io.restassured.path.json.JsonPath;
import request_repository.put_Request_Repository;


public class put_Tc_1 {
	@Test
	public static void orchestrator() throws IOException
	{
		int response_statuscode = 0;
		String response_body = null;
		String baseuri = put_Request_Repository.baseuri();
		String resource = put_Request_Repository.resource();
		String req_body = put_Request_Repository.Put_request_tc1();
		
		for (int i=0; i<5 ; i++)
		{
			response_statuscode = common_method_put_api.responsestatuscode_extractor(baseuri, resource, req_body);
			if (response_statuscode==200)
			{
				response_body = common_method_put_api.responsebody_extractor(baseuri, resource, req_body);
				responsebody_validator(response_body);
				common_method_utilities.evedence_file_creater("PUT_TC_1",req_body,response_body);
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
	
	public static void responsebody_validator(String responsebody)
	{
		JsonPath jsp = new JsonPath(responsebody);
		
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String date = jsp.getString("updatedAt");
		String res_date = date.substring(0, 10);
		String act_date = LocalDate.now().toString();
		
		System.out.println("Below response is shown for Put request :");
		System.out.println("name - " + res_name + "\njob - " + res_job + "\nupdatedAt - " + date + "\nexpected date - "+res_date+ "\nactual date - " + act_date + "\n");
		
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
		Assert.assertEquals(res_date, act_date);
}
}