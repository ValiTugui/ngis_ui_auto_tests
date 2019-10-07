package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;



public class PatientDetailsSteps extends Pages {


    public PatientDetailsSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Then("^the Patient Details page is displayed$")
    public void thePatientDetailsPageIsDisplayed() {
        patientDetailsPage.patientDetailsPageIsDisplayed();
    }

}
