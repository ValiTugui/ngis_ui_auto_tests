package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.TestUtils;
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
        boolean testResult = false;
        testResult = panelsPage.verifyPanelSearchFieldAndSearchIcon(expectedTitle);
        Assert.assertTrue(testResult);
    }

    @And("the user search and add the {string} panels")
    public void theUserShouldBeAbleToSearchAndAddThePanels(String searchPanel) {
        boolean testResult = false;
        testResult = panelsPage.searchAndAddPanel(searchPanel);
        Assert.assertTrue(testResult);
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
        boolean testResult;
        testResult = panelsPage.clicksOnVisitPanelsAppLink();
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Panels");
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user navigates to panelApp page")
    public void theUserNavigatesToPanelAppPage() {
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelAppExternal");
        }
        Assert.assertTrue(panelsPage.verifyPanelAppNavigation());
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

    @And("the user should be able to see a sub title (.*) on panels page")
    public void theUserShouldBeAbleToSeeASubTitleOnPanelsPage(String subtitle) {
        boolean testResult = false;
        testResult = panelsPage.verifyPenetranceTitle(subtitle);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see an additional line {string} underneath the penetrance title")
    public void theUserShouldBeAbleToSeeAnAdditionalLineUnderneathThePenetranceTitle(String textLine) {
        boolean testResult = false;
        testResult = panelsPage.verifyPenetranceIntroMessage(textLine);
        Assert.assertTrue(testResult);
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

    @And("the panels landing page displays the introduction message as {string}")
    public void thePanelsLandingPageDisplaysTheIntroductionMessageAs(String introMessage) {
        boolean testResult = false;
        testResult = panelsPage.verifyThePanelAssignerIntoMessage(introMessage);
        Assert.assertTrue(testResult);
    }

    @And("Penetrance section with options Complete and Incomplete")
    public void theUserShouldBeAbleToSeeTheButtonOptionsPresent() {
        boolean testResult = false;
        testResult = panelsPage.verifyThePresenceOfPenetranceOptions();
        Assert.assertTrue(testResult);
    }

    @And("the user should see the section with title (.*)")
    public void theUserShouldSeeTheSectionWithTitleSuggestionsBasedOnTheClinicalInformation(String sectionName) {
        boolean testResult = false;
        testResult = panelsPage.verifyThePresenceOfSuggestedPanelsSection(sectionName);
        Assert.assertTrue(testResult);
    }

    @And("the user sees suggested panels under the section Suggestions based on the clinical information")
    public void theUserShouldBeAbleToSeeSuggestedPanelsUnderTheSection() {
        boolean testResult = false;
        testResult = panelsPage.verifySuggestedPanels();
        Assert.assertTrue(testResult);
    }

    @And("the user sees link with title View On PanelApp attached to all the suggested panels")
    public void theUserSeesLinkWithTitleViewOnPanelAppAttachedToAllTheSuggestedPanels() {
        boolean testResult = false;
        testResult =  panelsPage.verifySuggestedPanelsLinkToPanelApp();
        Assert.assertTrue(testResult);
    }

    @And("the user sees an (.*) message on the page")
    public void theUserSeesAnStringMessageOnThePage(String message) {
        boolean testResult = false;
        testResult =  panelsPage.verifyNoSuggestedPanels(message);
        Assert.assertTrue(testResult);
    }
}//end