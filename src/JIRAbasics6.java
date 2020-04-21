import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import files.CommonFunctions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JIRAbasics6 {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Vishal\\eclipse-workspacevish\\APIPractice\\src\\files\\env.properties");
		prop.load(fis);
		
		//prop.get("HOST");
	}
	@Test
	public void JiraAPICreateIssue()
	{
		//Creating Issue/Defect
		
		RestAssured.baseURI= "http://localhost:8080";
		Response res=given().
		header("Cookie","JSESSIONID="+CommonFunctions.getSessionKey()).
		body("{"+
    "\"fields\": {"+
       "\"project\":{"+
          "\"key\": \"RES\""+
       "},"+
       "\"summary\": \"Issue 5 for adding comment\","+
       "\"description\": \"Creating my second bug\","+
       "\"issuetype\": {"+
          "\"name\": \"Bug\""+
       "}"+
   "}}").when().
		post("/rest/api/2/issue").then().statusCode(201).extract().response();
		
		   JsonPath js= CommonFunctions.resptoJson(res);
		   String id=js.get("id");
		   System.out.println(id);
		
				
		
		
		
		}
	
	/*@BeforeTest
	public void LaunchJIRA() throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\Vishal\\eclipse-workspacevish\\APIPractice\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void JiraTest()
	{
		//get sessionid from commonfunctions
		Response res=given().header("Content-Type", "application/json").header("Cookie",CommonFunctions.getSessionKey()).
		body("{"+
    "\"fields\": {"+
       "\"project\":{"+
          "\"key\": \"TEST\""+
       "}"+
       "\"summary\": \"Issue 5 for adding comment\","+
       "\"description\": \"Creating my second bug\","+
       "\"issuetype\": {"+
          "\"name\": \"Bug\""+
       "}"+
   "}}").
		when().post("/rest/api/2/issue").then().statusCode(201).extract().response();
		
		JsonPath js = CommonFunctions.resptoJson(res);
		System.out.println(js);
		}
		
*/	

}
