package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class PanelsSteps extends Pages {

    public PanelsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user should be able to see (.*) section with search field and search icon")
    public void theUserShouldBeSeeAddAnotherPanelSearchFieldAndSearchIcon(String expectedTitle) {
        boolean testResult = false;
        testResult = panelsPage.verifyPanelSearchFieldAndSearchIcon(expectedTitle);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelsPage.jpg");
            Assert.fail("Panels Search Page not displayed correctly");
        }
    }

    @And("the user search and add the {string} panels")
    public void theUserShouldBeAbleToSearchAndAddThePanels(String searchPanel) {
        boolean testResult = false;
        testResult = panelsPage.searchAndAddPanel(searchPanel);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelSearch.jpg");
            Assert.fail("Could search Panel");
        }
    }

    @And("the user should be able to see panels page is correctly displayed")
    public void theUserShouldBeAbleToSeePanelsPageIsCorrectlyDisplayed() {
        boolean testResult = panelsPage.verifyPanelsPageFields();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelSearchPage.jpg");
            Assert.fail("Panel search page not displayed");
        }
    }

    @And("the user should be able to change the penetrance status")
    public void theUserShouldBeAbleToChangeThePenetranceStatus() {
        boolean testResult = panelsPage.changeTheStatusOfPenetrance();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelPenetrance.jpg");
            Assert.fail("Panel penetrance not selected");
        }
    }

    @When("the user clicks on VisitPanelApp link")
    public void theUserClicksOnVisitPanelAppLink() {
        boolean testResult;
        testResult = panelsPage.clicksOnVisitPanelsAppLink();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelApplink.jpg");
            Assert.fail("Panel app link not clcked");
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Panels");
        }
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
        boolean testResult = panelsPage.verifyButtonAsCompletedByClickingInPanelsPage(expectedButton);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Panels.jpg");
            Assert.fail("Panels page button tick mark not correct");
        }
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
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PenetranceTitle.jpg");
            Assert.fail("Panel penetrance title could not verify");
        }
    }

    @Then("the user should be able to see an additional line {string} underneath the penetrance title")
    public void theUserShouldBeAbleToSeeAnAdditionalLineUnderneathThePenetranceTitle(String textLine) {
        boolean testResult = false;
        testResult = panelsPage.verifyPenetranceIntroMessage(textLine);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PenetranceIntro.jpg");
            Assert.fail("Panel penetrance intro message not correct");
        }
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
        boolean testResult = panelsPage.verifyDefaultStatusOfPenetranceButton(expectedButton);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PenetranceDefault.jpg");
            Assert.fail("Panel penetrance default status not correct");
        }
    }

    @And("the panels landing page displays the introduction message as shown below")
    public void thePanelsLandingPageDisplaysTheIntroductionMessageAsShownBelow(DataTable introMessage) {
        boolean testResult = false;
        List<List<String>> messages = introMessage.asLists();
        for (int i = 0; i < messages.size(); i++) {
            testResult = panelsPage.verifyThePanelAssignerIntoMessage(messages.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }

    @And("Penetrance section with options Complete and Incomplete")
    public void theUserShouldBeAbleToSeeTheButtonOptionsPresent() {
        boolean testResult = false;
        testResult = panelsPage.verifyThePresenceOfPenetranceOptions();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PenetranceOption.jpg");
            Assert.fail("Panel penetrance option not present");
        }
    }

    @And("the user should see the section with title (.*)")
    public void theUserShouldSeeTheSectionWithTitleDefaultPanelBasedOnTheClinicalInformation(String sectionName) {
        boolean testResult = false;
        testResult = panelsPage.verifyThePresenceOfSuggestedPanelsSection(sectionName);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelSuggested.jpg");
            Assert.fail("Suggested Panel section not present");
        }
    }

    @And("the user sees suggested panels under the section Default Panel based on the clinical information")
    public void theUserShouldBeAbleToSeeSuggestedPanelsUnderTheSection() {
        boolean testResult = false;
        testResult = panelsPage.verifySuggestedPanels();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelSuggested.jpg");
            Assert.fail("Suggested Panel section not present");
        }
    }

    @And("the user sees link with title View On PanelApp attached to all the suggested panels")
    public void theUserSeesLinkWithTitleViewOnPanelAppAttachedToAllTheSuggestedPanels() {
        boolean testResult = false;
        testResult =  panelsPage.verifySuggestedPanelsLinkToPanelApp();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelSuggested.jpg");
            Assert.fail("Suggested Panel section not present");
        }
    }

    @And("the user sees the (.*) message on the page")
    public void theUserSeesTheStringMessageOnThePage(String message) {
        boolean testResult = false;
        testResult =  panelsPage.verifyNoSuggestedPanels(message);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelSuggested.jpg");
            Assert.fail("Suggested Panel section not present");
        }
    }

    @And("the user should see the Add Panels section with the (.*)")
    public void theUserShouldSeeTheAddPanelsSectionWithThe(String message) {
        boolean testResult = false;
        testResult =  panelsPage.verifyMessageInAddPanels(message);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelSuggested.jpg");
            Assert.fail("Suggested Panel section not present");
        }
    }

    @Then("the user should not be able to deselect the suggested panel")
    public void theUserShouldNotBeAbleToDeselectTheSuggestedPanel() {
        boolean testResult = false;
        testResult = panelsPage.deselectSuggestedPanel();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelSuggested.jpg");
            Assert.fail("Suggested Panel deselected");
        }
    }

    @And("the default panel name is {string}")
    public void theDefaultPanelIs(String panelName) {
        if(!panelName.trim().isEmpty() || panelName.trim().length()!=0){
            Wait.waitForVisibility(driver, panelsPage.defaultPanelName, 15);
            Assert.assertEquals(panelName, panelsPage.defaultPanelName.getText());
        }
    }

    @When("the user searches and adds the following panels")
    public void theUserSearchesAndAddsTheFollowingPanels(List<String> panels) {
        boolean testResult = false;
        for (String panel: panels){
            testResult = panelsPage.searchAndAddPanel(panel);
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PanelSearch.jpg");
                Assert.fail("Could search Panel");
            }
        }
    }
}//end