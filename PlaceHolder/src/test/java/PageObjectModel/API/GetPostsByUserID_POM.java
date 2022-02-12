package PageObjectModel.API;

import PlaceHolder.common.EndPoints;
import PlaceHolder.serelization.Post;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;

public class GetPostsByUserID_POM {
    int postIndex;
    ArrayList<Integer> postsList = new ArrayList<>();
    public ArrayList<Integer> getPostsWithUserId(int userID) // pass user ID
    {
        //Posts by user id
        Response Posts_resp = given().queryParam("userId", userID).when().get(("https://jsonplaceholder.typicode.com".concat(EndPoints.Get_Posts)));
        JsonPath JPath = Posts_resp.jsonPath();
        List<Post> postsPerUserId = JPath.getList("", Post.class);
        for ( postIndex = 0; postIndex < postsPerUserId.size(); postIndex++)
        {
            postsList.add(postIndex , postsPerUserId.get(postIndex).getId());
        }
        System.out.println("This user has the following posts ids "+ postsList);
        return postsList;
    }
}
