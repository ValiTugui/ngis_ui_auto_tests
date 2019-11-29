package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @And("the user sees the title text as {string}")
    public void theUserSeesTheTitleTextAs(String expectedPageTitle) {
        Assert.assertEquals(expectedPageTitle, referralPage.getTheCurrentPageTitle());
    }

    @When("the user fills in all clinician form fields except Last name")
    public void theUserFillsInAllClinicianFormFieldsExceptLastName() {
        responsibleClinicianPage.fillInClinicianFormFieldsExceptLastNameField();
    }

    @And("the user sees First name, Last name, Phone number and email, Department name and address, Professional registration number")
    public void theUserSeesFirstNameLastNamePhoneNumberAndEmailDepartmentNameAndAddressProfessionalRegistrationNumber() {
        responsibleClinicianPage.confirmTheExpectedFieldsToBeSeemInClinicianForm();
    }

    @When("the user clicks the Additional Clinician link")
    public void theUserClicksTheAdditionalClinicianLink() {
        responsibleClinicianPage.clickAddAnotherLink();
    }

    @And("both clinicians details are persisted when returning to the {string} stage")
    public void bothCliniciansDetailsArePersistedWhenReturningToTheStage(String stage) {
        referralPage.navigateToStage(stage);
        referralPage.stageIsSelected(stage);
        Assert.assertTrue(responsibleClinicianPage.clinicianDetailsArePersistedAtLoad());
        Assert.assertTrue(responsibleClinicianPage.additionalClinicianOneDetailsArePersistedAtLoad());
    }

    @And("the page shows the following help messages")
    public void thePageShowsTheFollowingHelpMessages(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        String actualHelpText = responsibleClinicianPage.getClinicianHelpText();
        Debugger.println("Expected Help Text:" + list.get(0).get("helpMessageHeader"));
        Assert.assertEquals(list.get(0).get("helpMessageHeader"), actualHelpText);
    }

    @And("the user sees the section label as {string}")
    public void theUserSeesTheSectionLabelAs(String expectedSectionTitle) {
        Assert.assertEquals(expectedSectionTitle, responsibleClinicianPage.getSectionTitle());
    }

    @And("The user sees the text field First name")
    public void theUserSeesTheTextFieldFirstName() {
        Assert.assertTrue(responsibleClinicianPage.firstNameFieldDisplayed());
    }

    @And("The user sees the text field Last name")
    public void theUserSeesTheTextFieldLastName() {
        Assert.assertTrue(responsibleClinicianPage.lastNameFieldDisplayed());
    }

    @And("The user sees the text field Email address")
    public void theUserSeesTheTextFieldEmailAddress() {
        Assert.assertTrue(responsibleClinicianPage.emailFieldDisplayed());
    }

    @And("The user sees the text field Phone number")
    public void theUserSeesTheTextFieldPhoneNumber() {
        Assert.assertTrue(responsibleClinicianPage.phoneNumberFieldDisplayed());
    }

    @And("The user sees the text field Department name and address")
    public void theUserSeesTheTextFieldDepartmentNameAndAddress() {
        Assert.assertTrue(responsibleClinicianPage.departmentNameAndAddressFieldDisplayed());
    }

    @And("The user sees the text field Professional registration number")
    public void theUserSeesTheTextFieldProfessionalRegistrationNumber() {
        Assert.assertTrue(responsibleClinicianPage.professionalRegistrationNumberFieldDisplayed());
    }

    @And("the user sees the another section name as {string}")
    public void theUserSeesTheAnotherSectionNameAs(String expectedSectionTitle) {
        Assert.assertEquals(expectedSectionTitle, responsibleClinicianPage.getContactSectionTitle());
    }

    @And("the user sees the Save and Continue button")
    public void theUserSeesTheSaveAndContinueButton() {
        Assert.assertTrue(referralPage.saveAndContinueButtonIsDisplayed());
    }

    @And("the page shows the following help messages under Additional contacts")
    public void thePageShowsTheFollowingHelpMessagesUnderAdditionalContacts(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        String actualHelpText = responsibleClinicianPage.getContactSectionHelpText();
        Debugger.println("Expected Help Text:" + list.get(0).get("helpMessageHeader"));
        Assert.assertEquals(list.get(0).get("helpMessageHeader"), actualHelpText);
    }

    @And("the user creates additional clinician {int} by filling up all form fields")
    public void theUserCreatesAdditionalClinicianByFillingUpAllFormFields(int clinicianCount) throws IOException {
        if(clinicianCount == 1) {
            responsibleClinicianPage.fillInAdditionalClinicianOneFormFields();
        } else if (clinicianCount == 2){
            responsibleClinicianPage.fillInAdditionalClinicianTwoFormFields();
        } else throw new IOException(" Invalid additional clinician number ; please define the additional clinician locators in page objects class");

    }

    @And("the user see the {string} displayed to remove the Additional clinician details")
    public void theUserSeeTheDisplayedToRemoveTheAdditionalClinicianDetails(String hyperlinkText) {
        Assert.assertTrue(responsibleClinicianPage.verifyRemoveHyperlinkExists(hyperlinkText));
    }

    @And("three clinicians details are persisted when returning to the {string} stage")
    public void threeCliniciansDetailsArePersistedWhenReturningToTheStage(String expectedStage) {
        referralPage.navigateToStage(expectedStage);
        referralPage.stageIsSelected(expectedStage);
        // verify Main Clinician info
        Assert.assertTrue(responsibleClinicianPage.clinicianDetailsArePersistedAtLoad());
        // verify additional clinician 1 and additional clinician 2 info
        Assert.assertTrue(responsibleClinicianPage.additionalClinicianOneDetailsArePersistedAtLoad());
        Assert.assertTrue(responsibleClinicianPage.additionalClinicianTwoDetailsArePersistedAtLoad());
    }
    @And("the user fills in all the fields for the additional clinician")
    public void theUserFillsInAllTheFieldsForTheAdditionalClinician() {
        //responsibleClinicianPage.fillInAdditionalClinicianFormFields();
    }
}
