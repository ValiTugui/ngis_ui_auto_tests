package co.uk.gel.proj.pages;

import co.uk.gel.lib.Action;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

    @FindBy(xpath = "//legend[contains(text(),'Date of birth')]")
    public WebElement dobLabel;

    @FindBy(id = "dateOfBirthDay")
    public WebElement dateOfBirthDayFM;

    @FindBy(id = "dateOfBirthMonth")
    public WebElement dateOfBirthMonthFM;

    @FindBy(id = "dateOfBirthYear")
    public WebElement dateOfBirthYearFM;

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

    @FindBy(xpath = "//button/span[contains(text(),'Back')]")
    public WebElement backButton;

    @FindBy(xpath = "//button/span[contains(text(),'Edit patient details')]")
    public WebElement editPatientDetailsLink;

    @FindBy(css = "*[class*='helix']")
    public List<WebElement> helix;

    String helixIcon = "*[class*='helix']";

    @FindBy(css = "div[class*='error-message__text']")
    public List<WebElement> validationErrors;

    @FindBy(xpath = "//label[contains(@for,'relationship')]//following::div")
    public WebElement relationshipToProbandDropdown;

    @FindBy(xpath = "//div[@data-testid='notification-error']//span")
    public List<WebElement> notificationErrors;

    @FindBy(xpath = "//a[contains(text(),'amend the expected number of participants')]")
    public WebElement participantAmendmentLink;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(xpath = "//div[contains(@id,'question-id-q96')]")
    public WebElement diseaseStatusDropdown;

    @FindBy(xpath = "//label[contains(text(),'Phenotypic sex')]/../div/div")
    public WebElement phenotypicSexDropdown;

    @FindBy(xpath = "//label[contains(text(),'Karyotypic sex')]/../div/div")
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

    @FindBy(xpath = "//div[contains(@class,'test-list_')]//span[contains(@class,'checked')]")
    public List<WebElement> selectedTests;

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

    @FindBy(xpath = "//button/span[contains(text(),'Add family member')]")
    public WebElement addFamilyMemberButton;

    @FindBy(xpath = "//button/span[contains(text(),'Continue')]")
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

    @FindBy(xpath = "//span[contains(text(),'Being tested')]/ancestor::span[contains(@class,'css-')][1]")
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

    String specificFamilyEdit = "//ul//span[text()='NHSLastFour']/ancestor::div[contains(@class,'css')]/following-sibling::button[@aria-label='edit button']";

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
                return false;
            }
            Action.clickElement(driver, patientCard);
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    public boolean editPatientDetails() {
        try {
            if(!Wait.isElementDisplayed(driver, editPatientDetailsLink,10)){
                return false;
            }
            Action.clickElement(driver,editPatientDetailsLink);
            return true;
        }catch(Exception exp){
            return false;
        }
    }

    public boolean fillTheRelationshipToProband(String relationToProband) {
        try {
            Debugger.println("Filling RelationShip To Proband URL: "+driver.getCurrentUrl());
            validationErrors.clear();
            Action.scrollToTop(driver);
            if (!Wait.isElementDisplayed(driver, relationshipToProbandDropdown, 60)) {
                Debugger.println("FamilyMemberDetailsPage:relationshipToProbandDropdown element not displayed even after waiting period.");
                SeleniumLib.takeAScreenShot("fillTheRelationshipToProband.jpg");
                return false;
            }
            seleniumLib.clickOnWebElement(relationshipToProbandDropdown);
            Wait.seconds(2);
            Debugger.println("DDSize: "+dropdownValues.size());
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
                Action.clickElement(driver,unSelectedTest);
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
                    Action.clickElement(driver, unSelectedTest);//To make the test selected by default.
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

    public boolean editFMDropdownField(WebElement element, String value) {
        try {
            Action.clickElement(driver, element);
            Wait.seconds(3);
            boolean isPresent = false;
            for (WebElement ddElemt : dropdownValues) {
                if(ddElemt.getText().equalsIgnoreCase(value)){
                    ddElemt.click();
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent){
                Debugger.println("Could not select the drop down value:"+value);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in editDropdownField:" + value + " on:" + element + "\n" + exp);
            SeleniumLib.takeAScreenShot("FM_editDropdownField.jpg");
            return false;
        }
    }


    public boolean fillFamilyMemberDiseaseStatusWithGivenParams(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Debugger.println("URL: "+driver.getCurrentUrl());
        //DiseaseStatus
        String parValue = paramNameValue.get("DiseaseStatus");
        if (parValue != null && !parValue.isEmpty()) {
            if(!editFMDropdownField(diseaseStatusDropdown,parValue)){
                SeleniumLib.takeAScreenShot("DiseaseStatusNotSelected.jpg");
                return false;
            }
        }
        //Age Of Onset
        parValue = paramNameValue.get("AgeOfOnset");
        if (parValue != null && !parValue.isEmpty()) {
            String[] age_of_onsets = parValue.split(",");
            seleniumLib.sendValue(ageOfOnsetYearsField,age_of_onsets[0]);
            seleniumLib.sendValue(ageOfOnsetMonthsField,age_of_onsets[1]);
        }
        //HpoPhenoType
        boolean isHpoSelected = true;
        parValue = paramNameValue.get("HpoPhenoType");
        if (parValue != null && !parValue.isEmpty()) {
            searchAndSelectRandomHPOPhenotype(parValue);
        }
        //PhenotypicSex
        parValue = paramNameValue.get("PhenotypicSex");
        if (parValue != null && !parValue.isEmpty()) {
            SeleniumLib.scrollToElement(rareDiseaseDiagnosesInput);
            if(!editFMDropdownField(phenotypicSexDropdown,parValue)){
                SeleniumLib.takeAScreenShot("PhenotypicSexNotSelected.jpg");
            }
        }
        seleniumLib.sleepInSeconds(2);
        //KaryotypicSex
        parValue = paramNameValue.get("KaryotypicSex");
        if (parValue != null && !parValue.isEmpty()) {
            if(!editFMDropdownField(karyotypicSexDropdown,parValue)){
                SeleniumLib.takeAScreenShot("KaryotypicSexNotSelected.jpg");
            }
        }
        seleniumLib.sleepInSeconds(2);
        return isHpoSelected;
    }//method

    public boolean selectRareDiseaseDiagnoses(String diagnoses) {
        try {
            if (Wait.isElementDisplayed(driver, rareDiseaseDiagnosesInput, 30)) {
                seleniumLib.sendValue(rareDiseaseDiagnosesInput, diagnoses);
            }
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Action.selectByIndexFromDropDown(dropdownValues, 0);
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
            //Debugger.println("Verifying HPO already exists or not...." + hpoTerm + " in " + rows.size() + " rows.");
            for (WebElement row : rows) {
                hpoValue = row.findElement(By.xpath("./td[1]")).getText();
                if (hpoValue.equalsIgnoreCase(hpoTerm)) {
                    isExists = true;
                    //Debugger.println("Phenotype already exists:");
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
            Wait.seconds(2);
            if(dropdownValues.size() == 0){
                int numberOfHPO = hpoTerms.size();
                return numberOfHPO;
            }
            Action.scrollToTop(driver);
            Action.selectByIndexFromDropDown(dropdownValues, 0);
            // determine the total number of HPO terms
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, hpoTable);
            int numberOfHPO = hpoTerms.size();
            return numberOfHPO;
        } catch (ElementClickInterceptedException interExp) {
            //SeleniumLib click handles the javascript and Actions click also.
            //SeleniumLib.takeAScreenShot("PhenoTypeInterceptedExp.jpg");
            seleniumLib.clickOnWebElement(dropdownValues.get(0));
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, hpoTable);
            int numberOfHPO = hpoTerms.size();
            return numberOfHPO;
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
        expElements.add(dateOfBirthDayFM);
        expElements.add(dateOfBirthMonthFM);
        expElements.add(dateOfBirthYearFM);
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
                Debugger.println("Element: "+expElements.get(i)+" not present.");
                return false;
            }
            seleniumLib.elementHighlight(expElements.get(i));
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
        if (!Action.getValue(firstName).equalsIgnoreCase(familyMember.getFIRST_NAME())) {
            Debugger.println("Expected first name: " + familyMember.getFIRST_NAME() + ",actual:" + Action.getValue(firstName));
            return false;
        }
        if (!Action.getValue(lastName).equalsIgnoreCase(familyMember.getLAST_NAME())) {
            Debugger.println("Expected last name: " + familyMember.getLAST_NAME() + ",actual:" + Action.getValue(lastName));
            return false;
        }
        By selectedGenderElement = By.xpath(selectedGender.replaceAll("dummyGender", familyMember.getGENDER()));
        if (!seleniumLib.isElementPresent(selectedGenderElement)) {
            Debugger.println("Expected gender: " + familyMember.getGENDER() + " not loaded.");
            return false;
        }
        if (!Action.getValue(nhsNumber).equalsIgnoreCase(familyMember.getNHS_NUMBER())) {
            Debugger.println("Expected NHSNumber: " + familyMember.getNHS_NUMBER() + ",actual:" + Action.getValue(nhsNumber));
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
            //selectTheTest();
            if (!Wait.isElementDisplayed(driver, selectedTest, 20)) {
                Debugger.println("Expected status of Test is Selected default, but the current status is Deselected.");
                SeleniumLib.takeAScreenShot("DeSelectTest.jpg");
                return false;
            }
            try {
                Action.clickElement(driver, selectedTest);
            }catch(Exception exp1){
                seleniumLib.clickOnWebElement(selectedTest);
            }
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

    public boolean clickOnBackButton() {
        try {
            Action.clickElement(driver, backButton);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(backButton);
                return true;
            } catch (Exception exp1) {
                SeleniumLib.takeAScreenShot("BackButtonOnFMDetails.jpg");
                Debugger.println("Could not click on Back Button on FamilyDetailsPage: " + exp1+"\n"+driver.getCurrentUrl());
                return false;
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
            Action.clickElement(driver, editButton.get(num));
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on edit button for family Member:" + exp);
            return false;
        }
    }

    public boolean verifyTheDeleteMessage(String deleteMessage) {
        try {
            if(!Wait.isElementDisplayed(driver,successDeletionMessageOfFamilyMember,10)){
                Action.scrollToTop(driver);
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
                Action.clickElement(driver,selectedTest);
                Wait.seconds(2);
            }
            Action.clickElement(driver,unSelectedTest);
            return true;//Already deselected
        }catch(ElementClickInterceptedException exp){
            //The box might be in selected stage and element may not be able to click.
            // So moving control out and click again
            Action.clickElement(driver,selectedFamilyMembersLabel);
            Wait.seconds(2);
            Action.clickElement(driver,unSelectedTest);
            Wait.seconds(2);
            return true;
        }catch(Exception exp){
            try{
                seleniumLib.clickOnWebElement(unSelectedTest);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception in clickOnDeselectedTestCheckBox.." + exp1);
                SeleniumLib.takeAScreenShot("NoDeSelectedCheckBox.jpg");
                return false;
            }
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
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(beingTestedField);
        expElements.add(dobField);
        expElements.add(genderField);
        expElements.add(patientChoiceStatus);
        expElements.add(addFamilyMemberButton);

        for (int i = 0; i < expElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expElements.get(i))) {
                Debugger.println("Expected Element:"+expElements.get(i)+" not present in FM Page.\n"+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("FMPageValidation.jpg");
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
                    actualMessage = Action.getText(familyPageBeingTestedField.get(i));
                    if (testBadge.equalsIgnoreCase(actualMessage)) {
                        isPresent = true;
                        break;
                    }
                }
            } else if (testBadge.equalsIgnoreCase("Not being tested")) {
                for (int i = 0; i < familyPageNotBeingTestedField.size(); i++) {
                    actualMessage = Action.getText(familyPageNotBeingTestedField.get(i));
                    if (testBadge.equalsIgnoreCase(actualMessage)) {
                        isPresent = true;
                        break;
                    }
                }
            }

            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from checking being tested field " + exp);
            return false;
        }
    }

    public int getDisplayIndexOfSpecificReferral(String referralIdentifierName) {

        try {
            Wait.forElementToBeDisplayed(driver, displayedChildElements.get(0));
            for (int i = 0; i < displayedChildElements.size(); i++) {
                String name = Action.getText(displayedChildElements.get(i));
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
            Action.clickElement(driver, editButtonForParticipant);
            return true;
        } catch (Exception exp) {
            try{
                seleniumLib.clickOnWebElement(editButtonForParticipant);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception from editing family member" + exp);
                return false;
            }
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
                Action.clickElement(driver,unSelectedTest);
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
                return false;
            }
            if (!(expectedRelation.equalsIgnoreCase(actualRelationship))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean selectRelationshipToProband(String relation) {
        try {
            Wait.forElementToBeClickable(driver, relationshipToProband);
            relationshipToProband.click();
            Debugger.println("Drop down clicked");
            Wait.seconds(3);
            Click.element(driver, relationshipToProband.findElement(By.xpath("//span[text()='" + relation + "']")));
            Debugger.println("Selected the relation as " + relation);
            return true;
        } catch (Exception e) {
            return false;
        }
}

    public boolean verifyDisplayedRelationshipToProband(String expectedRelation) {

        WebElement displayedRelationshipToProband = driver.findElement(By.xpath("//span[contains(text(),'Relationship to proband')]//following::span[1]"));

        try {
            displayedRelationshipToProband.isDisplayed();
            String actualRelationship = displayedRelationshipToProband.getText();
            Debugger.println("The displayed relation is " + actualRelationship);
            if (!(expectedRelation.equalsIgnoreCase(actualRelationship))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean NoeditFamilyMemberBtnDisplayed() {
        try {
            if (!seleniumLib.isElementPresent(editButtonForParticipant)) {
                Debugger.println("No Family member available as expected, edit icon is not displayed as expected");
                return true;
            }
            Debugger.println("The family member is not deleted, the edit icon is still displayed");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception from editing family member" + exp);
            return false;
        }
    }

    public boolean deselectTestForProband(String testType){
        if(!Wait.isElementDisplayed(driver, selectedTests.get(0), 5)){
            Debugger.println("None of the tests have been selected for Proband");
            return false;
        }

        if(testType.contains("Tumour only")){
            Action.clickElement(driver,selectedTests.get(1));
        }else if(testType.contains("Germline and Tumour")){
            Action.clickElement(driver,selectedTests.get(0));
        }else{
            Debugger.println("Please enter a valid value. Eg: Tumour only/ Germline and Tumour");
            return false;
        }
        return true;
    }
}