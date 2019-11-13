package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NGISPatient;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.security.ssl.Debug;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FamilyMemberDetailsPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(id = "nhsNumber")
    public WebElement nhsNumber;

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

    @FindBy(css = "*[class*='helix']")
    public List<WebElement> helix;

    String helixIcon = "*[class*='helix']";

    @FindBy(css = "div[class*='error-message__text']")
    public List<WebElement> validationErrors;

    @FindBy(xpath = "(//label[text()='Relationship to proband']//following::div)[1]")
    public WebElement relationshipToProbandDropdown;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(xpath = "//*[contains(@id,'question-id-q96')]")
    public WebElement diseaseStatusDropdown;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-years')]")
    public WebElement ageOfOnsetYearsField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-months')]")
    public WebElement ageOfOnsetMonthsField;

    @FindBy(css = "#react-select-3-input")
    public WebElement hpoSearchField;

    @FindBy(css = "table[class*='table--hpo']")
    public WebElement hpoTable;

    @FindBy(css = "[class*='hpo-term__name']")
    public List<WebElement> hpoTerms;
    @FindBy(css = "div[id*='react-select']")
    public List<WebElement> dropdownValues;

    static NGISPatient familyMember;

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
            Debugger.println("Exception in searchPatientDetailsUsingNHSNumberAndDOB "+exp);
        }
    }
    public void clickPatientCard() {
        Wait.forElementToBeDisplayed(driver, patientCard);
        patientCard.click();
    }

    public void clickOnSaveAndContinueButton() {
        Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
        Click.element(driver, saveAndContinueButton);
        if (helix.size() > 0) {
            Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
        }
    }
    public boolean checkTheErrorMessageForInvalidField(String errorMessage, String fontColor) {
        try {

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
        Click.element(driver, relationshipToProbandDropdown);
        Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='"+relationToProband+"']")));
        //Here reading the Family member details to verify in next page  - whether details loaded with the expected details
        familyMember = new NGISPatient();
        try {
            By firstLastName = By.xpath("//h1[contains(text(),'Confirm family member details')]/..//h2");
            if (seleniumLib.isElementPresent(firstLastName)) {
                String f_l_name = seleniumLib.getText(firstLastName);
                familyMember.setFIRST_NAME(f_l_name.split(",")[0].trim());
                familyMember.setLAST_NAME(f_l_name.split(",")[1].trim());
            }
            By gender = By.xpath("//h1[contains(text(),'Confirm family member details')]/..//ul/li/span[contains(text(),'Gender')]/following-sibling::span");
            if (seleniumLib.isElementPresent(gender)) {
                familyMember.setGENDER(seleniumLib.getText(gender));
            }
            familyMember.setRELATIONSHIP_TO_PROBAND(relationToProband);
        }catch(Exception exp){
            Debugger.println("Exception in Reading Family Details from Confirmation Page. "+exp);
        }
    }
    public boolean verifyTheTestAndDetailsOfAddedFamilyMember(){
        Wait.seconds(5);
        //1. Verify the display of Title for the added Test.
        By testTitle = By.xpath("//h3[contains(text(),'Selected tests for')]/span[contains(text(),'"+familyMember.getRELATIONSHIP_TO_PROBAND()+"')]");
        if(!seleniumLib.isElementPresent(testTitle)){
            Debugger.println("Selected Test Title for Family member with Relation "+familyMember.getRELATIONSHIP_TO_PROBAND()+" not displayed.");
            return false;
        }
        //2. Verify the display of Relation to Proband as given.
        By selectedFamilyMember = By.xpath("//h4[contains(text(),'Selected family members')]/..//span[contains(text(),'"+familyMember.getRELATIONSHIP_TO_PROBAND()+"')]");
        if(!seleniumLib.isElementPresent(selectedFamilyMember)){
            Debugger.println("Selected Family member with Relation "+familyMember.getRELATIONSHIP_TO_PROBAND()+" not displayed.");
            return false;
        }
        //3. Verify the select as checked by default.
        By isTestSelected = By.xpath("//div[contains(@class,'test-list_')]//span[contains(@class,'checked')]");
        if(!seleniumLib.isElementPresent(isTestSelected)){
            Debugger.println("Test for Family member with Relation "+familyMember.getRELATIONSHIP_TO_PROBAND()+" not in SELECTED State.");
            return false;
        }
        return true;
    }
    public boolean verifyAddedFamilyMemberDetailsInLandingPage(){
       //This step has to be implemented.
        return true;
    }

    public void fillFamilyMemberDiseaseStatusWithGivenParams(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "DiseaseStatus": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                         try {
                             Click.element(driver, diseaseStatusDropdown);
                             Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + paramNameValue.get(key) + "']")));
                         }catch(Exception exp){
                             Debugger.println("Exception...:"+exp);
                             //diseaseStatusDropdown.refresh();
                         }
                    }
                    break;
                }
                case "AgeOfOnset": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        String[] age_of_onsets = paramNameValue.get(key).split(",");
                        ageOfOnsetYearsField.sendKeys(age_of_onsets[0]);
                        ageOfOnsetMonthsField.sendKeys(age_of_onsets[1]);
                        Debugger.println("Age of Onset DONE:");
                    }
                    break;
                }
                case "HpoPhenoType": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        Debugger.println("Phenotype doing:");
                        //Check whether the given Phenotype already added to the patient, if yes no need to enter again.
                        String hpoValue="";
                        boolean isExists = false;
                        List<WebElement> hpoRows = seleniumLib.getElements(By.xpath("//table[contains(@class,'--hpo')]/tbody/tr"));
                        if(hpoRows != null && hpoRows.size() > 0){
                            for(WebElement row:hpoRows){
                                hpoValue = row.findElement(By.xpath("./td[1]")).getText();
                                if(hpoValue.equalsIgnoreCase(paramNameValue.get(key))){
                                    isExists = true;
                                    Debugger.println("Phenotype already exists:");
                                    break;//for loop
                                }
                                //Ideally we need to check where Present is selected or not also.
                                ////table[contains(@class,'--hpo')]/tbody/tr[1]/td[2]//input[@value='Present']
                            }//for
                        }
                        if(!isExists) {
                            Debugger.println("Phenotype not exists adding...:");
                            searchAndSelectRandomHPOPhenotype(paramNameValue.get(key));//Re-using existing method
                        }
                    }
                    break;
                }

            }//switch
        }//for
    }//method
    public int searchAndSelectRandomHPOPhenotype(String hpoTerm) {
        Wait.seconds(5);
        new org.openqa.selenium.interactions.Actions(driver).moveToElement(hpoSearchField).perform();
        Actions.fillInValue(hpoSearchField, hpoTerm);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectByIndexFromDropDown(dropdownValues, 0);
        // determine the total number of HPO terms
        Wait.seconds(2);
        Wait.forElementToBeDisplayed(driver, hpoTable);
        int numberOfHPO = hpoTerms.size();
        return numberOfHPO;
    }

}//ends
