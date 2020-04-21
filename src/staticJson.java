import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.CommonFunctions;
import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class staticJson {

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
	
	}
	
	@Test()

	public void PostDataExcel() throws Exception {
		
	RestAssured.baseURI = prop.getProperty("HOST2");
	//you have to centralise your environment variables that are common to all like id password or base URL so put it into properties file.

	//Task1 - Grab The Response into a String
	Response res = given().header("Content-Type","application/json").
			//Key will also be common to all scripts so put that as well in properties file.
	//body will be taken from a notepad file which is having data in key value pair so get rid of all the formatting but only good for static json
	body(staticJson.GenerateStringFromResource("C:\\Users\\Vishal\\Documents\\static.json")).
	when().
	post("/Library/Addbook.php").
	then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();
	JsonPath js=CommonFunctions.resptoJson(res);
	String id=js.get("ID");
	System.out.println(id);
	
	System.out.println("--Delete Request starting--");
      String del = given().body("{\n" + " \n" + "\"ID\" : \"" + id + "\"\n" + " \n" + "}\n" + "").when()
            .post("Library/DeleteBook.php").getBody().asString();
      System.out.println(del);
      System.out.println("-----------------");

			}
	
	
	public static String GenerateStringFromResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path))); //takes the path, read all bytes and converts it into String.
		
	}
	

}
