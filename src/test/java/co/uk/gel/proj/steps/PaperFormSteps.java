package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class PaperFormSteps extends Pages {

    public PaperFormSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user clicks the {string} hyperlink")
    public void theUserClicksTheHyperlink(String arg0) {
        paperFormPage.clickSignInToTheOnlineServiceButton();
    }

    @Then("the user logs in to the Test Order system successfully")
    public void theUserLogsInToTheTestOrderSystemSuccessfully() {
        patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
    }
}
