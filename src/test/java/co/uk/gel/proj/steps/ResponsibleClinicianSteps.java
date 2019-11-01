package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ResponsibleClinicianSteps extends Pages {
    public ResponsibleClinicianSteps(SeleniumDriver driver) {
        super(driver);
    }

    @When("the user fills in all clinician form fields except the mandatory fields Last name , Department name and address")
    public void theUserFillsInAllClinicianFormFieldsExceptTheMandatoryFieldsLastNameDepartmentNameAndAddress() {
        responsibleClinicianPage.fillInClinicianFormFieldsExceptLastNameAndDepartmentAddress();
    }

    @And("the user see the {string} displayed to add Additional clinician details")
    public void theUserSeeTheDisplayedToAddAdditionalClinicianDetails(String hyperlinkText) {
        responsibleClinicianPage.verifyHyperlinkExists(hyperlinkText);
    }

    @Then("The Last name field should display an error message {string}")
    public void theLastNameFieldShouldDisplayAnErrorMessage(String errorMessage) {
        Assert.assertTrue(responsibleClinicianPage.verifyLastNameFieldIsMandatory(errorMessage));
    }

    @And("The mandatory field Last name should be highlighted with a {string} red mark")
    public void theMandatoryFieldLastNameShouldBeHighlightedWithARedMark(String hexColourString) {
        Assert.assertTrue(responsibleClinicianPage.verifyLastNameFieldIsHighlightedInRed(hexColourString));
    }

    @And("the user see the field Department name and address is marked as mandatory")
    public void theUserSeeTheFieldDepartmentNameAndAddressIsMarkedAsMandatory() {
        Assert.assertTrue(responsibleClinicianPage.verifyDepartmentalAddressIsDisplayedAsMandatoryField());
    }

    @When("the user fills in all clinician form fields except Department name and address")
    public void theUserFillsInAllClinicianFormFieldsExceptDepartmentNameAndAddress() {
        responsibleClinicianPage.fillInClinicianFormFieldsExceptDepartmentAddressField();
    }
}
