package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

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
        clinicalIndicationsTestSelect.waitUntilClinicalIndicationsResultsContainerIsLoaded();
        Assert.assertTrue("The List of Clinical Indication are NOT Loaded", clinicalIndicationsTestSelect.validateIfLoadingWheelIsPresent());
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

    @And("the user sees all {string} tabs and are clickable")
    public void theUserSeesAllTabsAndAreClickable(String tabCount) {
        Assert.assertTrue(clinicalIndicationsTestSelect.isTabPresent(Integer.parseInt(tabCount)));
        Assert.assertTrue(clinicalIndicationsTestSelect.isTabClickable(Integer.parseInt(tabCount)));
    }
}
