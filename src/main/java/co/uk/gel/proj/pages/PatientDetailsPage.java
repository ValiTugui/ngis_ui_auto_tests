package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.TestDataProvider.NgisPatientTwo;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

import static co.uk.gel.proj.util.RandomDataCreator.getRandomUKPostCode;

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
    public WebElement otherReasonExplanation;

    @FindBy(css = "h1[class*='page-title']")
    public WebElement pageTitle;

    @FindBy(css = "p[class*='sub-title']")
    public WebElement subPageTitle;

    @FindBy(css = "label[for*='dateOfBirth']")
    public WebElement dateOfBirthLabel;

    @FindBy(css = "label[for*='title']")
    public WebElement titleLabel;

    @FindBy(css = "label[for*='firstName']")
    public WebElement firstnameLabel;

    @FindBy(css = "label[for*='familyName']")
    public WebElement familyNameLabel;

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

    @FindBy(css = "*[data-testid*='notification-warning']")     //@FindBy(css = "*[class*='notification--warning']")
    public WebElement patientDetailsnotificationBanner;

    @FindBy(xpath = "//a[text()='Test Directory']")
    public WebElement testDirectoryLinkOnBanner;

    @FindBy(xpath = "//div[contains(@data-testid,'notification-warning')]")
    public WebElement textOnPatientDetailsNotificationBanner;

    @FindBy(xpath = "//div[contains(@class,'notification--warning')]/. //div[contains(@class,'notification__text')]")
    public WebElement textOnPatientDetailsNotificationBanner1;

    @FindBy(xpath = "//label[contains(@for,'lifeStatus')]//following::div")
    public WebElement lifeStatusButton;

    @FindBy(xpath = "//label[contains(@for,'ethnicity')]//following::div")
    public WebElement ethnicityButton;

    @FindBy(xpath = "//label[contains(@for,'relationship')]//following::div")
    public WebElement relationshipButton;

    @FindBy(xpath = "(//label[contains(@for,'noNhsNumberReason')]//following::div)[4]")
    public WebElement noNhsNumberReasonDropdown;

    @FindBy(css = "label[for*='noNhsNumberReason']")
    public WebElement noNhsNumberReasonLabel;

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

    @FindBy(xpath = "//button[text()='Start referral']")  //@FindBy(xpath = "//button[contains(@class,'button--medium') and @type='button']")
    public WebElement startReferralButton;

    @FindBy(xpath = "//button[text()='Start a new referral']")
    public WebElement startNewReferralButton;

    @FindBy(css = "*[data-testid*='notification-success']")
    public WebElement successNotification;

    @FindBy(xpath = "//*[text()='Go back to patient search']")
    public WebElement goBackToPatientSearchLink;
    
    @FindBy(css = "a[class*='referral-list']")
    public List<WebElement> referralListCards;

    @FindBy(css = "*[class*='referral-list__link']")
    public WebElement referralLink;

    @FindBy(css = "div[class*='css-1jwqj7b']") //@FindBy(css = "div[class*='referral-card']")
    public WebElement referralCard;

    @FindBy(css = "*[data-testid*='referral-card-status']")  //@FindBy(css = "*[class*='badge']")
    public WebElement referralStatus;

    @FindBy(xpath = "(//a[contains(@class,'referral-list')])[1]/.//*[text()='Relationship to proband']")
    public List<WebElement> referralProbandRelationShip;

    @FindBy(xpath = "(//a[contains(@class,'referral-list')])[1]/.//*[text()='Full Sibling']")
    public WebElement referralProbandRelationShipStatus;

    @FindBy(css = "*[class*='referral-card__cancel-reason']")
    public WebElement referralCancelReason;

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

    @FindBy(xpath = "//button[text()='Yes']")
    public WebElement yesButton;

    @FindBy(xpath = "//label[@for='ethnicity']/..//div[contains(@class,'option')]/span/span")
    public List<WebElement> ethnicityValues;

    @FindBy(css = "*[class*='error-message__text']")
    public List<WebElement> errorMessages;

    @FindBy(xpath = "(//div[contains(@class,'indicatorContainer')]//*[name()='svg']//*[name()='path'])[5]")
    public WebElement clearEthnicityDropDownValue;

    String startReferralButtonLocator = "//button[contains(@class,'submit-button') and @type='button']";
    String startANewReferralButtonLocator = "//button[contains(@class,'submit-button') and text()='Start a new referral']";
    String dropDownValuesFromLocator = "//span[text()[('^[A-Z ]*-*')]]";

    @FindBy(xpath = "//button[text()='Add new patient to referral']")
    public WebElement addNewPatientToReferral;


    public boolean patientDetailsPageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "/patient-details");
        //Wait.forElementToBeDisplayed(driver, startReferralButton);
        return true;
    }

    public void newPatientPageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "/new-patient");
    }

    public void fillInNewPatientDetailsWithoutAddressFields() {

        Wait.forElementToBeDisplayed(driver, title);
        newPatient.setTitle("Mr");
        title.sendKeys("Mr"); // OR //Actions.fillInValue(title, "MR");

        newPatient.setFirstName(TestUtils.getRandomFirstName());
        Actions.fillInValue(firstName, newPatient.getFirstName());

        newPatient.setLastName(TestUtils.getRandomLastName());
        Actions.fillInValue(familyName, newPatient.getLastName());

        String dayOfBirth = PatientSearchPage.testData.getDay();
        String monthOfBirth = PatientSearchPage.testData.getMonth();
        String yearOfBirth = PatientSearchPage.testData.getYear();

        // Date of Birth field will always be empty when accessing the New Patient "test-order/new-patient" directly
        if (Actions.getValue(dateOfBirth).isEmpty())
        {
            Debugger.println("Before: Date of Birth field empty: " + Actions.getValue(dateOfBirth));
            dayOfBirth = String.valueOf(faker.number().numberBetween(10, 31));
            monthOfBirth = String.valueOf(faker.number().numberBetween(10, 12));
            yearOfBirth = String.valueOf(faker.number().numberBetween(1900, 2019));
            Actions.fillInValue(dateOfBirth, dayOfBirth + "/" + monthOfBirth + "/" + yearOfBirth);
            Debugger.println("After: Date of Birth field empty: " + Actions.getValue(dateOfBirth));
        }

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
        newPatient.setPostCode(getRandomUKPostCode());
        Actions.fillInValue(postcode, newPatient.getPostCode());
    }

    public void fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason() {
        Wait.forElementToBeDisplayed(driver, firstName);
        newPatient.setFirstName(TestUtils.getRandomFirstName());
        newPatient.setLastName(TestUtils.getRandomLastName());
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
       try {
           if (!Wait.isElementDisplayed(driver, noNhsNumberReasonDropdown, 15)) {
               Actions.scrollToTop(driver);
           }
           Actions.retryClickAndIgnoreElementInterception(driver, noNhsNumberReasonDropdown);
           Actions.selectValueFromDropdown(noNhsNumberReasonDropdown,reason);
       }catch(Exception exp) {
           Debugger.println("Patient new page  : Exception from selecting noNhsNumberReasonDropDown: " + exp);
           SeleniumLib.takeAScreenShot("noNhsNumberReasonDropDown.jpg");
          }
       }

    public void clickSavePatientDetailsToNGISButton() {
        Wait.forElementToBeClickable(driver,savePatientDetailsToNGISButton);
        Click.element(driver, savePatientDetailsToNGISButton);
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
        } catch (Exception exp) {
            Debugger.println("PatientDetailsPage: clickStartReferralButton. Exception:" + exp);
            SeleniumLib.takeAScreenShot("StartReferralButton.jpg");
            Assert.assertFalse("PatientDetailsPage: clickStartReferralButton. Exception:" + exp, true);
        }
    }

    public void clickStartNewReferralButton() {
        try {
        Wait.forElementToBeDisplayed(driver, startNewReferralButton);
        Actions.clickElement(driver, startNewReferralButton);
        Wait.forElementToDisappear(driver, By.xpath(startANewReferralButtonLocator));
        } catch (Exception exp) {
            Debugger.println("PatientDetailsPage: clickStartNewReferralButton. Exception:" + exp);
            SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
            Assert.assertFalse("PatientDetailsPage: clickStartNewReferralButton. Exception:" + exp, true);
        }
    }

    public void clinicalIndicationIDMissingBannerIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, patientDetailsnotificationBanner);
        Assert.assertTrue(!Actions.getText(patientDetailsnotificationBanner).isEmpty());
    }

    public void startReferralButtonIsDisabled() {
        Wait.forElementToBeDisplayed(driver, startReferralButton);
        Assert.assertTrue(!startReferralButton.isEnabled());
    }

    public void startNewReferralButtonIsDisabled() {
        Wait.forElementToBeDisplayed(driver, startNewReferralButton);
        Assert.assertTrue(!startNewReferralButton.isEnabled());
    }

    public void clickTheGoBackLink(String expectedGoBackToPatientSearch) {
        By goBackLink = By.xpath("//*[text()= \"" + expectedGoBackToPatientSearch + "\"]");
        Actions.retryClickAndIgnoreElementInterception(driver,driver.findElement(goBackLink));
    }


    public boolean clickTheLinkOnNotificationBanner(String expectedLinkonBanner) {
        Wait.forElementToBeDisplayed(driver, patientDetailsnotificationBanner);
        try {
            By linkOnBanner;
            linkOnBanner =  By.xpath("//*[text()= \"" + expectedLinkonBanner + "\"]");
            Wait.forElementToBeDisplayed(driver, driver.findElement(linkOnBanner), 30);
            if (!Wait.isElementDisplayed(driver, driver.findElement(linkOnBanner), 10)) {
                Debugger.println("Test Directory Link is not displayed even after waiting period...Failing.");
                SeleniumLib.takeAScreenShot("testDirectoryLinkOnBanner.jpg");
                return false;
            }
//            Click.element(driver, driver.findElement(linkOnBanner));
            Actions.retryClickAndIgnoreElementInterception(driver,driver.findElement(linkOnBanner));
            return true;
        } catch (Exception exp) {
            Debugger.println("Test Directory Link is not shown on banner..." + exp);
            SeleniumLib.takeAScreenShot("testDirectoryLinkOnBanner.jpg");
            return false;
        }
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
        String firstNameValue = TestUtils.getRandomFirstName();
        String lastNameValue = TestUtils.getRandomLastName();
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

        selectMissingNhsNumberReason(reason);
        if (reason.equalsIgnoreCase("Other - provide explanation")) {
            Wait.forElementToBeDisplayed(driver, otherReasonExplanation);
            otherReasonExplanation.sendKeys(faker.numerify("misplaced my NHS Number"));
        }
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
        newPatient.setPostCode(getRandomUKPostCode());
        newPatient.setHospitalNumber(hospitalId);
        String postcodeValue = newPatient.getPostCode();
        Actions.fillInValue(postcode, postcodeValue);

        Debugger.println(" Newly created patient info   : " + firstNameValue + " " + lastNameValue + " " + dayOfBirth  + " " + monthOfBirth + " " + yearOfBirth + " " + gender + " " + postcodeValue);
        Debugger.println(" Newly created patient object1: " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode());
    }

    public void fillInAllFieldsNewPatientDetailsWithNHSNumber(String patientNameWithSpecialCharacters) {

        Wait.forElementToBeDisplayed(driver, title);
        String patientTitle = "Mr";
        newPatient.setTitle(patientTitle);
        title.sendKeys(patientTitle);

        String firstNameValue;
        String lastNameValue;

        if (patientNameWithSpecialCharacters.equalsIgnoreCase("SPECIAL_CHARACTERS")) {
            firstNameValue = TestUtils.getRandomFirstName().replaceFirst("[a-z]", "é");
            lastNameValue = TestUtils.getRandomLastName().concat("müller");
        } else {
            firstNameValue = TestUtils.getRandomFirstName();
            lastNameValue = TestUtils.getRandomLastName();
        }

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

        String gender = "Male";
        newPatient.setGender(gender);

        if(!Wait.isElementDisplayed(driver, administrativeGenderButton,15)){
            Actions.scrollToTop(driver);
        }
        editDropdownField(administrativeGenderButton, gender);
        editDropdownField(lifeStatusButton, "Alive");
        Actions.fillInValue(dateOfDeath, "01/01/2015");
        editDropdownField(ethnicityButton, "A - White - British");

        String patientNhsNumber = RandomDataCreator.generateRandomNHSNumber();
        newPatient.setNhsNumber(patientNhsNumber);
        Actions.clickElement(driver, yesButton);
        Actions.clickElement(driver, nhsNumber);
        Actions.fillInValue(nhsNumber, patientNhsNumber);
        Actions.clickElement(driver, nhsNumberLabel);

        String hospitalId = faker.numerify("A#R##BB##");
        Actions.fillInValue(hospitalNumber, hospitalId);
        Actions.fillInValue(addressLine0, faker.address().buildingNumber());
        Actions.fillInValue(addressLine1, faker.address().streetAddressNumber());
        Actions.fillInValue(addressLine2, faker.address().streetName());
        Actions.fillInValue(addressLine3, faker.address().cityName());
        Actions.fillInValue(addressLine4, faker.address().state());
        newPatient.setPostCode(getRandomUKPostCode());
        newPatient.setHospitalNumber(hospitalId);
        String postcodeValue = newPatient.getPostCode();
        Actions.fillInValue(postcode, postcodeValue);

        Debugger.println(" Newly created patient info   : " + patientTitle + " " + firstNameValue + " " + lastNameValue + " " + dayOfBirth + " " + monthOfBirth + " " + yearOfBirth + " " + gender + " " + postcodeValue);
        Debugger.println(" Newly created patient object1: " + newPatient.getTitle() + " " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode());
    }

    public NewPatient getNewlyCreatedPatientData(){
        Debugger.println(" Newly created patient object2: " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode());
        return newPatient;
    }

    public void editPatientGenderLifeStatusAndEthnicity(String gender, String lifeStatus, String ethnicity) {
        editDropdownField(administrativeGenderButton, gender);
        editDropdownField(lifeStatusButton, lifeStatus);
        editDropdownField(ethnicityButton, ethnicity);
    }

    public void addPatientEthnicity(String ethnicity) {
        editDropdownField(ethnicityButton, ethnicity);
    }

    public void clickAddDetailsToNGISButton() {
        Wait.forElementToBeClickable(driver, addDetailsToNGISButton);
        Click.element(driver, addDetailsToNGISButton);
        Wait.forElementToBeDisplayed(driver, successNotification);
    }

    public void clickUpdateNGISRecordButton() {
        Wait.forElementToBeClickable(driver,updateNGISRecordButton);
        Click.element(driver, updateNGISRecordButton);
    }

      public String getNotificationMessageForPatientCreatedOrUpdated() {
        Wait.forElementToBeDisplayed(driver, successNotification);
        return Actions.getText(successNotification);
    }


    public boolean verifyTheElementsOfPatientDetailsPageWithNhsNumber() {

        Wait.forElementToBeDisplayed(driver, ethnicityButton);
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
        nhsNumber.isDisplayed();
        nhsNumberLabel.isDisplayed();
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

    public List<String> getTheEthnicityDropDownValues() {
        Wait.forElementToBeClickable(driver, ethnicityButton);
        Actions.clickElement(driver, ethnicityButton);
        List<String> actualEthnicityValues = new ArrayList<String>();
        for (WebElement ethnicityValue : ethnicityValues) {
            actualEthnicityValues.add(ethnicityValue.getText().trim());
        }
        Debugger.println("Actual ethnicity values: " + actualEthnicityValues);
        return actualEthnicityValues;
    }

    public void fillInLastName() {
        Actions.fillInValue(familyName, TestUtils.getRandomLastName());
    }

    public void createNewFamilyMember(NGISPatientModel familyMember) {
        selectMissingNhsNumberReason(familyMember.getNO_NHS_REASON());
        familyMember.setTITLE("Mr");
        familyMember.setFIRST_NAME(TestUtils.getRandomFirstName());
        familyMember.setLAST_NAME(TestUtils.getRandomLastName());
        familyMember.setHOSPITAL_NO(faker.numerify("A#R##BB##"));
        familyMember.setADDRESS_LINE0(faker.address().buildingNumber());
        familyMember.setADDRESS_LINE1(faker.address().streetAddressNumber());
        familyMember.setADDRESS_LINE2(faker.address().streetName());
        familyMember.setADDRESS_LINE3(faker.address().cityName());
        familyMember.setADDRESS_LINE4(faker.address().state());
        familyMember.setPOST_CODE(RandomDataCreator.getRandomUKPostCode());

        Wait.forElementToBeDisplayed(driver, title);
        title.sendKeys(familyMember.getTITLE());
        Actions.fillInValue(firstName, familyMember.getFIRST_NAME());
        Actions.fillInValue(familyName, familyMember.getLAST_NAME());
        editDropdownField(administrativeGenderButton, familyMember.getGENDER());
        editDropdownField(lifeStatusButton, "Alive");
        editDropdownField(ethnicityButton, familyMember.getETHNICITY());
        editDropdownField(relationshipButton, familyMember.getRELATIONSHIP_TO_PROBAND());
        Actions.fillInValue(hospitalNumber,familyMember.getHOSPITAL_NO() );
        //Address
        Actions.fillInValue(addressLine0,familyMember.getADDRESS_LINE0() );
        Actions.fillInValue(addressLine1, familyMember.getADDRESS_LINE1());
        Actions.fillInValue(addressLine2, familyMember.getADDRESS_LINE2());
        Actions.fillInValue(addressLine3, familyMember.getADDRESS_LINE3());
        Actions.fillInValue(addressLine4, familyMember.getADDRESS_LINE4());
        Actions.fillInValue(postcode, familyMember.getPOST_CODE());
        //Adding Family member to a list for later stage verification
        FamilyMemberDetailsPage.addFamilyMemberToList(familyMember);
        Debugger.println("Family Member Added to List: NHS:"+familyMember.getNHS_NUMBER()+",DOB:"+familyMember.getDATE_OF_BIRTH()+",LNAME:"+familyMember.getLAST_NAME()+",FNAME:"+familyMember.getFIRST_NAME());
        Actions.clickElement(driver, addNewPatientToReferral);
    }
    public void fillInNHSNumber(){
        Actions.fillInValue(nhsNumber, newPatient.getNhsNumber());
    }

    public boolean createNewPatientReferral(NGISPatientModel referralDetails) {
        try {
            newPatientPageIsDisplayed();
            selectMissingNhsNumberReason(referralDetails.getNO_NHS_REASON());
            if (referralDetails.getNO_NHS_REASON().equalsIgnoreCase("Other - provide explanation")) {
                Wait.forElementToBeDisplayed(driver, otherReasonExplanation);
                otherReasonExplanation.sendKeys(faker.numerify("misplaced my NHS Number"));
            }
            if (referralDetails.getTITLE() == null || referralDetails.getTITLE().isEmpty()) {
                referralDetails.setTITLE("Mr");
            }
            if (referralDetails.getFIRST_NAME() == null || referralDetails.getFIRST_NAME().isEmpty()) {
                referralDetails.setFIRST_NAME(TestUtils.getRandomFirstName());
            }
            if (referralDetails.getLAST_NAME() == null || referralDetails.getLAST_NAME().isEmpty()) {
                referralDetails.setLAST_NAME(TestUtils.getRandomLastName());
            }
            if (referralDetails.getHOSPITAL_NO() == null || referralDetails.getHOSPITAL_NO().isEmpty()) {
                referralDetails.setHOSPITAL_NO(faker.numerify("A#R##BB##"));
            }
            if (referralDetails.getETHNICITY() == null || referralDetails.getETHNICITY().isEmpty()) {
                referralDetails.setETHNICITY("A - White - British");
            }
            if (referralDetails.getADDRESS_LINE0() == null || referralDetails.getADDRESS_LINE0().isEmpty()) {
                referralDetails.setADDRESS_LINE0(faker.address().buildingNumber());
                referralDetails.setADDRESS_LINE0(faker.address().buildingNumber());
                referralDetails.setADDRESS_LINE1(faker.address().streetAddressNumber());
                referralDetails.setADDRESS_LINE2(faker.address().streetName());
                referralDetails.setADDRESS_LINE3(faker.address().cityName());
                referralDetails.setADDRESS_LINE4(faker.address().state());
                referralDetails.setPOST_CODE(RandomDataCreator.getRandomUKPostCode());
            }
            Wait.forElementToBeDisplayed(driver, title);
            title.sendKeys(referralDetails.getTITLE());
            Actions.fillInValue(firstName, referralDetails.getFIRST_NAME());
            Actions.fillInValue(familyName, referralDetails.getLAST_NAME());
            editDropdownField(administrativeGenderButton, referralDetails.getGENDER());
            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(ethnicityButton, referralDetails.getETHNICITY());
            Actions.fillInValue(hospitalNumber, referralDetails.getHOSPITAL_NO());
            //Address
            Actions.fillInValue(addressLine0, referralDetails.getADDRESS_LINE0());
            Actions.fillInValue(addressLine1, referralDetails.getADDRESS_LINE1());
            Actions.fillInValue(addressLine2, referralDetails.getADDRESS_LINE2());
            Actions.fillInValue(addressLine3, referralDetails.getADDRESS_LINE3());
            Actions.fillInValue(addressLine4, referralDetails.getADDRESS_LINE4());
            Actions.fillInValue(postcode, referralDetails.getPOST_CODE());
            //Adding referral to to a list for later stage verification, if needed
            FamilyMemberDetailsPage.addFamilyMemberToList(referralDetails);
            Debugger.println("Referral Added to List: NHS:" + referralDetails.getNHS_NUMBER() + ",DOB:" + referralDetails.getDATE_OF_BIRTH() + ",LNAME:" + referralDetails.getLAST_NAME() + ",FNAME:" + referralDetails.getFIRST_NAME());
            //Adding Patient to NGIS
            clickSavePatientDetailsToNGISButton();
            patientIsCreated();
            clickStartNewReferralButton();
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in creating new Referral:"+exp);
            SeleniumLib.takeAScreenShot("NewReferralCreationError.jpg");
            return false;
        }
    }
    public boolean startReferral(){
        try{

            // Check condition for different scenarios when referral submit button is displayed
            if (addDetailsToNGISButtonList.size() > 0) {
                Debugger.println("Add Patient Details button shown");
                    addDetailsToNGISButton.click();
                    Wait.forElementToBeDisplayed(driver,successNotification);
                clickStartReferralButton();
            } else if (updateNGISRecordButtonList.size() > 0) {
                Debugger.println("Update Patient Details button shown");
                clickStartReferralButton();
            } else if (savePatientDetailsToNGISButtonList.size() > 0) {
                Debugger.println("Save Patient Details button shown");
                clickSavePatientDetailsToNGISButton();
                patientIsCreated();
                clickStartNewReferralButton();
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in starting the Referral: "+exp);
            SeleniumLib.takeAScreenShot("startReferralError.jpg");
            return false;
        }
    }
}
