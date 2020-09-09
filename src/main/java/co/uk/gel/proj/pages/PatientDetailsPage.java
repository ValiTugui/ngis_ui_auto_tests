package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static co.uk.gel.proj.util.RandomDataCreator.getRandomUKPostCode;

public class PatientDetailsPage {

    WebDriver driver;
    Faker faker = new Faker();
    public static NewPatient newPatient = new NewPatient();
    SeleniumLib seleniumLib;

    public PatientDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//input[@id='title']")
    public WebElement title;
    @FindBy(xpath = "//input[@id='firstName']")
    public WebElement firstName;
    @FindBy(xpath = "//input[@id='familyName']")
    public WebElement familyName;
    @FindBy(xpath = "//input[@id='dateOfBirthDay']")
    public WebElement dateOfBirthDay;
    @FindBy(xpath = "//input[@id='dateOfBirthMonth']")
    public WebElement dateOfBirthMonth;
    @FindBy(xpath = "//input[@id='dateOfBirthYear']")
    public WebElement dateOfBirthYear;

    @FindBy(xpath = "//input[@id='birthDateDay']")
    public WebElement dateOfBirthDay_patientsearch;
    @FindBy(xpath = "//input[@id='birthDateMonth']")
    public WebElement dateOfBirthMonth_patientsearch;
    @FindBy(xpath = "//input[@id='birthDateYear']")
    public WebElement dateOfBirthYear_patientsearch;

    @FindBy(xpath = "//div[contains(text(),'Day')]")
    public WebElement dayfield;
    @FindBy(xpath = "//div[contains(text(),'Month')]")
    public WebElement monthfield;
    @FindBy(xpath = "//div[contains(text(),'Year')]")
    public WebElement yearfield;
    @FindBy(xpath = "//input[@id='dateOfDiagnosisDay']")
    public WebElement dateOfDay_dateOfDiagnosis;
    @FindBy(xpath = "//input[@id='dateOfDiagnosisMonth']")
    public WebElement dateOfMonth_dateOfDiagnosis;
    @FindBy(xpath = "//input[@id='dateOfDiagnosisYear']")
    public WebElement dateOfYear_dateOfDiagnosis;
    @FindBy(xpath = "//input[@id='unit-id-samples-normal_or_germline_sample-37.answers.question-id-q328Day']")
    public WebElement dateOfDay_SampleDetails;
    @FindBy(xpath = "//input[@id='unit-id-samples-normal_or_germline_sample-37.answers.question-id-q328Month']")
    public WebElement dateOfMonth_SampleDetails;
    @FindBy(xpath = "//input[@id='unit-id-samples-normal_or_germline_sample-37.answers.question-id-q328Year']")
    public WebElement dateOfYear_SampleDetails;
    @FindBy(xpath = "//input[@placeholder='DD']")
    public WebElement dateOfDay_dateOfSignature;
    @FindBy(xpath = "//input[@placeholder='MM']")
    public WebElement dateOfMonth_dateOfSignature;
    @FindBy(xpath = "//input[@placeholder='YYYY']")
    public WebElement dateOfYear_dateOfSignature;

   // public WebElement dateOfDeath;
    public WebElement nhsNumber;
    @FindBy(xpath = "//input[@id='hospitalNumber']")
    public WebElement hospitalNumber;
    @FindBy(xpath = "//input[@id='postcode']")
    public WebElement postcode;
    @FindBy(xpath = "//textarea[@id='otherReasonExplanation']")
    public WebElement otherReasonExplanation;

    @FindBy(css = "h1[class*='page-title']")
    public WebElement pageTitle;

    @FindBy(css = "p[class*='sub-title']")
    public WebElement subPageTitle;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(css = "label[for*='nhsNumber']")
    public WebElement nhsNumberLabel;

    @FindBy(xpath = "//label[contains(@for,'administrativeGender')]//following::div")
    public WebElement administrativeGenderButton;

    @FindBy(css = "*[data-testid*='notification-warning']")     //@FindBy(css = "*[class*='notification--warning']")
    public WebElement patientDetailsnotificationBanner;

    @FindBy(xpath = "//div[contains(@data-testid,'notification-warning')]//a")
    public WebElement testDirectoryLink;

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

    //    @FindBy(xpath = "//button[text()='Save and continue']")
    @FindBy(xpath = "//*[text()='Save and continue']")
    public WebElement saveAndContinue;

    @FindBy(xpath = "//button[@type='submit'][contains(@class,'new-patient-form__submit')]")
    public WebElement createRecord;

    @FindBy(xpath = "//button[@type='submit']/span[text()='Start referral']")
    public WebElement startReferralButton;

    @FindBy(xpath = "//button[text()='Yes, start referral']")
    public WebElement CISearchStartReferral;

    @FindBy(xpath = "//input[@name='ci-radio']")
    public WebElement selectCIRadio;


    @FindBy(xpath = "//button[@type='submit']")
    public WebElement startNewReferralButton;

    @FindBy(xpath = "//div[@data-testid='notification-success']//span")
    public WebElement successNotification;

    @FindBy(css = "a[class*='referral-list']")
    public List<WebElement> referralListCards;

    @FindBy(css = "*[class*='referral-list__link']")
    public WebElement referralLink;

    @FindBy(css = "div[class*='css-1jwqj7b']") //@FindBy(css = "div[class*='referral-card']")
    public WebElement referralCard;

    @FindBy(xpath = "//div[contains(@id,'referral__header')]/div//child::span[contains(@class,'child-element')]")
    public List<WebElement> referralStatus;

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

    @FindBy(xpath = "(//div[contains(@class,'indicatorContainer')]//*[name()='svg']//*[name()='path'])[5]")
    public WebElement clearEthnicityDropDownValue;

    @FindBy(xpath = "(//div[contains(@class,'indicatorContainer')]//*[name()='svg']//*[name()='path'])[1]")
    public WebElement clearGenderDropDownValue;

    @FindBy(xpath = "(//div[contains(@class,'indicatorContainer')]//*[name()='svg']//*[name()='path'])[3]")
    public WebElement clearLifeStatusDropDownValue;

    @FindBy(xpath = "(//div[contains(@class,'indicatorContainer')]//*[name()='svg']//*[name()='path'])[2]")
    public WebElement clearLifeStatusDropDown;


    String dropDownValuesFromLocator = "//span[text()[('^[A-Z ]*-*')]]";

    @FindBy(xpath = "//button/span[text()='Create NGIS record']")
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

    @FindBy(xpath = "//input[@id='postcode']")
    public WebElement postcodeField;

