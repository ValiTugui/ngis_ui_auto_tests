package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResponsibleClinicianPage {

	WebDriver driver;
	Faker fake = new Faker();

	public ResponsibleClinicianPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class*='clinician-details__intro']")
	public WebElement clinicianDetails;

	@FindBy(css = "div[class*='clinician-details-form__heading']")
	public List<WebElement> clinicianFormHeader;

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
        Wait.forElementToBeDisplayed(driver, clinicianFirstNameField);
        Actions.clearField(clinicianFirstNameField);
        Actions.fillInValue(clinicianFirstNameField, fake.name().firstName());
        Actions.clearField(clinicianLastNameField);
        Actions.fillInValue(clinicianLastNameField, fake.name().lastName());
        Actions.clearField(clinicianPhoneNumberField);
        Actions.fillInValue(clinicianPhoneNumberField, fake.phoneNumber().cellPhone());
        Actions.clearField(clinicianEmailField);
        Actions.fillInValue(clinicianEmailField, fake.internet().emailAddress());
        Actions.clearField(clinicianDepartmentAddressField);
        Actions.fillInValue(clinicianDepartmentAddressField, fake.address().streetAddress());
        Actions.clearField(clinicianProfesionalRegistrationNumberField);
        Actions.fillInValue(clinicianProfesionalRegistrationNumberField, fake.number().digits(12));
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

    public boolean verifyTotalNumberOfDigitsInPhoneNumberField() {
        if (clinicianPhoneNumberField.getText().length() == 15) {
            return true;
        } else {
            return false;
        }

    }
}
