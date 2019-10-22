package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

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


    @And("the new patient page displays expected input-fields and a {string} submit button")
    public void theNewPatientPageDisplaysExpectedInputFieldsAndASubmitButton(String labelOnSubmitButton) {
        Assert.assertTrue("All expected fields are not displayed on new patient page", newPatientPage.verifyTheElementsOnAddNewPatientPage());
        Debugger.println("Actual referral submit button: " + labelOnSubmitButton + " : " +  "Expected referral submit button " + newPatientPage.savePatientDetailsToNGISButton.getText());
        Assert.assertEquals(labelOnSubmitButton, newPatientPage.savePatientDetailsToNGISButton.getText());
    }
}


