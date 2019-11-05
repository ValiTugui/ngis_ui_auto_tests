package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;

public class PaperFormSteps extends Pages {

    public PaperFormSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Then("the user logs in to the Test Order system successfully")
    public void theUserLogsInToTheTestOrderSystemSuccessfully() {

        boolean eachElementIsLoaded;
        switchToURL(driver.getCurrentUrl());
        eachElementIsLoaded = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected();
        Assert.assertTrue(eachElementIsLoaded);
    }

    @When("the user clicks the PDF order form button")
    public void clickPDFOrderFormButton() {
        Actions.clickElement(driver, paperFormPage.usePDFOrderFormButton);
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

    @When("the user clicks the Continue button")
    public void clickContinueButton() {
        paperFormPage.clickContinueButton();
    }

    @Then("the Review test selection page is properly opened and by default a test is selected")
    public void checkThatReviewTestSelectionPageIsProperlyOpened() {
        paperFormPage.checkThatReviewTestSelectionIsOpened();
        Assert.assertTrue((paperFormPage.paperFormHeader.getText()).toLowerCase().contains("review test selection"));
        Assert.assertTrue("First Test is selected by Default", paperFormPage.checkThatTestIsSelected());
        Assert.assertTrue((paperFormPage.confirmTestsSubCaption.getText()).contains("1 of " + Integer.toString(paperFormPage.testsPackage.size())));
    }

    @When("the user clicks the Continue button again")
    public void clickContinueButtonAgain() {
        paperFormPage.clickContinueButton();
    }

    @Then("the Offline order page is properly displayed for chosen clinical indication")
    public void offlineOrderPageIsDisplayedForRareDiseaseClinicalIndication() {
        Wait.forElementToBeDisplayed(driver, paperFormPage.offlineOrderContainer);
        Wait.forElementToBeDisplayed(driver, paperFormPage.paperFormHeader);
        Assert.assertTrue((paperFormPage.paperFormHeader.getText()).contains("Offline order"));
    }

    @And("The user should be able to see text {string} replaced with {string} Form")
    public void theUserShouldBeAbleToSeeTextReplacedWithForm(String wrongText, String correctText) {
        Assert.assertTrue(paperFormPage.downloadSections.get(1).findElement(By.tagName("h3")).getText().contains(correctText));
        Assert.assertFalse(paperFormPage.downloadSections.get(1).findElement(By.tagName("h3")).getText().contains(wrongText));
    }
}
