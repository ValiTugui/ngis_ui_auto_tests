package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class NotesSteps extends Pages {
    public NotesSteps(SeleniumDriver driver) {
        super(driver);
    }

    @When("the user fills in the Add Notes field")
    public void theUserFillsInTheAddNotesField() {
        notesPage.fillInAddNotesField();
    }

    @When("the user attempts to fill in Referral Notes field with data that exceed the maximum data allowed {int}")
    public void theUserAttemptsToFillInReferralNotesFieldWithDataThatExceedTheMaximumDataAllowed(int maximumCharactersAllowed) {
        notesPage.fillInAddNotesFieldWithOverThreeThousandCharacters();
    }
}
