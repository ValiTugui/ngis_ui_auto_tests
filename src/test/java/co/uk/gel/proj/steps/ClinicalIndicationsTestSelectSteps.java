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
        Assert.assertTrue(clinicalIndicationsTestSelect.testPackagePopupProps.findElements(By.tagName("h5")).get(0).getText().contains("Technology"));
        Assert.assertTrue(clinicalIndicationsTestSelect.testPackagePopupProps.findElements(By.tagName("h5")).get(1).getText().contains("Scope"));
        Assert.assertTrue(clinicalIndicationsTestSelect.testPackagePopupProps.findElements(By.tagName("h5")).get(2).getText().contains("Targeted genes"));
        Assert.assertTrue(clinicalIndicationsTestSelect.testPackagePopupProps.findElements(By.tagName("h5")).get(3).getText().contains("Sample type & state"));
        Assert.assertTrue(clinicalIndicationsTestSelect.testPackagePopupProps.findElements(By.tagName("h5")).get(4).getText().contains("Optimal family structure"));
        Assert.assertTrue(clinicalIndicationsTestSelect.testPackagePopupProps.findElements(By.tagName("h5")).get(5).getText().contains("Eligibility criteria"));

        for (int i = 0; i < clinicalIndicationsTestSelect.testPackagePopupProps.findElements(By.tagName("p")).size(); i++) {
            Assert.assertTrue(!clinicalIndicationsTestSelect.testPackagePopupProps.findElements(By.tagName("p")).get(i).getText().isEmpty());
        }
        Assert.assertTrue(clinicalIndicationsTestSelect.checkTestPagePopUpTitleMatchesSearchedText());
    }

    @And("the user clicks on {string} tab and see the clinical indications are loaded")
    public void theUserClicksOnTabAndSeeTheClinicalIndicationsAreLoaded(String arg0) {
        Assert.assertTrue("The List of Clinical Indication are NOT Loaded", clinicalIndicationsTestSelect.checkIfClinicalIndicationsAreLoaded());
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
}
