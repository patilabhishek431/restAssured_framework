package common_method;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class common_method_get_api {
	
	public static int responsestatuscode_extractor(String baseuri,String resource, String requestbody) {
		
		RestAssured.baseURI= baseuri;
		
		int responsestatuscode = given().header("Content-Type","apllication/json")
				.when().get(resource).then().extract().statusCode();
		return responsestatuscode;
	}
	
	public static String responsebody_extractor(String baseuri,String resource,String requestbody) {
        
		RestAssured.baseURI= baseuri;
		
		String responsebody = given().header("Content-Type","apllication/json")
				.when().get(resource).then().extract().response().asString();
		return responsebody;
	}
}
