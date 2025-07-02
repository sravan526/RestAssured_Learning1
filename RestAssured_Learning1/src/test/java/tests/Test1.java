package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;

public class Test1
{

	public static void main(String[] args)
	{
		/*
		 * GIVEN: Given will take the all the inputs from json (Query parameter, Authentication, headers, body)
		 * WHEN: When submits the API (GET, POST, PUT, DELETE) -Resouse and httpmrthods will pass here
		 * THEN: Then is used to validate the API response (Validate the response code)
		 * 
		 */
		
		// Create Place
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		String Response = given() .log().all() 
			.queryParam("key","qaclick123")
			.header("Content-Type","application/json")
			.body(payload.Addplace()).
		when()
			.post("/maps/api/place/add/json").
		then() /* .log().all() */
			.assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)")
			.extract().response().asString();
		
		System.out.println(Response);
			
		JsonPath js = new JsonPath(Response);//for parsing Jsonpath
		String Place_id = js.get("place_id");
		
		System.out.println(Place_id);
		
		//Update Place
		
		String newAddress="70 Summer walk, USA";
		
		given().log().all()
			.queryParam("key","qaclick123")
			.header("Content-Type","application/json")
			.body("{\r\n"
					+ "\"place_id\":\""+Place_id+"\",\r\n"
					+ "\"address\":\""+newAddress+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}").
		when().put("/maps/api/place/update/json").
		then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		//Read place using Get Method.
		
		String getPlaceResponse= given().log().all()
			.queryParam("key","qaclick123")
			.queryParam("place_id",Place_id).
		when().get("maps/api/place/get/json").
		then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(getPlaceResponse);
		String actualaddress = js1.getString("address");
		System.out.println(actualaddress);
		
		Assert.assertEquals(actualaddress, newAddress);
		
		


	}

}
