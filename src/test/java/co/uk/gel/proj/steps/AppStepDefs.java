package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AppStepDefs extends Pages {

    public AppStepDefs(SeleniumDriver driver) {
        super(driver);
    }

    @Given("^application is up and running$")
    public void applicationIsUpAndRunning() throws Throwable {
        Debugger.println("Application is Up and Running.");
    }

    @When("^the user login to the application with valid credentials$")
    public void theUserLoginToTheApplicationWithValidCredentials() throws Throwable {
        Debugger.println("User login to application with valid credentials.");
    }
    //appHome.testMethod step removed as it is not part of current applciation
}//end
