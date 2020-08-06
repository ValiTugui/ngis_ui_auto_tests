package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class DashBoardSteps extends Pages {

    public DashBoardSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("a web browser is at the dashboard page")
    public void aWebBrowserIsAtTheDashBoardPage() {
        dashBoardPage.navigateToDashboardPage();
        dashBoardPage.waitUntilDashboardPageResultsContainerIsLoaded();
        if(!driver.getTitle().equalsIgnoreCase(dashBoardPage.tabTitle)) {
            Debugger.println("Dashboard Page title is not as expected.");
            SeleniumLib.takeAScreenShot("DBTitleMismatch.jpg");
            Assert.fail("Expected DB PageTitle:"+dashBoardPage.tabTitle+", Actual:"+driver.getTitle());
        }
    }

    @And("User should be able to see my Dashboard")
    public void userShouldBeAbleToSeeMyDashBoardPage() {
        dashBoardPage.dashboardPageResultsIsLoaded();
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Dashboard.jpg");
        }
    }

    @Then("The user should see the Page title as {string}")
    public void theUserShouldSeeThePageTitleAs(String titleText) {
        Assert.assertTrue("The Correct Page Title is NOT Displayed", dashBoardPage.pageTitleValidation(titleText));
    }

    @And("the user sees the NHS logo on top in left side")
    public void theUserSeesTheNHSLogoOnTopInLeftSide() {
        boolean testResult = false;
        testResult = dashBoardPage.verifyTheNHSLogo();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see clickable tabs")
    public void theUserShouldBeAbleToSeeClickableTabs() {
        boolean testResult = false;
        testResult = dashBoardPage.verifyTheDashboardTabs();
        Assert.assertTrue(testResult);
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_DashboardTabs.jpg");
        }
    }

    @When("the user clicks on {string} Tab")
    public void theUserClicksOnTab(String tabName) {
        boolean testResult = false;
        testResult = dashBoardPage.clickOnTab(tabName);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be directed to Test selection url")
    public void theUserShouldBeDirectedToTestSelectionUrl() {
        boolean testResult = false;
        testResult = dashBoardPage.directedToTestSelectionPage();
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TestSelection.jpg");
        }
        Assert.assertTrue(testResult);
    }

    @And("User clicks Manage Sample")
    public void userClicksManageSample() {
        boolean testResult = false;
        testResult = dashBoardPage.clickOnManageSampleTab();
        Assert.assertTrue(testResult);
    }

    @And("the user logs in to the Interpretation Portal system")
    public void theUserLogsInToTheInterpretationPortalSystem() {
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_InterpretationPortal.jpg");
        }
        interpretationPortalHomePage.loginToInterpretationPortalWithADCredentials("user5.test@nhs.net", "G3nomics123");
    }

    @And("the user logs in to the {string} system")
    public void theUserLogsInToTheSystem(String portalName) {
        patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
        Debugger.println("The user is logged in to " + portalName);
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_"+TestUtils.removeAWord(portalName," ")+".jpg");
        }
    }

    @And("User Navigates back to Dashboard")
    public void userNavigatesBackToDashboard() {
        driver.navigate().back();
    }
}
