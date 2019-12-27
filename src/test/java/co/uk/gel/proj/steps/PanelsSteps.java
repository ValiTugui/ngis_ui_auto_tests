package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PanelsSteps extends Pages {

    public PanelsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user should be able to see add another panel search field and search icon")
    public void theUserShouldBeSeeAddAnotherPanelSearchFieldAndSearchIcon() {
        Assert.assertTrue(panelsPage.panelSearchFieldAndSearchIcon());
    }

    @And("the user should  be able to search and add the {string} panels")
    public void theUserShouldBeAbleToSearchAndAddThePanels(String searchPanel) {
        Assert.assertTrue(panelsPage.searchAndAddPanel(searchPanel));
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

    @When("the user clicks on VisitPanelApp link")
    public void theUserClicksOnVisitPanelAppLink() {
        Assert.assertTrue(panelsPage.clicksOnVisitPanelsAppLink());
    }

    @Then("the user navigates to panelApp page")
    public void theUserNavigatesToPanelAppPage() {
        Assert.assertTrue(panelsPage.verifyPanelAppNavigation());
    }

    @Then("the user should be able to see the button options present")
    public void theUserShouldBeAbleToSeeTheButtonOptionsPresent() {
        Assert.assertTrue(panelsPage.verifyThePresenceOfPenetranceOptions());
    }

    @And("the user clicks on {string} button and button will show tick marked")
    public void theUserClicksOnButtonAndButtonWillShowTickMarked(String expectedButton) {
        Assert.assertTrue(panelsPage.verifyButtonAsCompletedByClickingInPanelsPage(expectedButton));
    }

    @Then("the user should able to deselect the selected panels")
    public void theUserShouldAbleToDeselectTheSelectedPanels() {
        panelsPage.deselectTheSelectedPanels();
        Assert.assertTrue(panelsPage.verifyTheDeselectedPanels());
    }

    @Then("the user can see the selected panels listed")
    public void theSeesTheSelectedPanelsUnderAddedPanels() {
        boolean testResult = false;
        testResult = panelsPage.addedPanelsList();
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on Save and Continue in Panels Page")
    public void theUserClicksOnSaveAndContinue() {
        panelsPage.clicksOnSaveAndContinueButtonOnPanelsPage();
    }

    @And("the user should be able to see a sub title {string} on panels page")
    public void theUserShouldBeAbleToSeeASubTitleOnPanelsPage(String subtitle) {
        Assert.assertTrue(panelsPage.verifyPenetranceTitle(subtitle));
    }

    @Then("the user should be able to see an additional line {string} underneath the penetrance title")
    public void theUserShouldBeAbleToSeeAnAdditionalLineUnderneathThePenetranceTitle(String textLine) {
        Assert.assertTrue(panelsPage.verifyTextLineUnderPenetranceTitle(textLine));
    }

    @Then("the User should be able to see the list of the panels under the {string} Section")
    public void theUserShouldBeAbleToSeeTheListOfThePanelsUnderTheSection(String panelsSuggestion) {
        Assert.assertTrue(panelsPage.verifyListOfSuggestedPanels(panelsSuggestion));
    }

}//end