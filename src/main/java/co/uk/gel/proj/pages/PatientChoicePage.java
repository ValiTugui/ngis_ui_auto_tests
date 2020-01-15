package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.io.File;
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
    public WebElement formLibraryTitle;

    @FindBy(xpath = "//h4[contains(text(),'Supporting information')]")
    public WebElement additionalForms;

    @FindBy(xpath = "//h4[@class='form-section-header']")
    List<WebElement> patientChoiceFormsAndInformationTitle;

    @FindBy(xpath = "//a[@class='form-link']")
    List<WebElement> formLibraryLinks;

    @FindBy(xpath = "//p[contains(text(),'Before submitting a referral')]")
    public WebElement helpTextLabel;

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

    @FindBy(xpath = "//span[contains(text(),'Patient choice status')]/following-sibling::span[contains(@class,'css-')]")
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
    @FindBy(xpath = "//li[@class='message-error-line']")
    WebElement filUploadErrorMsg;

    @FindBy(id = "upload_doc")
    WebElement docUpload;
    @FindBy(xpath = "//div[contains(@class,'btn-secondary dropdown-toggle')]")
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

    @FindBy(xpath = "//p[@class='submition-info margin-smaller']")
    public WebElement patientChoiceFormCompletedMessage;

    @FindBy(xpath = "//a[@class='edit-button email-button']")
    public WebElement printPatientChoiceFormButton;

    @FindBy(xpath = "//div[@class='radio-question-error question-error']")
    WebElement errorMessageBox;

    String patientChoiceQuestion = "//h5[contains(text(),\"dummyQuestion\")]";

    @FindBy(xpath = "//div[contains(text(),'Patient choice category')]")
    WebElement patientCategoryReopen;

    @FindBy(xpath = "//div[contains(text(),'Test type')]")
    WebElement testTypeReopen;

    @FindBy(xpath = "//div[@class='header col-sm-5 nowrap'][contains(text(),'Recorded by')]")
    WebElement recordedByReopen;

    @FindBy(xpath = "//div[@class='question-answer-line d-flex']")
    public List<WebElement> selectedPatientChoiceQuestion;

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
    List<WebElement> patientChoiceSubTitles;

    @FindBy(xpath = "//label[@class='radio-container']")
    List<WebElement> patientChoiceAboutResearchOptions;

    String linkText = "//div[contains(@class,'row quicklinks-subnav')]//child::a[contains(text(),'dummyLinkText')]";

    @FindBy(xpath = "//button[@class='btn gel-btn-blue']")
    WebElement amendPatientChoice;

    @FindBy(xpath = "//div[contains(@class,'quicklinks-subnav')]")
    WebElement rowOfLinks;

    private String consulteeAttestationFirstOption = "//label[contains(@id,'Consultee_Q1.0')][text()='" + "dummyValue" + "']";
    private String consulteeAttestationSecondOption = "//label[contains(@id,'Consultee_Q2.0')][text()='" + "dummyValue" + "']";
    private String consulteeAttestationThirdOption = "//label[contains(@id,'Consultee_Q3.0')][text()='" + "dummyValue" + "']";
    private String patientChoiceOptionFirst = "//h5[contains(text(),'Has research participation been discussed')]/following::label[contains(text(),'" + "dummyValue" + "')]";
    private String patientChoiceOptionSecond = "//h5[contains(text(),'used for research, separate to NHS care')]/following::label[contains(text(),'" + "dummyValue" + "')]";
    private String consentIDPath = "//span[text()='dummyID']/preceding::span[@class='consent-out-dated'][1]";

    @FindBy(xpath = "//p[contains(text(),' Confirmation ID')]//span[@class='cct-value']")
    WebElement confirmationID;

    static String consentID;

    @FindBy(xpath = "//div[@class='tile-highlight']")
    WebElement patientChoiceResultTab;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'first name')]/../input[@type='text']")
    WebElement firstNameInput;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'last name')]/../input[@type='text']")
    WebElement lastNameInput;

    @FindBy(xpath = "//label[contains(@id,'Child')]")
    List<WebElement> childAssentOption;

    private String selectTheChildAssentOption = "//label[contains(@id,'Child')][text()='" + "dummyValue" + "']";

    @FindBy(xpath = "//h1[contains(text(),'Patient choice')]")
    public WebElement patientChoiceLandingPageTitle;

    @FindBy(xpath="//p[contains(text(),'the patient and family members need to choose how their samples can be used.')]")
    public WebElement patientChoiceTestPackagePageSubText;

    @FindBy(xpath="//div[contains(@class,'css')]/following::h2[contains(@class,'css')]")
    public WebElement additionalPatientParticipantsName;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@class,'child-element')][1]")
    public WebElement relationField;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@class,'child-element')][2]")
    public WebElement beingTestedField;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@id,'dateOfBirth')]")
    public WebElement dobField;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@id,'gender')]")
    public WebElement genderField;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@id,'patientChoiceStatus')]")
    public WebElement patientChoiceStatusText;

    @FindBy(xpath = "//div[contains(@class,'styles_search-input')]/child::input[@type='text']")
    public WebElement hintTextInSearchBoxOnRequestingOrganisation;

    @FindBy(xpath = "//button[contains(@class,'upload-doc-btn')]")
    WebElement choiceForUploadDocumentButton;

    @FindBy(xpath = "//li[@class='message-blue-line']")
    WebElement uploadMessage;

    @FindBy(xpath = "//span[@class='tooltiptext']")
    WebElement waitForDocUpload;

    @FindBy(xpath = "//div[contains(text(),'Child assent')]")
    WebElement childAssentTitle;

    @FindBy(xpath = "//p[contains(@class,'loading-data-count')]")
    public WebElement formSuccessfullyUploadedMsg;

    @FindBy(xpath = "//label[text()='Date of Signature']")
    WebElement dateOfSignature;

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
            NGISPatientModel familyMember = FamilyMemberDetailsPage.getFamilyMember(familyDetails);
            if(familyMember == null){
                Debugger.println("No Family Member Exists !!!!!");
                return false;
            }
            nhsNumber = familyMember.getNHS_NUMBER();
            if(nhsNumber == null){
                nhsNumber = familyMember.getNGIS_ID();
            }
            String nhsLastFour = nhsNumber.substring(6, nhsNumber.length());//Assuming NHSNumber is always 10 digit.
            Debugger.println("NHSFOUR : "+nhsLastFour);

            By pChoiceEdit = By.xpath(specificPatientChoiceEdit.replaceAll("NHSLastFour", nhsLastFour));
            if (!seleniumLib.isElementPresent(pChoiceEdit)) {
                Wait.seconds(10);
            }
            WebElement element = driver.findElement(pChoiceEdit);
            if (Wait.isElementDisplayed(driver, element, 100)) {
                seleniumLib.clickOnWebElement(element);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on edit patient choice of specific NHSNumber:" + exp);
            SeleniumLib.takeAScreenShot("PatientChoicePageEdit.jpg");
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

    public boolean fillRecordedByDetails(String familyDetails, String recordedBy) {
        try {
            boolean uploadDocument = false;
            String fileType = "";
            String fileName = "";
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(recordedBy);
            Set<String> paramsKey = paramNameValue.keySet();
            for (String key : paramsKey) {
                switch (key) {
                    case "ClinicianName":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            recordingClinicianNameInput.sendKeys(paramNameValue.get(key));
                            if(familyDetails != null && !familyDetails.isEmpty()) {
                                //Updating current family member with Recoding clinician name for later validations
                                NGISPatientModel familyMember = FamilyMemberDetailsPage.getFamilyMember(familyDetails);
                                if (familyMember != null) {
                                    familyMember.setRECORDING_CLINICIAN_NAME(paramNameValue.get(key));
                                    FamilyMemberDetailsPage.updateRecordingClinicianName(familyMember);
                                }
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
                    case "FileName":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            fileName = paramNameValue.get(key);
                        }
                        break;
                }//switch
            }//for
            if (uploadDocument) {
               if(uploadRecordTypeDocument(fileType,fileName)) {
                   return waitForFormUpload("Patient Choices");
               }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in Filling RecordedBy Information: " + exp);
            SeleniumLib.takeAScreenShot("RecordedBy.jpg");
            return false;
        }
    }

    public void clickOnContinue() {
        seleniumLib.clickOnWebElement(continueButton);
    }

    public boolean uploadRecordTypeDocument(String fileType,String fileName) {
        try {
            seleniumLib.clickOnWebElement(uploadDocumentOption);
            Wait.forElementToBeDisplayed(driver, uploadDocumentButton);
            if(!seleniumLib.upload(docUpload, uploadFilepath + fileName)){
                Debugger.println("Could not upload the file:"+fileName);
                return false;
            }
            Wait.seconds(10);//To ensure medium files are uploaded
            seleniumLib.scrollToElement(uploadDocumentOption);
            seleniumLib.clickOnWebElement(fileTypeDropDown);

            By formType = By.xpath(fileTypeDropDownValue.replaceAll("dummyOption", fileType));
            WebElement element = driver.findElement(formType);
            element.click();
            //Date need to pass as today's date. Getting current day moved to TestUtils
            String today[] = TestUtils.getCurrentDay();

            uploadDay.sendKeys(today[0]);
            uploadMonth.sendKeys(today[1]);
            uploadYear.sendKeys(today[2]);
            uploadDay.sendKeys(today[0]);//Purposefully entering again to ensure the continue button enabled
            Wait.seconds(5);
            Debugger.println("Continuing from Record Type...");
            clickOnContinue();
            Wait.seconds(2);
            Debugger.println("Record Type..Done with document upload.");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in uploading record type in Patient choice: " + exp);
            SeleniumLib.takeAScreenShot("recordTypeUpload.jpg");
            return false;
        }
    }

    public boolean selectPatientChoiceOption(String patient_choice) {
        try {
            if (patient_choice == null || patient_choice.isEmpty()) {
                return true;
            }
            if(!verifyTheOptionTitlePresence("Patient choices")){
                Debugger.println("Patient Choice section not loaded.");
                return false;
            }
            String pChoice = patientChoice.replaceAll("dummyChoice", patient_choice);
            WebElement webElement;
            try {
                webElement = driver.findElement(By.xpath(pChoice));
            } catch (NoSuchElementException nsee) {
                //If the element not found, waiting for 5 seconds and the search again. Some times it is taking time.
                Wait.seconds(5);
                webElement = driver.findElement(By.xpath(pChoice));
            }
            seleniumLib.clickOnWebElement(webElement);
            Debugger.println("Patient Choice Option: "+patient_choice+" Selected.");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Selecting PatientChoice:" + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceCategory.jpg");
            return false;
        }
    }

   public boolean selectChildAssent(String child_assent) {
        try {
            if (!Wait.isElementDisplayed(driver, childAssentTitle, 100)) {
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
            String firstElement = patientChoiceOptionFirst.replace("dummyValue", option[0]);
            WebElement firstSelectedOptionResult = driver.findElement(By.xpath(firstElement));
            if (!seleniumLib.isElementPresent(firstSelectedOptionResult)) {
                Debugger.println("1st Yes/No option not found");
            }
            seleniumLib.clickOnWebElement(firstSelectedOptionResult);
            String secondElement = patientChoiceOptionSecond.replace("dummyValue", option[1]);
            WebElement secondSelectedOptionResult = driver.findElement(By.xpath(secondElement));
            if (!seleniumLib.isElementPresent(secondSelectedOptionResult)) {
                Debugger.println("2nd Yes/No option not found");
            }
            seleniumLib.clickOnWebElement(secondSelectedOptionResult);
            Debugger.println("YesNo Completed");
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on 1st YesNoOptions. " + exp);
        }
    }

    public boolean clickingOnResearchParticipationYesNoOptions(String option) {
        try {
            String firstElement = patientChoiceOptionFirst.replace("dummyValue", option);
            WebElement firstSelectedOptionResult = driver.findElement(By.xpath(firstElement));
            if (!seleniumLib.isElementPresent(firstSelectedOptionResult)) {
                Debugger.println("Yes/No option not found.");
                return false;
            }
            seleniumLib.clickOnWebElement(firstSelectedOptionResult);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on 1st YesNoOptions. " + exp);
            return false;
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
            //Logic and component changed as different option while using drwaSignature and Fileupload
            if(Wait.isElementDisplayed(driver,submitPatientChoiceButton,60)){//Waiting for 60 seconds
                if (!Wait.isElementDisplayed(driver, saveAndContinueButton, 30)) {//Waiting for another 30 seconds
                    return false;
                }
            }
            submitPatientChoiceButton.click();
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
            String patientChoiceLinkTab = patientChoiceLink.replaceAll("New patient choice", linkText);
            Wait.seconds(10);//Default observed a delay of 5-10 seconds for loading this section
            WebElement webElement = driver.findElement(By.xpath(patientChoiceLinkTab));
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
                    return true;
                }
            }
            Debugger.println("Expected message:"+message+", Not displayed in Patient Choice.");
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
                Debugger.println("Actual color : " + continueButtonBgColor + " is displayed when continue button should not be highlighted");
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
            if (continueButtonBgColor.equalsIgnoreCase("rgba(240, 240, 240, 1)")) {
                Debugger.println("Actual color : " + continueButtonBgColor + " is displayed when continue button should be highlighted");
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
                Debugger.println("Actual color : " + submitPatientChoiceButtonBgColor + " is displayed when Submit patient choice button is not highlighted");
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
                return true;
            }
            return false;
        } catch (Exception exp) {
            Debugger.println("Continue Button not found. " + exp);
            SeleniumLib.takeAScreenShot("PatientChoicePageContinueBtnStatus.jpg");
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

    public boolean selectedPatientChoiceDetails(String selectedChoice) {
        try {
            boolean isPresent = false;
            String[] choices = selectedChoice.split("::");
            String actualText = "";
            Debugger.println("Expected QN: "+choices[0]+" \nANS:"+choices[1]);
            for (int i = 0; i < selectedPatientChoiceQuestion.size(); i++) {
                actualText = selectedPatientChoiceQuestion.get(i).getText();
                Debugger.println("ACTUAL: "+actualText);
                if(actualText.startsWith(choices[0].trim())) {
                    if (actualText.endsWith(choices[1].trim())) {
                        isPresent = true;
                        Debugger.println("PASS:::");
                        break;
                    }else{
                        Debugger.println("Expected to ends with: "+choices[0]+", But Actual:"+actualText);
                    }
                }
            }
            return isPresent;
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

    public boolean verifyTheQuestionInPatientChoice(String question) {
        try {
            Wait.seconds(5);//Waiting to load the Patient Choice Question
            String questionField = patientChoiceQuestion.replaceAll("dummyQuestion", question);
            By questionElement = By.xpath(questionField);
            if(!seleniumLib.isElementPresent(questionElement)){
                Debugger.println("Expected Question: "+question+" Not present under Patient Choice.Check PatientChoiceQuestion.jpg");
                SeleniumLib.takeAScreenShot("PatientChoiceQuestion.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheQuestionInPatientChoice,Question:"+question+exp);
            return false;
        }
    }

    public boolean errorMessageInPatientChoicePage(String boxColor,String message) {
        try {
            Wait.forElementToBeDisplayed(driver, errorMessageBox);
            if (!seleniumLib.isElementPresent(errorMessageBox)) {
                Debugger.println("Error box not found.");
                return false;
            }
            String borderDetails = errorMessageBox.getCssValue("border");
            String expColor = StylesUtils.convertFontColourBorderColor(boxColor);

            if(!borderDetails.equalsIgnoreCase(expColor)){
                Debugger.println("Expected Border Color is:"+expColor+", but present: "+borderDetails);
                return false;
            }
            //Note: Not able to capture the message and validate the content. Will try and implement.
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
        try {
            Wait.forElementToBeDisplayed(driver, signaturePad, 30);
            if (!seleniumLib.isElementPresent(signaturePad)) {
                Debugger.println("Signature Pad Not loaded for Patient Signature.");
                return false;
            }
            seleniumLib.scrollToElement(signaturePad);
            SeleniumLib.drawSignature(signaturePad);
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice Page: selectPatientSignature: " + exp);
            SeleniumLib.takeAScreenShot("PatientChoicePageSignature.jpg");
            return false;
        }
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

    public boolean previousSectionReclosed() {
        try {
            Wait.forElementToBeDisplayed(driver, patientChoiceCompressed);
            if (!seleniumLib.isElementPresent(patientChoiceCompressed)) {
                Debugger.println("previousSectionsClosed, Patient choice selected section not found. ");
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
        try {
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
        }catch(Exception exp){
            Debugger.println("Exception from drawing Signature in Patient Choice Page."+exp);
        }
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
    public boolean verifyThePatientChoiceSectionTitle(String sectionTitle) {
        try {
            int noOfOptions = patientChoiceSubTitles.size();
            if(noOfOptions < 1){
                Debugger.println("Patient Choice Titles are not displayed.");
                return false;
            }
            String actualTitle = "";
            for (int i = 0; i < noOfOptions; i++) {
                actualTitle = patientChoiceSubTitles.get(i).getText();
                Debugger.println("ACTUAL: "+actualTitle);
                if(actualTitle.equalsIgnoreCase(sectionTitle)){
                    return true;
                }
            }
            Debugger.println("Expected Patient Choice Title: "+sectionTitle+" not present under PatientChoice section.");
            return false;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage:Exception:verifyThePatientChoiceSectionTitle " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceSectionTitle.jpg");
            return false;
        }
    }

    public boolean verifyThePatientChoiceOption(String choiceOption) {
        try {
            int noOfOptions = patientChoiceAboutResearchOptions.size();
            if(noOfOptions < 1){
                Debugger.println("Patient Choice Options are not displayed.");
                return false;
            }
            String actualOption = "";
            for (int i = 0; i < noOfOptions; i++) {
                actualOption = patientChoiceAboutResearchOptions.get(i).getText();
                if(actualOption.equalsIgnoreCase(choiceOption)){
                    return true;
                }
            }
            Debugger.println("Expected Patient Choice Option: "+choiceOption+" not present under PatientChoice section.");
            return false;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyThePatientChoiceOptionsForConsultee " + exp);
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

    public boolean verifyFormsTitleUnderFormsLibrary(String formsTitle) {
        try {
            seleniumLib.waitForElementVisible(formLibraryTitle);
            if (!formsTitle.equalsIgnoreCase(formLibraryTitle.getText())) {
                Debugger.println("Expected Subtitle: " + formsTitle + ", but Actual Title is: " + formLibraryTitle.getText());
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice: Form Library title not found" + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceFormTitle.jpg");
            return false;
        }
    }

    public boolean verifyAdditionalFormsSection(String formsSection) {
        try {
            seleniumLib.waitForElementVisible(additionalForms);
            if (!formsSection.equalsIgnoreCase(additionalForms.getText())) {
                Debugger.println("Expected Subtitle: " + formsSection + ", but Actual Subtitle is: " + additionalForms.getText());
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice: Form Library title: Additional Forms not found" + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceFormSection.jpg");
            return false;
        }
    }

    public boolean clickOnLink(String link) {
        Wait.forElementToBeDisplayed(driver, rowOfLinks, 10);
        try {
            String dummyLink = linkText.replaceAll("dummyLinkText", link);
            WebElement webElement = driver.findElement(By.xpath(dummyLink));
            if (Wait.isElementDisplayed(driver, webElement, 3)) {
                seleniumLib.clickOnWebElement(webElement);
            } else {
                Debugger.println("Links on page after form loading " + link + " not loaded.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice Page: Click on Link: " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceFormPageLinks.jpg");
            return false;
        }
    }

    public boolean clickOnAmendPatientChoice() {
        Wait.forElementToBeDisplayed(driver, amendPatientChoice);
        try {
            seleniumLib.clickOnWebElement(amendPatientChoice);
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice Page: click on amend patient choice: " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceAmendOption.jpg");
            return false;
        }
    }

    public void clickingOnLacksCapacityOfConsulteeForThePerson(String option) {
        try {
            Actions.scrollToTop(driver);
            String firstOption = consulteeAttestationFirstOption.replace("dummyValue", option);
            WebElement firstOptionResult = driver.findElement(By.xpath(firstOption));
            if (!seleniumLib.isElementPresent(firstOptionResult)) {
                Debugger.println("1st Yes/No option not found");
            }
            seleniumLib.clickOnWebElement(firstOptionResult);
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on Lacks Capacity Of Consultee For The Person YesNoOptions. " + exp);
        }
    }

    public void clickingOnNationalGenomicResearchLibraryForThePerson(String option) {
        try {
            Actions.scrollToTop(driver);
            String secondOption = consulteeAttestationSecondOption.replace("dummyValue", option);
            WebElement secondOptionResult = driver.findElement(By.xpath(secondOption));
            if (!seleniumLib.isElementPresent(secondOptionResult)) {
                Debugger.println("1st Yes/No option not found");
            }
            seleniumLib.clickOnWebElement(secondOptionResult);
            Debugger.println(option + " option is selected...");
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on National Genomic Research Library YesNoOptions. " + exp);
        }
    }



    public void clickingOnWillingToAcceptTheRoleOfConsulteeForThePerson(String option) {
        try {
            Actions.scrollToTop(driver);
            String thirdOption = consulteeAttestationThirdOption.replace("dummyValue", option);
            WebElement thirdOptionResult = driver.findElement(By.xpath(thirdOption));
            if (!seleniumLib.isElementPresent(thirdOptionResult)) {
                Debugger.println("1st Yes/No option not found");
            }
            seleniumLib.clickOnWebElement(thirdOptionResult);
       } catch (Exception exp) {
            Debugger.println("Exception from clicking on Willing To Accept The Role Of Consultee YesNoOptions. " + exp);
        }
    }

    public boolean fillTheSignatureDetails(String signatureDetails) {
        try {

            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(signatureDetails);
            Set<String> paramsKey = paramNameValue.keySet();
            for (String key : paramsKey) {
                switch (key) {
                    case "FirstName":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            firstNameInput.sendKeys(paramNameValue.get(key));
                        }
                        break;
                    case "LastName":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            lastNameInput.sendKeys(paramNameValue.get(key));
                        }
                        break;
                }//switch
            }//for
            seleniumLib.clickOnWebElement(signaturePad);
            SeleniumLib.drawSignature(signaturePad);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in Filling Signature Information: " + exp);
            SeleniumLib.takeAScreenShot("Signature.jpg");
            return false;
        }
    }

    public boolean patientChoiceUnderHistoryTab() {
        try {
            Wait.forElementToBeDisplayed(driver, patientChoiceResultTab);
            if (!seleniumLib.isElementPresent(confirmationID)) {
                Debugger.println("Confirmation ID is not found...");
                return false;
            }
            consentID = confirmationID.getText();
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice result tab not found...");
            return false;
        }
    }

    public boolean replacedPatientChoiceUnderHistoryTab() {
        try {
            Wait.forElementToBeDisplayed(driver, patientChoiceResultTab);
            String replacedPath = consentIDPath.replaceAll("dummyID",consentID);
            WebElement replacedPatientChoice = driver.findElement(By.xpath(replacedPath));
            if (!seleniumLib.isElementPresent(replacedPatientChoice)) {
                Debugger.println("Replaced element not found in patient choice page..");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice result tab not found...");
            SeleniumLib.takeAScreenShot("PatientChoiceTabNotFound.jpg");
            return false;
        }
    }
    public boolean verifyTheChildAssentOption(String choiceOption) {
        try {
            int noOfOptions = childAssentOption.size();
            if(noOfOptions < 1){
                Debugger.println("Child Assent Options are not displayed.");
                return false;
            }
            String actualOption = "";
            for (int i = 0; i < noOfOptions; i++) {
                actualOption = childAssentOption.get(i).getText();
                if(actualOption.equalsIgnoreCase(choiceOption)){
                    return true;
                }
            }
            Debugger.println("Expected Child Assent Option: "+choiceOption+" not present under Child Assent section.");
            return false;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyTheChildAssentOptions " + exp);
            return false;
        }
    }

    public void clickingOnChildAgreeToParticipate(String option) {
        try {
            Actions.scrollToTop(driver);
            String selectOption = selectTheChildAssentOption.replace("dummyValue", option);
            WebElement selectedResult = driver.findElement(By.xpath(selectOption));
            if (!seleniumLib.isElementPresent(selectedResult)) {
                Debugger.println("To select Yes/No/Not applicable option not found");
            }
            seleniumLib.clickOnWebElement(selectedResult);
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on child agree to participate YesNoOptions. " + exp);
        }
    }

    public boolean enabledUploadDocumentButtonOnPatientChoiceFormPage() {
        try {
            Wait.forElementToBeDisplayed(driver, choiceForUploadDocumentButton);
            if (!choiceForUploadDocumentButton.isEnabled()) {
                Debugger.println("Add patient Choice Page:Recorded by:UploadDocumentButton beside continue Button Not found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from PatientChoicePage: enabledUploadDocumentButtonOnPatientChoiceFormPage " + exp);
            return false;
        }
    }

    public boolean verifyUploadMessage(String message) {
        try {
            Wait.forElementToBeDisplayed(driver, uploadMessage);
            if(!message.equalsIgnoreCase(uploadMessage.getText())){
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage, verifyUploadMessage - message not found in upload section." + exp);
            return false;
        }
    }

    public boolean waitForFormUpload(String expTitle) {
        Debugger.println("Waiting for form Upload...");
        //Logic has changed as tooltip coming for big files while uplaoding.Waiting to dissappear the tooltip.
        seleniumLib.moveAndClickOn(continueButton);
        if(seleniumLib.isElementPresent(waitForDocUpload)){
            Wait.forElementToDisappear(driver,By.xpath("//span[@class='tooltiptext']"));
            if(seleniumLib.isElementPresent(waitForDocUpload)){
                Wait.forElementToDisappear(driver,By.xpath("//span[@class='tooltiptext']"));
                if(seleniumLib.isElementPresent(waitForDocUpload)){
                   return false;
                }
                return true;
            }
        }
        return true;
    }

    public boolean verifyTheElementsOnPatientChoiceTestPackagePage(){
        Wait.forElementToBeDisplayed(driver, patientChoiceLandingPageTitle);
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(patientChoiceLandingPageTitle);
        expElements.add(patientChoiceTestPackagePageSubText);
        expElements.add(additionalPatientParticipantsName);
        expElements.add(relationField);
        expElements.add(beingTestedField);
        expElements.add(dobField);
        expElements.add(genderField);
        expElements.add(patientChoiceStatusText);
        expElements.add(continueButton);

        for (int i = 0; i < expElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expElements.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyFormUploadSuccessMessage(String expMessage) {
        try {
            Wait.forElementToBeDisplayed(driver, formSuccessfullyUploadedMsg);
            String actualMessage = formSuccessfullyUploadedMsg.getText();
            if (!expMessage.equalsIgnoreCase(actualMessage)) {
                Debugger.println("Patient choice page:verifyFormUploadSuccessMessage: message not matching");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient choice page:verifyFormUploadSuccessMessage: message not found " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceFormUploadSuccessMsg.jpg");
            return false;
        }
    }
    public boolean verifyInvalidFileUploadMessages(String fileName, String expMessage) {
        try {
            seleniumLib.clickOnWebElement(uploadDocumentOption);
            Wait.forElementToBeDisplayed(driver, uploadDocumentButton);
            seleniumLib.upload(docUpload, uploadFilepath + fileName);
            //Wait for 5 seconds to get the error message
            if (!Wait.isElementDisplayed(driver, filUploadErrorMsg, 30)) {
                //Error message not yet displayed...
                Debugger.println("Error Message for Unsupported file type not displayed");
                return false;
            }
            String actMessage = filUploadErrorMsg.getText();
            //Read the message.. and compare with what we pass
            if (!actMessage.contains(expMessage)) {
                Debugger.println("Expected Error Message: " + expMessage + ", But Actual is:" + actMessage);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyInvalidFileUploadMessages: " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceDocUpload.jpg");
            return false;
        }
    }

    public void clickTheUploadDocumentButton() {
        try {
            Wait.forElementToBeDisplayed(driver, uploadDocumentOption);
            Wait.forElementToBeClickable(driver, uploadDocumentOption);
            seleniumLib.clickOnWebElement(uploadDocumentOption);
        } catch (Exception exp) {
            Debugger.println("Exception from PatientChoicePage:clickTheUploadDocumentButton: " + exp);
            SeleniumLib.takeAScreenShot("PatientChoicePageUploadDocumentButton.jpg");
            Assert.assertFalse("PatientChoicePage:clickTheUploadDocumentButton:Exception:" + exp, true);
        }
    }

    public boolean uploadDocumentInRecordedBy(String fileName) {
        try {
            seleniumLib.clickOnWebElement(uploadDocumentOption);
            Wait.forElementToBeDisplayed(driver, uploadDocumentButton);
            // Uploading file by sending webelement and file name with path as value
            seleniumLib.upload(docUpload, uploadFilepath + fileName);
            if (!Wait.isElementDisplayed(driver, formSuccessfullyUploadedMsg, 20)) {
                //success message not yet displayed...
                Debugger.println("Success Message for file upload not displayed");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from uploading file..." + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceUploadDocumentInRecordedBy.jpg");
            return false;
        }
    }

    public boolean verifyTheDropDownAndDOBFieldOfPatientChoice() {
        try {
            Wait.forElementToBeDisplayed(driver, fileTypeDropDown);
            if (!seleniumLib.isElementPresent(fileTypeDropDown)) {
                Debugger.println("Dropdown is not present after uploading the file...");
                return false;
            }
            if (!seleniumLib.isElementPresent(uploadDay)) {
                Debugger.println("Day is not present after uploading the file...");
                return false;
            }
            if (!seleniumLib.isElementPresent(uploadMonth)) {
                Debugger.println("Month is not present after uploading the file...");
                return false;
            }
            if (!seleniumLib.isElementPresent(uploadYear)) {
                Debugger.println("Year is not present after uploading the file...");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient choice page:verifyTheDropDownAndDOBFieldOfPatientChoice, Exception found " + exp);
            SeleniumLib.takeAScreenShot("PatientChoicePageDropDownAndDOBField.jpg");
            return false;
        }
    }
    public boolean dateOfSignatureStatusInRecordedBY() {
        try {
            Wait.forElementToBeDisplayed(driver, dateOfSignature);
            if (uploadDay.isEnabled() && uploadMonth.isEnabled() && uploadYear.isEnabled()) {
                Debugger.println("Date of Signature fields are in enabled state.");
                return true;
            }
            Debugger.println("Date of Signature fields are in disabled state.");
            return false;
        } catch (Exception exp) {
            Debugger.println("Date of Signature fields not found. " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceDateofSignatureStatus.jpg");
            return false;
        }
    }

    public boolean selectUploadFormType(String dropdownValue) {
        try {
            Wait.forElementToBeDisplayed(driver, fileTypeDropDown);
            seleniumLib.scrollToElement(fileTypeDropDown);
            seleniumLib.clickOnWebElement(fileTypeDropDown);
//            Debugger.println("After clicking dropdown.....");
            By formType = By.xpath(fileTypeDropDownValue.replaceAll("dummyOption", dropdownValue));
            WebElement dropdownElement = driver.findElement(formType);
            if (!seleniumLib.isElementPresent(dropdownElement)) {
                Debugger.println("Form type dropdown value not found");
                return false;
            }
            seleniumLib.clickOnWebElement(dropdownElement);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from selecting dropdown in recorded by" + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceRecordedByPleaseSelectDropDown.jpg");
            return false;
        }
    }

    // new method created to fill any date being passed which replaces the fillingTheDOBInRecordedBy method which was only filling today's date
    public boolean fillTheDateOfSignatureInRecordedBy(String signatureDate) {
        try {
            Wait.forElementToBeDisplayed(driver, dateOfSignature);
            if (signatureDate != null && !signatureDate.isEmpty()) {
                String[] dobSplit = signatureDate.split("/");
                uploadDay.sendKeys(dobSplit[0]);
                uploadMonth.sendKeys(dobSplit[1]);
                uploadYear.sendKeys(dobSplit[2]);
                return true;
            }
            Debugger.println("Date of Signature value is not present ");
            return false;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: fillTheDateOfSignatureInRecordedBy: " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceDateofSignatureFilling.jpg");
            return false;
        }
    }

}//end
