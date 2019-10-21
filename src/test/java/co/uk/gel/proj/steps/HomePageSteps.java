package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

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
    }

    @And("the user types in the CI term  in the search field and selects the first result from the results list")
    public void theUserTypesInTheCITermInTheSearchFieldAndSelectsTheFirstResultFromTheResultsList(List<String> searchTerms) {
        homePage.typeInSearchField(searchTerms.get(0));
        homePage.clickSearchIconFromSearchField();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
    }
}
