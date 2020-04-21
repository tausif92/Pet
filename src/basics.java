import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class basics {

	@Test
	public void Test1() {
		
		//first thing is to get the base URL
		
		RestAssured.baseURI="https://maps.googleapis.com";
		//RestAssured.baseURI="http://216.10.245.166";
			//given block says what all you are doing to hit a request.
		
		given().
				//param("key","http://216.10.245.166").
				param("key","AIzaSyAq0ImpOj9J9H-TVwIjCQLcPwJ_6HxvR7Q").
				param("radius","500").
				param("location","-33.8670522,151.1957362").
				when().
				get("/maps/api/place/nearbysearch/json").
				//get("/maps/api/place/add/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("results[0].name",equalTo("Sydney")).and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"));
				//header(arg0, arg1)
				//body("results[0].geometry.location.lat",equalTo("-33.8688197"));
				//do the assertions i.e check whether status code is correct/output is correct etc

	}

}
