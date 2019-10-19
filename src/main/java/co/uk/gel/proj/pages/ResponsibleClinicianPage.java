package co.uk.gel.proj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResponsibleClinicianPage {

	WebDriver driver;

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

}
