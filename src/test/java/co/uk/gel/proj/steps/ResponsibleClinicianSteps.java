package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
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
        boolean testResult = false;
        testResult = responsibleClinicianPage.fillInClinicianFormFields();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ResponsibleClinician");
            Assert.fail("Could not fill Clinical Form Fields.");
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ResponsibleClinician");
        }
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
        boolean testResult = responsibleClinicianPage.verifyHyperlinkExists(hyperlinkText);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_hyperLinkText.jpg");
            Assert.fail("Could not see the hyper link text: "+hyperlinkText);
        }
    }

    @Then("The Last name field should display an error message {string}")
    public void theLastNameFieldShouldDisplayAnErrorMessage(String errorMessage) {
        Assert.assertTrue(responsibleClinicianPage.verifyLastNameFieldIsMandatory(errorMessage));
    }

    @And("The mandatory field Last name should be highlighted with a {string} red mark")
    public void theMandatoryFieldLastNameShouldBeHighlightedWithARedMark(String hexColourString) {
        boolean testResult = responsibleClinicianPage.verifyLastNameFieldIsHighlightedInRed(hexColourString);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"hexColourString.jpg");
            Assert.fail("Could not see the hexColourString: "+hexColourString);
        }
    }

    @And("the user see the field Department name and address is marked as mandatory")
    public void theUserSeeTheFieldDepartmentNameAndAddressIsMarkedAsMandatory() {
        Assert.assertTrue(responsibleClinicianPage.verifyDepartmentalAddressIsDisplayedAsMandatoryField());
    }

    @When("the user fills in all clinician form fields except Department name and address")
    public void theUserFillsInAllClinicianFormFieldsExceptDepartmentNameAndAddress() {
        responsibleClinicianPage.fillInClinicianFormFieldsExceptDepartmentAddressField();
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

    @When("the user creates additional clinician {int} by filling up all form fields except the mandatory fields Last name")
    public void theUserCreatesAdditionalClinicianByFillingUpAllFormFieldsExceptTheMandatoryFieldsLastName(int clinicianCount) throws IOException {
        if(clinicianCount == 1) {
            responsibleClinicianPage.fillInAdditionalClinicianOneFormFieldsExceptLastNameField();
        }  else throw new IOException(" Invalid additional clinician number ; please define the additional clinician locators in page objects class");

    }

    @And("The mandatory field Last name should be highlighted with a {string} red mark in additional clinician section")
    public void theMandatoryFieldLastNameShouldBeHighlightedWithARedMarkInAdditionalClinicianSection(String hexColourString) {
        Assert.assertTrue(responsibleClinicianPage.verifyLastNameFieldInAdditionalClinicianOneIsHighlightedInRed(hexColourString));
    }

    @And("the user sees the label Department name and address marked as mandatory")
    public void theUserSeesTheLabelDepartmentNameAndAddressMarkedAsMandatory() {
        Assert.assertTrue(responsibleClinicianPage.verifyDepartmentNameAndAddressLabelIsShownAsMandatory());
    }

    @And("The user sees the text field First name and it is blank")
    public void theUserSeesTheTextFieldFirstNameAndItIsBlank() {
        Assert.assertTrue(responsibleClinicianPage.firstNameFieldDisplayed());
        Assert.assertTrue(responsibleClinicianPage.firstNameFieldIsEmpty());
    }

    @And("The user sees the text field Last name and it is blank")
    public void theUserSeesTheTextFieldLastNameAndItIsBlank() {
        Assert.assertTrue(responsibleClinicianPage.lastNameFieldDisplayed());
        Assert.assertTrue(responsibleClinicianPage.lastNameFieldIsEmpty());
    }

    @And("The user sees the text field Email address and it is blank")
    public void theUserSeesTheTextFieldEmailAddressAndItIsBlank() {
        Assert.assertTrue(responsibleClinicianPage.emailFieldDisplayed());
        Assert.assertTrue(responsibleClinicianPage.emailFieldIsEmpty());
    }

    @And("The user sees the text field Phone number and it is blank")
    public void theUserSeesTheTextFieldPhoneNumberAndItIsBlank() {
        Assert.assertTrue(responsibleClinicianPage.phoneNumberFieldDisplayed());
        Assert.assertTrue(responsibleClinicianPage.phoneNumberFieldIsEmpty());
    }

    @And("The user sees the text field Department name and address and it is blank")
    public void theUserSeesTheTextFieldDepartmentNameAndAddressAndItIsBlank() {
        Assert.assertTrue(responsibleClinicianPage.departmentNameAndAddressFieldDisplayed());
        Assert.assertTrue(responsibleClinicianPage.departmentNameAndAddressFieldIsEmpty());
    }

    @And("The user sees the text field Professional registration number and it is blank")
    public void theUserSeesTheTextFieldProfessionalRegistrationNumberAndItIsBlank() {
        Assert.assertTrue(responsibleClinicianPage.professionalRegistrationNumberFieldDisplayed());
        Assert.assertTrue(responsibleClinicianPage.professionalRegistrationNumberFieldIsEmpty());
    }

    @And("The user sees the text field First name in Additional Clinician {int} and it is blank")
    public void theUserSeesTheTextFieldFirstNameInAdditionalClinicianAndItIsBlank(int cliniciansCount) {
        Assert.assertTrue(responsibleClinicianPage.firstNameFieldIsEmptyForAdditionalClinicianOne());
    }

    @And("The user sees the text field Last name in Additional Clinician {int} and it is blank")
    public void theUserSeesTheTextFieldLastNameInAdditionalClinicianAndItIsBlank(int cliniciansCount) {
        Assert.assertTrue(responsibleClinicianPage.lastNameFieldIsEmptyForAdditionalClinicianOne());
    }

    @And("The user sees the text field Email address in Additional Clinician {int} and it is blank")
    public void theUserSeesTheTextFieldEmailAddressInAdditionalClinicianAndItIsBlank(int cliniciansCount) {
        Assert.assertTrue(responsibleClinicianPage.emailFieldIsEmptyForAdditionalClinicianOne());
    }

    @And("The user sees the text field Phone number in Additional Clinician {int} and it is blank")
    public void theUserSeesTheTextFieldPhoneNumberInAdditionalClinicianAndItIsBlank(int cliniciansCount) {
        Assert.assertTrue(responsibleClinicianPage.phoneNumberFieldIsEmptyForAdditionalClinicianOne());
    }

    @And("The user sees the text field Department name and address in Additional Clinician {int} and it is blank")
    public void theUserSeesTheTextFieldDepartmentNameAndAddressInAdditionalClinicianAndItIsBlank(int cliniciansCount) {
        Assert.assertTrue(responsibleClinicianPage.departmentNameAndAddressFieldIsEmptyForAdditionalClinicianOne());
    }

    @And("The user sees the text field Professional registration number in Additional Clinician {int} and it is blank")
    public void theUserSeesTheTextFieldProfessionalRegistrationNumberInAdditionalClinicianAndItIsBlank(int cliniciansCount) {
        Assert.assertTrue(responsibleClinicianPage.professionalRegistrationNumberFieldIsEmptyForAdditionalClinicianOne());
    }

    @And("the text field First name, Last name, Email address, Professional registration number, Phone number and Department name and address should not enabled with auto-fill feature")
    public void theTextFieldFirstNameLastNameEmailAddressProfessionalRegistrationNumberPhoneNumberAndDepartmentNameAndAddressShouldNotEnabledWithAutoFillFeature() {
        Assert.assertTrue(responsibleClinicianPage.verifyResponsibleClinicianFieldsAreDisabledWithAutoCompleteFeature());
    }

    @When("the user fills the responsible clinician page with {string}")
    public void theUserFillsTheResponsibleClinicianPageWith(String clinicalInfo) {
        boolean testResult = false;
        testResult = responsibleClinicianPage.fillResponsibleClinicianDetails(clinicalInfo);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ResponsibleClinician.jpg");
            Assert.fail("Responsible Clinician Details could not enter.");
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ResponsibleClinician");
        }
   }

    @And("the user deletes the data in the Clinician Phone Number field")
    public void theUserDeletesTheDataInTheClinicianPhoneNumberField() {
        Action.clearField(responsibleClinicianPage.clinicianPhoneNumberField);
    }

    @When("the user attempts to fill in Clinician Phone Number field {string} with data that exceed the maximum data allowed {int}")
    public void theUserAttemptsToFillInClinicianPhoneNumberFieldWithDataThatExceedTheMaximumDataAllowed(String phoneNumber, int maximumCharactersAllowed) {
        if (phoneNumber.length() > maximumCharactersAllowed) {
            responsibleClinicianPage.clinicianPhoneNumberField.sendKeys(phoneNumber);
            Assert.assertTrue(true);
        }
    }

    @And("user sees the following help text under email address field")
    public void userSeesTheFollowingHelpTextUnderEmailAddressField(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        String actualHelpText = responsibleClinicianPage.getEmailSectionHelpText();
        Debugger.println("Expected Help Text:" + list.get(0).get("helpMessageHeader"));
        Assert.assertEquals(list.get(0).get("helpMessageHeader"), actualHelpText);
    }

    @When("the user fills in the Department name and address")
    public void theUserFillsInTheDepartmentNameAndAddress() {
        responsibleClinicianPage.fillInDepartmentDetailsField();
    }

    @And("the user enters more than {int} email address {string} in the field")
    public void theUserEntersMoreThanEmailAddressInTheField(int numberOfIDs, String mailIDs) {
        responsibleClinicianPage.enterMultipleEmailIDs(mailIDs);
    }

    @And("the user deletes the data in the Email address field")
    public void theUserDeletesTheDataInTheEmailAddressField() {
        //Actions.clearField(responsibleClinicianPage.clinicianEmailField);
        responsibleClinicianPage.clinicianEmailField.clear();
    }


    @And("the user enters {int} valid email address {string} in the field separated by {string}")
    public void theUserEntersValidEmailAddressInTheFieldSeparatedBy(int numberOfIDs, String mailIDs, String delimiter) {
        responsibleClinicianPage.enterMultipleEmailIDs(mailIDs);
    }

    @Then("the user should not sees an error message on the page")
    public void theUserShouldNotSeesAnErrorMessageOnThePage() {
        Assert.assertTrue(responsibleClinicianPage.verifyNoEmailWarningMessage());
    }
}