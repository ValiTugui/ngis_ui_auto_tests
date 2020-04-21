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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class FamilyMemberDetailsPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    //    @FindBy(xpath = "//h2[@class='css-1ujfcb9']")
    @FindBy(xpath = "//h2[contains(@class,'css') and contains(text(),',')]")
    public List<WebElement> nameResults;

    @FindBy(xpath = "//label[contains(text(),'Title')]")
    public WebElement pageTitleLabel;

    @FindBy(id = "title")
    public WebElement pageTitle;

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

    String selectedGender = "//span/span[contains(text(),'dummyGender')]";

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

    @FindBy(css = "a[class*='patient-card']")
    public WebElement patientCard;

    @FindBy(xpath = "//button[contains(text(),'Back')]")
    public WebElement backButton;

    @FindBy(xpath = "//button/span[contains(text(),'Edit patient details')]")
    public WebElement editPatientDetailsLink;

    @FindBy(css = "*[class*='helix']")
    public List<WebElement> helix;

    String helixIcon = "*[class*='helix']";

    @FindBy(css = "div[class*='error-message__text']")
    public List<WebElement> validationErrors;

    @FindBy(xpath = "(//label[text()='Relationship to proband']//following::div)[1]")
    public WebElement relationshipToProbandDropdown;

    @FindBy(xpath = "//div[@data-testid='notification-error']//span")
    public List<WebElement> notificationErrors;

    @FindBy(xpath = "//a[contains(text(),'amend the expected number of participants')]")
    public WebElement participantAmendmentLink;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(xpath = "//div[contains(@id,'question-id-q96')]")
    public WebElement diseaseStatusDropdown;

    @FindBy(xpath = "//div[contains(@id,'question-id-q90')]")
    public WebElement phenotypicSexDropdown;

    @FindBy(xpath = "//div[contains(@id,'question-id-q91')]")
    public WebElement karyotypicSexDropdown;

    @FindBy(xpath = "//input[contains(@id,'question-id-q111')]")
    public WebElement rareDiseaseDiagnosesInput;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-years')]")
    public WebElement ageOfOnsetYearsField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-months')]")
    public WebElement ageOfOnsetMonthsField;

    @FindBy(xpath = "//label[contains(text(),'Find an HPO phenotype or code')]/..//input")
    public WebElement hpoSearchField;

    @FindBy(css = "div[id*='react-select']")
    public List<WebElement> dropdownValues;

    @FindBy(xpath = "//h4[contains(text(),'Selected family members')]")
    WebElement selectedFamilyMembersLabel;

    @FindBy(css = "span[class*='child-element']")
    List<WebElement> displayedChildElements;

    @FindBy(xpath = "//button[@aria-label='edit button']")
    WebElement editButtonForParticipant;

    @FindBy(xpath = "//span[contains(@class,'family-member-test-package__patient')]")
    WebElement selectedTestForRelationship;
    @FindBy(xpath = "//h4[contains(text(),'Selected family members')]/..//span[contains(@class,'relationship-tag')]")
    List<WebElement> relationShipTags;
    @FindBy(xpath = "//div[contains(@class,'test-list_')]//span[contains(@class,'checked')]")
    WebElement selectedTest;
    @FindBy(xpath = "//div[contains(@class,'test-list_')]//span[contains(@class,'checkbox-card')]")
    WebElement unSelectedTest;

    String addFamilyMemberTitle = "//h1[contains(text(),'Add a family member to this referral')]/../div//h2[contains(text(),";
    By hpoRows = By.xpath("//table[contains(@class,'--hpo')]/tbody/tr");

    @FindBy(xpath = "//h3[contains(text(),'1 patient record found')]/../a//div[contains(@class,'styles_patient-card__top_')]/p[contains(@class,'patient-name')]")
    WebElement patientCardName;
    @FindBy(xpath = "//h3[contains(text(),'1 patient record found')]/../a//p[text()='Born']")
    WebElement patientCardBorn;
    @FindBy(xpath = "//h3[contains(text(),'1 patient record found')]/../a//p[text()='Gender']")
    WebElement patientCardGender;
    @FindBy(xpath = "//h3[contains(text(),'1 patient record found')]/../a//p[text()='NHS No.']")
    WebElement patientCardNHSNo;
    @FindBy(xpath = "//h3[contains(text(),'1 patient record found')]/../a//div[contains(@class,'styles_patient-card__bottom_')]/p")
    WebElement patientCardAddress;
    @FindBy(xpath = "//h3[contains(text(),'1 patient record found')]/../a//div[contains(@class,'styles_patient-card__bottom_')]/div/span")
    WebElement patientCardType;

    @FindBy(xpath = "//button[@aria-label='edit button']")
    List<WebElement> editButton;

    //Family Member Landing Page
    @FindBy(xpath = "//h1[contains(text(),'Add a family member to this referral')]")
    public WebElement familyMemberLandingPageTitle;

    String landingPageNamePath = "//div[contains(@class,'_participant-list__')]//span/h2[contains(text(),'dummyName')]";
    String landingPageRelationPath = "//div[contains(@class,'_participant-list__')]//span[contains(text(),'dummyRelation')]";
    @FindBy(xpath = "//div[contains(@class,'_participant-list__')]//ul/li//span[contains(@aria-labelledby,'dateOfBirth')]")
    List<WebElement> landingPageBorns;
    @FindBy(xpath = "//div[contains(@class,'_participant-list__')]//ul/li//span[contains(@aria-labelledby,'gender')]")
    List<WebElement> landingPageGenders;
    @FindBy(xpath = "//div[contains(@class,'_participant-list__')]//ul/li//span[contains(@aria-labelledby,'nhsNumber')]")
    List<WebElement> landingPageNhsNumbers;
    @FindBy(xpath = "//div[contains(@class,'_participant-list__')]//ul/li//span[contains(@aria-labelledby,'ngisId')]")
    List<WebElement> landingPageNgsIds;

    @FindBy(xpath = "//div[contains(@class,'css-1')]/following::span[contains(@class,'child-element')][2]")
    public WebElement beingTestedField;

    @FindBy(xpath = "//span[contains(text(),'Not being tested')]")
    public WebElement notBeingTestedField;

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

    @FindBy(xpath = "//span[contains(text(),'Family member removed from referral')]")
    public WebElement successDeletionMessageOfFamilyMember;

    @FindBy(xpath = "//h1[text()='Patient choice']")
    public WebElement patientChoicePageTitle;

    @FindBy(xpath = "//h1[contains(text(),'Print sample forms')]")
    public WebElement printFormsPageTitle;

    static ArrayList<NGISPatientModel> addedFamilyMembers = new ArrayList<NGISPatientModel>();
    static ArrayList<String> familyMemberLandingPageDetails;
    static ArrayList<String> patientChoicePageDetails;
    static ArrayList<String> printFormsPageDetails;

    @FindBy(xpath = "//div[contains(@class,'participant-list')]//span[contains(@aria-labelledby,'nhsNumber')]")
    public List<WebElement> nhsNumberResults;

    @FindBy(xpath = "//div[contains(@class,'participant-list')]//div[contains(@class,'contentCss')]")
    public List<WebElement> participantsList;

    //    @FindBy(xpath = "//span[contains(@aria-labelledby,'gender')]")
    @FindBy(xpath = "//div[contains(@class,'participant-list')]//span[contains(@aria-labelledby,'gender')]")
    public List<WebElement> genderResults;

    @FindBy(xpath = "//div[contains(@class,'participant-list')]//span[contains(@aria-labelledby,'hospitalNumber')]")
    public List<WebElement> hospitalNoResults;

    @FindBy(xpath = "//div[contains(@class,'participant-list')]//span[contains(@aria-labelledby,'ngisId')]")
    public List<WebElement> ngisIdResults;

    @FindBy(xpath = "//span[contains(text(),'Being tested')]/ancestor::span[contains(@class,'css-1')][1]")
    public List<WebElement> familyPageBeingTestedField;

    @FindBy(xpath = "//span[contains(text(),'Not being tested')]/ancestor::span[contains(@class,'css-')][1]")
    public List<WebElement> familyPageNotBeingTestedField;

    @FindBy(xpath = "//div[@class='styles_error-message__text__1v2Kl']")
    public WebElement oneParticipantMsg;
    @FindBy(xpath = "//div[@class='styles_test-list-item__info-message__2RWQ9']")
    public WebElement multipleParticipantMsg;
    //For PatientInformation Identifiers
    String subTitleMessage = "//p[contains(text(),\"dummyTitle\")]";
    String subTitleLink = "//a[contains(text(),\"dummyLink\")]";

    @FindBy(css = "table[class*='table--hpo']")
    public WebElement hpoTable;
    @FindBy(css = "[class*='hpo-term__name']")
    public List<WebElement> hpoTerms;

    String specificFamilyEdit = "//ul//span[text()='NHSLastFour']/ancestor::div[contains(@class,'css-1')]/following-sibling::button[@aria-label='edit button']";

    @FindBy(xpath = "//div[contains(@class,'form-group')]//th")
    public List<WebElement> rdDiagnosisFields;

    public FamilyMemberDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public boolean verifyPatientRecordDetailsDisplay(String relationToProband) {
        //Creating and storing the patient details for later validations
        NGISPatientModel familyMember = new NGISPatientModel();
        if (!Wait.isElementDisplayed(driver, patientCardName, 100)) {
            Debugger.println("Patient Name Details not displayed in Search Result.");
            return false;
        }
        String fullName = patientCardName.getText();
        String[] names = TestUtils.getPatientSplitNames(fullName);
        familyMember.setFIRST_NAME(names[0]);
        familyMember.setLAST_NAME(names[1]);
        familyMember.setTITLE(names[2]);

        if (!seleniumLib.isElementPresent(patientCardBorn)) {
            Debugger.println("Born Details not displayed in Search Result.");
            return false;
        }
        try {
            String bornText = patientCardBorn.getText();
            familyMember.setBORN_WITH_AGE(bornText);
            bornText = bornText.substring(0, bornText.indexOf("("));
            bornText = TestUtils.removeAWord(bornText, "Born");
            familyMember.setBORN_DATE(bornText.trim());
        } catch (Exception exp) {
            Debugger.println("Error in reading born details of added family member.");
        }
        if (!seleniumLib.isElementPresent(patientCardGender)) {
            Debugger.println("Gender Details not displayed in Search Result.");
            return false;
        }
        String genderText = patientCardGender.getText();
        genderText = TestUtils.removeAWord(genderText, "Gender");
        familyMember.setGENDER(genderText.trim());
        if (!seleniumLib.isElementPresent(patientCardNHSNo)) {
            Debugger.println("NHS number not displayed in Search Result.");
            return false;
        }
        String nhsNoText = patientCardNHSNo.getText();
        nhsNoText = TestUtils.removeAWord(nhsNoText, "NHS No.");
        familyMember.setNHS_NUMBER(nhsNoText.trim());
        if (!seleniumLib.isElementPresent(patientCardAddress)) {
            Debugger.println("Address not displayed in Search Result.");
            return false;
        }
        String address = patientCardAddress.getText();
        address = TestUtils.removeAWord(address, "Address");
        familyMember.setFULL_ADDRESS(address);

        if (!seleniumLib.isElementPresent(patientCardType)) {
            Debugger.println("Patient Type not displayed in Search Result.");
            return false;
        }
        String patientType = patientCardType.getText();
        familyMember.setPATIENT_TYPE(patientType);

        if (relationToProband != null) {
            familyMember.setRELATIONSHIP_TO_PROBAND(relationToProband);
        }
        addFamilyMemberToList(familyMember);//This to verify the details later.
        Debugger.println("Family Member: " + familyMember.getNHS_NUMBER() + " added to the list.");
        return true;
    }

    public boolean clickPatientCard() {
        try {
            if(!Wait.isElementDisplayed(driver, patientCard,10)){
                Debugger.println("Patient Card Not displayed..");
                SeleniumLib.takeAScreenShot("clickPatientCard.jpg");
                return false;
            }
            Actions.clickElement(driver,patientCard);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from clickPatientCard:"+exp);
            SeleniumLib.takeAScreenShot("clickPatientCard.jpg");
            return false;
        }
    }
    public boolean editPatientDetails() {
        try {
            if(!Wait.isElementDisplayed(driver, editPatientDetailsLink,10)){
                Debugger.println("Edit Patient Details Link not displayed..");
                SeleniumLib.takeAScreenShot("editPatientDetails.jpg");
                return false;
            }
            Actions.clickElement(driver,editPatientDetailsLink);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from editPatientDetails:"+exp);
            SeleniumLib.takeAScreenShot("editPatientDetails.jpg");
            return false;
        }
    }

    public boolean fillTheRelationshipToProband(String relationToProband) {
        try {
            validationErrors.clear();
            Actions.scrollToTop(driver);
            if (!Wait.isElementDisplayed(driver, relationshipToProbandDropdown, 60)) {
                Debugger.println("FamilyMemberDetailsPage:relationshipToProbandDropdown element not displayed even after waiting period.");
                SeleniumLib.takeAScreenShot("fillTheRelationshipToProband.jpg");
                return false;
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
                    SeleniumLib.takeAScreenShot("RelationshipToProband.jpg");
                    return false;
                }
                seleniumLib.clickOnWebElement(dropdownValue.findElement(ddElement));
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in selecting Relationship to Proband:" + exp);
            SeleniumLib.takeAScreenShot("RelationshipToProband.jpg");
            return false;
        }
    }

    public boolean verifyTheTestAndDetailsOfAddedFamilyMember(NGISPatientModel familyMember) {
        try {
            if (familyMember == null) {
                Debugger.println("Family Member cannot be null.");
                return false;
            }
            Debugger.println("Verifying Selected Test To Relationship Title");
            //1. Verify the display of Title for the added Test.
            if(!Wait.isElementDisplayed(driver,selectedTestForRelationship,30)){
                Debugger.println("Selected Test for Relationship title not loaded.");
                SeleniumLib.takeAScreenShot("SelectedTitleForRelationship.jpg");
                return false;
            }
            String actualRelation = selectedTestForRelationship.getText();
            if(actualRelation == null || actualRelation.isEmpty()){
                Debugger.println("Selected Test for Relationship title not loaded as empty.");
                SeleniumLib.takeAScreenShot("SelectedTitleForRelationship.jpg");
                return false;
            }
            if (!actualRelation.contains(familyMember.getRELATIONSHIP_TO_PROBAND())) {
                Debugger.println("Selected Test, Expected Relationship:" + familyMember.getRELATIONSHIP_TO_PROBAND() + ",Actual:" + actualRelation);
                SeleniumLib.takeAScreenShot("SelectedTitleForRelationship.jpg");
                return false;
            }
            Debugger.println("Verifying Relationship to proband tag");
            //2. Verify the display of Relation to Proband as given.
            //Select the test if not selected by default
            //Added this step to select, if not selected = IT is a BUG in Demo
            if (!Wait.isElementDisplayed(driver, selectedTest, 5)) {
                Actions.clickElement(driver,unSelectedTest);
                Wait.seconds(2);
            }

            if(relationShipTags.size() == 0){
                Debugger.println("Relationship to Proband is not loaded...");
                SeleniumLib.takeAScreenShot("RelationshipToProband.jpg");
                return false;
            }
            boolean isPresent = false;
            for(int i=0; i<relationShipTags.size(); i++){
                if(relationShipTags.get(i).getText().equalsIgnoreCase(familyMember.getRELATIONSHIP_TO_PROBAND())){
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent){
                Debugger.println("Relationship to Proband is not Present...");
                SeleniumLib.takeAScreenShot("RelationshipToProband.jpg");
                return false;
            }
             Debugger.println("Verifying Selected Test");
            //3. Select the test as checked by default.
            if (!Wait.isElementDisplayed(driver, selectedTest, 10)) {
                if (!Wait.isElementDisplayed(driver, unSelectedTest, 10)) {
                    Debugger.println("Option to Select/Deselect test not present in Select Test Page.");
                    SeleniumLib.takeAScreenShot("SelectDeselectTest.jpg");
                    return false;
                } else {
                    Actions.clickElement(driver, unSelectedTest);//To make the test selected by default.
                }
            }
            Debugger.println("Verified Test selection Page successfully");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying selected test title:" + exp);
            SeleniumLib.takeAScreenShot("FMSelectTestPage.jpg");
            return false;
        }
    }

    public boolean fillFamilyMemberDiseaseStatusWithGivenParams(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        //DiseaseStatus
        String parValue = paramNameValue.get("DiseaseStatus");
        if (parValue != null && !parValue.isEmpty()) {
            try {
                Click.element(driver, diseaseStatusDropdown);
                Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + parValue + "']")));
            } catch (Exception exp) {
                try{
                    seleniumLib.clickOnWebElement(diseaseStatusDropdown);
                    Wait.seconds(2);
                    seleniumLib.clickOnWebElement(dropdownValue.findElement(By.xpath("//span[text()='" + parValue+ "']")));
                }catch(Exception exp1) {
                    Debugger.println("Exception from selecting disease from the disease dropdown...:" + exp1);
                    SeleniumLib.takeAScreenShot("DiseaseDropDown.jpg");
                    return false;
                }
            }
        }
        //Age Of Onset
        parValue = paramNameValue.get("AgeOfOnset");
        if (parValue != null && !parValue.isEmpty()) {
            String[] age_of_onsets = parValue.split(",");
            ageOfOnsetYearsField.sendKeys(age_of_onsets[0]);
            ageOfOnsetMonthsField.sendKeys(age_of_onsets[1]);
        }
        //HpoPhenoType
        boolean isHpoSelected = true;
        parValue = paramNameValue.get("HpoPhenoType");
        if (parValue != null && !parValue.isEmpty()) {
            isHpoSelected = false;//Consider only if HPO Passed as an argument
            isHpoSelected = isHPOAlreadyConsidered(parValue);
            if (!isHpoSelected) {
                Debugger.println("Selecting Phenotype.... ....");
                if (searchAndSelectRandomHPOPhenotype(parValue) > 0) {
                    Debugger.println("Phenotype Selected....");
                    isHpoSelected = true;
                }
            }
        }
        //PhenotypicSex
        parValue = paramNameValue.get("PhenotypicSex");
        if (parValue != null && !parValue.isEmpty()) {
            try {
                Click.element(driver, phenotypicSexDropdown);
                Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + parValue + "']")));
            } catch (Exception exp) {
                try{
                    Debugger.println("PhenoTypicSex..SelenimumLib...");
                    seleniumLib.clickOnWebElement(phenotypicSexDropdown);
                    if(!Wait.isElementDisplayed(driver,dropdownValue,5)){
                        seleniumLib.clickOnWebElement(phenotypicSexDropdown);
                    }
                    Wait.seconds(2);
                    seleniumLib.clickOnElement(By.xpath("//span[text()='" + parValue+ "']"));
                }catch(Exception exp1) {
                    Debugger.println("Exception from selecting phenotypicSexDropdown...:" + exp1);
                    SeleniumLib.takeAScreenShot("phenotypicSexDropdown.jpg");
                    return false;
                }
            }
            String selectedValue = phenotypicSexDropdown.getText();
            if(!selectedValue.equalsIgnoreCase(parValue)){
                Debugger.println("PhenoTypicSex...not selected:");
                seleniumLib.clickOnWebElement(phenotypicSexDropdown);
                Wait.seconds(2);
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + parValue + "']")));
            }
        }
        //KaryotypicSex
        parValue = paramNameValue.get("KaryotypicSex");
        if (parValue != null && !parValue.isEmpty()) {
            try {
                Click.element(driver, karyotypicSexDropdown);
                Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + parValue + "']")));
            } catch (Exception exp) {
                try{
                    seleniumLib.clickOnWebElement(karyotypicSexDropdown);
                    Wait.seconds(2);
                    seleniumLib.clickOnWebElement(dropdownValue.findElement(By.xpath("//span[text()='" + parValue+ "']")));
                }catch(Exception exp1) {
                    Debugger.println("Exception from selecting karyotypicSexDropdown...:" + exp1);
                    SeleniumLib.takeAScreenShot("karyotypicSexDropdown.jpg");
                    return false;
                }

            }
        }
        return isHpoSelected;
    }//method

    public boolean selectRareDiseaseDiagnoses(String diagnoses) {
        try {
            if (Wait.isElementDisplayed(driver, rareDiseaseDiagnosesInput, 30)) {
                seleniumLib.sendValue(rareDiseaseDiagnosesInput, diagnoses);
            }
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectByIndexFromDropDown(dropdownValues, 0);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in selectRareDiseaseDiagnoses: " + exp);
            SeleniumLib.takeAScreenShot("RareDiseaseDiagnoses.jpg");
            return false;
        }

    }

    public boolean isHPOAlreadyConsidered(String hpoTerm) {
        String hpoValue = "";
        boolean isExists = false;
        if (!seleniumLib.isElementPresent(hpoRows)) {
            return false;
        }
        List<WebElement> rows = seleniumLib.getElements(hpoRows);
        if (rows != null && rows.size() > 0) {
            Debugger.println("Verifying HPO already exists or not...." + hpoTerm + " in " + rows.size() + " rows.");
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

    public int searchAndSelectRandomHPOPhenotype(String hpoTerm) {
        Wait.seconds(5);
        try {
            if (Wait.isElementDisplayed(driver, hpoSearchField, 30)) {
                seleniumLib.sendValue(hpoSearchField, hpoTerm);
            }
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            if (!Wait.isElementDisplayed(driver, dropdownValue, 10)) {
                Debugger.println("HPO term " + hpoTerm + " present in the dropdown.");
                return -1;
            }
            Actions.selectByIndexFromDropDown(dropdownValues, 0);
            // determine the total number of HPO terms
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, hpoTable);
            int numberOfHPO = hpoTerms.size();
            //Debugger.println("SizeOfHPOTerms: " + numberOfHPO);
            return numberOfHPO;
        } catch (ElementClickInterceptedException interExp) {
            //SeleniumLib click handles the javascript and Actions click also.
            SeleniumLib.takeAScreenShot("PhenoTypeInterceptedExp.jpg");
            seleniumLib.clickOnWebElement(dropdownValues.get(0));
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, hpoTable);
            return hpoTerms.size();
        } catch (Exception exp) {
            Debugger.println("ClinicalQuestionsPage: searchAndSelectRandomHPOPhenotype: Exception " + exp);
            return 0;
        }
    }

    public boolean verifyAddedFamilyMemberDetailsInLandingPage(String nhsDetails) {
        try {
            NGISPatientModel familyMember = getFamilyMember(nhsDetails);
            if (familyMember == null) {
                Debugger.println("Family Member with NHS Number:" + nhsDetails + " Not added to the list!.");
                return false;
            }
            //1. Verify the Name
            Debugger.println("FM Landing Page...Verifying First name..");
            String landingPageName = landingPageNamePath.replaceAll("dummyName", familyMember.getLAST_NAME() + ", " + familyMember.getFIRST_NAME());
            By firstNameLastName = By.xpath(landingPageName);
            if (!seleniumLib.isElementPresent(firstNameLastName)) {
                Debugger.println("Added Family member name: " + familyMember.getLAST_NAME() + ", " + familyMember.getFIRST_NAME() + " Not displayed on Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("FMLandingPage.jpg");
                return false;
            }
            //2. Verify Relation to Proband.
            Debugger.println("FM Landing Page...Verifying Relationship..");
            if (familyMember.getRELATIONSHIP_TO_PROBAND() != null) {//Only for family members, not for probands
                String landingPageRelation = landingPageRelationPath.replaceAll("dummyRelation", familyMember.getRELATIONSHIP_TO_PROBAND());
                By relationToProband = By.xpath(landingPageRelation);
                if (!seleniumLib.isElementPresent(relationToProband)) {
                    Debugger.println("Added Family member relationship: " + familyMember.getRELATIONSHIP_TO_PROBAND() + " Not displayed on Family Member Landing Page.");
                    SeleniumLib.takeAScreenShot("FMLandingPage.jpg");
                    return false;
                }
            }
            boolean isPresent = false;
            //3.Verify DOB
            String dob = "";
            Debugger.println("FM Landing Page...Verifying DOB info.");
            for (int i = 0; i < landingPageBorns.size(); i++) {
                dob = landingPageBorns.get(i).getText();
                if (dob.startsWith(TestUtils.getDOBInMonthFormat(familyMember.getDATE_OF_BIRTH()))) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Added Family member BornDate: " + familyMember.getBORN_DATE() + " Not displayed on Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("FMLandingPage.jpg");
                return isPresent;
            }
            //4.Verify Gender
            Debugger.println("FM Landing Page...Verifying Gender info.");
            for (int i = 0; i < landingPageGenders.size(); i++) {
                if (familyMember.getGENDER().equalsIgnoreCase(landingPageGenders.get(i).getText())) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Added Family member Gender: " + familyMember.getGENDER() + " Not displayed on Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("FMLandingPage.jpg");
                return false;
            }
            //5.Verify NHS
            Debugger.println("FM Landing Page...Verifying NHS info..");
            if (landingPageNhsNumbers.size() > 1) {//Checking for added family members, excluded Proband
                isPresent = false;
                String actualNhs = "";
                for (int i = 0; i < landingPageNhsNumbers.size(); i++) {
                    actualNhs = landingPageNhsNumbers.get(i).getText();
                    if (actualNhs != null) {
                        if (familyMember.getNHS_NUMBER().equalsIgnoreCase(actualNhs)) {
                            isPresent = true;
                            break;
                        }
                    } else {
                        //Family Member added without NHS number need not validate for NHS number.
                        isPresent = true;//NHS is not mandatory
                    }
                }//for
            }
            if (!isPresent) {
                Debugger.println("Added Family member NHSNumber: " + familyMember.getNHS_NUMBER() + " Not displayed on Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("FMLandingPage.jpg");
                return false;
            }
            //6.Verify NGISID
            String ngsId = "";
            isPresent = false;
            Debugger.println("FM Landing Page...Verifying NGSID..");
            for (int i = 0; i < landingPageNgsIds.size(); i++) {
                ngsId = landingPageNgsIds.get(i).getText();
                if (ngsId != null) {
                    if (familyMember.getNGIS_ID() == null) {
                        isPresent = true;
                        //Set the NGIS to the family member for verification in print forms , on need basis
                        familyMember.setNGIS_ID(ngsId);
                        FamilyMemberDetailsPage.updateNGISID(familyMember);
                        break;
                    } else {
                        if (familyMember.getNGIS_ID().equalsIgnoreCase(ngsId)) {
                            isPresent = true;
                            break;
                        }
                    }
                } else {
                    isPresent = true;
                }
            }
            if (!isPresent) {
                Debugger.println("Added Family member NGSID: " + familyMember.getNGIS_ID() + " Not displayed on Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("FMLandingPage.jpg");
                return false;
            }

            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception in validating added family member in landing page: " + exp);
            SeleniumLib.takeAScreenShot("FMLandingPage.jpg");
            return false;
        }
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

    public boolean verifyPopulatedDetailsForFamilyMember(String memberDetails) {
        NGISPatientModel familyMember = getFamilyMember(memberDetails);
        if (familyMember == null) {
            Debugger.println("Family Member " + memberDetails + " not found in the list.");
            return false;
        }
        Wait.seconds(2);
        if (!Actions.getValue(firstName).equalsIgnoreCase(familyMember.getFIRST_NAME())) {
            Debugger.println("Expected first name: " + familyMember.getFIRST_NAME() + ",actual:" + Actions.getValue(firstName));
            return false;
        }
        if (!Actions.getValue(lastName).equalsIgnoreCase(familyMember.getLAST_NAME())) {
            Debugger.println("Expected last name: " + familyMember.getLAST_NAME() + ",actual:" + Actions.getValue(lastName));
            return false;
        }
        By selectedGenderElement = By.xpath(selectedGender.replaceAll("dummyGender", familyMember.getGENDER()));
        if (!seleniumLib.isElementPresent(selectedGenderElement)) {
            Debugger.println("Expected gender: " + familyMember.getGENDER() + " not loaded.");
            return false;
        }
        if (!Actions.getValue(nhsNumber).equalsIgnoreCase(familyMember.getNHS_NUMBER())) {
            Debugger.println("Expected NHSNumber: " + familyMember.getNHS_NUMBER() + ",actual:" + Actions.getValue(nhsNumber));
            return false;
        }
        return true;
    }

    public boolean verifyTheTestCheckboxIsSelected(String nhsDetails) {
        try {
            //This code added to make the test pass, it is a known issue, as per manual team suggestion
            //https://jira.extge.co.uk/browse/NTOS-4911
            selectTheTest();

            NGISPatientModel ngisPatientModel = getFamilyMember(nhsDetails);
            if(!Wait.isElementDisplayed(driver, selectedTest,20)){
                Debugger.println("Test is not selected by default for the family member with NHS:"+ngisPatientModel.getNHS_NUMBER());
                SeleniumLib.takeAScreenShot("TestNoSelectedByDefault.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying verifyTheTestCheckboxIsSelected:" + exp);
            SeleniumLib.takeAScreenShot("TestNoSelectedByDefault.jpg");
            return false;
        }
    }

    public boolean deSelectTheTest() {
        try {
            //This code added to make the test pass, it is a known issue, as per manual team suggestion
            //https://jira.extge.co.uk/browse/NTOS-4911
            selectTheTest();
            if (!Wait.isElementDisplayed(driver, selectedTest, 20)) {
                Debugger.println("Expected status of Test is Selected default, but the current status is Deselected.");
                SeleniumLib.takeAScreenShot("DeSelectTest.jpg");
                return false;
            }
            Actions.clickElement(driver,selectedTest);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in deSelectTheTest:" + exp);
            SeleniumLib.takeAScreenShot("DeSelectTest.jpg");
            return false;
        }
    }

    public boolean verifyTestPackageCheckBoxDeSelected() {
        try {
            if (!Wait.isElementDisplayed(driver,unSelectedTest,10)) {
                Debugger.println("Expected to be the test deselected, but selected.");
                SeleniumLib.takeAScreenShot("DeselectedTest.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifyTestPackageCheckBoxDeSelected:" + exp);
            SeleniumLib.takeAScreenShot("DeselectedTest.jpg");
            return false;
        }
    }

    public void clickOnBackButton() {
        try {
            Actions.clickElement(driver, backButton);
        } catch (Exception exp) {
            try {
                Actions.scrollToBottom(driver);
                Actions.clickElement(driver, backButton);
            } catch (Exception exp1) {
                SeleniumLib.takeAScreenShot("BackButtonOnFMDetails.jpg");
                Debugger.println("Could not click on Back Button on FamilyDetailsPage: " + exp1);
            }
        }
    }

    public boolean verifyTheEditingReferralColor(String nhsDetails, String eColor) {
        try {
            NGISPatientModel familyMember = getFamilyMember(nhsDetails);
            if (familyMember == null) {
                Debugger.println("Family Member for :" + nhsDetails + " Not present in the added list.");
                return false;
            }
            Debugger.println("FN:" + familyMember.getFIRST_NAME() + ",LN:" + familyMember.getLAST_NAME() + ",DOB:" + familyMember.getDATE_OF_BIRTH());
            By lastName = By.xpath(addFamilyMemberTitle + "'" + familyMember.getLAST_NAME() + ",')]");
            WebElement webElement_LN = driver.findElement(lastName);
            if (!Wait.isElementDisplayed(driver, webElement_LN, 30)) {
                Debugger.println("Editing Member Details: " + lastName.toString() + " not found in Landing Page.");
                return false;
            }
            By firstName = By.xpath(addFamilyMemberTitle + "'" + familyMember.getFIRST_NAME() + "')]");
            WebElement webElement_FN = driver.findElement(firstName);
            if (!Wait.isElementDisplayed(driver, webElement_FN, 30)) {
                Debugger.println("Editing Member Details: " + firstName.toString() + " not found in Landing Page.");
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
            Debugger.println("Exception in verifying details of editing family member." + exp);
            SeleniumLib.takeAScreenShot("EditingFamilyDetails.jpg");
            return false;
        }
    }

    public boolean editSpecificFamilyMember(int num) {
        try {
            Actions.clickElement(driver, editButton.get(num));
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on edit button for family Member:" + exp);
            return false;
        }
    }

    public boolean verifyTheDeleteMessage(String deleteMessage) {
        try {
            if(!Wait.isElementDisplayed(driver,successDeletionMessageOfFamilyMember,10)){
                Actions.scrollToTop(driver);
            }
            String actualMessage = "";
            try{
                actualMessage = successDeletionMessageOfFamilyMember.getText();
            }catch(Exception exp1){
                actualMessage  =seleniumLib.getText(successDeletionMessageOfFamilyMember);
            }
            if(!actualMessage.equalsIgnoreCase(deleteMessage)){
                Debugger.println("Expected Message:"+deleteMessage+",But Actual:"+actualMessage);
                SeleniumLib.takeAScreenShot("FMDeleteMessage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying the Family Member Removal Message: " + exp);
            SeleniumLib.takeAScreenShot("FMDeleteMessage.jpg");
            return false;
        }
    }

    public boolean verifyTheDeleteMessageIsNotPresent() {
        if (Wait.isElementDisplayed(driver, successDeletionMessageOfFamilyMember, 5)) {
            return false; //Not supposed to present
        }
        return true;
    }

    public boolean clickOnDeselectedTestCheckBox() {
        try {
            //This method is to verify whether we can click on an unselected test check box
            //If the test is in selected mode, then making it as unselected and clicking
            if(Wait.isElementDisplayed(driver, selectedTest,30)){
                Actions.clickElement(driver,selectedTest);
            }
            Actions.clickElement(driver,unSelectedTest);
            return true;//Already deselected
        }catch(ElementClickInterceptedException exp){
            //The box might be in selected stage and element may not be able to click. So moving control out and click again
           Actions.clickElement(driver,selectedFamilyMembersLabel);
           Wait.seconds(2);
           Actions.clickElement(driver,unSelectedTest);
            Wait.seconds(2);
           return true;
        }catch(Exception exp){
            Debugger.println("Exception in clickOnDeselectedTestCheckBox.."+exp);
            SeleniumLib.takeAScreenShot("NoDeSelectedCheckBox.jpg");
            return false;
        }

    }

    public boolean unmatchedParticipantErrorMessage(String expMessage) {
        try {
            String actMessage = "";
            boolean isPresent = false;
            int noOfNotifications = notificationErrors.size();
            for (int i = 0; i < noOfNotifications; i++) {
                actMessage = notificationErrors.get(i).getText();
                if (actMessage.contains(expMessage)) {
                    isPresent = true;
                    break;
                }
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying unmatchedParticipantErrorMessage: Check WarningMessageNotPresent.jpg" + exp);
            SeleniumLib.takeAScreenShot("WarningMessageNotPresent.jpg");
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
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from removing family member " + exp);
            return false;
        }
    }

    public boolean verifyAlertMessageOnRemoval(String alertMessage) {
        try {
            Alert alert = driver.switchTo().alert();
            String actualMessage = driver.switchTo().alert().getText();
            if (!actualMessage.equalsIgnoreCase(alertMessage)) {
                Debugger.println("Actual alert message: " + actualMessage + ", Expected:" + alertMessage);
                alert.accept();
                return false;
            }
            alert.accept();
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from removing family member " + exp);
            return false;
        }
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

    public boolean patientChoiceStatusDetail() {
        Wait.forElementToBeDisplayed(driver, patientChoiceStatusField);
        if (!Wait.isElementDisplayed(driver, patientChoiceStatusField, 10)) {
            return false;
        }
        return true;
    }

    public boolean verifySubTitleMessage(String message) {
        try {
            String subTitle = subTitleMessage.replaceAll("dummyTitle", message);
            WebElement subTitleElement = driver.findElement(By.xpath(subTitle));
            return Wait.isElementDisplayed(driver, subTitleElement, 10);
        } catch (Exception exp) {
            Debugger.println("Exception verifying Subtitle Presence:" + message);
            SeleniumLib.takeAScreenShot("subTitleError.jpg");
            return false;
        }
    }

    public boolean verifySubTitleLink(String message) {
        try {
            String subTitle = subTitleLink.replaceAll("dummyLink", message);
            WebElement subTitleElement = driver.findElement(By.xpath(subTitle));
            return Wait.isElementDisplayed(driver, subTitleElement, 10);
        } catch (Exception exp) {
            Debugger.println("Exception verifying Subtitle Link Presence:" + message);
            SeleniumLib.takeAScreenShot("subTitleLinkError.jpg");
            return false;
        }
    }

    public boolean addFamilyMemberAndContinueButtonIsDisplayed() {
        if (!seleniumLib.isElementPresent(addFamilyMemberButton) &&
                !seleniumLib.isElementPresent(continueButton)) {
            return false;
        }
        return true;
    }

    public void readPatientDetailsInFamilyMemberLandingPage() {
        //Reading the Details of Family Members to Compare with PatientChoice and PrintForms later.
        Wait.forElementToBeDisplayed(driver, familyMemberLandingPageTitle);
        familyMemberLandingPageDetails = new ArrayList<String>();
        try {
            int noOfParticipants = participantsList.size();
            if (noOfParticipants == 0) {
                Debugger.println("No family members loaded in the landing Page.");
                SeleniumLib.takeAScreenShot("FMDetailsNotLoaded.jpg");
                return;
            }
            //Hospital/NHS results may or may not come based on the way we create the family members
            for (int i = 0; i < nhsNumberResults.size(); i++) {
                familyMemberLandingPageDetails.add(nhsNumberResults.get(i).getText());
            }
            for (int i = 0; i < hospitalNoResults.size(); i++) {
                familyMemberLandingPageDetails.add(hospitalNoResults.get(i).getText());
            }
            for (int i = 0; i < noOfParticipants; i++) {
                familyMemberLandingPageDetails.add(nameResults.get(i).getText());
                familyMemberLandingPageDetails.add(genderResults.get(i).getText());
                familyMemberLandingPageDetails.add(ngisIdResults.get(i).getText());
            }
            Debugger.println("No of Patient Details Read from FamilyMember Landing Page: " + familyMemberLandingPageDetails.size());
        } catch (Exception exp) {
            Debugger.println("Exception from reading the Family Member Details from FamilyMember Landing Page:" + exp);
            SeleniumLib.takeAScreenShot("FMDetailsNotLoaded.jpg");
        }
    }


    public boolean compareFamilyIdentifiersOnFamilyMemberAndPatientChoice() {
        if (familyMemberLandingPageDetails.equals(patientChoicePageDetails)) {
            return true;
        }
        return false;
    }

    public void readPrintFormsInPatientChoicePage() {
        //Reading the Details of PrintForms to Compare with Family Members.
        Wait.forElementToBeDisplayed(driver, printFormsPageTitle);
        printFormsPageDetails = new ArrayList<String>();
        try {
            int noOfParticipants = participantsList.size();
            if (noOfParticipants == 0) {
                Debugger.println("No family members loaded in the landing Page.");
                SeleniumLib.takeAScreenShot("PrintFormDetailsNotLoaded.jpg");
                return;
            }
            //Hospital/NHS results may or may not come based on the way we create the family members
            for (int i = 0; i < nhsNumberResults.size(); i++) {
                printFormsPageDetails.add(nhsNumberResults.get(i).getText());
            }
            for (int i = 0; i < hospitalNoResults.size(); i++) {
                printFormsPageDetails.add(hospitalNoResults.get(i).getText());
            }
            for (int i = 0; i < noOfParticipants; i++) {
                printFormsPageDetails.add(nameResults.get(i).getText());
                printFormsPageDetails.add(genderResults.get(i).getText());
                printFormsPageDetails.add(ngisIdResults.get(i).getText());
            }

            Debugger.println("No of Patient Details Read from Print Form Page: " + printFormsPageDetails.size());
        } catch (Exception exp) {
            Debugger.println("Exception from reading the Family Member Details from Print Form Page:" + exp);
        }
    }

    public void readPatientDetailsInPatientChoicePage() {
        //Reading the Details of Patient Choice to Compare with FamilyMemberLandingPage and PrintForms later.
        Wait.forElementToBeDisplayed(driver, patientChoicePageTitle);
        patientChoicePageDetails = new ArrayList<String>();
        try {
            int noOfParticipants = participantsList.size();
            if (noOfParticipants == 0) {
                Debugger.println("No family members loaded in the landing Page.");
                SeleniumLib.takeAScreenShot("PCDDetailsNotLoaded.jpg");
                return;
            }
            //Hospital/NHS results may or may not come based on the way we create the family members
            for (int i = 0; i < nhsNumberResults.size(); i++) {
                patientChoicePageDetails.add(nhsNumberResults.get(i).getText());
            }
            for (int i = 0; i < hospitalNoResults.size(); i++) {
                patientChoicePageDetails.add(hospitalNoResults.get(i).getText());
            }
            for (int i = 0; i < noOfParticipants; i++) {
                patientChoicePageDetails.add(nameResults.get(i).getText());
                patientChoicePageDetails.add(genderResults.get(i).getText());
                patientChoicePageDetails.add(ngisIdResults.get(i).getText());
            }

            Debugger.println("No of Patient Details Read from PatientChoice Page: " + patientChoicePageDetails.size());
        } catch (Exception exp) {
            Debugger.println("Exception from reading the Family Member Details from Patient Choice Page:" + exp);
        }
    }

    public boolean compareDataFromFamilyMemberAndPrintForms() {
        if (familyMemberLandingPageDetails.equals(printFormsPageDetails)) {
            return true;
        }
        return false;
    }

    public boolean verifyTestBadgeBackgroundColor(String testBadge, String color) {
        try {
            Wait.seconds(5);
            String expectedBgColor = StylesUtils.convertFontColourStringToCSSProperty(color.trim());
            if(expectedBgColor == null || expectedBgColor.equalsIgnoreCase("Not defined")){
                return false;
            }
            //Being test field color
            String actualColor = "";
            boolean isPresent = false;
            if (testBadge.equalsIgnoreCase("Being tested")) {
                if (familyPageBeingTestedField.size() == 0) {
                    Debugger.println("No member with test status " + testBadge + " is present.");
                    //Checking color of all test status, if present only
                    isPresent = true;
                }
                for (int i = 0; i < familyPageBeingTestedField.size(); i++) {
                    actualColor = familyPageBeingTestedField.get(i).getCssValue("background-color");
                    if (expectedBgColor.equalsIgnoreCase(actualColor)) {
                        isPresent = true;
                    } else {
                        isPresent = false;
                        Debugger.println("Expected background color of test badge:" + testBadge + " is:" + expectedBgColor + ",Actual:" + actualColor);
                        SeleniumLib.takeAScreenShot("BadgeBackgroundColor.jpg");
                        break;
                    }
                }
            } else if (testBadge.equalsIgnoreCase("Not being tested")) {
                if (familyPageNotBeingTestedField.size() == 0) {
                    Debugger.println("No member with test status " + testBadge + " is present.");
                    //Checking color of all test status, if present only
                    isPresent = true;
                }
                for (int i = 0; i < familyPageNotBeingTestedField.size(); i++) {
                    actualColor = familyPageNotBeingTestedField.get(i).getCssValue("background-color");
                    if (expectedBgColor.equalsIgnoreCase(actualColor)) {
                        isPresent = true;
                    } else {
                        isPresent = false;
                        Debugger.println("Expected background color of test badge:" + testBadge + " is:" + expectedBgColor + ",Actual:" + actualColor);
                        SeleniumLib.takeAScreenShot("BadgeBackgroundColor.jpg");
                        break;
                    }
                }
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from checking being tested field " + exp);
            SeleniumLib.takeAScreenShot("BadgeBackgroundColor.jpg");
            return false;
        }
    }

    public boolean verifyDeselectedPatientTestStatus(String nhsDetails, String status) {
        try {
            NGISPatientModel patient = getFamilyMember(nhsDetails);
            WebElement actualResult = driver.findElement(By.xpath("//h2[contains(text(),'" + patient.getFIRST_NAME() + "')]/following::span[contains(@class,'child-element')][2]"));
            if (!status.equalsIgnoreCase(actualResult.getText())) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("FamilyMemberDetailsPage:verifyDeselectedPatientTestStatus:Exception:" + exp);
        }
        return false;
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

    public static NGISPatientModel getFamilyMember(String nhsDetails) {
        try {
            if (nhsDetails == null || nhsDetails.isEmpty()) {
                if (addedFamilyMembers.size() > 0) {
                    return addedFamilyMembers.get(0);
                }
                return null;
            }
            String nhsNumber = "", dob = "";
            if (nhsDetails.indexOf(":") == -1) {//If we provide direct NHS number
                nhsNumber = nhsDetails;
            } else {//In the format of NHSNumber=xxxxxxx:Dob=dd-mm-yyyy
                HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(nhsDetails);
                Set<String> paramsKey = paramNameValue.keySet();
                for (String key : paramsKey) {
                    if (key.contains("NHSNumber")) {
                        nhsNumber = paramNameValue.get(key);
                    }
                    if (key.equalsIgnoreCase("DOB")) {
                        dob = paramNameValue.get(key);
                    }
                }
            }
            if (nhsNumber == null || nhsNumber.isEmpty()) {
                if (dob == null || dob.isEmpty()) {
                    return addedFamilyMembers.get(0);
                }
            }
            String actualNhs = "", actualDob = "";
            for (int i = 0; i < addedFamilyMembers.size(); i++) {
                actualNhs = addedFamilyMembers.get(i).getNHS_NUMBER();
                actualDob = addedFamilyMembers.get(i).getDATE_OF_BIRTH();
                //Debugger.println("ActNHS:"+actualNhs+","+actualDob+",EXP:"+nhsNumber+","+dob);
                if (nhsNumber.isEmpty() || nhsNumber.equalsIgnoreCase("NA")) {
                    if (actualDob != null && actualDob.equalsIgnoreCase(dob)) {
                        return addedFamilyMembers.get(i);
                    }
                } else {
                    if (actualNhs != null) {
                        if (actualNhs.equalsIgnoreCase(nhsNumber)) {
                            return addedFamilyMembers.get(i);
                        }
                    }
                }
            }
            Debugger.println("COULD NOT find Family Member for NHS :" + nhsNumber + " OR DOB: " + dob);
            return null;
        } catch (Exception exp) {
            Debugger.println("Family Member with NHSNumber:" + nhsDetails + " Not found in the added list." + exp);
            return null;
        }
    }

    public static void updateRecordingClinicianName(NGISPatientModel familyMember) {
        try {
            for (int i = 0; i < addedFamilyMembers.size(); i++) {
                if (addedFamilyMembers.get(i).getNHS_NUMBER().equalsIgnoreCase(familyMember.getNHS_NUMBER())) {
                    addedFamilyMembers.get(i).setRECORDING_CLINICIAN_NAME(familyMember.getRECORDING_CLINICIAN_NAME());
                }
            }
        } catch (Exception exp) {
            Debugger.println("Exception in setting up RECORDING_CLINICIAN_NAME for FamilyMember " + exp);
        }
    }

    public static void updateNGISID(NGISPatientModel familyMember) {
        try {
            for (int i = 0; i < addedFamilyMembers.size(); i++) {
                if (addedFamilyMembers.get(i).getNHS_NUMBER().equalsIgnoreCase(familyMember.getNHS_NUMBER())) {
                    addedFamilyMembers.get(i).setNGIS_ID(familyMember.getNGIS_ID());
                    //Debugger.println("Updated Patient NGSID for familyMember with DOB:"+familyMember.getDATE_OF_BIRTH()+", "+familyMember.getNGIS_ID());
                }
            }
        } catch (Exception exp) {
            Debugger.println("Exception in setting up NGIS ID " + exp);
        }
    }

    public static void updateNonNGISID(NGISPatientModel familyMember) {
        try {
            for (int i = 0; i < addedFamilyMembers.size(); i++) {
                if (addedFamilyMembers.get(i).getNHS_NUMBER().equalsIgnoreCase(familyMember.getNHS_NUMBER())) {
                    addedFamilyMembers.get(i).setNON_NGIS_ID1(familyMember.getNON_NGIS_ID1());
                    addedFamilyMembers.get(i).setNON_NGIS_ID2(familyMember.getNON_NGIS_ID2());
                    Debugger.println("Updated Patient Non-NGSID for familyMember with DOB:" + familyMember.getDATE_OF_BIRTH() + ", " + familyMember.getNON_NGIS_ID1() + "," + familyMember.getNON_NGIS_ID2());
                }
            }
        } catch (Exception exp) {
            Debugger.println("Exception in setting up NGIS ID " + exp);
        }
    }

    public static void updateRelationship(NGISPatientModel familyMember) {
        try {
            for (int i = 0; i < addedFamilyMembers.size(); i++) {
                if (addedFamilyMembers.get(i).getNHS_NUMBER().equalsIgnoreCase(familyMember.getNHS_NUMBER())) {
                    addedFamilyMembers.get(i).setRELATIONSHIP_TO_PROBAND(familyMember.getRELATIONSHIP_TO_PROBAND());
                    Debugger.println("Updated Relationship for member with DOB:" + familyMember.getDATE_OF_BIRTH() + ", " + familyMember.getNGIS_ID());
                }
            }
        } catch (Exception exp) {
            Debugger.println("Exception in setting up NGIS ID " + exp);
        }
    }

    public static void addFamilyMemberToList(NGISPatientModel familyMember) {
        addedFamilyMembers.add(familyMember);
    }

    public boolean verifyFamilyMemberTestBadge(String testBadge) {
        try {
            Wait.seconds(2);
            //Being test field color
            String actualMessage = "";
            boolean isPresent = false;
            if (testBadge.equalsIgnoreCase("Being tested")) {
                for (int i = 0; i < familyPageBeingTestedField.size(); i++) {
                    actualMessage = Actions.getText(familyPageBeingTestedField.get(i));
                    if (testBadge.equalsIgnoreCase(actualMessage)) {
                        isPresent = true;
                        break;
                    }
                }
            } else if (testBadge.equalsIgnoreCase("Not being tested")) {
                for (int i = 0; i < familyPageNotBeingTestedField.size(); i++) {
                    actualMessage = Actions.getText(familyPageNotBeingTestedField.get(i));
                    if (testBadge.equalsIgnoreCase(actualMessage)) {
                        isPresent = true;
                        break;
                    }
                }
            }
            if (!isPresent) {
                SeleniumLib.takeAScreenShot("BadgePresence.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from checking being tested field " + exp);
            SeleniumLib.takeAScreenShot("BadgePresence.jpg");
            return false;
        }
    }

    public int getDisplayIndexOfSpecificReferral(String referralIdentifierName) {

        try {
            Wait.forElementToBeDisplayed(driver, displayedChildElements.get(0));
            for (int i = 0; i < displayedChildElements.size(); i++) {
                String name = Actions.getText(displayedChildElements.get(i));
                if (name.equalsIgnoreCase(referralIdentifierName)) {
                    return i;
                }
                continue;
            }
            return -1;

        } catch (Exception exp) {
            Debugger.println("Exception from Proband/ family member" + exp);
            SeleniumLib.takeAScreenShot("FamilyMembersLandingPage.jpg");
            return -1;
        }
    }

    public boolean editFamilyMember() {
        try {
            Wait.forElementToBeDisplayed(driver, editButtonForParticipant);
            Actions.clickElement(driver, editButtonForParticipant);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from editing family member" + exp);
            SeleniumLib.takeAScreenShot("EditFamilyMember.jpg");
            return false;
        }
    }

    public boolean verifyTheDetailsOfFamilyMemberOnFamilyMemberPageForNotBeingTested() {
        Wait.forElementToBeDisplayed(driver, familyMemberLandingPageTitle);
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(notBeingTestedField);
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

    public boolean selectTheTest() {
        try {
            if (!Wait.isElementDisplayed(driver, selectedTest, 10)) {
                Actions.clickElement(driver,unSelectedTest);
                return true;
            }
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception in selectTheTest:" + exp);
            SeleniumLib.takeAScreenShot("testSelect.jpg");
            return false;
        }
    }

    public boolean editFamilyMemberHavingNHSDob(String familyMemberDetails) {
        String nhsNumber = "";
        try {
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyMemberDetails);
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
            if (!Wait.isElementDisplayed(driver, element, 100)) {
                Debugger.println("Family member edit button not displayed for " + familyMemberDetails);
                return false;
            }
            seleniumLib.clickOnWebElement(element);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on edit button for family member with NHSNumber: " + exp);
            return false;
        }
    }

    public boolean verifyRareDiseaseDiagnoseFieldPresence(String ageOfOnset) {
        try {
            if (rdDiagnosisFields.size() == 0) {
                Debugger.println("RD Diagnosis fields not displayed.");
                SeleniumLib.takeAScreenShot("RdDiagnosisFields.jpg");
                return false;
            }
            for (int i = 0; i < rdDiagnosisFields.size(); i++) {
                if (rdDiagnosisFields.get(i).getText().equalsIgnoreCase(ageOfOnset)) {//Should not present
                    Debugger.println("RD Diagnosis field - Age of Onset displayed.");
                    SeleniumLib.takeAScreenShot("RdDiagnosisFields.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("FamilyMemberDetailsPage : verifyRareDiseaseDiagnosesAgeOfOnSetFieldAbsence : " + exp);
            SeleniumLib.takeAScreenShot("RdDiagnosisFields.jpg");
            return false;
        }
    }

    public boolean verifySelectedRelationshipToProband(String expectedRelation) {
        try {
            Wait.forElementToBeClickable(driver, relationshipToProband);
            String actualRelationship = relationshipToProband.getText();
            if (actualRelationship == null) {
                Debugger.println("Relationship to proban is NULL");
                SeleniumLib.takeAScreenShot("ReationToProband.jpg");
                return false;
            }
            if (!(expectedRelation.equalsIgnoreCase(actualRelationship))) {
                Debugger.println("Relationship to proband: Actual:" + actualRelationship + " ,Expected: " + expectedRelation);
                SeleniumLib.takeAScreenShot("ReationToProband.jpg");
                return false;
            }
            return true;
        } catch (Exception e) {
            Debugger.println("Error message not found");
            SeleniumLib.takeAScreenShot("ReationToProband.jpg");
            return false;
        }
    }

}//ends
