package PageObjectModel.API;

import PlaceHolder.common.EndPoints;
import PlaceHolder.serelization.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetUserId_POM
{
	boolean idFound;
	int retrievedUserId;

    public String getUserID(String userName)
	{
        String baseURI = "https://jsonplaceholder.typicode.com";
		Response res = given().get(baseURI.concat(EndPoints.Get_Users));
		Assert.assertEquals(res.statusCode(),200);
		JsonPath JPath = res.jsonPath();
		List<User> users = JPath.getList("", User.class);
		String UserInListUserName;
		for(User user : users)
		{
			UserInListUserName = user.getUsername();
			if(UserInListUserName.equals(userName))
			{
				user.setId(user.getId());
				retrievedUserId=user.getId();
				idFound = true;
				break;
			}
		}
		if(idFound)
			return String.valueOf((retrievedUserId));
		else
			return ("Can't find this user name in the users list");
	}
}
