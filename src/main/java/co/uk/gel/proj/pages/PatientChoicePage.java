package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class PatientChoicePage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public PatientChoicePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    String patientChoiceLink = "//a[contains(text(),'New patient choice')]";

    @FindBy(xpath = "//h2[contains(text(),'Form library')]")
    WebElement formLibraryTitle;

    @FindBy(xpath = "//h4[@class='form-section-header']")
    List<WebElement> patientChoiceFormsAndInformationTitle;

    @FindBy(xpath = "//a[@class='form-link']")
    List<WebElement> formLibraryLinks;

    @FindBy(xpath = "//p[contains(text(),'Before submitting a referral')]")
    public WebElement helpTextLabel;


    @FindBy(xpath = "//button[@aria-label='edit button']")
    public List<WebElement> editPatientChoice2;

    String selectedOption = "//div[@class='current-value'][contains(text(),'dummyOption')]";
    String editButton = "//div[contains(text(),'dummyOption')]/following::button[@class='edit-button btn']";

    @FindBy(xpath = "//button[@aria-label='edit button']")
    public WebElement editPatientChoice;

    @FindBy(xpath = "//h2[contains(text(),'New patient choice form')]")
    public WebElement newPatientChoiceFormTitle;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'Recording clinician name')]/../input[@type='text']")
    WebElement recordingClinicianNameInput;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'Patient Hospital Number')]/../input[@type='text']")
    WebElement patientHospitalNumberInput;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'Admin Name / Email')]/../input[@type='text']")
    WebElement adminNameInput;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    public WebElement continueButton;

    @FindBy(xpath = "//button[contains(text(),'Save and continue')]")
    public WebElement saveAndContinueButton;

    @FindBy(xpath = "//button[contains(text(),'Upload document')]")
    public WebElement uploadDocumentOption;

    @FindBy(xpath = "//label[contains(@class,'upload-document-button')]")
    public WebElement uploadDocumentButton;

    @FindBy(xpath = "//div[@class='m-signature-pad--body']//canvas")
    WebElement signaturePad;
    @FindBy(xpath = "//*[contains(@id,'signature-pad')]//child::canvas")
    public WebElement signatureSection;

    @FindBy(xpath = "//label[contains(text(),'Parent/Guardian first name')]/following-sibling::input")
    WebElement parentFirstName;

    @FindBy(xpath = "//label[contains(text(),'Parent/Guardian last name')]/following-sibling::input")
    WebElement parentLastName;

    @FindBy(xpath = "//h5[text()='Parent/Guardian signature']")
    WebElement parentGuardianSignatureLabel;

    @FindBy(xpath = "//button[@class='btn submit-signature-button']")
    WebElement submitPatientChoice;

    @FindBy(xpath = "//span[contains(text(),'Patient choice status')]/following-sibling::span[contains(@class,'css-')]")///span") //span[@class='css-1ksowpi'][text()='Patient choice status']/../span[@class='css-1a6lz9d']/span")
    WebElement patientChoiceStatus;

    String patientChoiceCategory = "//label[contains(@class,'radio-container')][text()='dummyCategory']";
    String testType = "//label[contains(@class,'radio-container')][text()='dummyTestType']";
    String patientChoice = "//label[contains(@class,'radio-container')][contains(text(),'dummyChoice')]";
    String childAssent = "//label[contains(@class,'radio-container')][contains(text(),'dummyAssent')]";

    //For PatientInformation Identifiers
    String patientList = "//div[contains(@class,'styles_participant-list_')]/div[contains(@class,'css-1')]";
    String firstNameLastName = "//div[contains(@class,'styles_participant-list_')]//span[contains(@class,'css-')]//h2";
    String probandBeingTested = "//span[contains(@class,'child-element')]";
    String bornInformation = "//span[contains(@id,'dateOfBirth')]";
    String genderInformation = "//span[contains(@id,'gender')]";
    String nhsNumberInformation = "//span[contains(@id,'nhsNumber')]";
    String ngsIdInformation = "//span[contains(@id,'ngisId')]";
    String patientChoiceInformation = "//span[contains(@id,'patientChoiceStatus')]";
    String editButtonInformation = "//button[@aria-label='edit button']";

    String specificPatientChoiceEdit = "//ul//span[text()='NHSLastFour']/ancestor::div[contains(@class,'css-1')]//button";
    String fileTypeDropDownValue = "//a[@class='dropdown-item'][contains(text(),'dummyOption')]";

    String uploadFilepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator;

    @FindBy(xpath = "//div[@class='dropdown']")
    WebElement fileTypeDropDown;
    @FindBy(xpath = "//input[@placeholder='DD']")
    WebElement uploadDay;
    @FindBy(xpath = "//input[@placeholder='MM']")
    WebElement uploadMonth;
    @FindBy(xpath = "//input[@placeholder='YYYY']")
    WebElement uploadYear;

    @FindBy(css = "*[class*='participant-list__']")
    public WebElement landingPageList;

    @FindBy(css = "*[aria-labelledby*='patientChoiceStatus']")
    public List<WebElement> statuses;

    @FindBy(xpath = "//button[contains(@aria-label,'edit')]")
    public List<WebElement> memberEditButton;

    @FindBy(id = "Patient-0")
    public WebElement adultWithCapacityCategory;

    @FindBy(xpath = "//*[contains(@class,'recordedByContainer')]//child::input")
    public WebElement recordedByField;

    @FindBy(css = ".btn.cli-nxt-btn")
    public WebElement recordedByContinueButton;

    @FindBy(css = ".finish-button.btn")
    public WebElement patientChoicesContinueButton;

    @FindBy(id = "Choices_Q1.0-0")
    public WebElement agreeTestChoice;

    @FindBy(xpath = "//label[@id='Choices_Q1.0-1']")
    public WebElement discussionFormNotAvailable;

    @FindBy(id = "Choices_Q1.0-2")
    public WebElement declineTestChoice;

    @FindBy(xpath = "//label[@id='Choices_Q2.0-1']")
    public WebElement patientChoiceNotRequiredForTheTest;

    @FindBy(id = "Choices_Q2.3-0")
    public WebElement agreeResearchParticipation;

    @FindBy(id = "Choices_Q2.3.1-0")
    public WebElement agreeSampleUsage;

    @FindBy(css = "button[class*='submit-signature-button']")
    public WebElement submitSignatureButton;

    @FindBy(css = "button[class*='submit-button']")
    public WebElement submitButton;

    @FindBy(css = "*[class*='message-line']")
    public WebElement recordAlreadyExistsMessage;

    @FindBy(css = "button.btn.disabled-submit-signature-button")
    public WebElement disabledSubmitPatientChoice;

    @FindBy(xpath = "//h5[contains(text(),'opportunity to read and discuss')]")
    WebElement firstConsulteeAttestationQuestion;

    @FindBy(xpath = "//h5[contains(text(),'opportunity to read and discuss')]/following::label[contains(@id,'Consultee_Q1')]")
    List<WebElement> firstConsulteeAttestationOption;

    @FindBy(xpath = "//h5[contains(text(),'consulted about this person')]")
    WebElement secondConsulteeAttestationQuestion;

    @FindBy(xpath = "//h5[contains(text(),'consulted about this person')]/following::label[contains(@id,'Consultee_Q2')]")
    List<WebElement> secondConsulteeAttestationOption;

    @FindBy(xpath = "//p[contains(text(),'Enter the hospital trust')]")
    public WebElement introMessageOnRequestingOrganisation;

    @FindBy(xpath = "//li[@class='message-error-line']")
    public List<WebElement> errorMessageOnPatientChoiceForm;

    @FindBy(xpath = "//button[contains(text(),'Back')]")
    public WebElement backButtonOnAddPatientChoiceInformationPage;

    @FindBy(xpath = "//label[contains(text(),'Patient Hospital Number')]")
    public WebElement patientHospitalNumberLabel;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'Patient Hospital Number')]/../input[@type='text']")
    public WebElement patientHospitalNumberField;

    @FindBy(xpath = "//h5[contains(text(),'data and samples may be used for research')]/following::label[contains(text(),'Yes')]")
    WebElement yesButtonOfDataAndSampleResearch;

    @FindBy(xpath = "//h5[contains(text(),'data and samples may be used for research')]/following::label[contains(text(),'No')]")
    WebElement noButtonOfDataAndSampleResearch;

    @FindBy(xpath = "//h5[contains(text(),'research participation')]/following::label[contains(text(),'Yes')]")
    WebElement yesButtonForResearchParticipation;

    @FindBy(xpath = "//h5[contains(text(),'research participation')]/following::label[contains(text(),'No')]")
    WebElement noButtonForResearchParticipation;

    @FindBy(xpath = "//div[@class='accordion completed col-sm-12']")
    public WebElement patientChoiceCompressed;

    @FindBy(xpath = "//h5[contains(text(),'Reason for not capturing patient choice:')]")
    WebElement patientChoiceReasonQuestion;

    @FindBy(xpath = "//p[@class='submition-info margin-smaller']")
    public WebElement patientChoiceFormCompletedMessage;

    @FindBy(xpath = "//a[text()='Print Patient Choice Form']")
    public WebElement printPatientChoiceFormButton;

    @FindBy(xpath = "//div[@class='radio-question-error question-error']")
    WebElement errorMessageBox;

    @FindBy(xpath = "//input[@name='3']/following::label[contains(@id,'Choices')]")
    List<WebElement> researchParticipationOptions;

    String patientChoiceQuestion = "//h5[contains(text(),'dummyQuestion')]";

    @FindBy(xpath = "//div[contains(text(),'Patient choice category')]")
    WebElement patientCategoryReopen;

    @FindBy(xpath = "//div[contains(text(),'Test type')]")
    WebElement testTypeReopen;

    @FindBy(xpath = "//div[@class='header col-sm-5 nowrap'][contains(text(),'Recorded by')]")
    WebElement recordedByReopen;

    @FindBy(xpath = "//div[@class='question-answer-line d-flex']")
    public List<WebElement> selectedPatientChoiceQuestion;

    @FindBy(xpath = "//p[@class='question-value white-bg']")
    public List<WebElement> selectedPatientChoiceOption;

    @FindBy(xpath = "//button[contains(text(),'Submit ')]")
    public WebElement submitPatientChoiceButton;

    @FindBy(xpath = "//ul[@class='message-list']")
    WebElement warningMessageBox;

    @FindBy(xpath = "//li[@class='message-line']")
    List<WebElement> warningMessage;

    @FindBy(className = "steps-list")
    public WebElement stepsList;

    String optionIsList = "//div[contains(text(),'" + "dummyOption" + "')]//ancestor::div[contains(@class,'accordion completed')]";

    @FindBy(xpath = "//h5[@class='d-inline']")
    WebElement patientChoiceAboutResearch;

    @FindBy(xpath = "//label[@class='radio-container'][contains(@id,'Choices')]")
    List<WebElement> patientChoiceAboutResearchOptions;

    public boolean editPatientChoice() {
        try {
            Wait.forElementToBeDisplayed(driver, editPatientChoice);
            seleniumLib.clickOnWebElement(editPatientChoice);
            return true;
        } catch (Exception exp) {
            Debugger.println("Could not click on Patient Choice Edit: " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceEdit.jpg");
            return false;
        }
    }

    public boolean editSpecificPatientChoice(String familyDetails) {
        String nhsNumber = "";
        try {
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyDetails);
            Set<String> paramsKey = paramNameValue.keySet();
            for (String key : paramsKey) {
                if (key.equalsIgnoreCase("NHSNumber")) {
                    nhsNumber = paramNameValue.get(key);
                    break;
                }
            }
            if (nhsNumber == null || nhsNumber.isEmpty()) {
                Debugger.println("NHS Number not provided to edit the patient choice.");
                return false;
            }
            //Debugger.println("NHS : "+nhsNumber);
            String nhsLastFour = nhsNumber.substring(6, nhsNumber.length());//Assuming NHSNumber is always 10 digit.
            //Debugger.println("NHSFOUR : "+nhsLastFour);

            By pChoiceEdit = By.xpath(specificPatientChoiceEdit.replaceAll("NHSLastFour", nhsLastFour));
            if (!seleniumLib.isElementPresent(pChoiceEdit)) {
                Wait.seconds(5);
            }
            WebElement element = driver.findElement(pChoiceEdit);
            if (Wait.isElementDisplayed(driver, element, 100)) {
                seleniumLib.clickOnWebElement(element);
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on edit patient choice of specific NHSNumber:" + exp);
            return false;
        }
    }

    public boolean selectPatientChoiceCategory(String category) {
        try {
            if (category == null || category.isEmpty()) {
                return true;
            }
            Wait.forElementToBeDisplayed(driver, newPatientChoiceFormTitle, 100);
            String selectedPatientChoiceCategory = patientChoiceCategory.replaceAll("dummyCategory", category);
            Wait.seconds(10);//Default observed a delay of 5-10 seconds for loading this section
            WebElement webElement = driver.findElement(By.xpath(selectedPatientChoiceCategory));
            if (Wait.isElementDisplayed(driver, webElement, 100)) {
                seleniumLib.clickOnWebElement(webElement);
            } else {
                Debugger.println("Category: " + category + " not loaded.");
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Selecting PatientChoiceCategory:" + exp);
            SeleniumLib.takeAScreenShot("patientChoiceCategory.jpg");
            return false;
        }
    }

    public boolean selectTestType(String test_type) {
        try {
            if (test_type == null || test_type.isEmpty()) {
                return true;
            }
            String selectedTestType = testType.replaceAll("dummyTestType", test_type);
            WebElement webElement = driver.findElement(By.xpath(selectedTestType));
            if (Wait.isElementDisplayed(driver, webElement, 100)) {
                seleniumLib.clickOnWebElement(webElement);
            } else {
                Debugger.println("Test Type: " + test_type + " not displayed.");
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Selecting PatientChoiceTestType:" + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceTestType.jpg");
            return false;
        }
    }

    public boolean fillRecordedByDetails(String familyDetails, String recorderBy) {
        try {
            if (recorderBy == null || recorderBy.isEmpty()) {
                return true;
            }
            NGISPatientModel familyMember = null;//To fill the clinician details to the Member, to validate later
            if (familyDetails != null && !familyDetails.isEmpty()) {
                HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyDetails);
                Set<String> paramsKey = paramNameValue.keySet();
                String nhsNumber = "";
                for (String key : paramsKey) {
                    if (key.equalsIgnoreCase("NHSNumber")) {
                        nhsNumber = paramNameValue.get(key);
                        break;
                    }
                }
                nhsNumber = TestUtils.getNHSDisplayFormat(nhsNumber);
                familyMember = FamilyMemberDetailsPage.getFamilyMember(nhsNumber);
            }
            boolean uploadDocument = false;
            String fileType = "";
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(recorderBy);
            Set<String> paramsKey = paramNameValue.keySet();
            for (String key : paramsKey) {
                switch (key) {
                    case "ClinicianName":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            recordingClinicianNameInput.sendKeys(paramNameValue.get(key));
                            if (familyMember != null) {
                                familyMember.setRECORDING_CLINICIAN_NAME(paramNameValue.get(key));
                            }
                        }
                        break;
                    case "HospitalNumber":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            patientHospitalNumberInput.sendKeys(paramNameValue.get(key));
                        }
                        break;
                    case "AdminName":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            adminNameInput.sendKeys(paramNameValue.get(key));
                        }
                        break;
                    case "Action":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            if (paramNameValue.get(key).equalsIgnoreCase("UploadDocument")) {
                                uploadDocument = true;
                            }
                        }
                        break;
                    case "FileType":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            fileType = paramNameValue.get(key);
                        }
                        break;
                }//switch
            }//for
            if (uploadDocument) {
                return uploadRecordTypeDocument(fileType);
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in Filling RecordedBy Information: " + exp);
            SeleniumLib.takeAScreenShot("RecorderBy.jpg");
            return false;
        }
    }

    public void clickOnContinue() {
        seleniumLib.clickOnWebElement(continueButton);
    }

    public boolean uploadRecordTypeDocument(String fileType) {
        try {
            seleniumLib.clickOnWebElement(uploadDocumentOption);
            Wait.forElementToBeDisplayed(driver, uploadDocumentButton);
            if (Wait.isElementDisplayed(driver, uploadDocumentButton, 10)) {
                uploadDocumentButton.click();
            }

            if (!seleniumLib.upload(uploadFilepath + "testfile.pdf")) {
                Debugger.println("Could not upload document to Record Type in Patient Choice.");
                return false;
            }
            //Select the FormType
            Wait.forElementToBeDisplayed(driver, fileTypeDropDown);
            fileTypeDropDown.click();
            By formType = By.xpath(fileTypeDropDownValue.replaceAll("dummyOption", fileType));
            WebElement element = driver.findElement(formType);
            element.click();
            //Date need to pass as today's date.
            Calendar today = Calendar.getInstance();
            String year = "";
            String month = "";
            String day = "";
            int iyear = today.get(Calendar.YEAR);
            int imonth = today.get(Calendar.MONTH) + 1;
            int iday = today.get(Calendar.DATE);

            if (imonth < 10) {
                month = "0" + imonth;
            } else {
                month = "" + imonth;
            }
            year = "" + iyear;
            if (iday < 10) {
                day = "0" + iday;
            } else {
                day = "" + iday;
            }
            uploadDay.sendKeys(day);
            uploadMonth.sendKeys(month);
            uploadYear.sendKeys(year);
            Debugger.println("Record Type..Done with document uploaded.");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in uploading record type in Patient choice: " + exp);
            SeleniumLib.takeAScreenShot("recordTypeUpload.jpg");
            return false;
        }
    }

    public boolean selectPatientChoice(String patient_choice) {
        try {
            Debugger.println("Patient Choice......" + patient_choice);
            if (patient_choice == null || patient_choice.isEmpty()) {
                return true;
            }
            WebElement titleElement = driver.findElement(By.xpath("//div[contains(text(),'Patient choices')]"));
            Wait.forElementToBeDisplayed(driver, titleElement);
            String pChoice = patientChoice.replaceAll("dummyChoice", patient_choice);
            WebElement webElement;
            try {
                webElement = driver.findElement(By.xpath(pChoice));
            } catch (NoSuchElementException nsee) {
                //If the element not found, waiting for 5 seconds and the searchign again. Some times it is taking time.
                Wait.seconds(5);
                webElement = driver.findElement(By.xpath(pChoice));
            }
            seleniumLib.clickOnWebElement(webElement);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Selecting PatientChoice:" + exp);
            SeleniumLib.takeAScreenShot("PatientChoice.jpg");
            return false;
        }
    }

    private String patientChoiceOptionFirst = "//h5[contains(text(),'Has research participation been discussed')]/following::label[contains(text(),'" + "dummyValue" + "')]";

    private String patientChoiceOptionSecond = "//h5[contains(text(),'used for research, separate to NHS care')]/following::label[contains(text(),'" + "dummyValue" + "')]";

    public boolean selectChildAssent(String child_assent) {
        try {
            if (child_assent == null || child_assent.isEmpty()) {
                return true;
            }
            WebElement titleElement = driver.findElement(By.xpath("//div[contains(text(),'Child assent')]"));
            if (!Wait.isElementDisplayed(driver, titleElement, 100)) {
                return true;//Child assent not present and may not be required - for new patient's family members
            }
            Actions.scrollToTop(driver);
            String chdAssent = childAssent.replaceAll("dummyAssent", child_assent);
            Wait.seconds(1);
            WebElement webElement = driver.findElement(By.xpath(chdAssent));
            if (Wait.isElementDisplayed(driver, webElement, 10)) {
                webElement.click();
            } else {
                if (Wait.isElementDisplayed(driver, webElement, 30)) {
                    webElement.click();
                }
            }
            if (child_assent.equalsIgnoreCase("Yes")) {//Click on Signature board
                Debugger.println("Signing Child Assent Signature...");
                if (!SeleniumLib.drawSignature(signatureSection)) {
                    seleniumLib.clickOnWebElement(signaturePad);
                }
                Debugger.println("Signed Child Assent Signature...");
            }
            if (!Wait.isElementDisplayed(driver, continueButton, 60)) {
                Debugger.println("Child element could not select.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Selecting ChildAssent:" + exp);
            SeleniumLib.takeAScreenShot("ChildAssent.jpg");
            return false;
        }
    }

    public void clickingOnYesNoOptions(String options) {
        try {
            String[] option = null;
            if (options.indexOf(",") == -1) {
                option = new String[]{options};
            } else {
                option = options.split(",");
            }
//            Wait.seconds(2);
            String firstElement = patientChoiceOptionFirst.replace("dummyValue", option[0]);
            WebElement firstSelectedOptionResult = driver.findElement(By.xpath(firstElement));
            if (!seleniumLib.isElementPresent(firstSelectedOptionResult)) {
                Debugger.println("1st Yes/No option not found");
            }
            seleniumLib.clickOnWebElement(firstSelectedOptionResult);
//            Wait.seconds(2);
            String secondElement = patientChoiceOptionSecond.replace("dummyValue", option[1]);
            WebElement secondSelectedOptionResult = driver.findElement(By.xpath(secondElement));
            if (!seleniumLib.isElementPresent(secondSelectedOptionResult)) {
                Debugger.println("2nd Yes/No option not found");
            }
            seleniumLib.clickOnWebElement(secondSelectedOptionResult);
            Debugger.println("YesNo Completed");
//            Wait.seconds(20);
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on 1st YesNoOptions. " + exp);
        }
    }

    public void clickingOnResearchParticipationYesNoOptions(String option) {
        try {
            String firstElement = patientChoiceOptionFirst.replace("dummyValue", option);
            WebElement firstSelectedOptionResult = driver.findElement(By.xpath(firstElement));
            if (!seleniumLib.isElementPresent(firstSelectedOptionResult)) {
                Debugger.println("1st Yes/No option not found");
            }
            seleniumLib.clickOnWebElement(firstSelectedOptionResult);
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on 1st YesNoOptions. " + exp);
        }
    }

    public boolean fillParentSignatureDetails(String parentDetails) {
        try {
            if (parentDetails == null || parentDetails.isEmpty()) {
                //Signature
                if (!SeleniumLib.drawSignature(signatureSection)) {//Patient Signature only.
                    Debugger.println("Signature could not draw.. continuing with Click.");
                    signaturePad.click();
                }
                return true;
            }
            //Parent/Guardian details and signature.
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(parentDetails);
            Set<String> paramsKey = paramNameValue.keySet();
            for (String key : paramsKey) {
                switch (key) {
                    case "FirstName":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            parentFirstName.sendKeys(paramNameValue.get(key));
                        }
                        break;
                    case "LastName":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            parentLastName.sendKeys(paramNameValue.get(key));
                        }
                        break;
                }//switch
            }//for
            //Signature
            if (!SeleniumLib.drawSignature(signatureSection)) {
                Debugger.println("Signature could not draw.. continuing with Click.");
                signaturePad.click();
            }
            if (!Wait.isElementDisplayed(driver, parentGuardianSignatureLabel, 30)) {
                Debugger.println("Label Patient/Guardian Signature not present....");
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in Filling fillParentSignatureDetails Information: " + exp);
            SeleniumLib.takeAScreenShot("ParentSignature.jpg");
            return false;
        }
    }

    public boolean submitPatientChoice() {
        try {
            submitPatientChoice.click();
            if (!Wait.isElementDisplayed(driver, saveAndContinueButton, 30)) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from submitting Patient Choice...." + exp);
            return false;
        }
    }

    public boolean verifyPatientChoiceStatus(String expStatus) {
        try {
            if (!Wait.isElementDisplayed(driver, patientChoiceStatus, 30)) {
                Debugger.println("PatientChoice is not displayed.");
                SeleniumLib.takeAScreenShot("PatientChoice.jpg");
                return false;
            }
            String actStatus = patientChoiceStatus.getText();
            if (!expStatus.equalsIgnoreCase(actStatus)) {
                Debugger.println("Expected Patient Choice: " + expStatus + ", But actual is: " + actStatus);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception FamilyMemberDetailsPage:verifyPatientChoiceStatus :" + exp);
            return false;
        }
    }

    public boolean verifyPatientIdentifiersInPatientChoicePage() {
        try {
            //Validation of Core Information Presence
            int noOfPatients = 0;
            List<WebElement> patientLists = seleniumLib.getElements(By.xpath(patientList));
            if (patientLists != null) {
                noOfPatients = patientLists.size();
            }

            if (noOfPatients != FamilyMemberDetailsPage.noOfPatientsForIdentification) {
                Debugger.println("No of Patients Information Present in FamilyMember Landing Page and Patient Choice Page is not matching.");
                SeleniumLib.takeAScreenShot("PatientListChoicePage.jpg");
                return false;
            }
            Wait.seconds(2);
            Debugger.println("Validating Information of " + noOfPatients + " Patients in Patient Choice Page.");
            List<WebElement> nameList = seleniumLib.getElements(By.xpath(firstNameLastName));
            if (nameList == null || nameList.size() != noOfPatients) {
                Debugger.println("Expected Presence of First/Last Name field for " + noOfPatients + " patients in  Patient Choice Page.");
                SeleniumLib.takeAScreenShot("firstLastNameLst.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> probandTestedList = seleniumLib.getElements(By.xpath(probandBeingTested));
            if (probandTestedList == null || probandTestedList.size() != (noOfPatients * 2)) {
                Debugger.println("Expected Presence of Proband and Being Tested Information for " + noOfPatients + " patients in  Patient ChoicePage.");
                SeleniumLib.takeAScreenShot("probandTested.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> bornList = seleniumLib.getElements(By.xpath(bornInformation));
            if (bornList == null || bornList.size() != noOfPatients) {
                Debugger.println("Expected Presence of Born Information for " + noOfPatients + " patients in  Patient Choice Page.");
                SeleniumLib.takeAScreenShot("bornInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> genderList = seleniumLib.getElements(By.xpath(genderInformation));
            if (genderList == null || genderList.size() != noOfPatients) {
                Debugger.println("Expected Presence of Gender Information for " + noOfPatients + " patients in  Patient Choice Page.");
                SeleniumLib.takeAScreenShot("genderInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> ngisList = seleniumLib.getElements(By.xpath(ngsIdInformation));
            if (ngisList == null || ngisList.size() != noOfPatients) {
                Debugger.println("Expected Presence of NGSID Information for " + noOfPatients + " patients in  Patient Choice Page.");
                SeleniumLib.takeAScreenShot("ngsInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> pchoiceList = seleniumLib.getElements(By.xpath(patientChoiceInformation));
            if (pchoiceList == null || pchoiceList.size() != noOfPatients) {
                Debugger.println("Expected Presence of PatientChoice Information for " + noOfPatients + " patients in  Patient Choice Page.");
                SeleniumLib.takeAScreenShot("pchoiceInfo.jpg");
                return false;
            }
            //EDIT and REMOVE BUTTON
            Wait.seconds(2);
            List<WebElement> editButtonList = seleniumLib.getElements(By.xpath(editButtonInformation));
            if (editButtonList != null) {
                if (editButtonList.size() != noOfPatients) {
                    Debugger.println("Expected Presence of Edit Information for " + noOfPatients + " patients in  Patient Choice Page.");
                    SeleniumLib.takeAScreenShot("editButtonInfo.jpg");
                    return false;
                }
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in  Verifying Patient Identifier Information in Patient Choice Page.");
            return false;
        }

    }

    public boolean clickOnPatientChoiceInformationLink(String linkText) {
        try {
            if (linkText == null || linkText.isEmpty()) {
                return true;
            }
            Wait.forElementToBeDisplayed(driver, newPatientChoiceFormTitle, 100);
            patientChoiceLink = patientChoiceLink.replaceAll("New patient choice", linkText);
            Wait.seconds(10);//Default observed a delay of 5-10 seconds for loading this section
            WebElement webElement = driver.findElement(By.xpath(patientChoiceLink));
            seleniumLib.clickOnWebElement(webElement);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on PatientChoiceInformation Links:" + exp);
            SeleniumLib.takeAScreenShot("patientChoiceInformationLink.jpg");
            return false;
        }
    }

    public boolean verifyThePatientChoiceFormLibraryDetails() {
        Wait.forElementToBeDisplayed(driver, formLibraryTitle);
        List<WebElement> expElements = new ArrayList<WebElement>();
        //Expected Titles
        for (int i = 0; i < patientChoiceFormsAndInformationTitle.size(); i++) {
            expElements.add(patientChoiceFormsAndInformationTitle.get(i));
        }
        //Expected Links
        for (int i = 0; i < formLibraryLinks.size(); i++) {
            expElements.add(formLibraryLinks.get(i));
        }
        //Verifying the presence.
        for (int i = 0; i < expElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expElements.get(i))) {
                Debugger.println("Expected Element :" + expElements.get(i) + " Not present.");
                return false;
            }
        }
        return true;
    }

    public boolean verifySelectedOption(String expectedResult) {
        String selectedOptionField = selectedOption.replaceAll("dummyOption", expectedResult);
        WebElement selectedOptionResult = driver.findElement(By.xpath(selectedOptionField));
        if (!seleniumLib.isElementPresent(selectedOptionResult)) {
            Debugger.println("Message before Edit button not found in " + expectedResult);
            return false;
        }
        if (!selectedOptionResult.getText().contains(expectedResult)) {
            return false;
        }
        return true;
    }

    public boolean verifyEditButton(String category) {
        String editButtonField = editButton.replaceAll("dummyOption", category);
        WebElement editButtonResult = driver.findElement(By.xpath(editButtonField));
        if (!seleniumLib.isElementPresent(editButtonResult)) {
            Debugger.println("Edit button not found in " + category);
            return false;
        }
        return true;
    }

    public boolean verifyTheOptionTitlePresence(String expTitle) {
        By optionTitle = By.xpath("//div[contains(text(),'" + expTitle + "')]");
        if (!seleniumLib.isElementPresent(optionTitle)) {
            Wait.forElementToBeDisplayed(driver, driver.findElement(optionTitle));
            if (!seleniumLib.isElementPresent(optionTitle)) {
                Debugger.println("Expected title :" + expTitle + " not loaded in the page.");
                return false;
            }
        }
        return true;
    }

    public boolean verifyThePatientChoiceOptions() {
        try {
            Wait.forElementToBeDisplayed(driver, patientChoiceAboutResearch);
            if (!seleniumLib.isElementPresent(patientChoiceAboutResearch)) {
                Debugger.println("Patient choices: About genomic testing and agreed to the genomic test question not found.");
                return false;
            }
            Assert.assertEquals("Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?", patientChoiceAboutResearch.getText());

            for (int i = 0; i < patientChoiceAboutResearchOptions.size(); i++) {
                if (!seleniumLib.isElementPresent(patientChoiceAboutResearchOptions.get(i))) {
                    //Debugger.println("Patient choices options not found.");
                    return false;
                }
                switch (i) {
                    case 0:
                        //Debugger.println("1 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Patient has agreed to the test", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                    case 1:
                        //Debugger.println("2 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Record of Discussion form not currently available", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                    case 2:
                        //Debugger.println("3 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Patient changed their mind about the clinical test", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                }
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyThePatientChoiceOptions " + exp);
            return false;
        }
    }

    public boolean optionIsCompleted(String option) {
        try {
            Wait.forElementToBeDisplayed(driver, stepsList);
            String elementLocator = optionIsList.replace("dummyOption", option);
            WebElement webElementLocator = driver.findElement(By.xpath(elementLocator));
            if (!seleniumLib.isElementPresent(webElementLocator)) {
                Debugger.println("Tick mark not present.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in Checking patient_choice_form's option completion status: " + exp);
            SeleniumLib.takeAScreenShot("OptionComplete.jpg");
            return false;
        }
    }

    public boolean patientChoiceInformationWarningMessage(String message) {
        try {
            Wait.forElementToBeDisplayed(driver, warningMessageBox);
            for (int i = 0; i < warningMessage.size(); i++) {
                if (message.equalsIgnoreCase(warningMessage.get(i).getText())) {
                    Debugger.println("Actual : " + warningMessage.get(i).getText());
                    Debugger.println("Expected : " + message);
                    return true;
                }
            }
            Debugger.println("PatientChoicePage, patientChoiceInformationWarningMessage - warning message not matched.");
            return false;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage, patientChoiceInformationWarningMessage - warning message box not found. " + exp);
            return false;
        }
    }

    public boolean notHighlightedContinueButton() {
        try {
            Wait.forElementToBeDisplayed(driver, continueButton);
            String continueButtonBgColor = continueButton.getCssValue("background-color");
            if (!continueButtonBgColor.equalsIgnoreCase("rgba(240, 240, 240, 1)")) {
                Debugger.println("Expected color : " + continueButtonBgColor + " not displayed when continue button is not highlighted");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Continue Button not found. " + exp);
            return false;
        }
    }

    public boolean highlightedContinueButton() {
        try {
            Wait.forElementToBeDisplayed(driver, continueButton);
            String continueButtonBgColor = continueButton.getCssValue("background-color");
            if (!continueButtonBgColor.equalsIgnoreCase("rgba(9, 97, 183, 1)")) {
                Debugger.println("Expected color : " + continueButtonBgColor + " not displayed on highlighted continue button");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Continue Button not found. " + exp);
            return false;
        }
    }

    public boolean highlightedSubmitPatientChoiceButton() {
        try {
            Wait.forElementToBeDisplayed(driver, submitPatientChoiceButton);
            String submitPatientChoiceButtonBgColor = submitPatientChoiceButton.getCssValue("background-color");
            if (!submitPatientChoiceButtonBgColor.equalsIgnoreCase("rgba(9, 97, 183, 1)")) {
                Debugger.println("Expected color : " + submitPatientChoiceButtonBgColor + " not displayed when Submit patient choice button is not highlighted");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Submit patient choice Button not found. " + exp);
            return false;
        }
    }

    public boolean saveAndContinueButtonStatus() {
        try {
            Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
            if (saveAndContinueButton.isEnabled()) {
                Debugger.println("Save and continue button is in enabled state.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Continue Button not found. " + exp);
            return false;
        }
    }

    public boolean clickOnSaveAndContinueButton() {
        try {
            if (Wait.isElementDisplayed(driver, saveAndContinueButton, 200)) {
                Wait.forElementToBeClickable(driver, saveAndContinueButton);
                seleniumLib.clickOnWebElement(saveAndContinueButton);
            }
            if (seleniumLib.isElementPresent(saveAndContinueButton)) {//If still Present, Some times first click is just selecting only. so clicking again.
                seleniumLib.clickOnWebElement(saveAndContinueButton);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Continue Button not found. " + exp);
            return false;
        }
    }


    public boolean selectedPatientChoiceDetails() {
        try {
            ArrayList<WebElement> expElements = new ArrayList<WebElement>();
            for (int i = 0; i < selectedPatientChoiceQuestion.size(); i++) {
                expElements.add(selectedPatientChoiceQuestion.get(i));
            }
            for (int i = 0; i < selectedPatientChoiceOption.size(); i++) {
                expElements.add(selectedPatientChoiceOption.get(i));
            }

            for (int i = 0; i < expElements.size(); i++) {
                if (!seleniumLib.isElementPresent(expElements.get(i))) {
                    Debugger.println("selectedPatientChoiceDetails, Element not found." + expElements.get(i));
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient choice selected details are not found. " + exp);
            return false;
        }
    }

    public void clickOnEditButton(String category) {
        try {
            String editButtonField = editButton.replaceAll("dummyOption", category);
            WebElement editButtonResult = driver.findElement(By.xpath(editButtonField));
            Actions.scrollToTop(driver);
            seleniumLib.clickOnWebElement(editButtonResult);
        } catch (Exception exp) {
            Debugger.println("Exception from clicking edit button." + exp);
        }
    }

    public boolean previousSectionsReopened() {
        try {
            seleniumLib.isElementPresent(patientCategoryReopen);
            seleniumLib.isElementPresent(testTypeReopen);
            seleniumLib.isElementPresent(recordedByReopen);
        } catch (Exception exp) {
            Debugger.println("Patient Choice Page:previousSectionsReopened: " + exp);
            return false;
        }
        return true;
    }

    public void verifyTheQuestionInPatientChoice(String question) {
        try {
            Wait.seconds(10);
            String questionField = patientChoiceQuestion.replaceAll("dummyQuestion", question);
            WebElement questionElement = driver.findElement(By.xpath(questionField));
            if (!question.equalsIgnoreCase(questionElement.getText())) {
                Debugger.println("#Expected :" + question + " #Actual :" + questionElement.getText());
            }
            Debugger.println("Actual : " + questionElement.getText());
            Debugger.println("Expected : " + question);
            Assert.assertEquals(question, questionElement.getText());
        } catch (Exception exp) {
            Debugger.println("verifyTheQuestionInPatientChoice, element not found.");
        }
    }

    public boolean verifyResearchParticipationOfPatientChoice() {
        try {
            for (int i = 0; i < researchParticipationOptions.size(); i++) {
                if (!seleniumLib.isElementPresent(researchParticipationOptions.get(i))) {
                    Debugger.println("Patient choices research participation not found.");
                    return false;
                }
                switch (i) {
                    case 0:
                        Assert.assertEquals("Inappropriate to have discussion", researchParticipationOptions.get(i).getText());
                        break;
                    case 1:
                        Assert.assertEquals("Patient would like to revisit at a later date", researchParticipationOptions.get(i).getText());
                        break;
                    case 2:
                        Assert.assertEquals("Patient lacks capacity and no consultee available", researchParticipationOptions.get(i).getText());
                        break;
                    case 3:
                        Assert.assertEquals("Other", researchParticipationOptions.get(i).getText());
                        break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage,verifyResearchParticipationOfPatientChoice:" + exp);
            return false;
        }
    }

    public boolean errorMessageInPatientChoicePage() {
        try {
            Wait.forElementToBeDisplayed(driver, errorMessageBox);
            if (!seleniumLib.isElementPresent(errorMessageBox)) {
                Debugger.println("Error box not found.");
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage,errorMessageInPatientChoicePage: element not found." + exp);
            return false;
        }
    }

    public void clickingOnNHSCareYesNoOptions(String option) {
        try {
            Actions.scrollToTop(driver);
            String secondElement = patientChoiceOptionSecond.replace("dummyValue", option);
            WebElement secondSelectedOptionResult = driver.findElement(By.xpath(secondElement));
            if (!seleniumLib.isElementPresent(secondSelectedOptionResult)) {
                Debugger.println("2nd Yes/No option not found");
            }
            seleniumLib.clickOnWebElement(secondSelectedOptionResult);
            Debugger.println("YesNo Completed");
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on 2st YesNoOptions. " + exp);
        }
    }

    public boolean selectPatientSignature() {
        Wait.forElementToBeDisplayed(driver, signaturePad, 30);
        if (!seleniumLib.isElementPresent(signaturePad)) {
            Debugger.println("Signature Pad Not loaded for Patient Signature.");
            return false;
        }
        seleniumLib.scrollToElement(signaturePad);
        SeleniumLib.drawSignature(signaturePad);
        return true;
    }

    public boolean patientChoiceFormCompleted() {
        try {
            if (!seleniumLib.isElementPresent(patientChoiceFormCompletedMessage)) {
                Wait.forElementToBeDisplayed(driver, patientChoiceFormCompletedMessage);
                Wait.forElementToBeDisplayed(driver, printPatientChoiceFormButton);
                if (!seleniumLib.isElementPresent(patientChoiceFormCompletedMessage)) {
                    Debugger.println("The form is not loaded ");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice Page: patientChoiceFormCompleted: Form is not loaded: " + exp);
            return false;
        }
    }

    public boolean verifyReasonsOfPatientChoice() {
        try {
            if (!seleniumLib.isElementPresent(patientChoiceReasonQuestion)) {
                Debugger.println("Patient choices: Reason for not capturing patient choice: question not found.");
                return false;
            }
            Assert.assertEquals("Reason for not capturing patient choice:", patientChoiceReasonQuestion.getText());

            for (int i = 3; i < patientChoiceAboutResearchOptions.size(); i++) {
                if (!seleniumLib.isElementPresent(patientChoiceAboutResearchOptions.get(i))) {
                    Debugger.println("Patient choices reasons not found.");
                    return false;
                }
                switch (i) {
                    case 3:
                        Debugger.println("Reason 1 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Patient conversation happened; form to follow", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                    case 4:
                        Debugger.println("Reason 2 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Test does not require recording of patient choices", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                    case 5:
                        Debugger.println("Reason 3 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Patient currently lacks capacity and no consultee available", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                    case 6:
                        Debugger.println("Reason 4 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Associated with another referral", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                    case 7:
                        Debugger.println("Reason 5 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Other", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyThePatientChoiceReasons " + exp);
            return false;
        }
    }

    public boolean previousSectionReclosed() {
        try {
            Wait.forElementToBeDisplayed(driver, patientChoiceCompressed);
            if (!seleniumLib.isElementPresent(patientChoiceCompressed)) {
                Debugger.println("previousSectionsReclosed, Patient choice selected section not found. ");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("patientChoicePage: previousSectionsReclosed:  " + exp);
            return false;
        }
    }

    public void selectMember(int i) {
        Wait.forElementToBeDisplayed(driver, landingPageList);
        Click.element(driver, memberEditButton.get(i));
    }

    public void selectPatientChoiceCategory() {
        Click.element(driver, adultWithCapacityCategory);
    }

    public void selectTestType() {
        Click.element(driver, adultWithCapacityCategory);
    }

    public void enterRecordedByDetails() {
        Wait.forElementToBeDisplayed(driver, recordedByField);
        co.uk.gel.lib.Actions.fillInValue(recordedByField, "Sue");
        Click.element(driver, recordedByContinueButton);
    }

    public void selectChoicesWithPatientChoiceNotRequired() {
        Click.element(driver, discussionFormNotAvailable);
        Click.element(driver, patientChoiceNotRequiredForTheTest);
        Click.element(driver, patientChoicesContinueButton);
    }

    public void selectChoicesWithAgreeingTesting() {
        Click.element(driver, agreeTestChoice);
        Click.element(driver, agreeResearchParticipation);
        Click.element(driver, agreeSampleUsage);
        Click.element(driver, patientChoicesContinueButton);
    }

    public void drawSignature() {
        Wait.forElementToBeDisplayed(driver, signatureSection);
        Click.element(driver, signatureSection);
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driver);
        Action drawAction = builder.moveToElement(signatureSection, 135, 15) //start points x axis and y axis.
                .clickAndHold()
                .moveByOffset(80, 80)
                .moveByOffset(50, 20)
                .release()
                .build();
        drawAction.perform();
        Wait.seconds(1);
    }


    public boolean verifyYesNoButtonForResearchParticipation() {
        try {
            Wait.forElementToBeDisplayed(driver, yesButtonForResearchParticipation);
            ArrayList<WebElement> expElement = new ArrayList<WebElement>();
            expElement.add(yesButtonForResearchParticipation);
            expElement.add(noButtonForResearchParticipation);
            for (int i = 0; i < expElement.size(); i++) {
                if (!seleniumLib.isElementPresent(expElement.get(i))) {
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("verifyYesNoButtonForResearchParticipation, Yes No Button not found.");
            return false;
        }
    }

    public void submitPatientChoiceWithSignature() {
        try {
            Wait.forElementToDisappear(driver, By.cssSelector("button[class*='disabled-submit-signature-button']"));
            Click.element(driver, submitSignatureButton);
        } catch (Exception exp) {
            Debugger.println("Exception from submitting Patient Choice with Signature...." + exp);

        }
    }

    public void clickingOnDataAndSampleForResearchYesNoOptions(String option) {
        try {
            Actions.scrollToTop(driver);
            String secondElement = patientChoiceOptionSecond.replace("dummyValue", option);
            WebElement secondSelectedOptionResult = driver.findElement(By.xpath(secondElement));
            if (!seleniumLib.isElementPresent(secondSelectedOptionResult)) {
                Debugger.println("2nd Yes/No option not found");
            }
            seleniumLib.clickOnWebElement(secondSelectedOptionResult);
            Debugger.println("YesNo Completed");
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on 2st YesNoOptions. " + exp);
        }
    }

    public void submitPatientChoiceWithoutSignature() {
        try {
            Click.element(driver, submitButton);
        } catch (Exception exp) {
            Debugger.println("Exception from submitting Patient Choice...." + exp);
        }
    }

    public boolean statusUpdatedCorrectly(String status, int row) {
        Wait.forElementToBeDisplayed(driver, landingPageList, 100);
        return status.equalsIgnoreCase(statuses.get(row).getText());
    }

    public boolean messageThatPatientChoiceRecordExistsIsDisplayed(String expectedTextMessage) {
        Wait.forElementToBeDisplayed(driver, recordAlreadyExistsMessage);
        return recordAlreadyExistsMessage.getText().contains(expectedTextMessage);
    }

    public void addPatientChoiceIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, adultWithCapacityCategory);
    }

    public boolean verifyHelpTextLabelIsVisible() {
        try {
            Wait.forElementToBeDisplayed(driver, helpTextLabel, 200);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception before seeing Patient Choice participants info ...." + exp);
            return false;
        }
    }


    public boolean verifyYesNoButtonForSeparateToNnsCare() {
        try {
            Wait.forElementToBeDisplayed(driver, yesButtonOfDataAndSampleResearch);
            ArrayList<WebElement> expElement = new ArrayList<WebElement>();
            expElement.add(yesButtonOfDataAndSampleResearch);
            expElement.add(noButtonOfDataAndSampleResearch);
            for (int i = 0; i < expElement.size(); i++) {
                if (!seleniumLib.isElementPresent(expElement.get(i))) {
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("verifyYesNoButtonForSeparateToNnsCare, Yes No Button not found.");
            return false;
        }
    }

    public boolean patientHospitalNumberInRecordedByOption() {
        try {
            if (!seleniumLib.isElementPresent(patientHospitalNumberLabel)) {
                Debugger.println("Add patient Choice Page:Recorded by:Hospital number label Not found");
                return false;
            }
            if (!seleniumLib.isElementPresent(patientHospitalNumberField)) {
                Debugger.println("Add patient Choice Page:Recorded by:Hospital number Field not found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Add patient Choice Page:Recorded by:Hospital number" + exp);
            return false;
        }

    }


    public boolean backButtonOnPatientChoiceInformationPage() {
        try {
            Wait.forElementToBeDisplayed(driver, backButtonOnAddPatientChoiceInformationPage);
            if (!seleniumLib.isElementPresent(backButtonOnAddPatientChoiceInformationPage)) {
                Debugger.println("Add patient Choice Page:Recorded by:Back Button Not found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Add patient Choice Page:backButtonOnPatientChoiceInformationPage:" + exp);
            return false;
        }
    }


    public boolean verifyErrorMessageOnPatientChoiceFormPage(String errorMessage) {
        try {
            String[] expMessages = null;
            if (errorMessage.indexOf(",") == -1) {
                expMessages = new String[]{errorMessage};
            } else {
                expMessages = errorMessage.split(",");
            }
            String actualMessage = "";
            for (int i = 0; i < expMessages.length; i++) {
                actualMessage = Actions.getText(errorMessageOnPatientChoiceForm.get(i));
                if (!expMessages[i].equalsIgnoreCase(actualMessage)) {
                    Debugger.println("Expected Message: " + errorMessage + ", but Actual Message: " + actualMessage);
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoiceFormPage:Exception from validating Error Message " + exp);
            return false;
        }

    }

    public boolean enabledContinueButtonOnPatientChoiceFormPage() {
        Debugger.println("continue Button on patient choice page");
        try {
            Wait.forElementToBeDisplayed(driver, continueButton);
            if (!continueButton.isEnabled()) {
                Debugger.println("Add patient Choice Page:Recorded by:continue Button Not found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Add patient Choice Page:continue Button:" + exp);
            return false;
        }
    }

    public boolean clickOnSubmitPatientChoiceButton() {
        Debugger.println("Patient Choice: Clicking on submit patient choice button");
        try {
            Wait.forElementToBeDisplayed(driver, submitPatientChoiceButton);
            seleniumLib.clickOnWebElement(submitPatientChoiceButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice: Clicking on submit patient choice button:" + exp);
            return false;
        }
    }

    public boolean verifyPatientChoiceForm() {
        Debugger.println("Patient Choice Form");
        try {
            Wait.forElementToBeDisplayed(driver, newPatientChoiceFormTitle);
            if (!seleniumLib.isElementPresent(newPatientChoiceFormTitle)) {
                Debugger.println("Add patient Choice Page:verifyPatientChoiceForm : Title Not found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Add patient Choice Page:verifyPatientChoiceForm : Title:" + exp);
            return false;
        }
    }


    public boolean verifyTheIntroMessage(String introMessage) {
        try {
            Wait.forElementToBeDisplayed(driver, introMessageOnRequestingOrganisation);
            Assert.assertEquals(introMessage, introMessageOnRequestingOrganisation.getText());
            return true;
        } catch (Exception exp) {
            Debugger.println("Requesting Organisation page: Intro message: " + exp + "Message Not Matched");
            return false;
        }

    }

    @FindBy(xpath = "//div[contains(@class,'styles_search-input')]/child::input[@type='text']")
    public WebElement hintTextInSearchBoxOnRequestingOrganisation;

    public boolean verifyHintText() {
        try {
            Wait.forElementToBeDisplayed(driver, hintTextInSearchBoxOnRequestingOrganisation);
            if (!seleniumLib.isElementPresent(hintTextInSearchBoxOnRequestingOrganisation)) {
                Debugger.println("Hint text in search box not present.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice page: verifyHintText, Element not found. " + exp);
            return false;
        }
    }

//    2040

    public boolean verifyThePatientChoiceOptionsForConsultee() {
        try {
            Wait.forElementToBeDisplayed(driver, patientChoiceAboutResearch);
            if (!seleniumLib.isElementPresent(patientChoiceAboutResearch)) {
                Debugger.println("Patient choices: About genomic testing and agreed to the genomic test question not found.");
                return false;
            }
            Assert.assertEquals("Has the consultee had the opportunity to read and discuss information about genomic testing and agreed to the genomic test on behalf of the patient?", patientChoiceAboutResearch.getText());

            for (int i = 0; i < patientChoiceAboutResearchOptions.size(); i++) {
                if (!seleniumLib.isElementPresent(patientChoiceAboutResearchOptions.get(i))) {
                    Debugger.println("Patient choices consultee options not found.");
                    return false;
                }
                switch (i) {
                    case 0:
                        Debugger.println("1 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Consultee has agreed to the test", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                    case 1:
                        Debugger.println("2 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Record of Discussion form not currently available", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                    case 2:
                        Debugger.println("3 :" + patientChoiceAboutResearchOptions.get(i).getText());
                        Assert.assertEquals("Consultee changed their mind about the clinical test", patientChoiceAboutResearchOptions.get(i).getText());
                        break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyThePatientChoiceOptionsForConsultee " + exp);
            return false;
        }
    }


    public boolean verifyTheConsulteeAttestationFirstOptions() {
        try {
            Wait.forElementToBeDisplayed(driver, firstConsulteeAttestationQuestion);
            ArrayList<WebElement> expElements = new ArrayList<WebElement>();
            expElements.add(firstConsulteeAttestationQuestion);
            for (int i = 0; i < firstConsulteeAttestationOption.size(); i++) {
                expElements.add(firstConsulteeAttestationOption.get(i));
            }
            expElements.add(secondConsulteeAttestationQuestion);
            for (int i = 0; i < secondConsulteeAttestationOption.size(); i++) {
                expElements.add(secondConsulteeAttestationOption.get(i));
            }
            for (int i = 0; i < expElements.size(); i++) {
                if (!seleniumLib.isElementPresent(expElements.get(i))) {
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("verifyTheConsulteeAttestationFirstOptions: Elements not found.");
            return false;
        }
    }

    public boolean submitPatientChoiceButtonStatus() {
        try {
            seleniumLib.waitForElementVisible(disabledSubmitPatientChoice);
            if (!seleniumLib.isElementPresent(disabledSubmitPatientChoice)) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: submitPatientChoiceButtonStatus: " + exp);
            return false;
        }
    }
}//end
