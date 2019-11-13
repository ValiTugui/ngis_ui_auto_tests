package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
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

    @Then("the {string} page is properly opened and by default a test is selected")
    public void checkThatReviewTestSelectionPageIsProperlyOpened(String pageTitle) {
        paperFormPage.checkThatReviewTestSelectionIsOpened();
        Assert.assertTrue((paperFormPage.paperFormHeader.getText()).matches(pageTitle));
        Assert.assertTrue("First Test is NOT selected by Default", paperFormPage.checkThatTestIsSelected());
        Assert.assertTrue((paperFormPage.confirmTestsSubCaption.getText()).contains("1 of " + Integer.toString(paperFormPage.testsPackage.size())));
    }

    @When("the user clicks the Continue button again")
    public void clickContinueButtonAgain() {
        paperFormPage.clickContinueButton();
    }

    @Then("the {string} page is properly displayed for chosen clinical indication")
    public void offlineOrderPageIsDisplayedForRareDiseaseClinicalIndication(String pageTitle) {
        Wait.forElementToBeDisplayed(driver, paperFormPage.offlineOrderContainer);
        Wait.forElementToBeDisplayed(driver, paperFormPage.paperFormHeader);
        Assert.assertTrue((paperFormPage.paperFormHeader.getText()).matches(pageTitle));
    }

    @And("The user should be able to see text {string} replaced with {string} Form")
    public void theUserShouldBeAbleToSeeTextReplacedWithForm(String wrongText, String correctText) {
        Assert.assertTrue(paperFormPage.downloadSections.get(1).findElement(By.tagName("h3")).getText().contains(correctText));
        Assert.assertFalse(paperFormPage.downloadSections.get(1).findElement(By.tagName("h3")).getText().contains(wrongText));
    }

    @And("the warning message is present on the Offline order page")
    public void theWarningMessageIsPresentOnTheOfflineOrderPage() {
        Assert.assertTrue("Warning Message is NOT Present", paperFormPage.warningBanner.isDisplayed());
    }

    @And("the warning message contains the text {string} in the Referral section")
    public void theWarningMessageContainsTheTextInTheReferralSection(String warningText) {
        Assert.assertTrue("Warning Message DO NOT contain the correct text", paperFormPage.checkTheWarningText(warningText));
    }

    @And("the user should able to select online or offline order")
    public void theUserShouldAbleToSelectOnlineOrOfflineOrder() {
        Assert.assertTrue(paperFormPage.usePDFOrderFormButton.isEnabled());
        Assert.assertTrue(paperFormPage.signInToOnlineServiceButton.isEnabled());
    }

    @And("the user selects the test in the test page and clicks on Continue button")
    public void theUserSelectsTheTestInTheTestPageAndClicksOnContinueButton() {
        Click.element(driver, paperFormPage.clinicalCardCheckbox);
        Click.element(driver, paperFormPage.continueButton.get(0));
    }

    @And("the user should be able to see text {string} under Review Test Selection heading")
    public void theUserShouldBeAbleToSeeTextUnderReviewTestSelectionHeading(String headerText) {
        Assert.assertTrue("Header Text DO NOT contain the correct text", paperFormPage.checkTheReviewSelectionPageHeaderText(headerText));
    }
}
