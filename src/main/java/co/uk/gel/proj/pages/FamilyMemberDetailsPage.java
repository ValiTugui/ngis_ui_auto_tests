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

    @FindBy(xpath = "//h2[contains(@class,'css')]/following::button[2]")

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

    @FindBy(xpath = "//div[contains(@id,'question-id-q96')]")
    public WebElement diseaseStatusDropdown;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-years')]")
    public WebElement ageOfOnsetYearsField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-months')]")
    public WebElement ageOfOnsetMonthsField;

    @FindBy(xpath = "//label[contains(text(),'Find an HPO phenotype or code')]/..//input")
    public WebElement hpoSearchField;

    @FindBy(xpath = "//button[contains(text(),'Add new patient to referral')]")
    public WebElement AddReferralButton;

    @FindBy(css = "div[id*='react-select']")
    public List<WebElement> dropdownValues;

    @FindBy(xpath = "//div[contains(@class,'test-list')]//span[contains(@class,'checked')]")
    WebElement testPackageCheckBoxChecked;

    @FindBy(xpath = "//div[contains(@class,'test-list')]//span[contains(@class,'checkbox')]")
    WebElement testPackageCheckBox;

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

    @FindBy(xpath = "//div[contains(@class,'css')]/following::h2[contains(@class,'css')]")
    public WebElement additionalFamilyMemberName;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@class,'child-element')][1]")
    public WebElement relationField;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@class,'child-element')][2]")
    public WebElement beingTestedField;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@id,'dateOfBirth')]")
    public WebElement dobField;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@id,'gender')]")
    public WebElement genderField;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@id,'patientChoiceStatus')]")
    public WebElement patientChoiceStatus;

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

    static ArrayList<NGISPatientModel> addedFamilyMembers = new ArrayList<NGISPatientModel>();
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

    @FindBy(xpath = "//div[contains(@class,'styles_site-panel')]/following::span[contains(@class,'203Ot')]")
    public WebElement familyMemberTestPackagePageSubTitle2;

    @FindBy(xpath = "//span[contains(text(),'Being tested')]/ancestor::span[contains(@class,'css-1')][1]")
    public WebElement familyPageBeingTestedField;

    @FindBy(xpath = "//span[contains(text(),'Not being tested')]/ancestor::span[contains(@class,'css-')][1]")
    public WebElement familyPageNotBeingTestedField;

    @FindBy(xpath = "//h2[contains(@class,'css-')]")
    public WebElement nameField;

    @FindBy(xpath = "//div[@class='styles_error-message__text__1v2Kl']")
    public WebElement oneParticipantMsg;
    @FindBy(xpath = "//div[@class='styles_test-list-item__info-message__2RWQ9']")
    public WebElement multipleParticipantMsg;

    static String memberName;

    //For PatientInformation Identifiers
    public static int noOfPatientsForIdentification = 0;
    String patientList = "//div[contains(@class,'styles_participant-list_')]/div[contains(@class,'css-1')]";
    String firstNameLastName = "//div[contains(@class,'styles_participant-list_')]//span[contains(@class,'css-')]//h2";
    String probandBeingTested = "//span[contains(@class,'child-element')]";
    String bornInformation = "//span[contains(@id,'dateOfBirth')]";
    String genderInformation = "//span[contains(@id,'gender')]";
    String nhsNumberInformation = "//span[contains(@id,'nhsNumber')]";
    String ngsIdInformation = "//span[contains(@id,'ngisId')]";
    String patientChoiceInformation = "//span[contains(@id,'patientChoiceStatus')]";
    String editButtonInformation = "//button[@aria-label='edit button']";
    String removeButtonInformation = "//button[@aria-label='remove button']";

    @FindBy(xpath = "//div[@class='styles_participant-info__4Bpvb']")
    public WebElement familyMemberFocussed;

    @FindBy(xpath = "//h2[contains(@class,'css-')]")
    public WebElement familyMemberNames;

    @FindBy(xpath = "//span[@class='child-element']")
    public WebElement relationshipStatus;
    @FindBy(xpath = "//span[text()='Born']/following::span[contains(@aria-labelledby,'dateOfBirth')]")
    public WebElement familyMemberDob;
    @FindBy(xpath = "//span[text()='Gender']/following::span[contains(@aria-labelledby,'gender')]")
    public WebElement familyMemberGender;
    @FindBy(xpath = "//span[text()='NHS No.']/following::span[contains(@aria-labelledby,'nhsNumber')]")
    public WebElement familyMemberNhsNumbers;
    @FindBy(xpath = "//span[text()='Patient NGIS ID']/following::span[contains(@aria-labelledby,'ngisId')]")
    public WebElement familyMemberNgisId;

    String specificFamilyEdit = "//ul//span[text()='NHSLastFour']/ancestor::div[contains(@class,'css-1')]//button";
    @FindBy(className = "todo-list")
    public WebElement toDoList;
    String stageIsToDo = "a[href*='" + "dummyStage" + "']";

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

    public boolean verifyPatientRecordDetailsDisplay(String relationToProband) {
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
        NGISPatientModel familyMember = new NGISPatientModel();
        try {
            String bornText = patientCardBorn.getText();
            bornText = bornText.substring(0, bornText.indexOf("("));
            bornText = bornText.replaceAll("Born", "");
            familyMember.setBORN_DATE(bornText.trim());
        } catch (Exception exp) {
            Debugger.println("Error in reading born details of added family member.");
        }
        if (!seleniumLib.isElementPresent(patientCardGender)) {
            Debugger.println("Patient Gender not displayed in Search Result.");
            return false;
        }
        String genderText = patientCardGender.getText();
        familyMember.setGENDER(genderText.replaceAll("Gender", "").trim());
        if (!seleniumLib.isElementPresent(patientCardNHSNo)) {
            Debugger.println("Patient NHS number not displayed in Search Result.");
            return false;
        }
        String nhsNoText = patientCardNHSNo.getText();
        familyMember.setNHS_NUMBER(nhsNoText.replaceAll("NHS No.", "").trim());
        if (relationToProband != null) {
            familyMember.setRELATIONSHIP_TO_PROBAND(relationToProband);
        }
        addedFamilyMembers.add(familyMember);//This to verify the details later.
        Debugger.println("Family Member: " + familyMember.getNHS_NUMBER() + " added to the list.");
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

    public void fillTheRelationshipToProband(String relationToProband, String nhsDetails) {
        if (!nhsDetails.isEmpty()) {
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(nhsDetails);
            Set<String> paramsKey = paramNameValue.keySet();
            String nhsNumber = "";
            for (String key : paramsKey) {
                if (key.equalsIgnoreCase("NHSNumber")) {
                    nhsNumber = paramNameValue.get(key);
                    break;
                }
            }
            NGISPatientModel familyMember = getFamilyMember(nhsNumber);
            if (familyMember != null) {
                familyMember.setFIRST_NAME(seleniumLib.getAttributeValue(firstName, "value"));
                familyMember.setLAST_NAME(seleniumLib.getAttributeValue(lastName, "value"));
                updateFirstLastName(familyMember);
            }
        }
        validationErrors.clear();
        if (!Wait.isElementDisplayed(driver, relationshipToProbandDropdown, 60)) {
            Debugger.println("FamilyMemberDetailsPage:relationshipToProbandDropdown element not displayed even after waiting period.");
        }
        seleniumLib.clickOnWebElement(relationshipToProbandDropdown);
        Wait.seconds(2);
        By ddElement = By.xpath("//span[text()='" + relationToProband + "']");
        if (seleniumLib.isElementPresent(ddElement)) {
            seleniumLib.clickOnWebElement(dropdownValue.findElement(ddElement));
        } else {
            seleniumLib.clickOnWebElement(relationshipToProbandDropdown);
            Wait.seconds(2);
            if (!seleniumLib.isElementPresent(ddElement)) {
                Debugger.println("FamilyMemberDetailsPage:relationshipToProbandDropdown value: " + relationToProband + " not present in drop down.");
                return;
            }
            seleniumLib.clickOnWebElement(dropdownValue.findElement(ddElement));
        }
        Wait.seconds(2);
        if (validationErrors.size() > 0) {
            Debugger.println("FamilyMemberDetailsPage:Error in selecting second time Proband drop down pag:." + validationErrors.get(0).getText());
        }
    }

    public boolean verifyTheTestAndDetailsOfAddedFamilyMember(String nhsDetails) {

        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(nhsDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        String nhsNumber = "";
        for (String key : paramsKey) {
            if (key.equalsIgnoreCase("NHSNumber")) {
                nhsNumber = paramNameValue.get(key);
                break;
            }
        }
        NGISPatientModel familyMember = getFamilyMember(nhsNumber);
        if (familyMember == null) {
            Debugger.println("Family Member with NHS Number:" + nhsNumber + " Not found in the added list!.");
            return false;
        }
        //1. Verify the display of Title for the added Test.
        By testTitle = By.xpath(selectedTestTitle + "'" + familyMember.getRELATIONSHIP_TO_PROBAND() + "')]");
        if (!seleniumLib.isElementPresent(testTitle)) {
            Debugger.println("Selected Test Title for Family member with Relation " + familyMember.getRELATIONSHIP_TO_PROBAND() + " not displayed." + testTitle);
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
                Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + paramNameValue.get("DiseaseStatus") + "']")));
            } catch (Exception exp) {
                Debugger.println("Exception from selecting disease from the disease dropdown...:" + exp);
                SeleniumLib.takeAScreenShot("DiseaseDropDown.jpg");
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
            Debugger.println("Verifying HPO already exists or not...." + hpoTerm + " in " + rows.size() + " rows.");
            for (WebElement row : rows) {
                hpoValue = row.findElement(By.xpath("./td[1]")).getText();
                Debugger.println("ExistingHPO.....:" + hpoValue);
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
            Debugger.println("Selecting Specified HPOPhenotype: " + hpoTerm);
            Actions.scrollToTop(driver);
            if (Wait.isElementDisplayed(driver, hpoSearchField, 30)) {
                seleniumLib.clickOnWebElement(hpoSearchField);
                seleniumLib.sendValue(hpoSearchField, hpoTerm);
            } else {
                //Wait for 30 more seconds
                if (Wait.isElementDisplayed(driver, hpoSearchField, 30)) {
                    seleniumLib.clickOnWebElement(hpoSearchField);
                    seleniumLib.sendValue(hpoSearchField, hpoTerm);
                } else {
                    Debugger.println("HpoSearch field has not visible...failing the test.");
                    SeleniumLib.takeAScreenShot("hpoSearchFiledNotVisible.jpg");
                    //Assert.assertFalse("HpoSearch field has not visible...failing the test.",true);
                }
            }
            By ddValue = By.xpath("//span[text()='" + hpoTerm + "']");
            if (seleniumLib.isElementPresent(ddValue)) {
                seleniumLib.clickOnElement(ddValue);
            } else {
                Debugger.println("Phenotype " + hpoTerm + " not present...failing.");
                SeleniumLib.takeAScreenShot("PhenotypeNotPresent.jpg");
                //Assert.assertFalse("Phenotype "+hpoTerm+"not visible...failing the test.",true);
            }

        } catch (Exception exp) {
            Debugger.println("Exception from searchAndSelectSpecificHPOPhenotype: " + exp);
            SeleniumLib.takeAScreenShot("SpecificPhenoType.jpg");
        }
    }

    public boolean verifyAddedFamilyMemberDetailsInLandingPage(String nhsDetails) {

        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(nhsDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        String nhsNumber = "";
        for (String key : paramsKey) {
            if (key.equalsIgnoreCase("NHSNumber")) {
                nhsNumber = paramNameValue.get(key);
                break;
            }
        }
        NGISPatientModel familyMember = getFamilyMember(nhsNumber);
        if (familyMember == null) {
            Debugger.println("Family Member with NHS Number:" + nhsNumber + " Not added to the list!.");
            return false;
        }
        //1. Verify the display of Title for the added Test.
        By firstNameLastName = By.xpath(addFamilyMemberTitle + "'" + familyMember.getLAST_NAME() + ", " + familyMember.getFIRST_NAME() + "')]");
        if (!seleniumLib.isElementPresent(firstNameLastName)) {
            Debugger.println("Selected Family member not displayed in Landing Page: " + familyMember.getFIRST_NAME() + ", " + familyMember.getFIRST_NAME());
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
            NGISPatientModel familyMember = getFamilyMember("");
            Debugger.println("Verifying TheTestCheckboxIsSelected for: " + familyMember.getFIRST_NAME() + "," + familyMember.getRELATIONSHIP_TO_PROBAND());
            Wait.forElementToBeDisplayed(driver, testPackageCheckBoxChecked, 60);
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
            if (Wait.isElementDisplayed(driver, testPackageCheckBoxChecked, 10)) {
                seleniumLib.clickOnWebElement(testPackageCheckBoxChecked);
                memberName = nameField.getText();
                Debugger.println("Deselected the Test for :" + memberName);
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
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("BackButtonOnFMDetails.jpg");
            Debugger.println("Could not click on Back Button on FamilyDetailsPage: " + exp);
        }
    }

    public boolean verifyTheEditingReferralColor(String nhsDetails, String eColor) {
        try {
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(nhsDetails);
            Set<String> paramsKey = paramNameValue.keySet();
            String nhsNumber = "";
            for (String key : paramsKey) {
                if (key.equalsIgnoreCase("NHSNumber")) {
                    nhsNumber = paramNameValue.get(key);
                    break;
                }
            }
            NGISPatientModel familyMember = getFamilyMember(nhsNumber);
            if (familyMember == null) {
                Debugger.println("Family Member with NHS:" + nhsNumber + " Not present in the added list.");
                return false;
            }
            Debugger.println("Verifying color of editing referral member: " + familyMember.getLAST_NAME() + "," + familyMember.getFIRST_NAME());
            By firstNameLastName = By.xpath(addFamilyMemberTitle + "'" + familyMember.getLAST_NAME() + ", " + familyMember.getFIRST_NAME() + "')]");
            WebElement webElement_FN = driver.findElement(firstNameLastName);
            if (!Wait.isElementDisplayed(driver, webElement_FN, 30)) {
                Debugger.println("Editing Member Details: " + firstNameLastName.toString() + " not found in Landing Page.");
                return false;
            }
            String actualColor = webElement_FN.getCssValue("color");
            String expColor = StylesUtils.convertFontColourStringToCSSProperty(eColor);
            if (!expColor.equalsIgnoreCase(actualColor)) {
                Debugger.println("Expected Color of editing family member is: " + expColor + ",But actual Color displayed is:" + actualColor);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying color of editing family member." + exp);
            return false;
        }
    }

    public boolean editSpecificFamilyMember(String familyDetails) {
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
            String nhsLastFour = nhsNumber.substring(6, nhsNumber.length());//Assuming NHSNumber is always 10 digit.

            By familyEdit = By.xpath(specificFamilyEdit.replaceAll("NHSLastFour", nhsLastFour));
            WebElement element = driver.findElement(familyEdit);
            if (Wait.isElementDisplayed(driver, element, 100)) {
                seleniumLib.clickOnWebElement(element);
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on edit button for family with NHSNumber:" + exp);
            return false;
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

        for (int i = 0; i < expElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expElements.get(i))) {
                return false;
            }
        }
        return true;
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
        try {
            Wait.forElementToBeDisplayed(driver, successDeletionMessageOfFamilyMember);
            Assert.assertEquals(deleteMessage, successDeletionMessageOfFamilyMember.getText());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying the Family Member Removal Message: " + exp);
            return false;
        }
    }

    public boolean verifyTheDeleteMessageIsPresent() {
        if (seleniumLib.isElementPresent(successDeletionMessageOfFamilyMember)) {
            return false;
        }
        return true;
    }

    public void deselectCheckBoxOnFamilyPage() {
        Wait.forElementToBeDisplayed(driver, testPackageCheckBox);
        seleniumLib.clickOnWebElement(testPackageCheckBox);
    }

    public boolean unmatchedParticipantErrorMessage(String expMessage) {
        try {
            Wait.forElementToBeDisplayed(driver, unmatchedParticipantMessage, 20);
            if (!seleniumLib.isElementPresent(unmatchedParticipantMessage)) {
                Debugger.println("Expected Unmatched Participant Error not displayed.");
                return false;
            }
            String actMessage = Actions.getText(unmatchedParticipantMessage);
            if (!actMessage.contains(expMessage)) {
                Debugger.println("Actual Message: " + actMessage + "\n DOES NOT CONTAINS Expected Message:" + expMessage);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying unmatchedParticipantErrorMessage:" + exp);
            return false;
        }
    }

    public void clicksOnParticipantAmendmentLink() {
        if (!seleniumLib.isElementPresent(participantAmendmentLink)) {
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

    public void clickOnSaveAndContinueButton() {
        if (Wait.isElementDisplayed(driver, saveAndContinueButton, 10)) {
            seleniumLib.clickOnWebElement(saveAndContinueButton);
            if (helix.size() > 0) {
                Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
            }
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

    public boolean verifyTheElementsOnFamilyMemberPage() {
        try {
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
            if (probandTestedList == null || probandTestedList.size() != (noOfPatients * 2)) {
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
            if (editButtonList != null) {
                if (editButtonList.size() != (noOfPatients - 1)) {
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

        } catch (Exception exp) {
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

    public boolean patientChoiceStatusDetail() {
        Wait.forElementToBeDisplayed(driver, patientChoiceStatusField);
        if (!Wait.isElementDisplayed(driver, patientChoiceStatusField, 10)) {
            return false;
        }
        return true;
    }

    public void subTitleMessage(String subTitlemsg) {
        familyMemberTestPackagePageSubTitle.isDisplayed();
        String actualMessage1 = familyMemberTestPackagePageSubTitle.getText();
        String actualMessage2 = familyMemberTestPackagePageSubTitle2.getText();
        String actualMessage = actualMessage1 + actualMessage2;
        Assert.assertEquals(subTitlemsg, actualMessage);

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
            Wait.forElementToBeDisplayed(driver, familyPageBeingTestedField);
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

            } else if (testfield.equalsIgnoreCase(actualMessage2)) {
                Debugger.println("Expected field: " + testfield + ", and Actual field:" + actualMessage2);
                if (!expectedFontColor.equalsIgnoreCase(actColor2)) {
                    Debugger.println("Expected Color: " + expectedFontColor + ", and Actual Color: " + actColor2);
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from checking being tested field " + exp);
            return false;
        }
    }

    public boolean verifyDeselectedPatientTestStatus(String status) {
        try {
            WebElement actualResult = driver.findElement(By.xpath("//h2[contains(text(),'" + memberName + "')]/following::span[contains(@class,'child-element')][2]"));
            if (!status.equalsIgnoreCase(actualResult.getText())) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("FamilyMemberDetailsPage:verifyDeselectedPatientTestStatus:Exception:" + exp);
        }
        return false;
    }

    public boolean verifyPatientIdentifiersInFamilyMemberLandingPage() {
        try {
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
            if (probandTestedList == null || probandTestedList.size() != (noOfPatients * 2)) {
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
            if (editButtonList != null) {
                if (editButtonList.size() != (noOfPatients - 1)) {
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
        } catch (Exception exp) {
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

    public boolean verifyMaxAllowedValuesInRelationshipToProbandField(int maxAllowedValues) {
        try {
            if (!Wait.isElementDisplayed(driver, relationshipToProbandDropdown, 30)) {
                Debugger.println("FamilyMemberDetailsPage:relationshipToProbandDropdown element not displayed even after waiting period.");
                Wait.seconds(20);
            }
            seleniumLib.clickOnWebElement(relationshipToProbandDropdown);
            Wait.seconds(2);
            By ddElement = By.xpath("//span[text()='" + "12" + "']");
            int i = 0;
            int numberOfElements = dropdownValues.size();
            for (WebElement element : dropdownValues) {
                Debugger.println(" Relationship to proband value : " + ++i + " : " + element.getText());
            }
            Debugger.println(" Number of items displayed in Relationship to proband  Field  : " + numberOfElements);
            return numberOfElements <= maxAllowedValues;
        } catch (Exception exp) {
            Debugger.println("Oops unable to locate drop-down element value -> " + exp);
            return false;

        }
    }

    public static NGISPatientModel getFamilyMember(String nhsNumber) {
        try {
            if (nhsNumber == null || nhsNumber.isEmpty()) {
                return addedFamilyMembers.get(0);
            }
            for (int i = 0; i < addedFamilyMembers.size(); i++) {
                if (addedFamilyMembers.get(i).getNHS_NUMBER().equalsIgnoreCase(nhsNumber)) {
                    return addedFamilyMembers.get(i);
                }
            }
            Debugger.println("COULD NOT find Family Member for :" + nhsNumber);
            return null;
        } catch (Exception exp) {
            Debugger.println("Family Member with NHSNumber:" + nhsNumber + " Not found in the added list.");
            return null;
        }
    }

    public void updateFirstLastName(NGISPatientModel familyMember) {
        try {
            for (int i = 0; i < addedFamilyMembers.size(); i++) {
                if (addedFamilyMembers.get(i).getNHS_NUMBER().equalsIgnoreCase(familyMember.getNHS_NUMBER())) {
                    addedFamilyMembers.get(i).setFIRST_NAME(familyMember.getFIRST_NAME());
                    addedFamilyMembers.get(i).setLAST_NAME(familyMember.getLAST_NAME());
                    Debugger.println("Set First and Last name of Familymember: " + familyMember.getNHS_NUMBER() + "as " + familyMember.getFIRST_NAME() + " and " + familyMember.getLAST_NAME());
                }
            }
        } catch (Exception exp) {
            Debugger.println("Exception in setting up First Last Name for FamilyMember " + exp);
        }
    }

    public String getPartialUrl(String stage) {
        String partialUrl = null;
        HashMap<String, String> partialUrls = new HashMap<String, String>();
        partialUrls.put("Patient details", "patient-details");
        partialUrls.put("Requesting organisation", "ordering-entity");
        partialUrls.put("Test package", "test-package");
        partialUrls.put("Responsible clinician", "clinical-details");
        partialUrls.put("Clinical questions", "clinical-questions");
        partialUrls.put("Notes", "notes");
        partialUrls.put("Print forms", "downloads");
        partialUrls.put("Family members", "family-members");
        partialUrls.put("Tumours", "tumours");
        partialUrls.put("Samples", "samples");
        partialUrls.put("Panels", "panels");
        partialUrls.put("Patient choice", "patient-choice");
        partialUrls.put("Pedigree", "pedigree");
        if (partialUrls.containsKey(stage)) {
            partialUrl = partialUrls.get(stage);
        }
        return partialUrl;
    }

    public void navigateToStage(String stage) {
        Wait.forElementToBeDisplayed(driver, toDoList, 100);
        String webElementLocator = stageIsToDo.replace("dummyStage", getPartialUrl(stage));
        WebElement referralStage = toDoList.findElement(By.cssSelector(webElementLocator));
        Wait.forElementToBeDisplayed(driver, referralStage);
        try {
            Actions.clickElement(driver, referralStage);
            seleniumLib.clickOnWebElement(referralStage);
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("navigateToStage.jpg");
            //Sometimes click on stage link on second time gives ElementClickInterceptedException.
            // Below code added to handle that.
            Actions.scrollToTop(driver);
            Actions.clickElement(driver, referralStage);
        }

    }
}//ends
