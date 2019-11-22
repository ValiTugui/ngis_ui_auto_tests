package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.hu.De;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class FamilyMemberDetailsPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//h1[contains(text(),'Confirm family member details')]")
    public WebElement familyMemberDetailsTitle;

    @FindBy(xpath = "//h2[@class='css-1ujfcb9']")
    public WebElement familyMemeberInfoName;

    @FindBy(xpath = "//label[contains(text(),'Title')]")
    public WebElement pageTitleLabel;

    @FindBy(id = "title")
    public WebElement pageTitle;

    @FindBy(xpath = "//h3[@class='styles_text__1aikh styles_text--3__117-L styles_no-results__header__1RMRD']")
    public WebElement errorMessage;

    @FindBy(xpath = "//label[contains(text(),'First name')]")
    public WebElement firstNameLabel;

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(xpath = "//label[contains(text(),'Last name')]")
    public WebElement lastNameLabel;

    @FindBy(id = "familyName")
    public WebElement lastName;

    @FindBy(xpath = "//label[contains(text(),'Date of birth')]")
    public WebElement dobLabel;

    @FindBy(id = "dateOfBirth")
    public WebElement dobOfFamilyMember;

    @FindBy(xpath = "//label[text()='Gender']")
    public WebElement genderLabel;

    @FindBy(xpath = "//label[text()='Gender']//following::div")
    public WebElement gender;

    @FindBy(xpath = "//label[contains(text(),'Life status')]")
    public WebElement lifeStatusLabel;

    @FindBy(xpath = "//label[contains(text(),'Life status')]//following::div[1]")
    public WebElement lifeStatus;

    @FindBy(xpath = "//label[contains(text(),'Ethnicity')]")
    public WebElement ethnicityLabel;

    @FindBy(xpath = "//label[contains(text(),'Ethnicity')]//following::div[1]")
    public WebElement ethnicity;

    @FindBy(xpath = "//label[contains(text(),'Relationship to proband')]")
    public WebElement relationshipToProbandLabel;

    @FindBy(xpath = "//label[text()='Relationship to proband']//following::div[1]")
    public WebElement relationshipToProband;

    @FindBy(xpath = "//label[contains(text(),'NHS Number')]")
    public WebElement nhsNumberLabel;

    @FindBy(xpath = "//h2[@class='css-1ujfcb9']/following::button[1]")
    public WebElement editBoxTestPackage;

    @FindBy(xpath = "//h2[@class='css-1ujfcb9']/following::button[2]")
    public WebElement removeFamilyMember;

    @FindBy(id = "nhsNumber")
    public WebElement nhsNumber;

    @FindBy(xpath = "//label[contains(text(),'Hospital Number')]")
    public WebElement hospitalNumberLabel;

    @FindBy(id = "hospitalNumber")
    public WebElement hospitalNumber;

    @FindBy(id = "dateDay")
    public WebElement dateDay;

    @FindBy(id = "dateMonth")
    public WebElement dateMonth;

    @FindBy(id = "dateYear")
    public WebElement dateYear;

    @FindBy(xpath = "//button[contains(string(),'Search')]")
    public WebElement searchButton;

    @FindBy(css = "a[class*='patient-card']")
    public WebElement patientCard;

    @FindBy(css = "button[class*='referral-navigation__continue']")
    public WebElement saveAndContinueButton;

    @FindBy(xpath = "//button[contains(text(),'Back')]")
    public WebElement backButton;

    @FindBy(css = "*[class*='helix']")
    public List<WebElement> helix;

    String helixIcon = "*[class*='helix']";

    @FindBy(css = "div[class*='error-message__text']")
    public List<WebElement> validationErrors;

    @FindBy(xpath = "(//label[text()='Relationship to proband']//following::div)[1]")
    public WebElement relationshipToProbandDropdown;

    @FindBy(xpath = "//div[contains(@class,'styles_notification__1W')]")
    public WebElement unmatchedParticipantMessage;

    @FindBy(xpath = "//a[contains(text(),'amend')]")
    public WebElement participantAmendmentLink;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(xpath = "//*[contains(@id,'question-id-q96')]")
    public WebElement diseaseStatusDropdown;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-years')]")
    public WebElement ageOfOnsetYearsField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-months')]")
    public WebElement ageOfOnsetMonthsField;

    @FindBy(xpath = "//label[contains(text(),'Find an HPO phenotype or code')]/..//input")
    public WebElement hpoSearchField;
    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::div[contains(@class,'css-1qv4t1n')]")
    public WebElement patientCardField;

    @FindBy(css = "table[class*='table--hpo']")
    public WebElement hpoTable;

