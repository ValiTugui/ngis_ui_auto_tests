package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class PaperFormSteps extends Pages {

    public PaperFormSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user clicks the {string} hyperlink")
    public void theUserClicksTheHyperlink(String arg0) {
        paperFormPage.clickSignInToTheOnlineServiceButton();
    }

    @Then("the user logs in to the Test Order system successfully")
    public void theUserLogsInToTheTestOrderSystemSuccessfully() {
        patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
    }


    @And("the user enters the keyword {string} in the search field")
    public void theUserEntersTheKeywordInTheSearchField(String ordering_entity) {
        paperFormPage.fillInSpecificKeywordInSearchField(ordering_entity);
        paperFormPage.checkThatEntitySuggestionsAreDisplayed();
    }

    @And("the user selects a random entity from the suggestions list")
    public void theUserSelectsARandomEntityFromTheSuggestionsList() {
        paperFormPage.selectRandomEntityFromSuggestionsList();
    }

    @And("the user enters the invalid keyword {string} in the search field")
    public void theUserEntersTheInvalidKeywordInTheSearchField(String ordering_entity) {
        paperFormPage.fillInSpecificKeywordInSearchField(ordering_entity);
    }
}
