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
    public List<WebElement> nameResults;

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

    @FindBy(xpath = "//div[contains(@class,'styles_notification__1W')]/div//p")
    public WebElement unmatchedParticipantMessage;

    @FindBy(xpath = "//a[contains(text(),'amend the expected number of participants')]")
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

    @FindBy(xpath = "//div[contains(@class,'test-list')]//span[contains(@class,'checkbox')]")
    WebElement testPackageCheckBox;

    By firstLastNameTitle = By.xpath("//h1[contains(text(),'Confirm family member details')]/..//h2");

    By genderTitle = By.xpath("//h1[contains(text(),'Confirm family member details')]/..//ul/li/span[contains(text(),'Gender')]/following-sibling::span");
    By selectedTest = By.xpath("//div[contains(@class,'test-list_')]//span[contains(@class,'checked')]");
    By unSelectedTest = By.xpath("//div[contains(@class,'test-list_')]//span[contains(@class,'checkbox-card')]");
    String selectedTestTitle = "//h3[contains(text(),'Selected tests for')]/span[contains(text(),";
    String selectedMemberTitle = "//h4[contains(text(),'Selected family members')]/..//span[contains(text(),";
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

    @FindBy(xpath = "//button[@aria-label='edit button']")
    List<WebElement> editButton;

    @FindBy(xpath = "//button[@aria-label='remove button']")
    List<WebElement> deleteButton;

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

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::h2[@class='css-1ueygkf']")
    public WebElement unsuccessAdditionalFamilyMemberName;
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

    @FindBy(xpath = "//span[text()='Patient NGIS ID']")
    public WebElement patientNGISId;

    @FindBy(xpath = "//span[text()='Patient choice status']")
    public WebElement patientChoiceStatusField;

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
    @FindBy(xpath = "//span[contains(text(),'Not entered')]")
    public WebElement patientChoiceNotEnteredStatus;

    @FindBy(xpath = "//span[contains(text(),'Not entered')]/following::button[@aria-label='edit button']")
    public WebElement notEnteredEditBox;
    static NGISPatientModel familyMember;
    static ArrayList<String> familyMemberLandingPageDetails;
    static ArrayList<String> patientChoicePageDetails;
    static ArrayList<String> printFormsPageDetails;

    @FindBy(xpath = "//span[contains(@aria-labelledby,'nhsNumber')]")
    public List<WebElement> nhsNumberResults;

    @FindBy(xpath = "//span[contains(@aria-labelledby,'gender')]")
    public List<WebElement> genderResults;

    @FindBy(xpath = "//span[contains(@aria-labelledby,'ngisId')]")
    public List<WebElement> ngisIdResults;

    @FindBy(xpath = "//span[contains(@id,'patientChoiceStatus')]/following-sibling::span")
    public WebElement patientChoiceStatusResult;

    @FindBy(xpath = "//span[@class='styles_text__1aikh styles_text--5__203Ot']")
    public WebElement familyMemberTestPackagePageSubTitle2;
    @FindBy(xpath = "//span[@class='css-1tu091a']")
    public WebElement familyPageBeingTestedField;

    @FindBy(xpath = "//span[@class='css-weu35e']")
    public WebElement familyPageNotBeingTestedField;

    @FindBy(xpath ="//h2[@class='css-1ujfcb9']")
    public WebElement nameField;

    @FindBy(xpath = "//div[@class='styles_error-message__text__1v2Kl']")
    public WebElement oneParticipantMsg;
    @FindBy(xpath = "//div[@class='styles_test-list-item__info-message__2RWQ9']")
    public WebElement multipleParticipantMsg;

    static String memberName;

    //For PatientInformation Identifiers
    public static int noOfPatientsForIdentification = 0;
    String patientList ="//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']";
    String firstNameLastName = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//span[@class='css-xmy3u2']//h2";
    String probandBeingTested = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//span[@class='css-o788i1']//span[@class='css-xmy3u2']";
    String bornInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//ul//li//span[text()='Born']";
    String genderInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//ul//li//span[text()='Gender']";
    String nhsNumberInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//ul//li//span[text()='NHS No.']";
    String ngsIdInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//ul//li//span[text()='Patient NGIS ID']";
    String patientChoiceInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//ul//li//span[text()='Patient choice status']";
    String editButtonInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//button[@aria-label='edit button']";
    String removeButtonInformation = "//div[contains(@class,'styles_participant-list_')]/div[@class='css-1yllhwh']//button[@aria-label='remove button']";

    @FindBy(xpath = "//div[@class='css-1qv4t1n']")
    public WebElement familyMemberFocussed;
    @FindBy(xpath = "//span[@class='css-1sg2lsz']")
    public WebElement familyMemberNames;
    @FindBy(xpath = "//span[@class='css-ugl1y7']/child::span")
    public WebElement relationshipStatus;
    @FindBy(xpath = "//span[text()='Born']/following::span[contains(@aria-labelledby,'dateOfBirth')]")
    public WebElement familyMemberDob;
    @FindBy(xpath = "//span[text()='Gender']/following::span[contains(@aria-labelledby,'gender')]")
    public WebElement familyMemberGender;
    @FindBy(xpath = "//span[text()='NHS No.']/following::span[contains(@aria-labelledby,'nhsNumber')]")
    public WebElement familyMemberNhsNumbers;
    @FindBy(xpath = "//span[text()='Patient NGIS ID']/following::span[contains(@aria-labelledby,'ngisId')]")
    public WebElement familyMemberNgisId;


    public FamilyMemberDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public void searchPatientDetailsUsingNHSNumberAndDOB(String nhsNo, String dob) {
        try {
            Wait.forElementToBeDisplayed(driver, nhsNumber);
            String[] dobs = dob.split("-");
            nhsNumber.sendKeys(nhsNo);
            dateDay.sendKeys(dobs[0]);
            dateMonth.sendKeys(dobs[1]);
            dateYear.sendKeys(dobs[2]);

            searchButton.click();
        } catch (Exception exp) {
            Debugger.println("Exception in searchPatientDetailsUsingNHSNumberAndDOB " + exp + " DOB expected in DD-MM-YYYY Format: " + dob);
        }
    }

    public boolean verifyPatientRecordDetailsDisplay() {
        //Verify the Title
        Wait.forElementToBeDisplayed(driver, patientRecordFoundTitle);
        if (!seleniumLib.isElementPresent(patientRecordFoundTitle)) {
            Debugger.println("Patient found title not displayed.");
            if (seleniumLib.isElementPresent(errorMessage)) {
                Debugger.println("Error: " + errorMessage.getText());
            }
            return false;
        }
        //Verify other details - Born, Gender and NHS Number
        if (!seleniumLib.isElementPresent(patientCardBorn)) {
            Debugger.println("Patient Born not displayed in Search Result.");
            return false;
        }
        if (!seleniumLib.isElementPresent(patientCardGender)) {
            Debugger.println("Patient Gender not displayed in Search Result.");
            return false;
        }
        if (!seleniumLib.isElementPresent(patientCardNHSNo)) {
            Debugger.println("Patient NHS number not displayed in Search Result.");
            return false;
        }
        return true;
    }

    public boolean verifyThePageTitlePresence(String expTitle) {
        By pageTitle = By.xpath("//h1[contains(text(),'" + expTitle + "')]");
        if (!seleniumLib.isElementPresent(pageTitle)) {
            Wait.forElementToBeDisplayed(driver, driver.findElement(pageTitle));
            if (!seleniumLib.isElementPresent(pageTitle)) {
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


    public boolean verifyTheErrorMessageDisplay(String errorMessage, String fontColor) {
        try {
            Wait.seconds(5);
            String actualMessage = Actions.getText(validationErrors.get(0));
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
        } catch (Exception exp) {
            Debugger.println("Exception from validating Error Message " + exp);
            return false;
        }
    }


    public void fillTheRelationshipToProband(String relationToProband) {
        validationErrors.clear();
        if(!Wait.isElementDisplayed(driver,relationshipToProbandDropdown,30)){
            Debugger.println("FamilyMemberDetailsPage:relationshipToProbandDropdown element not displayed even after waiting period.");
            Wait.seconds(20);
        }
        seleniumLib.clickOnWebElement(relationshipToProbandDropdown);
        Wait.seconds(2);
        By ddElement = By.xpath("//span[text()='" + relationToProband + "']");
        if(!seleniumLib.isElementPresent(ddElement)){
            Debugger.println("FamilyMemberDetailsPage:relationshipToProbandDropdown value: "+relationToProband+" not present in drop down.");
            return;
        }
        Click.element(driver, dropdownValue.findElement(ddElement));
        Wait.seconds(2);
        if (validationErrors.size() > 0) {
            Debugger.println("FamilyMemberDetailsPage:Error in selecting Proband drop down pag:."+validationErrors.get(0).getText());
        }
    }

    public void readFamilyMemberDetailsFor(String relationToProband) {
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
            Debugger.println("Read Family Member Details........." + familyMember.getFIRST_NAME());
            Wait.seconds(5);
        } catch (Exception exp) {
            Debugger.println("Exception in Reading Family Details from Confirmation Page. " + exp);
        }
    }

    public boolean verifyTheTestAndDetailsOfAddedFamilyMember() {
        Wait.seconds(5);
        //1. Verify the display of Title for the added Test.
        By testTitle = By.xpath(selectedTestTitle + "'" + familyMember.getRELATIONSHIP_TO_PROBAND() + "')]");
        if (!seleniumLib.isElementPresent(testTitle)) {
            Debugger.println("Selected Test Title for Family member with Relation " + familyMember.getRELATIONSHIP_TO_PROBAND() + " not displayed.");
            return false;
        }
        //2. Verify the display of Relation to Proband as given.
        By selectedFamilyMember = By.xpath(selectedMemberTitle + "'" + familyMember.getRELATIONSHIP_TO_PROBAND() + "')]");
        if (!seleniumLib.isElementPresent(selectedFamilyMember)) {
            Debugger.println("Selected Family member with Relation " + familyMember.getRELATIONSHIP_TO_PROBAND() + " not displayed.");
            return false;
        }
        //3. Verify the select as checked by default.
        if (!seleniumLib.isElementPresent(selectedTest)) {
            if (!seleniumLib.isElementPresent(unSelectedTest)) {
                Debugger.println("Option to select test not present in Select Test Page.");
                return false;
            } else {
                seleniumLib.clickOnElement(unSelectedTest);//To make the test selected by default.
            }
        }
        return true;
    }

    public void fillFamilyMemberDiseaseStatusWithGivenParams(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        //DiseaseStatus handling as the first item, otherwise some overlay element visible on top of this and creating issue in clicking on the same.
        if (paramNameValue.get("DiseaseStatus") != null && !paramNameValue.get("DiseaseStatus").isEmpty()) {
            try {
                Click.element(driver, diseaseStatusDropdown);
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + paramNameValue.get("DiseaseStatus") + "']")));
            } catch (Exception exp) {
                Debugger.println("Exception from selecting disease from the disease dropdown...:" + exp);
            }
        }
        for (String key : paramsKey) {
            if (key.equalsIgnoreCase("DiseaseStatus")) {
                continue;
            }
            switch (key) {
                case "AgeOfOnset": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        String[] age_of_onsets = paramNameValue.get(key).split(",");
                        ageOfOnsetYearsField.sendKeys(age_of_onsets[0]);
                        ageOfOnsetMonthsField.sendKeys(age_of_onsets[1]);
                    }
                    break;
                }
                case "HpoPhenoType": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        //Check whether the given Phenotype already added to the patient, if yes no need to enter again.
                        boolean alreadyPresent = isHPOAlreadyConsidered(paramNameValue.get(key));
                        if (!alreadyPresent) {
                            searchAndSelectSpecificHPOPhenotype(paramNameValue.get(key));
                        }
                    }
                    break;
                }
            }//switch
        }//for
    }//method

    public boolean isHPOAlreadyConsidered(String hpoTerm) {
        String hpoValue = "";
        boolean isExists = false;
        List<WebElement> rows = seleniumLib.getElements(hpoRows);
        if (rows != null && rows.size() > 0) {
            for (WebElement row : rows) {
                hpoValue = row.findElement(By.xpath("./td[1]")).getText();
                if (hpoValue.equalsIgnoreCase(hpoTerm)) {
                    isExists = true;
                    Debugger.println("Phenotype already exists:");
                    break;//for loop
                }
            }//for
        }
        return isExists;
    }

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
        } catch (Exception exp) {
            Debugger.println("Exception from searchAndSelectSpecificHPOPhenotype: " + exp);
        }
    }

    public boolean verifyAddedFamilyMemberDetailsInLandingPage() {
        //1. Verify the display of Title for the added Test.
        By firstNameLastName = By.xpath(addFamilyMemberTitle + "'" + familyMember.getFIRST_NAME() + ", " + familyMember.getLAST_NAME() + "')]");
        if (!seleniumLib.isElementPresent(firstNameLastName)) {
            Debugger.println("Selected Family member not displayed in Landing Page: " + familyMember.getFIRST_NAME() + ", " + familyMember.getLAST_NAME());
            return false;
        }
        //2. Verify the display of Relation to Proband as given.
        By selectedFamilyMember = By.xpath(addFamilyMemberReferralTitle + "'" + familyMember.getRELATIONSHIP_TO_PROBAND() + "')]");
        if (!seleniumLib.isElementPresent(selectedFamilyMember)) {
            Debugger.println("Selected Family member's Relation " + familyMember.getRELATIONSHIP_TO_PROBAND() + " not displayed.");
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
        for (int i = 0; i < expElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expElements.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void removeFetchedDataInFamilyMemberDetailsPage(String clearToDropdown) {
        Actions.clearField(firstName);
        Actions.clearField(lastName);
        Actions.clearField(dobOfFamilyMember);
        String[] expInputs = null;
        expInputs = clearToDropdown.split(",");
        String pathToElement = "";
        By xpathElement = null;
        for (int i = 0; i < expInputs.length; i++) {
            pathToElement = "//label[text()='" + expInputs[i] + "']//following::div[@class='css-16pqwjk-indicatorContainer'][1]";
            xpathElement = By.xpath(pathToElement);
            if (!seleniumLib.isElementPresent(xpathElement)) {
                Debugger.println("Path :" + pathToElement + " Could not locate");
                break;
            }
            try {
                seleniumLib.clickOnElement(xpathElement);
            } catch (Exception exp) {
                //seleniumLib.moveMouseAndClickOnElement(xpathElement);
            }
        }
    }

    public boolean verifyTheTestCheckboxIsSelected() {
        try {
            Wait.forElementToBeDisplayed(driver, testPackageCheckBoxChecked, 30);
            if (!seleniumLib.isElementPresent(testPackageCheckBoxChecked)) {//If not present
                Debugger.println("Test for Family member " + familyMember.getRELATIONSHIP_TO_PROBAND() + " not in SELECTED State.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("FamilyMemberDetailsPage:verifyTheTestCheckboxIsSelected:Exception:" + exp);
            return false;
        }

    }

    public void clickOnAddNewPatientToReferral() {
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
            if(Wait.isElementDisplayed(driver,testPackageCheckBoxChecked,10)) {
                seleniumLib.clickOnWebElement(testPackageCheckBoxChecked);
                memberName = nameField.getText();
                Debugger.println("Deselected the Test for :"+memberName);
            }

        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("testSelect.jpg");
            Debugger.println("FamilyMemberDetailsPage:deSelectTheTest:Exception:" + exp);
        }
    }

    public boolean verifyTestPackageCheckBoxDeSelected() {
        try {
            if (seleniumLib.isElementPresent(testPackageCheckBoxChecked)) {
                Debugger.println("Expected the TestPackage as DeSelected, but it is in Selected State.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("FamilyMemberDetailsPage:verifyTestPackageCheckBoxDeSelected:Exception:" + exp);
            return false;
        }

    }

    public void clickOnBackButton() {
        try {
            seleniumLib.clickOnWebElement(backButton);
            if (helix.size() > 0) {
                Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
            }
        }catch(Exception exp){
            SeleniumLib.takeAScreenShot("BackButtonOnFMDetails.jpg");
            Debugger.println("Could not click on Back Button on FamilyDetailsPage: "+exp);
        }
    }

    public boolean verifyThePatientCardField() {
        //1. Verify the display of Title for the added Family Member
        By firstNameLastName = By.xpath(addFamilyMemberTitle + "'" + familyMember.getFIRST_NAME() + ", " + familyMember.getLAST_NAME() + "')]");
        if (!seleniumLib.isElementPresent(firstNameLastName)) {
            Debugger.println("Selected Family member not displayed in Landing Page: " + familyMember.getFIRST_NAME() + ", " + familyMember.getLAST_NAME());
            return false;
        }
        return true;
    }

    public boolean verifyTheEditingReferralColor() {
        By firstNameLastName = By.xpath(addFamilyMemberTitle + "'" + familyMember.getFIRST_NAME() + ", " + familyMember.getLAST_NAME() + "')]");
        WebElement webElement_FN = driver.findElement(firstNameLastName);
        String color = webElement_FN.getCssValue("color").toString();
        if(!color.equalsIgnoreCase("rgba(218, 41, 28, 1)")){
            Debugger.println("Expected color : "+color+" not displayed on editing family member.");
            return false;
        }
        return true;

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

        for (int i = 0; i < expElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expElements.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void getTextFromPatientCardFields() {
        List<WebElement> patientCards = driver.findElements(By.xpath("//div[@class='css-1yllhwh']/following::h2[@class='css-1ujfcb9']"));
        Iterator<WebElement> itr = patientCards.iterator();
        while (itr.hasNext()) {
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
        Assert.assertEquals(deleteMessage, successDeletionMessageOfFamilyMember.getText());
        return true;
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

    public void deselectCheckBoxOnFamilyPage(){

        Wait.forElementToBeDisplayed(driver,testPackageCheckBox);
        seleniumLib.clickOnWebElement(testPackageCheckBox);

    }

    public boolean unmatchedParticipantErrorMessage(String expMessage) {
       try {
           Wait.forElementToBeDisplayed(driver,unmatchedParticipantMessage,20);
           if (!seleniumLib.isElementPresent(unmatchedParticipantMessage)) {
               Debugger.println("Expected Unmatched Participant Error not displayed.");
               return false;
           }
           String actMessage = Actions.getText(unmatchedParticipantMessage);
           if(!actMessage.contains(expMessage)){
               Debugger.println("Actual Message: "+actMessage+"\n DOES NOT CONTAINS Expected Message:"+expMessage);
               return false;
           }
           return true;
       }catch(Exception exp){
           return false;
       }
    }

    public void clicksOnParticipantAmendmentLink() {
        if(!seleniumLib.isElementPresent(participantAmendmentLink)){
            Debugger.println("Link to Amend the number of participant not present as expected.");
            return;
        }
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

        for (int i = 0; i < expElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expElements.get(i))) {
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

        for (int i = 0; i < expElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expElements.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void clickOnCheckBoxAndSaveAndContinueButton() {
        seleniumLib.clickOnWebElement(testPackageCheckBoxChecked);
        Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
        Click.element(driver, saveAndContinueButton);
        if (helix.size() > 0) {
            Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
        }
    }
    public boolean participantsNotMatchingMsg(String expectedMessage) {
        Wait.forElementToBeDisplayed(driver, unmatchedParticipantMessage);
        String actualMessage = unmatchedParticipantMessage.getText();
        if (!actualMessage.equalsIgnoreCase(expectedMessage)) {
            return false;
        }
        return true;
    }
    public boolean printFormsPageIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, printFormsPageTitle);
             return true;
        }
    public boolean verifyTheElementsOnFamilyMemberPage() {
        try{
        Wait.forElementToBeDisplayed(driver, familyMemberLandingPageTitle);
            //Validation of Core Information Presence
            int noOfPatients = 0;
            List<WebElement> patientLists = seleniumLib.getElements(By.xpath(patientList));
            if (patientLists != null) {
                noOfPatients = patientLists.size();
            }

            if (noOfPatients == 0) {
                Debugger.println("No Patients Information Present in FamilyMember Landing Page.");
                SeleniumLib.takeAScreenShot("NoPatientList.jpg");
                return false;
            }
            noOfPatientsForIdentification = noOfPatients;
            Wait.seconds(2);
            Debugger.println("Validating Information of " + noOfPatients + " Patients in Family Member Landing Page.");
            List<WebElement> nameList = seleniumLib.getElements(By.xpath(firstNameLastName));
            if (nameList == null || nameList.size() != noOfPatients) {
                Debugger.println("Expected Presence of First/Last Name field for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("firstLastNameLst.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> probandTestedList = seleniumLib.getElements(By.xpath(probandBeingTested));
            if (probandTestedList == null || probandTestedList.size() != (noOfPatients*2)) {
                Debugger.println("Expected Presence of Proband and Being Tested Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("probandTested.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> bornList = seleniumLib.getElements(By.xpath(bornInformation));
            if (bornList == null || bornList.size() != noOfPatients) {
                Debugger.println("Expected Presence of Born Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("bornInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> genderList = seleniumLib.getElements(By.xpath(genderInformation));
            if (genderList == null || genderList.size() != noOfPatients) {
                Debugger.println("Expected Presence of Gender Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("genderInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> nhsList = seleniumLib.getElements(By.xpath(nhsNumberInformation));
            if (nhsList == null || nhsList.size() != noOfPatients) {
                Debugger.println("Expected Presence of NHS Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("nhsInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> ngisList = seleniumLib.getElements(By.xpath(ngsIdInformation));
            if (ngisList == null || ngisList.size() != noOfPatients) {
                Debugger.println("Expected Presence of NGSID Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("ngsInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> pchoiceList = seleniumLib.getElements(By.xpath(patientChoiceInformation));
            if (pchoiceList == null || pchoiceList.size() != noOfPatients) {
                Debugger.println("Expected Presence of PatientChoice Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("pchoiceInfo.jpg");
                return false;
            }
            //EDIT and REMOVE BUTTON
            Wait.seconds(2);
            List<WebElement> editButtonList = seleniumLib.getElements(By.xpath(editButtonInformation));
            if (editButtonList != null){
                if(editButtonList.size() != (noOfPatients-1)) {
                    Debugger.println("Expected Presence of Edit Information for " + (noOfPatients - 1) + " patients in  Family Member Landing Page.");
                    SeleniumLib.takeAScreenShot("editButtonInfo.jpg");
                    return false;
                }
            }
            Wait.seconds(2);
            List<WebElement> removeButtonList = seleniumLib.getElements(By.xpath(removeButtonInformation));
            if (removeButtonList != null) {
                if (removeButtonList.size() != (noOfPatients - 1)) {
                    Debugger.println("Expected Presence of Remove Information for " + (noOfPatients - 1) + " patients in  Family Member Landing Page.");
                    SeleniumLib.takeAScreenShot("removeButtonInfo.jpg");
                    return false;
                }
            }

        }catch(Exception exp){
            Debugger.println("Exception in  Verifying Patient Identifier Information in FamilyMember Landing Page.");
            return false;
        }
        return true;

    }
    public boolean participantsErrorMessageCheck(String expectedMsg, String expectedColor) {
        try {
            String actualMessage = oneParticipantMsg.getText();
            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(expectedColor);
            String actColor = oneParticipantMsg.getCssValue("color");
            if (expectedMsg.equalsIgnoreCase(actualMessage)) {
                Debugger.println("Expected field: " + expectedMsg + ", and Actual field: " + actualMessage);
                if (!expectedFontColor.equalsIgnoreCase(actColor)) {
                    Debugger.println("Expected Color: " + expectedFontColor + ", and Actual Color: " + actColor);
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from checking participant addition message field " + exp);
            return false;
        }
    }

    public boolean participantsWarningMessageCheck(String expectedMsg, String expectedColor) {
        try {
            String actualMessage = multipleParticipantMsg.getText();
            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(expectedColor);
            String actColor = multipleParticipantMsg.getCssValue("color");
            if (expectedMsg.equalsIgnoreCase(actualMessage)) {
                Debugger.println("Expected field: " + expectedMsg + ", and Actual field:" + actualMessage);
                if (!expectedFontColor.equalsIgnoreCase(actColor)) {
                    Debugger.println("Expected Color: " + expectedFontColor + ", and Actual Color: " + actColor);
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from checking participant addition message field " + exp);
            return false;
        }
    }
    public void clickOnParticipantAmendmentLink() {
        try {
            seleniumLib.clickOnWebElement(participantAmendmentLink);
            if (helix.size() > 0) {
                Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
            }
        } catch (Exception exp) {
            Debugger.println("Could not click on participantAmendmentLink on FamilyDetailsPage: " + exp);
        }
    }
    public void clickOnContinueButton() {
        Wait.forElementToBeDisplayed(driver, continueButton);
        continueButton.click();
    }
    public void patientChoiceStatus(String status) {
        Wait.forElementToBeDisplayed(driver, familyMemberLandingPageTitle);
        try {
            Assert.assertEquals(status, patientChoiceStatusResult.getText());
        } catch (Exception exp) {
            Debugger.println("[Expected status :" + status + "][Actual status :" + patientChoiceStatusResult.getText() + "]" + exp);
        }
    }
    public boolean verifyFamilyMemberBanner() {
        Wait.forElementToBeDisplayed(driver, familyMemberFocussed);
        if (!seleniumLib.isElementPresent(familyMemberFocussed)) {
            return false;
        }
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(familyMemberNames);
        expElements.add(relationshipStatus);
        expElements.add(familyMemberDob);
        expElements.add(familyMemberGender);
        expElements.add(familyMemberNhsNumbers);
        expElements.add(familyMemberNgisId);
        for (int i = 0; i < expElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expElements.get(i))) {
                return false;
            }
        }
        return true;
    }

   public boolean patientChoiceStatusDetail(){
        Wait.forElementToBeDisplayed(driver,patientChoiceStatusField);
        if(!Wait.isElementDisplayed(driver,patientChoiceStatusField,10)){
            return false;
        }
        return true;
    }

   public void subTitleMessage(String subTitlemsg){
        familyMemberTestPackagePageSubTitle.isDisplayed();
        String actualMessage1 = familyMemberTestPackagePageSubTitle.getText();
        String actualMessage2 = familyMemberTestPackagePageSubTitle2.getText();
        String actualMessage=actualMessage1+actualMessage2;
        Assert.assertEquals(subTitlemsg,actualMessage);

    }

    public boolean addFamilyMemberAndContinueButtonIsDisplayed() {
        if (!seleniumLib.isElementPresent(addFamilyMemberButton) && !seleniumLib.isElementPresent(continueButton)) {
            return false;
        }
        return true;
    }

    public void patientDetailsInFamilyMemberLandingPage() {
        Wait.forElementToBeDisplayed(driver, familyMemberLandingPageTitle);
        familyMemberLandingPageDetails = new ArrayList<String>();
        try {
            for (int i = 0; i < nameResults.size(); i++) {
                familyMemberLandingPageDetails.add(nameResults.get(i).getText());
                familyMemberLandingPageDetails.add(nhsNumberResults.get(i).getText());
                familyMemberLandingPageDetails.add(genderResults.get(i).getText());
                familyMemberLandingPageDetails.add(ngisIdResults.get(i).getText());
            }
        } catch (Exception exp) {
            Debugger.println("Exception from getting result");
        }
    }


        public boolean verifyDataFromFamilyMemberAndPatientChoice() {
        if (familyMemberLandingPageDetails.equals(patientChoicePageDetails)) {
            return true;
        }
        return false;
    }
    public void printFormsInPatientChoicePage() {
        Wait.forElementToBeDisplayed(driver, printFormsPageTitle);
        printFormsPageDetails = new ArrayList<String>();
        try {
            for (int i = 0; i < nameResults.size(); i++) {
                printFormsPageDetails.add(nameResults.get(i).getText());
                printFormsPageDetails.add(nhsNumberResults.get(i).getText());
                printFormsPageDetails.add(genderResults.get(i).getText());
                printFormsPageDetails.add(ngisIdResults.get(i).getText());
            }
        } catch (Exception exp) {
            Debugger.println("Exception from print forms page getting result");
        }
    }
    public void patientDetailsInPatientChoicePage() {
        Wait.forElementToBeDisplayed(driver, patientChoicePageTitle);
        patientChoicePageDetails = new ArrayList<String>();
        try {
            for (int i = 0; i < nameResults.size(); i++) {
                patientChoicePageDetails.add(nameResults.get(i).getText());
                patientChoicePageDetails.add(nhsNumberResults.get(i).getText());
                patientChoicePageDetails.add(genderResults.get(i).getText());
                patientChoicePageDetails.add(ngisIdResults.get(i).getText());
            }
        } catch (Exception exp) {
            Debugger.println("Exception from patient page getting result");
        }
    }
    public boolean verifyDataFromFamilyMemberAndPrintForms() {
        if (familyMemberLandingPageDetails.equals(printFormsPageDetails)) {
            return true;
        }
        return false;
    }
    public boolean testedFieldColor(String testfield, String color) {
        try {
            Wait.seconds(5);
            String actualMessage1 = Actions.getText(familyPageBeingTestedField);
            String actualMessage2 = Actions.getText(familyPageNotBeingTestedField);

            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(color);
            String actColor1 = familyPageBeingTestedField.getCssValue("background-color");
            String actColor2 = familyPageNotBeingTestedField.getCssValue("background-color");

            if (testfield.equalsIgnoreCase(actualMessage1)) {
                Debugger.println("Expected field: " + testfield + ", and Actual field: " + actualMessage1);
                if (!expectedFontColor.equalsIgnoreCase(actColor1)) {
                    Debugger.println("Expected Color: " + expectedFontColor + ", and Actual Color: " + actColor1);
                    return false;
                }

            }
            else if(testfield.equalsIgnoreCase(actualMessage2)){
                Debugger.println("Expected field: " + testfield +", and Actual field:" +actualMessage2);
                if (!expectedFontColor.equalsIgnoreCase(actColor2)) {
                    Debugger.println("Expected Color: " + expectedFontColor + ", and Actual Color: " + actColor2);
                    return false;
                }
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from checking being tested field "+exp);
            return false;
        }
    }
    public boolean verifyDeselectedPatientTestStatus(String status) {
        try {
            WebElement actualResult = driver.findElement(By.xpath("//h2[contains(text(),'"+memberName+"')]/following::span[contains(@class,'child-element')][2]"));
            if(!status.equalsIgnoreCase(actualResult.getText())){
                return false;
            }
            return true;
        }catch (Exception exp){
            Debugger.println("FamilyMemberDetailsPage:verifyDeselectedPatientTestStatus:Exception:" + exp);
        }
        return false;
    }
    public boolean verifyPatientIdentifiersInFamilyMemberLandingPage() {
        try{
            Wait.forElementToBeDisplayed(driver, familyMemberLandingPageTitle);
            //Validation of Core Information Presence
            int noOfPatients = 0;
            List<WebElement> patientLists = seleniumLib.getElements(By.xpath(patientList));
            if (patientLists != null) {
                noOfPatients = patientLists.size();
            }

            if (noOfPatients == 0) {
                Debugger.println("No Patients Information Present in FamilyMember Landing Page.");
                SeleniumLib.takeAScreenShot("NoPatientList.jpg");
                return false;
            }
            noOfPatientsForIdentification = noOfPatients;
            Wait.seconds(2);
            Debugger.println("Validating Information of " + noOfPatients + " Patients in Family Member Landing Page.");
            List<WebElement> nameList = seleniumLib.getElements(By.xpath(firstNameLastName));
            if (nameList == null || nameList.size() != noOfPatients) {
                Debugger.println("Expected Presence of First/Last Name field for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("firstLastNameLst.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> probandTestedList = seleniumLib.getElements(By.xpath(probandBeingTested));
            if (probandTestedList == null || probandTestedList.size() != (noOfPatients*2)) {
                Debugger.println("Expected Presence of Proband and Being Tested Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("probandTested.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> bornList = seleniumLib.getElements(By.xpath(bornInformation));
            if (bornList == null || bornList.size() != noOfPatients) {
                Debugger.println("Expected Presence of Born Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("bornInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> genderList = seleniumLib.getElements(By.xpath(genderInformation));
            if (genderList == null || genderList.size() != noOfPatients) {
                Debugger.println("Expected Presence of Gender Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("genderInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> nhsList = seleniumLib.getElements(By.xpath(nhsNumberInformation));
            if (nhsList == null || nhsList.size() != noOfPatients) {
                Debugger.println("Expected Presence of NHS Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("nhsInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> ngisList = seleniumLib.getElements(By.xpath(ngsIdInformation));
            if (ngisList == null || ngisList.size() != noOfPatients) {
                Debugger.println("Expected Presence of NGSID Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("ngsInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> pchoiceList = seleniumLib.getElements(By.xpath(patientChoiceInformation));
            if (pchoiceList == null || pchoiceList.size() != noOfPatients) {
                Debugger.println("Expected Presence of PatientChoice Information for " + noOfPatients + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("pchoiceInfo.jpg");
                return false;
            }
            //EDIT and REMOVE BUTTON
            Wait.seconds(2);
            List<WebElement> editButtonList = seleniumLib.getElements(By.xpath(editButtonInformation));
            if (editButtonList != null){
                if(editButtonList.size() != (noOfPatients-1)) {
                    Debugger.println("Expected Presence of Edit Information for " + (noOfPatients - 1) + " patients in  Family Member Landing Page.");
                    SeleniumLib.takeAScreenShot("editButtonInfo.jpg");
                    return false;
                }
            }
            Wait.seconds(2);
            List<WebElement> removeButtonList = seleniumLib.getElements(By.xpath(removeButtonInformation));
            if (removeButtonList != null) {
                if (removeButtonList.size() != (noOfPatients - 1)) {
                    Debugger.println("Expected Presence of Remove Information for " + (noOfPatients - 1) + " patients in  Family Member Landing Page.");
                    SeleniumLib.takeAScreenShot("removeButtonInfo.jpg");
                    return false;
                }
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in  Verifying Patient Identifier Information in FamilyMember Landing Page.");
            return false;
        }

    }
    public boolean editAndDeleteButtonDisplay() {
        for (int i = 0; i < editButton.size(); i++) {
            if (!seleniumLib.isElementPresent(editButton.get(i)) && !seleniumLib.isElementPresent(deleteButton.get(i))) {
                return false;
            }
        }
        return true;
    }
    public void editPatientChoiceOfFamilyMember() {
        try {
            for (int i = 1; i < editButton.size(); i++) {
                editButton.get(i).click();
                Wait.seconds(5);
                break;//Click on any one should be enough.
            }
        } catch (Exception exp) {
            Debugger.println("FamilyMemberDetailsPage editPatientChoiceOfFamilyMember(), EditBox not present" + exp);
        }
    }
}//ends
