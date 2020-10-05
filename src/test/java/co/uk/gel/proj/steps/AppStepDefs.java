package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;


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

    @Given("a web browser is at the kibana app search page")
    public void aWebBrowserIsAtTheKibanaAppSearchPage(List<String> attributeOfUrl) {
        boolean testResult=false;
        String baseURLKibana = attributeOfUrl.get(0);
        String KibanaPage = AppConfig.getPropertyValueFromPropertyFile(baseURLKibana);
        Wait.forPageToBeLoaded(driver);
        driver.get(KibanaPage);
//        Wait.forPageToBeLoaded(driver);
        String navigatedURL = driver.getCurrentUrl();
        Debugger.println("Current URL before LOGIN is :" + navigatedURL);
        Wait.seconds(20);
//        testResult = ;
        Assert.assertTrue(testResult);
    }

    @Then("the user search for referral id in search box {string}")
    public void theUserSearchForReferralIdInSearchBox(String refID) {
        boolean testResult = false;
        testResult = kibanaPage.searchReferralId(refID);
        Assert.assertTrue(testResult);
    }

    @Then("the user clicks on {string} button")
    public void theUserClicksOnButton(String button) {
        boolean testResult = false;
        testResult = kibanaPage.clickOnButton(button);
        Assert.assertTrue(testResult);
    }

    @Then("the user clicks on previous time window and selects the date link")
    public void theUserClicksOnPreviousTimeWindowAndSelectsTheLink(List<String> link) {
        boolean testResult = false;
        testResult = kibanaPage.selectTheDateLink(link);
        Assert.assertTrue(testResult);
    }

    //appHome.testMethod step removed as it is not part of current applciation
}//end
