package placeHolder.framework;

//import io.restassured.RestAssured;
import org.testng.annotations.*;
import io.restassured.RestAssured;

public class PlaceHolderConfiguration {
	
	@BeforeSuite (alwaysRun = true)
	public void configure() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

	}
	

}
