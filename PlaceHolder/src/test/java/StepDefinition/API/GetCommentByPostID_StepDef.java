package StepDefinition.API;

import PageObjectModel.API.GetPostComments_POM;
import PlaceHolder.common.SharedValues;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class GetCommentByPostID_StepDef {
    GetPostComments_POM getPostComments = new GetPostComments_POM();

    @And("Send request to get Comment that's mentioned into these posts")
    public void sendRequestToGetCommentThatSMentionedIntoThesePosts (){
       try {
           SharedValues.setCommentsList(getPostComments.getCommentsPerPost(SharedValues.getPostsList()));
            }
        catch (NullPointerException e)
        {
            System.out.println("This post doesn't have any comments in our database");
            System.exit(1);
        }
    }

    @Then("Validate the emails formats")
    public void validateTheEmailsFormats()
    {
        getPostComments.validateEmailsFormat(SharedValues.getCommentsList());
    }
}
