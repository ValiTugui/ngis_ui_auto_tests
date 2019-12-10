package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class PanelsSteps extends Pages{

    public PanelsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user should be able to see add another panel search field and search icon")
    public void theUserShouldBeSeeAddAnotherPanelSearchFieldAndSearchIcon() {
        boolean testResult = false;
        testResult = panelsPage.panelSearchFieldAndSearchIcon();
        Assert.assertTrue(testResult);

    }

    @And("the user should  be able to search and add the {string}panels")
    public void theUserShouldBeAbleToSearchAndAddThePanels(String panelResult) {
        boolean testResult = false;
        testResult = panelsPage.searchPanelsInSearchBox(panelResult);
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see selected panels")
    public void theUserShouldBeAbleToSeeSelectedPanels() {
        boolean testResult = false;
        testResult = panelsPage.selectedPanels();
        Assert.assertTrue(testResult);

    }
    @Then("the user can see the selected panels listed")
    public void theSeesTheSelectedPanelsUnderAddedPanels() {
        boolean testResult = false;
        testResult = panelsPage.addedPanelsList();
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see an intro message {string} on requesting organisation page")
    public void theUserShouldBeAbleToSeeAnIntroMessageOnRequestingOrganisationPage(String introMessage) {
        boolean testResult = false;
        testResult = panelsPage.verifyTheIntroMessage(introMessage);
        Assert.assertTrue(testResult);
    }


    @Then("the user should be able to see hint text in search box on requesting organisation page")
    public void theUserShouldBeAbleToSeeHintTextInSearchBoxOnRequestingOrganisationPage() {
        boolean testResult = false;
        testResult = panelsPage.verifyHintText();
        Assert.assertTrue(testResult);

    }

    @And("the suggested panels should be listed in the page")
    public void suggestedPanelsShouldBeListedInThePage() {
        boolean testResult = false;
        testResult = panelsPage.selectedPanels();
        Assert.assertTrue(testResult);

    }

}//end