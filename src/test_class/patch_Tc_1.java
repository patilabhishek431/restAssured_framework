package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_method_patch_api;
import common_method.common_method_utilities;
import io.restassured.path.json.JsonPath;
import request_repository.patch_Request_Repository;


public class patch_Tc_1 {
	@Test
	public static void orchestrator() throws IOException {
		
		int response_statuscode = 0;
		String responsebody = null;
		
		String baseuri = patch_Request_Repository.baseuri();
		String resource = patch_Request_Repository.resource();
		String requestbody = patch_Request_Repository.Patch_request_tc1();
		
		for (int i=0; i<5; i++)
		{
			response_statuscode = common_method_patch_api.responsestatuscode_extractor(baseuri, resource, requestbody);
			if (response_statuscode==200)
			{
				responsebody = common_method_patch_api.responsebody_extractor(baseuri, resource, requestbody);
				response_body_validator(responsebody);
				common_method_utilities.evedence_file_creater("PATCH_TC_1", requestbody, responsebody);
				System.out.println();
				break;
			}
			else
			{
				System.out.println("Correct response code is not found hence retrying : " +i);
			}
		}
		Assert.assertEquals(response_statuscode, 200);
	}
	
		public static void response_body_validator(String responsebody)
		{
			JsonPath jsp = new JsonPath(responsebody);
			
			String res_name = jsp.getString("name");
			String res_job = jsp.getString("job");
			String date = jsp.getString("updatedAt");
			String res_date = date.substring(0, 10);
			String act_date = LocalDate.now().toString();
			
			System.out.println("Below response is shown for Patch request :");
			System.out.println("name: " +res_name+ "\njob: " +res_job+ "\nupdatedAt: " +date+ "\nExpected date: " +res_date+ "\nActual date: " +act_date+ "\n");
			
			Assert.assertEquals(res_name, "morpheus");
			Assert.assertEquals(res_job, "zion resident");
			Assert.assertEquals(res_date, act_date);
		}
}
