package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
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

}//end