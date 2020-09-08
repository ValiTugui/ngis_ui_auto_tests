package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class NotesSteps extends Pages {
    public NotesSteps(SeleniumDriver driver) {
        super(driver);
    }

    @When("the user fills in the Add Notes field")
    public void theUserFillsInTheAddNotesField() {
        boolean testResult;
        testResult = notesPage.fillInAddNotesField();
        if(!testResult){
            Assert.fail("Could not fill Notes Details.");
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Notes");
        }
    }

    @When("the user attempts to fill in Referral Notes field with {int} data that exceed the maximum data allowed 3000")
    public void theUserAttemptsToFillInReferralNotesFieldWithDataThatExceedTheMaximumDataAllowed(int dataSize) {
        notesPage.fillInAddNotesFieldWithOverThreeThousandCharacters(dataSize);
    }

    @Then("the user should see the message displayed (.*) in the notes page")
    public void theUserIsPreventedFromEnteringDataThatExceedThatAllowableMaximumDataInTheField(String errorMessage) {
        boolean testResult  = false;
        testResult = notesPage.verifyNotesPageWarningMessage(errorMessage);
        Assert.assertTrue(testResult);

    }

    @Then("the user should be able to see an info message (.*) on the Notes page")
    public void theUserShouldBeAbleToSeeAOnTheNotesPage(String infoMessage) {
        boolean testResult = false;
        testResult = notesPage.verifyInfoMessageOnNotesPage(infoMessage);
        Assert.assertTrue(testResult);
    }

    @Then("the user reads & validate the notes details data {string}")
    public void theUserReadsandValidateTheNotesDetails(String Message )
    {
        boolean testResult = false;
        testResult = notesPage.verifyNotesmessage(Message);
        Assert.assertTrue(testResult);
    }

}
