package co.uk.gel.proj.pages;

import co.uk.gel.lib.Action;
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
import org.gel.models.participant.avro.PedigreeMember;
import org.gel.models.participant.avro.Referral;
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

//    @FindBy(xpath = "//label[contains(@for,'administrativeGender')]//following::div")
@FindBy(xpath = "//input[@id='administrativeGender']/../../parent::div")
    public WebElement administrativeGenderButton;

    @FindBy(css = "*[data-testid*='notification-warning']")
    public WebElement patientDetailsnotificationBanner;

    @FindBy(xpath = "//div[contains(@data-testid,'notification-warning')]//a")
    public WebElement testDirectoryLink;

    @FindBy(xpath = "//div[contains(@data-testid,'notification-warning')]")
    public WebElement textOnPatientDetailsNotificationBanner;

//    @FindBy(xpath = "//label[contains(@for,'lifeStatus')]//following::div")
@FindBy(xpath = "//input[contains(@id,'lifeStatus')]/../../parent::div")
    public WebElement lifeStatusButton;

//    @FindBy(xpath = "//label[contains(@for,'ethnicity')]//following::div")
@FindBy(xpath = "//input[contains(@id,'ethnicity')]/../../parent::div")
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

    @FindBy(xpath = "//button/span[text()='Save and continue']")
    public WebElement saveAndContinue;

    @FindBy(xpath = "//button[@type='submit'][contains(@class,'new-patient-form__submit')]")
    public WebElement createRecord;

    @FindBy(xpath = "//button[@type='submit']/span[contains(text(),'Start referral')]")
    public WebElement startReferralButton;

    @FindBy(xpath = "//button[text()='Yes, start referral']")
    public WebElement CISearchStartReferral;

    @FindBy(xpath = "//input[@name='ci-radio']")
    public WebElement selectCIRadio;

    @FindBy(xpath = "//button/span[contains(.,'Start referral') or contains(., 'Start new referral')]")
    public WebElement startNewReferralButton;

    @FindBy(xpath = "//div[@data-testid='notification-success']//span")
    public WebElement successNotification;

    @FindBy(css = "a[class*='referral-list']")
    public List<WebElement> referralListCards;

    @FindBy(css = "*[class*='referral-list__link']")
    public WebElement referralLink;

    @FindBy(css = "div[class*='css-2r6793']")
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

    @FindBy(xpath = "//input[@name='administrativeGender']/../div")
    public WebElement genderPath;


    //New Patient Record Page
    @FindBy(xpath = "//div[contains(@class,'form-header')]/p")
    public WebElement patientName;

    @FindBy(xpath = "//span[text()='Patient NGIS ID']/following-sibling::span")
    public WebElement patientNgisId;

    @FindBy(xpath = "//span[text()='Date of birth']/following-sibling::span")
    public WebElement dateOfBirth;

    @FindBy(xpath = "//span[text()='Gender']/following-sibling::span")
    public WebElement gender;

    @FindBy(xpath = "//span[text()='Life status']/following-sibling::span")
    public WebElement lifeStatus;

    @FindBy(xpath = "//span[text()='Ethnicity']/following-sibling::span")
    public WebElement ethnicity;

    @FindBy(xpath = "//span[text()='NHS Number']/following-sibling::span")
    public WebElement NHSNumber;

    @FindBy(xpath = "//span[text()='Hospital Number']/following-sibling::span")
    public WebElement hospitalNum;

    @FindBy(xpath = "//span[text()='Address']/following-sibling::span")
    public WebElement address;


    @FindBy(xpath = "//label[contains(@for,'ethnicity')]/span")
    WebElement ethnicityMissing;
    @FindBy(xpath = "//div[contains(@class,'styles_error-message')]")
    public List<WebElement> errorMessages;

    @FindBy(xpath = "//input[@id='administrativeGender']/../../parent::div")
    public WebElement genderFieldStatus;

    @FindBy(xpath = "//input[@id='lifeStatus']/../../parent::div")
    public WebElement lifeStatusFieldStatus;

    @FindBy(xpath = "//input[@id='administrativeGender']")
    public WebElement genderPatientDetails;

//    @FindBy(xpath = "//div[@id='react-select-2-option-0']")
    @FindBy(xpath = "//div[@id='react-select-6-option-0']")
    public WebElement getGenderFemale;

