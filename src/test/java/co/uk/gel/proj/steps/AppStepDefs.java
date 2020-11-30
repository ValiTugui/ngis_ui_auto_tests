package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.models.ReferralFromJson;
import co.uk.gel.models.Referrals;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.gel.models.participant.avro.Referral;
import org.json.simple.JSONObject;
import org.junit.Assert;


public class AppStepDefs extends Pages {

    ReferralFromJson referralFromJson = new ReferralFromJson();

    public AppStepDefs(SeleniumDriver driver) {
        super(driver);
    }

    @Given("^the json file (.*) with referral information is available in the specified location$")
    public void applicationIsUpAndRunning(String fileName) throws Throwable {
        String stepResult = referralFromJson.verifyFilePresence(fileName);
        if(!stepResult.equalsIgnoreCase("Success")){
            Assert.fail(stepResult);
        }
    }

    @When("^the referral object is created successfully from the json file (.*)$")
    public void theDetailsProvidedInTheJsonFileIsCorrect(String jsonFile) {
        String stepResult = referralFromJson.readReferralDetailsFromJson(jsonFile);
        if(!stepResult.equalsIgnoreCase("Success")){
            Assert.fail(stepResult);
        }
    }

    @Then("the referral should be created via TOMS using json provided information and submitted successfully")
    public void theReferralsShouldBeCreatedAndSubmittedSuccessfullyViaTOMS() {
        Referral referralObject = referralFromJson.getReferralObject();
        if(referralObject == null){
            Assert.fail("Referral Object is not initialized.");
        }
        //Requesting Organisation
        fillStageRequestingOrganisation(referralObject);
        //Test Package
        fillStageTestPackage(referralObject);
        //Responsible Clinician
        //Tumours
        //Samples
        //Notes
        //Patient Choice
        //Print Forms
        //Submit Referral
    }
    private void fillStageTestPackage(Referral referralObject){
        String stageName = "Test package";
        boolean testResult = referralPage.navigateToStage(stageName);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord(stageName," ")+".jpg");
            Assert.fail("Could not navigate to stage:"+stageName);
        }
    }
    private void fillStageRequestingOrganisation(Referral referralObject){
        String stageName = "Requesting organisation";
        boolean testResult = referralPage.navigateToStage(stageName);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord(stageName," ")+".jpg");
            Assert.fail("Could not navigate to stage:"+stageName);
        }
        testResult = paperFormPage.fillInSpecificKeywordInSearchField(referralObject.getRequester().getOrganisationName());
        if(!testResult){
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("Could not search for Order entity.");
        }
        testResult = paperFormPage.checkThatEntitySuggestionsAreDisplayed();
        if(!testResult){
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("No suggestions listed for the order entity.");
        }
        testResult = paperFormPage.selectRandomEntityFromSuggestionsList();
        if(!testResult){
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("Could not select requesting organization.");
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("Could not save requesting organization.");
        }
    }
}//end
