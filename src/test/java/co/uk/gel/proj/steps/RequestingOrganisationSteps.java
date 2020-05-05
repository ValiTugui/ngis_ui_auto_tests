package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RequestingOrganisationSteps extends Pages {

    public RequestingOrganisationSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Then("the details of the new organisation are displayed")
    public void theDetailsOfTheNewOrganisationAreDisplayed() {
        try {
            Assert.assertTrue("Stage : Requesting Organisation - Ordering entity details are not shown", requestingOrganisationPage.verifyOrganisationDetails());
            for (WebElement header : requestingOrganisationPage.organisationDetailHeader) {
                Assert.assertTrue(!Actions.getText(header).isEmpty());
            }
            for (WebElement details : requestingOrganisationPage.organisationDetailText) {
                Assert.assertTrue(!Actions.getText(details).isEmpty());
            }
            // to store Ordering entity name and address
            PatientDetailsPage.newPatient.setOrderingEntity(Actions.getText(requestingOrganisationPage.organisationDetailText.get(1)));
        }catch(Exception exp){
            Debugger.println("Exception from verifying details of Selected Organization Details: "+exp);
            SeleniumLib.takeAScreenShot("SelectedOrgDetails.jpg");
        }
    }

    @Then("there isn't any search results returned")
    public void thereIsnTAnySearchResultsReturned() {
        Assert.assertTrue("Stage : Requesting Organisation - There shouldn't be any search results returned ", requestingOrganisationPage.shouldSeeNoResultsOnThePage());
    }

    @And("the Save and Continue button should be disabled")
    public void theSaveAndContinueButtonShouldBeDisabled() {
        Assert.assertFalse("Stage : Requesting Organisation - Save And Continue button shouldn't be clickable  ", requestingOrganisationPage.checkTheContinueButtonIsClickable());
    }

    @And("the requesting organisation has search label displayed")
    public void theRequestingOrganisationHasSearchLabelDisplayed() {
        Assert.assertTrue(requestingOrganisationPage.checkOrderingEntityPageLabel());
    }


    @And("the user sees the search field with search icon")
    public void theUserSeesTheSearchFieldWithSearchIcon() {
        Assert.assertTrue(requestingOrganisationPage.checkSearchIcon());
    }

    @And("the user sees the tool tip with text as {string}")
    public void theUserSeesTheToolTipWithTextAs(String toolTipTextValue) {
        Assert.assertTrue(requestingOrganisationPage.checkToolTipInfo(toolTipTextValue));
    }

    @When("the user clicks on the tool tip")
    public void theUserClicksOnTheToolTip() {
        requestingOrganisationPage.clickToolTipIcon();
    }

    @Then("a slide out panel is displayed with following header as {string},  body details as {string} and a X for the user to select to close the panel")
    public void aSlideOutPanelIsDisplayedWithFollowingHeaderAsBodyDetailsAsAndAXForTheUserToSelectToCloseThePanel(String headerText, String bodyText) {
        Assert.assertTrue(requestingOrganisationPage.checkSlidePanelInfo(headerText, bodyText));
    }

    @And("the user is able to close the panel")
    public void theUserIsAbleToCloseThePanel() {
        requestingOrganisationPage.clickSlidePanelCloseButton();
    }

    @Then("the requesting organisation page in Test Directory is displayed with Title, title copy text, search icon and search placeholder text")
    public void theRequestingOrganisationPageInTestDirectoryIsDisplayedWithTitleTitleCopyTextSearchIconAndSearchPlaceholderText(List<String> checkText) {
        Assert.assertTrue(requestingOrganisationPage.checkRequestingOrganisationPageInfo(checkText.get(0), checkText.get(1)));
        Assert.assertTrue(paperFormPage.confirmOrderingEntitySearchFieldPlaceholderText(checkText.get(2)));
    }

    @And("the message {string} displayed on the page")
    public void theMessageDisplayedOnThePage(String expMessage) {
        String actualMessage = requestingOrganisationPage.getNoResultMessage();
        Assert.assertNotNull(actualMessage);
        Assert.assertTrue(actualMessage.equalsIgnoreCase(actualMessage));
    }
}
