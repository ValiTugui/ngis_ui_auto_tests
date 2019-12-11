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

    @And("the user should be able to see an intro message {string} on requesting organisation page")
    public void theUserShouldBeAbleToSeeAnIntroMessageOnRequestingOrganisationPage(String introMessage) {
        Assert.assertTrue(panelPage.verifyTheIntroMessage(introMessage));
    }

    @Then("the user should be able to see hint text in search box on requesting organisation page")
    public void theUserShouldBeAbleToSeeHintTextInSearchBoxOnRequestingOrganisationPage() {
        Assert.assertTrue(panelPage.verifyHintText());
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

    @And("the user should click on {string} button and it will be selected")
    public void theUserShouldClickOnButtonAndItWillBeSelected(String expectedButton) {
        Assert.assertTrue(panelPage.clickOnButtonsInPanelsPage(expectedButton));
    }

    @Then("the user is able to see that the selected {string} button has tick marked")
    public void theUserIsAbleToSeeThatTheSelectedButtonHasTickMarked(String expectedButton) {
        Assert.assertTrue(panelPage.verifySelectedButton(expectedButton));
    }

    @And("the user clicks the Save and Continue button in panels page")
    public void theUserClicksTheSaveAndContinueButtonInPanelsPage() {
        panelPage.clickOnSaveAndContinueButtonInPanelsPage();
    }
}//end