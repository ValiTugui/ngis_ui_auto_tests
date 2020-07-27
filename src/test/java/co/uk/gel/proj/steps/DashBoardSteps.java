package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
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
        Assert.assertEquals(driver.getTitle(), dashBoardPage.tabTitle);
    }

    @And("User should be able to see my Dashboard")
    public void userShouldBeAbleToSeeMyDashBoardPage() {
        dashBoardPage.dashboardPageResultsIsLoaded();
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
    }

    @When("the user clicks on Find a genomic test")
    public void theUserClicksOnFindAGenomicTest() {
        boolean testResult = false;
        testResult = dashBoardPage.clickOnFindAGenomicTestTab();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be directed to Test selection url")
    public void theUserShouldBeDirectedToTestSelectionUrl() {
        boolean testResult = false;
        testResult = dashBoardPage.directedToTestSelectionPage();
        Assert.assertTrue(testResult);
    }

    @And("User clicks Manage Sample")
    public void userClicksManageSample() {
        boolean testResult = false;
        testResult = dashBoardPage.clickOnManageSampleTab();
        Assert.assertTrue(testResult);
    }
}
