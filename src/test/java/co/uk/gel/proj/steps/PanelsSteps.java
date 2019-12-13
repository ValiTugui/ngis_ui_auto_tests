package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class PanelsSteps extends Pages {

    public PanelsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user should be able to see add another panel search field and search icon")
    public void theUserShouldBeSeeAddAnotherPanelSearchFieldAndSearchIcon() {
        Assert.assertTrue(panelsPage.panelSearchFieldAndSearchIcon());
    }

    @And("the user should  be able to search and add the {string}panels")
    public void theUserShouldBeAbleToSearchAndAddThePanels(String panelResult) {
        Assert.assertTrue(panelsPage.searchPanelsInSearchBox(panelResult));
    }

    @And("the user should be able to see selected panels")
    public void theUserShouldBeAbleToSeeSelectedPanels() {
        Assert.assertTrue(panelsPage.selectedPanels());
    }

    @Then("the user sees the selected panels under added panels")
    public void theUserSeesTheSelectedPanelsUnderAddedPanels() {
        Assert.assertTrue(panelsPage.addedPanelsList());
    }

    @And("the user should be able to see panels page is correctly displayed")
    public void theUserShouldBeAbleToSeePanelsPageIsCorrectlyDisplayed() {
        Assert.assertTrue(panelsPage.verifyPanelsPageFields());
    }

    @And("the user should be able to change the penetrance status")
    public void theUserShouldBeAbleToChangeThePenetranceStatus() {
        Assert.assertTrue(panelsPage.changeTheStatusOfPenetrance());
    }

    @Then("the user clicks on VisitPanelApp link and navigates to panelApp page")
    public void theUserClicksOnVisitPanelAppLinkAndNavigatesToPanelAppPage() {
        Assert.assertTrue(panelsPage.clicksOnVisitPanelsAppLink());
    }

    @And("the user should be able to see the button options present")
    public void theUserShouldBeAbleToSeeTheButtonOptionsPresent() {
        Assert.assertTrue(panelsPage.completeIncompleteButtonsPresent());
    }

    @Then("the user clicks on {string} button and button will show tick marked")
    public void theUserClicksOnButtonAndButtonWillShowTickMarked(String expectedButton) {
        Assert.assertTrue(panelsPage.verifyButtonAsCompletedByClickingInPanelsPage(expectedButton));
    }

    @And("the user should able to deselect the selected panels")
    public void theUserShouldAbleToDeselectTheSelectedPanels() {
        panelsPage.deselectTheSelectedPanels();
        Assert.assertTrue(panelsPage.verifyTheDeselectedPanels());
    }
}//end