package StepDefinition.API;

import PageObjectModel.API.GetPostsByUserID_POM;
import PlaceHolder.common.SharedValues;
import io.cucumber.java.en.And;

public class GetPostsByUserID_StepDef extends GetPostsByUserID_POM {
    GetPostsByUserID_POM getPostsByUserID = new GetPostsByUserID_POM();
    @And("Send request to get Posts that's posted by this UserId")
    public void sendRequestToGetPostsThatSPostedByThisUserId()
    {
        try
        {
            SharedValues.setPostsList(getPostsByUserID.getPostsWithUserId(SharedValues.getUserId()));
        }
        catch (NullPointerException e)
        {
            System.out.println("This user doesn't have posts in our database");
            System.exit(1);
        }
    }
}
