package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class NewPatientPage {

	WebDriver driver;

	public NewPatientPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement title;
	public WebElement firstName;
	public WebElement familyName;
	public WebElement lastName;
	public WebElement dateOfBirth;
	public WebElement dateOfDeath;
	public WebElement nhsNumber;
	public WebElement hospitalNumber;
	public WebElement postcode;
	public WebElement dateDay;
	public WebElement dateMonth;
	public WebElement dateYear;

	@FindBy(css = "h1[class*='page-title']")
	public WebElement pageTitle;

	@FindBy(css = "label[for*='dateOfBirth']")
	public WebElement dateOfBirthLabel;

	@FindBy(css = "label[for*='title']")
	public WebElement titleLabel;

	@FindBy(css = "label[for*='firstName']")
	public WebElement firstnameLabel;

	@FindBy(css = "label[for*='familyName']")
	public WebElement familyNameLabel;

	@FindBy(css = "label[for*='gender']")
	public WebElement genderLabel;

	@FindBy(css = "label[for*='administrativeGender']")
	public WebElement administrativeGenderLabel;

	@FindBy(css = "div[id*='react-select']")
	public WebElement dropdownValue;

	@FindBy(css = "label[for*='lifeStatus']")
	public WebElement lifeStatusLabel;

	@FindBy(css = "label[for*='ethnicity']")
	public WebElement ethnicityLabel;

	@FindBy(css = "label[for*='hospitalNumber']")
	public WebElement hospitalNumberLabel;

	@FindBy(css = "label[for*='nhsNumber']")
	public WebElement nhsNumberLabel;

	@FindBy(css = "label[for*='address']")
	public WebElement addressLabel;

	@FindBy(css = "label[for*='postcode']")
	public WebElement postcodeLabel;

	@FindBy(xpath = "//label[contains(@for,'administrativeGender')]//following::div")
	public WebElement administrativeGenderButton;

	@FindBy(xpath = "//label[contains(@for,'gender')]//following::div")
	public WebElement genderButton;

	@FindBy(css = "*[class*='notification--warning']")
	public WebElement patientDetailsnotificationBanner;

	@FindBy(xpath = "//label[contains(@for,'lifeStatus')]//following::div")
	public WebElement lifeStatusButton;

	@FindBy(xpath = "//label[contains(@for,'ethnicity')]//following::div")
	public WebElement ethnicityButton;

	@FindBy(xpath = "//label[contains(@for,'noNhsNumberReason')]//following::div")
	public WebElement noNhsNumberReasonDropdown;

	@FindBy(xpath = "//button[text()='Update NGIS record']")
	public WebElement updateNGISRecordButton;

	@FindBy(xpath = "//button[text()='Add details to NGIS']")
	public WebElement addDetailsToNGISButton;

	@FindBy(xpath = "//button[text()='Save patient details to NGIS']")
	public WebElement savePatientDetailsToNGISButton;

	//	@FindBy(xpath = "//button[text()='Start referral']")
	//	public WebElement startReferralButton;

	@FindBy(id = "address[0]")
	public WebElement addressLine0;

	@FindBy(id = "address[1]")
	public WebElement addressLine1;

	@FindBy(id = "address[2]")
	public WebElement addressLine2;

	@FindBy(id = "address[3]")
	public WebElement addressLine3;

	@FindBy(id = "address[4]")
	public WebElement addressLine4;


	public void newPatientPageIsDisplayed() {
		Wait.forURLToContainSpecificText(driver, "/new-patient");
	}

	public void nhsNumberAndDOBFieldsArePrePopulatedInNewPatientPage() {
		String DOB = PatientSearchPage.testData.getDay()  + "/" + PatientSearchPage.testData.getMonth() + "/" + PatientSearchPage.testData.getYear();
		Debugger.println("Expected DOB : " + DOB + " : " + "Actual DOB" + Actions.getValue(dateOfBirth));
		Assert.assertEquals(DOB, Actions.getValue(dateOfBirth));
	}


	public boolean verifyTheElementsOnAddNewPatientPage() {

		Wait.forElementToBeDisplayed(driver, savePatientDetailsToNGISButton);
		pageTitle.isDisplayed();
		title.isDisplayed();
		titleLabel.isDisplayed();
		firstName.isDisplayed();
		firstnameLabel.isDisplayed();
		familyName.isDisplayed();
		familyNameLabel.isDisplayed();
		dateOfBirth.isDisplayed();
		dateOfBirthLabel.isDisplayed();
		administrativeGenderButton.isDisplayed();
		administrativeGenderLabel.isDisplayed();
		lifeStatusButton.isDisplayed();
		lifeStatusLabel.isDisplayed();
		dateOfBirth.isDisplayed();
		dateOfBirthLabel.isDisplayed();
		ethnicityButton.isDisplayed();
		ethnicityLabel.isDisplayed();
		noNhsNumberReasonDropdown.isDisplayed();
		hospitalNumber.isDisplayed();
		hospitalNumberLabel.isDisplayed();
		addressLabel.isDisplayed();
		addressLine0.isDisplayed();
		addressLine1.isDisplayed();
		addressLine2.isDisplayed();
		addressLine3.isDisplayed();
		addressLine4.isDisplayed();
		postcodeLabel.isDisplayed();
		postcode.isDisplayed();

		return true;
	}

}

