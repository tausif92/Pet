import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;
import files.Resources;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import files.PayLoad;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.CommonFunctions;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics4 {
	Properties prop=new Properties();
	
	@BeforeTest 
	public void getData() throws IOException
	{
		//you should not hard code the baseURL as if it changes then you have to change in every script so make sure to take it from
		//properties file which is nothing but  key value pair.
		
		
		//Now we need to give the path where our file is located
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Vishal\\eclipse-workspacevish\\APIPractice\\src\\files\\env.properties");
		//create a connection between fis and prop
		
	prop.load(fis);
	//prop.getProperty("HOST");
	}
	
	@Test

	public void MapAPIAddAndDeletePlaceTest() throws Exception {
		
	RestAssured.baseURI = prop.getProperty("HOST");
	//you have to centralise your environment variables that are common to all like id password or base URL so put it into properties file.

	//Task1 - Grab The Response into a String
	Response res = given().
			//Key will also be common to all scripts so put that as well in properties file.
	queryParam("key",prop.getProperty("KEY")).
	body(PayLoad.PostPayload()).
	when().
	post(Resources.getPostData()).
	then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	body("status", equalTo("OK")).extract().response();

	//Task2 - Convert response into JSON
	// we'll use the method to convert it into Json instead of writing it in Test file.
	
	JsonPath js=CommonFunctions.resptoJson(res);
	String placeID=js.getString("place_id");
	

	/*String resString=res.asString(); //convert to string first
	System.out.println(resString);
	JsonPath js=new JsonPath(resString);
	//Now take the key that you want to Delete.
	String placeID=js.getString("place_id");
	System.out.println(placeID);*/

	//Task 3 - place this place ID in Delete Request and delete the place.

	given().queryParam("key", "qaclick123").
	body("{" + "\"place_id\" : \"" + placeID + "\"" + "}").
	when().
	post("/maps/api/place/delete/json").
	then()
	.log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	body("status", equalTo("OK"));
	}

}
