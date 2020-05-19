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
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("Notes"," ")+"_Filled");
        }
        Assert.assertTrue(testResult);
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

}
