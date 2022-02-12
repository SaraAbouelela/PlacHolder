package StepDefinition.API;

import PageObjectModel.API.GetUserId_POM;
import PlaceHolder.common.SharedValues;
import io.cucumber.java.en.When;

public class GetUsers_StepDef extends GetUserId_POM {

    GetUserId_POM getUserId = new GetUserId_POM();
    @When("User send request to get userId with specific {string}")
    public void user_send_request_to_get_user_id_with_specific(String userName)
   {
       try{
           SharedValues.setUserId(Integer.parseInt(getUserId.getUserID(userName)));
           System.out.println("The requested username has User ID = "+ SharedValues.getUserId());
       }
       catch (NumberFormatException e)
       {
           System.out.println("This username doesn't exist in our database");
           System.exit(1);
       }
   }

}
