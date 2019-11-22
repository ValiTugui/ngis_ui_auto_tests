package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ResponsibleClinicianSteps extends Pages {
    public ResponsibleClinicianSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user fills in all the clinician form fields")
    public void theUserFillsInAllTheClinicianFormFields() {
        responsibleClinicianPage.fillInClinicianFormFields();
    }
    @When("the user fills in {string} in clinician form fields")
    public void the_user_fills_in_in_clinician_form_fields(String emailValue) {
        responsibleClinicianPage.enterEmail(emailValue);
    }

    @Then("the user sees an {string} message on the page")
    public void the_user_sees_an_message_on_the_page(String errorMessage) {
        Assert.assertTrue(responsibleClinicianPage.verifyInvalidEmailWarningMessage(errorMessage));
    }

    @And("The user should be restricted to enter more than {string} digits in the Phone number field.")
    public void theUserShouldBeRestrictedToEnterMoreThanDigitsInThePhoneNumberField(String maxNumberOfDigits) {
        Assert.assertTrue(responsibleClinicianPage.verifyTotalNumberOfDigitsInPhoneNumberField(Integer.parseInt(maxNumberOfDigits)));
    }

    @And("the user fills in invalid phone number value by providing {string} digits in clinician form fields")
    public void theUserFillsInInvalidPhoneNumberValueByProvidingDigitsInClinicianFormFields(String invalidNumberOfDigits) {
        Faker fakeData = new Faker();
        responsibleClinicianPage.enterPhoneNumber(fakeData.number().digits(Integer.parseInt(invalidNumberOfDigits)));
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

    @When("the user clicks the Additional Clinician link")
    public void theUserClicksTheAdditionalClinicianLink() {
        responsibleClinicianPage.clickAddAnotherLink();
    }

    @And("the user fills in all the fields for the additional clinician")
    public void theUserFillsInAllTheFieldsForTheAdditionalClinician() {
        responsibleClinicianPage.fillInAdditionalClinicianFormFields();
    }

    @And("both clinicians details are persisted when returning to the {string} stage")
    public void bothCliniciansDetailsArePersistedWhenReturningToTheStage(String stage) {
        referralPage.navigateToStage(stage);
        referralPage.stageIsSelected(stage);
        responsibleClinicianPage.clinicianDetailsArePersistedAtLoad();
        responsibleClinicianPage.additionalClinicianDetailsArePersistedAtLoad();
    }
}
