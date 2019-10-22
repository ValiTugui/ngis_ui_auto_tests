package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class RequestingOrganisationSteps extends Pages {

    public RequestingOrganisationSteps(SeleniumDriver driver) {
        super(driver);
    }
    @Then("the details of the new organisation are displayed")
    public void theDetailsOfTheNewOrganisationAreDisplayed() {
        Assert.assertTrue("Stage : Requesting Organisation - Ordering entity details are not shown", requestingOrganisationPage.verifyOrganisationDetails());

    }


    @Then("there isn't any search results returned")
    public void thereIsnTAnySearchResultsReturned() {
        Assert.assertTrue("Stage : Requesting Organisation - There shouldn't be any search results returned ",requestingOrganisationPage.shouldSeeNoResultsOnThePage());
    }

    @And("the Save and Continue button should be disabled")
    public void theSaveAndContinueButtonShouldBeDisabled() {
        Assert.assertFalse("Stage : Requesting Organisation - Save And Continue button shouldn't be clickable  ",requestingOrganisationPage.checkTheContinueButtonIsClickable());
    }

    @And("the Save and Continue button should be clickable")
    public void theSaveAndContinueButtonShouldBeClickable() {
        Assert.assertTrue("Stage : Requesting Organisation - Save And Continue button should be clickable ", requestingOrganisationPage.checkTheContinueButtonIsClickable());
        requestingOrganisationPage.clickTheContinueButton();
        Assert.assertTrue(referralPage.stageIsSelected("Test package"));
    }
}
