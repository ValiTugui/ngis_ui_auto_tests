package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.security.ssl.Debug;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PatientChoicePage {

    WebDriver driver;
    SeleniumLib seleniumLib;

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

    @FindBy(xpath = "//button[contains(text(),'Upload document')]")
    public WebElement uploadDocumentOption;

    @FindBy(xpath = "//label[contains(@class,'upload-document-button')]")
    public WebElement uploadDocumentButton;


    @FindBy(xpath = "//div[@class='m-signature-pad--body']//canvas")
    WebElement signaturePad;

    @FindBy(xpath = "//label[contains(text(),'Parent/Guardian first name')]/following-sibling::input")
    WebElement parentFirstName;

    @FindBy(xpath = "//label[contains(text(),'Parent/Guardian last name')]/following-sibling::input")
    WebElement parentLastName;

    @FindBy(xpath = "//button[@class='btn submit-signature-button']")
    WebElement submitPatientChoice;

    @FindBy(xpath = "//span[@class='css-1ksowpi'][text()='Patient choice status']/../span[@class='css-1a6lz9d']/span")
    WebElement patientChoiceStatus;

    @FindBy(xpath = "//h5[contains(text(),'Has research participation been discussed')]/following::label[contains(text(),'Yes')]")
    WebElement patientChoiceOption1;

    @FindBy(xpath = "//h5[contains(text(),'used for research, separate to NHS care')]/following::label[contains(text(),'Yes')]")
    WebElement patientChoiceOption2;

    @FindBy(xpath = "//*[@id='route-wrapper']//div[contains(text(),'Parent/Guardian signature')]")
    WebElement sectionTitle_ParentGuardianSignature;

    String patientChoiceCategory = "//label[contains(@class,'radio-container')][text()='dummyCategory']";
    String testType = "//label[contains(@class,'radio-container')][text()='dummyTestType']";
    String patientChoice = "//label[contains(@class,'radio-container')][contains(text(),'dummyChoice')]";
    String childAssent = "//label[contains(@class,'radio-container')][contains(text(),'dummyAssent')]";

    //For PatientInformation Identifiers
    String patientList ="//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']";
    String firstNameLastName = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//span[@class='css-xmy3u2']//h2";
    String probandBeingTested = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//span[@class='css-o788i1']//span[@class='css-xmy3u2']";
    String bornInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//ul//li//span[text()='Born']";
    String genderInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//ul//li//span[text()='Gender']";
    String nhsNumberInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//ul//li//span[text()='NHS No.']";
    String ngsIdInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//ul//li//span[text()='Patient NGIS ID']";
    String patientChoiceInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//ul//li//span[text()='Patient choice status']";
    String editButtonInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//button[@aria-label='edit button']";

    String specificPatientChoiceEdit = "//ul//span[text()='NHSLastFour']/ancestor::div[@class='css-1qv4t1n']//button";
    String fileTypeDropDownValue = "//a[@class='dropdown-item'][contains(text(),'dummyOption')]";

    String uploadFilepath = System.getProperty("user.dir") + File.separator +"testdata"+File.separator;

    @FindBy(xpath = "//div[@class='dropdown']")
    WebElement fileTypeDropDown;
    @FindBy(xpath = "//input[@placeholder='DD']")
    WebElement uploadDay;
    @FindBy(xpath = "//input[@placeholder='MM']")
    WebElement uploadMonth;
    @FindBy(xpath = "//input[@placeholder='YYYY']")
    WebElement uploadYear;


    public PatientChoicePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public boolean editPatientChoice() {
        try{
            seleniumLib.clickOnWebElement(editPatientChoice);
            return true;
        } catch (Exception exp) {
            Debugger.println("Could not click on Patient Choice Edit: " + exp);
            SeleniumLib.takeAScreenShot("PatientChoiceEdit.jpg");
            return false;
        }
    }
    public boolean editSpecificPatientChoice(String familyDetails){
        String nhsNumber = "";
        try {
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyDetails);
            Set<String> paramsKey = paramNameValue.keySet();
            for (String key : paramsKey) {
                if(key.equalsIgnoreCase("NHSNumber")){
                  nhsNumber = paramNameValue.get(key);
                  break;
                }
            }
            if(nhsNumber == null || nhsNumber.isEmpty()){
                Debugger.println("NHS Number not provided to edit the patient choice.");
                return false;
            }
            //Debugger.println("NHS : "+nhsNumber);
            String nhsLastFour = nhsNumber.substring(6,nhsNumber.length());//Assuming NHSNumber is always 10 digit.
            //Debugger.println("NHSFOUR : "+nhsLastFour);
            By pChoiceEdit = By.xpath(specificPatientChoiceEdit.replaceAll("NHSLastFour", nhsLastFour));
            WebElement element = driver.findElement(pChoiceEdit);
            element.click();
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from clicking on edit patient choice of specific NHSNumber:"+exp);
            return false;
        }
    }

    public boolean selectPatientChoiceCategory(String category){
        try{
            if(category == null || category.isEmpty()) {
                return true;
            }
            Wait.forElementToBeDisplayed(driver,newPatientChoiceFormTitle,100);
            patientChoiceCategory = patientChoiceCategory.replaceAll("dummyCategory",category);
            Wait.seconds(10);//Default observed a delay of 5-10 seconds for loading this section
            WebElement webElement = driver.findElement(By.xpath(patientChoiceCategory));
            seleniumLib.clickOnWebElement(webElement);
            Debugger.println("PatientChoiceCategory..Done");
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from Selecting PatientChoiceCategory:"+exp);
            SeleniumLib.takeAScreenShot("patientChoiceCategory.jpg");
            return false;
        }
    }
    public boolean selectTestType(String test_type){
        try{
            if(test_type == null || test_type.isEmpty()) {
                return true;
            }
            testType = testType.replaceAll("dummyTestType",test_type);
            WebElement webElement = driver.findElement(By.xpath(testType));
            webElement.click();
            Debugger.println("TestType..Done");
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from Selecting PatientChoiceTestType:"+exp);
            SeleniumLib.takeAScreenShot("PatientChoiceTestType.jpg");
            return false;
        }
    }
    public boolean fillRecordedByDetails(String recorderBy) {
        try {
            if(recorderBy == null || recorderBy.isEmpty()){
                return true;
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
                            if(paramNameValue.get(key).equalsIgnoreCase("UploadDocument")){
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
            if(uploadDocument){
                return uploadRecordTypeDocument(fileType);
            }
            Debugger.println("Record Type..Done");
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in Filling RecordedBy Information: "+exp);
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
        }catch(Exception exp){
            Debugger.println("Exception in uploading record type in Patient choice: "+exp);
            SeleniumLib.takeAScreenShot("recordTypeUpload.jpg");
            return false;
        }
    }
    public boolean selectPatientChoice(String patient_choice){
        try{
            if(patient_choice == null || patient_choice.isEmpty()) {
                return true;
            }

            WebElement titleElement = driver.findElement(By.xpath("//div[contains(text(),'Patient choices')]"));
            Wait.forElementToBeDisplayed(driver,titleElement);
            //Actions.scrollToTop();
            patientChoice = patientChoice.replaceAll("dummyChoice",patient_choice);
            WebElement webElement;
            try {
                webElement = driver.findElement(By.xpath(patientChoice));
            }catch(NoSuchElementException nsee){
                //If the element not found, waiting for 5 seconds and the searchign again. Some times it is taking time.
                Wait.seconds(5);
                webElement = driver.findElement(By.xpath(patientChoice));
            }
            seleniumLib.clickOnWebElement(webElement);
            seleniumLib.clickOnWebElement(patientChoiceOption1);
            seleniumLib.clickOnWebElement(patientChoiceOption2);
            if(!Wait.isElementDisplayed(driver,continueButton,5)){
                Debugger.println("Patient Choice not done properly.");
                return false;
            }
            seleniumLib.scrollToElement(continueButton);
            Debugger.println("patientChoice..Done");
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from Selecting PatientChoice:"+exp);
            SeleniumLib.takeAScreenShot("PatientChoice.jpg");
            return false;
        }
    }
    public boolean selectChildAssent(String child_assent){
        try{
            if(child_assent == null || child_assent.isEmpty()) {
                return true;
            }
            WebElement titleElement = driver.findElement(By.xpath("//div[contains(text(),'Child assent')]"));
            Wait.forElementToBeDisplayed(driver,titleElement);
            Actions.scrollToTop(driver);
            childAssent = childAssent.replaceAll("dummyAssent",child_assent);
            Wait.seconds(1);
            WebElement webElement = driver.findElement(By.xpath(childAssent));
            if(!Wait.isElementDisplayed(driver,webElement,10)){
                Debugger.println("Waiting for Child assent options...");
                Wait.seconds(2);
            }
            webElement.click();
            if(child_assent.equalsIgnoreCase("Yes")){//Click on Signature board
                Debugger.println("Signing Child Assent Signature...");
                try {
                    Wait.forElementToBeDisplayed(driver, signaturePad, 30);
                }catch (TimeoutException texp){
                    Debugger.println("Signature Pad not preset, selecting child assent again...");
                    seleniumLib.clickOnWebElement(webElement);
                    Wait.seconds(5);
                }
                if(!Wait.isElementDisplayed(driver,signaturePad,3)){
                    Debugger.println("Signature Board Not loaded for ChildAssent.");
                    return false;
                }
                seleniumLib.clickOnWebElement(signaturePad);
            }

            if(!Wait.isElementDisplayed(driver,continueButton,10)){
                Debugger.println("Child element could not select.");
                return false;
            }
            Debugger.println("Child Assent Done.");
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from Selecting ChildAssent:"+exp);
            SeleniumLib.takeAScreenShot("ChildAssent.jpg");
            return false;
        }
    }

    public boolean fillParentSignatureDetails(String parentDetails) {
        try {
            if(parentDetails == null || parentDetails.isEmpty()){
                return true;
            }
            WebElement titleElement = driver.findElement(By.xpath("//div[contains(text(),'Parent/Guardian signature')]"));
            Wait.forElementToBeDisplayed(driver,titleElement);
            if(!Wait.isElementDisplayed(driver,sectionTitle_ParentGuardianSignature,180)){//Typically this takes time 1-2 minutes
                Debugger.println("ParentGuardianSignature section not loaded even after waiting time. Failing.");
                SeleniumLib.takeAScreenShot("parentGuardianSign.jpg");
                return false;
            }
            seleniumLib.scrollToElement(sectionTitle_ParentGuardianSignature);
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
            if(Wait.isElementDisplayed(driver,signaturePad,100)) {
                seleniumLib.clickOnWebElement(signaturePad);
            }
            if(Wait.isElementDisplayed(driver,submitPatientChoice,100)) {
                seleniumLib.scrollToElement(submitPatientChoice);
                seleniumLib.clickOnWebElement(submitPatientChoice);
            }else{
                Debugger.println("Submit patient choice option not displayed. Failing...");
                SeleniumLib.takeAScreenShot("submitPatientChoice.jpg");
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in Filling fillParentSignatureDetails Information: "+exp);
            SeleniumLib.takeAScreenShot("ParentSignature.jpg");
            return false;
        }
    }
    public void submitPatientChoice() {
        try {
           seleniumLib.clickOnWebElement(submitPatientChoice);
           Wait.seconds(30);
        } catch (Exception exp) {
            Debugger.println("Exception from submitting Patient Choice...." + exp);
            SeleniumLib.takeAScreenShot("SubmitPateintChoice.jpg");
        }
    }

    public boolean verifyPatientChoiceStatus(String expStatus) {
        try {
            if(!Wait.isElementDisplayed(driver,patientChoiceStatus,30)){
                Debugger.println("PatientChoice is not displayed.");
                SeleniumLib.takeAScreenShot("PatientChoice.jpg");
                return false;
            }
            String actStatus = patientChoiceStatus.getText();
            if(!expStatus.equalsIgnoreCase(actStatus)){
                Debugger.println("Expected Patient Choice: "+expStatus+", But actual is: "+actStatus);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception FamilyMemberDetailsPage:verifyPatientChoiceStatus :"+ exp);
            return false;
        }
    }
    public boolean verifyPatientIdentifiersInPatientChoicePage() {
        try{
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
            if (probandTestedList == null || probandTestedList.size() != (noOfPatients*2)) {
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
            List<WebElement> nhsList = seleniumLib.getElements(By.xpath(nhsNumberInformation));
            if (nhsList == null || nhsList.size() != noOfPatients) {
                Debugger.println("Expected Presence of NHS Information for " + noOfPatients + " patients in  Patient Choice.");
                SeleniumLib.takeAScreenShot("nhsInfo.jpg");
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
            if (editButtonList != null){
                if(editButtonList.size() != noOfPatients) {
                    Debugger.println("Expected Presence of Edit Information for " + noOfPatients + " patients in  Patient Choice Page.");
                    SeleniumLib.takeAScreenShot("editButtonInfo.jpg");
                    return false;
                }
            }

            return true;
        }catch(Exception exp){
            Debugger.println("Exception in  Verifying Patient Identifier Information in Patient Choice Page.");
            return false;
        }

    }
}//end
