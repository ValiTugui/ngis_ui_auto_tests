package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
//import cucumber.api.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class ClinicalIndicationsTestSelectSteps extends Pages {


    public ClinicalIndicationsTestSelectSteps(SeleniumDriver driver) {
        super(driver);
    }

//    @When("^the user clicks on Start test order button$")
//    public void clickStartTestOrderButton() {
//        homePage.closeCookiesBannerFromFooter();
//        clinicalIndicationsTestSelect.clickStartTestOrderButton();
//    }

    @When("the user clicks the Start referral button")
    public void theUserClicksTheStartReferralButton() {
        homePage.closeCookiesBannerFromFooter();
        clinicalIndicationsTestSelect.clickStartReferralButton();
    }




}
