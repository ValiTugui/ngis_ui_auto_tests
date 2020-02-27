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

    @And("the user should be able to see (.*) section with search field and search icon")
    public void theUserShouldBeSeeAddAnotherPanelSearchFieldAndSearchIcon(String expectedTitle) {
        Assert.assertTrue(panelsPage.panelSearchFieldAndSearchIcon(expectedTitle));
    }

    @And("the user search and add the {string} panels")
    public void theUserShouldBeAbleToSearchAndAddThePanels(String searchPanel) {
        Assert.assertTrue(panelsPage.searchAndAddPanel(searchPanel));
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

    @And("the user clicks on (.*) button and button will show tick marked")
    public void theUserClicksOnButtonAndButtonWillShowTickMarked(String expectedButton) {
        Assert.assertTrue(panelsPage.verifyButtonAsCompletedByClickingInPanelsPage(expectedButton));
    }

    @Then("the user should be able to deselect the selected panels")
    public void theUserShouldBeAbleToDeselectTheSelectedPanels() {
        panelsPage.deselectTheSelectedPanels();
        Assert.assertTrue(panelsPage.verifyTheDeselectedPanels());
    }

    @And("the user clicks on Save and Continue in Panels Page")
    public void theUserClicksOnSaveAndContinue() {
        boolean testResult = false;
        testResult = panelsPage.clicksOnSaveAndContinueButtonOnPanelsPage();
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see a sub title (.*) on panels page")
    public void theUserShouldBeAbleToSeeASubTitleOnPanelsPage(String subtitle) {
        Assert.assertEquals(subtitle, panelsPage.verifyPenetranceTitle());
    }

    @Then("the user should be able to see an additional line {string} underneath the penetrance title")
    public void theUserShouldBeAbleToSeeAnAdditionalLineUnderneathThePenetranceTitle(String textLine) {
        Assert.assertTrue(panelsPage.verifyTextLineUnderPenetranceTitle(textLine));
    }
    @Then("the user should be able to see suggested panels under the (.*) section")
    public void theUserShouldBeAbleToSeeSuggestedPanelsUnderTheSection(String panelsSuggestion) {
        Assert.assertTrue(panelsPage.verifySuggestedPanels(panelsSuggestion));
    }
    @Then("the user sees the selected {string} panels under added panels")
    public void theUserSeesTheSelectedPanelsUnderAddedPanels(String selectedAddedPanels) {
        String[] addedPanels = null;
        if (selectedAddedPanels.indexOf(",") == -1) {
            addedPanels = new String[]{selectedAddedPanels};
        } else {
            addedPanels = selectedAddedPanels.split(",");
        }
        for(int i=0; i<addedPanels.length; i++) {
            Assert.assertTrue(panelsPage.verifyInAddedPanelsList(addedPanels[i]));
        }
    }

    @And("the user should see the default status of penetrance button as (.*)")
    public void theUserShouldSeeTheDefaultStatusOfPenetranceButtonAs(String expectedButton) {
        Assert.assertTrue(panelsPage.verifyDefaultStatusOfPenetranceButton(expectedButton));
    }

}//end