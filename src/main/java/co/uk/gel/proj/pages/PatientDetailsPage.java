package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.TestDataProvider.NgisPatientOne;
import co.uk.gel.proj.TestDataProvider.NgisPatientTwo;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PatientDetailsPage {

    WebDriver driver;
    Faker faker = new Faker();
    public static NewPatient newPatient = new NewPatient();

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
    public WebElement otherReasonExplanation;

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

    @FindBy(css = "label[for*='hospitalNumber']")
    public WebElement hospitalNumberLabel;

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

    @FindBy(xpath = "(//label[contains(@for,'noNhsNumberReason')]//following::div)[4]")
    public WebElement noNhsNumberReasonDropdown;

    @FindBy(xpath = "textarea[class*='textarea']")
    public WebElement explanationForNoNhsNumber;

    @FindBy(xpath = "//button[text()='Update NGIS record']")
    public WebElement updateNGISRecordButton;

    @FindBy(xpath = "//button[text()='Add details to NGIS']")
    public WebElement addDetailsToNGISButton;

    @FindBy(xpath = "//button[text()='Save patient details to NGIS']")
    public WebElement savePatientDetailsToNGISButton;

    @FindBy(xpath = "//button[text()='Update NGIS record']")
    public List<WebElement> updateNGISRecordButtonList;

    @FindBy(xpath = "//button[text()='Save patient details to NGIS']")
    public List<WebElement> savePatientDetailsToNGISButtonList;

    @FindBy(xpath = "//button[text()='Add details to NGIS']")
    public List<WebElement> addDetailsToNGISButtonList;

    @FindBy(xpath = "(//p[text()='Referral ID'])[2]/..//p[2]")
    public WebElement firstReferralIDInReferralCard;

    @FindBy(xpath = "//button[text()='Start referral']")
    public WebElement startReferralButton2;

    @FindBy(xpath = "//button[contains(@class,'submit-button') and @type='button']")
    public WebElement startReferralButton;

    @FindBy(xpath = "//button[text()='Start a new referral']")
    public WebElement startNewReferralButton;

    @FindBy(css = "*[class*='success-notification']")
    public WebElement successNotification;

    @FindBy(xpath = "//*[contains(@class,'patient-details-form__back')]//child::a")
    public WebElement goBackToPatientSearchLink;

    @FindBy(css = "a[class*='referral-list']")
    public List<WebElement> referralListCards;

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

    @FindBy(xpath = "//button[text()='No']")
    public WebElement noButton;

    @FindBy(xpath = "//button[text()='Yes']")
    public WebElement yesButton;

    String startReferralButtonLocator = "//button[contains(@class,'submit-button') and @type='button']";
    String startANewReferralButtonLocator = "//button[contains(@class,'submit-button') and text()='Start a new referral']";
    String dropDownValuesFromLocator = "//span[text()[('^[A-Z ]*-*')]]";


    public boolean patientDetailsPageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "/patient-details");
        Wait.forElementToBeDisplayed(driver, startReferralButton);
        return true;
    }

    public void newPatientPageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "/new-patient");
    }

    public void fillInNewPatientDetailsWithoutAddressFields() {

        Wait.forElementToBeDisplayed(driver, title);
        newPatient.setTitle("Mr");
        title.sendKeys("Mr"); // OR //Actions.fillInValue(title, "MR");

        newPatient.setFirstName(faker.name().firstName());
        Actions.fillInValue(firstName, newPatient.getFirstName());

        newPatient.setLastName(faker.name().lastName());
        Actions.fillInValue(familyName, newPatient.getLastName());

        String dayOfBirth = String.valueOf(faker.number().numberBetween(1, 31));
        String monthOfBirth = String.valueOf(faker.number().numberBetween(1, 12));
        String yearOfBirth = String.valueOf(faker.number().numberBetween(1900, 2019));

        newPatient.setDay(dayOfBirth);
        newPatient.setMonth(monthOfBirth);
        newPatient.setYear(yearOfBirth);

        String nhsNumber = RandomDataCreator.generateRandomNHSNumber();
        newPatient.setNhsNumber(nhsNumber);

        String gender = "Male";
        newPatient.setGender(gender);
        editDropdownField(administrativeGenderButton, gender);
        editDropdownField(lifeStatusButton, "Alive");
        Actions.fillInValue(dateOfDeath, "01/01/2015");
        editDropdownField(ethnicityButton, "A - White - British");
        Actions.fillInValue(hospitalNumber, faker.numerify("A#R##BB##"));
    }

    public void fillInAllFieldsNewPatientDetailsWithOutNhsNumber(String reason) {
        //fillInAllNewPatientDetails();
        selectMissingNhsNumberReason(reason);
        if (reason.equalsIgnoreCase("Other - provide explanation")) {
            Wait.forElementToBeDisplayed(driver, otherReasonExplanation);
            otherReasonExplanation.sendKeys(faker.numerify("misplaced my NHS Number"));
        }
        //This function moved from top to last as in e2e latest, works like this.
        fillInAllNewPatientDetails();
    }

    public void fillInAllNewPatientDetails() {
        fillInNewPatientDetailsWithoutAddressFields();
        Actions.fillInValue(addressLine0, faker.address().buildingNumber());
        Actions.fillInValue(addressLine1, faker.address().streetAddressNumber());
        Actions.fillInValue(addressLine2, faker.address().streetName());
        Actions.fillInValue(addressLine3, faker.address().cityName());
        Actions.fillInValue(addressLine4, faker.address().state());
        newPatient.setPostCode(faker.address().zipCode());
        Actions.fillInValue(postcode, newPatient.getPostCode());
    }

    public void fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason() {
        Wait.forElementToBeDisplayed(driver, firstName);
        newPatient.setFirstName(faker.name().firstName());
        newPatient.setLastName(faker.name().lastName());
        newPatient.setDay(String.valueOf(faker.number().numberBetween(1, 31)));
        newPatient.setMonth(String.valueOf(faker.number().numberBetween(1, 12)));
        newPatient.setYear(String.valueOf(faker.number().numberBetween(1900, 2019)));
        Actions.fillInValue(firstName, newPatient.getFirstName());
        Actions.fillInValue(familyName, newPatient.getLastName());
        Actions.fillInValue(dateOfBirth, newPatient.getDay() + "/" + newPatient.getMonth() + "/" + newPatient.getYear());
        editDropdownField(administrativeGenderButton, "Male");
        editDropdownField(lifeStatusButton, "Alive");
        Actions.fillInValue(hospitalNumber, faker.numerify("A#R##BB##"));
    }

    public void editDropdownField(WebElement element, String value) {

        try {
            Actions.retryClickAndIgnoreElementInterception(driver, element);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
            //Click.element(driver, element);
            Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + value + "']")));
        } catch (Exception exp) {
            Debugger.println("Oops unable to locate drop-down element value : " + value + ":" + exp);
        }

    }

    public void fillInAllMandatoryPatientDetailsWithoutNhsNumber(String reason) {
        fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason();
        selectMissingNhsNumberReason(reason);
    }

    public void selectMissingNhsNumberReason(String reason) {
        editDropdownField(noNhsNumberReasonDropdown, reason);
    }

    public void clickSavePatientDetailsToNGISButton() {
        Actions.clickElement(driver, savePatientDetailsToNGISButton);
    }

    public void patientIsCreated() {
        Wait.forElementToBeDisplayed(driver, successNotification);
        Assert.assertEquals("Details saved", Actions.getText(successNotification));
    }

    public void clickStartReferralButton() {
        try {
            Wait.forElementToBeDisplayed(driver, startReferralButton);
            Actions.clickElement(driver, startReferralButton);
            Wait.forElementToDisappear(driver, By.xpath(startReferralButtonLocator));
        }catch(Exception exp){
            Debugger.println("PatientDetailsPage: clickStartReferralButton. Exception:"+exp);
            SeleniumLib.takeAScreenShot("StartReferralButton.jpg");
            Assert.assertFalse("PatientDetailsPage: clickStartReferralButton. Exception:"+exp,true);
        }
    }

    public void clickStartNewReferralButton() {
        Wait.forElementToBeDisplayed(driver, startNewReferralButton);
        Actions.clickElement(driver, startNewReferralButton);
        Wait.forElementToDisappear(driver, By.xpath(startANewReferralButtonLocator));
    }

    public void clinicalIndicationIDMissingBannerIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, patientDetailsnotificationBanner);
        Assert.assertTrue(!Actions.getText(patientDetailsnotificationBanner).isEmpty());
    }

    public void startReferralButtonIsDisabled() {
        Wait.forElementToBeDisplayed(driver, startReferralButton);
        Assert.assertTrue(!startReferralButton.isEnabled());
    }

    public void clickGoBackToPatientSearchLink() {
        Click.element(driver, goBackToPatientSearchLink);
    }

    public void clickTestDirectoryLinkFromNotificationBanner() {
        Wait.forElementToBeDisplayed(driver, patientDetailsnotificationBanner);
        Actions.clickElement(driver, patientDetailsnotificationBanner.findElement(By.tagName("a")));
    }

    public boolean nhsNumberFieldIsDisabled() {
        Wait.forElementToBeDisplayed(driver, title);
        Debugger.println("For normal user, NHSNumber field is disabled and set to FALSE:  " + nhsNumber.isEnabled());
        return nhsNumber.isEnabled();
    }

    public boolean nhsNumberFieldIsEnabled() {
        Wait.forElementToBeDisplayed(driver, title);
        Debugger.println("For a Super user, NHSNumber field is enabled and set to True:  " + nhsNumber.isEnabled());
        return nhsNumber.isEnabled();
    }

    public boolean editAndAddNhsNumberAsSuperUser() {
        Wait.forElementToBeDisplayed(driver, nhsNumber);
        Actions.clearField(nhsNumber);  //nhsNumber.clear();
        nhsNumber.sendKeys(NgisPatientTwo.NHS_NUMBER);
        return true;
    }

    public void nhsNumberAndDOBFieldsArePrePopulatedInNewPatientPage() {
        String DOB = PatientSearchPage.testData.getDay() + "/" + PatientSearchPage.testData.getMonth() + "/" + PatientSearchPage.testData.getYear();
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

    public void verifyAndClickOnTheReferralCardOnPatientDetailsPage() {

        Wait.forElementToBeDisplayed(driver, referralCard, 70);
        if (referralListCards.size() > 0)
            referralListCards.get(0).click();
        else {
            Debugger.println("No referral card found");
        }
    }

    public boolean verifyMaxAllowedValuesInEthnicityField(int maxAllowedValues){
        try {
            Wait.forElementToBeDisplayed(driver, title);
            Actions.retryClickAndIgnoreElementInterception(driver, ethnicityButton);
            int numberOfElements = dropdownValue.findElements(By.xpath(dropDownValuesFromLocator)).size();
            Debugger.println(" Number of items displayed in Ethnicity Field  : " + numberOfElements);
            return numberOfElements <= maxAllowedValues;
        } catch (Exception exp) {
            Debugger.println("Oops unable to locate drop-down element value -> " + exp);
            return false;
        }
    }

    public boolean patientReferralsAreDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, referralLink);
            Wait.forElementToBeDisplayed(driver, referralCard);
            return true;
        }catch (Exception exp){
            Debugger.println("No Referrals are found for the patient");
            return false;
        }
    }

    public boolean verifyReferralStatus(String expectedStatus) {
        Wait.forElementToBeDisplayed(driver, referralStatus);
        return expectedStatus.equalsIgnoreCase(Actions.getText(referralStatus));
    }

    public boolean verifyReferralReason(String expectedReason) {
        Wait.forElementToBeDisplayed(driver, referralCancelReason);
        return expectedReason.equalsIgnoreCase(Actions.getText(referralCancelReason));
    }

    public void fillInAllFieldsNewPatientDetailsExceptNHSNumber(String reason) {
        Wait.forElementToBeDisplayed(driver, title);
        newPatient.setTitle("Mr");
        title.sendKeys("Mr"); // OR //Actions.fillInValue(title, "MR");
        String firstNameValue = faker.name().firstName();
        String lastNameValue = faker.name().lastName();
        newPatient.setFirstName(firstNameValue);
        Actions.fillInValue(firstName, newPatient.getFirstName());

        newPatient.setLastName(lastNameValue);
        Actions.fillInValue(familyName, newPatient.getLastName());

        String dayOfBirth = PatientSearchPage.testData.getDay();
        String monthOfBirth = PatientSearchPage.testData.getMonth();
        String yearOfBirth = PatientSearchPage.testData.getYear();

        newPatient.setDay(dayOfBirth);
        newPatient.setMonth(monthOfBirth);
        newPatient.setYear(yearOfBirth);

        String nhsNumber = RandomDataCreator.generateRandomNHSNumber();
        newPatient.setNhsNumber(nhsNumber);

        String gender = "Male";
        newPatient.setGender(gender);
        editDropdownField(administrativeGenderButton, gender);
        editDropdownField(lifeStatusButton, "Alive");
        Actions.fillInValue(dateOfDeath, "01/01/2015");
        editDropdownField(ethnicityButton, "A - White - British");
        String hospitalId = faker.numerify("A#R##BB##");
        selectMissingNhsNumberReason(reason);
        if (reason.equalsIgnoreCase("Other - provide explanation")) {
            Wait.forElementToBeDisplayed(driver, otherReasonExplanation);
            otherReasonExplanation.sendKeys(faker.numerify("misplaced my NHS Number"));
        }
        Actions.fillInValue(hospitalNumber, hospitalId);
        Actions.fillInValue(addressLine0, faker.address().buildingNumber());
        Actions.fillInValue(addressLine1, faker.address().streetAddressNumber());
        Actions.fillInValue(addressLine2, faker.address().streetName());
        Actions.fillInValue(addressLine3, faker.address().cityName());
        Actions.fillInValue(addressLine4, faker.address().state());
        newPatient.setPostCode(faker.address().zipCode());
        newPatient.setHospitalNumber(hospitalId);
        String postcodeValue = newPatient.getPostCode();
        Actions.fillInValue(postcode, postcodeValue);

        Debugger.println(" Newly created patient info   : " + firstNameValue + " " + lastNameValue + " " + dayOfBirth  + " " + monthOfBirth + " " + yearOfBirth + " " + gender + " " + postcodeValue);
        Debugger.println(" Newly created patient object1: " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode());
    }

    public NewPatient getNewlyCreatedPatientData(){
        Debugger.println(" Newly created patient object2: " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode());
        return newPatient;
    }
}
