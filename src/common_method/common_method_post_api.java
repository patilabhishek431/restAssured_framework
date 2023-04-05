package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class common_method_post_api {
	
	public static int responsestatuscode_extractor(String baseuri,String resource,String request_body) 
	{
		RestAssured.baseURI = baseuri;
		int response_statuscode = given().header("Content-Type","application/json").body(request_body)
				.when().post(resource).then().extract().statusCode();
		return response_statuscode;
	}
	
	public static String responsebody_extractor(String baseuri,String resource,String request_body) 
	{
		RestAssured.baseURI = baseuri;
		String responsebody = given().header("Content-Type","application/json").body(request_body)
				.when().post(resource).then().extract().response().asString();
		return responsebody;
	}
}
