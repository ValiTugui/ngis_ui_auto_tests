package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.TestUtils;
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
    public void theUserLogsInToTheTestOrderSystemSuccessfully(List<String> pageTitleText) {
        boolean eachElementIsLoaded;
        switchToURL(driver.getCurrentUrl());
        eachElementIsLoaded = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected();
        if(!eachElementIsLoaded){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientSearchYes.jpg");
            Assert.fail("Patient search with Yes.");
        }
        Assert.assertTrue(referralPage.getTheCurrentPageTitle().matches(pageTitleText.get(0)));
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FindYourPatient.jpg");
        }
    }

    @When("the user clicks the PDF order form button")
    public void clickPDFOrderFormButton() {
        paperFormPage.clickOnUsePDFOrderFormButton();
    }

    @And("the user enters the keyword {string} in the search field")
    public void theUserEntersTheKeywordInTheSearchField(String ordering_entity) {
        boolean testResult = false;
        testResult = paperFormPage.fillInSpecificKeywordInSearchField(ordering_entity);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_OrderEntity.jpg");
            Assert.fail("Could not search for Order entity.");
        }
        testResult = paperFormPage.checkThatEntitySuggestionsAreDisplayed();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_EntitySuggestion.jpg");
            Assert.fail("No suggestions listed for the order entity:"+ordering_entity);
        }
    }

    @And("the user selects a random entity from the suggestions list")
    public void theUserSelectsARandomEntityFromTheSuggestionsList() {
        boolean testResult = false;
        testResult = paperFormPage.selectRandomEntityFromSuggestionsList();
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_RequestingOrganisation.jpg");
        }
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_RequestingOrganisation.jpg");
            Assert.fail("Could not select requesting organization.");
        }

    }

    @And("the user selects the first entity from the suggestions list")
    public void theUserSelectsTheFirstEntityFromTheSuggestionsList() {
        paperFormPage.selectFirstEntityFromSuggestionsList();
    }

    @And("the user enters the invalid keyword {string} in the search field")
    public void theUserEntersTheInvalidKeywordInTheSearchField(String ordering_entity) {
        paperFormPage.fillInSpecificKeywordInSearchField(ordering_entity);
    }

    @And("the user clicks the Sign in hyperlink")
    public void theUserClicksTheSignInHyperlink(List<String> hyperLinks) {
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_OnlineService.jpg");
        }
        paperFormPage.clickSignInToTheOnlineServiceButton();
    }

    @When("the user clicks the Continue button")
    public void clickContinueButton() {
        paperFormPage.clickContinueButton();
    }

    @Then("the {string} page is properly opened and by default a test is selected")
    public void checkThatReviewTestSelectionPageIsProperlyOpened(String pageTitle) {
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_"+TestUtils.removeAWord(pageTitle," ")+".jpg");
        }
        Assert.assertTrue(paperFormPage.checkThatReviewTestSelectionIsOpened());
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
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_"+TestUtils.removeAWord(pageTitle," ")+".jpg");
        }
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

    @And("the user sees the search label with {string}")
    public void theUserSeesTheSearchLabelWith(String expectedSubHeaderText) {
        Assert.assertTrue(paperFormPage.confirmOrderingEntityLabelText(expectedSubHeaderText));
    }

    @And("the user see the search field has placeholder text as {string}")
    public void theUserSeeTheSearchFieldHasPlaceholderTextAs(String expectedPlaceholderText) {
        Assert.assertTrue(paperFormPage.confirmOrderingEntitySearchFieldPlaceholderText(expectedPlaceholderText));
    }

    @And("the user should be able to see two sections as follows and a {string} button")
    public void theUserShouldBeAbleToSeeTwoSectionsAsFollowsAndAButton(String buttonName, List<String> sectionName) {
        Assert.assertTrue(paperFormPage.checkThatStepsTitlesForRoutedClinicalIndicationAreCorrect(buttonName, sectionName.get(0), sectionName.get(1)));
    }

    @And("the Complete the forms with patient information section the following should be displayed")
    public void theCompleteTheFormsWithPatientInformationSectionTheFollowingShouldBeDisplayed(List<String> sectionName) {
        Assert.assertTrue(paperFormPage.checkThatNameOfDowmloadSectionIsDisplayed(sectionName.get(0), sectionName.get(1), sectionName.get(2)));
    }

    @And("the user should be see lab details for {string} under the heading Send samples and completed forms without any warning message")
    public void theUserShouldBeSeeLabDetailsForUnderTheHeadingSendSamplesAndCompletedFormsWithoutAnyWarningMessage(String placeSearchTerm) {
        Assert.assertTrue(paperFormPage.checkThataddressOfLabIsDisplayed(placeSearchTerm));
    }

    @And("the user should see the {string} button next to each of the forms")
    public void theUserShouldSeeTheButtonNextToEachOfTheForms(String buttonName) {
        boolean testResult  = false;
        testResult = paperFormPage.checkThatDownloadButtonsAreDisplayed();
        Assert.assertTrue(testResult);
        testResult = paperFormPage.verifyTheDownloadButtonLabel(buttonName);
        Assert.assertTrue(testResult);
    }

    @Then("the details of the search results are displayed")
    public void theDetailsOfTheSearchResultsAreDisplayed() {
        Assert.assertTrue(paperFormPage.checkThatInformationEntityIsDisplayed());
        Assert.assertTrue(paperFormPage.checkThatSelectedEntityNameIsTheSameAsTheSearchValue());
    }

    @And("the Continue button should be clickable")
    public void theContinueButtonShouldBeClickable() {
        paperFormPage.checkContinueIsClickable();
    }

    @And("the user should be able to see a click able link {string} at top right side of the page")
    public void theUserShouldBeAbleToSeeAClickAbleLinkAtTopRightSideOfThePage(String linkName) {
        Assert.assertTrue(paperFormPage.checkCancelOrderLinkIdDisplayed(linkName));
    }

    @When("the user clicks the link Cancel Order")
    public void theUseClicksTheLinkCancelOrder() {
        paperFormPage.clickCancelOrderLink();
        Actions.acceptAlert(driver);
    }
 }
