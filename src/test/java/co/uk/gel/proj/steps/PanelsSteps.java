package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class PanelsSteps extends Pages{

    public PanelsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user should be able to see add another panel search field and search icon")
    public void theUserShouldBeSeeAddAnotherPanelSearchFieldAndSearchIcon() {
        Assert.assertTrue(panelPage.panelSearchFieldAndSearchIcon());
    }

    @And("the user should  be able to search and add the {string}panels")
    public void theUserShouldBeAbleToSearchAndAddThePanels(String panelResult) {
        Assert.assertTrue(panelPage.searchPanelsInSearchBox(panelResult));
    }

    @And("the user should be able to see selected panels")
    public void theUserShouldBeAbleToSeeSelectedPanels() {
        Assert.assertTrue(panelPage.selectedPanels());
    }

    @Then("the user sees the selected panels under added panels")
    public void theUserSeesTheSelectedPanelsUnderAddedPanels() {
        Assert.assertTrue(panelPage.addedPanelsList());
    }

    @And("the user should be able to see panels page is correctly displayed")
    public void theUserShouldBeAbleToSeePanelsPageIsCorrectlyDisplayed() {
        Assert.assertTrue(panelPage.verifyPanelsPageFields());
    }

    @And("the user should be able to change the penetrance status")
    public void theUserShouldBeAbleToChangeThePenetranceStatus() {
        Assert.assertTrue(panelPage.changeTheStatusOfPenetrance());
    }

    @Then("the user clicks on VisitPanelApp link and navigates to panelApp page")
    public void theUserClicksOnVisitPanelAppLinkAndNavigatesToPanelAppPage() {
        Assert.assertTrue(panelPage.clicksOnVisitPanelsAppLink());
    }

    //E2EUI-1231
    @And("the user should be able to see the button options present")
    public void theUserShouldBeAbleToSeeTheButtonOptionsPresent() {
        Assert.assertTrue(panelPage.completeIncompleteButtonsPresent());
    }

    @Then("the user clicks on {string} button and button will show tick marked")
    public void theUserClicksOnButtonAndButtonWillShowTickMarked(String expectedButton) {
        Assert.assertTrue(panelPage.verifyButtonAsCompletedByClickingInPanelsPage(expectedButton));
    }

}//end