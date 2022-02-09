package PlaceHolder.Tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.*;
import PlaceHolder.common.EndPoints;
import PlaceHolder.serelization.Comment;
import PlaceHolder.serelization.Post;
import PlaceHolder.serelization.User;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.TestCase;
import io.restassured.mapper.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.*;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.*;

/**
 * Unit test for simple App.
 */
public class GetUserIdTest 
{
	int userId;
	//    /**
	//     * Create the test case
	//     *
	//     * @param testName name of the test case
	//     */
	//    public GetUserIdTest( String testName )
	//    {
	//        super( testName );
	//    }
	//
	//    /**
	//     * @return the suite of tests being tested
	//     */
	////    public static Test suite()
	////    {
	////        return new TestSuite( AppTest.class );
	////    }
	//
	//    /**
	//     * Rigourous Test :-)
	//     */
	@Test (enabled = false)
	public void getUserID()
	{
		Response res = given().get(("https://jsonplaceholder.typicode.com".concat(EndPoints.Get_Users.toString())));
		
		// Way number one 
		JsonPath JPath = res.jsonPath();
		List<User> users = JPath.getList("", User.class);
		String userName; 
		for(User user : users)
		{
			userName = user.getUsername();
			if(userName.equals("Delphine"))
			{
				System.out.println("here");
				System.out.println(user.getId().toString());
			}
		}
		
		// Way number two 

		//		JsonPath JPath = res.jsonPath();
		//		int RespLengh = JPath.getInt("res.size()");
		//		System.out.println("Length "+ RespLengh);
		//		for (int i =0; i < RespLengh ;  i++) {
		//			System.out.println("i value"+ i);
		//			if(JPath.get("username["+i+"]").equals("Delphine")) {
		//				System.out.println(res.jsonPath().get("["+i+"].id".toString()));
		//			}
		//		}

		//		System.out.println(res.asString());
	}

	
		@Test 
		public void getPostsWithUserId() // pass user ID 
		{
			//Posts by user id 
			Response Posts_resp = given().queryParam("userId", "9").when().get(("https://jsonplaceholder.typicode.com".concat(EndPoints.Get_Posts.toString())));
			//System.out.println(Posts_resp.asString());
			
			JsonPath JPath = Posts_resp.jsonPath();
			List<Post> posts = JPath.getList("", Post.class);
			ArrayList<Integer> posts_Ids = new ArrayList<Integer>();
			Response CommentsPerPost_Resp ;
			JsonPath CommentsPerPost_Path;
			for(Post post : posts)
			{   System.out.println("Posts Id "+ post.getId());
				CommentsPerPost_Resp = given().when().get(("https://jsonplaceholder.typicode.com".concat(EndPoints.Get_Posts.toString()).concat("/").concat(post.getId().toString()).concat(EndPoints.Get_Comments)));
				 CommentsPerPost_Path = CommentsPerPost_Resp.jsonPath();
				List<Comment> comments_Per_Post = CommentsPerPost_Path.getList("", Comment.class);
				for (Comment comment : comments_Per_Post) 
				{	System.out.println("Comment Ids"+ comment.getId());
					System.out.println(comment.getEmail());
				}
			}
			
		}
}
