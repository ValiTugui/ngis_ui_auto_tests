package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
//import cucumber.api.java.en.When;
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
        Assert.assertTrue("The Loading Wheel is Displayed", clinicalIndicationsTestSelect.validateIfLoadingWheelIsPresent());
    }

    @And("the text {string} is not displayed")
    public void theTextIsNotDisplayed(String wrongText) {
        Assert.assertTrue(wrongText + "  is not Displayed", clinicalIndicationsTestSelect.validateIfWrongTextIsNotDisplayed(clinicalIndicationsTestSelect.loadingText, wrongText));
    }

    @Then("the text {string} is displayed")
    public void theTextIsDisplayed(String correctText) {
        Assert.assertTrue(correctText + "  is Displayed", clinicalIndicationsTestSelect.validateIfCorrectTextIsDisplayed(clinicalIndicationsTestSelect.loadingText, correctText));
    }

    @And("the list of clinical indications is loading")
    public void theListOfClinicalIndicationsIsLoading() {
        Assert.assertTrue("The List of Clinical Indication is Loading", clinicalIndicationsTestSelect.validateIfLoadingWheelIsPresent());
    }

}
