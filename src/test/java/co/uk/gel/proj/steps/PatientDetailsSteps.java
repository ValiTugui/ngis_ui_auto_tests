package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import io.cucumber.java.en.When;


public class PatientDetailsSteps extends Pages {

    PatientDetailsPage patientDetails;
    public PatientDetailsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @When("^the user clicks the Start Referral button$")
    public void clickStartReferralButton() {

        patientDetailsPage.clickStartReferralButton();

    }

}