    @FindBy(xpath = "//*[string()='Address']//following-sibling::span")
    public WebElement addressField;

    @FindBy(xpath = "//input[@id='administrativeGender']/../div/span/span")
    public WebElement genderPath;

    public boolean patientDetailsPageIsDisplayed() {
        try {
            Wait.forURLToContainSpecificText(driver, "/patient");
            //Wait.forElementToBeDisplayed(driver, startReferralButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in patientDetailsPageIsDisplayed:" + exp);
            SeleniumLib.takeAScreenShot("PatientDetails.jpg");
            return false;
        }
    }

    public boolean newPatientPageIsDisplayed() {
        try {
            Wait.forURLToContainSpecificText(driver, "/new-patient");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from newPatientPageIsDisplayed:" + exp);
            SeleniumLib.takeAScreenShot("newPatientPageIsDisplayed.jpg");
            return false;
        }
    }
    public boolean newFamilyMemberPageIsDisplayed() {
        try {
            Wait.forURLToContainSpecificText(driver, "/family-members/new");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from newFamilyMemberPageIsDisplayed:" + exp);
            SeleniumLib.takeAScreenShot("newFamilyMemberPage.jpg");
            return false;
        }
    }

    public String getPageSubTitle() {
        try {
            if (!Wait.isElementDisplayed(driver, subPageTitle, 10)) {
                Debugger.println("subPageTitle not loaded.");
                SeleniumLib.takeAScreenShot("subPageTitle.jpg");
                return null;
            }
            return subPageTitle.getText();
        } catch (Exception exp) {
            try {
                return seleniumLib.getText(subPageTitle);
            } catch (Exception exp1) {
                Debugger.println("Exception from checking subPageTitle:" + exp1);
                SeleniumLib.takeAScreenShot("subPageTitle.jpg");
                return null;
            }
        }
    }

