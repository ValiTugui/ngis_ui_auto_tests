package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class NewPatientSteps extends Pages {


    public NewPatientSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Then("the new patient page is opened")
    public void theNewPatientPageIsOpened() {
        newPatientPage.newPatientPageIsDisplayed();
    }

    @And("the NHS number and DOB fields are pre-populated in the new patient page from the search page")
    public void theNHSNumberAndDOBFieldsArePrePopulatedInTheNewPatientPageFromTheSearchPage() {
         newPatientPage.nhsNumberAndDOBFieldsArePrePopulatedInNewPatientPage();
    }


}


