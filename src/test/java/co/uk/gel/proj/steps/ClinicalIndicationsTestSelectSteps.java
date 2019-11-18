package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
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

//    @When("^the user clicks on Start test order button$")
//    public void clickStartTestOrderButton() {
//        homePage.closeCookiesBannerFromFooter();
//        clinicalIndicationsTestSelect.clickStartTestOrderButton();
//    }

    @When("the user clicks the Start referral button")
    public void theUserClicksTheStartReferralButton() {
        homePage.closeCookiesBannerFromFooter();
        clinicalIndicationsTestSelect.clickStartReferralButton();
    }


    @And("the loading wheel is displayed")
    public void theLoadingWheelIsDisplayed() {
        Assert.assertTrue("The Loading Wheel is NOT Displayed", clinicalIndicationsTestSelect.validateIfLoadingWheelIsPresent());
    }

    @And("the text {string} is not displayed")
    public void theTextIsNotDisplayed(String wrongText) {
        Assert.assertTrue(wrongText + "  is Displayed", clinicalIndicationsTestSelect.validateIfWrongTextIsNotDisplayed(clinicalIndicationsTestSelect.loadingText, wrongText));
    }

    @Then("the text {string} is displayed")
    public void theTextIsDisplayed(String correctText) {
        Assert.assertTrue(correctText + "  is NOT Displayed", clinicalIndicationsTestSelect.validateIfCorrectTextIsDisplayed(clinicalIndicationsTestSelect.loadingText, correctText));
    }

    @And("the list of clinical indications are loaded")
    public void theListOfClinicalIndicationsIsLoading() {
        Assert.assertTrue("The List of Clinical Indication are NOT Loaded", clinicalIndicationsTestSelect.checkIfClinicalIndicationsAreLoaded());
    }

    @And("the user sees the {string} tab is selected by default")
    public void theUserSeesTheTabIsSelectedByDefault(String tabName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.isTabSelected(tabName));
    }

    @And("the user sees the button {string} on Bottom right")
    public void theUserSeesTheButtonOnBottomRight(String buttonName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.validateIfCorrectTextIsDisplayed(clinicalIndicationsTestSelect.startTestOrderButton, buttonName));
    }

    @And("the user selects the {string} tab")
    public void theUserClicksOnTab(String tabName) {
        clinicalIndicationsTestSelect.selectTab(tabName);
    }

    @And("the user clicks on view more icon")
    public void theUserClicksOnViewMoreIcon() {
        Click.element(driver, clinicalIndicationsTestSelect.testInfoIcon);
    }

    @And("the user click on Go to test page button")
    public void theUserClickOnGoToTestPageButtom() {
        Wait.seconds(1);
        Click.element(driver, clinicalIndicationsTestSelect.goToTestPageButtonFromPopup);
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
        clinicalIndicationsTestSelect.clickBackToSearchButton();
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

    @And("the user sees Clinical indications modal with two sections")
    public void theUserSeesClinicalIndicationsModalWithTwoSections(List<String> sectionName) {
        Assert.assertTrue(clinicalIndicationsTestSelect.clinicalIndicationsTabValidation(sectionName.get(0), sectionName.get(1)));
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
}
