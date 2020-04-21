import io.restassured.RestAssured;
import files.CommonFunctions;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class basics5 {

	@Test
	public void Test1() {
		
		//first thing is to get the base URL
		
		RestAssured.baseURI="https://maps.googleapis.com";
		//RestAssured.baseURI="http://216.10.245.166";
			//given block says what all you are doing to hit a request.
		
		Response res = given().
				//param("key","http://216.10.245.166").
				param("key","AIzaSyAq0ImpOj9J9H-TVwIjCQLcPwJ_6HxvR7Q").
				param("radius","500").
				param("location","-33.8670522,151.1957362").log().all(). //you can log all the request parameters, headers etc.
				when().
				get("/maps/api/place/nearbysearch/json").
				//get("/maps/api/place/add/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("results[0].name",equalTo("Sydney")).and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).log().all().
				//you can also log the response.
				extract().response();
		JsonPath js= CommonFunctions.resptoJson(res);
		
		//Response will return 20 results means it will return an array of name result of size 20. get the length and print.
		int count=js.get("results.size()");
		System.out.println(count); 
		
		//Now you have to get names from all 20 indices of array so for that use for loop and traverse each and every indice and get name.

		for(int i=0;i<count;i++)
		{
			System.out.println(js.get("results["+i+"].name")+"");
		}
	}

}
