package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;

public class ClinicalIndicationsTestSelectSteps extends Pages {


    public ClinicalIndicationsTestSelectSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user clicks the Start Test Order Referral button")
    public void theUserClicksTheStartTestOrderReferralButton() {
        homePage.closeCookiesBannerFromFooter();
        if(!clinicalIndicationsTestSelect.clickStartTestOrderReferralButton()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_StartReferral.jpg");
            Assert.fail("Could not click on Start Referral.");
        }
    }

    @And("the loading wheel is displayed")
    public void theLoadingWheelIsDisplayed() {
        if(!clinicalIndicationsTestSelect.validateIfLoadingWheelIsPresent()) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_LoadingWheel.jpg");
            Assert.fail("Loading wheel not displayed.");
        }
    }

    @And("the text {string} is not displayed")
    public void theTextIsNotDisplayed(String wrongText) {
        if(!clinicalIndicationsTestSelect.validateIfWrongTextIsNotDisplayed(wrongText)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoText.jpg");
            Assert.fail(wrongText+ " displayed.");
        }
    }

    @Then("the text {string} is displayed")
    public void theTextIsDisplayed(String correctText) {
        boolean testResult = false;
        testResult = clinicalIndicationsTestSelect.validateIfCorrectTextIsDisplayed(correctText);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoText.jpg");
            Assert.fail(correctText+ " not displayed.");
        }

    }

    @And("the list of clinical indications are loaded")
    public void theListOfClinicalIndicationsIsLoading() {
        Wait.seconds(3);
        if(!clinicalIndicationsTestSelect.checkIfClinicalIndicationsAreLoaded()) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClinicalIndication.jpg");
            Assert.fail("The List of Clinical Indication are NOT Loaded");
        }
    }

    @And("the user sees the {string} tab is selected by default")
    public void theUserSeesTheTabIsSelectedByDefault(String tabName) {
        if(!clinicalIndicationsTestSelect.isTabSelected(tabName)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TabNotSelected.jpg");
            Assert.fail("Tab "+tabName+" not selected by default.");
        }
    }

    @And("the user sees the button {string} on Bottom right")
    public void theUserSeesTheButtonOnBottomRight(String buttonName) {
        boolean testResult = false;
        testResult = clinicalIndicationsTestSelect.validateIfCorrectButtonDisplayed(buttonName);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoButton.jpg");
            Assert.fail("Button "+buttonName+" not present.");
        }
    }

    @And("the user selects the {string} tab")
    public void theUserClicksOnTab(String tabName) {
        boolean testResult = false;
        testResult = clinicalIndicationsTestSelect.selectTab(tabName);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TabNotSelected.jpg");
            Assert.fail("Tab "+tabName+" not present.");
        }
    }

    @And("the user clicks on view more icon")
    public void theUserClicksOnViewMoreIcon() {
        boolean testResult = false;
        testResult = clinicalIndicationsTestSelect.clickOnViewMoreIcon();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ViewMore.jpg");
            Assert.fail("Could not click on view more icon.");
        }
    }

    @And("the user click on Go to test page button")
    public void theUserClickOnGoToTestPageButtom() {
        boolean testResult = false;
        testResult = clinicalIndicationsTestSelect.clickGoToTestPageButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_GoToTestPage.jpg");
            Assert.fail("Could not click on  Go To Test Page button.");
        }
    }

    @And("the user should be able to see all {string} tabs and are clickable")
    public void theUserShouldBeAbleToSeeAllTabsAndAreClickable(String tabCount, List<String> tabName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.isTabPresent(Integer.parseInt(tabCount), tabName.get(0), tabName.get(1), tabName.get(2), tabName.get(3)));
        Assert.assertTrue(Actions.isTabClickable(driver, Integer.parseInt(tabCount), clinicalIndicationsTestSelect.clinicalIndicationTabs));
    }

    @Then("the user should be able to see a new modal window")
    public void theUserShouldBeAbleToSeeANewModalWindow() {
        clinicalIndicationsTestSelect.testPackagePopUpValidations();
        Assert.assertTrue(clinicalIndicationsTestSelect.checkTestPagePopUpContents());
        for (int i = 0; i < clinicalIndicationsTestSelect.testPackagePopupProps.findElements(By.tagName("p")).size(); i++) {
            Assert.assertFalse(clinicalIndicationsTestSelect.testPackagePopupProps.findElements(By.tagName("p")).get(i).getText().isEmpty());
        }
        Assert.assertTrue(clinicalIndicationsTestSelect.checkTestPagePopUpTitleMatchesSearchedText());
        Assert.assertTrue(clinicalIndicationsTestSelect.goToTestPageButtonFromPopup.isDisplayed());
        Assert.assertTrue(clinicalIndicationsTestSelect.closePopupButton.isDisplayed());
    }

    @And("the user should be able to see sections are displayed based on Clinical Indications type")
    public void theUserShouldBeAbleToSeeSectionsAreDisplayedBasedOnClinicalIndicationsType(List<String> sectionName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.testDetailsTabValidation(sectionName.get(0), sectionName.get(1), sectionName.get(2)));
    }

    @And("the user should be able to see {string} section is displayed")
    public void theUserShouldBeAbleToSeeSectionsAreDisplayed(String sectionName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.labsTabValidation(sectionName));
    }

    @And("the user should be able to see {string} sections of Order process are displayed")
    public void theUserShouldBeAbleToSeeSectionsOfOrderProcessAreDisplayed(String numOfSection, List<String> sectionName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.orderProcessTabValidation(Integer.parseInt(numOfSection)));
        for (int i = 0; i < clinicalIndicationsTestSelect.orderProcesssTitles.size(); i++) {
            Assert.assertTrue(clinicalIndicationsTestSelect.orderProcesssTitles.get(i).getText().matches(sectionName.get(i)));
        }
    }

    @Then("the user should be able to see a link {string} at left side top of the page")
    public void theUserShouldBeAbleToSeeALinkAtLeftSideTopOfThePage(String buttonName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.checkIfBackToSearchButtonPresent(buttonName));
    }

    @And("the user clicks on Back to Search button")
    public void theUserClicksOnBackToSearchButton() {
        if(!clinicalIndicationsTestSelect.clickBackToSearchButton()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_BackButton.jpg");
            Assert.fail("Could not click on  Back to Search Button.");
        }
    }

    @And("the user should be able to see the following under Eligibility Criteria tab")
    public void theUserShouldBeAbleToSeeTheFollowingUnderEligibilityCriteriaTab(List<String> sectionName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.eligibilityCriteriaTabValidation(sectionName.get(0), sectionName.get(1), sectionName.get(2), sectionName.get(3)));
    }

    @And("the user should be able to see {string} according to the CI selected")
    public void theUserShouldBeAbleToSeeAccordingToTheCISelected(String whoCanOrderContent) {
        Assert.assertTrue(clinicalIndicationsTestSelect.whoCanOrderContentValidation(whoCanOrderContent));
    }

    @Then("the user clicks on first Clinical indications results displayed")
    public void theUserClicksOnClinicalIndicationsResultsDisplayed() {
        clinicalIndicationsTestSelect.clickFirstResultInClinicalIndications();
    }

    @And("the user sees Clinical indications modal with two sections and {string} is present")
    public void theUserSeesClinicalIndicationsModalWithTwoSections(String buttonName, List<String> sectionName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.clinicalIndicationsTabValidation(buttonName, sectionName.get(0), sectionName.get(1)));
    }

    @And("the user should be able to see Clinical indications list is displayed containing clickable cards for each clinical indication")
    public void theUserShouldBeAbleToSeeClinicalIndicationsListIsDisplayedContainingClickableCardsForEachClinicalIndication() {
        for (int i = 0; i < clinicalIndicationsTestSelect.clinicalIndicationsResults.size(); i++) {
            Assert.assertTrue(clinicalIndicationsTestSelect.clinicalIndicationsResults.get(i).isEnabled());
        }
    }

    @And("the user should be able to see the following under Further Info tab")
    public void theUserShouldBeAbleToSeeTheFollowingUnderFurtherInfoTab(List<String> sectionName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.furtherInfoTabValidation(sectionName.get(0), sectionName.get(1), sectionName.get(2), sectionName.get(3)));
    }

    @And("the user click on Go to Clinical Indication button")
    public void theUserClickOnGoToClinicalIndicationButton() {
        boolean testResult = false;
        testResult = clinicalIndicationsTestSelect.clickGoToClinicalIndicationButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClinicalIndicate.jpg");
            Assert.fail("Could not click on Clinical Indicate Button.");
        }
    }

    @Then("the browser navigates to the previously selected Clinical Indication Details page while still saving the user's most recent search for further page navigation")
    public void theBrowserNavigatesToThePreviouslySelectedClinicalIndicationDetailsPageWhileStillSavingTheUserSMostRecentSearchForFurtherPageNavigation() {
        Assert.assertTrue(clinicalIndicationsTestSelect.checkIfClinicalIndicationsSearchValueMatchesTheSearchTermGiven());
    }

    @And("the user verifies the page will be covered by an overlay")
    public void theUserVerifiesThePageWillBeCoveredByAnOverlay() {
        boolean testResult = false;
        testResult = clinicalIndicationsTestSelect.verifyTheOverlayIsDisplayed();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Overlay.jpg");
            Assert.fail("Page not covered by overlay.");
        }
    }

    @When("the user clicks the close icon of clinical indication pop up")
    public void theUserClicksTheCloseIconOfClinicalIndicationPopUp() {
        boolean testResult = false;
        testResult = clinicalIndicationsTestSelect.closeClinicalIndicationPopUp();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClinicalIndication.jpg");
            Assert.fail("Could not click on Close icon of Clinical Indication Pop up");
        }
    }//end
}