//    @FindBy(xpath = "//div[@id='react-select-2-option-2']")
    @FindBy(xpath = "//div[@id='react-select-6-option-2']")
    public WebElement getGenderOther;

    @FindBy(xpath = "//div[@id='react-select-6-option-3']")
    public WebElement getGenderUnknown;

    @FindBy(xpath = "//div[@id='react-select-6-option-1']")
    public WebElement getGenderMale;



    public boolean patientDetailsPageIsDisplayed() {
        try {
            Wait.forURLToContainSpecificText(driver, "/patient");
            //Wait.forElementToBeDisplayed(driver, startReferralButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in patientDetailsPageIsDisplayed:" + exp);
            return false;
        }
    }

    public boolean newPatientPageIsDisplayed() {
        try {
            Wait.forURLToContainSpecificText(driver, "/new-patient");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from newPatientPageIsDisplayed:" + exp);
            return false;
        }
    }

    public boolean newFamilyMemberPageIsDisplayed() {
        try {
            Wait.forURLToContainSpecificText(driver, "/family-members/new");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from newFamilyMemberPageIsDisplayed:" + exp);
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

    @FindBy(xpath = "//label[text()='Ethnicity']/..//div[@class='css-1hwfws3']")
    public WebElement ethnicityFieldStatus;
    public boolean fillInNewPatientDetailsWithoutAddressFields() {
        try {
            Wait.forElementToBeDisplayed(driver, title);
            newPatient.setTitle("Mr");
            title.sendKeys("Mr"); // OR //Actions.fillInValue(title, "MR");

            newPatient.setFirstName(TestUtils.getRandomFirstName());
            Action.fillInValue(firstName, newPatient.getFirstName());

            newPatient.setLastName(TestUtils.getRandomLastName());
            Action.fillInValue(familyName, newPatient.getLastName());

            String dayOfBirth = PatientSearchPage.testData.getDay();
            String monthOfBirth = PatientSearchPage.testData.getMonth();
            String yearOfBirth = PatientSearchPage.testData.getYear();

            // Date of Birth field will always be empty when accessing the New Patient "test-order/new-patient" directly
            String dobDay = seleniumLib.getText(dateOfBirthDay);
            if (dobDay == null || dobDay.isEmpty()) {
                dayOfBirth = String.valueOf(faker.number().numberBetween(10, 31));
                monthOfBirth = String.valueOf(faker.number().numberBetween(10, 12));
                yearOfBirth = String.valueOf(faker.number().numberBetween(1900, 2019));
                seleniumLib.sendValue(dateOfBirthDay, dayOfBirth);
                seleniumLib.sendValue(dateOfBirthMonth, monthOfBirth);
                seleniumLib.sendValue(dateOfBirthYear, yearOfBirth);
            }

            newPatient.setDay(dayOfBirth);
            newPatient.setMonth(monthOfBirth);
            newPatient.setYear(yearOfBirth);

            String nhsNumber = RandomDataCreator.generateRandomNHSNumber();
            newPatient.setNhsNumber(nhsNumber);
            String gender = "Male";
            selectGender(administrativeGenderButton, gender);
            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(ethnicityButton, "A - White - British");
            String hospitalNumberFake = faker.numerify("A#R##BB##");
            Action.fillInValue(hospitalNumber, hospitalNumberFake);
            // if gender is not select it will re-try to select
            if (genderFieldStatus.getText().contains("Select...")){
                selectGender(administrativeGenderButton, gender);
            }
            // if life status is not select it will re-try to select
            if (lifeStatusFieldStatus.getText().contains("Select...")){
                editDropdownField(lifeStatusButton, "Alive");
            }
            // if Ethnicity is not select it will re-try to select
            if (ethnicityFieldStatus.getText().contains("Select...")){
                editDropdownField(ethnicityButton, "A - White - British");
            }
            newPatient.setHospitalNumber(hospitalNumberFake);
            newPatient.setGender(gender);
            newPatient.setLifeStatus("Alive");
            newPatient.setEthincity("A - White - British");
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

            addressLine0.clear();
            Action.fillInValue(addressLine0, patientAddressDetails.get(0));
            addressLine1.clear();
            Action.fillInValue(addressLine1, patientAddressDetails.get(1));
            addressLine2.clear();
            Action.fillInValue(addressLine2, patientAddressDetails.get(2));
            addressLine3.clear();
            Action.fillInValue(addressLine3, patientAddressDetails.get(3));
            addressLine4.clear();
            Action.fillInValue(addressLine4, patientAddressDetails.get(4));
            newPatient.setPostCode(getRandomUKPostCode());
            postcode.clear();
            Action.fillInValue(postcode, newPatient.getPostCode());
            //Debugger.println("Expected patient address - List " + patientAddressDetails + " : " + newPatient.getPatientAddress());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInAllNewPatientDetails:" + exp);
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
            Action.fillInValue(firstName, newPatient.getFirstName());
            Action.fillInValue(familyName, newPatient.getLastName());
            seleniumLib.sendValue(dateOfBirthDay, newPatient.getDay());
            seleniumLib.sendValue(dateOfBirthMonth, newPatient.getMonth());
            seleniumLib.sendValue(dateOfBirthYear, newPatient.getYear());
            selectGender(administrativeGenderButton, "Male");
            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(ethnicityButton, "B - White - Irish");
            Action.fillInValue(hospitalNumber, faker.numerify("A#R##BB##"));
            return selectMissingNhsNumberReason(reason);
        } catch (Exception exp) {
            Debugger.println("Exception in fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason:" + exp);
            SeleniumLib.takeAScreenShot("fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason.jpg");
            return false;
        }
    }

    public boolean editDropdownField(WebElement element, String value) {
        try {
            Action.clickElement(driver, element);
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
                SeleniumLib.takeAScreenShot("PC_editDropdownField.jpg");
                return false;
            }
        }
    }

    //Family member Gender is throwing error by using existing one, so created new one.
    public boolean selectGender(WebElement element, String optionValue) {
        WebElement ddValue = null;
        try {
            try {
                Action.clickElement(driver, element);
            } catch (Exception exp1) {
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
                    Action.clickElement(driver, ddElements.get(0));
                    Wait.seconds(2);
                } catch (Exception exp1) {
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
    @FindBy(xpath = "//input[@id='noNhsNumberReason']/parent::*[1]")
    public  WebElement reasonHhsNumberMissingFieldStatus;
    public boolean selectMissingNhsNumberReason(String reason) {
        try {
            if (!Wait.isElementDisplayed(driver, noNhsNumberReasonDropdown, 15)) {
                Action.scrollToTop(driver);
            }
            try {
                Action.clickElement(driver, noNhsNumberReasonDropdown);
            } catch (Exception exp1) {
                seleniumLib.clickOnWebElement(noNhsNumberReasonDropdown);
            }
            Action.selectValueFromDropdown(noNhsNumberReasonDropdown, reason);
            if (reason.equalsIgnoreCase("Other (please provide reason)")) {
                if (Wait.isElementDisplayed(driver, otherReasonExplanation, 20)) {
                    otherReasonExplanation.sendKeys(faker.numerify("misplaced my NHS Number"));
                }
            }
            // if Reason NHS Missing dropdown Box is not select it will retry again
            if (reasonHhsNumberMissingFieldStatus.getText().contains("Select...")){
                Action.selectValueFromDropdown(noNhsNumberReasonDropdown, reason);
                if (reason.equalsIgnoreCase("Other (please provide reason)")) {
                    if (Wait.isElementDisplayed(driver, otherReasonExplanation, 20)) {
                        otherReasonExplanation.sendKeys(faker.numerify("misplaced my NHS Number"));
                    }
                }
            }
            newPatient.setReasonForNoNHSNumber(reason);
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
                Action.scrollToBottom(driver);
                return false;
            }
            seleniumLib.clickOnWebElement(createRecord);
        } catch (Exception exp) {
            try {
                Action.clickElement(driver, createRecord);
            } catch (Exception exp1) {
                Debugger.println("Exception in clickOnCreateRecord:" + exp);
                return false;
            }
        }
        if (errorMessages.size() > 0) {
            //Mostly due to ethnicity drop down selection
            Debugger.println("ERROR MESSAGES.......");
            for (int i = 0; i < errorMessages.size(); i++) {
                Debugger.println("\t" + errorMessages.get(i).getText());
                if (errorMessages.get(i).getText().contains("Life status is")) {
                    Debugger.println("Re-entering Life status.");
                    By ddByElement = By.xpath("//label[contains(@for,'lifeStatus')]/..//div[contains(@class,'placeholder')]");
                    WebElement ddWebElement = driver.findElement(ddByElement);
                    editDropdownField(ddWebElement, "Alive");
                } else if (errorMessages.get(i).getText().contains("Ethnicity")) {
                    Debugger.println("Re-entering Ethnicity.");
                    By ddByElement = By.xpath("//label[contains(@for,'ethnicity')]/..//div[contains(@class,'placeholder')]");
                    WebElement ddWebElement = driver.findElement(ddByElement);
                    editDropdownField(ddWebElement, "A - White - British");
                } else if (errorMessages.get(i).getText().contains("Gender")) {
                    Debugger.println("Re-entering Gender.");
                    By ddByElement = By.xpath("//label[contains(@for,'administrativeGender')]/..//div[contains(@class,'placeholder')]");
                    WebElement ddWebElement = driver.findElement(ddByElement);
                    selectGender(ddWebElement, "Male");
                }
            }
            seleniumLib.clickOnWebElement(createRecord);
            SeleniumLib.sleepInSeconds(3);
            //Checking again
            if (errorMessages.size() > 0) {
                for (int i = 0; i < errorMessages.size(); i++) {
                    if (errorMessages.get(i).getText().contains("Life status is")) {
                        Debugger.println("Re-entering Life status.");
                        By ddByElement = By.xpath("//label[contains(@for,'lifeStatus')]/..//div[contains(@class,'placeholder')]");
                        WebElement ddWebElement = driver.findElement(ddByElement);
                        editDropdownField(ddWebElement, "Alive");
                    } else if (errorMessages.get(i).getText().contains("Ethnicity")) {
                        Debugger.println("Re-entering Ethnicity.");
                        By ddByElement = By.xpath("//label[contains(@for,'ethnicity')]/..//div[contains(@class,'placeholder')]");
                        WebElement ddWebElement = driver.findElement(ddByElement);
                        editDropdownField(ddWebElement, "A - White - British");
                    } else if (errorMessages.get(i).getText().contains("Gender")) {
                        Debugger.println("Re-entering Gender.");
                        By ddByElement = By.xpath("//label[contains(@for,'administrativeGender')]/..//div[contains(@class,'placeholder')]");
                        WebElement ddWebElement = driver.findElement(ddByElement);
                        selectGender(ddWebElement, "Male");
                    }
                }
                seleniumLib.clickOnWebElement(createRecord);
                SeleniumLib.sleepInSeconds(3);
                if (errorMessages.size() > 0) {
                    Debugger.println("Patient could not create...");
                    Action.scrollToTop(driver);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean clickSavePatientDetailsToNGISButton() {
        try {
            if (!Wait.isElementDisplayed(driver, createRecord, 30)) {
                Debugger.println("Create Record button not present in new patient creation page.");
                Action.scrollToBottom(driver);
                return false;
            }
            seleniumLib.clickOnWebElement(createRecord);
        } catch (Exception exp) {
            try {
                Action.clickElement(driver, createRecord);
            } catch (Exception exp1) {
                Debugger.println("Exception in clickOnCreateRecord:" + exp);
                return false;
            }
        }
        return true;
    }

    public boolean patientIsCreated() {
        try {
            if (!Wait.isElementDisplayed(driver, successNotification, 30)) {
                return false;
            }
            String successMsg = Action.getText(successNotification);
            if (successMsg.equalsIgnoreCase("NGIS patient record created")) {
                return true;
            }
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception in creating the patient." + exp + "\n" + driver.getCurrentUrl());
            return false;
        }
    }

    public boolean clickStartReferralButton() {
        try {
            if (!Wait.isElementDisplayed(driver, startReferralButton, 30)) {
                Debugger.println("Start Referral Button not displayed.\n" + driver.getCurrentUrl());
                return false;
            }
            Action.clickElement(driver, startReferralButton);
            // Wait.forElementToDisappear(driver, By.xpath(startReferralButtonLocator));
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(startReferralButton);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickStartReferralButton. Exception:" + exp1 + "\n" + driver.getCurrentUrl());
                return false;
            }
        }
    }

    public boolean clickCISearchStartReferralButton() {
        try {

            if (!Wait.isElementDisplayed(driver, CISearchStartReferral, 10)) {
                Debugger.println("Start Referral Button not displayed.");
                return false;
            }
            Action.clickElement(driver, CISearchStartReferral);
            Wait.seconds(10);
            if (Wait.isElementDisplayed(driver, selectCIRadio, 20)) {
                Action.clickElement(driver, selectCIRadio);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientDetailsPage: clickStartReferralButton. Exception:" + exp);
            return false;
        }
    }

    public boolean clickStartNewReferralButton() {
        int timer = 10;
        Wait.seconds(2);
        try {
            if (!Wait.isElementDisplayed(driver, startNewReferralButton, 30)) {
                Debugger.println("Start New Referral Button not displayed.");
                return false;
            }
            String currentURL = driver.getCurrentUrl();
            //Debugger.println("Status: " + startNewReferralButton.isEnabled());
            if (!startNewReferralButton.isEnabled()) {
                Wait.seconds(3);
            }
            seleniumLib.clickOnWebElement(startReferralButton);
            //Adding verification for URL change to click again on Start Referral button again if URL does not change
            while(driver.getCurrentUrl().equals(currentURL)) {
                startNewReferralButton.click();
                Wait.seconds(1);
                if(timer==0){
                    Debugger.println("PatientDetailsPage: clickStartNewReferralButton didn't happen after trying 10 times");
                    return false;
                }
                timer--;
            }

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(startReferralButton);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickStartNewReferralButton. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clinicalIndicationIDMissingBannerIsDisplayed() {
        try {
            if (!Wait.isElementDisplayed(driver, patientDetailsnotificationBanner, 30)) {
                Debugger.println("Clinical Indication ID Missing message not displayed.");
                return false;
            }
            String text = Action.getText(patientDetailsnotificationBanner);
            if (text.isEmpty()) {
                Debugger.println("Clinical Indication ID Missing message is EMPTY.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from: clinicalIndicationIDMissingBannerIsDisplayed" + exp);
            return false;
        }
    }

    public boolean startNewReferralButtonIsDisabled() {
        try {
            String disable_attribute = startNewReferralButton.getAttribute("aria-disabled");
            if (disable_attribute != null && disable_attribute.equalsIgnoreCase("true")) {
                return true;
            }
            return false;
        } catch (Exception exp) {
            return true;
        }
    }

    public boolean clickTheGoBackLink(String expectedGoBackToPatientSearch) {
        By goBackLink = null;
        try {
            goBackLink = By.xpath("//a[contains(text(),'" + expectedGoBackToPatientSearch + "')]");
            if (!Wait.isElementDisplayed(driver, driver.findElement(goBackLink), 10)) {
                Debugger.println("Link not present:" + expectedGoBackToPatientSearch);
                return false;
            }
            Action.clickElement(driver, driver.findElement(goBackLink));
            Wait.seconds(2);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnElement(goBackLink);
                Wait.seconds(2);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception from clickTheGoBackLink:" + exp1);
                return false;
            }
        }
    }


    public boolean clickTheLinkOnNotificationBanner() {
        if (!Wait.isElementDisplayed(driver, testDirectoryLink, 30)) {
            Debugger.println("Test Directory Link is not present...");
            return false;
        }
        try {
            Action.clickElement(driver, testDirectoryLink);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(testDirectoryLink);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Test Directory Link is not shown on banner..." + exp1);
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
                return false;
            }
            if (!nhsNumber.isEnabled()) {
                Debugger.println("For a Super user, NHSNumber field is expected to be enabled and set to True, but not." + driver.getCurrentUrl());
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from nhsNumberFieldIsEnabled:" + exp);
            return false;
        }
    }

    public void nhsNumberAndDOBFieldsArePrePopulatedInNewPatientPage() {
        String DOB = PatientSearchPage.testData.getDay() + "/" + PatientSearchPage.testData.getMonth() + "/" + PatientSearchPage.testData.getYear();
        String actualDOB = seleniumLib.getText(dateOfBirthDay) + "/" + seleniumLib.getText(dateOfBirthMonth) + "/" + seleniumLib.getText(dateOfBirthYear);
        Assert.assertEquals(DOB, actualDOB);
    }

    public void verifyAndClickOnTheReferralCardOnPatientDetailsPage() {
        if (!Wait.isElementDisplayed(driver, referralCard, 70)) {
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
            Action.retryClickAndIgnoreElementInterception(driver, ethnicityButton);
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
            if (!Wait.isElementDisplayed(driver, referralLink, 60)) {
                Debugger.println("Referral Link not displayed.");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, referralCard, 60)) {
                Debugger.println("Referral Card not displayed.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("No Referrals are found for the patient:" + exp);
            return false;
        }
    }

    public boolean verifyReferralStatus(String expectedStatus) {
        try {
            if (referralStatus.size() == 0) {
                Debugger.println("Referral status is not displayed....");
                return false;
            }
            boolean isPresent = false;
            for (int i = 0; i < referralStatus.size(); i++) {
                if (expectedStatus.equalsIgnoreCase(Action.getText(referralStatus.get(i)))) {
                    isPresent = true;
                    break;
                }
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception in verifyReferralStatus:" + exp);
            return false;
        }
    }

    public boolean verifyReferralReason(String expectedReason) {
        if (!Wait.isElementDisplayed(driver, referralCancelReason, 60)) {
            Debugger.println("referralCancelReason not displayed.");
            return false;
        }
        return expectedReason.equalsIgnoreCase(Action.getText(referralCancelReason));
    }

    public boolean fillInAllFieldsNewPatientDetailsExceptNHSNumber(String reason) {
        try {

            newPatient.setTitle("Mr");
            title.sendKeys("Mr"); // OR //Actions.fillInValue(title, "MR");
            String firstNameValue = TestUtils.getRandomFirstName();
            String lastNameValue = TestUtils.getRandomLastName();
            newPatient.setFirstName(firstNameValue);
            Action.fillInValue(firstName, newPatient.getFirstName());

            newPatient.setLastName(lastNameValue);
            Action.fillInValue(familyName, newPatient.getLastName());

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
            Action.fillInValue(hospitalNumber, hospitalId);
            Action.fillInValue(addressLine0, faker.address().buildingNumber());
            Action.fillInValue(addressLine1, faker.address().streetAddressNumber());
            Action.fillInValue(addressLine2, faker.address().streetName());
            Action.fillInValue(addressLine3, faker.address().cityName());
            Action.fillInValue(addressLine4, faker.address().state());
            newPatient.setPostCode(getRandomUKPostCode());
            newPatient.setHospitalNumber(hospitalId);
            String postcodeValue = newPatient.getPostCode();
            Action.fillInValue(postcode, postcodeValue);
            // if gender field is empty then it is retry again
            if (genderFieldStatus.getText().contains("Select...")){
                selectGender(administrativeGenderButton, gender);
            }
            // if life status field is empty then it is retry again
            if (lifeStatusFieldStatus.getText().contains("Select...")){
                editDropdownField(lifeStatusButton, "Alive");
            }
            // if Ethnicity field is empty then it is retry again
            if (ethnicityFieldStatus.getText().contains("Select...")){
                editDropdownField(ethnicityButton, "A - White - British");
            }
            //Debugger.println(" Newly created patient info   : " + firstNameValue + " " + lastNameValue + " " + dayOfBirth + " " + monthOfBirth + " " + yearOfBirth + " " + gender + " " + postcodeValue);
            //Debugger.println(" Newly created patient object1: " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInAllFieldsNewPatientDetailsExceptNHSNumber:" + exp);
            SeleniumLib.takeAScreenShot("fillInAllFieldsNewPatientDetailsExceptNHSNumber.jpg");
            return false;
        }
    }

    public boolean fillInAllFieldsNewPatientDetailsWithNHSNumber(String patientNameWithSpecialCharacters) {
        try {
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
            Action.fillInValue(firstName, newPatient.getFirstName());
            Action.fillInValue(familyName, newPatient.getLastName());
            selectGender(administrativeGenderButton, gender);
            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(ethnicityButton, "A - White - British");
            Action.clickElement(driver, yesButton);
            Action.clickElement(driver, nhsNumber);
            Action.fillInValue(nhsNumber, patientNhsNumber);
            Action.clickElement(driver, nhsNumberLabel);

            Action.fillInValue(hospitalNumber, hospitalId);
            Action.fillInValue(addressLine0, faker.address().buildingNumber());
            Action.fillInValue(addressLine1, faker.address().streetAddressNumber());
            Action.fillInValue(addressLine2, faker.address().streetName());
            Action.fillInValue(addressLine3, faker.address().cityName());
            Action.fillInValue(addressLine4, faker.address().state());
            Action.fillInValue(postcode, postcodeValue);
            // if gender field is empty then it is retry again
            if (genderFieldStatus.getText().contains("Select...")){
                selectGender(administrativeGenderButton, gender);
            }
            // if life status field is empty then it is retry again
            if (lifeStatusFieldStatus.getText().contains("Select...")){
                editDropdownField(lifeStatusButton, "Alive");
            }
            // if Ethnicity field is empty then it is retry again
            if (ethnicityFieldStatus.getText().contains("Select...")){
                editDropdownField(ethnicityButton, "A - White - British");
            }
            //Debugger.println(" Newly created patient info   : " + patientTitle + " " + firstNameValue + " " + lastNameValue + " " + dayOfBirth + " " + monthOfBirth + " " + yearOfBirth + " " + gender + " " + postcodeValue);
            //Debugger.println(" Newly created patient object1: " + newPatient.getTitle() + " " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInAllFieldsNewPatientDetailsWithNHSNumber:" + exp);
            SeleniumLib.takeAScreenShot("fillInAllFieldsNewPatientDetailsWithNHSNumber.jpg");
            return false;
        }
    }

    public NewPatient getNewlyCreatedPatientData() {
        Debugger.println(" Newly created patient object2: " + newPatient.getFirstName() + " " + newPatient.getLastName() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear() + " " + newPatient.getGender() + " " + newPatient.getPostCode() + " " + newPatient.getHospitalNumber() + " " + newPatient.getLifeStatus() + " " + newPatient.getEthincity());
        return newPatient;
    }

    public boolean editPatientGenderLifeStatusAndEthnicity(String gender, String lifeStatus, String ethnicity) {
        try {
            if (Wait.isElementDisplayed(driver, administrativeGenderButton, 15)) {
                Action.retryClickAndIgnoreElementInterception(driver, clearGenderDropDownValue);
                selectGender(administrativeGenderButton, gender);
                editDropdownField(lifeStatusButton, lifeStatus);
                editDropdownField(ethnicityButton, ethnicity);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from edit drop-down details " + exp);
            return false;
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
            try {
                seleniumLib.clickOnWebElement(addDetailsToNGISButton);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception from Clicking on addPatientDetailsToNGISButton:" + exp1);
                SeleniumLib.takeAScreenShot("NoAddPatientDetailsToNGISButton.jpg");
                return false;
            }
        }
    }

    public boolean clickUpdateNGISRecordButton() {
        try {
            if (!Wait.isElementDisplayed(driver, updateNGISRecordButton, 30)) {
                Action.scrollToBottom(driver);
                return false;
            }
            Action.clickElement(driver, updateNGISRecordButton);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(updateNGISRecordButton);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception from Clicking on UpdatePatientDetailsToNGISButton:" + exp);
                return false;
            }
        }
    }

    public String getNotificationMessageForPatientCreatedOrUpdated() {
        try {
            try {
                Wait.forElementToBeDisplayed(driver, successNotification);
            } catch (Exception exp) {
                Wait.forElementToBeDisplayed(driver, successNotification);
            }

            return Action.getText(successNotification);
        } catch (Exception exp) {
            try {
                return seleniumLib.getText(successNotification);
            } catch (Exception exp1) {
                Debugger.println("Exception in getNotificationMessageForPatientCreatedOrUpdated: " + exp1);
                SeleniumLib.takeAScreenShot("getNotificationMessageForPatientCreatedOrUpdated.jpg");
                return null;
            }
        }
    }

    public List<String> getTheEthnicityDropDownValues() {
        try {
            Wait.forElementToBeClickable(driver, ethnicityButton);
            Action.clickElement(driver, ethnicityButton);
            List<String> actualEthnicityValues = new ArrayList<String>();
            for (WebElement ethnicityValue : ethnicityValues) {
                actualEthnicityValues.add(ethnicityValue.getText().trim());
            }
            //Debugger.println("Actual ethnicity values: " + actualEthnicityValues);
            return actualEthnicityValues;
        } catch (Exception exp) {
            Debugger.println("Exception in getting Ethnicity Dropdown." + exp);
            return null;
        }
    }

    public boolean fillInLastName() {
        try {
            Action.fillInValue(familyName, TestUtils.getRandomLastName());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInLastName:" + exp);
            return false;
        }
    }
    @FindBy(xpath = "//label[contains(@for,'relationship')]/..//div[@class='css-1hwfws3']")
    public WebElement relationShipStatus;

    public boolean createNewFamilyMember(NGISPatientModel familyMember) {
        try {
            //Debugger.println("Adding new Family Member...");
            selectMissingNhsNumberReason(familyMember.getNO_NHS_REASON());
            if (familyMember.getGENDER().trim().equalsIgnoreCase("Male")) {
                familyMember.setTITLE("Mr");
            }else if (familyMember.getGENDER().trim().equalsIgnoreCase("Female")){
                familyMember.setTITLE("Ms");
            }else{
                familyMember.setTITLE("Mr");
            }
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
            Action.fillInValue(firstName, familyMember.getFIRST_NAME());
            Action.fillInValue(familyName, familyMember.getLAST_NAME());
            selectGender(administrativeGenderButton, familyMember.getGENDER().trim());
            editDropdownField(lifeStatusButton, "Alive");
            editDropdownField(ethnicityButton, familyMember.getETHNICITY());
            editDropdownField(relationshipButton, familyMember.getRELATIONSHIP_TO_PROBAND());
            Action.fillInValue(hospitalNumber, familyMember.getHOSPITAL_NO());
            // if relation ship to proband is not select it will re-try again
            if (relationShipStatus.getText().contains("Select...")){
                editDropdownField(relationshipButton, familyMember.getRELATIONSHIP_TO_PROBAND());
            }
            //Address
            Action.fillInValue(addressLine0, familyMember.getADDRESS_LINE0());
            Action.fillInValue(addressLine1, familyMember.getADDRESS_LINE1());
            Action.fillInValue(addressLine2, familyMember.getADDRESS_LINE2());
            Action.fillInValue(addressLine3, familyMember.getADDRESS_LINE3());
            Action.fillInValue(addressLine4, familyMember.getADDRESS_LINE4());
            Action.fillInValue(postcode, familyMember.getPOST_CODE());
            Action.clickElement(driver, addNewPatientToReferral);
            Wait.seconds(10);//Wait for 10 seconds to create the new member
            //Removed isPatientCreated check for Family member addition as it is not needed
            FamilyMemberDetailsPage.addFamilyMemberToList(familyMember);
            //Debugger.println("Family Member Added to List: NHS:" + familyMember.getNHS_NUMBER() + ",DOB:" + familyMember.getDATE_OF_BIRTH() + ",LNAME:" + familyMember.getLAST_NAME() + ",FNAME:" + familyMember.getFIRST_NAME());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from adding mew family member:" + exp);
            //SeleniumLib.takeAScreenShot("NewFamilyMember.jpg");
            return false;
        }
    }

    public boolean fillInNHSNumber() {
        try {
            Action.fillInValue(nhsNumber, newPatient.getNhsNumber());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in fillInNHSNumber:" + exp);
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
                Action.clickElement(driver, yesButton);
                Action.clickElement(driver, nhsNumber);
                Action.fillInValue(nhsNumber, referralDetails.getNHS_NUMBER());
                Action.clickElement(driver, nhsNumberLabel);
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
            // if gender is null or empty then set gender
            if (referralDetails.getGENDER() == null || referralDetails.getGENDER().isEmpty()) {
                referralDetails.setGENDER("Male");
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
            Action.fillInValue(firstName, referralDetails.getFIRST_NAME());
            Action.fillInValue(familyName, referralDetails.getLAST_NAME());
            selectGender(administrativeGenderButton, referralDetails.getGENDER());
            // if gender is not select it will re-try to select
            if (genderFieldStatus.getText().contains("Select...")){
                selectGender(administrativeGenderButton, referralDetails.getGENDER());
            }
            editDropdownField(lifeStatusButton, referralDetails.getLIFE_STATUS());
            // if life status is not select it will re-try to select
            if (lifeStatusFieldStatus.getText().contains("Select...")){
                editDropdownField(lifeStatusButton, referralDetails.getLIFE_STATUS());
            }
            editDropdownField(ethnicityButton, referralDetails.getETHNICITY());
            Action.fillInValue(hospitalNumber, referralDetails.getHOSPITAL_NO());
            // if Ethnicity is not select it will re-try to select
            if (ethnicityFieldStatus.getText().contains("Select...")){
                editDropdownField(ethnicityButton, referralDetails.getETHNICITY());
            }
            //Address
            Action.fillInValue(addressLine0, referralDetails.getADDRESS_LINE0());
            Action.fillInValue(addressLine1, referralDetails.getADDRESS_LINE1());
            Action.fillInValue(addressLine2, referralDetails.getADDRESS_LINE2());
            Action.fillInValue(addressLine3, referralDetails.getADDRESS_LINE3());
            Action.fillInValue(addressLine4, referralDetails.getADDRESS_LINE4());
            Action.fillInValue(postcode, referralDetails.getPOST_CODE());
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
            //Observed undefined attached in the URL sometime....This is to verify the URL the moment
            Debugger.println("NewReferralCreationError:URL:" + driver.getCurrentUrl());
            return false;
        }
    }

    public List<String> getActualPatientAddressOnPatientDetailPage() {
        try {
            Wait.forElementToBeDisplayed(driver, addressLine0);
            List<String> actualPatientAddress = new ArrayList<>();

            actualPatientAddress.add(Action.getValue(addressLine0));
            actualPatientAddress.add(Action.getValue(addressLine1));
            actualPatientAddress.add(Action.getValue(addressLine2));
            actualPatientAddress.add(Action.getValue(addressLine3));
            actualPatientAddress.add(Action.getValue(addressLine4));

            //Debugger.println("Actual patient address in patient detail page " + actualPatientAddress);
            return actualPatientAddress;
        } catch (Exception exp) {
            Debugger.println("Exception getting Actual Patient Address:" + exp);
            return null;
        }
    }

    public void fillInHospitalNo() {
        Action.fillInValue(hospitalNumber, faker.numerify("A#R##BB##"));
    }

    public void fillInFirstName() {
        Action.fillInValue(firstName, TestUtils.getRandomFirstName());
    }

    public boolean verifyPatientDetails(String patientDetails) {
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
                    By genderPath = By.xpath("//input[@id='administrativeGender']/parent::div/../../div/span/span");
                    actValue = seleniumLib.getText(genderPath);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "LifeStatus": {
                    By lifeStatusPath = By.xpath("//input[@id='lifeStatus']/parent::div/../../div/span/span");
                    actValue = seleniumLib.getText(lifeStatusPath);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "Ethnicity": {
                    By ethnicityPath = By.xpath("//input[@name='ethnicity']/parent::div/div/div/div/span/span");
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
                return false;
            }
            return true;
        } catch (NoSuchElementException exp) {
            Action.scrollToBottom(driver);
            WebElement relationToProbandElement = driver.findElement(By.xpath(actValue));
            if (!Wait.isElementDisplayed(driver, relationToProbandElement, 10)) {
                Debugger.println("Relation to Proband element not visible.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception verifyRelationshipToProbandDropDownShowsRecentlyUsedSuggestion." + exp);
            return false;
        }
    }

    public boolean verifyTheSubmittedReferrals() {
        try {
            Wait.forElementToBeDisplayed(driver, createdAndSubmittedReferralsTitle);
            //List of Total Referrals submitted  - both proband and family members
            if (referralIDOfCreatedReferrals.size() == 0) {
                Debugger.println("No referral created and submitted.");
                return false;
            }
            //List of  Referrals submitted  - only family members
            if (relationshipToProbandReferralID.size() == 0) {
                Debugger.println("No family member added with all the referral present in the page.");
                return false;
            }
            if (referralIDOfCreatedReferrals.size() <= relationshipToProbandReferralID.size()) {
                Debugger.println("Expected details of both proband and family referrals submitted.");
                return false;
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheSubmittedReferrals: " + exp);
            return false;
        }
    }

    public boolean verifyTheSubmittedReferralCardsAreClickable() {
        try {
            if (submittedReferralCardsList.size() == 0) {
                return false;
            }
            if (!Action.isTabClickable(driver, submittedReferralCardsList.size(), submittedReferralCardsList)) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    public boolean verifyColorOfCreateRecordButton(String expectedColor) {
        try {
            if (!Wait.isElementDisplayed(driver, createRecord, 30)) {
                Debugger.println("Create Record Button not displayed.");
                return false;
            }
            String buttonBgColor = createRecord.getCssValue("background-color");
            if (buttonBgColor == null) {
                Debugger.println("Button background color attribute is not present");
                return false;
            }
            if (!buttonBgColor.equalsIgnoreCase(StylesUtils.convertFontColourStringToCSSProperty(expectedColor))) {
                Debugger.println("Actual button color :" + buttonBgColor + ", But Excepted button color :" + expectedColor);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyColorOfCreateRecordButton. " + exp);
            return false;
        }
    }

    public boolean clickOnSaveAndContinueButton() {
        String currentURL = driver.getCurrentUrl();
        try {
            //This is different from Referral page save and Continue
            if (!Wait.isElementDisplayed(driver, saveAndContinue, 10)) {
                Debugger.println("saveAndContinue button not exists.");
                return false;
            }
            Action.clickElement(driver, saveAndContinue);
            //It will wait 3 seconds and then verifies if the URL changes
            //If the URL didn't change, it will click again on Save and Continue button
            Wait.seconds(3);
            if(currentURL.equalsIgnoreCase(driver.getCurrentUrl())){
                Action.clickElement(driver, saveAndContinue);
                Debugger.println("Clicking Save and Continue button again.");
            }
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(saveAndContinue);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception from saveAndContinue:" + exp);
                return false;
            }
        }
    }

    public boolean fillPostcodeValue(String postcode) {
        try {
            if (!Wait.isElementDisplayed(driver, postcodeField, 20)) {
                Debugger.println("The Postcode field is not displayed in the edit patient details page." + driver.getCurrentUrl());
                return false;
            }
            Action.clearInputField(postcodeField);
            postcodeField.sendKeys(postcode);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in filling the postcode: " + exp);
            return false;
        }
    }

    public boolean verifyPostcodeFormatInAddress(String formattedPostcode) {
        try {
            if (!Wait.isElementDisplayed(driver, addressField, 20)) {
                Debugger.println("The address is not displayed." + driver.getCurrentUrl());
                return false;
            }
            String actualAddress = addressField.getText();
            if (!actualAddress.contains(formattedPostcode)) {
                Debugger.println("The postcode format expected is:" + formattedPostcode + " But actual address is:" + actualAddress);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from checking the postcode format in address:" + exp);
            return false;
        }
    }

    public boolean verifyPostcodeFormatInPD(String formattedPostcode) {
        try {
            if (!Wait.isElementDisplayed(driver, postcodeField, 20)) {
                Debugger.println("The address is not displayed." + driver.getCurrentUrl());
                return false;
            }
            String actualPostcode = postcodeField.getAttribute("value");
            if (!actualPostcode.equalsIgnoreCase(formattedPostcode)) {
                Debugger.println("The postcode format expected is:" + formattedPostcode + " But actual is:" + actualPostcode);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from checking the postcode format in address:" + exp);
            return false;
        }
    }

    public String verifyPatientDetails_NGIS() {
        try {
            String actualPrefix = title.getAttribute("value");
            String actualFirstName = firstName.getAttribute("value");
            String actualLastName = familyName.getAttribute("value");
            String actualFullDOB = dateOfBirthDay.getAttribute("value") + "/" + dateOfBirthMonth.getAttribute("value") + "/" + dateOfBirthYear.getAttribute("value");
            String actualGender = genderFieldStatus.getText();
            String actualNHSNumber = nhsNumber.getAttribute("value");

            NewPatient newPatient = getNewlyCreatedPatientData();
            String expectedDOB = newPatient.getDay() + "/" + newPatient.getMonth() + "/" + newPatient.getYear();
            if (!newPatient.getTitle().equalsIgnoreCase(actualPrefix)) {
                return "Expected Prefix: " + newPatient.getTitle() + ",But Actual:" + actualPrefix;
            }
            if (!newPatient.getFirstName().equalsIgnoreCase(actualFirstName)) {
                return "Expected FirstName: " + newPatient.getFirstName() + ",But Actual:" + actualFirstName;
            }
            if (!newPatient.getLastName().equalsIgnoreCase(actualLastName)) {
                return "Expected LastName: " + newPatient.getLastName() + ",But Actual:" + actualLastName;
            }
            if (!expectedDOB.equalsIgnoreCase(actualFullDOB)) {
                return "Expected DOB: " + actualFullDOB + ",But Actual:" + actualFullDOB;
            }
            if (!newPatient.getGender().equalsIgnoreCase(actualGender)) {
                return "Expected Gender: " + newPatient.getGender() + ",But Actual:" + actualGender;
            }
            if (actualNHSNumber != null && !actualNHSNumber.isEmpty()) {
                if (!newPatient.getNhsNumber().equalsIgnoreCase(actualNHSNumber)) {
                    return "Expected NHSNumber: " + newPatient.getGender() + ",But Actual:" + actualNHSNumber;
                }
            }
            return "Success";
        } catch (Exception exp) {
            Debugger.println("Could not verify NGIS Patient Details:" + exp + "\n" + driver.getCurrentUrl());
            return "Could not verify NGIS Patient details:" + exp;
        }
    }

    public String fillDateOfBirth(String dateOfBirth) {
        try {
            String[] dobSplit = dateOfBirth.split("-");
            seleniumLib.sendValue(dateOfBirthDay, dobSplit[0]);
            seleniumLib.sendValue(dateOfBirthMonth, dobSplit[1]);
            seleniumLib.sendValue(dateOfBirthYear, dobSplit[2]);
            return "Success";
        } catch (Exception exp) {
            Debugger.println("Could not fill Date Of Birth: " + exp + "\n" + driver.getCurrentUrl());
            return "Could not fill Date Of Birth: " + exp;
        }
    }

    public String clearDateOfBirth() {
        try {
            if (!seleniumLib.isElementPresent(dateOfBirthDay)) {
                return "Date of birth fields not displayed.";
            }
            Action.clearInputField(dateOfBirthDay);
            Action.clearInputField(dateOfBirthMonth);
            Action.clearInputField(dateOfBirthYear);
            return "Success";
        } catch (Exception exp) {
            Debugger.println("Could not Clear Date Of Birth: " + exp + "\n" + driver.getCurrentUrl());
            return "Could not Clear Date Of Birth: " + exp;
        }
    }


    public String fillBirthday(String Birthday) {
        try {
            seleniumLib.sendValue(dateOfBirthDay, Birthday);
            return "Success";
        } catch (Exception exp) {
            Debugger.println("Could not fill Date Of Birth: " + exp + "\n" + driver.getCurrentUrl());
            return "Could not fill Date Of Birthday: " + exp;
        }
    }

    public String fillBirthYear(String BirthYear) {
        try {
            seleniumLib.sendValue(dateOfBirthYear, BirthYear);
            return "Success";
        } catch (Exception exp) {
            Debugger.println("Could not fill Date Of Birth: " + exp + "\n" + driver.getCurrentUrl());
            return "Could not fill month Of Birthyear: " + exp;
        }
    }


    public boolean dayField() {
        try {
            if (!Wait.isElementDisplayed(driver, dayfield, 10)) {
                Debugger.println("dayfield not displayed.");
                return false;
            }
            return true;
        } catch (Exception exp1) {
            Debugger.println("Exception from checking dayfield:" + exp1);
            return false;
        }
    }

    public boolean clickDayinputfield() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfBirthDay_patientsearch, 30)) {
                Debugger.println("day of Birthday field not displayed.");
                return false;
            }
            if (!dateOfBirthDay_patientsearch.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfBirthDay_patientsearch);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthDay);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickDayinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickDayinputfield_1() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfBirthDay, 30)) {
                Debugger.println("day of Birthday field not displayed.");
                return false;
            }
            Debugger.println("Status: " + dateOfBirthDay.isEnabled());
            if (!dateOfBirthDay.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfBirthDay);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthDay);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickDayinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean monthfield() {
        try {
            if (!Wait.isElementDisplayed(driver, monthfield, 10)) {
                Debugger.println("monthfield not displayed.");
                return false;
            }
            return true;
        } catch (Exception exp1) {
            Debugger.println("Exception from checking monthfield:" + exp1);
            return false;
        }
    }


    public boolean clickMonthinputfield() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfBirthMonth_patientsearch, 30)) {
                Debugger.println("Month of Birthday field not displayed.");
                return false;
            }
            Debugger.println("Status: " + dateOfBirthMonth_patientsearch.isEnabled());
            if (!dateOfBirthMonth_patientsearch.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfBirthMonth_patientsearch);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthMonth_patientsearch);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickMonthinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickMonthinputfield_1() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfBirthMonth, 30)) {
                Debugger.println("Month of Birthday field not displayed.");
                return false;
            }
            //Debugger.println("Status: " + dateOfBirthMonth.isEnabled());
            if (!dateOfBirthMonth.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfBirthMonth);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthMonth);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickMonthinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean yearfield() {
        try {
            if (!Wait.isElementDisplayed(driver, yearfield, 10)) {
                Debugger.println("yearfield not displayed.");
                return false;
            }
            return true;
        } catch (Exception exp1) {
            Debugger.println("Exception from checking yearfield:" + exp1);
            return false;
        }
    }


    public boolean clickYearinputfield() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfBirthYear_patientsearch, 30)) {
                Debugger.println("Year of Birthday field not displayed.");
                return false;
            }
            //Debugger.println("Status: " + dateOfBirthYear_patientsearch.isEnabled());
            if (!dateOfBirthYear_patientsearch.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfBirthYear_patientsearch);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthYear_patientsearch);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickYearinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickYearinputfield_1() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfBirthYear, 30)) {
                Debugger.println("Year of Birthday field not displayed.");
                return false;
            }
            Debugger.println("Status: " + dateOfBirthYear.isEnabled());
            if (!dateOfBirthYear.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfBirthYear);

            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfBirthYear);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickYearinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickDayInputFieldDateOfDiagnosis() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfDay_dateOfDiagnosis, 30)) {
                Debugger.println("day of Birthday field not displayed.");
                return false;
            }
            Debugger.println("Status: " + dateOfDay_dateOfDiagnosis.isEnabled());
            if (!dateOfDay_dateOfDiagnosis.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfDay_dateOfDiagnosis);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfDay_dateOfDiagnosis);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickDayinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickMonthInputFieldDateOfDiagnosis() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfMonth_dateOfDiagnosis, 30)) {
                Debugger.println("Month of Birthday field not displayed.");
                return false;
            }
            //Debugger.println("Status: " + dateOfMonth_dateOfDiagnosis.isEnabled());
            if (!dateOfMonth_dateOfDiagnosis.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfMonth_dateOfDiagnosis);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfMonth_dateOfDiagnosis);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickMonthinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickYearInputFieldDateOfDiagnosis() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfYear_dateOfDiagnosis, 30)) {
                Debugger.println("Year of Birthday field not displayed.");
                return false;
            }
            //Debugger.println("Status: " + dateOfYear_dateOfDiagnosis.isEnabled());
            if (!dateOfYear_dateOfDiagnosis.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfYear_dateOfDiagnosis);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfYear_dateOfDiagnosis);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickYearinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickDayInputFieldSampleDetails() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfDay_SampleDetails, 30)) {
                Debugger.println("day of Birthday field not displayed.");
                return false;
            }
            Debugger.println("Status: " + dateOfDay_SampleDetails.isEnabled());
            if (!dateOfDay_SampleDetails.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfDay_SampleDetails);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfDay_SampleDetails);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickDayinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickMonthInputFieldSampleDetails() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfMonth_SampleDetails, 30)) {
                Debugger.println("Month of Birthday field not displayed.");
                return false;
            }
            Debugger.println("Status: " + dateOfMonth_SampleDetails.isEnabled());
            if (!dateOfMonth_SampleDetails.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfMonth_SampleDetails);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfMonth_SampleDetails);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickMonthinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickYearInputFieldSampleDetails() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfYear_SampleDetails, 30)) {
                Debugger.println("Year of Birthday field not displayed.");
                return false;
            }
            //Debugger.println("Status: " + dateOfYear_SampleDetails.isEnabled());
            if (!dateOfYear_SampleDetails.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfYear_SampleDetails);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfYear_SampleDetails);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickYearinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickDayInputFieldDateOfSignature() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfDay_dateOfSignature, 30)) {
                Debugger.println("day of Birthday field not displayed.");
                return false;
            }
            //Debugger.println("Status: " + dateOfDay_dateOfSignature.isEnabled());
            if (!dateOfDay_SampleDetails.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfDay_dateOfSignature);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfDay_dateOfSignature);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickDayinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickMonthInputFieldDateOfSignature() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfMonth_dateOfSignature, 30)) {
                Debugger.println("Month of Birthday field not displayed.");
                //SeleniumLib.takeAScreenShot("Monthinputfield.jpg");
                return false;
            }
            if (!dateOfMonth_dateOfSignature.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfMonth_dateOfSignature);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfMonth_dateOfSignature);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickMonthinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean clickYearInputFieldDateOfSignature() {
        try {
            if (!Wait.isElementDisplayed(driver, dateOfYear_dateOfSignature, 30)) {
                Debugger.println("Year of Birthday field not displayed.");
                return false;
            }
            //Debugger.println("Status: " + dateOfYear_dateOfSignature.isEnabled());
            if (!dateOfYear_dateOfSignature.isEnabled()) {
                Wait.seconds(3);
            }
            Action.clickElement(driver, dateOfYear_dateOfSignature);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(dateOfYear_dateOfSignature);
                return true;
            } catch (Exception exp1) {
                Debugger.println("PatientDetailsPage: clickYearinputfield. Exception:" + exp);
                return false;
            }
        }
    }

    public boolean updatePatientDetails(String patientDetails) {
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
                        editDropdownField(ethnicityFieldStatus, paramNameValue.get(key));
                    }
                    break;
                }
                case "Postcode": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        fillPostcodeValue(paramNameValue.get(key));
                    }
                    break;
                }
                case "Title": {
                    seleniumLib.sendValue(title, paramNameValue.get(key));
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
                return null;
            }
            return administrativeGenderButton.getText();
        } catch (Exception exp) {
            Debugger.println("Exception from readGenderValue:" + exp);
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
                        editDropdownField(ethnicityFieldStatus, paramNameValue.get(key));
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
                    By genderPath = By.xpath("//input[@id='administrativeGender']/parent::div/../../div/span/span");
                    actValue = seleniumLib.getText(genderPath);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "LifeStatus": {
                    actValue = seleniumLib.getText(lifeStatusFieldStatus);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "Ethnicity": {
                    actValue = seleniumLib.getText(ethnicityFieldStatus);
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

    public boolean readEthnicityMandatoryStatus() {
        try {
            if (!Wait.isElementDisplayed(driver, ethnicityMissing, 10)) {
                Debugger.println("Ethnicity field is not loaded.");
                return false;
            }
            Debugger.println("The status is-" + ethnicityMissing.getText());
            if (!ethnicityMissing.getText().equals("✱")) {
                Debugger.println("Ethnicity field is not missing value.");
                return false;
            }
            Debugger.println("The ethnicity is mandatory status is-" + ethnicityMissing.getText());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from readEthnicityStatus:" + exp);
            return false;
        }
    }

    public boolean startNewReferralButtonIsEnabled() {
        try {
            if (!Wait.isElementDisplayed(driver, startNewReferralButton, 30)) {
                Debugger.println("Start new referral button not displayed");
                return false;
            }
            if (!startNewReferralButton.isEnabled()) {
                Debugger.println("Start new referral button not enabled");
                    return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Start new referral button not enabled");
            return false;
        }
    }

    public boolean verifyTheGoBackLink(String expectedGoBackToPatientSearch) {
        By goBackLink = null;
        try {
            goBackLink = By.xpath("//a[contains(text(),'" + expectedGoBackToPatientSearch + "')]");
            if (!Wait.isElementDisplayed(driver, driver.findElement(goBackLink), 10)) {
                Debugger.println("Link not present:" + expectedGoBackToPatientSearch);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheGoBackLink:" + exp);
            return false;
        }
    }

    public boolean verifyThePatientRecordDetails() {
        try {
            NewPatient newPatient = getNewlyCreatedPatientData();
            String expectedDOB = newPatient.getDay() + "-" + newPatient.getMonth() + "-" + newPatient.getYear();
            String expectedDobInMonth = TestUtils.getDOBInMonthFormat(expectedDOB);
            String[] actualDOB = dateOfBirth.getText().trim().split(" ");
            if (!actualDOB[0].equalsIgnoreCase(expectedDobInMonth)) {
                Debugger.println("Expected DOB: " + expectedDobInMonth + ",But Actual:" + actualDOB);
                return false;
            }
            String expectedPatientName = newPatient.getLastName() + ", " + newPatient.getFirstName() + " (" + newPatient.getTitle() + ")";
            if (!expectedPatientName.equalsIgnoreCase(patientName.getText().trim())) {
                Debugger.println("Expected LastName: " + expectedPatientName + ",But Actual:" + patientName);
                return false;
            }
            if (!newPatient.getGender().equalsIgnoreCase(gender.getText().trim())) {
                Debugger.println("Expected Gender: " + newPatient.getGender() + ",But Actual:" + gender.getText().trim());
                return false;
            }
            if (!newPatient.getLifeStatus().equalsIgnoreCase(lifeStatus.getText().trim())) {
                Debugger.println("Expected LifeStatus: " + newPatient.getLifeStatus() + ",But Actual:" + lifeStatus.getText().trim());
                return false;
            }
            if (!newPatient.getEthincity().equalsIgnoreCase(ethnicity.getText().trim())) {
                Debugger.println("Expected Ethincity: " + newPatient.getEthincity() + ",But Actual:" + ethnicity.getText().trim());
                return false;
            }
            String expReason = newPatient.getReasonForNoNHSNumber();
            if (!expReason.equalsIgnoreCase(NHSNumber.getText().trim())) {
                Debugger.println("Expected NHSNumber: " + newPatient.getNhsNumber() + ",But Actual:" + NHSNumber.getText().trim());
                return false;
            }
            if (!newPatient.getHospitalNumber().equalsIgnoreCase(hospitalNum.getText().trim())) {
                Debugger.println("Expected HospitalNumber: " + newPatient.getHospitalNumber() + ",But Actual:" + hospitalNum.getText().trim());
                return false;
            }
            String actualAddress = newPatient.getPatientAddress().get(0) + ", " + newPatient.getPatientAddress().get(1) + ", " + newPatient.getPatientAddress().get(2) + ", " + newPatient.getPatientAddress().get(3) + ", " + newPatient.getPatientAddress().get(4) + ", " + newPatient.getPostCode();
            if (!(address.getText().trim()).equalsIgnoreCase(actualAddress.trim())) {
                Debugger.println("Expected Address: " + address.getText().trim() + ",But Actual:" + actualAddress);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Could not verify NGIS Patient Details in Patient Record Page:" + exp + "\n" + driver.getCurrentUrl());
            return false;
        }
    }

    public boolean verifyPatientNgisId() {
        try {
            if (!Wait.isElementDisplayed(driver, patientNgisId, 10)) {
                Debugger.println("Patient NGIS Id not displayed.");
                return false;
            }
            return true;
        } catch (Exception exp1) {
            Debugger.println("Exception from verifyPatientNgisId:" + exp1);
            return false;
        }
    }

    public boolean verifyPostCodeErrorMessage(String expMessage) {
        try {
            Action.clickElement(driver, driver.findElement(By.xpath("//label[text()='Postcode']")));
            WebElement postCodeFieldError = driver.findElement(By.xpath("//div[text()='This postcode is not in a valid format']"));
            if (!Wait.isElementDisplayed(driver, postCodeFieldError, 20)) {
                Debugger.println("The Post Code field error message not displayed");
                return false;
            }
            String actualPostcodeErrorMessage = postCodeFieldError.getText();
            if (!actualPostcodeErrorMessage.equalsIgnoreCase(expMessage)) {
                Debugger.println("The postcode field expected error is:" + expMessage + " But actual is:" + actualPostcodeErrorMessage);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from checking the postcode error message:" + exp);
            return false;
        }
    }

    public boolean verifyNoMergeWarningNotificationBannerDisplayed() {
        try {
            if (!Wait.isElementDisplayed(driver, textOnPatientDetailsNotificationBanner, 10)) {
                Debugger.println("textOnPatientDetailsNotificationBanner not loaded.");
                SeleniumLib.takeAScreenShot("textOnPatientDetailsNotificationBanner.jpg");
                return true;
            }
            return false;
        } catch (Exception exp1) {
            Debugger.println("Exception from checking textOnPatientDetailsNotificationBanner:" + exp1);
            SeleniumLib.takeAScreenShot("textOnPatientDetailsNotificationBanner.jpg");
            return false;
        }
    }

    //From JSON data for JSON Framework
    public boolean fillInPatientDetailsFromJson(String caseType,String reason, Referral referralObject) {
        try {
            String firstNameValue = TestUtils.getRandomFirstName();
            String lastNameValue = TestUtils.getRandomLastName();
            newPatient.setFirstName(firstNameValue);
            newPatient.setLastName(lastNameValue);
            String dayOfBirth = PatientSearchPage.testData.getDay();
            String monthOfBirth = PatientSearchPage.testData.getMonth();
            String yearOfBirth = PatientSearchPage.testData.getYear();
            newPatient.setDay(dayOfBirth);
            newPatient.setMonth(monthOfBirth);
            newPatient.setYear(yearOfBirth);
            String nhsNumber = RandomDataCreator.generateRandomNHSNumber();
            newPatient.setNhsNumber(nhsNumber);
            String gender = null;
            String lifeStatus = null;
            if (caseType.equalsIgnoreCase("Cancer")) {
                gender = referralObject.getCancerParticipant().getSex().name();
                try {
                    lifeStatus = referralObject.getPedigree().getMembers().get(0).getLifeStatus().name();
                }catch (NullPointerException exp){
                    Debugger.println("Exception from reading Life Status from given Json: "+exp+System.lineSeparator()+"....Continuing with life status as 'alive'.");
                    lifeStatus = "ALIVE";
                }
            } else {
                List<Integer> probandMemberNum = TestUtils.getMemberPositionDetailsFromJson(referralObject, "Proband");
                if (probandMemberNum == null) {
                    SeleniumLib.takeAScreenShot("NoProbandDetails.jpg");
                    Assert.fail("Could not get member details from JSON.");
                }
                Debugger.println("The position of proband member participant is " + probandMemberNum.toString());
                PedigreeMember probandMember = referralObject.getPedigree().getMembers().get(probandMemberNum.get(0));
                gender= probandMember.getSex().name();
                Debugger.println("The Gender value "+gender);
                lifeStatus=probandMember.getLifeStatus().name();
                Debugger.println("The life status value "+lifeStatus);
            }
            //modify the values from JSON to convert from Uppercase to lower case
            //for Gender
            String newGenderValue=TestUtils.convertUpperCaseJSONDataToProperFormat(gender);
            Debugger.println("Gender value old:- "+gender+" new- "+newGenderValue);
            newPatient.setGender(newGenderValue);
            //for life status
            String newlifeStatus=TestUtils.convertUpperCaseJSONDataToProperFormat(lifeStatus);
            Debugger.println("Life status is- "+lifeStatus+" corrected value- "+newlifeStatus);

            String hospitalId = faker.numerify("A#R##BB##");
            newPatient.setHospitalNumber(hospitalId);
            newPatient.setPostCode(getRandomUKPostCode());
            //Fill in the values

            Action.fillInValue(firstName, newPatient.getFirstName());
            Action.fillInValue(familyName, newPatient.getLastName());
            selectMissingNhsNumberReason(reason);
            selectGender(administrativeGenderButton, newGenderValue);
            if (newGenderValue.equalsIgnoreCase("Male")) {
                newPatient.setTitle("Mr");
                title.sendKeys("Mr");
            }else if (newGenderValue.equalsIgnoreCase("Female")){
                newPatient.setTitle("Ms");
                title.sendKeys("Ms");
            }
            editDropdownField(lifeStatusButton, newlifeStatus);

            editDropdownField(ethnicityButton, "A - White - British");
            selectMissingNhsNumberReason(reason);
            if (reason.equalsIgnoreCase("Other (please provide reason)")) {
                Wait.forElementToBeDisplayed(driver, otherReasonExplanation);
                otherReasonExplanation.sendKeys(faker.numerify("misplaced my NHS Number"));
            }
            Action.fillInValue(hospitalNumber, hospitalId);
            Action.fillInValue(addressLine0, faker.address().buildingNumber());
            Action.fillInValue(addressLine1, faker.address().streetAddressNumber());
            Action.fillInValue(addressLine2, faker.address().streetName());
            Action.fillInValue(addressLine3, faker.address().cityName());
            Action.fillInValue(addressLine4, faker.address().state());
            Action.fillInValue(postcode, newPatient.getPostCode());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInPatientDetailsFromJson:" + exp);
            SeleniumLib.takeAScreenShot("fillInPatientDetailsFromJson.jpg");
            return false;
        }
    }

    public boolean checkGenderDropdownSuggestionInPatientDetails(String expectedGenderValue) {
        try{
            genderPatientDetails.sendKeys(expectedGenderValue);
            Wait.seconds(1);
            String actualGender ="";
            if (expectedGenderValue.equalsIgnoreCase("F")) {
                actualGender = getGenderFemale.getText();
                getGenderFemale.click();
            } else if (expectedGenderValue.equalsIgnoreCase("O")) {
                actualGender = getGenderOther.getText();
                getGenderOther.click();
            } else if (expectedGenderValue.equalsIgnoreCase("U")) {
                actualGender = getGenderUnknown.getText();
                getGenderUnknown.click();
            } else if (expectedGenderValue.equalsIgnoreCase("M")){
                actualGender = getGenderMale.getText();
                getGenderMale.click();
            }else{
                Debugger.println("Please check your input..");
                return false;
            }

            Wait.seconds(1);
            String expectedGender = expectedGenderValue;

            if (actualGender.charAt(0) != expectedGender.charAt(0)) {
                System.out.println("Values are NOT matching Expected Gender= " + expectedGender + ", Actual Gender: " + actualGender);
                return false;
            }
            return true;
        }catch (Exception msg) {
            SeleniumLib.takeAScreenShot("CheckingGenderInPatientDetailsFailed.jpg");
            System.out.println("Failed to check gender in patient details from clickingGenderDropdownInPatientdetails " + msg);
            return false;
        }
    }

}//end
