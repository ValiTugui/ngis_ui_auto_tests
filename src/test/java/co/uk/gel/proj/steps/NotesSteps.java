package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
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
        Assert.assertTrue(testResult);
    }

    @When("the user attempts to fill in Referral Notes field with data that exceed the maximum data allowed {int}")
    public void theUserAttemptsToFillInReferralNotesFieldWithDataThatExceedTheMaximumDataAllowed(int maximumCharactersAllowed) {
        notesPage.fillInAddNotesFieldWithOverThreeThousandCharacters();
    }

    @Then("the user should be able to see a {string} on the Notes page")
    public void theUserShouldBeAbleToSeeAOnTheNotesPage(String infoMessage) {
        boolean testResult = false;
        testResult = notesPage.verifyInfoMessageOnNotesPage(infoMessage);
        Assert.assertTrue(testResult);
    }

    @Then("the user should see an error message {string} in {string} for maximum chracters")
    public void theUserShouldSeeAnErrorMessageInForMaximumChracters(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = notesPage.checkTheErrorMessageForMaxCharacters(errorMessage, messageColor);
        Assert.assertTrue(testResult);
    }
}
