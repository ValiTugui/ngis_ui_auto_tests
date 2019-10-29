package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
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

    @Then("The user should see the Page title as Welcome to the National Genomic Informatics System")
    public void thePageTitleValidation () {
        String titleText = "Welcome to the National Genomic Informatics System";
        Assert.assertTrue("The Correct Page Title is Displayed", dashBoardPage.pageTitleValidation(titleText));
    }
}
