package placeHolder.framework;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class PlaceHolderConfiguration{
	@BeforeMethod(alwaysRun = true)
	public void configure() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}
}