//    @FindBy(xpath = "//h2[@class='css-1ujfcb9']/following::button[2]")
//    public WebElement removeFamilyMember;

    @FindBy(xpath = "//div[contains(text(),'Family member removed from referral')]")
    public WebElement successMessageAfterRemovefamilyMember;

    @FindBy(xpath = "//button[contains(text(),'Add new patient to referral')]")
    public WebElement AddReferralButton;

    @FindBy(css = "[class*='hpo-term__name']")
    public List<WebElement> hpoTerms;
    @FindBy(css = "div[id*='react-select']")
    public List<WebElement> dropdownValues;

    @FindBy(xpath = "//div[contains(@class,'test-list')]//span[contains(@class,'checked')]")
    WebElement testPackageCheckBoxChecked;

    By firstLastNameTitle = By.xpath("//h1[contains(text(),'Confirm family member details')]/..//h2");

    By genderTitle            = By.xpath("//h1[contains(text(),'Confirm family member details')]/..//ul/li/span[contains(text(),'Gender')]/following-sibling::span");
    By selectedTest           = By.xpath("//div[contains(@class,'test-list_')]//span[contains(@class,'checked')]");
    By unSelectedTest         = By.xpath("//div[contains(@class,'test-list_')]//span[contains(@class,'checkbox-card')]");
    String selectedTestTitle    = "//h3[contains(text(),'Selected tests for')]/span[contains(text(),";
    String selectedMemberTitle  = "//h4[contains(text(),'Selected family members')]/..//span[contains(text(),";
    String addFamilyMemberTitle = "//h1[contains(text(),'Add a family member to this referral')]/../div//h2[contains(text(),";
    String addFamilyMemberReferralTitle = "//h1[contains(text(),'Add a family member to this referral')]/../div//span[contains(text(),";
    By hpoRows = By.xpath("//table[contains(@class,'--hpo')]/tbody/tr");

    @FindBy(xpath = "//h3[contains(text(),'1 patient record found')]")
    WebElement patientRecordFoundTitle;

    @FindBy(xpath = "//h3[contains(text(),'1 patient record found')]/../a//p[text()='Born']")
    WebElement patientCardBorn;

    @FindBy(xpath = "//h3[contains(text(),'1 patient record found')]/../a//p[text()='Gender']")
    WebElement patientCardGender;

    @FindBy(xpath = "//h3[contains(text(),'1 patient record found')]/../a//p[text()='NHS No.']")
    WebElement patientCardNHSNo;

    @FindBy(xpath = "//p[contains(text(),'Tested family members you add')]")
    public WebElement familyMemberTestPackagePageSubTitle;

    @FindBy(xpath = "//a[contains(text(),'add non-tested family members')]")
    public WebElement familyMemberTestPackagePageLink;


    @FindBy(xpath = "//h1[contains(text(),'Add a family member to this referral')]")
    public WebElement familyMemberLandingPageTitle;

    @FindBy(xpath = "//p[contains(text(),'Tested family members you add')]")
    public WebElement familyMemberLandingPageSubTitle;

    @FindBy(xpath = "//a[contains(text(),'add non-tested family members')]")
    public WebElement familyMemberLandingPageLink;

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::h2[@class='css-1ujfcb9']")
    public WebElement additionalFamilyMemberName;

    By additionalFamilyMemberList = By.xpath("//div[@class='css-1yllhwh']/following::h2[@class='css-1ujfcb9']");

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::h2[@class='css-1ujfcb9']/following::span[@class='css-ugl1y7']")
    public WebElement relationField;

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::h2[@class='css-1ujfcb9']/following::span[@class='css-1tu091a']")
    public WebElement beingTestedField;

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::span[contains(@id,'dateOfBirth')]")
    public WebElement dobField;

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::span[contains(@id,'gender')]")
    public WebElement genderField;

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::span[contains(@id,'patientChoiceStatus')]")
    public WebElement patientChoiceStatus;

    @FindBy(xpath = "//button[contains(text(),'Add family member')]")
    public WebElement addFamilyMemberButton;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    public WebElement continueButton;

    @FindBy(xpath = "//div[contains(text(),'Family member removed from referral')]")
    public WebElement successDeletionMessageOfFamilyMember;

    @FindBy(xpath = "//h1[text()='Patient choice']")
    public WebElement patientChoicePageTitle;

      @FindBy(xpath = "//h1[contains(text(),'Print sample forms')]")
    public WebElement printFormsPageTitle;

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::span[contains(@id,'gender')]/following::span[1]")
    public WebElement genderResult;

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::span[contains(@id,'nhsNumber')]/following::span[1]")
    public WebElement nhsNumberResult;

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::span[contains(@id,'ngisId')]/following::span[1]")
    public WebElement ngisIdResult;

    static NGISPatientModel familyMember;

    public FamilyMemberDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public void searchPatientDetailsUsingNHSNumberAndDOB(String nhsNo,String dob) {
        try {
            Wait.forElementToBeDisplayed(driver, nhsNumber);
            String[] dobs = dob.split("-");
            nhsNumber.sendKeys(nhsNo);
            dateDay.sendKeys(dobs[0]);
            dateMonth.sendKeys(dobs[1]);
            dateYear.sendKeys(dobs[2]);

            searchButton.click();
        }catch (Exception exp){
            Debugger.println("Exception in searchPatientDetailsUsingNHSNumberAndDOB "+exp+" DOB expected in DD-MM-YYYY Format: "+dob);
        }
        }
    public boolean verifyPatientRecordDetailsDisplay(){
        //Verify the Title
        Wait.forElementToBeDisplayed(driver,patientRecordFoundTitle);
        if(!seleniumLib.isElementPresent(patientRecordFoundTitle)){
            Debugger.println("Patient found title not displayed.");
            if(seleniumLib.isElementPresent(errorMessage)){
                Debugger.println("Error: "+errorMessage.getText());
            }
            return false;
        }
        //Verify other details - Born, Gender and NHS Number
        if(!seleniumLib.isElementPresent(patientCardBorn)){
            Debugger.println("Patient Born not displayed in Search Result.");
            return false;
        }
        if(!seleniumLib.isElementPresent(patientCardGender)){
            Debugger.println("Patient Gender not displayed in Search Result.");
            return false;
        }
        if(!seleniumLib.isElementPresent(patientCardNHSNo)){
            Debugger.println("Patient NHS number not displayed in Search Result.");
            return false;
        }
        return true;
    }
    public boolean verifyThePageTitlePresence(String expTitle){
        By pageTitle = By.xpath("//h1[contains(text(),'"+expTitle+"')]");
        if(!seleniumLib.isElementPresent(pageTitle)){
            Wait.forElementToBeDisplayed(driver,driver.findElement(pageTitle));
            if(!seleniumLib.isElementPresent(pageTitle)) {
                Debugger.println("Expected title :" + expTitle + " not loaded in the page.");
                return false;
            }
    }
        return true;
    }

    public void clickPatientCard() {
        Wait.forElementToBeDisplayed(driver, patientCard);
        patientCard.click();
    }
    public void clickOnSaveAndContinueButton() {
        try {
        Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
            Wait.forElementToBeClickable(driver,saveAndContinueButton);
            Wait.seconds(2);
        Click.element(driver, saveAndContinueButton);
            Wait.seconds(5);
        if (helix.size() > 0) {
            Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
        }
        }catch(Exception exp){
            Debugger.println("Could not click on Save and Continue...."+exp);
    }
    }
    public boolean verifyTheErrorMessageDisplay(String errorMessage, String fontColor) {
        try {
            Wait.seconds(5);
            String actualMessage = seleniumLib.getText(validationErrors.get(0));
            if (!errorMessage.equalsIgnoreCase(actualMessage)) {
                Debugger.println("Expected Message: " + errorMessage + ", but Actual Message: " + actualMessage);
                return false;
            }
            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(fontColor);
            String actColor = validationErrors.get(0).getCssValue("color");
            if (!expectedFontColor.equalsIgnoreCase(actColor)) {
                Debugger.println("Expected Color: " + expectedFontColor + ", but Actual Color: " + actColor);
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from validating Error Message "+exp);
            return false;
        }
    }
    public void fillTheRelationshipToProband(String relationToProband){
        validationErrors.clear();
        Click.element(driver, relationshipToProbandDropdown);
        Wait.seconds(2);
        Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='"+relationToProband+"']")));
        Wait.seconds(2);
        if(validationErrors.size() > 0){
            validationErrors.clear();
            Click.element(driver, relationshipToProbandDropdown);
            Wait.seconds(2);
            Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='"+relationToProband+"']")));
            Wait.seconds(2);
        }
        if(validationErrors.size() > 0){
            Debugger.println("Relation to Proband Not Selected.");
        }

    }
    public void readFamilyMemberDetailsFor(String relationToProband){
        //Here reading the Family member details to verify in next page  - whether details loaded with the expected details
        familyMember = new NGISPatientModel();
        try {
            if (seleniumLib.isElementPresent(firstLastNameTitle)) {
                String f_l_name = seleniumLib.getText(firstLastNameTitle);
                familyMember.setFIRST_NAME(f_l_name.split(",")[0].trim());
                familyMember.setLAST_NAME(f_l_name.split(",")[1].trim());
            }

            if (seleniumLib.isElementPresent(genderTitle)) {
                familyMember.setGENDER(seleniumLib.getText(genderTitle));
            }
            familyMember.setRELATIONSHIP_TO_PROBAND(relationToProband);
            Debugger.println("Read Family Member Details........."+familyMember.getFIRST_NAME());
            Wait.seconds(5);
        }catch(Exception exp){
            Debugger.println("Exception in Reading Family Details from Confirmation Page. "+exp);
        }
    }
    public boolean verifyTheTestAndDetailsOfAddedFamilyMember(){
        Wait.seconds(5);
        //1. Verify the display of Title for the added Test.
        By testTitle = By.xpath(selectedTestTitle+"'"+familyMember.getRELATIONSHIP_TO_PROBAND()+"')]");
        if(!seleniumLib.isElementPresent(testTitle)){
            Debugger.println("Selected Test Title for Family member with Relation "+familyMember.getRELATIONSHIP_TO_PROBAND()+" not displayed.");
            return false;
        }
        //2. Verify the display of Relation to Proband as given.
        By selectedFamilyMember = By.xpath(selectedMemberTitle+"'"+familyMember.getRELATIONSHIP_TO_PROBAND()+"')]");
        if(!seleniumLib.isElementPresent(selectedFamilyMember)){
            Debugger.println("Selected Family member with Relation "+familyMember.getRELATIONSHIP_TO_PROBAND()+" not displayed.");
            return false;
        }
        //3. Verify the select as checked by default.
         if(!seleniumLib.isElementPresent(selectedTest)){
            if(!seleniumLib.isElementPresent(unSelectedTest)){
                Debugger.println("Option to select test not present in Select Test Page.");
                return false;
            }else{
                seleniumLib.clickOnElement(unSelectedTest);//To make the test selected by default.
            }
         }
        return true;
    }

    public void fillFamilyMemberDiseaseStatusWithGivenParams(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        //DiseaseStatus handling as the first item, otherwise some overlay element visible on top of this and creating issue in clicking on the same.
        if(paramNameValue.get("DiseaseStatus") != null && !paramNameValue.get("DiseaseStatus").isEmpty()) {
            try {
                Click.element(driver, diseaseStatusDropdown);
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + paramNameValue.get("DiseaseStatus") + "']")));
            }catch(Exception exp){
                Debugger.println("Exception from selecting disease from the disease dropdown...:"+exp);
            }
        }
        for (String key : paramsKey) {
            if(key.equalsIgnoreCase("DiseaseStatus")){
                continue;
            }
            switch (key) {
                case "AgeOfOnset": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        String[] age_of_onsets = paramNameValue.get(key).split(",");
                        ageOfOnsetYearsField.sendKeys(age_of_onsets[0]);
                        ageOfOnsetMonthsField.sendKeys(age_of_onsets[1]);
                    }
                    break;
                }
                case "HpoPhenoType": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        //Check whether the given Phenotype already added to the patient, if yes no need to enter again.
                        String hpoValue="";
                        boolean isExists = false;
                        List<WebElement> rows = seleniumLib.getElements(hpoRows);
                        if(rows != null && rows.size() > 0){
                            for(WebElement row:rows){
                                hpoValue = row.findElement(By.xpath("./td[1]")).getText();
                                if(hpoValue.equalsIgnoreCase(paramNameValue.get(key))){
                                    isExists = true;
                                    Debugger.println("Phenotype already exists:");
                                    break;//for loop
                                }
                            }//for
                        }
                        if(!isExists) {
                           searchAndSelectSpecificHPOPhenotype(paramNameValue.get(key));
                        }
                    }
                    break;
                }
            }//switch
        }//for
    }//method
    public void searchAndSelectSpecificHPOPhenotype(String hpoTerm) {
        try {
            Wait.seconds(3);
            if (!seleniumLib.isElementPresent(hpoSearchField)) {
                seleniumLib.scrollToElement(hpoSearchField);
            }
            seleniumLib.clickOnWebElement(hpoSearchField);
            Actions.fillInValue(hpoSearchField, hpoTerm);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, hpoTerm);
        }catch(Exception exp){
            Debugger.println("Exception from searchAndSelectSpecificHPOPhenotype: "+exp);
        }
    }
    public boolean verifyAddedFamilyMemberDetailsInLandingPage(){
        //1. Verify the display of Title for the added Test.
        By firstNameLastName = By.xpath(addFamilyMemberTitle+"'"+familyMember.getFIRST_NAME()+", "+familyMember.getLAST_NAME()+"')]");
        if(!seleniumLib.isElementPresent(firstNameLastName)){
            Debugger.println("Selected Family member not displayed in Landing Page: "+familyMember.getFIRST_NAME()+", "+familyMember.getLAST_NAME());
            return false;
        }
        //2. Verify the display of Relation to Proband as given.
        By selectedFamilyMember = By.xpath(addFamilyMemberReferralTitle+"'"+familyMember.getRELATIONSHIP_TO_PROBAND()+"')]");
        if(!seleniumLib.isElementPresent(selectedFamilyMember)){
            Debugger.println("Selected Family member's Relation "+familyMember.getRELATIONSHIP_TO_PROBAND()+" not displayed.");
            return false;
        }
        return true;
    }
    public boolean verifyTheElementsOnFamilyMemberDetailsPage() {
        Wait.forElementToBeDisplayed(driver, pageTitleLabel);
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(pageTitleLabel);
        expElements.add(pageTitle);
        expElements.add(firstNameLabel);
        expElements.add(firstName);
        expElements.add(lastName);
        expElements.add(lastNameLabel);
        expElements.add(dobLabel);
        expElements.add(dobOfFamilyMember);
        expElements.add(genderLabel);
        expElements.add(gender);
        expElements.add(lifeStatusLabel);
        expElements.add(lifeStatus);
        expElements.add(ethnicityLabel);
        expElements.add(ethnicity);
        expElements.add(relationshipToProbandLabel);
        expElements.add(relationshipToProband);
        expElements.add(nhsNumberLabel);
        expElements.add(nhsNumber);
        expElements.add(hospitalNumberLabel);
        expElements.add(hospitalNumber);
        for(int i=0; i<expElements.size(); i++){
            if(!seleniumLib.isElementPresent(expElements.get(i))){
                return false;
            }
        }
        return true;
    }

    public void removeFetchedDataInFamilyMemberDetailsPage(String clearToDropdown){
        Actions.clearField(firstName);
        Actions.clearField(lastName);
        Actions.clearField(dobOfFamilyMember);
        String[] expInputs = null;
        expInputs = clearToDropdown.split(",");
        String pathToElement = "";
        By xpathElement = null;
        for(int i=0; i<expInputs.length;i++) {
            pathToElement = "//label[text()='"+expInputs[i]+"']//following::div[@class='css-16pqwjk-indicatorContainer'][1]";
            xpathElement = By.xpath(pathToElement);
            if(!seleniumLib.isElementPresent(xpathElement)){
                Debugger.println("Path :"+pathToElement+" Could not locate");
                break;
            }
            try {
                seleniumLib.clickOnElement(xpathElement);
            }catch(Exception exp){
                //seleniumLib.moveMouseAndClickOnElement(xpathElement);
            }
        }
    }
    public boolean verifyTheTestCheckboxIsSelected(){
        try {
            Wait.forElementToBeDisplayed(driver, testPackageCheckBoxChecked);
            if (!seleniumLib.isElementPresent(testPackageCheckBoxChecked)) {//If not present
                Debugger.println("Test for Family member " + familyMember.getRELATIONSHIP_TO_PROBAND() + " not in SELECTED State.");
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("FamilyMemberDetailsPage:verifyTheTestCheckboxIsSelected:Exception:"+exp);
            return false;
        }

    }
    public void clickOnAddNewPatientToReferral(){
        Wait.forElementToBeDisplayed(driver, AddReferralButton);
        Click.element(driver, AddReferralButton);
    }
    public void verifyTheTitleOfTheFamilyMemberDetailsPage(String expTitle) {
        Wait.forElementToBeDisplayed(driver, pageTitle);
        Debugger.println("The actual page title  is :" + pageTitle.getText());
        Assert.assertEquals(expTitle, pageTitle.getText().trim());
    }

    public void deSelectTheTest() {
        try {
            seleniumLib.clickOnWebElement(testPackageCheckBoxChecked);
        }catch(Exception exp){
            Debugger.println("FamilyMemberDetailsPage:deSelectTheTest:Exception:"+exp);
        }
    }
    public boolean verifyTestPackageCheckBoxDeSelected() {
        try {
            if(seleniumLib.isElementPresent(testPackageCheckBoxChecked)){
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("FamilyMemberDetailsPage:verifyTestPackageCheckBoxDeSelected:Exception:"+exp);
            return false;
        }

    }
    public void clickOnBackButton() {
        if(!seleniumLib.isElementPresent(backButton)){
            Debugger.println("Back button not present on the Family member details page");
        }
        seleniumLib.clickOnWebElement(backButton);
        if (helix.size() > 0) {
            Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
        }
    }
    public boolean verifyThePatientCardField(){
        Wait.forElementToBeDisplayed(driver,patientCardField);
        if(!seleniumLib.isElementPresent(patientCardField)){
            Debugger.println("Test for Family member to this referral "+familyMember.getRELATIONSHIP_TO_PROBAND()+" not in available.");
            return false;
        }
        return true;
    }
    public void verifyThePatientByEdited(){
        List<WebElement> patientCards = driver.findElements(additionalFamilyMemberList);
        Iterator<WebElement> itr = patientCards.iterator();
        while(itr.hasNext()) {
            String resultMessage= itr.next().getText();
            seleniumLib.clickOnWebElement(editBoxTestPackage);
            Assert.assertEquals(resultMessage, seleniumLib.getText(familyMemeberInfoName));
        }
    }

    public boolean verifyTheElementsOnFamilyMemberLandingPage() {
        Wait.forElementToBeDisplayed(driver, familyMemberLandingPageTitle);
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(familyMemberLandingPageTitle);
        expElements.add(familyMemberLandingPageSubTitle);
        expElements.add(familyMemberLandingPageLink);
        expElements.add(additionalFamilyMemberName);
        expElements.add(relationField);
        expElements.add(beingTestedField);
        expElements.add(dobField);
        expElements.add(genderField);
        expElements.add(patientChoiceStatus);
        expElements.add(addFamilyMemberButton);
        expElements.add(continueButton);

        for(int i=0; i<expElements.size(); i++){
            if(!seleniumLib.isElementPresent(expElements.get(i))){
                return false;
            }
        }
        return true;
    }
    public void getTextFromPatientCardFields(){
        List<WebElement> patientCards = driver.findElements(By.xpath("//div[@class='css-1yllhwh']/following::h2[@class='css-1ujfcb9']"));
        Iterator<WebElement> itr = patientCards.iterator();
        while(itr.hasNext()) {
            Debugger.println(itr.next().getText());
        }
    }

    public boolean removeFamilyFromLandingPage() {
        try {
            removeFamilyMember.click();
            Alert alert = driver.switchTo().alert();
            String alertMessage = driver.switchTo().alert().getText();
            Debugger.println("Alert message for removing family member" + alertMessage);
            alert.accept();
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from removing family member " + exp);
            return false;
        }
    }

    public boolean verifyTheDeleteMessage(String deleteMessage) {
        Wait.forElementToBeDisplayed(driver, successDeletionMessageOfFamilyMember);
        if(deleteMessage.equalsIgnoreCase(successDeletionMessageOfFamilyMember.getText())){
            Debugger.println("Expected Message: "+deleteMessage+" Not present in the family member landing page.");
            return false;
        }
        return true;
    }

    public void clickOnContinueButton() {
        Wait.forElementToBeDisplayed(driver, continueButton);
        continueButton.click();
    }

    public boolean patientChoicePageIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, patientChoicePageTitle);
        return true;
    }

    public boolean verifyTheDeleteMessageIsPresent() {
        if (seleniumLib.isElementPresent(successDeletionMessageOfFamilyMember)) {
            return false;
        }
        return true;
    }

    public boolean unmatchedParticipantErrorMessage() {
        if (seleniumLib.isElementPresent(unmatchedParticipantMessage)) {
            return true;
        }
        return false;
    }

    public void clicksOnParticipantAmendmentLink() {
        seleniumLib.clickOnWebElement(participantAmendmentLink);
    }
    public boolean removeAFamilyMember() {
        try {
            removeFamilyMember.click();
            Alert alert = driver.switchTo().alert();
            String alertMessage = driver.switchTo().alert().getText();
            Debugger.println("Alert message for removing family member" + alertMessage);
            alert.accept();
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from removing family member " + exp);
            return false;
        }
    }
    public boolean verifyTheElementsOnFamilyMemberTestPackagePage() {
        Wait.forElementToBeDisplayed(driver, familyMemberLandingPageTitle);
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(familyMemberLandingPageTitle);
        expElements.add(familyMemberTestPackagePageSubTitle);
        expElements.add(familyMemberTestPackagePageLink);
        expElements.add(additionalFamilyMemberName);
        expElements.add(relationField);
        expElements.add(beingTestedField);
        expElements.add(dobField);
        expElements.add(genderField);
        expElements.add(patientChoiceStatus);
        expElements.add(addFamilyMemberButton);
        expElements.add(continueButton);

        for(int i=0; i<expElements.size(); i++){
            if(!seleniumLib.isElementPresent(expElements.get(i))){
                return false;
            }
        }
        return true;
    }
    public boolean verifyTheDetailsOfFamilyMemberOnFamilyMemberPage() {
        Wait.forElementToBeDisplayed(driver, familyMemberLandingPageTitle);
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(beingTestedField);
        expElements.add(dobField);
        expElements.add(genderField);
        expElements.add(patientChoiceStatus);
        expElements.add(addFamilyMemberButton);

        for(int i=0; i<expElements.size(); i++){
            if(!seleniumLib.isElementPresent(expElements.get(i))){
                return false;
            }
        }
        return true;
    }
}//ends
