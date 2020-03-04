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

    @FindBy(xpath = "//h2[@class='css-1ujfcb9']")
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

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-years')]")
    public WebElement ageOfOnsetYearsField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-months')]")
    public WebElement ageOfOnsetMonthsField;

    @FindBy(xpath = "//label[contains(text(),'Find an HPO phenotype or code')]/..//input")
    public WebElement hpoSearchField;

    @FindBy(css = "div[id*='react-select']")
    public List<WebElement> dropdownValues;

    @FindBy(xpath = "//div[contains(@class,'test-list')]//span[contains(@class,'checked')]")
    WebElement testPackageCheckBoxChecked;

    @FindBy(xpath = "//div[contains(@class,'test-list')]//span[contains(@class,'checkbox')]")
    WebElement testPackageCheckBox;

    @FindBy(css = "span[class*='child-element']")
    List<WebElement> displayedChildElements;

    @FindBy(xpath = "//button[@aria-label='edit button']")
    WebElement editButtonForParticipant;

    By selectedTest = By.xpath("//div[contains(@class,'test-list_')]//span[contains(@class,'checked')]");
    By unSelectedTest = By.xpath("//div[contains(@class,'test-list_')]//span[contains(@class,'checkbox-card')]");
    String selectedTestTitle = "//h3[contains(text(),'Selected tests for')]/span[contains(text(),";
    String selectedMemberTitle = "//h4[contains(text(),'Selected family members')]/..//span[contains(text(),";
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

    @FindBy(xpath = "//span[contains(@aria-labelledby,'nhsNumber')]")
    public List<WebElement> nhsNumberResults;

    @FindBy(xpath = "//span[contains(@aria-labelledby,'gender')]")
    public List<WebElement> genderResults;

    @FindBy(xpath = "//span[contains(@aria-labelledby,'ngisId')]")
    public List<WebElement> ngisIdResults;

    @FindBy(xpath = "//span[contains(text(),'Being tested')]/ancestor::span[contains(@class,'css-1')][1]")
    public List<WebElement> familyPageBeingTestedField;

    @FindBy(xpath = "//span[contains(text(),'Not being tested')]/ancestor::span[contains(@class,'css-')][1]")
    public List<WebElement> familyPageNotBeingTestedField;

    @FindBy(xpath = "//div[@class='styles_error-message__text__1v2Kl']")
    public WebElement oneParticipantMsg;

    @FindBy(xpath = "//div[@class='styles_test-list-item__info-message__2RWQ9']")
    public WebElement multipleParticipantMsg;

    @FindBy(xpath = "//h1[contains(text(),'Select ')]")
    WebElement testPackagePageTitle;

    //For PatientInformation Identifiers
    public static int noOfPatientsForIdentification = 0;
    String patientList = "//div[contains(@class,'styles_participant-list_')]/div[contains(@class,'css-')]";
    String firstNameLastName = "//div[contains(@class,'styles_participant-list_')]//span[contains(@class,'css-')]//h2";
    String probandBeingTested = "//div[contains(@class,'styles_participant-list_')]//span[contains(@class,'child-element')]";
    String bornInformation = "//span[contains(@id,'dateOfBirth')]";
    String genderInformation = "//span[contains(@id,'gender')]";
    String nhsNumberInformation = "//span[contains(@id,'nhsNumber')]";
    String ngsIdInformation = "//span[contains(@id,'ngisId')]";
    String patientChoiceInformation = "//span[contains(@id,'patientChoiceStatus')]";
    String editButtonInformation = "//button[@aria-label='edit button']";
    String removeButtonInformation = "//button[@aria-label='remove button']";
    String subTitleMessage = "//p[contains(text(),\"dummyTitle\")]";
    String subTitleLink = "//a[contains(text(),\"dummyLink\")]";

    @FindBy(css = "table[class*='table--hpo']")
    public WebElement hpoTable;
    @FindBy(css = "[class*='hpo-term__name']")
    public List<WebElement> hpoTerms;

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

    public void clickPatientCard() {
        Wait.forElementToBeDisplayed(driver, patientCard);
        patientCard.click();
    }

    public void fillTheRelationshipToProband(String relationToProband) {
        try {
            validationErrors.clear();
            Actions.scrollToTop(driver);
            if (!Wait.isElementDisplayed(driver, relationshipToProbandDropdown, 60)) {
                Debugger.println("FamilyMemberDetailsPage:relationshipToProbandDropdown element not displayed even after waiting period.");
                return;
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
                    return;
                }
                seleniumLib.clickOnWebElement(dropdownValue.findElement(ddElement));
            }

        } catch (Exception exp) {
            Debugger.println("Exception in selecting Relationship to Proband:" + exp);
        }
    }

    public boolean verifyTheTestAndDetailsOfAddedFamilyMember(NGISPatientModel familyMember) {
        try {
            if (familyMember == null) {
                Debugger.println("Family Member cannot be null.");
                return false;
            }

            Debugger.println("Verifying Relationship Title");
            //1. Verify the display of Title for the added Test.
            By testTitle = By.xpath(selectedTestTitle + "'" + familyMember.getRELATIONSHIP_TO_PROBAND() + "')]");
            if (!Wait.isElementDisplayed(driver, driver.findElement(testTitle), 120)) {
                Debugger.println("Selected Test Title for Family member with Relation " + familyMember.getRELATIONSHIP_TO_PROBAND() + " not displayed." + testTitle);
                return false;
            }
            Debugger.println("Verifying Relationship to proband");
            //2. Verify the display of Relation to Proband as given.
            By selectedFamilyMember = By.xpath(selectedMemberTitle + "'" + familyMember.getRELATIONSHIP_TO_PROBAND() + "')]");
            if (!Wait.isElementDisplayed(driver, driver.findElement(selectedFamilyMember), 120)) {
                Debugger.println("Selected Family member with Relation " + familyMember.getRELATIONSHIP_TO_PROBAND() + " not displayed.");
                return false;
            }
            Debugger.println("Verifying Selected Test");
            //3. Select the test as checked by default.
            if (!Wait.isElementDisplayed(driver, driver.findElement(selectedTest), 120)) {
                if (!Wait.isElementDisplayed(driver, driver.findElement(unSelectedTest), 120)) {
                    Debugger.println("Option to select test not present in Select Test Page.");
                    return false;
                } else {
                    Actions.clickElement(driver, driver.findElement(unSelectedTest));//To make the test selected by default.
                }
            }
            Debugger.println("Verified Test selection Page successfully");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying selected test title:" + exp);
            return false;
        }
    }

    public boolean fillFamilyMemberDiseaseStatusWithGivenParams(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        //DiseaseStatus handling as the first item, otherwise some overlay element visible on top of this and creating issue in clicking on the same.

        if (paramNameValue.get("DiseaseStatus") != null && !paramNameValue.get("DiseaseStatus").isEmpty()) {
            Debugger.println("Updating Disease Status ....");
            try {
                Click.element(driver, diseaseStatusDropdown);
                Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + paramNameValue.get("DiseaseStatus") + "']")));
            } catch (Exception exp) {
                Debugger.println("Exception from selecting disease from the disease dropdown...:" + exp);
                SeleniumLib.takeAScreenShot("DiseaseDropDown.jpg");
                return false;
            }
        }
        boolean isHpoSelected = true;
        for (String key : paramsKey) {
            if (key.equalsIgnoreCase("DiseaseStatus")) {
                continue;
            }
            switch (key) {
                case "AgeOfOnset": {
                    Debugger.println("Updating Age of Onset ....");
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        String[] age_of_onsets = paramNameValue.get(key).split(",");
                        ageOfOnsetYearsField.sendKeys(age_of_onsets[0]);
                        ageOfOnsetMonthsField.sendKeys(age_of_onsets[1]);
                    }
                    break;
                }
                case "HpoPhenoType": {
                    Debugger.println("Updating Phenotype ....");
                    isHpoSelected = false;//Consider only if HPO Passed as an argument
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        //Check whether the given Phenotype already added to the patient, if yes no need to enter again.
                        isHpoSelected = isHPOAlreadyConsidered(paramNameValue.get(key));
                        if (!isHpoSelected) {
                            Debugger.println("Selecting Phenotype.... ....");
                            if (searchAndSelectRandomHPOPhenotype(paramNameValue.get(key)) > 0) {
                                Debugger.println("Phenotype Selected....");
                                isHpoSelected = true;
                            }
                        }
                    }
                    break;
                }
            }//switch
        }//for
        return isHpoSelected;
    }//method

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
            if (seleniumLib.isElementPresent(hpoSearchField)) {
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
            String landingPageRelation = landingPageRelationPath.replaceAll("dummyRelation", familyMember.getRELATIONSHIP_TO_PROBAND());
            By relationToProband = By.xpath(landingPageRelation);
            if (!seleniumLib.isElementPresent(relationToProband)) {
                Debugger.println("Added Family member relationship: " + familyMember.getRELATIONSHIP_TO_PROBAND() + " Not displayed on Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("FMLandingPage.jpg");
                return false;
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
            NGISPatientModel familyMember = getFamilyMember(nhsDetails);
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

    public void deSelectTheTest() {
        try {
            if (Wait.isElementDisplayed(driver, testPackageCheckBoxChecked, 10)) {
                seleniumLib.clickOnWebElement(testPackageCheckBoxChecked);
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
            Actions.scrollToTop(driver);
            Wait.forElementToBeDisplayed(driver, successDeletionMessageOfFamilyMember);
            Assert.assertEquals(deleteMessage, successDeletionMessageOfFamilyMember.getText());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying the Family Member Removal Message: " + exp);
            return false;
        }
    }

    public boolean verifyTheDeleteMessageIsNotPresent() {
        if (Wait.isElementDisplayed(driver, successDeletionMessageOfFamilyMember, 5)) {
            return false; //Not supposed to present
        }
        return true;
    }

    public void deselectCheckBoxOnFamilyPage() {
        Wait.forElementToBeDisplayed(driver, testPackageCheckBox);
        seleniumLib.clickOnWebElement(testPackageCheckBox);
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
            if (!isPresent) {
                Debugger.println("Expected Message:" + expMessage + " not present.");
                SeleniumLib.takeAScreenShot("MessageNotPresent.jpg");
            }
            return isPresent;
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
            for (int i = 0; i < nameResults.size(); i++) {
                familyMemberLandingPageDetails.add(nameResults.get(i).getText());
                familyMemberLandingPageDetails.add(nhsNumberResults.get(i).getText());
                familyMemberLandingPageDetails.add(genderResults.get(i).getText());
                familyMemberLandingPageDetails.add(ngisIdResults.get(i).getText());
            }
            Debugger.println("No of Patient Details Read from FamilyMember LAnding Page: " + familyMemberLandingPageDetails.size());
        } catch (Exception exp) {
            Debugger.println("Exception from reading the Family Member Details from FamilyMember Landing Page:" + exp);
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
            for (int i = 0; i < nameResults.size(); i++) {
                printFormsPageDetails.add(nameResults.get(i).getText());
                printFormsPageDetails.add(nhsNumberResults.get(i).getText());
                printFormsPageDetails.add(genderResults.get(i).getText());
                printFormsPageDetails.add(ngisIdResults.get(i).getText());
            }
            Debugger.println("No of Patient Details Read from Print Form Page: " + familyMemberLandingPageDetails.size());
        } catch (Exception exp) {
            Debugger.println("Exception from reading the Family Member Details from Print Form Page:" + exp);
        }
    }

    public void readPatientDetailsInPatientChoicePage() {
        //Reading the Details of Patient Choice to Compare with FamilyMemberLandingPage and PrintForms later.
        Wait.forElementToBeDisplayed(driver, patientChoicePageTitle);
        patientChoicePageDetails = new ArrayList<String>();
        try {
            for (int i = 0; i < nameResults.size(); i++) {
                patientChoicePageDetails.add(nameResults.get(i).getText());
                patientChoicePageDetails.add(nhsNumberResults.get(i).getText());
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
            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(color);
            //Being test field color
            String actualMessage = "";
            String actualColor = "";
            boolean isPresent = false;
            if (testBadge.equalsIgnoreCase("Being tested")) {
                for (int i = 0; i < familyPageBeingTestedField.size(); i++) {
                    actualMessage = Actions.getText(familyPageBeingTestedField.get(i));
                    actualColor = familyPageBeingTestedField.get(i).getCssValue("background-color");
                    if (testBadge.equalsIgnoreCase(actualMessage) &&
                            expectedFontColor.equalsIgnoreCase(actualColor)) {
                        isPresent = true;
                    } else {
                        isPresent = false;
                        break;
                    }
                }
            } else if (testBadge.equalsIgnoreCase("Not being tested")) {
                for (int i = 0; i < familyPageNotBeingTestedField.size(); i++) {
                    actualMessage = Actions.getText(familyPageNotBeingTestedField.get(i));
                    actualColor = familyPageNotBeingTestedField.get(i).getCssValue("background-color");
                    if (testBadge.equalsIgnoreCase(actualMessage) &&
                            expectedFontColor.equalsIgnoreCase(actualColor)) {
                        isPresent = true;
                    } else {
                        isPresent = false;
                        break;
                    }
                }
            }
            if (!isPresent) {
                SeleniumLib.takeAScreenShot("BadgeBackgroundColor.jpg");
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

    public boolean verifyPatientIdentifiersInFamilyMemberLandingPage(String patientNo) {
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
            Wait.seconds(2);
            List<WebElement> nameList = seleniumLib.getElements(By.xpath(firstNameLastName));
            if (nameList == null || nameList.size() != Integer.parseInt(patientNo)) {
                Debugger.println("Expected Presence of First/Last Name field for " + patientNo + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("firstLastNameLst.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> probandTestedList = seleniumLib.getElements(By.xpath(probandBeingTested));
            if (probandTestedList == null || probandTestedList.size() != (Integer.parseInt(patientNo) * 2)) {
                Debugger.println("Expected Presence of Proband and Being Tested Information for " + patientNo + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("probandTested.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> bornList = seleniumLib.getElements(By.xpath(bornInformation));
            if (bornList == null || bornList.size() != (Integer.parseInt(patientNo) + 1)) {
                Debugger.println("Expected Presence of Born Information for " + patientNo + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("bornInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> genderList = seleniumLib.getElements(By.xpath(genderInformation));
            if (genderList == null || genderList.size() != (Integer.parseInt(patientNo) + 1)) {
                Debugger.println("Expected Presence of Gender Information for " + patientNo + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("genderInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> ngisList = seleniumLib.getElements(By.xpath(ngsIdInformation));
            if (ngisList == null || ngisList.size() != (Integer.parseInt(patientNo) + 1)) {
                Debugger.println("Expected Presence of NGSID Information for " + patientNo + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("ngsInfo.jpg");
                return false;
            }
            Wait.seconds(2);
            List<WebElement> pchoiceList = seleniumLib.getElements(By.xpath(patientChoiceInformation));
            Debugger.println("npcgis: " + pchoiceList.size());
            if (pchoiceList == null || pchoiceList.size() != Integer.parseInt(patientNo)) {
                Debugger.println("Expected Presence of PatientChoice Information for " + patientNo + " patients in  Family Member Landing Page.");
                SeleniumLib.takeAScreenShot("pchoiceInfo.jpg");
                return false;
            }
            //EDIT and REMOVE BUTTON
            Wait.seconds(2);
            List<WebElement> editButtonList = seleniumLib.getElements(By.xpath(editButtonInformation));
            if (editButtonList != null) {
                Debugger.println("editbut: " + editButtonList.size());
                if (editButtonList.size() != (Integer.parseInt(patientNo) - 1)) {
                    Debugger.println("Expected Presence of Edit Information for " + (Integer.parseInt(patientNo) - 1) + " patients in  Family Member Landing Page.");
                    SeleniumLib.takeAScreenShot("editButtonInfo.jpg");
                    return false;
                }
            }
            Wait.seconds(2);
            List<WebElement> removeButtonList = seleniumLib.getElements(By.xpath(removeButtonInformation));
            if (removeButtonList != null) {
                Debugger.println("removebut: " + removeButtonList.size());
                if (removeButtonList.size() != (Integer.parseInt(patientNo) - 1)) {
                    Debugger.println("Expected Presence of Remove Information for " + (Integer.parseInt(patientNo) - 1) + " patients in  Family Member Landing Page.");
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
                    if (key.equalsIgnoreCase("NHSNumber")) {
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
            if (Wait.isElementDisplayed(driver, testPackageCheckBox, 10)) {
                seleniumLib.clickOnWebElement(testPackageCheckBox);
                return true;
            }
            return false;
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("testSelect.jpg");
            Debugger.println("FamilyMemberDetailsPage:selectTheTest:Exception:" + exp);
            return false;
        }
    }

    public boolean verifyThePageTitlePresence(String testPageTitle) {
        try {
            seleniumLib.waitForElementVisible(testPackagePageTitle);
            if (!testPageTitle.equalsIgnoreCase(testPackagePageTitle.getText())) {
                Debugger.println("Expected Subtitle: " + testPageTitle + ", but Actual Subtitle is: " + testPackagePageTitle.getText());
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Family Member:Test Package Title Not found " + exp);
            SeleniumLib.takeAScreenShot("FMTestPageTitle.jpg");
            return false;
        }
    }


}//ends