    public String getNotificationBannerText() {
        try {
            if (!Wait.isElementDisplayed(driver, textOnPatientDetailsNotificationBanner, 10)) {
                Debugger.println("textOnPatientDetailsNotificationBanner not loaded.");
                SeleniumLib.takeAScreenShot("textOnPatientDetailsNotificationBanner.jpg");
                return null;
            }
            return textOnPatientDetailsNotificationBanner.getText();
        } catch (Exception exp) {
            try {
                return seleniumLib.getText(textOnPatientDetailsNotificationBanner);
            } catch (Exception exp1) {
                Debugger.println("Exception from checking textOnPatientDetailsNotificationBanner:" + exp1);
                SeleniumLib.takeAScreenShot("textOnPatientDetailsNotificationBanner.jpg");
                return null;
            }
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
            String dobDay = seleniumLib.getText(dateOfBirthDay);
            if (dobDay == null || dobDay.isEmpty()) {
                dayOfBirth = String.valueOf(faker.number().numberBetween(10, 31));
                monthOfBirth = String.valueOf(faker.number().numberBetween(10, 12));
                yearOfBirth = String.valueOf(faker.number().numberBetween(1900, 2019));
                seleniumLib.sendValue(dateOfBirthDay,dayOfBirth);
                seleniumLib.sendValue(dateOfBirthMonth,monthOfBirth);
                seleniumLib.sendValue(dateOfBirthYear,yearOfBirth);
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
            editDropdownField(ethnicityButton, "A - White - British");
            Actions.fillInValue(hospitalNumber, faker.numerify("A#R##BB##"));
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInNewPatientDetailsWithoutAddressFields:" + exp);
            SeleniumLib.takeAScreenShot("fillInNewPatientDetailsWithoutAddressFields.jpg");
            return false;
        }
    }

    public boolean fillInAllFieldsNewPatientDetailsWithOutNhsNumber(String reason) {
        if (!selectMissingNhsNumberReason(reason)) {
            return false;
        }
        return fillInAllNewPatientDetails();
    }

    public boolean fillInAllNewPatientDetails() {
        try {
            if (!fillInNewPatientDetailsWithoutAddressFields()) {
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
        } catch (Exception exp) {
            Debugger.println("Exception from fillInAllNewPatientDetails:" + exp);
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
            seleniumLib.sendValue(dateOfBirthDay,newPatient.getDay());
            seleniumLib.sendValue(dateOfBirthMonth,newPatient.getMonth());
            seleniumLib.sendValue(dateOfBirthYear,newPatient.getYear());
            selectGender(administrativeGenderButton, "Male");
            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(ethnicityButton, "B - White - Irish");
            Actions.fillInValue(hospitalNumber, faker.numerify("A#R##BB##"));
            return selectMissingNhsNumberReason(reason);
        } catch (Exception exp) {
            Debugger.println("Exception in fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason:" + exp);
            SeleniumLib.takeAScreenShot("fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason.jpg");
            return false;
        }
    }

    public boolean editDropdownField(WebElement element, String value) {
        try {
            Actions.clickElement(driver, element);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
            //Click.element(driver, element);
            Wait.seconds(2);
            Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + value + "']")));
            return true;
        } catch (Exception exp) {
            try {
                Debugger.println("editDropdownField trying with seleniumLib..");
                seleniumLib.clickOnWebElement(element);
                Wait.seconds(2);
                seleniumLib.clickOnElement(By.xpath("//span[text()='" + value + "']"));
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception in editDropdownField:" + value + " on:" + element + "\n" + exp1);
                SeleniumLib.takeAScreenShot("editDropdownField.jpg");
                return false;
            }
        }
    }

    //Family member Gender is throwing error by using existing one, so created new one.
    public boolean selectGender(WebElement element, String optionValue) {
        WebElement ddValue = null;
        try {
            try {
                Actions.clickElement(driver, element);
            }catch(Exception exp1){
                seleniumLib.clickOnWebElement(element);
            }
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
            //Click.element(driver, element);
            Wait.seconds(3);
            List<WebElement> ddElements = driver.findElements(By.xpath("//label[@for='administrativeGender']/..//div//span[text()='" + optionValue + "']"));
            //Debugger.println("Size of Gender DD elements: "+ddElements.size());
            if (ddElements.size() > 0) {
                try {
                    Wait.forElementToBeClickable(driver, ddElements.get(0));
                    Actions.clickElement(driver, ddElements.get(0));
                    Wait.seconds(2);
                }catch(Exception exp1){
                    seleniumLib.clickOnWebElement(ddElements.get(0));
                }
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
            try {
                Actions.clickElement(driver, noNhsNumberReasonDropdown);
            }catch(Exception exp1){
                seleniumLib.clickOnWebElement(noNhsNumberReasonDropdown);
            }
            Actions.selectValueFromDropdown(noNhsNumberReasonDropdown, reason);
            if (reason.equalsIgnoreCase("Other (please provide reason)")) {
                if(Wait.isElementDisplayed(driver, otherReasonExplanation,20)) {
                    otherReasonExplanation.sendKeys(faker.numerify("misplaced my NHS Number"));
                }
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
            if (!Wait.isElementDisplayed(driver, createRecord, 30)) {
                Debugger.println("Create Record button not present in new patient creation page.");
                SeleniumLib.takeAScreenShot("CreateRecord.jpg");
                Actions.scrollToBottom(driver);
                return false;
            }
            seleniumLib.clickOnWebElement(createRecord);
            return true;
        } catch (Exception exp) {
            try{
                Actions.clickElement(driver, createRecord);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception in clickOnCreateRecord:" + exp);
                SeleniumLib.takeAScreenShot("CreateRecord.jpg");
                return false;
            }
        }
    }

    public boolean patientIsCreated() {
        try {
            if (!Wait.isElementDisplayed(driver, successNotification, 30)) {
                Debugger.println("NGIS Patient Created Message not displayed."+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("PCCreatedsuccessNotification.jpg");
                return false;
            }
            String successMsg = Actions.getText(successNotification);
            if (successMsg.equalsIgnoreCase("NGIS patient record created")) {
                return true;
            }
            Debugger.println("ActualMessage:" + successMsg + ",Expected:NGIS patient record created."+driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("PatientNotCreated.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception in creating the patient." + exp+"\n"+driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("PCCreatedException.jpg");
            return false;
        }
    }

    public boolean clickStartReferralButton() {
        try {
            if (!Wait.isElementDisplayed(driver, startReferralButton, 30)) {
                Debugger.println("Start Referral Button not displayed.\n"+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("StartReferral.jpg");
                return false;
            }
            Actions.clickElement(driver, startReferralButton);
            // Wait.forElementToDisappear(driver, By.xpath(startReferralButtonLocator));
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(startReferralButton);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickStartReferralButton. Exception:" + exp1 + "\n" + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("StartReferral.jpg");
                return false;
            }
        }
    }

    public boolean clickCISearchStartReferralButton() {
        try {
            if (!Wait.isElementDisplayed(driver, CISearchStartReferral, 10)) {
                Debugger.println("Start Referral Button not displayed.");
                SeleniumLib.takeAScreenShot("StartReferral.jpg");
                return false;
            }
            Actions.clickElement(driver, CISearchStartReferral);
            Wait.seconds(10);
            if (Wait.isElementDisplayed(driver, selectCIRadio, 20)) {
                Actions.clickElement(driver, selectCIRadio);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientDetailsPage: clickStartReferralButton. Exception:" + exp);
            SeleniumLib.takeAScreenShot("StartReferral.jpg");
            return false;
        }
    }

    public boolean clickStartNewReferralButton() {
        try {
            if (!Wait.isElementDisplayed(driver, startNewReferralButton, 30)) {
                Debugger.println("Start New Referral Button not displayed.");
                SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
                return false;
            }
            Debugger.println("Status: "+startNewReferralButton.isEnabled());
            if(!startNewReferralButton.isEnabled()){
                Wait.seconds(3);
            }
            Actions.clickElement(driver, startNewReferralButton);

            return true;
        } catch (Exception exp) {
            try{
                seleniumLib.clickOnWebElement(startReferralButton);
                return true;
            }catch(Exception exp1) {
                Debugger.println("PatientDetailsPage: clickStartNewReferralButton. Exception:" + exp);
                SeleniumLib.takeAScreenShot("StartNewReferralButton.jpg");
                return false;
            }
        }
    }

    public boolean clinicalIndicationIDMissingBannerIsDisplayed() {
        try {
            if (!Wait.isElementDisplayed(driver, patientDetailsnotificationBanner, 30)) {
                Debugger.println("Clinical Indication ID Missing message not displayed.");
                SeleniumLib.takeAScreenShot("patientDetailsnotificationBanner.jpg");
                return false;
            }
            String text = Actions.getText(patientDetailsnotificationBanner);
            if (text.isEmpty()) {
                Debugger.println("Clinical Indication ID Missing message is EMPTY.");
                SeleniumLib.takeAScreenShot("patientDetailsnotificationBanner.jpg");
                return false;
            }
            Debugger.println("TEXT:" + text);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from: clinicalIndicationIDMissingBannerIsDisplayed" + exp);
            SeleniumLib.takeAScreenShot("patientDetailsnotificationBanner.jpg");
            return false;
        }
    }

    public boolean startReferralButtonIsDisabled() {
        try {
            if (!Wait.isElementDisplayed(driver, startReferralButton, 30)) {
                Debugger.println("startReferralButtonIsDisabled ot displayed");
                SeleniumLib.takeAScreenShot("startReferralButton.jpg");
                return false;
            }
            if (startReferralButton.isEnabled()) {
                Debugger.println("startReferralButton expected to be disabled. but enabled.");
                SeleniumLib.takeAScreenShot("startReferralButton.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in startReferralButtonIsDisabled:" + exp);
            SeleniumLib.takeAScreenShot("startReferralButton.jpg");
            return false;
        }
    }

    public void startNewReferralButtonIsDisabled() {
        Wait.forElementToBeDisplayed(driver, startNewReferralButton);
        Assert.assertTrue(!startNewReferralButton.isEnabled());
    }

    public boolean clickTheGoBackLink(String expectedGoBackToPatientSearch) {
        By goBackLink = null;
        try {
            goBackLink = By.xpath("//a[contains(text(),'" + expectedGoBackToPatientSearch + "')]");
            if (!Wait.isElementDisplayed(driver, driver.findElement(goBackLink), 10)) {
                Debugger.println("Link not present:" + expectedGoBackToPatientSearch);
                SeleniumLib.takeAScreenShot("clickTheGoBackLink.jpg");
                return false;
            }
            Actions.clickElement(driver, driver.findElement(goBackLink));
            Wait.seconds(2);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnElement(goBackLink);
                Wait.seconds(2);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception from clickTheGoBackLink:" + exp1);
                SeleniumLib.takeAScreenShot("clickTheGoBackLink.jpg");
                return false;
            }
        }
    }


    public boolean clickTheLinkOnNotificationBanner() {
        if (!Wait.isElementDisplayed(driver, testDirectoryLink, 30)) {
            Debugger.println("Test Directory Link is not present...");
            SeleniumLib.takeAScreenShot("testDirectoryLinkOnBanner.jpg");
            return false;
        }
        try {
            Actions.clickElement(driver, testDirectoryLink);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(testDirectoryLink);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Test Directory Link is not shown on banner..." + exp1);
                SeleniumLib.takeAScreenShot("testDirectoryLinkOnBanner.jpg");
                return false;
            }
        }
    }

    public boolean nhsNumberFieldIsDisabled() {
        Wait.forElementToBeDisplayed(driver, title);
        if (nhsNumber.isEnabled()) {
            return false;
        }
        return true;
    }

    public boolean nhsNumberFieldIsEnabled() {
        try {
             if (!Wait.isElementDisplayed(driver, nhsNumber, 30)) {
                Debugger.println("NHS number field not displayed");
                SeleniumLib.takeAScreenShot("NHSNumberDisable.jpg");
                return false;
            }
            if(!nhsNumber.isEnabled()){
                Debugger.println("For a Super user, NHSNumber field is expected to be enabled and set to True, but not."+driver.getCurrentUrl());
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from nhsNumberFieldIsEnabled:" + exp);
            SeleniumLib.takeAScreenShot("NHSNumberDisable.jpg");
            return false;
        }
    }

    public void nhsNumberAndDOBFieldsArePrePopulatedInNewPatientPage() {
        String DOB = PatientSearchPage.testData.getDay() + "/" + PatientSearchPage.testData.getMonth() + "/" + PatientSearchPage.testData.getYear();
        String actualDOB = seleniumLib.getText(dateOfBirthDay)+"/"+seleniumLib.getText(dateOfBirthMonth)+"/"+seleniumLib.getText(dateOfBirthYear);
        Assert.assertEquals(DOB, actualDOB);
    }

    public void verifyAndClickOnTheReferralCardOnPatientDetailsPage() {
        if(!Wait.isElementDisplayed(driver, referralCard, 70)){
            Debugger.println("No referral card found");
            return;
        }
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
            if(!Wait.isElementDisplayed(driver, referralLink,60)){
                Debugger.println("Referral Link not displayed.");
                SeleniumLib.takeAScreenShot("ReferralLink.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver, referralCard,60)){
                Debugger.println("Referral Card not displayed.");
                SeleniumLib.takeAScreenShot("ReferralCard.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("No Referrals are found for the patient:"+exp);
            SeleniumLib.takeAScreenShot("ReferralLinkCard.jpg");
            return false;
        }
    }

    public boolean verifyReferralStatus(String expectedStatus) {
        try {
            if (referralStatus.size() == 0) {
                Debugger.println("Referral status is not displayed....");
                SeleniumLib.takeAScreenShot("ReferralStatus.jpg");
                return false;
            }
            boolean isPresent = false;
            for (int i = 0; i < referralStatus.size(); i++) {
                if (expectedStatus.equalsIgnoreCase(Actions.getText(referralStatus.get(i)))) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Referral status expected:" + expectedStatus + " not displayed.");
                SeleniumLib.takeAScreenShot("ReferralStatus.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception in verifyReferralStatus:" + exp);
            SeleniumLib.takeAScreenShot("ReferralStatus.jpg");
            return false;
        }
    }

    public boolean verifyReferralReason(String expectedReason) {
        if(!Wait.isElementDisplayed(driver, referralCancelReason,60)){
            Debugger.println("referralCancelReason not displayed.");
            SeleniumLib.takeAScreenShot("referralCancelReason.jpg");
            return false;
        }
        return expectedReason.equalsIgnoreCase(Actions.getText(referralCancelReason));
    }

    public boolean fillInAllFieldsNewPatientDetailsExceptNHSNumber(String reason) {
        try {

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
            String nhsNumber = RandomDataCreator.generateRandomNHSNumber();
            newPatient.setNhsNumber(nhsNumber);

            String gender = "Male";
            newPatient.setGender(gender);
            selectGender(administrativeGenderButton, gender);
            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(ethnicityButton, "A - White - British");
            String hospitalId = faker.numerify("A#R##BB##");
            selectMissingNhsNumberReason(reason);
            if (reason.equalsIgnoreCase("Other (please provide reason)")) {
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
        } catch (Exception exp) {
            Debugger.println("Exception from fillInAllFieldsNewPatientDetailsExceptNHSNumber:" + exp);
            SeleniumLib.takeAScreenShot("fillInAllFieldsNewPatientDetailsExceptNHSNumber.jpg");
            return false;
        }
    }

    public boolean fillInAllFieldsNewPatientDetailsWithNHSNumber(String patientNameWithSpecialCharacters) {
        try {
            Debugger.println("Yes..here.......");
            String patientTitle = "Mr";
            newPatient.setTitle(patientTitle);
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
            newPatient.setLastName(lastNameValue);
            String dayOfBirth = PatientSearchPage.testData.getDay();
            String monthOfBirth = PatientSearchPage.testData.getMonth();
            String yearOfBirth = PatientSearchPage.testData.getYear();
            newPatient.setDay(dayOfBirth);
            newPatient.setMonth(monthOfBirth);
            newPatient.setYear(yearOfBirth);
            String gender = "Male";
            newPatient.setGender(gender);
            String patientNhsNumber = RandomDataCreator.generateRandomNHSNumber();
            newPatient.setNhsNumber(patientNhsNumber);
            String hospitalId = faker.numerify("A#R##BB##");
            newPatient.setPostCode(getRandomUKPostCode());
            newPatient.setHospitalNumber(hospitalId);
            String postcodeValue = newPatient.getPostCode();

            title.sendKeys(patientTitle);
            Actions.fillInValue(firstName, newPatient.getFirstName());
            Actions.fillInValue(familyName, newPatient.getLastName());
            selectGender(administrativeGenderButton, gender);
            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(ethnicityButton, "A - White - British");
            Actions.clickElement(driver, yesButton);
            Actions.clickElement(driver, nhsNumber);
            Actions.fillInValue(nhsNumber, patientNhsNumber);
            Actions.clickElement(driver, nhsNumberLabel);

            Actions.fillInValue(hospitalNumber, hospitalId);
            Actions.fillInValue(addressLine0, faker.address().buildingNumber());
            Actions.fillInValue(addressLine1, faker.address().streetAddressNumber());
            Actions.fillInValue(addressLine2, faker.address().streetName());
            Actions.fillInValue(addressLine3, faker.address().cityName());
            Actions.fillInValue(addressLine4, faker.address().state());
            Actions.fillInValue(postcode, postcodeValue);

            Debugger.println(" Newly created patient info   : " + patientTitle + " " + firstNameValue + " " + lastNameValue + " " + dayOfBirth + " " + monthOfBirth + " " + yearOfBirth + " " + gender + " " + postcodeValue);
            Debugger.println(" Newly created patient object1: " + newPatient.getTitle() + " " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInAllFieldsNewPatientDetailsWithNHSNumber:" + exp);
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

    public boolean addPatientEthnicity(String ethnicity) {
        try {
            if (Wait.isElementDisplayed(driver, ethnicityButton, 15)) {
                return editDropdownField(ethnicityButton, ethnicity);
            }
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception from adding ethnicity value " + exp);
            SeleniumLib.takeAScreenShot("AddEthnicityDropDownValue.jpg");
            return false;
        }
    }

    public boolean clickAddDetailsToNGISButton() {
        try {
            Wait.forElementToBeClickable(driver, addDetailsToNGISButton);
            Click.element(driver, addDetailsToNGISButton);
            return true;
        } catch (Exception exp) {
            try{
                seleniumLib.clickOnWebElement(addDetailsToNGISButton);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception from Clicking on addPatientDetailsToNGISButton:" + exp1);
                SeleniumLib.takeAScreenShot("NoAddPatientDetailsToNGISButton.jpg");
                return false;
            }
        }
    }

    public boolean clickUpdateNGISRecordButton() {
        try {
            if (!Wait.isElementDisplayed(driver, updateNGISRecordButton, 30)) {
                Actions.scrollToBottom(driver);
                SeleniumLib.takeAScreenShot("updateNGISRecordButton.jpg");
                return false;
            }
            Actions.clickElement(driver, updateNGISRecordButton);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(updateNGISRecordButton);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception from Clicking on UpdatePatientDetailsToNGISButton:" + exp);
                SeleniumLib.takeAScreenShot("NoUpdatePatientDetailsToNGISButton.jpg");
                return false;
            }
        }
    }

    public String getNotificationMessageForPatientCreatedOrUpdated() {
        try {
            Wait.forElementToBeDisplayed(driver, successNotification);
            return Actions.getText(successNotification);
        } catch (Exception exp) {
            try{
                return seleniumLib.getText(successNotification);
            }catch(Exception exp1) {
                Debugger.println("Exception in getNotificationMessageForPatientCreatedOrUpdated: " + exp1);
            SeleniumLib.takeAScreenShot("getNotificationMessageForPatientCreatedOrUpdated.jpg");
            return null;
        }
    }
    }

    public List<String> getTheEthnicityDropDownValues() {
        try {
        Wait.forElementToBeClickable(driver, ethnicityButton);
        Actions.clickElement(driver, ethnicityButton);
        List<String> actualEthnicityValues = new ArrayList<String>();
        for (WebElement ethnicityValue : ethnicityValues) {
            actualEthnicityValues.add(ethnicityValue.getText().trim());
        }
            //Debugger.println("Actual ethnicity values: " + actualEthnicityValues);
        return actualEthnicityValues;
        }catch(Exception exp){
            Debugger.println("Exception in getting Ethnicity Dropdown."+exp);
            return null;
        }
    }

    public boolean fillInLastName() {
        try {
            Actions.fillInValue(familyName, TestUtils.getRandomLastName());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInLastName:" + exp);
            SeleniumLib.takeAScreenShot("fillInLastName.jpg");
            return false;
        }
    }

    public boolean createNewFamilyMember(NGISPatientModel familyMember) {
        try {
            //Debugger.println("Adding new Family Member...");
            selectMissingNhsNumberReason(familyMember.getNO_NHS_REASON());
            familyMember.setTITLE("Mr");
            //Name without single appostrophe
            familyMember.setFIRST_NAME(TestUtils.getRandomFirstName().replaceAll("'", ""));
            familyMember.setLAST_NAME(TestUtils.getRandomLastName().replaceAll("'", ""));
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
        } catch (Exception exp) {
            Debugger.println("Exception in fillInNHSNumber:" + exp);
            SeleniumLib.takeAScreenShot("fillInNHSNumber.jpg");
            return false;
        }
    }

    public boolean createNewPatientReferral(NGISPatientModel referralDetails) {
        try {
            if (!newPatientPageIsDisplayed()) {
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
            if (referralDetails.getLIFE_STATUS() == null || referralDetails.getLIFE_STATUS().isEmpty()) {
                referralDetails.setLIFE_STATUS("Alive");
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
//            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(lifeStatusButton,referralDetails.getLIFE_STATUS());
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
            if (!clickOnCreateRecord()) {
                return false;
            }
            if (!patientIsCreated()) {
                return false;
            }
            if (!clickStartReferralButton()) {
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

    public List<String> getActualPatientAddressOnPatientDetailPage() {
        try {
        Wait.forElementToBeDisplayed(driver, addressLine0);
        List<String> actualPatientAddress = new ArrayList<>();

        actualPatientAddress.add(Actions.getValue(addressLine0));
        actualPatientAddress.add(Actions.getValue(addressLine1));
        actualPatientAddress.add(Actions.getValue(addressLine2));
        actualPatientAddress.add(Actions.getValue(addressLine3));
        actualPatientAddress.add(Actions.getValue(addressLine4));

            //Debugger.println("Actual patient address in patient detail page " + actualPatientAddress);
        return actualPatientAddress;
        }catch(Exception exp){
            Debugger.println("Exception getting Actual Patient Address:"+exp);
            return null;
        }
    }

    public void fillInHospitalNo() {
        Actions.fillInValue(hospitalNumber, faker.numerify("A#R##BB##"));
    }

    public void fillInFirstName() {
        Actions.fillInValue(firstName, TestUtils.getRandomFirstName());
    }

    public boolean verifyPatientDetails(String patientDetails){
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(patientDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        String actValue = "";
        String expValue = "";
        for (String key : paramsKey) {
            expValue = paramNameValue.get(key);
            switch (key) {
                case "FirstName": {
                    actValue = firstName.getAttribute("value");
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "LastName": {
                    actValue = familyName.getAttribute("value");
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "DOB": {
                    actValue = dateOfBirthDay.getAttribute("value") + "-";
                    actValue += dateOfBirthMonth.getAttribute("value") + "-";
                    actValue += dateOfBirthYear.getAttribute("value");
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "Gender": {
                    By genderPath = By.xpath("//input[@id='administrativeGender']/../div/span/span");
                    actValue = seleniumLib.getText(genderPath);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "LifeStatus": {
                    By lifeStatusPath = By.xpath("//input[@id='lifeStatus']/../div/span/span");
                    actValue = seleniumLib.getText(lifeStatusPath);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "Ethnicity": {
                    By ethnicityPath = By.xpath("//input[@name='ethnicity']/..//span/span");
                    actValue = seleniumLib.getText(ethnicityPath);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "Postcode": {
                    actValue = postcodeField.getAttribute("value");
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public boolean verifyRelationshipToProbandDropDownShowsRecentlyUsedSuggestion(String expValue) {
        String actValue = null;
        try {
            actValue = relationshipToProbandType.replaceAll("dummyOption", expValue);
            WebElement relationToProbandElement = driver.findElement(By.xpath(actValue));
            if (!Wait.isElementDisplayed(driver, relationToProbandElement, 10)) {
                Debugger.println("Relation to Proband element not visible.");
                SeleniumLib.takeAScreenShot("relationShipToProbandSuggestion.jpg");
                return false;
            }
            return true;
        } catch (NoSuchElementException exp) {
            Actions.scrollToBottom(driver);
            WebElement relationToProbandElement = driver.findElement(By.xpath(actValue));
            if (!Wait.isElementDisplayed(driver, relationToProbandElement, 10)) {
                Debugger.println("Relation to Proband element not visible.");
                SeleniumLib.takeAScreenShot("relationShipToProbandSuggestion.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
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
            if (referralIDOfCreatedReferrals.size() <= relationshipToProbandReferralID.size()) {
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
            if (!Actions.isTabClickable(driver, submittedReferralCardsList.size(), submittedReferralCardsList)) {
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

    public boolean verifyColorOfCreateRecordButton(String expectedColor) {
        try {
            if (!Wait.isElementDisplayed(driver, createRecord, 30)) {
                Debugger.println("Create Record Button not displayed.");
                SeleniumLib.takeAScreenShot("CreateButton.jpg");
                return false;
            }
            String buttonBgColor = createRecord.getCssValue("background-color");
            if (buttonBgColor == null) {
                Debugger.println("Button background color attribute is not present");
                SeleniumLib.takeAScreenShot("CreateButton.jpg");
                return false;
            }
            if (!buttonBgColor.equalsIgnoreCase(StylesUtils.convertFontColourStringToCSSProperty(expectedColor))) {
                Debugger.println("Actual button color :" + buttonBgColor + ", But Excepted button color :" + expectedColor);
                SeleniumLib.takeAScreenShot("CreateButton.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyColorOfCreateRecordButton. " + exp);
            SeleniumLib.takeAScreenShot("CreateButton.jpg");
            return false;
        }
    }

    public boolean clickOnSaveAndContinueButton() {
        try {
            //This is different from Referral page save nd Continue
            if (!Wait.isElementDisplayed(driver, saveAndContinue, 10)) {
                Debugger.println("saveAndContinue button not exists.");
                SeleniumLib.takeAScreenShot("saveAndContinue.jpg");
                return false;
            }
            Actions.clickElement(driver, saveAndContinue);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(saveAndContinue);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception from saveAndContinue:" + exp);
                SeleniumLib.takeAScreenShot("saveAndContinue.jpg");
                return false;
            }
        }
    }

    public boolean fillPostcodeValue(String postcode) {
        try {
            if(!Wait.isElementDisplayed(driver,postcodeField,20)){
                Debugger.println("The Postcode field is not displayed in the edit patient details page."+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("fillPostcode.jpg");
                return false;
            }
            postcodeField.clear();
            postcodeField.sendKeys(postcode);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in filling the postcode: "+exp);
            SeleniumLib.takeAScreenShot("fillPostcode.jpg");
            return false;
        }
    }

    public boolean verifyPostcodeFormatInAddress(String formattedPostcode) {
        try{
            if(!Wait.isElementDisplayed(driver,addressField,20)){
                Debugger.println("The address is not displayed."+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("VerifyPostcodeFormatInFM.jpg");
                return false;
            }
            String actualAddress=addressField.getText();
            if(!actualAddress.contains(formattedPostcode)){
                Debugger.println("The postcode format expected is:"+formattedPostcode+" But actual address is:"+actualAddress);
                SeleniumLib.takeAScreenShot("VerifyPostcodeFormatInFM.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from checking the postcode format in address:" + exp);
            SeleniumLib.takeAScreenShot("VerifyPostcodeFormatInFM.jpg");
            return false;
        }
    }

    public boolean verifyPostcodeFormatInPD(String formattedPostcode) {
        try{
            if(!Wait.isElementDisplayed(driver,postcodeField,20)){
                Debugger.println("The address is not displayed."+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("VerifyPostcodeFormatPD.jpg");
                return false;
            }
            String actualPostcode=postcodeField.getAttribute("value");
            if(!actualPostcode.equalsIgnoreCase(formattedPostcode)){
                Debugger.println("The postcode format expected is:"+formattedPostcode+" But actual is:"+actualPostcode);
                SeleniumLib.takeAScreenShot("VerifyPostcodeFormatPD.jpg");
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from checking the postcode format in address:"+exp);
            SeleniumLib.takeAScreenShot("VerifyPostcodeFormatPD.jpg");
            return false;
        }
    }

    public String verifyPatientDetails_NGIS(){
        try {
            String actualPrefix = title.getAttribute("value");
            String actualFirstName = firstName.getAttribute("value");
            String actualLastName = familyName.getAttribute("value");
            String actualFullDOB = dateOfBirthDay.getAttribute("value") + "/" + dateOfBirthMonth.getAttribute("value") + "/" + dateOfBirthYear.getAttribute("value");
            String actualGender = administrativeGenderButton.getText().trim();
            String actualNHSNumber = nhsNumber.getAttribute("value");

            NewPatient newPatient = getNewlyCreatedPatientData();
            String expectedDOB = newPatient.getDay() + "/" + newPatient.getMonth() + "/" + newPatient.getYear();
            if(!newPatient.getTitle().equalsIgnoreCase(actualPrefix)){
                return "Expected Prefix: "+newPatient.getTitle()+",But Actual:"+actualPrefix;
            }
            if(!newPatient.getFirstName().equalsIgnoreCase(actualFirstName)){
                return "Expected FirstName: "+newPatient.getFirstName()+",But Actual:"+actualFirstName;
            }
            if(!newPatient.getLastName().equalsIgnoreCase(actualLastName)){
                return "Expected LastName: "+newPatient.getLastName()+",But Actual:"+actualLastName;
            }
            if(!expectedDOB.equalsIgnoreCase(actualFullDOB)){
                return "Expected DOB: "+actualFullDOB+",But Actual:"+actualFullDOB;
            }
            if(!newPatient.getGender().equalsIgnoreCase(actualGender)){
                return "Expected Gender: "+newPatient.getGender()+",But Actual:"+actualGender;
            }
            if(actualNHSNumber != null && !actualNHSNumber.isEmpty()) {
                if (!newPatient.getNhsNumber().equalsIgnoreCase(actualNHSNumber)) {
                    return "Expected NHSNumber: " + newPatient.getGender() + ",But Actual:" + actualNHSNumber;
                }
            }
            return "Success";
        }catch(Exception exp){
            Debugger.println("Could not verify NGIS Patient Details:"+exp+"\n"+driver.getCurrentUrl());
            return "Could not verify NGIS Patient details:"+exp;
        }
    }
    public String fillDateOfBirth(String dateOfBirth){
        try {
            String[] dobSplit = dateOfBirth.split("-");
            seleniumLib.sendValue(dateOfBirthDay, dobSplit[0]);
            seleniumLib.sendValue(dateOfBirthMonth, dobSplit[1]);
            seleniumLib.sendValue(dateOfBirthYear, dobSplit[2]);
            return "Success";
        }catch(Exception exp){
            Debugger.println("Could not fill Date Of Birth: "+exp+"\n"+driver.getCurrentUrl());
            return "Could not fill Date Of Birth: "+exp;
        }
    }
    public String clearDateOfBirth(){
        try {
            if(!seleniumLib.isElementPresent(dateOfBirthDay)){
                return "Date of birth fields not displayed.";
            }
            Actions.clearInputField(dateOfBirthDay);
            Actions.clearInputField(dateOfBirthMonth);
            Actions.clearInputField(dateOfBirthYear);
            return "Success";
        }catch(Exception exp){
            Debugger.println("Could not Clear Date Of Birth: "+exp+"\n"+driver.getCurrentUrl());
            return "Could not Clear Date Of Birth: "+exp;
        }
    }


    public String fillBirthday(String Birthday){
        try {
            seleniumLib.sendValue(dateOfBirthDay, Birthday);
            return "Success";
        }catch(Exception exp){
            Debugger.println("Could not fill Date Of Birth: "+exp+"\n"+driver.getCurrentUrl());
            return "Could not fill Date Of Birthday: "+exp;
        }
    }

    public String fillBirthYear(String BirthYear){
        try {
            seleniumLib.sendValue(dateOfBirthYear, BirthYear);
            return "Success";
        }catch(Exception exp){
            Debugger.println("Could not fill Date Of Birth: "+exp+"\n"+driver.getCurrentUrl());
            return "Could not fill month Of Birthyear: "+exp;
        }
    }


    public boolean dayfield() {
        try {
            if (!Wait.isElementDisplayed(driver, dayfield, 10)) {
                Debugger.println("dayfield not displayed.");
                SeleniumLib.takeAScreenShot("dayfield.jpg");
                return false;
            }
            return true;
        }  catch (Exception exp1) {
            Debugger.println("Exception from checking dayfield:" + exp1);
            SeleniumLib.takeAScreenShot("subPageTitle.jpg");
            return false;
        }
    }


    public boolean clickDayinputfield() {
        try {
            if (!Wait.isElementDisplayed(driver,dateOfBirthDay_patientsearch, 30)) {
                Debugger.println("day of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Dayinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfBirthDay_patientsearch.isEnabled());
            if (!dateOfBirthDay_patientsearch.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfBirthDay_patientsearch);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthDay);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickDayinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Dayinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickDayinputfield_1() {
        try {
            if (!Wait.isElementDisplayed(driver,dateOfBirthDay, 30)) {
                Debugger.println("day of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Dayinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfBirthDay.isEnabled());
            if (!dateOfBirthDay.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfBirthDay);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthDay);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickDayinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Dayinputfield.jpg");
                return false;
            }
        }
    }
    public boolean monthfield() {
        try {
            if (!Wait.isElementDisplayed(driver, monthfield, 10)) {
                Debugger.println("monthfield not displayed.");
                SeleniumLib.takeAScreenShot("monthfield.jpg");
                return false;
            }
            return true;
        }  catch (Exception exp1) {
            Debugger.println("Exception from checking monthfield:" + exp1);
            SeleniumLib.takeAScreenShot("monthTitle.jpg");
            return false;
        }
    }


    public boolean clickMonthinputfield() {
        try {
            if (!Wait.isElementDisplayed(driver,dateOfBirthMonth_patientsearch, 30)) {
                Debugger.println("Month of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfBirthMonth_patientsearch.isEnabled());
            if (!dateOfBirthMonth_patientsearch.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfBirthMonth_patientsearch);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthMonth_patientsearch);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickMonthinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickMonthinputfield_1() {
        try {
            if (!Wait.isElementDisplayed(driver,dateOfBirthMonth, 30)) {
                Debugger.println("Month of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfBirthMonth.isEnabled());
            if (!dateOfBirthMonth.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfBirthMonth);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthMonth);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickMonthinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
        }
    }
    public boolean yearfield() {
        try {
            if (!Wait.isElementDisplayed(driver, yearfield, 10)) {
                Debugger.println("yearfield not displayed.");
                SeleniumLib.takeAScreenShot("yearfield.jpg");
                return false;
            }
            return true;
        }
        catch (Exception exp1) {
            Debugger.println("Exception from checking yearfield:" + exp1);
            SeleniumLib.takeAScreenShot("yearTitle.jpg");
            return false;
        }
    }


    public boolean clickYearinputfield() {
        try {
            if (!Wait.isElementDisplayed(driver,dateOfBirthYear_patientsearch, 30)) {
                Debugger.println("Year of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Yearinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfBirthYear_patientsearch.isEnabled());
            if (!dateOfBirthYear_patientsearch.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfBirthYear_patientsearch);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthYear_patientsearch);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickYearinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Yearinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickYearinputfield_1() {
        try {
            if (!Wait.isElementDisplayed(driver,dateOfBirthYear, 30)) {
                Debugger.println("Year of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Yearinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfBirthYear.isEnabled());
            if (!dateOfBirthYear.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfBirthYear);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthYear);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickYearinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Yearinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickDayInputFieldDateOfDiagnosis() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfDay_dateOfDiagnosis, 30)) {
                Debugger.println("day of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Dayinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfDay_dateOfDiagnosis.isEnabled());
            if (!dateOfDay_dateOfDiagnosis.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfDay_dateOfDiagnosis);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfDay_dateOfDiagnosis);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickDayinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Dayinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickMonthInputFieldDateOfDiagnosis() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfMonth_dateOfDiagnosis, 30)) {
                Debugger.println("Month of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfMonth_dateOfDiagnosis.isEnabled());
            if (!dateOfMonth_dateOfDiagnosis.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfMonth_dateOfDiagnosis);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfMonth_dateOfDiagnosis);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickMonthinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickYearInputFieldDateOfDiagnosis() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfYear_dateOfDiagnosis, 30)) {
                Debugger.println("Year of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Yearinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " +dateOfYear_dateOfDiagnosis.isEnabled());
            if (!dateOfYear_dateOfDiagnosis.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfYear_dateOfDiagnosis);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfYear_dateOfDiagnosis);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickYearinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Yearinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickDayInputFieldSampleDetails() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfDay_SampleDetails, 30)) {
                Debugger.println("day of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Dayinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfDay_SampleDetails.isEnabled());
            if (!dateOfDay_SampleDetails.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfDay_SampleDetails);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfDay_SampleDetails);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickDayinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Dayinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickMonthInputFieldSampleDetails() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfMonth_SampleDetails, 30)) {
                Debugger.println("Month of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfMonth_SampleDetails.isEnabled());
            if (!dateOfMonth_SampleDetails.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfMonth_SampleDetails);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfMonth_SampleDetails);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickMonthinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickYearInputFieldSampleDetails() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfYear_SampleDetails, 30)) {
                Debugger.println("Year of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Yearinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " +dateOfYear_SampleDetails.isEnabled());
            if (!dateOfYear_SampleDetails.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfYear_SampleDetails);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfYear_SampleDetails);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickYearinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Yearinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickDayInputFieldDateOfSignature() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfDay_dateOfSignature, 30)) {
                Debugger.println("day of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Dayinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " +dateOfDay_dateOfSignature.isEnabled());
            if (!dateOfDay_SampleDetails.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfDay_dateOfSignature);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfDay_dateOfSignature);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickDayinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Dayinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickMonthInputFieldDateOfSignature() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfMonth_dateOfSignature, 30)) {
                Debugger.println("Month of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " + dateOfMonth_dateOfSignature.isEnabled());
            if (!dateOfMonth_dateOfSignature.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfMonth_dateOfSignature);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfMonth_dateOfSignature);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickMonthinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
        }
    }

    public boolean clickYearInputFieldDateOfSignature() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfYear_dateOfSignature, 30)) {
                Debugger.println("Year of Birthday field not displayed.");
                SeleniumLib.takeAScreenShot("Yearinputfield.jpg");
                return false;
            }
            Debugger.println("Status: " +dateOfYear_dateOfSignature.isEnabled());
            if (!dateOfYear_dateOfSignature.isEnabled()) {
                Wait.seconds(3);
            }
            Actions.clickElement(driver, dateOfYear_dateOfSignature);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfYear_dateOfSignature);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickYearinputfield. Exception:" + exp);
                SeleniumLib.takeAScreenShot("Yearinputfield.jpg");
                return false;
            }
        }
    }

    public boolean updatePatientDetails(String patientDetails){
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(patientDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "FirstName": {
                    seleniumLib.sendValue(firstName, paramNameValue.get(key));
                    break;
                }
                case "LastName": {
                    seleniumLib.sendValue(familyName, paramNameValue.get(key));
                    break;
                }
                case "DOB": {
                    String dobValue = paramNameValue.get(key);
                    if (dobValue != null && !dobValue.isEmpty()) {
                        String[] dobSplit = dobValue.split("-");
                        seleniumLib.sendValue(dateOfBirthDay, dobSplit[0]);
                        seleniumLib.sendValue(dateOfBirthMonth, dobSplit[1]);
                        seleniumLib.sendValue(dateOfBirthYear, dobSplit[2]);
                    }
                    break;
                }
                case "Gender": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        selectGender(administrativeGenderButton, paramNameValue.get(key));
                    }
                    break;
                }
                case "LifeStatus": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        editDropdownField(lifeStatusButton, paramNameValue.get(key));
                    }
                    break;
                }
                case "Ethnicity": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        editDropdownField(ethnicityButton, paramNameValue.get(key));
                    }
                    break;
                }
                case "Postcode": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        fillPostcodeValue(paramNameValue.get(key));
                    }
                    break;
                }
            }
        }
        return true;
    }

    public String readGenderValue() {
        try {
            if (!Wait.isElementDisplayed(driver, administrativeGenderButton, 5)) {
                Debugger.println("GenderButton is not loaded.");
                SeleniumLib.takeAScreenShot("readGenderValue.jpg");
                return null;
            }
            return administrativeGenderButton.getText();
        }
       catch (Exception exp){
                Debugger.println("Exception from readGenderValue:" + exp);
                SeleniumLib.takeAScreenShot("readGenderValue.jpg");
                return null;
            }
        }

    public boolean updateFamilyMemberDetails(String familyMemberDetails) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyMemberDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "DOB": {
                    String dobValue = paramNameValue.get(key);
                    if (dobValue != null && !dobValue.isEmpty()) {
                        String[] dobSplit = dobValue.split("-");
                        seleniumLib.sendValue(dateOfBirthDay, dobSplit[0]);
                        seleniumLib.sendValue(dateOfBirthMonth, dobSplit[1]);
                        seleniumLib.sendValue(dateOfBirthYear, dobSplit[2]);
                    }
                    break;
                }
                case "Gender": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        selectGender(genderPath, paramNameValue.get(key));
                    }
                    break;
                }
                case "LifeStatus": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        editDropdownField(lifeStatusButton, paramNameValue.get(key));
                    }
                    break;
                }
                case "Ethnicity": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        editDropdownField(ethnicityButton, paramNameValue.get(key));
                    }
                    break;
                }
                case "RelationShipToProband": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        editDropdownField(relationshipButton, paramNameValue.get(key));
                    }
                    break;
                }
            }
        }
        return true;
    }
    public boolean verifyFamilyMemberDetails(String familyMemberDetails) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyMemberDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        String actValue = "";
        String expValue = "";
        for (String key : paramsKey) {
            expValue = paramNameValue.get(key);
            switch (key) {
                case "DOB": {
                    actValue = dateOfBirthDay.getAttribute("value") + "-";
                    actValue += dateOfBirthMonth.getAttribute("value") + "-";
                    actValue += dateOfBirthYear.getAttribute("value");
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "Gender": {
                    By genderPath = By.xpath("//input[@id='administrativeGender']/../div/span/span");
                    actValue = seleniumLib.getText(genderPath);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "LifeStatus": {
                    actValue = seleniumLib.getText(lifeStatusButton);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "Ethnicity": {
                    actValue = seleniumLib.getText(ethnicityButton);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "RelationShipToProband": {
                    actValue = seleniumLib.getText(relationshipButton);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }
}//end
