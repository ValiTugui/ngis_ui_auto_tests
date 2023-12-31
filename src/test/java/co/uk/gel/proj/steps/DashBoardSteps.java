package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Action;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

public class DashBoardSteps extends Pages {

    public DashBoardSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("a web browser is at the dashboard page")
    public void aWebBrowserIsAtTheDashBoardPage() {
        dashBoardPage.navigateToDashboardPage();
        //SeleniumLib.sleepInSeconds(10);
        dashBoardPage.waitUntilDashboardPageResultsContainerIsLoaded();
        if (!driver.getTitle().equalsIgnoreCase(dashBoardPage.tabTitle)) {
            Debugger.println("Dashboard Page title is not as expected.");
            SeleniumLib.takeAScreenShot("DBTitleMismatch.jpg");
            Assert.fail("Expected DB PageTitle:" + dashBoardPage.tabTitle + ", Actual:" + driver.getTitle());
        }
    }

    @And("User should be able to see my Dashboard")
    public void userShouldBeAbleToSeeMyDashBoardPage() {
        SeleniumLib.sleepInSeconds(20);
        if (!dashBoardPage.dashboardPageResultsIsLoaded()) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_DashBoard.jpg");
            Assert.fail("Dashboard not visible.");
        }
        if (AppConfig.snapshotRequired) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_Dashboard.jpg");
        }
    }

    @Then("The user should see the Page title as {string}")
    public void theUserShouldSeeThePageTitleAs(String titleText) {
        if (!dashBoardPage.pageTitleValidation(titleText)) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_DBTitle.jpg");
            Assert.fail("The Correct Page Title is NOT Displayed");
        }
    }

    @And("the user sees the NHS logo on top in left side")
    public void theUserSeesTheNHSLogoOnTopInLeftSide() {
        boolean testResult = false;
        testResult = dashBoardPage.verifyTheNHSLogo();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NHSLogo.jpg");
            Assert.fail("NHS Logo not displayed");
        }
    }

    @Then("the user should be able to see clickable tabs")
    public void theUserShouldBeAbleToSeeClickableTabs() {
        boolean testResult = false;
        testResult = dashBoardPage.verifyTheDashboardTabs();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_DashboardTabs.jpg");
            Assert.fail("Clickable tabs not visible.");
        }
        if (AppConfig.snapshotRequired) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_DashboardTabs.jpg");
        }
    }

    @When("the user clicks on {string} Tab")
    public void theUserClicksOnTab(String tabName) {
        boolean testResult = false;
        testResult = dashBoardPage.clickOnTab(tabName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NoTab.jpg");
            Assert.fail("Could not click on tab:" + tabName);
        }
    }

    @Then("the user should be directed to Test selection url")
    public void theUserShouldBeDirectedToTestSelectionUrl() {
        boolean testResult = false;
        testResult = dashBoardPage.directedToTestSelectionPage();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TestSelection.jpg");
            Assert.fail("Not redirected to Test selection URL:");
        }
        if (AppConfig.snapshotRequired) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TestSelection.jpg");
        }
    }

    @And("User clicks Manage Sample")
    public void userClicksManageSample() {
        boolean testResult = false;
        testResult = dashBoardPage.clickOnManageSampleTab();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_ManageSample.jpg");
            Assert.fail("Not clicked on Manage Sample:");
        }
    }

    @And("the user logs in to the Interpretation Portal system")
    public void theUserLogsInToTheInterpretationPortalSystem() {
        if (AppConfig.snapshotRequired) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_InterpretationPortal.jpg");
        }
        interpretationPortalHomePage.loginToInterpretationPortalWithADCredentials("user5.test@nhs.net", "G3nomics123?");
    }

    @And("the user logs in to the {string} system")
    public void theUserLogsInToTheSystem(String portalName) {
        patientSearchPage.loginToTestOrderingSystemAsStandardUser(driver);
        //Debugger.println("The user is logged in to " + portalName);
        if (AppConfig.snapshotRequired) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_" + TestUtils.removeAWord(portalName, " ") + ".jpg");
        }
    }

    @And("User Navigates back to Dashboard")
    public void userNavigatesBackToDashboard() {
        driver.navigate().back();
    }

    @Given("a web browser is at the dashboard page with super user")
    public void aWebBrowserIsAtTheDashboardPageWithSuperUser() {
        dashBoardPage.navigateToDashboardPageWithSuperUser();
        SeleniumLib.sleepInSeconds(10);
        dashBoardPage.waitUntilDashboardPageResultsContainerIsLoaded();
        if (!driver.getTitle().equalsIgnoreCase(dashBoardPage.tabTitle)) {
            Debugger.println("Dashboard Page title is not as expected.");
            SeleniumLib.takeAScreenShot("DBTitleMismatch.jpg");
            Assert.fail("Expected DB PageTitle:" + dashBoardPage.tabTitle + ", Actual:" + driver.getTitle());
        }
    }

    @And("the user should be able to click on the following tabs")
    public void theUserShouldBeAbleToClicksOnTabs(List<String> inputTabs) {
        boolean testResult = false;
        for (String input_tab : inputTabs) {
            testResult = dashBoardPage.clickOnTabsAndVerifyTheUrl(input_tab);
            if (!testResult) {
                Debugger.println("Could not click on tab : " + input_tab);
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "CouldNotClickOnTab_" + input_tab + ".jpg");
                Assert.assertTrue(testResult);
            }
        }
    }

    @Then("the user should see the banner elements based on the current environment")
    public void theUserShouldSeeTheBannerDetailsBasedOnTheCurrentEnvironment() {
        Wait.seconds(4);
        //Expected UI Values
        String expectedEnvironment = System.getProperty("TestEnvironment");
        String expectedServiceName = "Genomic Medicine Service";
        String expectedNonProdBackgroundBannerColor = "174, 37, 115"; //purple
        String expectedTestEnvBackgroundColor = "255, 184, 28"; //yellow
        String expectedUserName = AppConfig.getApp_username().split("@")[0].replace(".", " ");
        String expectedProdBackgroundBannerColor = "rgba(0, 94, 184, 1)"; //blue

        if(driver.getCurrentUrl().contains("dashboard")){
            if(!Wait.isElementDisplayed(driver, dashBoardPage.dashboardGenomicsMedicineServiceLogo, 5)){
                Assert.fail("Dashboard - Genomics Medicine Service Logo is not displayed");
            }
        }else{
            if (!Wait.isElementDisplayed(driver, dashBoardPage.testSelectionNhsLogo, 5)) {
                Assert.fail("NHS Logo is not displayed");
            }
            if (!Wait.isElementDisplayed(driver, dashBoardPage.genomicsMedicineServiceLogo, 5)) {
                Assert.fail("Genomics Medicine Service");
            }
            String actualServiceName = Action.getText(dashBoardPage.genomicsMedicineServiceLogo);

            //Validate that Genomics Medicine Service name is displayed correctly
            Assert.assertEquals(expectedServiceName, actualServiceName);
        }

        // Non prod environments must have the test environment displayed on a specific background color
        // Banner's background color must be same for all non prod environments but different comparing to prod environment
        if(expectedEnvironment.equals("prod")){
            String actualProdBackgroundBannerColor = Action.getCssValue(dashBoardPage.nonProdBannerColor, "background-color");
            Assert.assertEquals(expectedProdBackgroundBannerColor, actualProdBackgroundBannerColor);

            //Verify to not have the environment displayed in Prod
            Assert.assertFalse(Wait.isElementDisplayed(driver, dashBoardPage.testEnvironment, 5));
            Assert.assertFalse(Wait.isElementDisplayed(driver, dashBoardPage.getTestEnvBackgroundColor, 5));

        }else{
            //Actual UI values
            String actualNonProdBackgroundBannerColor = Action.getCssValue(dashBoardPage.nonProdBannerColor, "background-color");
            String actualTestEnvBackgroundColor = String.valueOf(dashBoardPage.getTestEnvBackgroundColor.getCssValue("background-color"));
            String actualEnvironment = Action.getText(dashBoardPage.testEnvironment).split(": ")[1];

            if (!Wait.isElementDisplayed(driver, dashBoardPage.testEnvironment, 5)) {
                Assert.fail("Test Environment is not displayed");
            }
            Assert.assertEquals(expectedEnvironment, actualEnvironment);
            Assert.assertTrue(actualNonProdBackgroundBannerColor.contains(expectedNonProdBackgroundBannerColor));
            Assert.assertTrue(actualTestEnvBackgroundColor.contains(expectedTestEnvBackgroundColor));
        }

        // Test Ordering page must have the logout link and the username
        Wait.seconds(2);
        if (driver.getCurrentUrl().endsWith("test-order/patient-search")) {
            String actualUserName = Action.getText(dashBoardPage.loggedInUserName);
            if (!Wait.isElementDisplayed(driver, homePage.logOutLink, 5)) {
                Assert.fail("Logout link is not present on test-order/patient-search page");
            }
            Assert.assertEquals(expectedUserName, actualUserName);
        }
    }
}
