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

    @FindBy(xpath = "//p[contains(text(),'Before submitting a referral')]")
    public WebElement helpTextLabel;

    String selectedOption = "//div[@class='current-value'][contains(text(),'dummyOption')]";
    String editButton = "//div[contains(text(),'dummyOption')]/following::button[@class='edit-button btn']";

    @FindBy(xpath = "//button[@aria-label='edit button']")
    public WebElement editPatientChoice;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'Recording clinician name')]/../input[@type='text']")
    WebElement recordingClinicianNameInput;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'Responsible clinician name')]/../input[@type='text']")
    WebElement responsibleClinicianNameInput;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'Document scanned and checked by:')]/../input[@type='text']")
    WebElement documentCheckedByInput;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'Patient Hospital Number')]/../input[@type='text']")
    WebElement patientHospitalNumberInput;

    @FindBy(xpath = "//div[@class='text-input']/label[contains(text(),'Admin Name / Email')]/../input[@type='text']")
    WebElement adminNameInput;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    public WebElement continueButton;

    @FindBy(xpath = "//button[contains(text(),'Form to follow')]")
    public WebElement formToFollow;

    @FindBy(xpath = "//button[contains(text(),'Save and continue')]")
    public WebElement saveAndContinueButton;

    @FindBy(xpath = "//label[contains(@class,'upload-document-button')]")
    public WebElement uploadDocumentButton;

    @FindBy(xpath = "//div[@class='m-signature-pad--body']//canvas")
    WebElement signaturePad;
    @FindBy(xpath = "//*[contains(@id,'signature-pad')]//child::canvas")
    public WebElement signatureSection;

    @FindBy(xpath = "//span[contains(text(),'Patient choice status')]/following-sibling::span[contains(@class,'css-')]")
    List<WebElement> patientChoiceStatus;

    String patientChoiceCategory = "//label[contains(@class,'radio-container')][text()='dummyCategory']";
    String testType = "//label[contains(@class,'radio-container')][text()='dummyTestType']";
    String childAssent = "//label[contains(@class,'radio-container')][contains(text(),'dummyAssent')]";

    //For PatientInformation Identifiers
    String patientList = "//div[contains(@class,'styles_participant-list_')]/div[contains(@class,'css')]";
    String firstNameLastName = "//div[contains(@class,'styles_participant-list_')]//span[contains(@class,'css-')]//h2";
    String probandBeingTested = "//div[contains(@class,'styles_participant-list_')]//span[contains(@class,'child-element')]";
    String bornInformation = "//div[contains(@class,'styles_participant-list_')]//span[contains(@id,'dateOfBirth')]";
    String genderInformation = "//div[contains(@class,'styles_participant-list_')]//span[contains(@id,'gender')]";
    String ngsIdInformation = "//div[contains(@class,'styles_participant-list_')]//span[contains(@id,'ngisId')]";
    String patientChoiceInformation = "//span[contains(@id,'patientChoiceStatus')]";
    String editButtonInformation = "//button[@aria-label='edit button']";

    String fileTypeDropDownValue = "//a[@class='dropdown-item'][contains(text(),'dummyOption')]";

    String uploadFilepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator;

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

    @FindBy(xpath = "//label[@id='Choices_Q1.0-3']")
    public WebElement patientChoiceNotRequiredForTheTest;

    @FindBy(id = "Choices_Q2.3-0")
    public WebElement agreeResearchParticipation;

    @FindBy(id = "Choices_Q2.3.1-0")
    public WebElement agreeSampleUsage;

    @FindBy(id = "Child-0")
    public WebElement childAssentYes;

    @FindBy(id = "Consultee_Q1.0-0")
    public WebElement consulteeReadYes;

    @FindBy(id = "Consultee_Q2.0-0")
    public WebElement consulteeConsultedYes;

    @FindBy(id = "Consultee_Q3.0-0")
    public WebElement consulteeAgreedYes;

    @FindBy(css = "button[class*='submit-signature-button']")
    public WebElement submitSignatureButton;

    @FindBy(css = "button[class*='submit-button']")
    public WebElement submitButton;

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

    @FindBy(xpath = "//p[@class='submition-info margin-smaller']")
    public WebElement patientChoiceFormCompletedMessage;

    @FindBy(xpath = "//a[@class='edit-button email-button']")
    public WebElement printPatientChoiceFormButton;

    @FindBy(xpath = "//div[@class='radio-question-error question-error']")
    WebElement errorMessageBox;

    @FindBy(xpath = "//div[contains(text(),'Patient choice category')]")
    WebElement patientCategoryReopen;

    @FindBy(xpath = "//div[contains(text(),'Test type')]")
    WebElement testTypeReopen;

    @FindBy(xpath = "//div[@class='header col-sm-5 nowrap'][contains(text(),'Recorded by')]")
    WebElement recordedByReopen;

    @FindBy(xpath = "//button[contains(text(),'Submit ')]")
    public WebElement submitPatientChoiceButton;

    @FindBy(xpath = "//ul[@class='message-list']")
    WebElement warningMessageBox;

    @FindBy(xpath = "//ul[@class='message-list']/li")
    List<WebElement> warningMessages;

    @FindBy(xpath = "//div[@data-testid='notification-warning']/span")
    List<WebElement> notificationWarning;

    @FindBy(className = "steps-list")
    public WebElement stepsList;

    String optionIsList = "//div[contains(text(),'" + "dummyOption" + "')]//ancestor::div[contains(@class,'accordion completed')]";

    @FindBy(xpath = "//button[@class='btn gel-btn-blue']")
    WebElement amendPatientChoice;

    @FindBy(xpath = "//button[contains(@class,'clear-button')]")
    WebElement signatureClearButton;

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

    @FindBy(xpath = "//div[contains(@class,'styles_search-input')]/child::input[@type='text']")
    public WebElement hintTextInSearchBoxOnRequestingOrganisation;

    @FindBy(xpath = "//span[@class='tooltiptext']")
    WebElement waitForDocUpload;

    @FindBy(xpath = "//div[contains(text(),'Child assent')]")
    WebElement childAssentTitle;

    String formSection = "//div[@class='form-section-container']/child::h4[text()='dummySection']";
    String formLinks = "//div[@class='form-section-container']/child::h4[text()='dummySection']/..//a";
    String selectedChoices = "//div[text()='dummySection']/../..//div[@class='question-answer-line d-flex']";
    String selectedTabTitle = "//h2[contains(text(),'dummySubtitle')]";
    String sectionOptions = "//div[@class='steps-list']//div[text()='dummySection']/../..//label[contains(@class,'radio-container')]";
    String sectionTitle = "//div[contains(text(),'dummySection')]";
    String mandatoryFieldSymbol = "//dummyFieldType[contains(text(),'dummyLabel')]/div";

    String questionTitle = "//div[@class='question-title']/h5[ contains(text(),\"dummyQuestion\")]";
    String questionOptions = "//div[@class='question-title']/h5[ contains(text(),\"dummyQuestion\")]/../..//label";

    @FindBy(xpath = "//p[@class='form-subheader']")
    WebElement formSubHeader;

    @FindBy(xpath = "//a[@class='back-button d-block']")
    WebElement formLiraryBackButton;

    @FindBy(xpath = "//div[@class='dropdown-content']//a[@class='dropdown-item']")
    public List<WebElement> dropDownValues;

    @FindBy(xpath = "//li[@class='message-error-line']")
    WebElement filUploadErrorMsg;

    String optionalFieldNames = "//label[text()='dummyLabel']";

    @FindBy(xpath = "//span[contains(@aria-labelledby,'ngisId')]")
    List<WebElement> ngsIdValues;
    @FindBy(xpath = "//li[@class='message-blue-line']")
    WebElement uploadMessage;

    @FindBy(xpath = "//p[contains(@class,'loading-data-count')]")
    public WebElement fileUploadSuccessMsg;

    @FindBy(xpath = "//p[contains(@class,'uploaded-filename')]")
    public WebElement uploadedFileName;

    @FindBy(xpath = "//p[text()=' Referral ID:']//span[@class='cct-value']")
    public WebElement referalIdOnHistoryTab;

    @FindBy(xpath = "//span[text()='Referral ID']/parent::li")
    public WebElement referralIdOnReferralBar;

    @FindBy(xpath = "//img[contains(@class,'cancel-logo')]")
    public WebElement cancelUpload;

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

    public boolean verifySelectedTabInPatientChoice(String tabSectionTitle) {
        String selectedSubtitle = selectedTabTitle.replaceAll("dummySubtitle", tabSectionTitle);
        try {
            WebElement subTitleElement = driver.findElement(By.xpath(selectedSubtitle));
            Wait.forElementToBeDisplayed(driver, subTitleElement, 100);
            if (!Wait.isElementDisplayed(driver, subTitleElement, 30)) {
                Debugger.println("Expected subtitle:" + tabSectionTitle + " not present in Patient choice. Pls check PCSubtitle.jpg");
                SeleniumLib.takeAScreenShot("PCSubtitle.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Wait.seconds(10);//This is introduced based on the screenshot error. This wait will apply only on exceptional cases
            WebElement subTitleElement = driver.findElement(By.xpath(selectedSubtitle));
            if (Wait.isElementDisplayed(driver, subTitleElement, 10)) {
                return true;
            }
            Debugger.println("Exception in verifying the selected tab section in PatientChoice." + exp);
            SeleniumLib.takeAScreenShot("PCSubtitle.jpg");
            return false;
        }
    }

    public boolean selectPatientChoiceCategory(String category) {
        String categoryToBeSelected = patientChoiceCategory.replaceAll("dummyCategory", category);
        WebElement webElement = null;
        try {
            Wait.seconds(10);//Default observed a delay of 5-10 seconds for loading this section
            webElement = driver.findElement(By.xpath(categoryToBeSelected));
            if (Wait.isElementDisplayed(driver, webElement, 100)) {
                seleniumLib.clickOnWebElement(webElement);
                return true;
            }
            return false;
        } catch (NoSuchElementException exp) {
            //Waiting for another 20 seconds and trying again - Added this based on the errors observed
            Wait.seconds(20);
            webElement = driver.findElement(By.xpath(categoryToBeSelected));
            if (Wait.isElementDisplayed(driver, webElement, 100)) {
                seleniumLib.clickOnWebElement(webElement);
                return true;
            }
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception from Selecting PatientChoiceCategory:" + exp);
            SeleniumLib.takeAScreenShot("patientChoiceCategory.jpg");
            return false;
        }
    }

    public boolean selectOptionInSection(String optionName, String sectionName) {
        try {
            if (optionName == null || optionName.isEmpty()) {//Not selecting any option
                return true;
            }
            String options = sectionOptions.replaceAll("dummySection", sectionName);
            List<WebElement> optionsList = driver.findElements(By.xpath(options));
            if (optionsList == null || optionsList.size() == 0) {
                Debugger.println("Could not find any options under the section :" + sectionName);
                return false;
            }
            boolean isFound = false;
            for (int i = 0; i < optionsList.size(); i++) {
                if (optionsList.get(i).getText().equalsIgnoreCase(optionName)) {
                    isFound = true;
                    Actions.clickElement(driver, optionsList.get(i));
                    break;
                }
            }
            if (!isFound) {
                Debugger.println("Option :" + optionName + " could not found under the section :" + sectionName);
            }
            return isFound;
        } catch (Exception exp) {
            Debugger.println("Exception from Selecting PatientChoiceCategory:" + exp);
            SeleniumLib.takeAScreenShot("patientChoice.jpg");
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
                    case "RecordingClinicianName":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            recordingClinicianNameInput.sendKeys(paramNameValue.get(key));
                            if (familyDetails != null && !familyDetails.isEmpty()) {
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
                    case "ResponsibleClinicianName":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            responsibleClinicianNameInput.sendKeys(paramNameValue.get(key));
                        }
                        break;
                    case "DocumentCheckedBy":
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            documentCheckedByInput.sendKeys(paramNameValue.get(key));
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
                if (uploadRecordTypeDocument(fileType, fileName)) {
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
        try {
            if (Wait.isElementDisplayed(driver, continueButton, 10)) {
                Actions.retryClickAndIgnoreElementInterception(driver, continueButton);
            } else if (Wait.isElementDisplayed(driver, formToFollow, 10)) {
                Actions.clickElement(driver, formToFollow);
            }
        } catch (Exception exp) {
            Debugger.println("Exception in clicking on Continue Button in PC:" + exp);
            SeleniumLib.takeAScreenShot("PCContinueButton.jpg");
        }
    }

    public boolean uploadRecordTypeDocument(String fileType, String fileName) {
        try {
            Wait.forElementToBeDisplayed(driver, uploadDocumentButton);
            if (!seleniumLib.upload(docUpload, uploadFilepath + fileName)) {
                Debugger.println("Could not upload the file:" + fileName);
                return false;
            }
            Wait.seconds(5);//To ensure medium files are uploaded
            if (fileType == null || fileType.isEmpty()) {
                //No need to select the file type and date. For another validations, page remains as it is
                return true;
            }
            if (!selectUploadFormType(fileType)) {
                Debugger.println("Could not select the file type drop down: " + fileType);
                SeleniumLib.takeAScreenShot("FileDropDown.jpg");
                return false;
            }
            //Date need to pass as today's date. Getting current day moved to TestUtils
            if (!fillTheDateOfSignatureInRecordedBy()) {
                Debugger.println("Could not fill the date of signature of file upload.");
                SeleniumLib.takeAScreenShot("DateOfSignature.jpg");
                return false;
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in uploading record type in Patient choice: " + exp);
            SeleniumLib.takeAScreenShot("recordTypeUpload.jpg");
            return false;
        }
    }

    //This is used for End to end RD user journey
    public boolean selectDefaultPatientChoices() {
        try {
            Click.element(driver, agreeTestChoice);
            Click.element(driver, agreeResearchParticipation);
            Click.element(driver, agreeSampleUsage);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Setting default Patient Choice: " + exp);
            SeleniumLib.takeAScreenShot("DefaultPatientChoice.jpg");
            return false;
        }
    }

    public boolean selectOptionForQuestion(String option, String question) {
        try {
            Actions.scrollToTop(driver);
            String optionsString = questionOptions.replaceAll("dummyQuestion", question);
            List<WebElement> options = driver.findElements(By.xpath(optionsString));
            boolean isFound = false;
            for (int i = 0; i < options.size(); i++) {
                if (options.get(i).getText().equalsIgnoreCase(option)) {
                    options.get(i).click();
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                Debugger.println("Option: " + option + " not present for the question:" + question);
                SeleniumLib.takeAScreenShot("PCOptionNotSelected.jpg");
                Actions.scrollToBottom(driver);
                SeleniumLib.takeAScreenShot("PCOptionNotPresent.jpg");
            }
            return isFound;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyTheQuestionOptions " + exp);
            SeleniumLib.takeAScreenShot("PCOptionNotSelected.jpg");
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
                if (!SeleniumLib.drawSignature(signatureSection)) {
                    seleniumLib.clickOnWebElement(signaturePad);
                }
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

    public boolean submitPatientChoice() {
        try {
            //Logic and component changed as different option while using drwaSignature and Fileupload
            if (Wait.isElementDisplayed(driver, submitPatientChoiceButton, 60)) {//Waiting for 60 seconds
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

    public boolean verifyPatientChoiceStatus(String expStatus, int index) {
        try {
            int noOfStatus = patientChoiceStatus.size();
            if (noOfStatus < index) {
                Debugger.println("Patient choice status not displayed for member at " + index + " position");
                SeleniumLib.takeAScreenShot("PatientChoiceStatus.jpg");
                return false;
            }
            Wait.forElementToBeDisplayed(driver, patientChoiceStatus.get(index), 30);
            String actStatus = patientChoiceStatus.get(index).getText();
            if (!expStatus.equalsIgnoreCase(actStatus)) {
                Debugger.println("Expected Patient Choice: " + expStatus + ", But actual is: " + actStatus);
                SeleniumLib.takeAScreenShot("PatientChoiceStatus.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifyPatientChoiceStatus :" + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceStatus.jpg");
            return false;
        }
    }

    public boolean clickOnPatientChoiceInformationLink(String linkText) {
        try {
            if (linkText == null || linkText.isEmpty()) {
                return true;
            }
            // Wait.forElementToBeDisplayed(driver, newPatientChoiceFormTitle, 100);
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

    public boolean verifySelectedOption(String expectedResult) {
        try {
            String selectedOptionField = selectedOption.replaceAll("dummyOption", expectedResult);
            WebElement selectedOptionResult = driver.findElement(By.xpath(selectedOptionField));
            if (!Wait.isElementDisplayed(driver, selectedOptionResult, 30)) {
                Debugger.println("Element before Edit button not found for " + expectedResult);
                SeleniumLib.takeAScreenShot("PCOptionTitle.jpg");
                return false;
            }
            if (!selectedOptionResult.getText().contains(expectedResult)) {
                Debugger.println("Title before edit button is not matching: pls check PCOptionTitle.jpg");
                SeleniumLib.takeAScreenShot("PCOptionTitle.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from validating title of PC option:" + exp);
            SeleniumLib.takeAScreenShot("PCOptionTitle.jpg");
            return false;
        }
    }

    public boolean verifyEditButton(String category) {
        try {
            String editButtonField = editButton.replaceAll("dummyOption", category);
            WebElement editButtonResult = driver.findElement(By.xpath(editButtonField));
            if (!Wait.isElementDisplayed(driver, editButtonResult, 30)) {
                Debugger.println("Edit option not present for section:" + category + ". Pls check EditOptionNotPresent.jpg");
                SeleniumLib.takeAScreenShot("EditOptionNotPresent.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in checking the Edit option for:" + category + ":" + exp);
            SeleniumLib.takeAScreenShot("EditOptionNotPresent.jpg");
            return false;
        }
    }

    public boolean verifyTheSectionTitle(String sectionName) {
        try {
            if (Actions.isAlertPresent(driver)) {
                Actions.dismissAlert(driver);
            }
            String section = sectionTitle.replaceAll("dummySection", sectionName);
            return seleniumLib.isElementPresent(By.xpath(section));
        } catch (Exception exp) {
            Debugger.println("Section :" + sectionName + " not present.");
            return false;
        }
    }

    public boolean optionIsCompleted(String option) {
        WebElement webElementLocator = null;
        try {
            Wait.forElementToBeDisplayed(driver, stepsList);
            String elementLocator = optionIsList.replace("dummyOption", option);
            webElementLocator = driver.findElement(By.xpath(elementLocator));
            if (!Wait.isElementDisplayed(driver, webElementLocator, 60)) {
                Debugger.println("Option " + option + " is not marked as completed as expected.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            try {
                Actions.scrollToTop(driver);
                if (!Wait.isElementDisplayed(driver, webElementLocator, 60)) {
                    Debugger.println("Option " + option + " is not marked as completed as expected.");
                    return false;
                }
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception in Checking patient_choice_form's option completion status: " + exp);
                SeleniumLib.takeAScreenShot("PCOptionComplete.jpg");
                return false;
            }
        }
    }

    public boolean verifyWarningMessage(String message) {
        try {
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, warningMessageBox);
            for (int i = 0; i < warningMessages.size(); i++) {
                Debugger.println("WM:ACT:"+warningMessages.get(i).getText());
                if (message.equalsIgnoreCase(warningMessages.get(i).getText())) {
                    return true;
                }
            }
            Debugger.println("WarningMessage:" + message + " not present.");
            SeleniumLib.takeAScreenShot("NoWarningMessage.jpg");
            Actions.scrollToTop(driver);
            SeleniumLib.takeAScreenShot("NoWarningMessage1.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception validating warning message in PC: " + exp);
            SeleniumLib.takeAScreenShot("PCWarningMessage.jpg");
            return false;
        }
    }

    public boolean verifyNotificationWarning(String message) {
        try {
            Wait.seconds(2);
            for (int i = 0; i < notificationWarning.size(); i++) {
                if (notificationWarning.get(i).getText().contains(message)) {
                    return true;
                }
            }
            SeleniumLib.takeAScreenShot("NoWarningMessage.jpg");
            Actions.scrollToTop(driver);
            SeleniumLib.takeAScreenShot("NoWarningMessage1.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage, patientChoiceInformationWarningMessage - warning message box not found. " + exp);
            SeleniumLib.takeAScreenShot("NoWarningMessage.jpg");
            return false;
        }
    }

    public boolean verifySubmitPatientChoiceButtonStatus(String expectedColor) {
        try {
            Wait.forElementToBeDisplayed(driver, submitPatientChoiceButton);
            String expectedBackground = StylesUtils.convertFontColourStringToCSSProperty(expectedColor);
            String actualColor = submitPatientChoiceButton.getCssValue("background-color");
            if (!actualColor.equalsIgnoreCase(expectedBackground)) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying Submit Patient Choice Status:" + exp);
            return false;
        }
    }

    public boolean verifySaveAndContinueButtonStatus() {
        try {
            Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
            if (saveAndContinueButton.isEnabled()) {
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

    public boolean verifySelectedChoicesInSection(String sectionName, String selectedChoice) {
        try {
            boolean isPresent = false;
            String[] choices = selectedChoice.split("::");
            String actualText = "";
            String selectedAnswers = selectedChoices.replaceAll("dummySection", sectionName);
            List<WebElement> answersList = driver.findElements(By.xpath(selectedAnswers));
            for (int i = 0; i < answersList.size(); i++) {
                actualText = answersList.get(i).getText();
                if (actualText.startsWith(choices[0].trim())) {
                    if (actualText.endsWith(choices[1].trim())) {
                        isPresent = true;
                        break;
                    } else {
                        Debugger.println("Expected Answer: " + choices[1] + ", But Actual:" + actualText);
                    }
                }
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying selected choices in section:" + sectionName + "\n" + exp);
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

    public boolean errorMessageInPatientChoicePage(String boxColor, String message) {
        try {
            Actions.scrollToTop(driver);
            Wait.forElementToBeDisplayed(driver, errorMessageBox);
            if (!seleniumLib.isElementPresent(errorMessageBox)) {
                Debugger.println("Error box not found.");
                return false;
            }
            String borderDetails = errorMessageBox.getCssValue("border");
            String expColor = StylesUtils.convertFontColourBorderColor(boxColor);

            if (!borderDetails.equalsIgnoreCase(expColor)) {
                Debugger.println("Expected Border Color is:" + expColor + ", but present: " + borderDetails);
                return false;
            }
            //Note: Not able to capture the message and validate the content. Will try and implement.
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage,errorMessageInPatientChoicePage: element not found." + exp);
            return false;
        }
    }

    public boolean selectPatientSignature() {
        try {
            Wait.forElementToBeDisplayed(driver, signaturePad);
            if (!SeleniumLib.drawSignature(signaturePad)) {
                Actions.scrollToTop(driver);
                if (!SeleniumLib.drawSignature(signaturePad)) {
                    Actions.scrollToBottom(driver);
                    return SeleniumLib.drawSignature(signaturePad);
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice Page: selectSignature: " + exp);
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

    public boolean selectMember(int i) {
        try {
            if (Wait.isElementDisplayed(driver, landingPageList, 30)) {
                if (memberEditButton.size() > i) {
                    Click.element(driver, memberEditButton.get(i));
                }
            }else{
                Debugger.println("Could not locate the Patient choice for member at location: "+i);
                SeleniumLib.takeAScreenShot("PatientChoiceEdit.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            try{
                seleniumLib.clickOnWebElement(memberEditButton.get(i));
                return true;
            }catch(Exception exp1){
                Debugger.println("Exception from selecting Patient choice to edit at " + i + ".:" + exp);
                SeleniumLib.takeAScreenShot("PatientChoiceEdit.jpg");
                return false;
            }

        }
    }

    public boolean selectPatientChoiceCategory() {
        try {
            if(!Wait.isElementDisplayed(driver,adultWithCapacityCategory,10)){
                Debugger.println("adultWithCapacityCategory not displayed.");
                SeleniumLib.takeAScreenShot("adultWithCapacityCategory.jpg");
            }
            Click.element(driver, adultWithCapacityCategory);
            return true;
        }catch(Exception exp){
            try {
                seleniumLib.clickOnWebElement(adultWithCapacityCategory);
                return true;
            }catch(Exception exp1){
                Debugger.println("Exception in adultWithCapacityCategory:"+exp);
                SeleniumLib.takeAScreenShot("adultWithCapacityCategory.jpg");
                return false;
            }
        }
    }

    public void selectTestType() {
        Click.element(driver, adultWithCapacityCategory);
    }

    public boolean enterRecordedByDetails() {
        try {

            Wait.forElementToBeDisplayed(driver, recordedByField);
            co.uk.gel.lib.Actions.fillInValue(recordedByField, "Sue");
            Click.element(driver, recordedByContinueButton);
            return true;
        }catch(Exception exp){
            try {
                seleniumLib.clickOnWebElement(recordedByContinueButton);
                return true;
            }catch(Exception exp1){
                Debugger.println("Exception in enterRecordedByDetails:" + exp1);
                SeleniumLib.takeAScreenShot("enterRecordedByDetails.jpg");
                return false;
            }
         }
    }

    public boolean selectChoicesWithPatientChoiceNotRequired() {
        try {
            Click.element(driver, patientChoiceNotRequiredForTheTest);
            Click.element(driver, patientChoicesContinueButton);
            return true;
        }catch(Exception exp){
            try {
                seleniumLib.clickOnWebElement(patientChoiceNotRequiredForTheTest);
                seleniumLib.clickOnWebElement(patientChoicesContinueButton);
                return true;
            }catch(Exception exp1){
                Debugger.println("Exception in selectChoicesWithPatientChoiceNotRequired:" + exp1);
                SeleniumLib.takeAScreenShot("selectChoicesWithPatientChoiceNotRequired.jpg");
                return false;
            }
        }
    }

    public void selectChoicesWithAgreeingTesting() {
        try {
            Click.element(driver, agreeTestChoice);
            Click.element(driver, agreeResearchParticipation);
            Click.element(driver, agreeSampleUsage);
            Click.element(driver, patientChoicesContinueButton);
        } catch (Exception exp) {
            Actions.scrollToTop(driver);
            Click.element(driver, agreeTestChoice);
            Click.element(driver, agreeResearchParticipation);
            Click.element(driver, agreeSampleUsage);
            Click.element(driver, patientChoicesContinueButton);
        }
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
        } catch (Exception exp) {
            Debugger.println("Exception from drawing Signature in Patient Choice Page." + exp);
        }
    }

    public boolean submitPatientChoiceWithSignature() {
        try {
            Wait.forElementToDisappear(driver, By.cssSelector("button[class*='disabled-submit-signature-button']"));
            Click.element(driver, submitSignatureButton);
            return true;
        } catch (Exception exp) {
            try{
                seleniumLib.clickOnWebElement(submitSignatureButton);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception from submitting Patient Choice with Signature...." + exp1);
                SeleniumLib.takeAScreenShot("submitPatientChoiceWithSignature.jpg");
                return false;
            }

        }
    }

    public boolean submitPatientChoiceWithoutSignature() {
        try {
            Click.element(driver, submitButton);
            return true;
        } catch (Exception exp) {
            try{
                seleniumLib.clickOnWebElement(submitButton);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception from submitting Patient Choice...." + exp1);
                SeleniumLib.takeAScreenShot("submitPatientChoiceWithoutSignature.jpg");
                return false;
            }
        }
    }

    public boolean statusUpdatedCorrectly(String status, int row) {
        Wait.forElementToBeDisplayed(driver, landingPageList, 100);
        return status.equalsIgnoreCase(statuses.get(row).getText());
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
            if (!Wait.isElementDisplayed(driver,backButtonOnAddPatientChoiceInformationPage,30)) {
                Debugger.println("Back button link not present in Patient Choice Page.");
                SeleniumLib.takeAScreenShot("PatientChoiceBackButtonPage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying backButtonOnPatientChoiceInformationPage:" + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceBackButtonPage.jpg");
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
        try {
            if (Wait.isElementDisplayed(driver, continueButton, 10)) {
                if (!continueButton.isEnabled()) {
                    Debugger.println("Add patient Choice Page:Recorded by:continue Button Not found");
                    return false;
                }
            } else if (Wait.isElementDisplayed(driver, formToFollow, 10)) {
                if (!formToFollow.isEnabled()) {
                    Debugger.println("Add patient Choice Page:Recorded by:Form to follow Button Not found");
                    SeleniumLib.takeAScreenShot("RecordByButton.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Add patient Choice Page:continue Button:" + exp);
            return false;
        }
    }

    public boolean clickOnSubmitPatientChoiceButton() {
        try {
            Wait.forElementToBeDisplayed(driver, submitPatientChoiceButton);
            seleniumLib.clickOnWebElement(submitPatientChoiceButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice: Clicking on submit patient choice button:" + exp);
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

    public boolean verifyQuestionTitle(String title) {
        try {
            String question = questionTitle.replaceAll("dummyQuestion", title);
            WebElement questionTitleElement = driver.findElement(By.xpath(question));
            if (!Wait.isElementDisplayed(driver, questionTitleElement, 60)) {
                Debugger.println("Could found Question:" + question + " in Patient Choice");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception:verifyQuestionTitle " + exp);
            SeleniumLib.takeAScreenShot("PatientChoices.jpg");
            return false;
        }
    }

    public boolean verifyTheChoiceOptionsForTheQuestion(String question, String choiceOption) {
        try {
            String optionsString = questionOptions.replaceAll("dummyQuestion", question);
            List<WebElement> options = driver.findElements(By.xpath(optionsString));
            boolean isFound = false;
            for (int i = 0; i < options.size(); i++) {
                if (options.get(i).getText().equalsIgnoreCase(choiceOption)) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                Debugger.println("Option: " + choiceOption + " not present for the question:" + question);
            }
            return isFound;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyThePatientChoiceOptionsForConsultee " + exp);
            return false;
        }
    }

    public boolean clickOnAmendPatientChoice() {
        Wait.forElementToBeDisplayed(driver, amendPatientChoice);
        try {
            Actions.clickElement(driver, amendPatientChoice);
            Wait.seconds(5);//Observed some delay here while running from jenkins
            return true;
        } catch (Exception exp) {
            Debugger.println("Patient Choice Page: click on amend patient choice: " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceAmendOption.jpg");
            return false;
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

    public boolean clearTheSignature() {
        try {
            Wait.forElementToBeClickable(driver, signatureClearButton);
            Actions.clickElement(driver, signatureClearButton);
            Wait.seconds(2);
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
            String replacedPath = consentIDPath.replaceAll("dummyID", consentID);
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

    public boolean waitForFormUpload(String expTitle) {
        //Logic has changed as tooltip coming for big files while uplaoding.Waiting to dissappear the tooltip.
        seleniumLib.moveAndClickOn(continueButton);
        if (seleniumLib.isElementPresent(waitForDocUpload)) {
            Wait.forElementToDisappear(driver, By.xpath("//span[@class='tooltiptext']"));
            if (seleniumLib.isElementPresent(waitForDocUpload)) {
                Wait.forElementToDisappear(driver, By.xpath("//span[@class='tooltiptext']"));
                if (seleniumLib.isElementPresent(waitForDocUpload)) {
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    public boolean verifyTheFormLibrarySection(String sectionName) {
        try {
            String formLinkPath = formSection.replaceAll("dummySection", sectionName);
            WebElement formLinkElement = driver.findElement(By.xpath(formLinkPath));
            if (!Wait.isElementDisplayed(driver,formLinkElement,10)) {
                Debugger.println("Section:" + sectionName + " Not present under Form Library in Patient Choice");
                SeleniumLib.takeAScreenShot("formLibrarySection.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            //Trying with SeleniumLib click which handles javascript click also
            try {
                String formLinkPath = formSection.replaceAll("dummySection", sectionName);
                if(!seleniumLib.isElementPresent(By.xpath(formLinkPath))) {
                    Debugger.println("Section:" + sectionName + " Not present under Form Library in Patient Choice");
                    SeleniumLib.takeAScreenShot("formLibrarySection.jpg");
                    return false;
                }
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception from verifyTheFormLibrarySection:" + exp1);
                SeleniumLib.takeAScreenShot("formLibrarySection.jpg");
                return false;
            }
        }
    }

    public boolean verifyTheSupportingInformationLink(String formSection, String linkForm) {
        try {
            boolean isPresent = false;
            String linkForms = formLinks.replaceAll("dummySection", formSection);
            List<WebElement> supportingInformationLinks = driver.findElements(By.xpath(linkForms));
            for (int i = 0; i < supportingInformationLinks.size(); i++) {
                if (supportingInformationLinks.get(i).getText().equalsIgnoreCase(linkForm)) {
                    //Click on the link, Using seleniumLib click as the direct click sometimes gives some element not clickable error
                    seleniumLib.clickOnWebElement(supportingInformationLinks.get(i));
                    Wait.seconds(3);//Wait for three second to Load the form.
                    if (formSubHeader.getText().equalsIgnoreCase(linkForm)) {
                        isPresent = true;
                    }
                    Actions.scrollToTop(driver);
                    seleniumLib.clickOnWebElement(formLiraryBackButton);
                    Wait.seconds(3);//Wait for three second to navigate back to previous page.
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Form Link: " + linkForm + " not present under the section: " + formSection);
                SeleniumLib.takeAScreenShot("formLinkNotPresent.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheSupportingInformationLink:" + exp);
            SeleniumLib.takeAScreenShot("formLinkNotPresent.jpg");
            return false;
        }
    }

    public boolean verifyMandatoryFieldDisplaySymbolInPatientChoice(String fieldName, String fieldType, String symbol, String symbolColor) {
        try {
            String fieldPath = mandatoryFieldSymbol.replaceAll("dummyFieldType", fieldType);
            fieldPath = fieldPath.replaceAll("dummyLabel", fieldName);
            WebElement mandatoryField = driver.findElement(By.xpath(fieldPath));
            String actSymbol = mandatoryField.getText();
            String actColor = mandatoryField.getCssValue("color");
            String expColor = StylesUtils.convertFontColourStringToCSSProperty(symbolColor);
            if (symbol.equalsIgnoreCase(actSymbol)) {
                if (expColor.equalsIgnoreCase(actColor)) {
                    return true;
                }
            }
            Debugger.println("Filed: " + fieldName + " not displayed as mandatory field.Actual Symbol:" + actSymbol + ",EXP:" + symbol + ",Actual Color:" + actColor + ",EXP:" + expColor);
            SeleniumLib.takeAScreenShot("MandatoryFieldError.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception in validating Mandatory fields: " + exp);
            SeleniumLib.takeAScreenShot("MandatoryFieldError.jpg");
            return false;
        }
    }

    public boolean verifyOptionalFieldPresence(String fieldName) {
        try {
            String fieldPath = optionalFieldNames.replaceAll("dummyLabel", fieldName);
            WebElement optionalField = driver.findElement(By.xpath(fieldPath));
            if (!Wait.isElementDisplayed(driver, optionalField, 10)) {
                Debugger.println("Field/Section: " + fieldName + " not displayed as optional field.");
                SeleniumLib.takeAScreenShot("OptionalFieldMissing.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in validating Optional fields: " + exp);
            SeleniumLib.takeAScreenShot("OptionalFieldMissing.jpg");
            return false;
        }
    }

    public boolean verifyInvalidFileUploadMessages(String fileName, String expMessage) {
        try {
            if (!uploadRecordTypeDocument("", fileName)) {
                Debugger.println("File could not upload..");
                SeleniumLib.takeAScreenShot("FileNotUploaded.jpg");
                return false;
            }
            //Wait for 5 seconds to get the error message
            if (!Wait.isElementDisplayed(driver, filUploadErrorMsg, 30)) {
                //Error message not yet displayed...
                Debugger.println("Error Message for Unsupported file type not displayed");
                SeleniumLib.takeAScreenShot("UnSupportFileError.jpg");
                return false;
            }
            String actMessage = filUploadErrorMsg.getText();
            //Read the message.. and compare with what we pass
            if (!actMessage.contains(expMessage)) {
                Debugger.println("Expected Error Message: " + expMessage + ", But Actual is:" + actMessage);
                SeleniumLib.takeAScreenShot("UnSupportFileErrorMessage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyInvalidFileUploadMessages: " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceDocUpload.jpg");
            return false;
        }
    }

    public boolean verifyFileTypeDropdownValues(List<String> expectedOptions) {
        try {
            Wait.forElementToBeDisplayed(driver, fileTypeDropDown, 30);
            String[] expValues = expectedOptions.toArray(new String[0]);
            Actions.clickElement(driver, fileTypeDropDown);
            Wait.seconds(2);
            boolean isPresent = false;
            //Check the size of the drop down fields - to ensure no extra values present
            if ((expectedOptions.size()) != dropDownValues.size()) {
                Debugger.println("Expected " + expectedOptions.size() + " drop down values, but present " + dropDownValues.size());
                SeleniumLib.takeAScreenShot("PCDropdownMismatch.jpg");
                return false;
            }
            for (int i = 0; i < expValues.length; i++) {//For each expected value
                isPresent = false;
                for (int j = 0; j < dropDownValues.size(); j++) {//For the actual dropdown values
                    if (expValues[i].equalsIgnoreCase(dropDownValues.get(j).getText())) {
                        isPresent = true;
                        break;//inner loop
                    }
                }//for actual
                if (!isPresent) {
                    Debugger.println("Expected drop down value:" + expValues[i] + " not present in File type dropdown in RecordType.");
                    SeleniumLib.takeAScreenShot("FileTypeDD.jpg");
                }
                Actions.clickElement(driver, fileTypeDropDown);//Click again to collapse
            }//for expValues
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying file type dropdown options  : " + exp);
            SeleniumLib.takeAScreenShot("FileTypeDD.jpg");
            return false;
        }
    }

    public boolean dateOfSignatureStatusInRecordedBYSection() {
        try {
            Wait.forElementToBeDisplayed(driver, uploadDay);
            if (uploadDay.isEnabled() && uploadMonth.isEnabled() && uploadYear.isEnabled()) {
                return true;
            }
            SeleniumLib.takeAScreenShot("PCDateofSignatureStatus.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Date of Signature fields not found. " + exp);
            SeleniumLib.takeAScreenShot("PCDateofSignatureStatus.jpg");
            return false;
        }
    }

    public boolean selectUploadFormType(String dropdownValue) {
        try {
            if (!Wait.isElementDisplayed(driver, fileTypeDropDown, 30)) {
                Debugger.println("Could not locate fileTypeDropdown in PC.");
                SeleniumLib.takeAScreenShot("PCFileTypeDropdown.jpg");
                return false;
            }
            Actions.clickElement(driver, fileTypeDropDown);
            Wait.seconds(2);
            boolean isSelected = false;
            for (int j = 0; j < dropDownValues.size(); j++) {//For the actual dropdown values
                if (dropdownValue.equalsIgnoreCase(dropDownValues.get(j).getText())) {
                    dropDownValues.get(j).click();
                    isSelected = true;
                    break;
                }
            }
            if (!isSelected) {
                Debugger.println("Could not select the dropdown value:" + dropdownValue + " from PC Filetype drop down.");
                SeleniumLib.takeAScreenShot("PCRecordedByDropDown.jpg");
            }
            return isSelected;
        } catch (Exception exp) {
            Debugger.println("Exception from selecting dropdown in recorded by" + exp);
            SeleniumLib.takeAScreenShot("PCRecordedByDropDown.jpg");
            return false;
        }
    }

    public boolean fillTheDateOfSignatureInRecordedBy() {
        try {
            Wait.forElementToBeDisplayed(driver, uploadDay, 5);
            String today[] = TestUtils.getCurrentDay();
            uploadDay.sendKeys(today[0]);
            uploadMonth.sendKeys(today[1]);
            uploadYear.sendKeys(today[2]);
            uploadDay.sendKeys(today[0]);//Purposefully entering again to ensure the continue button enabled
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: fillTheDateOfSignatureInRecordedBy: " + exp);
            SeleniumLib.takeAScreenShot("PCDateofSignatureFilling.jpg");
            return false;
        }
    }

    public boolean verifyFormUploadSuccessMessage(String expMessage) {
        try {
            Wait.forElementToBeDisplayed(driver, fileUploadSuccessMsg);
            String actualMessage = fileUploadSuccessMsg.getText();
            if (!expMessage.equalsIgnoreCase(actualMessage)) {
                Debugger.println("Upload success message:" + expMessage + " not present.");
                SeleniumLib.takeAScreenShot("PCFormUploadSuccessMsg.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Verifying verifyFormUploadSuccessMessage:" + exp);
            SeleniumLib.takeAScreenShot("PCFormUploadSuccessMsg.jpg");
            return false;
        }
    }

    public boolean verifyUploadMessage(String message) {
        try {
            Wait.forElementToBeDisplayed(driver, uploadMessage);
            if (!message.equalsIgnoreCase(uploadMessage.getText())) {
                SeleniumLib.takeAScreenShot("PCFormUploadMsg.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage, verifyUploadMessage - message not found in upload section." + exp);
            SeleniumLib.takeAScreenShot("PCFormUploadMsg.jpg");
            return false;
        }
    }

    public boolean clickOnPatientChoiceStatusLink(int index) {
        try {
            int noOfStatus = patientChoiceStatus.size();
            if (noOfStatus < index) {
                Debugger.println("Patient choice status not displayed for member at " + index + " position");
                SeleniumLib.takeAScreenShot("PatientChoiceStatus.jpg");
                return false;
            }
            Wait.forElementToBeDisplayed(driver, patientChoiceStatus.get(index), 30);
            Actions.clickElement(driver, patientChoiceStatus.get(index));
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in clicking patient choice status link" + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceStatus.jpg");
            return false;
        }
    }

    public void waitUntilTokenExpire(int timeToWait) {
        Wait.seconds(timeToWait);
    }

    public boolean verifyFamilyMemberDetailsPatientChoicePage(NGISPatientModel familyMember) {
        try {
            boolean isPresent = false;
            List<WebElement> nameList = seleniumLib.getElements(By.xpath(firstNameLastName));
            for (int i = 0; i < nameList.size(); i++) {
                if (nameList.get(i).getText().contains(familyMember.getLAST_NAME() + ", " + familyMember.getFIRST_NAME())) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Family Member Name: " + familyMember.getLAST_NAME() + "," + familyMember.getFIRST_NAME() + " not present in Patient Choice Landing Page.");
                SeleniumLib.takeAScreenShot("PCLandingPage.jpg");
                return isPresent;
            }

            isPresent = false;
            for (int i = 0; i < ngsIdValues.size(); i++) {
                if (ngsIdValues.get(i).getText().contains(familyMember.getNGIS_ID())) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("NGISID: " + familyMember.getNGIS_ID() + " not present in Patient Choice Landing Page.");
                SeleniumLib.takeAScreenShot("PCLandingPage.jpg");
                return isPresent;
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception in Verifying FamilyMember details in Patient choice landing Page.");
            SeleniumLib.takeAScreenShot("PCLandingPage.jpg");
            return false;
        }
    }

    public boolean verifyUploadedFileName(String fileName) {
        try {
            if (fileName == null || fileName.isEmpty()) {//Not expecting any uploaded file presence
                //After removing all uploaded file, verifying non-presence
                if (!Wait.isElementDisplayed(driver, uploadedFileName, 5)) {
                    return true;
                } else {
                    Debugger.println("Not expected any uplaoded files, but still present.");
                    SeleniumLib.takeAScreenShot("UploadedFilePresence.jpg");
                    return false;
                }
            }
            Wait.forElementToBeDisplayed(driver, uploadedFileName);
            if (!uploadedFileName.isDisplayed()) {
                Debugger.println("The uploaded file name " + fileName + " is not displayed. Pls check UploadedFileName.jpg.");
                SeleniumLib.takeAScreenShot("UploadedFileName.jpg");
                return false;
            }
            if (!uploadedFileName.getText().equalsIgnoreCase(fileName)) {
                Debugger.println("The uploaded file name expected." + fileName + " Actual:" + uploadedFileName.getText() + ".Pls check UploadedFileName.jpg.");
                SeleniumLib.takeAScreenShot("UploadedFileName.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoice page:uploadedfileNameIsDisplayedOnThePage :exception found " + exp);
            SeleniumLib.takeAScreenShot("uploadedFileName.jpg");
            return false;
        }

    }

    public boolean verifyThePageSectionTitleInPatientChoicePage(String expTitle) {
        By pageTitle = By.xpath("//h2[contains(text(),'" + expTitle + "')]");
        if (!seleniumLib.isElementPresent(pageTitle)) {
            Wait.forElementToBeDisplayed(driver, driver.findElement(pageTitle));
            if (!seleniumLib.isElementPresent(pageTitle)) {
                Debugger.println("Expected title :" + expTitle + " not loaded in the page.");
                return false;
            }
        }
        return true;
    }

    public boolean verifyReferralIdOnHistoryTabIsSameAsOnReferralIdOnReferralBar() {
        try {
            Wait.forElementToBeDisplayed(driver, referalIdOnHistoryTab);
            Wait.forElementToBeDisplayed(driver, referralIdOnReferralBar);
            //As observed it is taking 3 secs to load the referral Id on history tab
            Wait.seconds(3);
            if (!referralIdOnReferralBar.getText().contains(referalIdOnHistoryTab.getText())) {
                Debugger.println("The referral id on history tab and referral bar are different");
                SeleniumLib.takeAScreenShot("PCReferralIdValidation.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage : verifyReferralIdOnHistoryTabIsSameAsOnReferralIdOnReferralBar : exception found" + exp);
            SeleniumLib.takeAScreenShot("verifyReferralIds.jpg");
            return false;
        }
    }

    public boolean verifyFormToFollowButtonStatus(String expectedColor) {
        try {
            Wait.forElementToBeDisplayed(driver, formToFollow);
            String expectedBackground = StylesUtils.convertFontColourStringToCSSProperty(expectedColor);
            String actualColor = formToFollow.getCssValue("background-color");
            if (!actualColor.equalsIgnoreCase(expectedBackground)) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying formToFollow button Status:" + exp);
            return false;
        }
    }

    public boolean verifyContinueButtonStatus(String expectedColor) {
        try {
            Wait.forElementToBeDisplayed(driver, continueButton);
            String expectedBackground = StylesUtils.convertFontColourStringToCSSProperty(expectedColor);
            String actualColor = continueButton.getCssValue("background-color");
            if (!actualColor.equalsIgnoreCase(expectedBackground)) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying formToFollow button Status:" + exp);
            return false;
        }
    }

    public void clickOnFormToFollow() {
        try {
            if (Wait.isElementDisplayed(driver, formToFollow, 10)) {
                Actions.clickElement(driver, formToFollow);
            } else if (Wait.isElementDisplayed(driver, formToFollow, 10)) {
                Actions.clickElement(driver, formToFollow);
            }
        } catch (Exception exp) {
            Debugger.println("Exception in clicking on FormToFollow Button in PC:" + exp);
            SeleniumLib.takeAScreenShot("PCFormToFollowButton.jpg");
        }
    }

    public void clickOnCancelUpload() {
        try {
            if (Wait.isElementDisplayed(driver, cancelUpload, 10)) {
                Actions.clickElement(driver, cancelUpload);
            } else if (Wait.isElementDisplayed(driver, cancelUpload, 10)) {
                Actions.clickElement(driver, cancelUpload);
            }
        } catch (Exception exp) {
            Debugger.println("Exception in clicking on CancelUpload Button in PC:" + exp);
            SeleniumLib.takeAScreenShot("PCCancelUploadButton.jpg");
        }
    }

    //Added this method to complete default PC for each category with agreeing test - used in user journeys
    public boolean completePatientChoiceWithAgreeingTestForFamilyMember(String familyMember, String category, String recordBy) {
        boolean testResult = false;
        try {
            //Category
            testResult = selectPatientChoiceCategory(category);
            if (!testResult) {
                return testResult;
            }
            //TestType
            testResult = selectTestType("Rare & inherited diseases – WGS");
            if (!testResult) {
                return testResult;
            }
            //RecordBy
            testResult = fillRecordedByDetails(familyMember, recordBy);
            if (!testResult) {
                return testResult;
            }
            Wait.seconds(3);
            clickOnContinue();
            Wait.seconds(3);
            verifyTheSectionTitle("Patient choice");
            Actions.clickElement(driver, agreeTestChoice);
            Actions.clickElement(driver, agreeResearchParticipation);
            Actions.clickElement(driver, agreeSampleUsage);
            Wait.seconds(5);
            clickOnContinue();
            Wait.seconds(2);
            if (category.equalsIgnoreCase("Child")) {
                verifyTheSectionTitle("Child assent");
                Actions.scrollToTop(driver);
                Actions.clickElement(driver, childAssentYes);
                Wait.seconds(2);
                clickOnContinue();
                Wait.seconds(2);
            } else if (category.equalsIgnoreCase("Adult (Without Capacity)")) {
                verifyTheSectionTitle("Consultee attestation");
                Actions.scrollToTop(driver);
                Actions.clickElement(driver, consulteeReadYes);
                Actions.clickElement(driver, consulteeConsultedYes);
                Actions.clickElement(driver, consulteeAgreedYes);
                Wait.seconds(2);
                clickOnContinue();
                Wait.seconds(2);
            }
            submitPatientChoice();
            return testResult;

        } catch (Exception exp) {
            Debugger.println("Exception from Submitting Patient Choice for :" + familyMember + "\n" + exp);
            SeleniumLib.takeAScreenShot("PCSubmissionError.jpg");
            return false;
        }

    }

}//end
