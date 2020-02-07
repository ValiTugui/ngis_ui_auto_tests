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

    @FindBy(xpath = "//button[contains(text(),'Upload document')]")
    public WebElement uploadDocumentOption;

    @FindBy(xpath = "//label[contains(@class,'upload-document-button')]")
    public WebElement uploadDocumentButton;

    @FindBy(xpath = "//div[@class='m-signature-pad--body']//canvas")
    WebElement signaturePad;
    @FindBy(xpath = "//*[contains(@id,'signature-pad')]//child::canvas")
    public WebElement signatureSection;

    @FindBy(xpath = "//span[contains(text(),'Patient choice status')]/following-sibling::span[contains(@class,'css-')]")
    WebElement patientChoiceStatus;

    String patientChoiceCategory = "//label[contains(@class,'radio-container')][text()='dummyCategory']";
    String testType = "//label[contains(@class,'radio-container')][text()='dummyTestType']";
    String childAssent = "//label[contains(@class,'radio-container')][contains(text(),'dummyAssent')]";

    //For PatientInformation Identifiers
    String patientList = "//div[contains(@class,'styles_participant-list_')]/div[contains(@class,'css')]";
    String firstNameLastName = "//div[contains(@class,'styles_participant-list_')]//span[contains(@class,'css-')]//h2";
    String probandBeingTested = "//div[contains(@class,'styles_participant-list_')]//span[contains(@class,'child-element')]";
    String bornInformation = "//span[contains(@id,'dateOfBirth')]";
    String genderInformation = "//span[contains(@id,'gender')]";
    String ngsIdInformation = "//span[contains(@id,'ngisId')]";
    String patientChoiceInformation = "//span[contains(@id,'patientChoiceStatus')]";
    String editButtonInformation = "//button[@aria-label='edit button']";

    String specificPatientChoiceEdit = "//ul//span[text()='NHSLastFour']/ancestor::div[contains(@class,'css-1')]//button";
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

    @FindBy(xpath = "//label[@id='Choices_Q1.0-1']")
    public WebElement discussionFormNotAvailable;

    @FindBy(xpath = "//label[@id='Choices_Q1.0-3']")
    public WebElement patientChoiceNotRequiredForTheTest;

    @FindBy(id = "Choices_Q2.3-0")
    public WebElement agreeResearchParticipation;

    @FindBy(id = "Choices_Q2.3.1-0")
    public WebElement agreeSampleUsage;

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

    String linkText = "//div[contains(@class,'row quicklinks-subnav')]//child::a[contains(text(),'dummyLinkText')]";

    @FindBy(xpath = "//button[@class='btn gel-btn-blue']")
    WebElement amendPatientChoice;

    @FindBy(xpath = "//div[contains(@class,'quicklinks-subnav')]")
    WebElement rowOfLinks;

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

    String formSection ="//div[@class='form-section-container']/child::h4[text()='dummySection']";
    String formLinks ="//div[@class='form-section-container']/child::h4[text()='dummySection']/..//a";
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

    public boolean verifySelectedTabInPatientChoice(String tabSectionTitle){
        String selectedSubtitle = selectedTabTitle.replaceAll("dummySubtitle",tabSectionTitle);
        try {
            WebElement subTitleElement = driver.findElement(By.xpath(selectedSubtitle));
            Wait.forElementToBeDisplayed(driver,subTitleElement,100);
            if(!Wait.isElementDisplayed(driver,subTitleElement,30)){
                Debugger.println("Expected subtitle:"+tabSectionTitle+" not present in Patient choice. Pls check PCSubtitle.jpg");
                SeleniumLib.takeAScreenShot("PCSubtitle.jpg");
                return false;
            }
            return true;
        }catch(Exception exp){
            Wait.seconds(10);//This is introduced based on the screenshot error. This wait will apply only on exceptional cases
            WebElement subTitleElement = driver.findElement(By.xpath(selectedSubtitle));
            if(Wait.isElementDisplayed(driver,subTitleElement,10)){
                return true;
            }
            Debugger.println("Exception in verifying the selected tab section in PatientChoice."+exp);
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
        }catch (Exception exp) {
            Debugger.println("Exception from Selecting PatientChoiceCategory:" + exp);
            SeleniumLib.takeAScreenShot("patientChoiceCategory.jpg");
            return false;
        }
    }
    public boolean selectOptionInSection(String optionName,String sectionName) {
        try {
            if (optionName == null || optionName.isEmpty()) {//Not selecting any option
                return true;
            }
            String options = sectionOptions.replaceAll("dummySection",sectionName);
            List<WebElement> optionsList = driver.findElements(By.xpath(options));
            if(optionsList == null || optionsList.size() == 0){
                Debugger.println("Could not find any options under the section :"+sectionName);
                return false;
            }
            boolean isFound = false;
            for(int i=0; i<optionsList.size(); i++){
               if(optionsList.get(i).getText().equalsIgnoreCase(optionName)){
                   isFound = true;
                   Actions.clickElement(driver,optionsList.get(i));
                   break;
               }
            }
            if(!isFound){
                Debugger.println("Option :"+optionName+" could not found under the section :"+sectionName);
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
        try {
            Wait.forElementToBeDisplayed(driver,continueButton,10);
            Actions.clickElement(driver,continueButton);
        }catch(Exception exp){
            try {
                //Continue button in Recorded by section has changed to FormToFolow
                Wait.forElementToBeDisplayed(driver, formToFollow, 10);
                Actions.clickElement(driver, formToFollow);
            }catch(Exception exp1) {
                Debugger.println("Exception in clicking on Continue Button in PC:" + exp1);
                SeleniumLib.takeAScreenShot("PCContinueButton.jpg");
            }
        }
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
            clickOnContinue();
            Wait.seconds(2);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in uploading record type in Patient choice: " + exp);
            SeleniumLib.takeAScreenShot("recordTypeUpload.jpg");
            return false;
        }
    }
    //This is used for End to end RD user journey
    public boolean selectDefaultPatientChoices(){
        try {
            Click.element(driver, agreeTestChoice);
            Click.element(driver, agreeResearchParticipation);
            Click.element(driver, agreeSampleUsage);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from Setting default Patient Choice: "+exp);
            SeleniumLib.takeAScreenShot("DefaultPatientChoice.jpg");
            return false;
        }
    }

    public boolean selectOptionForQuestion(String option,String question) {
        try {
            Actions.scrollToTop(driver);
            String optionsString = questionOptions.replaceAll("dummyQuestion",question);
            List<WebElement> options = driver.findElements(By.xpath(optionsString));
            boolean isFound = false;
            for (int i = 0; i < options.size(); i++) {
                if(options.get(i).getText().equalsIgnoreCase(option)){
                    options.get(i).click();
                    isFound = true;
                    break;
                }
            }
            if(!isFound) {
                Debugger.println("Option: " + option + " not present for the question:"+question);
            }
            return isFound;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyTheQuestionOptions " + exp);
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
            //Debugger.println("Validating Information of " + noOfPatients + " Patients in Patient Choice Page.");
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
            if (!Wait.isElementDisplayed(driver,selectedOptionResult,30)) {
                Debugger.println("Element before Edit button not found for " + expectedResult);
                return false;
            }
            if (!selectedOptionResult.getText().contains(expectedResult)) {
                Debugger.println("Title before edit button is not matching: pls check PCOptionTitle.jpg");
                SeleniumLib.takeAScreenShot("PCOptionTitle.jpg");
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from validating title of PC option:"+exp);
            SeleniumLib.takeAScreenShot("PCOptionTitle.jpg");
            return false;
        }
    }

    public boolean verifyEditButton(String category) {
       try {
           String editButtonField = editButton.replaceAll("dummyOption", category);
           WebElement editButtonResult = driver.findElement(By.xpath(editButtonField));
           if (!Wait.isElementDisplayed(driver,editButtonResult,30)) {
               Debugger.println("Edit option not present for section:"+category+". Pls check EditOptionNotPresent.jpg");
               SeleniumLib.takeAScreenShot("EditOptionNotPresent.jpg");
               return false;
           }
           return true;
       }catch(Exception exp){
           Debugger.println("Exception in checking the Edit option for:"+category+":"+exp);
           SeleniumLib.takeAScreenShot("EditOptionNotPresent.jpg");
           return false;
       }
    }

    public boolean verifyTheSectionTitle(String sectionName) {
        try {
            if(Actions.isAlertPresent(driver)){
                Actions.dismissAlert(driver);
            }
            String section = sectionTitle.replaceAll("dummySection", sectionName);
            return seleniumLib.isElementPresent(By.xpath(section));
         }catch(Exception exp) {
            Debugger.println("Section :"+sectionName+" not present.");
            return false;
        }
    }
    public boolean optionIsCompleted(String option) {
        WebElement webElementLocator = null;
        try {
            Wait.forElementToBeDisplayed(driver, stepsList);
            String elementLocator = optionIsList.replace("dummyOption", option);
            webElementLocator = driver.findElement(By.xpath(elementLocator));
            if(!Wait.isElementDisplayed(driver,webElementLocator,60)){
                Debugger.println("Option "+option+" is not marked as completed as expected.");
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
            }catch(Exception exp1) {
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
            for (int i = 0; i < warningMessage.size(); i++) {
                if (message.equalsIgnoreCase(warningMessage.get(i).getText())) {
                    return true;
                }
            }
            SeleniumLib.takeAScreenShot("NoWarningMessage.jpg");
            Actions.scrollToTop(driver);
            SeleniumLib.takeAScreenShot("NoWarningMessage1.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage, patientChoiceInformationWarningMessage - warning message box not found. " + exp);
            return false;
        }
    }

    public boolean notHighlightedContinueButton(String expectedColor) {
        try {
            Wait.forElementToBeDisplayed(driver, continueButton);
            String expectedBackground = StylesUtils.convertFontColourStringToCSSProperty(expectedColor);
            String actualColor = continueButton.getCssValue("background-color");
            if (!actualColor.equalsIgnoreCase(expectedBackground)) {
                Debugger.println("Actual background color : " + actualColor + ", Expected :"+expectedBackground);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Continue Button not found. " + exp);
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
            Debugger.println("Continue Button not found. " + exp);
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

    public boolean verifySelectedChoicesInSection(String sectionName,String selectedChoice) {
        try {
            boolean isPresent = false;
            String[] choices = selectedChoice.split("::");
            String actualText = "";
            String selectedAnswers = selectedChoices.replaceAll("dummySection",sectionName);
            List<WebElement> answersList = driver.findElements(By.xpath(selectedAnswers));
            for (int i = 0; i < answersList.size(); i++) {
                actualText = answersList.get(i).getText();
                if(actualText.startsWith(choices[0].trim())) {
                    if (actualText.endsWith(choices[1].trim())) {
                        isPresent = true;
                        break;
                    }else{
                        Debugger.println("Expected Answer: "+choices[1]+", But Actual:"+actualText);
                    }
                }
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying selected choices in section:"+sectionName+"\n" + exp);
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

    public boolean errorMessageInPatientChoicePage(String boxColor,String message) {
        try {
            Actions.scrollToTop(driver);
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

    public boolean selectPatientSignature() {
        try {
            Wait.forElementToBeDisplayed(driver, signaturePad);
            SeleniumLib.drawSignature(signaturePad);
            return true;
        } catch (Exception exp) {
            try {
                Actions.scrollToBottom(driver);
                SeleniumLib.drawSignature(signaturePad);
                return true;
            }catch(Exception exp1) {
                try {
                    Actions.scrollToTop(driver);
                    SeleniumLib.drawSignature(signaturePad);
                    return true;
                }catch(Exception exp2) {
                    Debugger.println("Patient Choice Page: selectSignature: " + exp);
                    SeleniumLib.takeAScreenShot("PatientChoicePageSignature.jpg");
                    return false;
                }
            }
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

   public void selectMember(int i) {
        try {
            Wait.forElementToBeDisplayed(driver, landingPageList);
            Click.element(driver, memberEditButton.get(i));
        }catch(Exception exp){
            Debugger.println("Exception from selecting Patient choice to edit at "+i+".:"+exp);
            SeleniumLib.takeAScreenShot("PatientChoiceEdit.jpg");
        }
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
        Click.element(driver, patientChoiceNotRequiredForTheTest);
        Click.element(driver, patientChoicesContinueButton);
    }

    public void selectChoicesWithAgreeingTesting() {
        try {
            Click.element(driver, agreeTestChoice);
            Click.element(driver, agreeResearchParticipation);
            Click.element(driver, agreeSampleUsage);
            Click.element(driver, patientChoicesContinueButton);
        }catch(Exception exp){
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
        }catch(Exception exp){
            Debugger.println("Exception from drawing Signature in Patient Choice Page."+exp);
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
        try {
            if(Wait.isElementDisplayed(driver, continueButton,10)) {
                if (!continueButton.isEnabled()) {
                    Debugger.println("Add patient Choice Page:Recorded by:continue Button Not found");
                    return false;
                }
            }else if(Wait.isElementDisplayed(driver,formToFollow,10)){
                if(!formToFollow.isEnabled()){
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
            String question = questionTitle.replaceAll("dummyQuestion",title);
            WebElement questionTitleElement = driver.findElement(By.xpath(question));
            if(!Wait.isElementDisplayed(driver,questionTitleElement,60)){
                Debugger.println("Could found Question:" + question+" in Patient Choice");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception:verifyQuestionTitle " + exp);
            SeleniumLib.takeAScreenShot("PatientChoices.jpg");
            return false;
        }
    }

    public boolean verifyTheChoiceOptionsForTheQuestion(String question,String choiceOption) {
        try {
            String optionsString = questionOptions.replaceAll("dummyQuestion",question);
            List<WebElement> options = driver.findElements(By.xpath(optionsString));
            boolean isFound = false;
            for (int i = 0; i < options.size(); i++) {
                if(options.get(i).getText().equalsIgnoreCase(choiceOption)){
                    isFound = true;
                    break;
                }
            }
            if(!isFound) {
                Debugger.println("Option: " + choiceOption + " not present for the question:"+question);
            }
            return isFound;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage: verifyThePatientChoiceOptionsForConsultee " + exp);
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
            Actions.clickElement(driver,amendPatientChoice);
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

    public boolean waitForFormUpload(String expTitle) {
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
   public boolean verifyTheFormLibrarySection(String sectionName){
        try {
            String formLinkPath = formSection.replaceAll("dummySection", sectionName);
            WebElement formLinkElement = driver.findElement(By.xpath(formLinkPath));
            if(!formLinkElement.isDisplayed()){
                Debugger.println("Section:"+ sectionName+" Not present under Form Library in Patient Choice");
                SeleniumLib.takeAScreenShot("formLibrarySection.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheFormLibrarySection:" + exp);
            SeleniumLib.takeAScreenShot("formLibrarySection.jpg");
            return false;
        }
    }
    public boolean verifyTheSupportingInformationLink(String formSection,String linkForm) {
        try {
            boolean isPresent = false;
            String linkForms = formLinks.replaceAll("dummySection",formSection);
            List<WebElement> supportingInformationLinks = driver.findElements(By.xpath(linkForms));
            for (int i = 0; i < supportingInformationLinks.size(); i++) {
                if(supportingInformationLinks.get(i).getText().equalsIgnoreCase(linkForm)){
                    //Click on the link, Using seleniumLib click as the direct click sometimes gives some element not clickable error
                    seleniumLib.clickOnWebElement(supportingInformationLinks.get(i));
                    Wait.seconds(3);//Wait for three second to Load the form.
                    if(formSubHeader.getText().equalsIgnoreCase(linkForm)){
                        isPresent = true;
                    }
                    Actions.scrollToTop(driver);
                    formLiraryBackButton.click();
                    Wait.seconds(3);//Wait for three second to navigate back to previous page.
                    break;
                }
            }
            if(!isPresent){
                Debugger.println("Form Link: "+linkForm+" not present under the section: "+formSection);
                SeleniumLib.takeAScreenShot("formLinkNotPresent.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheSupportingInformationLink:" + exp);
            SeleniumLib.takeAScreenShot("formLinkNotPresent.jpg");
            return false;
        }
    }
    public boolean verifyMandatoryFieldDisplaySymbolInPatientChoice(String fieldName,String fieldType,String symbol,String symbolColor){
        try{
            String fieldPath = mandatoryFieldSymbol.replaceAll("dummyFieldType",fieldType);
            fieldPath = fieldPath.replaceAll("dummyLabel",fieldName);
            WebElement mandatoryField = driver.findElement(By.xpath(fieldPath));
            String actSymbol = mandatoryField.getText();
            String actColor = mandatoryField.getCssValue("color");
            String expColor = StylesUtils.convertFontColourStringToCSSProperty(symbolColor);
            if(symbol.equalsIgnoreCase(actSymbol)){
                if(expColor.equalsIgnoreCase(actColor)){
                    return true;
                }
            }
            Debugger.println("Filed: "+fieldName+" not displayed as mandatory field.Actual Symbol:"+actSymbol+",EXP:"+symbol+",Actual Color:"+actColor+",EXP:"+expColor);
            SeleniumLib.takeAScreenShot("MandatoryFieldError.jpg");
            return false;
        }catch(Exception exp){
            Debugger.println("Exception in validating Mandatory fields: "+exp);
            SeleniumLib.takeAScreenShot("MandatoryFieldError.jpg");
            return false;
        }
    }
}//end
