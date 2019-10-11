package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import cucumber.api.java.en.Then;
import io.cucumber.java.en.Given;

public class PatientDetailsSteps extends Pages {


    public PatientDetailsSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Then("^the Patient Details page is displayed$")
    public void thePatientDetailsPageIsDisplayed() {
        patientDetailsPage.patientDetailsPageIsDisplayed();
    }


}
