package co.uk.gel.proj.pages;


import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ResponsibleClinicianPage {

    WebDriver driver;
    Faker fake = new Faker();


    String key1 = "mainClinician";
    String key2 = "additionalClinician1";
    String key3 = "additionalClinician2";
    public HashMap<String, ArrayList<String>> cliniciansMap = new HashMap<>();
    SeleniumLib seleniumLib;

    public ResponsibleClinicianPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1//following::p")
    public WebElement clinicianDetails;

    @FindBy(css = "div[class*='clinician-details-form__heading']")
    public WebElement clinicianFormHeader;

    @FindBy(css = "div[class*='clinician-details-form__info']")
    public WebElement clinicianFormInfo;

    @FindBy(css = "button[class*='clinician-details-form__add']")
    public WebElement addAnotherClinicianButton;

    @FindBy(css = "button[class*='clinician-details-form__remove']")
    public List<WebElement> removeClinicianButton;

    @FindBy(css = "p[class*='hint__text']")
    public List<WebElement> hintTexts;

    @FindBy(css = "label[for*='responsibleClinician.forename']")
    public WebElement clinicianFirstNameLabel;

    @FindBy(css = "label[for*='responsibleClinician.surname']")
    public WebElement clinicianLastNameLabel;

    @FindBy(css = "label[for*='responsibleClinician.phoneNumber']")
    public WebElement clinicianPhoneNumberLabel;

    @FindBy(css = "label[for*='responsibleClinician.dualEmails']")
    public WebElement clinicianEmailLabel;

    @FindBy(css = "label[for*='responsibleClinician.departmentalAddress']")
    public WebElement clinicianDepartmentAddressLabel;

    @FindBy(xpath = "//label[contains(text(),'Department name and address')]/span")
    public WebElement clinicianDepartmentAddressLabelWithAsterisk;

    @FindBy(css = "label[for*='responsibleClinician.professionalRegistrationNumber']")
    public WebElement clinicianProfesionalRegistrationNumberLabel;

    @FindBy(css = "div[class*='clinician-details-form__contact__heading']")
    public WebElement clinicianContactSectionLabel;

    @FindBy(css = "input[id*='responsibleClinician.forename']")
    public WebElement clinicianFirstNameField;

    @FindBy(css = "input[id*='responsibleClinician.surname']")
    public WebElement clinicianLastNameField;

    @FindBy(css = "input[id*='responsibleClinician.phoneNumber']")
    public WebElement clinicianPhoneNumberField;

    @FindBy(css = "input[id*='responsibleClinician.dualEmails']")
    public WebElement clinicianEmailField;

    @FindBy(css = "textarea[id*='responsibleClinician.departmentalAddress']")
    public WebElement clinicianDepartmentAddressField;

    @FindBy(css = "textarea[id*='responsibleClinician.professionalRegistrationNumber']")
    public WebElement clinicianProfesionalRegistrationNumberField;

    @FindBy(css = "input[id*='additionalClinicians[0].forename']")
    public WebElement additionalClinician1FirstNameField;

    @FindBy(css = "input[id*='additionalClinicians[0].surname']")
    public WebElement additionalClinician1LastNameField;

    @FindBy(css = "input[id*='additionalClinicians[0].phoneNumber']")
    public WebElement additionalClinician1PhoneNumberField;

    @FindBy(css = "input[id*='additionalClinicians[0].dualEmails']")
    public WebElement additionalClinician1EmailField;

    @FindBy(css = "textarea[id*='additionalClinicians[0].departmentalAddress']")
    public WebElement additionalClinician1DepartmentAddressField;

    @FindBy(css = "textarea[id*='additionalClinicians[0].professionalRegistrationNumber']")
    public WebElement additionalClinician1ProfessionalRegistrationNumberField;

    @FindBy(css = "input[id*='additionalClinicians[1].forename']")
    public WebElement additionalClinician2FirstNameField;

    @FindBy(css = "input[id*='additionalClinicians[1].surname']")
    public WebElement additionalClinician2LastNameField;

    @FindBy(css = "input[id*='additionalClinicians[1].phoneNumber']")
    public WebElement additionalClinician2PhoneNumberField;

    @FindBy(css = "input[id*='additionalClinicians[1].email']")
    public WebElement additionalClinician2EmailField;

    @FindBy(css = "textarea[id*='additionalClinicians[1].departmentalAddress']")
    public WebElement additionalClinician2DepartmentAddressField;

    @FindBy(css = "textarea[id*='additionalClinicians[1].professionalRegistrationNumber']")
    public WebElement additionalClinician2ProfessionalRegistrationNumberField;

    @FindBy(css = "div[class*='error-message__text']")
    public List<WebElement> clinicianErrorMessages;

    @FindBy(css = "label[for*='additionalClinicians[0].surname']")
    public WebElement additionalClinician1LastNameLabel;

    String mandatoryLabelAttribute = "label__required-icon";
    String autoCompleteAttributeOff = "autoComplete_off";

    public boolean fillInClinicianFormFields() {
        try {
            String firstName = RandomDataCreator.getRandomFirstName();
            String lastName = RandomDataCreator.getRandomLastName();
            String phoneNumber = RandomDataCreator.getRandomPhoneNumber();
            String email = RandomDataCreator.getRandomEmailAddress();
            String departmentAddress = RandomDataCreator.getRandomAddress();
            String professionalRegistrationNumber = RandomDataCreator.getRandomProfessionalRegistrationNumber();

            Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
            Action.clearField(clinicianFirstNameField);
            Action.fillInValue(clinicianFirstNameField, firstName);
            Action.clearField(clinicianLastNameField);
            Action.fillInValue(clinicianLastNameField, lastName);
            Action.clearField(clinicianPhoneNumberField);
            Action.fillInValue(clinicianPhoneNumberField, phoneNumber);
            Action.clearField(clinicianEmailField);
            Action.fillInValue(clinicianEmailField, email);
            Action.clearField(clinicianDepartmentAddressField);
            Action.fillInValue(clinicianDepartmentAddressField, departmentAddress);
            Action.clearField(clinicianProfesionalRegistrationNumberField);
            Action.fillInValue(clinicianProfesionalRegistrationNumberField, professionalRegistrationNumber);

            ArrayList clinicianDetails = new ArrayList();
            clinicianDetails.add(0, firstName);
            clinicianDetails.add(1, lastName);
            clinicianDetails.add(2, phoneNumber);
            clinicianDetails.add(3, email);
            clinicianDetails.add(4, departmentAddress);
            clinicianDetails.add(5, professionalRegistrationNumber);

            storeClinicianDataForVerification(key1, clinicianDetails);
            PatientDetailsPage.newPatient.setResponsibleClinicianName(firstName + " " + lastName);
            PatientDetailsPage.newPatient.setResponsibleClinicianEmail(email);
            PatientDetailsPage.newPatient.setResponsibleClinicianContactNumber(phoneNumber);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in filling fillInClinicianFormFields:" + exp);
            return false;
        }

    }

    public void enterEmail(String emailValue) {
        Wait.forElementToBeDisplayed(driver, clinicianEmailField);
        clinicianEmailField.sendKeys(emailValue);
    }

    public boolean verifyInvalidEmailWarningMessage(String expectedErrorMessage) {
        return clinicianErrorMessages.get(0).getText().contains(expectedErrorMessage);
    }

    public void enterPhoneNumber(String phoneNumberValue) {
        clinicianPhoneNumberField.sendKeys(phoneNumberValue);
    }

    public boolean verifyTotalNumberOfDigitsInPhoneNumberField(int maxNumberOfAllowedDigits) {
        Wait.forElementToBeDisplayed(driver, clinicianPhoneNumberField);
        int actualNumberOfDigits = clinicianPhoneNumberField.getText().length();
        if (actualNumberOfDigits <= maxNumberOfAllowedDigits) {
            return true;
        } else {
            return false;
        }
    }

    @FindBy(xpath = "//label[@for='responsibleClinician.departmentalAddress']//span[@class='label__required-icon']")
    public WebElement clinicianDepartmentalAddressLabelRequired;

    public void fillInClinicianFormFieldsExceptLastNameAndDepartmentAddress() {
        Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
        Action.clearField(clinicianFirstNameField);
        Action.fillInValue(clinicianFirstNameField, fake.name().firstName());
        Action.clearField(clinicianPhoneNumberField);
        Action.fillInValue(clinicianPhoneNumberField, fake.phoneNumber().cellPhone());
        Action.clearField(clinicianEmailField);
        Action.fillInValue(clinicianEmailField, fake.internet().emailAddress());
        Action.clearField(clinicianProfesionalRegistrationNumberField);
        Action.fillInValue(clinicianProfesionalRegistrationNumberField, fake.number().digits(12));
    }

    public void fillInClinicianFormFieldsExceptDepartmentAddressField() {
        Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
        Action.clearField(clinicianFirstNameField);
        Action.fillInValue(clinicianFirstNameField, fake.name().firstName());
        Action.clearField(clinicianLastNameField);
        Action.fillInValue(clinicianLastNameField, fake.name().lastName());
        Action.clearField(clinicianPhoneNumberField);
        Action.fillInValue(clinicianPhoneNumberField, fake.phoneNumber().cellPhone());
        Action.clearField(clinicianEmailField);
        Action.fillInValue(clinicianEmailField, fake.internet().emailAddress());
        Action.clearField(clinicianProfesionalRegistrationNumberField);
        Action.fillInValue(clinicianProfesionalRegistrationNumberField, fake.number().digits(12));
    }


    public boolean verifyHyperlinkExists(String expectedHyperlinkText) {
        Wait.forElementToBeDisplayed(driver, addAnotherClinicianButton);
        if (addAnotherClinicianButton.isDisplayed() && addAnotherClinicianButton.getText().contains(expectedHyperlinkText)) {
            return true;
        }
        return false;
    }

    public boolean verifyRemoveHyperlinkExists(String expectedHyperlinkText) {
        Wait.forElementToBeDisplayed(driver, removeClinicianButton.get(0));
        return removeClinicianButton.get(0).isDisplayed() && Action.getText(removeClinicianButton.get(0)).contains(expectedHyperlinkText);
    }

    public boolean verifyLastNameFieldIsMandatory(String expectedErrorMessage) {
        int latestLastNameError = clinicianErrorMessages.size() - 1;
        return clinicianErrorMessages.get(latestLastNameError).getText().contains(expectedErrorMessage);
    }

    public boolean verifyLastNameFieldIsHighlightedInRed(String expectedColourUponError) {
        Wait.forElementToBeDisplayed(driver, clinicianLastNameLabel);
        Wait.forElementToBeDisplayed(driver, clinicianLastNameField);
        String lastNameLabelActualColorUponError = clinicianLastNameLabel.getCssValue("color").toString();
        String lastNameFieldErrorMessageActualColorUponError = clinicianErrorMessages.get(0).getCssValue("color").toString();
        String redColour = StylesUtils.convertFontColourStringToCSSProperty(expectedColourUponError);
        if (lastNameLabelActualColorUponError.equals(redColour) && lastNameFieldErrorMessageActualColorUponError.equals(redColour)) {
            return true;
        }
        return false;
    }

    public boolean verifyLastNameFieldInAdditionalClinicianOneIsHighlightedInRed(String expectedColourUponError) {
        Wait.forElementToBeDisplayed(driver, additionalClinician1LastNameLabel);
        Wait.forElementToBeDisplayed(driver, additionalClinician1LastNameField);
        String lastNameLabelActualColorUponError = additionalClinician1LastNameLabel.getCssValue("color").toString();
        String lastNameFieldErrorMessageActualColorUponError = clinicianErrorMessages.get(0).getCssValue("color").toString();
        String redColour = StylesUtils.convertFontColourStringToCSSProperty(expectedColourUponError);
        if (lastNameLabelActualColorUponError.equals(redColour) && lastNameFieldErrorMessageActualColorUponError.equals(redColour)) {
            return true;
        } else return false;
    }

    public boolean verifyDepartmentalAddressIsDisplayedAsMandatoryField() {
        Wait.forElementToBeDisplayed(driver, clinicianDepartmentalAddressLabelRequired);
        // verify the asterisk (*) symbol is shown next to the Departmental Address label on the Responsible Clinician page
        Wait.forElementToBeDisplayed(driver, clinicianDepartmentalAddressLabelRequired);
        return clinicianDepartmentalAddressLabelRequired.getText().contains("*");
    }

    public void fillInClinicianFormFieldsExceptLastNameField() {
        Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
        Action.clearField(clinicianFirstNameField);
        Action.fillInValue(clinicianFirstNameField, fake.name().firstName());
        Action.clearField(clinicianPhoneNumberField);
        Action.fillInValue(clinicianPhoneNumberField, fake.phoneNumber().cellPhone());
        Action.clearField(clinicianEmailField);
        Action.fillInValue(clinicianEmailField, fake.internet().emailAddress());
        Action.clearField(clinicianDepartmentAddressField);
        Action.fillInValue(clinicianDepartmentAddressField, fake.address().streetAddress());
        Action.clearField(clinicianProfesionalRegistrationNumberField);
        Action.fillInValue(clinicianProfesionalRegistrationNumberField, fake.number().digits(12));
    }

    public void fillInDepartmentDetailsField() {
        Wait.forElementToBeDisplayed(driver, clinicianDepartmentAddressField);
        Action.clearField(clinicianDepartmentAddressField);
        Action.fillInValue(clinicianDepartmentAddressField, fake.address().streetAddress());
    }

    public void confirmTheExpectedFieldsToBeSeemInClinicianForm() {
        Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
        clinicianFirstNameField.isDisplayed();
        clinicianLastNameField.isDisplayed();
        clinicianPhoneNumberField.isDisplayed();
        clinicianEmailField.isDisplayed();
        clinicianDepartmentAddressField.isDisplayed();
        clinicianProfesionalRegistrationNumberField.isDisplayed();
    }

    public void clickAddAnotherLink() {
        Action.clickElement(driver, addAnotherClinicianButton);
    }

    public void fillInAdditionalClinicianOneFormFields() {
        String firstName = RandomDataCreator.getRandomFirstName();
        String lastName = RandomDataCreator.getRandomLastName();
        String phoneNumber = RandomDataCreator.getRandomPhoneNumber();
        String email = RandomDataCreator.getRandomEmailAddress();
        String departmentAddress = RandomDataCreator.getRandomAddress();
        String professionalRegistrationNumber = RandomDataCreator.getRandomProfessionalRegistrationNumber();

        Wait.forElementToBeDisplayed(driver, additionalClinician1FirstNameField);
        Action.fillInValue(additionalClinician1FirstNameField, firstName);
        Action.fillInValue(additionalClinician1LastNameField, lastName);
        Action.fillInValue(additionalClinician1PhoneNumberField, phoneNumber);
        Action.fillInValue(additionalClinician1EmailField, email);
        Action.fillInValue(additionalClinician1DepartmentAddressField, departmentAddress);
        Action.fillInValue(additionalClinician1ProfessionalRegistrationNumberField, professionalRegistrationNumber);

        ArrayList<String> clinicianDetails = new ArrayList<>();
        clinicianDetails.add(0, firstName);
        clinicianDetails.add(1, lastName);
        clinicianDetails.add(2, phoneNumber);
        clinicianDetails.add(3, email);
        clinicianDetails.add(4, departmentAddress);
        clinicianDetails.add(5, professionalRegistrationNumber);

        storeClinicianDataForVerification(key2, clinicianDetails);

    }

    public void fillInAdditionalClinicianTwoFormFields() {
        String firstName = RandomDataCreator.getRandomFirstName();
        String lastName = RandomDataCreator.getRandomLastName();
        String phoneNumber = RandomDataCreator.getRandomPhoneNumber();
        String email = RandomDataCreator.getRandomEmailAddress();
        String departmentAddress = RandomDataCreator.getRandomAddress();
        String professionalRegistrationNumber = RandomDataCreator.getRandomProfessionalRegistrationNumber();

        Wait.forElementToBeDisplayed(driver, additionalClinician2FirstNameField);
        Action.fillInValue(additionalClinician2FirstNameField, firstName);
        Action.fillInValue(additionalClinician2LastNameField, lastName);
        Action.fillInValue(additionalClinician2PhoneNumberField, phoneNumber);
        Action.fillInValue(additionalClinician2EmailField, email);
        Action.fillInValue(additionalClinician2DepartmentAddressField, departmentAddress);
        Action.fillInValue(additionalClinician2ProfessionalRegistrationNumberField, professionalRegistrationNumber);

        ArrayList<String> clinicianDetails = new ArrayList<>();
        clinicianDetails.add(0, firstName);
        clinicianDetails.add(1, lastName);
        clinicianDetails.add(2, phoneNumber);
        clinicianDetails.add(3, email);
        clinicianDetails.add(4, departmentAddress);
        clinicianDetails.add(5, professionalRegistrationNumber);

        storeClinicianDataForVerification(key3, clinicianDetails);

    }

    public void fillInAdditionalClinicianOneFormFieldsExceptLastNameField() {
        String firstName = RandomDataCreator.getRandomFirstName();
        String phoneNumber = RandomDataCreator.getRandomPhoneNumber();
        String email = RandomDataCreator.getRandomEmailAddress();
        String departmentAddress = RandomDataCreator.getRandomAddress();
        String professionalRegistrationNumber = RandomDataCreator.getRandomProfessionalRegistrationNumber();

        Wait.forElementToBeDisplayed(driver, additionalClinician1FirstNameField);
        Action.fillInValue(additionalClinician1FirstNameField, firstName);
        Action.fillInValue(additionalClinician1PhoneNumberField, phoneNumber);
        Action.fillInValue(additionalClinician1EmailField, email);
        Action.fillInValue(additionalClinician1DepartmentAddressField, departmentAddress);
        Action.fillInValue(additionalClinician1ProfessionalRegistrationNumberField, professionalRegistrationNumber);
    }

    public boolean clinicianDetailsArePersistedAtLoad() {
        Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
        boolean doesFirstNameMatch = Action.getValue(clinicianFirstNameField).matches(cliniciansMap.get(key1).get(0));
        boolean doesLastNameMatch = Action.getValue(clinicianLastNameField).matches(cliniciansMap.get(key1).get(1));
        boolean doesPhoneNumberMatch = Action.getValue(clinicianPhoneNumberField).matches(cliniciansMap.get(key1).get(2));
        boolean doesEmailMatch = Action.getValue(clinicianEmailField).matches(cliniciansMap.get(key1).get(3));
        boolean doesDepartmentAddressMatch = Action.getValue(clinicianDepartmentAddressField).matches(cliniciansMap.get(key1).get(4));
        boolean doesProfessionalRegistrationNumberMatch = Action.getValue(clinicianProfesionalRegistrationNumberField).matches(cliniciansMap.get(key1).get(5));

        return doesFirstNameMatch && doesLastNameMatch && doesPhoneNumberMatch && doesEmailMatch && doesDepartmentAddressMatch && doesProfessionalRegistrationNumberMatch;
    }

    public boolean additionalClinicianOneDetailsArePersistedAtLoad() {
        Wait.forElementToBeDisplayed(driver, additionalClinician1FirstNameField);
        boolean doesFirstNameMatch = Action.getValue(additionalClinician1FirstNameField).matches(cliniciansMap.get(key2).get(0));
        boolean doesLastNameMatch = Action.getValue(additionalClinician1LastNameField).matches(cliniciansMap.get(key2).get(1));
        boolean doesPhoneNumberMatch = Action.getValue(additionalClinician1PhoneNumberField).matches(cliniciansMap.get(key2).get(2));
        boolean doesEmailMatch = Action.getValue(additionalClinician1EmailField).matches(cliniciansMap.get(key2).get(3));
        boolean doesDepartmentAddressMatch = Action.getValue(additionalClinician1DepartmentAddressField).matches(cliniciansMap.get(key2).get(4));
        boolean doesProfessionalRegistrationNumberMatch = Action.getValue(additionalClinician1ProfessionalRegistrationNumberField).matches(cliniciansMap.get(key2).get(5));

        return doesFirstNameMatch && doesLastNameMatch && doesPhoneNumberMatch && doesEmailMatch && doesDepartmentAddressMatch && doesProfessionalRegistrationNumberMatch;
    }

    public boolean additionalClinicianTwoDetailsArePersistedAtLoad() {
        Wait.forElementToBeDisplayed(driver, additionalClinician2FirstNameField);
        boolean doesFirstNameMatch = Action.getValue(additionalClinician2FirstNameField).matches(cliniciansMap.get(key3).get(0));
        boolean doesLastNameMatch = Action.getValue(additionalClinician2LastNameField).matches(cliniciansMap.get(key3).get(1));
        boolean doesPhoneNumberMatch = Action.getValue(additionalClinician2PhoneNumberField).matches(cliniciansMap.get(key3).get(2));
        boolean doesEmailMatch = Action.getValue(additionalClinician2EmailField).matches(cliniciansMap.get(key3).get(3));
        boolean doesDepartmentAddressMatch = Action.getValue(additionalClinician2DepartmentAddressField).matches(cliniciansMap.get(key3).get(4));
        boolean doesProfessionalRegistrationNumberMatch = Action.getValue(additionalClinician2ProfessionalRegistrationNumberField).matches(cliniciansMap.get(key3).get(5));

        return doesFirstNameMatch && doesLastNameMatch && doesPhoneNumberMatch && doesEmailMatch && doesDepartmentAddressMatch && doesProfessionalRegistrationNumberMatch;
    }

    public void storeClinicianDataForVerification(String clinicianIdentifier, ArrayList<String> clinicianInfo) {
        cliniciansMap.put(clinicianIdentifier, clinicianInfo);
    }

    public String getClinicianHelpText() {
        Wait.forElementToBeDisplayed(driver, clinicianDetails);
        return Action.getText(clinicianDetails);
    }

    public String getSectionTitle() {
        Wait.forElementToBeDisplayed(driver, clinicianFormHeader);
        return Action.getText(clinicianFormHeader);
    }

    public boolean firstNameFieldDisplayed() {
        Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
        return clinicianFirstNameField.isEnabled() && clinicianFirstNameLabel.isDisplayed();
    }

    public boolean firstNameFieldIsEmpty() {
        return Action.getText(clinicianFirstNameField).isEmpty();
    }

    public boolean lastNameFieldDisplayed() {
        Wait.forElementToBeDisplayed(driver, clinicianLastNameField);
        return clinicianLastNameField.isEnabled() && clinicianLastNameLabel.isDisplayed();
    }

    public boolean lastNameFieldIsEmpty() {
        return Action.getText(clinicianLastNameField).isEmpty();
    }

    public boolean emailFieldDisplayed() {
        Wait.forElementToBeDisplayed(driver, clinicianEmailField);
        return clinicianEmailField.isEnabled() && clinicianEmailLabel.isDisplayed();
    }

    public boolean emailFieldIsEmpty() {
        return Action.getText(clinicianEmailField).isEmpty();
    }

    public boolean phoneNumberFieldDisplayed() {
        Wait.forElementToBeDisplayed(driver, clinicianPhoneNumberField);
        return clinicianPhoneNumberField.isEnabled() && clinicianPhoneNumberLabel.isDisplayed();
    }

    public boolean phoneNumberFieldIsEmpty() {
        return Action.getText(clinicianPhoneNumberField).isEmpty();
    }

    public boolean departmentNameAndAddressFieldDisplayed() {
        Wait.forElementToBeDisplayed(driver, clinicianDepartmentAddressField);
        return clinicianDepartmentAddressField.isEnabled() && clinicianDepartmentAddressLabel.isDisplayed();
    }

    public boolean departmentNameAndAddressFieldIsEmpty() {
        return Action.getText(clinicianDepartmentAddressField).isEmpty();
    }

    public boolean professionalRegistrationNumberFieldDisplayed() {
        Wait.forElementToBeDisplayed(driver, clinicianProfesionalRegistrationNumberField);
        return clinicianProfesionalRegistrationNumberField.isEnabled() && clinicianProfesionalRegistrationNumberLabel.isDisplayed();
    }

    public boolean professionalRegistrationNumberFieldIsEmpty() {
        return Action.getText(clinicianProfesionalRegistrationNumberField).isEmpty();
    }

    public String getContactSectionTitle() {
        Wait.forElementToBeDisplayed(driver, clinicianContactSectionLabel);
        return Action.getText(clinicianContactSectionLabel);
    }

    public String getContactSectionHelpText() {
        Wait.forElementToBeDisplayed(driver, clinicianFormInfo);
        return Action.getText(clinicianFormInfo);
    }

    public String getEmailSectionHelpText() {
        Wait.forElementToBeDisplayed(driver, hintTexts.get(0));
        return Action.getText(hintTexts.get(0));
    }

    public boolean verifyDepartmentNameAndAddressLabelIsShownAsMandatory() {
        Wait.forElementToBeDisplayed(driver, clinicianDepartmentAddressLabel);
        Wait.isElementDisplayed(driver, clinicianDepartmentAddressLabelWithAsterisk, 1);
        return Action.getClassName(clinicianDepartmentAddressLabelWithAsterisk).contains(mandatoryLabelAttribute);
    }

    public boolean professionalRegistrationNumberFieldIsEmptyForAdditionalClinicianOne() {
        boolean fieldIsDisplayed = additionalClinician1ProfessionalRegistrationNumberField.isDisplayed();
        boolean fieldIsBlank = Action.getText(additionalClinician1ProfessionalRegistrationNumberField).isEmpty();
        return fieldIsDisplayed && fieldIsBlank;
    }

    public boolean departmentNameAndAddressFieldIsEmptyForAdditionalClinicianOne() {
        boolean fieldIsDisplayed = additionalClinician1DepartmentAddressField.isDisplayed();
        boolean fieldIsBlank = Action.getText(additionalClinician1DepartmentAddressField).isEmpty();
        return fieldIsDisplayed && fieldIsBlank;
    }

    public boolean emailFieldIsEmptyForAdditionalClinicianOne() {
        boolean fieldIsDisplayed = additionalClinician1EmailField.isDisplayed();
        boolean fieldIsBlank = Action.getText(additionalClinician1EmailField).isEmpty();
        return fieldIsDisplayed && fieldIsBlank;
    }

    public boolean phoneNumberFieldIsEmptyForAdditionalClinicianOne() {
        boolean fieldIsDisplayed = additionalClinician1PhoneNumberField.isDisplayed();
        boolean fieldIsBlank = Action.getText(additionalClinician1PhoneNumberField).isEmpty();
        return fieldIsDisplayed && fieldIsBlank;
    }

    public boolean lastNameFieldIsEmptyForAdditionalClinicianOne() {
        boolean fieldIsDisplayed = additionalClinician1LastNameField.isDisplayed();
        boolean fieldIsBlank = Action.getText(additionalClinician1LastNameField).isEmpty();
        return fieldIsDisplayed && fieldIsBlank;
    }

    public boolean firstNameFieldIsEmptyForAdditionalClinicianOne() {
        boolean fieldIsDisplayed = additionalClinician1FirstNameField.isDisplayed();
        boolean fieldIsBlank = Action.getText(additionalClinician1FirstNameField).isEmpty();
        return fieldIsDisplayed && fieldIsBlank;
    }

    public boolean verifyResponsibleClinicianFieldsAreDisabledWithAutoCompleteFeature() {
        try {
            if (!Wait.isElementDisplayed(driver, clinicianFirstNameField, 30)) {
                Debugger.println("Responsible Clinician Page is not displayed properly." + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("ResponsibleClinicianPage.jpg");
                return false;
            }
            String autoComplete = clinicianFirstNameField.getAttribute("autocomplete");
            if (!autoComplete.equalsIgnoreCase("Off")) {
                Debugger.println("Responsible Clinician Page:clinicianFirstNameField autoComplete attribute supposed to be OFF. but not.");
                return false;
            }
            autoComplete = clinicianLastNameField.getAttribute("autocomplete");
            if (!autoComplete.equalsIgnoreCase("Off")) {
                Debugger.println("Responsible Clinician Page:clinicianLastNameField autoComplete attribute supposed to be OFF. but not.");
                return false;
            }

            autoComplete = clinicianPhoneNumberField.getAttribute("autocomplete");
            if (!autoComplete.equalsIgnoreCase("Off")) {
                Debugger.println("Responsible Clinician Page:clinicianPhoneNumberField autoComplete attribute supposed to be OFF. but not.");
                return false;
            }

            autoComplete = clinicianEmailField.getAttribute("autocomplete");
            if (!autoComplete.equalsIgnoreCase("Off")) {
                Debugger.println("Responsible Clinician Page:clinicianEmailField autoComplete attribute supposed to be OFF. but not.");
                return false;
            }

            autoComplete = clinicianDepartmentAddressField.getAttribute("autocomplete");
            if (!autoComplete.equalsIgnoreCase("Off")) {
                Debugger.println("Responsible Clinician Page:clinicianDepartmentAddressField autoComplete attribute supposed to be OFF. but not.");
                return false;
            }

            autoComplete = clinicianProfesionalRegistrationNumberField.getAttribute("autocomplete");
            if (!autoComplete.equalsIgnoreCase("Off")) {
                Debugger.println("Responsible Clinician Page:clinicianProfesionalRegistrationNumberField autoComplete attribute supposed to be OFF. but not.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyResponsibleClinicianFieldsAreDisabledWithAutoCompleteFeature." + exp + "\n" + driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("ResponsibleClinicianPage.jpg");
            return false;
        }
    }

    //Added for filling up the Responsible clinician page
    public boolean fillResponsibleClinicianDetails(String clinicalInfo) {
        try {
            if (clinicalInfo == null || clinicalInfo.isEmpty()) {
                return true;//Some cases where responsible clinician details need not enter
            }
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(clinicalInfo);
            //Changed switch case, as the order matters to avoid overlay elements
            //FirstName
            String paramValue = paramNameValue.get("FirstName");
            if (paramValue != null && (!paramValue.isEmpty())) {
                try {
                    clinicianFirstNameField.sendKeys(paramValue);
                } catch (Exception exp) {
                    seleniumLib.sendValue(clinicianFirstNameField, paramValue);
                }
            }
            //LastName
            paramValue = paramNameValue.get("LastName");
            if (paramValue != null && (!paramValue.isEmpty())) {
                try {
                    clinicianLastNameField.sendKeys(paramValue);
                } catch (Exception exp) {
                    seleniumLib.sendValue(clinicianLastNameField, paramValue);
                }
            }
            //Email
            paramValue = paramNameValue.get("Email");
            if (paramValue != null && (!paramValue.isEmpty())) {
                try {
                    clinicianEmailField.sendKeys(paramValue);
                } catch (Exception exp) {
                    seleniumLib.sendValue(clinicianEmailField, paramValue);
                }
            }
            //Department
            paramValue = paramNameValue.get("Department");
            if (paramValue != null && (!paramValue.isEmpty())) {
                try {
                    clinicianDepartmentAddressField.sendKeys(paramValue);
                } catch (Exception exp) {
                    seleniumLib.sendValue(clinicianDepartmentAddressField, paramValue);
                }
            }
            //Registration
            paramValue = paramNameValue.get("Registration");
            if (paramValue != null && (!paramValue.isEmpty())) {
                try {
                    clinicianProfesionalRegistrationNumberField.sendKeys(paramValue);
                } catch (Exception exp) {
                    seleniumLib.sendValue(clinicianProfesionalRegistrationNumberField, paramValue);
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in Filling ResponsibleClinician Information: " + exp + "\n" + driver.getCurrentUrl());
            return false;
        }
    }

    public boolean fillResponsibleClinicianFields(String clinicalInfo) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(clinicalInfo);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "FirstName": {
                    seleniumLib.sendValue(clinicianFirstNameField, paramNameValue.get(key));
                    break;
                }
                case "LastName": {
                    seleniumLib.sendValue(clinicianLastNameField, paramNameValue.get(key));
                    break;
                }
                case "Department": {
                    seleniumLib.sendValue(clinicianDepartmentAddressField, paramNameValue.get(key));
                    break;
                }
                case "EmailAddress": {
                    seleniumLib.sendValue(clinicianEmailField, paramNameValue.get(key));
                }

            }
        }
        return true;
    }

    public boolean verifyResponsibleClinicianDetails(String clinicalInfo) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(clinicalInfo);
        Set<String> paramsKey = paramNameValue.keySet();
        String actValue = "";
        for (String key : paramsKey) {
            switch (key) {
                case "FirstName": {
                    actValue = clinicianFirstNameField.getAttribute("value");

                    if (!actValue.equalsIgnoreCase(paramNameValue.get(key))) {
                        Debugger.println("Expected :" + key + ": " + paramNameValue.get(key) + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "LastName": {
                    actValue = clinicianLastNameField.getAttribute("value");
                    if (!actValue.equalsIgnoreCase(paramNameValue.get(key))) {
                        Debugger.println("Expected :" + key + ": " + paramNameValue.get(key) + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "Department": {
                    actValue = clinicianDepartmentAddressField.getAttribute("value");
                    if (!actValue.equalsIgnoreCase(paramNameValue.get(key))) {
                        Debugger.println("Expected :" + key + ": " + paramNameValue.get(key) + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "EmailAddress": {
                    actValue = clinicianEmailField.getAttribute("value");
                    if (!actValue.equalsIgnoreCase(paramNameValue.get(key))) {
                        Debugger.println("Expected :" + ": " + paramNameValue.get(key) + ", Actual:" + actValue);
                    }
                }
            }
        }
        return true;
    }

    public void enterMultipleEmailIDs(String mailIDs) {
        Wait.forElementToBeDisplayed(driver, clinicianEmailField);
        clinicianEmailField.sendKeys(mailIDs);
        Wait.seconds(2);
        clinicianPhoneNumberField.click();
        Wait.seconds(2);
    }

    public boolean verifyNoEmailWarningMessage() {
        try {
            for (int i = 0; i < clinicianErrorMessages.size(); i++) {
                if (Wait.isElementDisplayed(driver, clinicianErrorMessages.get(i), 5)) {
//                    Debugger.println("error message displayed");
                    return false;
                }
            }
            Debugger.println("NO error message displayed");
            return true;

        } catch (Exception exp) {
//            Debugger.println("Exception message");
            return false;
        }
    }

}