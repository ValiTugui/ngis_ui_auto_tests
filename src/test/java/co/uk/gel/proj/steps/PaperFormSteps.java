package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class PaperFormSteps extends Pages {

    public PaperFormSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Then("the user logs in to the Test Order system successfully")
    public void theUserLogsInToTheTestOrderSystemSuccessfully() {

        // NavigateTo(AppConfig.getPropertyValueFromPropertyFile("APP_URL"), "patient-search");

        if (driver.getCurrentUrl().contains("patient-search")) {
            Wait.forElementToBeDisplayed(driver, patientSearchPage.pageTitle);
            Assert.assertTrue(patientSearchPage.pageTitle.isDisplayed());
        } else {
            if (driver.getCurrentUrl().contains("login.microsoft")) {
                Wait.forElementToBeDisplayed(driver, patientSearchPage.emailAddressField);
                Assert.assertTrue(patientSearchPage.emailAddressField.isDisplayed());
                patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
            }
        }
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

    @And("the user clicks the Sign in hyperlink")
    public void theUserClicksTheSignInHyperlink(List<String> hyperLinks) {
        paperFormPage.clickSignInToTheOnlineServiceButton();

    }
}
