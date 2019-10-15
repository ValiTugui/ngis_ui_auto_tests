package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import cucumber.api.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;


public class HomePageSteps extends Pages {


    public HomePageSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("a web browser is at the Private Test Selection homepage")
    public void aWebBrowserIsAtThePrivateTestSelectionHomepage() {
        homePage.navigateToPrivateTestDirectoryHomePage();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
    }

    @And("the user types in the CI term {string} in the search field and selects the first result from the results list")
    public void theUserTypesInTheCITermInTheSearchFieldAndSelectsTheFirstResultFromTheResultsList(String searchTerm) {
        homePage.typeInSearchField(searchTerm);
        homePage.clickSearchIconFromSearchField();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
    }
}
