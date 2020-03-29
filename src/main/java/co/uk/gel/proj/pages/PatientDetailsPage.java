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
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.*;
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

    @FindBy(xpath = "//div[contains(@data-testid,'notification-warning')]")
    public WebElement textOnPatientDetailsNotificationBanner;

    @FindBy(xpath = "//label[contains(@for,'lifeStatus')]//following::div")
    public WebElement lifeStatusButton;

    @FindBy(xpath = "//label[contains(@for,'ethnicity')]//following::div")
    public WebElement ethnicityButton;

    @FindBy(xpath = "//label[contains(@for,'relationship')]//following::div")
    public WebElement relationshipButton;

    @FindBy(xpath = "(//label[contains(@for,'noNhsNumberReason')]//following::div)[4]")
    public WebElement noNhsNumberReasonDropdown;

    @FindBy(xpath = "//button[text()='Update NGIS record']")
    public WebElement updateNGISRecordButton;

    @FindBy(xpath = "//button[text()='Add details to NGIS']")
    public WebElement addDetailsToNGISButton;

    @FindBy(xpath = "//button[text()='Save patient details to NGIS']")
    public WebElement savePatientDetailsToNGISButton;

    @FindBy(xpath = "//button[text()='Create record']")
    public WebElement createRecord;


    @FindBy(xpath = "//button[text()='Update NGIS record']")
    public List<WebElement> updateNGISRecordButtonList;

    @FindBy(xpath = "//button[text()='Save patient details to NGIS']")
    public List<WebElement> savePatientDetailsToNGISButtonList;

    @FindBy(xpath = "//button[text()='Add details to NGIS']")
    public List<WebElement> addDetailsToNGISButtonList;

    @FindBy(xpath = "//button[text()='Start referral']")  //@FindBy(xpath = "//button[contains(@class,'button--medium') and @type='button']")
    public WebElement startReferralButton;

    @FindBy(xpath = "//button[text()='Start new referral']")
    public WebElement startNewReferralButton;

    //@FindBy(css = "*[data-testid*='notification-success']")
    @FindBy(xpath = "//div[@data-testid='notification-success']//span")
    public WebElement successNotification;

    @FindBy(xpath = "//*[text()='Go back to patient search']")
    public WebElement goBackToPatientSearchLink;
    
    @FindBy(css = "a[class*='referral-list']")
    public List<WebElement> referralListCards;

    @FindBy(css = "*[class*='referral-list__link']")
    public WebElement referralLink;

    @FindBy(css = "div[class*='css-1jwqj7b']") //@FindBy(css = "div[class*='referral-card']")
    public WebElement referralCard;

    //@FindBy(css = "*[data-testid*='referral-card-status']")  //@FindBy(css = "*[class*='badge']")
    @FindBy(xpath = "//div[contains(@data-testid,'referral-card-header')]/div//child::span[contains(@class,'child-element')]")
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

    @FindBy(xpath = "(//div[contains(@class,'indicatorContainer')]//*[name()='svg']//*[name()='path'])[1]")
    public WebElement clearGenderDropDownValue;

    @FindBy(xpath = "(//div[contains(@class,'indicatorContainer')]//*[name()='svg']//*[name()='path'])[3]")
    public WebElement clearLifeStatusDropDownValue;

    @FindBy(xpath = "(//div[contains(@class,'indicatorContainer')]//*[name()='svg']//*[name()='path'])[2]")
    public WebElement clearLifeStatusDropDown;


    String startReferralButtonLocator = "//button[contains(@class,'submit-button') and @type='button']";
    String startANewReferralButtonLocator = "//button[contains(@class,'submit-button') and text()='Start a new referral']";
    String dropDownValuesFromLocator = "//span[text()[('^[A-Z ]*-*')]]";

    @FindBy(xpath = "//button[text()='Create NGIS record']")
    public WebElement addNewPatientToReferral;

    String relationshipToProbandType = "//span[contains(text(),'dummyOption')]/ancestor::div[contains(@class,'container')]";

    @FindBy(xpath = "//div[contains(text(),'Created and submitted referrals')]")
    WebElement createdAndSubmittedReferralsTitle;

    @FindBy(xpath = "//span[text()='Relationship to proband']/ancestor::div[contains(@class,'css-')]/ancestor::div[contains(@class,'css-')]//span[@data-testid='referral-card-id']")
    List<WebElement> relationshipToProbandReferralID;

    @FindBy(xpath = "//span[@data-testid='referral-card-id']")
    List<WebElement> referralIDOfCreatedReferrals;

    @FindBy(xpath = "//a[contains(@class,'styles_referral-list__link__')]")
    List<WebElement> submittedReferralCardsList;

    public boolean patientDetailsPageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "/patient-details");
        //Wait.forElementToBeDisplayed(driver, startReferralButton);
        return true;
    }

    public boolean newPatientPageIsDisplayed() {
        try {
            Wait.forURLToContainSpecificText(driver, "/new-patient");
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from newPatientPageIsDisplayed:"+exp);
            SeleniumLib.takeAScreenShot("newPatientPageIsDisplayed.jpg");
            return false;
        }
    }

    public boolean fillInNewPatientDetailsWithoutAddressFields() {
        try {
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
            if (Actions.getValue(dateOfBirth).isEmpty()) {
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
            selectGender(administrativeGenderButton, gender);
            editDropdownField(lifeStatusButton, "Alive");
            Actions.fillInValue(dateOfDeath, "01/01/2015");
            editDropdownField(ethnicityButton, "A - White - British");
            Actions.fillInValue(hospitalNumber, faker.numerify("A#R##BB##"));
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from fillInNewPatientDetailsWithoutAddressFields:"+exp);
            SeleniumLib.takeAScreenShot("fillInNewPatientDetailsWithoutAddressFields.jpg");
            return false;
        }
    }

    public boolean fillInAllFieldsNewPatientDetailsWithOutNhsNumber(String reason) {
          if(!selectMissingNhsNumberReason(reason)){
                return false;
          }
          return fillInAllNewPatientDetails();
     }

    public boolean fillInAllNewPatientDetails() {
        try {
            if(!fillInNewPatientDetailsWithoutAddressFields()){
                return false;
            }
            List<String> patientAddressDetails = new ArrayList<String>();
            patientAddressDetails.add(faker.address().buildingNumber());
            patientAddressDetails.add(faker.address().streetAddressNumber());
            patientAddressDetails.add(faker.address().streetName());
            patientAddressDetails.add(faker.address().cityName());
            patientAddressDetails.add(faker.address().state());
            newPatient.setPatientAddress(patientAddressDetails);

            Actions.fillInValue(addressLine0, patientAddressDetails.get(0));
            Actions.fillInValue(addressLine1, patientAddressDetails.get(1));
            Actions.fillInValue(addressLine2, patientAddressDetails.get(2));
            Actions.fillInValue(addressLine3, patientAddressDetails.get(3));
            Actions.fillInValue(addressLine4, patientAddressDetails.get(4));
            newPatient.setPostCode(getRandomUKPostCode());
            Actions.fillInValue(postcode, newPatient.getPostCode());
            //Debugger.println("Expected patient address - List " + patientAddressDetails + " : " + newPatient.getPatientAddress());
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from fillInAllNewPatientDetails:"+exp);
            SeleniumLib.takeAScreenShot("fillInAllNewPatientDetails.jpg");
            return false;
        }
    }

    public boolean fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason(String reason) {
        try {
            Wait.forElementToBeDisplayed(driver, firstName);
            newPatient.setFirstName(TestUtils.getRandomFirstName());
            newPatient.setLastName(TestUtils.getRandomLastName());
            newPatient.setDay(String.valueOf(faker.number().numberBetween(1, 31)));
            newPatient.setMonth(String.valueOf(faker.number().numberBetween(1, 12)));
            newPatient.setYear(String.valueOf(faker.number().numberBetween(1900, 2019)));
            Actions.fillInValue(firstName, newPatient.getFirstName());
            Actions.fillInValue(familyName, newPatient.getLastName());
            Actions.fillInValue(dateOfBirth, newPatient.getDay() + "/" + newPatient.getMonth() + "/" + newPatient.getYear());
            selectGender(administrativeGenderButton, "Male");
            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(ethnicityButton, "B - White - Irish");
            Actions.fillInValue(hospitalNumber, faker.numerify("A#R##BB##"));
            return selectMissingNhsNumberReason(reason);
        }catch(Exception exp){
            Debugger.println("Exception in fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason:"+exp);
            SeleniumLib.takeAScreenShot("fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason.jpg");
            return false;
        }
    }

    public boolean editDropdownField(WebElement element, String value) {
        try {
            Actions.retryClickAndIgnoreElementInterception(driver, element);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
            //Click.element(driver, element);
            Wait.seconds(2);
            Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + value + "']")));
            return true;
        } catch (Exception exp) {
            Debugger.println("Oops unable to locate drop-down element value : " + value + ":" + exp);
            SeleniumLib.takeAScreenShot("editDropdownField.jpg");
            return false;
        }
    }

    //Family member Gender is throwing error by using existing one, so created new one.
    public boolean selectGender(WebElement element, String optionValue) {
        WebElement ddValue = null;
        try {
            Actions.retryClickAndIgnoreElementInterception(driver, element);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
            //Click.element(driver, element);
            Wait.seconds(3);
            List<WebElement> ddElements = driver.findElements(By.xpath("//label[@for='administrativeGender']/..//div//span[text()='" + optionValue + "']"));
            //Debugger.println("Size of Gender DD elements: "+ddElements.size());
            if (ddElements.size() > 0) {
                Wait.forElementToBeClickable(driver, ddElements.get(0));
                Actions.clickElement(driver, ddElements.get(0));
                Wait.seconds(2);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in selecting Gender for Family Member: " + exp);
            SeleniumLib.takeAScreenShot("FMGenderDropDown.jpg");
            return false;
        }
    }

    public boolean selectMissingNhsNumberReason(String reason) {
        try {
            if (!Wait.isElementDisplayed(driver, noNhsNumberReasonDropdown, 15)) {
                Actions.scrollToTop(driver);
            }
            Actions.retryClickAndIgnoreElementInterception(driver, noNhsNumberReasonDropdown);
            Actions.selectValueFromDropdown(noNhsNumberReasonDropdown, reason);
            if (reason.equalsIgnoreCase("Other - provide explanation")) {
                Wait.forElementToBeDisplayed(driver, otherReasonExplanation);
                otherReasonExplanation.sendKeys(faker.numerify("misplaced my NHS Number"));
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient new page  : Exception from selecting noNhsNumberReasonDropDown: " + exp);
            SeleniumLib.takeAScreenShot("noNhsNumberReasonDropDown.jpg");
            return false;
        }
    }

    public boolean clickOnCreateRecord() {
        try {
            if(!Wait.isElementDisplayed(driver,createRecord,30)){
                Debugger.println("Create Record button not present in new patient creation page.");
                SeleniumLib.takeAScreenShot("CreateRecord.jpg");
                return false;
            }
            Actions.clickElement(driver,createRecord);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in clickOnCreateRecord:" + exp);
            SeleniumLib.takeAScreenShot("CreateRecord.jpg");
            return false;
        }
    }

    public boolean patientIsCreated() {
        try {
            if(!Wait.isElementDisplayed(driver,successNotification,30)){
                Debugger.println("NGIS Patient Created Message not displayed.");
                SeleniumLib.takeAScreenShot("PatientNoCreated.jpg");
                return false;
            }
            if (Actions.getText(successNotification).equalsIgnoreCase("NGIS patient record created")) {
                return true;
            }
            Debugger.println("Patient Referral Creation Success Message not displayed: Pls check PatientNotCreated.jpg");
            SeleniumLib.takeAScreenShot("PatientNotCreated.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception in creating the patient." + exp);
            SeleniumLib.takeAScreenShot("PatientNotCreated.jpg");
            return false;
        }
    }

    public boolean clickStartReferralButton() {
        try {
            if(!Wait.isElementDisplayed(driver,startReferralButton,10)){
                Debugger.println("Start Referral Button not displayed.");
                SeleniumLib.takeAScreenShot("StartReferral.jpg");
                return false;
            }
            Actions.clickElement(driver, startReferralButton);
           // Wait.forElementToDisappear(driver, By.xpath(startReferralButtonLocator));
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientDetailsPage: clickStartReferralButton. Exception:" + exp);
            SeleniumLib.takeAScreenShot("StartReferral.jpg");
            return false;
        }
    }

    public boolean clickStartNewReferralButton() {
        try {
            if(!Wait.isElementDisplayed(driver, startNewReferralButton, 30)){
                Debugger.println("Start New Referral Button not displayed.");
                SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
                return false;
            }
            Actions.clickElement(driver, startNewReferralButton);
            Wait.forElementToDisappear(driver, By.xpath(startANewReferralButtonLocator));
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientDetailsPage: clickStartNewReferralButton. Exception:" + exp);
            SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
            return false;
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
        Actions.retryClickAndIgnoreElementInterception(driver, driver.findElement(goBackLink));
    }


    public boolean clickTheLinkOnNotificationBanner(String expectedLinkonBanner) {
        Wait.forElementToBeDisplayed(driver, patientDetailsnotificationBanner);
        try {
            By linkOnBanner;
            linkOnBanner = By.xpath("//*[text()= \"" + expectedLinkonBanner + "\"]");
            Wait.forElementToBeDisplayed(driver, driver.findElement(linkOnBanner), 30);
            if (!Wait.isElementDisplayed(driver, driver.findElement(linkOnBanner), 10)) {
                Debugger.println("Test Directory Link is not displayed even after waiting period...Failing.");
                SeleniumLib.takeAScreenShot("testDirectoryLinkOnBanner.jpg");
                return false;
            }
//            Click.element(driver, driver.findElement(linkOnBanner));
            Actions.retryClickAndIgnoreElementInterception(driver, driver.findElement(linkOnBanner));
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
        try {
            Wait.forElementToBeDisplayed(driver, title);
            if(!Wait.isElementDisplayed(driver,nhsNumber,10)){
                Debugger.println("NHS number field not displayed");
                SeleniumLib.takeAScreenShot("NHSNumberDisable.jpg");
                return false;
            }
            Debugger.println("For a Super user, NHSNumber field is enabled and set to True:  " + nhsNumber.isEnabled());
            return nhsNumber.isEnabled();
        }catch(Exception exp){
            Debugger.println("Exception from nhsNumberFieldIsEnabled:"+exp);
            SeleniumLib.takeAScreenShot("NHSNumberDisable.jpg");
            return false;
        }
    }

    public boolean editAndAddNhsNumberAsSuperUser() {
        Wait.forElementToBeDisplayed(driver, nhsNumber);
        Actions.clearInputField(nhsNumber);  //nhsNumber.clear();
        nhsNumber.sendKeys(NgisPatientTwo.NHS_NUMBER);
        return true;
    }

    public void nhsNumberAndDOBFieldsArePrePopulatedInNewPatientPage() {
        String DOB = PatientSearchPage.testData.getDay() + "/" + PatientSearchPage.testData.getMonth() + "/" + PatientSearchPage.testData.getYear();
        Debugger.println("Expected DOB : " + DOB + " : " + "Actual DOB" + Actions.getValue(dateOfBirth));
        Assert.assertEquals(DOB, Actions.getValue(dateOfBirth));
    }


    public boolean verifyTheElementsOnAddNewPatientPageNormalUserFlow() {

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

    public boolean verifyTheElementsOnAddNewPatientPageSuperUserFlow() {

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

    public boolean verifyMaxAllowedValuesInEthnicityField(int maxAllowedValues) {
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
        } catch (Exception exp) {
            Debugger.println("No Referrals are found for the patient");
            return false;
        }
    }

    public boolean verifyReferralStatus(String expectedStatus) {
        try {
            if(!Wait.isElementDisplayed(driver,referralStatus,100)){
                Debugger.println("Referral status is not displayed....");
                SeleniumLib.takeAScreenShot("ReferralStatus.jpg");
                return false;
            }
            if(!expectedStatus.equalsIgnoreCase(Actions.getText(referralStatus))){
                Debugger.println("Referral status expected:"+expectedStatus+",Actual:"+Actions.getText(referralStatus));
                SeleniumLib.takeAScreenShot("ReferralStatus.jpg");
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in verifyReferralStatus:"+exp);
            SeleniumLib.takeAScreenShot("ReferralStatus.jpg");
            return false;
        }
    }

    public boolean verifyReferralReason(String expectedReason) {
        Wait.forElementToBeDisplayed(driver, referralCancelReason);
        return expectedReason.equalsIgnoreCase(Actions.getText(referralCancelReason));
    }

    public boolean fillInAllFieldsNewPatientDetailsExceptNHSNumber(String reason) {
        try {
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
//        if (reason.equalsIgnoreCase("Other - provide explanation")) {
//            Wait.forElementToBeDisplayed(driver, otherReasonExplanation);
//            otherReasonExplanation.sendKeys(faker.numerify("misplaced my NHS Number"));
//        }
            String nhsNumber = RandomDataCreator.generateRandomNHSNumber();
            newPatient.setNhsNumber(nhsNumber);

            String gender = "Male";
            newPatient.setGender(gender);
            selectGender(administrativeGenderButton, gender);
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

            Debugger.println(" Newly created patient info   : " + firstNameValue + " " + lastNameValue + " " + dayOfBirth + " " + monthOfBirth + " " + yearOfBirth + " " + gender + " " + postcodeValue);
            Debugger.println(" Newly created patient object1: " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode());
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from fillInAllFieldsNewPatientDetailsExceptNHSNumber:"+exp);
            SeleniumLib.takeAScreenShot("fillInAllFieldsNewPatientDetailsExceptNHSNumber.jpg");
            return false;
        }
    }

    public boolean fillInAllFieldsNewPatientDetailsWithNHSNumber(String patientNameWithSpecialCharacters) {
        try {
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
            selectGender(administrativeGenderButton, gender);
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
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from fillInAllFieldsNewPatientDetailsWithNHSNumber:"+exp);
            SeleniumLib.takeAScreenShot("fillInAllFieldsNewPatientDetailsWithNHSNumber.jpg");
            return false;
        }
    }

    public NewPatient getNewlyCreatedPatientData() {
        Debugger.println(" Newly created patient object2: " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode());
        return newPatient;
    }

    public void editPatientGenderLifeStatusAndEthnicity(String gender, String lifeStatus, String ethnicity) {
        try {
            if (Wait.isElementDisplayed(driver, administrativeGenderButton, 15)) {
                Actions.retryClickAndIgnoreElementInterception(driver, clearGenderDropDownValue);
                selectGender(administrativeGenderButton, gender);
                editDropdownField(lifeStatusButton, lifeStatus);
                editDropdownField(ethnicityButton, ethnicity);
            }
        } catch (Exception exp) {
            Debugger.println("Exception from edit drop-down details " + exp);
            SeleniumLib.takeAScreenShot("patientDetailsEditDropDownDetails.jpg");
        }
    }

    public void addPatientEthnicity(String ethnicity) {
        try {
            if (Wait.isElementDisplayed(driver, ethnicityButton, 15)) {
                editDropdownField(ethnicityButton, ethnicity);
                Debugger.println("Ethnicity field is now filled + " + Actions.getText(ethnicityButton));
            }
        } catch (Exception exp) {
            Debugger.println("Exception from adding ethnicity value " + exp);
            SeleniumLib.takeAScreenShot("AddEthnicityDropDownValue.jpg");
        }
    }

    public boolean clickAddDetailsToNGISButton() {
        try {
            Wait.forElementToBeClickable(driver, addDetailsToNGISButton);
            Click.element(driver, addDetailsToNGISButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on addPatientDetailsToNGISButton:" + exp);
            SeleniumLib.takeAScreenShot("NoAddPatientDetailsToNGISButton.jpg");
            return false;
        }
    }

    public void clickUpdateNGISRecordButton() {
        try {
            Wait.forElementToBeClickable(driver, updateNGISRecordButton);
            Click.element(driver, updateNGISRecordButton);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on UpdatePatientDetailsToNGISButton:" + exp);
            SeleniumLib.takeAScreenShot("NoUpdatePatientDetailsToNGISButton.jpg");
        }
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

    public boolean createNewFamilyMember(NGISPatientModel familyMember) {
        try {
            //Debugger.println("Adding new Family Member...");
            selectMissingNhsNumberReason(familyMember.getNO_NHS_REASON());
            familyMember.setTITLE("Mr");
            //Name without single appostrophe
            familyMember.setFIRST_NAME(TestUtils.getRandomFirstName().replaceAll("'",""));
            familyMember.setLAST_NAME(TestUtils.getRandomLastName().replaceAll("'",""));
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
            selectGender(administrativeGenderButton, familyMember.getGENDER().trim());
            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(ethnicityButton, familyMember.getETHNICITY());
            editDropdownField(relationshipButton, familyMember.getRELATIONSHIP_TO_PROBAND());
            Actions.fillInValue(hospitalNumber, familyMember.getHOSPITAL_NO());
            //Address
            Actions.fillInValue(addressLine0, familyMember.getADDRESS_LINE0());
            Actions.fillInValue(addressLine1, familyMember.getADDRESS_LINE1());
            Actions.fillInValue(addressLine2, familyMember.getADDRESS_LINE2());
            Actions.fillInValue(addressLine3, familyMember.getADDRESS_LINE3());
            Actions.fillInValue(addressLine4, familyMember.getADDRESS_LINE4());
            Actions.fillInValue(postcode, familyMember.getPOST_CODE());
            Actions.clickElement(driver, addNewPatientToReferral);
            Wait.seconds(10);//Wait for 10 seconds to create the new member
            //Removed isPatientCreated check for Family member addition as it is not needed
            FamilyMemberDetailsPage.addFamilyMemberToList(familyMember);
            Debugger.println("Family Member Added to List: NHS:" + familyMember.getNHS_NUMBER() + ",DOB:" + familyMember.getDATE_OF_BIRTH() + ",LNAME:" + familyMember.getLAST_NAME() + ",FNAME:" + familyMember.getFIRST_NAME());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from adding mew family member:" + exp);
            SeleniumLib.takeAScreenShot("NewFamilyMember.jpg");
            return false;
        }
    }

    public boolean fillInNHSNumber() {
        try {
            Actions.fillInValue(nhsNumber, newPatient.getNhsNumber());
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in fillInNHSNumber:"+exp);
            SeleniumLib.takeAScreenShot("fillInNHSNumber.jpg");
            return false;
        }
    }

    public boolean createNewPatientReferral(NGISPatientModel referralDetails) {
        try {
            if(!newPatientPageIsDisplayed()){
                return false;
            }
            //Going ahead with NHS number, for new NGIS Patients
            if (referralDetails.getNO_NHS_REASON().equalsIgnoreCase("NGIS")) {
                Actions.clickElement(driver, yesButton);
                Actions.clickElement(driver, nhsNumber);
                Actions.fillInValue(nhsNumber, referralDetails.getNHS_NUMBER());
                Actions.clickElement(driver, nhsNumberLabel);
            } else {
                //New Patient Without NHS Number
                selectMissingNhsNumberReason(referralDetails.getNO_NHS_REASON());
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
            selectGender(administrativeGenderButton, referralDetails.getGENDER());
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
            //Adding Patient to NGIS
            if(!clickOnCreateRecord()){
                return false;
            }
            if (!patientIsCreated()) {
                return false;
            }
            if(!clickStartReferralButton()){
                return false;
            }
            //Adding referral to to a list for later stage verification, if needed
            FamilyMemberDetailsPage.addFamilyMemberToList(referralDetails);
            Debugger.println("Referral Added to List: NHS:" + referralDetails.getNHS_NUMBER() + ",DOB:" + referralDetails.getDATE_OF_BIRTH() + ",LNAME:" + referralDetails.getLAST_NAME() + ",FNAME:" + referralDetails.getFIRST_NAME());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in creating new Referral:" + exp);
            SeleniumLib.takeAScreenShot("NewReferralCreationError.jpg");
            //Observed undefined attached in the URL sometime....This is to verify the URL the moment
            Debugger.println("NewReferralCreationError:URL:" + driver.getCurrentUrl());
            return false;
        }
    }
    public boolean startReferral() {
        try {
            boolean flag = false;
            // Check condition for different scenarios when referral submit button is displayed
            if (addDetailsToNGISButtonList.size() > 0) {
                Debugger.println("Add Patient Details button shown");
                //Ethnicity included as it is mandatory field
                editDropdownField(ethnicityButton, "A - White - British");
                addDetailsToNGISButton.click();
                Wait.forElementToBeDisplayed(driver, successNotification);
                clickStartReferralButton();
            } else if (updateNGISRecordButtonList.size() > 0) {
                Debugger.println("Update Patient Details button shown");
                clickStartReferralButton();
            } else if (savePatientDetailsToNGISButtonList.size() > 0) {
                Debugger.println("Save Patient Details button shown");
                //clickSavePatientDetailsToNGISButton();
                clickOnCreateRecord();
                patientIsCreated();
                flag = clickStartNewReferralButton();
            }
            return flag;
        } catch (Exception exp) {
            Debugger.println("Exception in starting the Referral: " + exp);
            SeleniumLib.takeAScreenShot("startReferralError.jpg");
            return false;
        }
    }


    public List<String> getActualPatientAddressOnPatientDetailPage() {

        Wait.forElementToBeDisplayed(driver, addressLine0);
        List<String> actualPatientAddress = new ArrayList<>();

        actualPatientAddress.add(Actions.getValue(addressLine0));
        actualPatientAddress.add(Actions.getValue(addressLine1));
        actualPatientAddress.add(Actions.getValue(addressLine2));
        actualPatientAddress.add(Actions.getValue(addressLine3));
        actualPatientAddress.add(Actions.getValue(addressLine4));

        Debugger.println("Actual patient address in patient detail page " + actualPatientAddress);
        return actualPatientAddress;
    }

    public void fillInHospitalNo() {
        Actions.fillInValue(hospitalNumber, faker.numerify("A#R##BB##"));
    }

    public void fillInFirstName() {
        Actions.fillInValue(firstName, TestUtils.getRandomFirstName());
    }

    public boolean verifyRelationshipToProbandDropDownShowsRecentlyUsedSuggestion(String expValue) {
        String actValue = null;
        try {
            actValue = relationshipToProbandType.replaceAll("dummyOption", expValue);
            WebElement relationToProbandElement = driver.findElement(By.xpath(actValue));
            if(!Wait.isElementDisplayed(driver,relationToProbandElement,10)){
                Debugger.println("Relation to Proband element not visible.");
                SeleniumLib.takeAScreenShot("relationShipToProbandSuggestion.jpg");
                return false;
            }
            return true;
        }catch (NoSuchElementException exp) {
            Actions.scrollToBottom(driver);
            WebElement relationToProbandElement = driver.findElement(By.xpath(actValue));
            if(!Wait.isElementDisplayed(driver,relationToProbandElement,10)){
                Debugger.println("Relation to Proband element not visible.");
                SeleniumLib.takeAScreenShot("relationShipToProbandSuggestion.jpg");
                return false;
            }
            return true;
        }catch (Exception exp) {
            Debugger.println("Exception verifyRelationshipToProbandDropDownShowsRecentlyUsedSuggestion." + exp);
            SeleniumLib.takeAScreenShot("relationShipToProbandSuggestion.jpg");
            return false;
        }
    }

    public boolean verifyTheSubmittedReferrals() {
        try {
            Wait.forElementToBeDisplayed(driver, createdAndSubmittedReferralsTitle);
            //List of Total Referrals submitted  - both proband and family members
            if (referralIDOfCreatedReferrals.size() == 0) {
                Debugger.println("No referral created and submitted.");
                SeleniumLib.takeAScreenShot("ExistingReferrals.jpg");
                return false;
            }
            //List of  Referrals submitted  - only family members
            if (relationshipToProbandReferralID.size() == 0) {
                Debugger.println("No family member added with all the referral present in the page.");
                SeleniumLib.takeAScreenShot("ExistingReferrals.jpg");
                return false;
            }
            if(referralIDOfCreatedReferrals.size() <= relationshipToProbandReferralID.size()){
                Debugger.println("Expected details of both proband and family referrals submitted.");
                SeleniumLib.takeAScreenShot("ExistingReferrals.jpg");
                return false;
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheSubmittedReferrals: " + exp);
            SeleniumLib.takeAScreenShot("ExistingReferrals.jpg");
            return false;
        }
    }

    public boolean verifyTheSubmittedReferralCardsAreClickable() {
        try {
            if (submittedReferralCardsList.size() == 0) {
                Debugger.println("No submitted referral card found.");
                SeleniumLib.takeAScreenShot("SubmittedReferralsList.jpg");
                return false;
            }
            if(!Actions.isTabClickable(driver, submittedReferralCardsList.size(), submittedReferralCardsList)){
                Debugger.println("Submitted referral cards are not clickable.");
                SeleniumLib.takeAScreenShot("SubmittedReferralsList.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheSubmittedReferralCardsAreClickable: " + exp);
            SeleniumLib.takeAScreenShot("SubmittedReferralsList.jpg");
            return false;
        }
    }
    public boolean verifyColorOfSavePatientDetailsToNGISButton(String expectedColor) {
        try {
            Wait.forElementToBeDisplayed(driver, savePatientDetailsToNGISButton);
            String buttonBgColor = savePatientDetailsToNGISButton.getCssValue("background-color");
            if(buttonBgColor == null){
                Debugger.println("Button background color attribute is not present");
                SeleniumLib.takeAScreenShot("PatientDetailsToNGIS.jpg");
                return false;
            }
            if (!buttonBgColor.equalsIgnoreCase(StylesUtils.convertFontColourStringToCSSProperty(expectedColor))) {
                Debugger.println("Actual button color :" + buttonBgColor + ", But Excepted button color :" + expectedColor);
                SeleniumLib.takeAScreenShot("PatientDetailsToNGIS.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Save Patient Details To NGIS Button not found. " + exp);
            SeleniumLib.takeAScreenShot("PatientDetailsToNGIS.jpg");
            return false;
        }
    }
}//end
