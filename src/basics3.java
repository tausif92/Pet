import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;



import io.restassured.RestAssured;

import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class basics3 {

@org.testng.annotations.Test

public void MapAPIAddAndDeletePlaceTest() throws Exception {
	String b="{\n" + "    \"location\": {\n" + "        \"lat\": -38.383494,\n" + "        \"lng\": 33.427362\n"

+ "    },\n" + "    \"accuracy\": 50,\n" + "    \"name\": \"Frontline house\",\n"

+ "    \"phone_number\": \"(+91) 983 893 3937\",\n"

+ "    \"address\": \"29, side layout, cohen 09\",\n" + "    \"types\": [\n"

+ "        \"shoe park\",\n" + "        \"shop\"\n" + "    ],\n"

+ "    \"website\": \"http://google.com\",\n" + "    \"language\": \"French-IN\"\n" + "}";

RestAssured.baseURI = "http://216.10.245.166";

//Task1 - Grab The Response into a String
Response res = given().
queryParam("key", "qaclick123").
body(b).
when().
post("/maps/api/place/add/json").
then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
body("status", equalTo("OK")).extract().response();

//Task2 - Convert response into JSON

String resString=res.asString(); //convert to string first
//System.out.println(resString);
JsonPath js=new JsonPath(resString);
//Now take the key that you want to Delete.
String placeID=js.getString("place_id");
System.out.println(placeID);

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