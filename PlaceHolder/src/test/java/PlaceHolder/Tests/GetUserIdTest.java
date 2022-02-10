package PlaceHolder.Tests;

import PlaceHolder.common.EndPoints;
import PlaceHolder.serelization.Comment;
import PlaceHolder.serelization.Post;
import PlaceHolder.serelization.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

/**
 * Unit test for simple App.
 */
public class GetUserIdTest 
{
	int userId;
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
			boolean validEmail;
			String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher;

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
				{
//					System.out.println("Comment Id "+ comment.getId());
					matcher = pattern.matcher(comment.getEmail());
					System.out.println(comment.getEmail() +" : "+ matcher.matches());				}
			}
			
		}
}