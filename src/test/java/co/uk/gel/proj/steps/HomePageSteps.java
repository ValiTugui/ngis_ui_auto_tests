package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;


public class HomePageSteps extends Pages {


    public HomePageSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("a web browser is at the Private Test Selection homepage")
    public void aWebBrowserIsAtThePrivateTestSelectionHomepage(List<String> attributeOfURL) {
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        Assert.assertEquals(driver.getTitle(), homePage.tabTitle);
    }

    @And("the user types in the CI term  in the search field and selects the first result from the results list")
    public void theUserTypesInTheCITermInTheSearchFieldAndSelectsTheFirstResultFromTheResultsList(List<String> searchTerms) {
        homePage.typeInSearchField(searchTerms.get(0));
        homePage.clickSearchIconFromSearchField();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
    }

    @And("the user types in the CI term  in the search field")
    public void theUserTypesInTheCITermInTheSearchField(List<String> searchTerms) {
        homePage.typeInSearchField(searchTerms.get(0));
        homePage.clickSearchIconFromSearchField();
    }

    @Then("the Test Directory homepage is displayed")
    public void theTestDirectoryHomepageIsDisplayed() {
        homePage.TestDirectoryHomePageIsDisplayed();
    }

    @Then("^the search results have been displayed$")
    public void searchResultsDisplays() throws InterruptedException {
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
    }

    @And("^the number of results shown in each filters & total results should match$")
    public void validateFilterResultCountToTotalResult() throws InterruptedException {
        long a = homePage.totalSearchResult();
        long b = homePage.rareAndInheritedDiseasesSearchResult();
        long c = homePage.tumorSearchResult();
        Assert.assertEquals(a, b + c);
    }

    @And("the user selects the Tests tab")
    public void theUserSelectsTheTestsTab() {
        homePage.testsTab.click();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
    }

    @Then("various test details are displayed")
    public void variousTestDetailsAreDisplayed() {
        Assert.assertTrue("Various test details are displayed Properly", homePage.testResultsAreLoaded());
    }

    @And("the user has scrolled down the page to the bottom \\(Footer)")
    public void theUserHasScrolledDownThePageToTheBottomFooter() {
        Actions.scrollToBottom(driver);
        Wait.forElementToBeDisplayed(driver, globalBehaviourPage.footerText);
    }
}
