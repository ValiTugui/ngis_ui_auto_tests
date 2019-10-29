package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class HomePageSteps extends Pages {


    public HomePageSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("a web browser is at the Private Test Selection homepage")
    public void aWebBrowserIsAtThePrivateTestSelectionHomepage() {
        homePage.navigateToPrivateTestDirectoryHomePage();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        Assert.assertEquals(driver.getTitle(), homePage.tabTitle);
    }

    @And("the user types in the CI term {string} in the search field and selects the first result from the results list")
    public void theUserTypesInTheCITermInTheSearchFieldAndSelectsTheFirstResultFromTheResultsList(String searchTerm) {
        homePage.typeInSearchField(searchTerm);
        homePage.clickSearchIconFromSearchField();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
    }

    @When("^a user clicks on any search filter$")
    public void userClicksOnAnySearchFilter() throws InterruptedException {
        //SeleniumLib.isClickable(testDirectoryPage.rareAndInheritedDiseasesChkBox);
        homePage.rareAndInheritedDiseasesChkBox.click();
        homePage.tumorChkBox.click();
    }


    @Then("^the search results have been displayed$")
    public void searchResultsDisplays() throws InterruptedException {
        //SeleniumLib.isClickable(testDirectoryPage.rareAndInheritedDiseasesChkBox);
        homePage.rareAndInheritedDiseasesSearchResult();
        homePage.tumorSearchResult();
    }

    @And("^the number of results shown in each filters & total results should match$")
    public void validateFilterResultCountToTotalResult() throws InterruptedException {
        long a=homePage.totalSearchResult();
        long b=homePage.rareAndInheritedDiseasesSearchResult();
        long c=homePage.tumorSearchResult();
        Assert.assertEquals(a, b+c);
    }

    @When ("the user has selected a test {string}")
    public void theUserSelectsATest(String searchTerm) {
        homePage.testsTab.click();
        homePage.typeInSearchField(searchTerm);
        homePage.clickSearchIconFromSearchField();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
    }
}
