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

    @Then("the Test Directory homepage is displayed")
    public void theTestDirectoryHomepageIsDisplayed() {
        homePage.TestDirectoryHomePageIsDisplayed();
    }

    @When("^a user clicks on any search filter$")
    public void userClicksOnAnySearchFilter() {
        homePage.rareAndInheritedDiseasesChkBox.click();
        homePage.tumorChkBox.click();
    }


    @Then("^the search results have been displayed$")
    public void searchResultsDisplays() throws InterruptedException {
        homePage.rareAndInheritedDiseasesSearchResult();
        homePage.tumorSearchResult();
    }

    @And("^the number of results shown in each filters & total results should match$")
    public void validateFilterResultCountToTotalResult() throws InterruptedException {
        long a = homePage.totalSearchResult();
        long b = homePage.rareAndInheritedDiseasesSearchResult();
        long c = homePage.tumorSearchResult();
        Assert.assertEquals(a, b + c);
    }

    @And("The user selects the Tests tab")
    public void theUserSelectsTheTestsTab() {
        homePage.testsTab.click();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
    }

    @Then("Various test details are displayed")
    public void variousTestDetailsAreDisplayed() {
        Assert.assertTrue("Various test details are displayed Properly", homePage.testResultsAreLoaded());
    }

    @And("The user has scrolled down the page to the bottom \\(Footer)")
    public void theUserHasScrolledDownThePageToTheBottomFooter() {
        Actions.scrollToBottom(driver);
        Wait.forElementToBeDisplayed(driver, homePage.NGISVersion);
    }

    @Then("The user can see the NGIS version number on the right side bottom of the page next to the privacy policy link")
    public void theUserCanSeeTheNGISVersionNumberOnTheRightSideBottomOfThePageNextToThePrivacyPolicyLink() {
        Assert.assertTrue("The NGIS version number is present next to the privacy policy link", homePage.isNGISVersionPresent());

    }
}
