package files;

import static io.restassured.RestAssured.given;

import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommonFunctions {
	
	
	public static JsonPath resptoJson(Response r)
	{
		String resString=r.asString();
		JsonPath j=new JsonPath(resString);
		return j;
	}
	
	//as the sessionID will be unique to entire session, so we can write that in commonfunctions and return the value of that sessionId to
	//our method and call that method in Test case.
	public static String getSessionKey()
	{
		RestAssured.baseURI="http://localhost:8008";
		Response resp=given().header("Content-Type","application/json").body("{ \"username\": \"kungwanivishal13\", \"password\": \"kunguKAPTAN@15\"}").
		when().post("/rest/auth/1/session").
		then().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		//Response will be in raw format so convert it into string and then to json and print
		JsonPath js = CommonFunctions.resptoJson(resp);
		String sessionid=js.get("session.value"); //get the cookie id as that will be used in other parts
		//System.out.println(sessionid);
		return sessionid;
	}
}