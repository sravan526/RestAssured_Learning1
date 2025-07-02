package tests;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse
{

	
	public static void main(String[] args)
	{
		
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		//Print the number of courses returned By API
		int count =js.getInt("courses.size()");
		System.out.println("the count is"+count);
		
		//Print the purchas amount
		
		int puchaseamount = js.getInt("dashboard.purchaseAmount");
		System.out.println("The total purchse amount is" +puchaseamount);
		
		//print the title of the first course
		String firstcoursetitle= js.get("courses[0].title");
		System.out.println("the first course title is "+firstcoursetitle);
		
		
		

	}

}
