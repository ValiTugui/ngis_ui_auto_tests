package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PatientDetailsPage {
    WebDriver driver;
    public PatientDetailsPage(WebDriver driver) {
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

    @FindBy(xpath = "//button[text()='Update NGIS record']")
    public WebElement updateNGISRecordButton;

    @FindBy(xpath = "//button[text()='Add details to NGIS']")
    public WebElement addDetailsToNGISButton;

    @FindBy(xpath = "//button[text()='Save patient details to NGIS']")
    public WebElement savePatientDetailsToNGISButton;

    //	@FindBy(xpath = "//button[text()='Start referral']")
    //	public WebElement startReferralButton;

    @FindBy(xpath = "//button[contains(@class,'submit-button') and @type='button']")
    public WebElement startReferralButton;

    @FindBy(xpath = "//button[text()='Start a new referral']")
    public WebElement startNewReferralButton;

    @FindBy(css = "*[class*='success-notification']")
    public WebElement successNotification;

    @FindBy(xpath = "//*[contains(@class,'patient-details-form__back')]//child::a")
    public WebElement goBackToPatientSearchLink;

    @FindBy(css = "div[class*='referral-card']")
    public WebElement referralCard;

    @FindBy(css = "div[class*='referral-card__title']")
    public WebElement referralCardClinicalIndication;

    @FindBy(css = "*[class*='referral-card__clinician-info']")
    public WebElement referralCardClinicianInfo;

    @FindBy(css = "*[class*='referral-card__label']")
    public WebElement referralCardLabel;

    @FindBy(xpath = "//*[contains(@class,'referral-card__label')]//following::p")
    public WebElement referralID;

    @FindBy(css = "*[class*='badge']")
    public WebElement referralStatus;

    @FindBy(css = "*[class*='referral-card__cancel-reason']")
    public WebElement referralCancelReason;

    @FindBy(css = "*[class*='referral-list__link']")
    public WebElement referralLink;

    @FindBy(css = "div[class*='referral-list__heading']")
    public WebElement referralsListHeader;

    @FindBy(css = "div[class*='referral-list__cancelled-heading']")
    public WebElement cancelledReferralsListHeader;

    @FindBy(css = "div[class*='referral-list__info']")
    public WebElement referralsListInfo;

    @FindBy(css = "div[class*='error-message']")
    public List<WebElement> validationErrors;

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

    @FindBy(css = "*[class*='required-icon']")
    public List<WebElement> requiredRedAsterix;

    String startReferralButtonLocator = "//button[contains(@class,'submit-button') and @type='button']";

    public void clickStartReferralButton() {
        Actions.clickElement(driver, startReferralButton);
        Wait.forElementToDisappear(driver, By.xpath(startReferralButtonLocator));
    }

    public void clickStartNewReferralButton() {
        Actions.clickElement(driver, startNewReferralButton);
    }



}
