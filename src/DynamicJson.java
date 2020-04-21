	
	import static io.restassured.RestAssured.given;

	import static org.hamcrest.Matchers.equalTo;
	import files.Resources;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.Properties;
	import files.PayLoad;
	import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
	import files.CommonFunctions;
	import io.restassured.RestAssured;

	import io.restassured.http.ContentType;

	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;

	public class DynamicJson {
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
		
		@Test(dataProvider="BooksData")

		public void PostDataExcel(String isbn,String aisle) throws Exception {
			
		RestAssured.baseURI = prop.getProperty("HOST2");
		//you have to centralise your environment variables that are common to all like id password or base URL so put it into properties file.

		//Task1 - Grab The Response into a String
		Response res = given().header("Content-Type","application/json").
				//Key will also be common to all scripts so put that as well in properties file.
		
		body(PayLoad.AddBookPost(isbn,aisle)).
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
		
		
		
		@DataProvider(name="BooksData")
		public Object[][] getDataProvider()
		{
			//array - collection of similar type of elements
			//Multidimensional array - collection of arrays.
			
			//return new Object[][] {{"dataprovider1","1"},{"dataprovider2","2"},{"dataprovider3","3"}}; //creating an object of multiDime array
			//and initialising elements, all in one line.
			
			//you create a multidimensional array, first index i.e 3 represents the number of times the test case will run and second
			//index i.e 2 will tell that it has 2 parameters that will be used in every test run.
			      Object[][] data = new Object[3][2];
			      data[0][0] = "thyq";
			      data[0][1] = "78934";
			 
			      data[1][0] = "abcg";
			      data[1][1] = "78934";
			 
			      data[2][0] = "defh";
			      data[2][1] = "78934";
			 
			      return data;
	
		}

	}

