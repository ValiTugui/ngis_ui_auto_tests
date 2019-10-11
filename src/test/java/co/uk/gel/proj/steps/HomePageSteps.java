package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import cucumber.api.java.en.Then;
import io.cucumber.java.en.Given;


public class HomePageSteps extends Pages {


    public HomePageSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("a web browser is at the Private Test Selection homepage")
    public void aWebBrowserIsAtThePrivateTestSelectionHomepage() {
        homePage.navigateToPrivateTestDirectoryHomePage();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
    }

}
