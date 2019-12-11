package co.uk.gel.proj.pages;


import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.TestUtils;
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
import java.util.Set;

public class ResponsibleClinicianPage {

	WebDriver driver;
	Faker fake = new Faker();


    String key1 = "mainClinician";
    String key2 = "additionalClinician1";
    String key3 = "additionalClinician2";
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

	@FindBy(css = "input[id*='responsibleClinician.email']")
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

	@FindBy(css = "input[id*='additionalClinicians[0].email']")
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

	@FindBy(css = "p[class*='hint__text']")
	public List<WebElement> clinicianFieldsHintTexts;

	@FindBy(css = "div[class*='error-message__text']")
	public List<WebElement> clinicianErrorMessages;

	@FindBy(css = "label[for*='additionalClinicians[0].surname']")
	public WebElement additionalClinician1LastNameLabel;

	String mandatoryLabelAttribute = "label__required-icon";
	String autoCompleteAttributeOff = "autoComplete_off";

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
        PatientDetailsPage.newPatient.setResponsibleClinicianName(firstName + " " + lastName);
        PatientDetailsPage.newPatient.setResponsibleClinicianEmail(email);
        PatientDetailsPage.newPatient.setResponsibleClinicianContactNumber(phoneNumber);

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

	public boolean verifyRemoveHyperlinkExists(String expectedHyperlinkText){
      Wait.forElementToBeDisplayed(driver, removeClinicianButton.get(0));
      return removeClinicianButton.get(0).isDisplayed() && Actions.getText(removeClinicianButton.get(0)).contains(expectedHyperlinkText);
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

	public boolean verifyLastNameFieldInAdditionalClinicianOneIsHighlightedInRed(String expectedColourUponError){
		Wait.forElementToBeDisplayed(driver, additionalClinician1LastNameLabel);
		Wait.forElementToBeDisplayed(driver, additionalClinician1LastNameField);
		String lastNameLabelActualColorUponError = additionalClinician1LastNameLabel.getCssValue("color").toString();
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
		Actions.clickElement(driver, addAnotherClinicianButton);
	}

	public void fillInAdditionalClinicianOneFormFields() {
		String firstName = RandomDataCreator.getRandomFirstName();
		String lastName = RandomDataCreator.getRandomLastName();
		String phoneNumber = RandomDataCreator.getRandomPhoneNumber();
		String email = RandomDataCreator.getRandomEmailAddress();
		String departmentAddress = RandomDataCreator.getRandomAddress();
		String professionalRegistrationNumber = RandomDataCreator.getRandomProfessionalRegistrationNumber();

		Wait.forElementToBeDisplayed(driver, additionalClinician1FirstNameField);
		Actions.fillInValue(additionalClinician1FirstNameField, firstName);
		Actions.fillInValue(additionalClinician1LastNameField, lastName);
		Actions.fillInValue(additionalClinician1PhoneNumberField, phoneNumber);
		Actions.fillInValue(additionalClinician1EmailField, email);
		Actions.fillInValue(additionalClinician1DepartmentAddressField, departmentAddress);
		Actions.fillInValue(additionalClinician1ProfessionalRegistrationNumberField, professionalRegistrationNumber);

		ArrayList<String> clinicianDetails = new ArrayList<>();
		clinicianDetails.add(0,firstName);
		clinicianDetails.add(1,lastName);
		clinicianDetails.add(2,phoneNumber);
		clinicianDetails.add(3,email);
		clinicianDetails.add(4,departmentAddress);
		clinicianDetails.add(5,professionalRegistrationNumber);

	    storeClinicianDataForVerification(key2 , clinicianDetails);

	}

	public void fillInAdditionalClinicianTwoFormFields() {
		String firstName = RandomDataCreator.getRandomFirstName();
		String lastName = RandomDataCreator.getRandomLastName();
		String phoneNumber = RandomDataCreator.getRandomPhoneNumber();
		String email = RandomDataCreator.getRandomEmailAddress();
		String departmentAddress = RandomDataCreator.getRandomAddress();
		String professionalRegistrationNumber = RandomDataCreator.getRandomProfessionalRegistrationNumber();

		Wait.forElementToBeDisplayed(driver, additionalClinician2FirstNameField);
		Actions.fillInValue(additionalClinician2FirstNameField, firstName);
		Actions.fillInValue(additionalClinician2LastNameField, lastName);
		Actions.fillInValue(additionalClinician2PhoneNumberField, phoneNumber);
		Actions.fillInValue(additionalClinician2EmailField, email);
		Actions.fillInValue(additionalClinician2DepartmentAddressField, departmentAddress);
		Actions.fillInValue(additionalClinician2ProfessionalRegistrationNumberField, professionalRegistrationNumber);

		ArrayList<String> clinicianDetails = new ArrayList<>();
		clinicianDetails.add(0,firstName);
		clinicianDetails.add(1,lastName);
		clinicianDetails.add(2,phoneNumber);
		clinicianDetails.add(3,email);
		clinicianDetails.add(4,departmentAddress);
		clinicianDetails.add(5,professionalRegistrationNumber);

	    storeClinicianDataForVerification(key3 , clinicianDetails);

	}

	public void fillInAdditionalClinicianOneFormFieldsExceptLastNameField() {
		String firstName = RandomDataCreator.getRandomFirstName();
		String phoneNumber = RandomDataCreator.getRandomPhoneNumber();
		String email = RandomDataCreator.getRandomEmailAddress();
		String departmentAddress = RandomDataCreator.getRandomAddress();
		String professionalRegistrationNumber = RandomDataCreator.getRandomProfessionalRegistrationNumber();

		Wait.forElementToBeDisplayed(driver, additionalClinician1FirstNameField);
		Actions.fillInValue(additionalClinician1FirstNameField, firstName);
		Actions.fillInValue(additionalClinician1PhoneNumberField, phoneNumber);
		Actions.fillInValue(additionalClinician1EmailField, email);
		Actions.fillInValue(additionalClinician1DepartmentAddressField, departmentAddress);
		Actions.fillInValue(additionalClinician1ProfessionalRegistrationNumberField, professionalRegistrationNumber);
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

	public boolean additionalClinicianOneDetailsArePersistedAtLoad() {
		Wait.forElementToBeDisplayed(driver, additionalClinician1FirstNameField);
		boolean doesFirstNameMatch = Actions.getValue(additionalClinician1FirstNameField).matches(cliniciansMap.get(key2).get(0));
		boolean doesLastNameMatch = Actions.getValue(additionalClinician1LastNameField).matches(cliniciansMap.get(key2).get(1));
		boolean doesPhoneNumberMatch = Actions.getValue(additionalClinician1PhoneNumberField).matches(cliniciansMap.get(key2).get(2));
		boolean doesEmailMatch = Actions.getValue(additionalClinician1EmailField).matches(cliniciansMap.get(key2).get(3));
		boolean doesDepartmentAddressMatch = Actions.getValue(additionalClinician1DepartmentAddressField).matches(cliniciansMap.get(key2).get(4));
		boolean doesProfessionalRegistrationNumberMatch = Actions.getValue(additionalClinician1ProfessionalRegistrationNumberField).matches(cliniciansMap.get(key2).get(5));

		return doesFirstNameMatch && doesLastNameMatch && doesPhoneNumberMatch && doesEmailMatch && doesDepartmentAddressMatch && doesProfessionalRegistrationNumberMatch;
	}

	public boolean additionalClinicianTwoDetailsArePersistedAtLoad() {
		Wait.forElementToBeDisplayed(driver, additionalClinician2FirstNameField);
		boolean doesFirstNameMatch = Actions.getValue(additionalClinician2FirstNameField).matches(cliniciansMap.get(key3).get(0));
		boolean doesLastNameMatch = Actions.getValue(additionalClinician2LastNameField).matches(cliniciansMap.get(key3).get(1));
		boolean doesPhoneNumberMatch = Actions.getValue(additionalClinician2PhoneNumberField).matches(cliniciansMap.get(key3).get(2));
		boolean doesEmailMatch = Actions.getValue(additionalClinician2EmailField).matches(cliniciansMap.get(key3).get(3));
		boolean doesDepartmentAddressMatch = Actions.getValue(additionalClinician2DepartmentAddressField).matches(cliniciansMap.get(key3).get(4));
		boolean doesProfessionalRegistrationNumberMatch = Actions.getValue(additionalClinician2ProfessionalRegistrationNumberField).matches(cliniciansMap.get(key3).get(5));

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

	public boolean firstNameFieldIsEmpty(){
		return Actions.getText(clinicianFirstNameField).isEmpty();
	}

	public boolean lastNameFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianLastNameField);
		return clinicianLastNameField.isEnabled() && clinicianLastNameLabel.isDisplayed();
	}

	public boolean lastNameFieldIsEmpty(){
		return Actions.getText(clinicianLastNameField).isEmpty();
	}

	public boolean emailFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianEmailField);
		return clinicianEmailField.isEnabled() && clinicianEmailLabel.isDisplayed();
	}

	public boolean emailFieldIsEmpty(){
		return Actions.getText(clinicianEmailField).isEmpty();
	}
	public boolean phoneNumberFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianPhoneNumberField);
		return clinicianPhoneNumberField.isEnabled() && clinicianPhoneNumberLabel.isDisplayed();
	}

	public boolean phoneNumberFieldIsEmpty(){
		return Actions.getText(clinicianPhoneNumberField).isEmpty();
	}
	public boolean departmentNameAndAddressFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianDepartmentAddressField);
		return clinicianDepartmentAddressField.isEnabled() && clinicianDepartmentAddressLabel.isDisplayed();
	}

	public boolean departmentNameAndAddressFieldIsEmpty(){
		return Actions.getText(clinicianDepartmentAddressField).isEmpty();
	}
	public boolean professionalRegistrationNumberFieldDisplayed(){
		Wait.forElementToBeDisplayed(driver, clinicianProfesionalRegistrationNumberField);
		return clinicianProfesionalRegistrationNumberField.isEnabled() && clinicianProfesionalRegistrationNumberLabel.isDisplayed();
	}

	public boolean professionalRegistrationNumberFieldIsEmpty(){
		return Actions.getText(clinicianProfesionalRegistrationNumberField).isEmpty();
	}
	public String getContactSectionTitle(){
		Wait.forElementToBeDisplayed(driver, clinicianContactSectionLabel);
		return Actions.getText(clinicianContactSectionLabel);
	}

	public String getContactSectionHelpText(){
		Wait.forElementToBeDisplayed(driver, clinicianFormInfo);
		return Actions.getText(clinicianFormInfo);
	}

	public boolean verifyDepartmentNameAndAddressLabelIsShownAsMandatory(){
		Wait.forElementToBeDisplayed(driver, clinicianDepartmentAddressLabel);
		Wait.isElementDisplayed(driver, clinicianDepartmentAddressLabelWithAsterisk, 1);
		return Actions.getClassName(clinicianDepartmentAddressLabelWithAsterisk).contains(mandatoryLabelAttribute);
	}

	public boolean professionalRegistrationNumberFieldIsEmptyForAdditionalClinicianOne(){
		boolean fieldIsDisplayed = additionalClinician1ProfessionalRegistrationNumberField.isDisplayed();
		boolean fieldIsBlank = Actions.getText(additionalClinician1ProfessionalRegistrationNumberField).isEmpty();
		return fieldIsDisplayed && fieldIsBlank;
	}

	public boolean departmentNameAndAddressFieldIsEmptyForAdditionalClinicianOne(){
		boolean fieldIsDisplayed = additionalClinician1DepartmentAddressField.isDisplayed();
		boolean fieldIsBlank = Actions.getText(additionalClinician1DepartmentAddressField).isEmpty();
		return fieldIsDisplayed && fieldIsBlank;
	}

	public boolean emailFieldIsEmptyForAdditionalClinicianOne(){
		boolean fieldIsDisplayed = additionalClinician1EmailField.isDisplayed();
		boolean fieldIsBlank = Actions.getText(additionalClinician1EmailField).isEmpty();
		return fieldIsDisplayed && fieldIsBlank;
	}

	public boolean phoneNumberFieldIsEmptyForAdditionalClinicianOne(){
		boolean fieldIsDisplayed = additionalClinician1PhoneNumberField.isDisplayed();
		boolean fieldIsBlank = Actions.getText(additionalClinician1PhoneNumberField).isEmpty();
		return fieldIsDisplayed && fieldIsBlank;
	}

	public boolean lastNameFieldIsEmptyForAdditionalClinicianOne(){
		boolean fieldIsDisplayed = additionalClinician1LastNameField.isDisplayed();
		boolean fieldIsBlank = Actions.getText(additionalClinician1LastNameField).isEmpty();
		return fieldIsDisplayed && fieldIsBlank;
	}

	public boolean firstNameFieldIsEmptyForAdditionalClinicianOne(){
		boolean fieldIsDisplayed = additionalClinician1FirstNameField.isDisplayed();
		boolean fieldIsBlank = Actions.getText(additionalClinician1FirstNameField).isEmpty();
		return fieldIsDisplayed && fieldIsBlank;
	}

	public boolean verifyResponsibleClinicianFieldsAreDisabledWithAutoCompleteFeature(){
		Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);

		boolean autoCompleteOffClinicianFirstNameField =  Actions.getAutoCompleteAttribute(clinicianFirstNameField).equalsIgnoreCase(autoCompleteAttributeOff);
		boolean autoCompleteOffClinicianLastNameField = Actions.getAutoCompleteAttribute(clinicianLastNameField).equalsIgnoreCase(autoCompleteAttributeOff);
		boolean autoCompleteOffClinicianPhoneNumberField =  Actions.getAutoCompleteAttribute(clinicianPhoneNumberField).equalsIgnoreCase(autoCompleteAttributeOff);
		boolean autoCompleteOffClinicianEmailField = Actions.getAutoCompleteAttribute(clinicianEmailField).equalsIgnoreCase(autoCompleteAttributeOff);
		boolean autoCompleteOffClinicianDepartmentAddressField = Actions.getAutoCompleteAttribute(clinicianDepartmentAddressField).equalsIgnoreCase(autoCompleteAttributeOff);
		boolean autoCompleteOffClinicianProfessionalRegistrationNumberField = Actions.getAutoCompleteAttribute(clinicianProfesionalRegistrationNumberField).equalsIgnoreCase(autoCompleteAttributeOff);

		if(autoCompleteOffClinicianFirstNameField &&  autoCompleteOffClinicianLastNameField &&
				autoCompleteOffClinicianPhoneNumberField && autoCompleteOffClinicianEmailField &&
				autoCompleteOffClinicianDepartmentAddressField && autoCompleteOffClinicianProfessionalRegistrationNumberField )
		{
			return true;
		} else return  false;
	}
	//Added for filling up the Responsible clinician page
	public boolean fillResponsibleClinicianDetails(String clinicalInfo) {
		try {
			if(clinicalInfo == null || clinicalInfo.isEmpty()){
				return true;
			}
			HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(clinicalInfo);
			Set<String> paramsKey = paramNameValue.keySet();
			for (String key : paramsKey) {
				switch (key) {
					case "FirstName":
						if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
							clinicianFirstNameField.sendKeys(paramNameValue.get(key));
						}
						break;
					case "LastName":
						if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
							clinicianLastNameField.sendKeys(paramNameValue.get(key));
						}
						break;
					case "Email":
						if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
							clinicianEmailField.sendKeys(paramNameValue.get(key));
						}
						break;
					case "Department":
						if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
							clinicianDepartmentAddressField.sendKeys(paramNameValue.get(key));
						}
						break;
					case "Registration":
						if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
							clinicianProfesionalRegistrationNumberField.sendKeys(paramNameValue.get(key));
						}
						break;
				}//switch
			}//for

			return true;
		}catch(Exception exp){
			Debugger.println("Exception in Filling ResponsibleClinician Information: "+exp);
			SeleniumLib.takeAScreenShot("ResponsibleClinician.jpg");
			return false;
		}
	}
}
