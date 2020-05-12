package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
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
       boolean testResult = false;
       testResult =  homePage.typeInSearchField(searchTerms.get(0));
       Assert.assertTrue(testResult);
       AppConfig.properties.setProperty("Search_Term", searchTerms.get(0));
       testResult = homePage.clickSearchIconFromSearchField();
       Assert.assertTrue(testResult);
        testResult = homePage.waitUntilHomePageResultsContainerIsLoaded();
        Assert.assertTrue(testResult);
        homePage.closeCookiesBannerFromFooter();
        testResult = homePage.selectFirstEntityFromResultList();
        Assert.assertTrue(testResult);
        Wait.seconds(3);
    }

    @And("the user types in the CI term  in the search field")
    public void theUserTypesInTheCITermInTheSearchField(List<String> searchTerms) {
        homePage.typeInSearchField(searchTerms.get(0));
        homePage.clickSearchIconFromSearchField();
    }

    @Then("the Test Directory homepage is displayed")
    public void theTestDirectoryHomepageIsDisplayed() {
        boolean testResult = false;
        testResult = homePage.TestDirectoryHomePageIsDisplayed();
        Assert.assertTrue(testResult);

    }

    @Then("^the search results have been displayed$")
    public void searchResultsDisplays() throws InterruptedException {
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
    }

    @And("^the number of results shown in each filters & total results should match$")
    public void validateFilterResultCountToTotalResult() throws InterruptedException {
        long totalSearchResult = homePage.totalSearchResult();
        if(totalSearchResult == 0){
            Debugger.println("Total Search Result Zero.. Something wrong.");
            Assert.assertTrue(false);
        }
        long inheritedDisease = homePage.rareAndInheritedDiseasesSearchResult();
        long tumours = homePage.tumorSearchResult();
        if(totalSearchResult != (inheritedDisease+tumours)){
            Debugger.println("Total search result not equals the inherited and tumor search results as expected.");
            Assert.assertTrue(false);
        }

    }

    @And("the user selects the Tests tab")
    public void theUserSelectsTheTestsTab() {
        Wait.seconds(2);
        homePage.testsTab.click();
        Wait.seconds(2);
        if(!homePage.waitUntilHomePageResultsContainerIsLoaded()){
            Assert.assertTrue(false);
        }
        homePage.closeCookiesBannerFromFooter();

    }

    @Then("various test details are displayed")
    public void variousTestDetailsAreDisplayed() {
        Assert.assertTrue("Various test details are NOT displayed Properly", homePage.testResultsAreLoaded());
    }

    @And("the user has scrolled down the page to the bottom \\(Footer)")
    public void theUserHasScrolledDownThePageToTheBottomFooter() {
        Actions.scrollToBottom(driver);
        Wait.forElementToBeDisplayed(driver, globalBehaviourPage.footerText);
    }

    @And("the browser navigates to the Private Test Selection Homepage with the user's last search and results automatically loaded in")
    public void theBrowserNavigatesToThePageWithTheUserSLastSearchAndResultsAutomaticallyLoadedIn() {
        Assert.assertEquals(driver.getTitle(), homePage.tabTitle);
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        Assert.assertTrue(homePage.searchField.getAttribute("value").matches(AppConfig.getSearchTerm()));

    }

}
