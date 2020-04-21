package files;

public class PayLoad {
	
	public static String PostPayload()
	{
		String b="{\n" + "    \"location\": {\n" + "        \"lat\": -38.383494,\n" + "        \"lng\": 33.427362\n"

	+ "    },\n" + "    \"accuracy\": 50,\n" + "    \"name\": \"Frontline house\",\n"

	+ "    \"phone_number\": \"(+91) 983 893 3937\",\n"

	+ "    \"address\": \"29, side layout, cohen 09\",\n" + "    \"types\": [\n"

	+ "        \"shoe park\",\n" + "        \"shop\"\n" + "    ],\n"

	+ "    \"website\": \"http://google.com\",\n" + "    \"language\": \"French-IN\"\n" + "}";
		
		return b;

	}
	
	public static String AddBookPost(String isbn, String aisle)
	{
		String payload="{\r\n\"name\":\"Learn for Appium Automation with Java\",\r\n\"isbn\":\""+isbn+"\",\r\n\"aisle\":\""+aisle+"\",\r\n\"author\":\"Johny foe\"\r\n}\r\n";
		return payload;
		
	}

}
