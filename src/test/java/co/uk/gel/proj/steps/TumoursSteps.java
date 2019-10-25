package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.TumoursPage;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

public class TumoursSteps extends Pages {

    public TumoursSteps(SeleniumDriver driver) {
        super(driver);
    }


    @And("the user enters {string} in the date of diagnosis field")
    public void theUserEntersInTheDateOfDiagnosisField(String dateOfDiagnosis) {

        String[] value = dateOfDiagnosis.split("-");  // Split DOB in the format 01-01-1900
        tumoursPage.fillInDateOfDiagnosisOnAddOrTumourPage(value[0], value[1], value[2]);
    }

    @Then("the message will be displayed as {string} in {string} color for the date of diagnosis field")
    public void theMessageWillBeDisplayedAsInColorForTheDateOfDiagnosisField(String errorMessage, String fontColor) {
        patientSearchPage.checkTheErrorMessagesInDOB(errorMessage, fontColor);
    }
}
