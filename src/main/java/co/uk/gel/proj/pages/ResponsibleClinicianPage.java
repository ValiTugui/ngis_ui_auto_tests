package co.uk.gel.proj.pages;

import co.uk.gel.lib.*;
import co.uk.gel.proj.util.RandomDataCreator;
import com.github.javafaker.Faker;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.StylesUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponsibleClinicianPage {

	WebDriver driver;
	Faker fake = new Faker();

String key1 = "mainClinician";
String key2 = "additionalClinician";
	public HashMap<String, ArrayList<String>> cliniciansMap = new HashMap<>();

	public ResponsibleClinicianPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class*='clinician-details__intro']")
	public WebElement clinicianDetails;

	@FindBy(css = "div[class*='clinician-details-form__heading']")
	public WebElement clinicianFormHeader;

	@FindBy(css = "div[class*='clinician-details-form__info']")
	public WebElement clinicianFormInfo;

	@FindBy(css = "button[class*='clinician-details-form__add']")
	public WebElement addAnotherClinicianButton;

	@FindBy(css = "button[class*='clinician-details-form__remove']")
	public List<WebElement> removeClinicianButton;

	@FindBy(css = "label[for*='responsibleClinician.forename']")
	public WebElement clinicianFirstNameLabel;

	@FindBy(css = "label[for*='responsibleClinician.surname']")
	public WebElement clinicianLastNameLabel;

	@FindBy(css = "label[for*='responsibleClinician.phoneNumber']")
	public WebElement clinicianPhoneNumberLabel;

	@FindBy(css = "label[for*='responsibleClinician.emailAddress']")
	public WebElement clinicianEmailLabel;

	@FindBy(css = "label[for*='responsibleClinician.departmentalAddress']")
	public WebElement clinicianDepartmentAddressLabel;

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

	@FindBy(css = "input[id*='responsibleClinician.email']")
	public WebElement clinicianEmailField;

	@FindBy(css = "textarea[id*='responsibleClinician.departmentalAddress']")
	public WebElement clinicianDepartmentAddressField;

	@FindBy(css = "textarea[id*='responsibleClinician.professionalRegistrationNumber']")
	public WebElement clinicianProfesionalRegistrationNumberField;

	@FindBy(css = "input[id*='additionalClinicians[0].forename']")
	public WebElement additionalClinicianFirstNameField;

	@FindBy(css = "input[id*='additionalClinicians[0].surname']")
	public WebElement additionalClinicianLastNameField;

	@FindBy(css = "input[id*='additionalClinicians[0].phoneNumber']")
	public WebElement additionalClinicianPhoneNumberField;

	@FindBy(css = "input[id*='additionalClinicians[0].email']")
	public WebElement additionalClinicianEmailField;

	@FindBy(css = "textarea[id*='additionalClinicians[0].departmentalAddress']")
	public WebElement additionalClinicianDepartmentAddressField;

	@FindBy(css = "textarea[id*='additionalClinicians[0].professionalRegistrationNumber']")
	public WebElement additionalClinicianProfesionalRegistrationNumberField;

	@FindBy(css = "p[class*='hint__text']")
	public List<WebElement> clinicianFieldsHintTexts;

	@FindBy(css = "div[class*='error-message__text']")
	public List<WebElement> clinicianErrorMessages;

	public void fillInClinicianFormFields() {
		String firstName = RandomDataCreator.getRandomFirstName();
		String lastName = RandomDataCreator.getRandomLastName();
		String phoneNumber = RandomDataCreator.getRandomPhoneNumber();
		String email = RandomDataCreator.getRandomEmailAddress();
		String departmentAddress = RandomDataCreator.getRandomAddress();
		String professionalRegistrationNumber = RandomDataCreator.getRandomProfessionalRegistrationNumber();

		Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
        Actions.clearField(clinicianFirstNameField);
        Actions.fillInValue(clinicianFirstNameField, firstName);
        Actions.clearField(clinicianLastNameField);
        Actions.fillInValue(clinicianLastNameField, lastName);
        Actions.clearField(clinicianPhoneNumberField);
        Actions.fillInValue(clinicianPhoneNumberField, phoneNumber);
        Actions.clearField(clinicianEmailField);
        Actions.fillInValue(clinicianEmailField, email);
        Actions.clearField(clinicianDepartmentAddressField);
        Actions.fillInValue(clinicianDepartmentAddressField, departmentAddress);
        Actions.clearField(clinicianProfesionalRegistrationNumberField);
        Actions.fillInValue(clinicianProfesionalRegistrationNumberField, professionalRegistrationNumber);

		ArrayList clinicianDetails = new ArrayList();
		clinicianDetails.add(0,firstName);
		clinicianDetails.add(1,lastName);
		clinicianDetails.add(2,phoneNumber);
		clinicianDetails.add(3,email);
		clinicianDetails.add(4,departmentAddress);
		clinicianDetails.add(5,professionalRegistrationNumber);

		storeClinicianDataForVerification(key1 , clinicianDetails);


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
		Actions.clearField(clinicianFirstNameField);
		Actions.fillInValue(clinicianFirstNameField, fake.name().firstName());
		Actions.clearField(clinicianPhoneNumberField);
		Actions.fillInValue(clinicianPhoneNumberField, fake.phoneNumber().cellPhone());
		Actions.clearField(clinicianEmailField);
		Actions.fillInValue(clinicianEmailField, fake.internet().emailAddress());
		Actions.clearField(clinicianProfesionalRegistrationNumberField);
		Actions.fillInValue(clinicianProfesionalRegistrationNumberField, fake.number().digits(12));
	}

	public void fillInClinicianFormFieldsExceptDepartmentAddressField() {
		Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
		Actions.clearField(clinicianFirstNameField);
		Actions.fillInValue(clinicianFirstNameField, fake.name().firstName());
		Actions.clearField(clinicianLastNameField);
		Actions.fillInValue(clinicianLastNameField, fake.name().lastName());
		Actions.clearField(clinicianPhoneNumberField);
		Actions.fillInValue(clinicianPhoneNumberField, fake.phoneNumber().cellPhone());
		Actions.clearField(clinicianEmailField);
		Actions.fillInValue(clinicianEmailField, fake.internet().emailAddress());
		Actions.clearField(clinicianProfesionalRegistrationNumberField);
		Actions.fillInValue(clinicianProfesionalRegistrationNumberField, fake.number().digits(12));
	}


	public boolean verifyHyperlinkExists(String expectedHyperlinkText){
      Wait.forElementToBeDisplayed(driver, addAnotherClinicianButton);
      if(addAnotherClinicianButton.isDisplayed() && addAnotherClinicianButton.getText().contains(expectedHyperlinkText)){
      	return true;
	  }
      else return false;
	}

	public boolean verifyLastNameFieldIsMandatory(String expectedErrorMessage) {
		return clinicianErrorMessages.get(0).getText().contains(expectedErrorMessage);
	}

	public boolean verifyLastNameFieldIsHighlightedInRed(String expectedColourUponError){
		Wait.forElementToBeDisplayed(driver, clinicianLastNameLabel);
		Wait.forElementToBeDisplayed(driver, clinicianLastNameField);
		String lastNameLabelActualColorUponError = clinicianLastNameLabel.getCssValue("color").toString();
		String lastNameFieldErrorMessageActualColorUponError = clinicianErrorMessages.get(0).getCssValue("color").toString();
		String redColour = StylesUtils.convertFontColourStringToCSSProperty(expectedColourUponError);
        if(lastNameLabelActualColorUponError.equals(redColour) && lastNameFieldErrorMessageActualColorUponError.equals(redColour)){
        	return true;
		}
        else return false;
	}

	public boolean verifyDepartmentalAddressIsDisplayedAsMandatoryField(){
		Wait.forElementToBeDisplayed(driver, clinicianDepartmentalAddressLabelRequired);
		// verify the asterisk (*) symbol is shown next to the Departmental Address label on the Responsible Clinician page
		Wait.forElementToBeDisplayed(driver, clinicianDepartmentalAddressLabelRequired);
		return clinicianDepartmentalAddressLabelRequired.getText().contains("*");
	}

	public void fillInClinicianFormFieldsExceptLastNameField() {
		Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
		Actions.clearField(clinicianFirstNameField);
		Actions.fillInValue(clinicianFirstNameField, fake.name().firstName());
		Actions.clearField(clinicianPhoneNumberField);
		Actions.fillInValue(clinicianPhoneNumberField, fake.phoneNumber().cellPhone());
		Actions.clearField(clinicianEmailField);
		Actions.fillInValue(clinicianEmailField, fake.internet().emailAddress());
		Actions.clearField(clinicianDepartmentAddressField);
		Actions.fillInValue(clinicianDepartmentAddressField, fake.address().streetAddress());
		Actions.clearField(clinicianProfesionalRegistrationNumberField);
		Actions.fillInValue(clinicianProfesionalRegistrationNumberField, fake.number().digits(12));
	}

	public void confirmTheExpectedFieldsToBeSeemInClinicianForm(){
		Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
		clinicianFirstNameField.isDisplayed();
		clinicianLastNameField.isDisplayed();
		clinicianPhoneNumberField.isDisplayed();
		clinicianEmailField.isDisplayed();
		clinicianDepartmentAddressField.isDisplayed();
		clinicianProfesionalRegistrationNumberField.isDisplayed();
	}

	public void clickAddAnotherLink() {
		if (removeClinicianButton.size() == 0) {
			Click.element(driver, addAnotherClinicianButton);
		} else {
			Click.element(driver, removeClinicianButton.get(0));
			Click.element(driver, addAnotherClinicianButton);
		}
	}

	public void fillInAdditionalClinicianFormFields() {
		String firstName = RandomDataCreator.getRandomFirstName();
		String lastName = RandomDataCreator.getRandomLastName();
		String phoneNumber = RandomDataCreator.getRandomPhoneNumber();
		String email = RandomDataCreator.getRandomEmailAddress();
		String departmentAddress = RandomDataCreator.getRandomAddress();
		String professionalRegistrationNumber = RandomDataCreator.getRandomProfessionalRegistrationNumber();

		Wait.forElementToBeDisplayed(driver, additionalClinicianFirstNameField);
		Actions.fillInValue(additionalClinicianFirstNameField, firstName);
		Actions.fillInValue(additionalClinicianLastNameField, lastName);
		Actions.fillInValue(additionalClinicianPhoneNumberField, phoneNumber);
		Actions.fillInValue(additionalClinicianEmailField, email);
		Actions.fillInValue(additionalClinicianDepartmentAddressField, departmentAddress);
		Actions.fillInValue(additionalClinicianProfesionalRegistrationNumberField, professionalRegistrationNumber);

		ArrayList<String> clinicianDetails = new ArrayList<>();
		clinicianDetails.add(0,firstName);
		clinicianDetails.add(1,lastName);
		clinicianDetails.add(2,phoneNumber);
		clinicianDetails.add(3,email);
		clinicianDetails.add(4,departmentAddress);
		clinicianDetails.add(5,professionalRegistrationNumber);

	    storeClinicianDataForVerification(key2 , clinicianDetails);

	}
	public boolean clinicianDetailsArePersistedAtLoad() {
		Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
		boolean doesFirstNameMatch = Actions.getValue(clinicianFirstNameField).matches(cliniciansMap.get(key1).get(0));
		boolean doesLastNameMatch = Actions.getValue(clinicianLastNameField).matches(cliniciansMap.get(key1).get(1));
		boolean doesPhoneNumberMatch = Actions.getValue(clinicianPhoneNumberField).matches(cliniciansMap.get(key1).get(2));
		boolean doesEmailMatch = Actions.getValue(clinicianEmailField).matches(cliniciansMap.get(key1).get(3));
		boolean doesDepartmentAddressMatch = Actions.getValue(clinicianDepartmentAddressField).matches(cliniciansMap.get(key1).get(4));
		boolean doesProfessionalRegistrationNumberMatch = Actions.getValue(clinicianProfesionalRegistrationNumberField).matches(cliniciansMap.get(key1).get(5));

		return doesFirstNameMatch && doesLastNameMatch && doesPhoneNumberMatch && doesEmailMatch && doesDepartmentAddressMatch && doesProfessionalRegistrationNumberMatch;
	}

	public boolean additionalClinicianDetailsArePersistedAtLoad() {
		Wait.forElementToBeDisplayed(driver, additionalClinicianFirstNameField);
		boolean doesFirstNameMatch = Actions.getValue(additionalClinicianFirstNameField).matches(cliniciansMap.get(key2).get(0));
		boolean doesLastNameMatch = Actions.getValue(additionalClinicianLastNameField).matches(cliniciansMap.get(key2).get(1));
		boolean doesPhoneNumberMatch = Actions.getValue(additionalClinicianPhoneNumberField).matches(cliniciansMap.get(key2).get(2));
		boolean doesEmailMatch = Actions.getValue(additionalClinicianEmailField).matches(cliniciansMap.get(key2).get(3));
		boolean doesDepartmentAddressMatch = Actions.getValue(additionalClinicianDepartmentAddressField).matches(cliniciansMap.get(key2).get(4));
		boolean doesProfessionalRegistrationNumberMatch = Actions.getValue(additionalClinicianProfesionalRegistrationNumberField).matches(cliniciansMap.get(key2).get(5));

		return doesFirstNameMatch && doesLastNameMatch && doesPhoneNumberMatch && doesEmailMatch && doesDepartmentAddressMatch && doesProfessionalRegistrationNumberMatch;
	}

	public void storeClinicianDataForVerification(String clinicianIdentifier, ArrayList<String> clinicianInfo) {
		cliniciansMap.put(clinicianIdentifier,clinicianInfo);
	}

	public String getClinicianHelpText(){
		Wait.forElementToBeDisplayed(driver, clinicianDetails);
		return Actions.getText(clinicianDetails);
	}

	public String getSectionTitle(){
		Wait.forElementToBeDisplayed(driver, clinicianFormHeader);
		return Actions.getText(clinicianFormHeader);
	}

	public boolean firstNameFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
		return clinicianFirstNameField.isEnabled() && clinicianFirstNameLabel.isDisplayed();
	}

	public boolean lastNameFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianLastNameField);
		return clinicianLastNameField.isEnabled() && clinicianLastNameLabel.isDisplayed();
	}

	public boolean emailFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianEmailField);
		return clinicianEmailField.isEnabled() && clinicianEmailLabel.isDisplayed();
	}

	public boolean phoneNumberFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianPhoneNumberField);
		return clinicianPhoneNumberField.isEnabled() && clinicianPhoneNumberLabel.isDisplayed();
	}

	public boolean departmentNameAndAddressFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianDepartmentAddressField);
		return clinicianDepartmentAddressField.isEnabled() && clinicianDepartmentAddressLabel.isDisplayed();
	}

	public boolean professionalRegistrationNumberFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianProfesionalRegistrationNumberField);
		return clinicianProfesionalRegistrationNumberField.isEnabled() && clinicianProfesionalRegistrationNumberLabel.isDisplayed();
	}

	public String getContactSectionTitle(){
		Wait.forElementToBeDisplayed(driver, clinicianContactSectionLabel);
		return Actions.getText(clinicianContactSectionLabel);
	}

	public String getContactSectionHelpText(){
		Wait.forElementToBeDisplayed(driver, clinicianFormInfo);
		return Actions.getText(clinicianFormInfo);
	}
}
