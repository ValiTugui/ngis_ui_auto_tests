package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.sql.Driver;
import java.util.*;
import java.util.List;

public class ClinicalQuestionsPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    public ClinicalQuestionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//*[contains(@id,'question-id-q96')]")
    public WebElement diseaseStatusDropdown;

    @FindBy(xpath = "//*[contains(@id,'question-id-q96')]//div[contains(@class,'indicatorContainer')][1]")
    public WebElement diseaseStatusCloseIcon;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(css = "div[id*='react-select']")
    public List<WebElement> dropdownValues;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-years')]")
    public WebElement ageOfOnsetYearsField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q97-months')]")
    public WebElement ageOfOnsetMonthsField;

    //Adding family members in a loop, the orginal xpath is getting changed
    @FindBy(xpath = "//input[contains(@id,'react-select-')]")
    public List<WebElement> hpoSearchField;

    @FindBy(css = "*[class*='hpo-term__name']")
    public List<WebElement> hpoTermNames;

    @FindBy(xpath = "//table[contains(@class,'table--hpo')]")
    public WebElement hpoTable;

    @FindBy(css = "[class*='hpo-term__name']")
    public List<WebElement> hpoTerms;

    @FindBy(xpath = "//label[contains(@class,'radio')]")
    public List<WebElement> radioButtons;

    @FindBy(xpath = "//td[contains(@class,'hpo-term__modifiers')]//child::div")
    public WebElement hpoModifiersDropdown;

    @FindBy(xpath = "//*[contains(@class,'multiValue')]")
    public WebElement hpoModifierValue;

    @FindBy(xpath = "//span[contains(@class,'radio__text')]")
    public List<WebElement> radioButtonsTexts;

    @FindBy(css = "[checked]")
    public List<WebElement> getSelectedRadioButtonsOnClinicalQuestions;

    @FindBy(xpath = "//*[contains(@id,'question-id-q90')]")
    public WebElement phenotypicSexDropdown;

    @FindBy(xpath = "//*[contains(@id,'question-id-q91')]")
    public WebElement karyotypicSexDropdown;

    @FindBy(css = "[class*='hpo-term__delete']")
    public List<WebElement> hpoTermsDeleteIcons;

    @FindBy(xpath = "//input[@id='unit-id-clinical_questions-QR06-13.answers[0].question-id-q111']")
    public WebElement diagnosisValue;

    @FindBy(xpath = "//input[@id='unit-id-clinical_questions-QR06-13.answers[1].question-id-q111']")
    public WebElement diagnosisValueSecondField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q111')]//child::div")
    public WebElement diagnosisField;

    @FindBy(xpath = "//div[@id='unit-id-clinical_questions-QR06-13.answers[0].question-id-q111']//div[@class='css-1hwfws3']")
    public WebElement cancelDiagnosisValue;

    @FindBy(css = "div[class*='error-message__text']")
    public WebElement nonNullableFieldErrorMessage;

    @FindBy(css = "div[class*='hpo__search']")
    public WebElement hpoSectionLabel;

    @FindBy(xpath = "//span[contains(text(),'Omim')]")
    public WebElement omimRadioButton;

    @FindBy(xpath = "//span[contains(text(),'Omim')]")
    public List<WebElement> omimRadioButtons;

    @FindBy(css = "[class*='switchable-enum']")
    public WebElement rareDiseaseDiagnosisTable;

    @FindBy(xpath = "//span[contains(text(),'Orphanet')]")
    public WebElement orphanetRadioButton;

    @FindBy(xpath = "//span[contains(text(),'Orphanet')]")
    public List<WebElement> orphanetRadioButtons;

    @FindBy(xpath = "//label[contains(@class,'switchable-enum__radio')]")
    public List<WebElement> rareDiseaseDiagnosesRadioButtons;

    @FindBy(xpath = "//*[contains(@id,'question-id-q114')]")
    public WebElement rareDiseaseDiagnosisStatusDropdown;

    @FindBy(xpath = "//div[contains(@id,'question-id-q114')]")
    public List<WebElement> rareDiseaseDiagnosisStatusDropdowns;

    @FindBy(css = "*[data-testid*='notification-error']")
    public WebElement hpoErrorNotification;

    @FindBy(xpath = "//button/span[contains(text(), '+ Add another')]")
    public WebElement addAnotherRareDiseaseLink;

    String hpoSectionMarkedAsMandatoryToDO = "HPO phenotype or code ✱";
    By hpoRows = By.xpath("//table[contains(@class,'--hpo')]/tbody/tr");

    String selectSingleValue = "div[class*='singleValue']";
    String tagName = "span";
    @FindBy(css = "*[class*='styles_field-label__']")
    public List<WebElement> genericFieldLabels;

    @FindBy(xpath = "//div[contains(@class,'styles_hpo-select')]//div[contains(@class,'placeholder')]")
    WebElement hpoPlaceHolder;

    String dropDownPlaceHolderText = "//label[text()='dummyValue']//following::div[contains(@id,'answers.question-id')][1]";
    @FindBy(xpath = "//h2[contains(@class,'group__heading')]")
    public List<WebElement> fieldHeaders;

    public static String genderValue;

    public boolean verifyTheCountOfHPOTerms(int minimumNumberOfHPOTerms) {
        try {
            Wait.forElementToBeDisplayed(driver, hpoTable);
            int actualNumberOfHPOTerms = hpoTerms.size();
            return actualNumberOfHPOTerms >= minimumNumberOfHPOTerms;
        }catch(Exception exp){
            Debugger.println("Exception from verifyTheCountOfHPOTerms:"+exp);
            return false;
        }
    }

    public int searchAndSelectRandomHPOPhenotype(String hpoTerm) {
        Wait.seconds(5);
        try {
            if (hpoSearchField.size() < 1) {
                return 0;
            }
            if (!Wait.isElementDisplayed(driver, hpoSearchField.get(0), 10)) {
                //Scroll to the element and try
                SeleniumLib.scrollToElement(hpoSearchField.get(0));
                if (!Wait.isElementDisplayed(driver, hpoSearchField.get(0), 10)) {
                    Actions.scrollToTop(driver);
                }
            }
            hpoSearchField.get(0).sendKeys(hpoTerm);
            if (!Wait.isElementDisplayed(driver, dropdownValue, 10)) {
                return 0;
            }
            if (dropdownValues.size() < 1) {
                return 0;
            }
            Actions.selectByIndexFromDropDown(dropdownValues, 0);
            // determine the total number of HPO terms Loaded - If selected, it would be minimum one
            Wait.seconds(2);
            int numberOfHPO = hpoTerms.size();
            if (numberOfHPO < 1) {
                //Scrolling to search field and Selecting as some time overlay observed while running from jenkins
                SeleniumLib.scrollToElement(hpoSearchField.get(0));
                Actions.selectByIndexFromDropDown(dropdownValues, 0);
                // determine the total number of HPO terms Loaded - If selected, it would be minimum one
                Wait.seconds(2);
            }
            if (numberOfHPO < 1) {
                return 0;
            }
            return numberOfHPO;
        } catch (TimeoutException exp) {
            //One reason observed is the overlay of global patient card on phenotype element...so trying to scroll down and select
            //Scroll to Top also may cause the same issue, so scrolling to previous element and trying
            SeleniumLib.scrollToElement(ageOfOnsetYearsField);
            Actions.selectByIndexFromDropDown(dropdownValues, 0);
            return 0;
        } catch (Exception exp) {
            return 0;
        }
    }

    public boolean verifySpecificHPOTermDisplayedInTheFirstRow(String expectedHPOTerm) {
        try {
            Wait.seconds(2);
            if (!Wait.isElementDisplayed(driver, hpoTable, 10)) {
                return false;
            }
            if (hpoTermNames.size() < 1) {
                return false;
            }
            String actualHPOTermDisplayedInTheFirstRow = hpoTermNames.get(0).getText();
            if (!actualHPOTermDisplayedInTheFirstRow.contains(expectedHPOTerm)) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    public String retrySelectingDiagnosis(String diagnosis) {
        try {
            seleniumLib.sendValue(diagnosisValue,diagnosis);
            seleniumLib.sleepInSeconds(3);
            if(dropdownValues.size() > 0){
                Actions.selectByIndexFromDropDown(dropdownValues, 0);
            }
            return "Success";
        } catch (Exception exp) {
            Debugger.println("Exception from searchAndSelectSpecificDiagnosis1:"+exp+"\n"+driver.getCurrentUrl());
            return "Exception from searchAndSelectSpecificDiagnosis1:";
        }
    }

    public String searchAndSelectSpecificDiagnosis(String diagnosis) {
        try {
            if(!Wait.isElementDisplayed(driver, diagnosisValue,30)){
                return null;
            }
            Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(diagnosisValue, diagnosis);
            if (!Wait.isElementDisplayed(driver, dropdownValue, 10)) {
                //Try again
                diagnosisValue.clear();
                Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(diagnosisValue, diagnosis);
                if (!Wait.isElementDisplayed(driver, dropdownValue, 10)) {
                    return null;
                }
            }
            Wait.seconds(2);
            boolean testResult = Actions.selectByIndexFromDropDown(dropdownValues, 0);
            if(!testResult){
                Debugger.println("Could not select the drop down value.."+driver.getCurrentUrl());
                return null;
            }
            Wait.seconds(2);
            String actText  = Actions.getText(diagnosisField);
            if(actText == null || !actText.equalsIgnoreCase(diagnosis)){
                By selectedField = By.xpath("//div[contains(@id,'id-q111')]//div[contains(@class,'singleValue')]//span/span");
                actText = seleniumLib.getText(selectedField);
            }
            return actText;
        } catch (Exception exp) {
            Debugger.println("Exception from searchAndSelectSpecificDiagnosis:"+exp+"\n"+driver.getCurrentUrl());
            return null;
        }
    }

    public void searchAndSelectSpecificDiagnosisSecondField(String diagnosis) {
        try {
            Wait.forElementToBeDisplayed(driver, diagnosisValueSecondField);
            Wait.seconds(2);
            Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(diagnosisValueSecondField, diagnosis);
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Wait.seconds(2);
            if (!Wait.isElementDisplayed(driver, dropdownValue, 10)) {
                Debugger.println("Diagnosis term " + diagnosis + " not present in the dropdown.");
            }
            Actions.selectByIndexFromDropDown(dropdownValues, 0);
            Wait.seconds(2);
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("RareDiseaseDiagnosisSecondField.jpg");
        }
    }


    public void clearRareDiseaseDiagnosisFieldByPressingBackspaceKey() throws AWTException {
        if (!Actions.getText(diagnosisField).isEmpty()) {
            diagnosisValue.click();
            Actions.clearField(diagnosisValue);
            Wait.forElementToBeDisplayed(driver, cancelDiagnosisValue);
        }
    }

    public boolean confirmRareDiseaseDiagnosisFieldIsEmpty(String diagnosisValue) {
        Wait.forElementToBeDisplayed(driver, diagnosisField);
        return (!diagnosisField.getText().equalsIgnoreCase(diagnosisValue));
    }

    public String selectDiseaseStatus(String diseaseStatusValue) {
        try {
            if (!Wait.isElementDisplayed(driver, diseaseStatusDropdown, 30)) {
                Actions.scrollToTop(driver);
            }
            String selectedValue = Actions.getText(diseaseStatusDropdown);
            if (selectedValue != null &&
                    selectedValue.equalsIgnoreCase(diseaseStatusValue)) {
                return selectedValue;//Already Selected the Specified Value
            }
            Actions.clickElement(driver, diseaseStatusDropdown);
            if(!Wait.isElementDisplayed(driver, dropdownValue,10)){
                return null;
            }
            Actions.selectValueFromDropdown(dropdownValue, diseaseStatusValue);
            selectedValue = Actions.getText(diseaseStatusDropdown);
            return selectedValue;
        } catch (Exception exp) {
            return null;
        }
    }

    public boolean confirmHPOPhenotypeSectionIsMarkedAsMandatory() {
        try {
            Wait.forElementToBeDisplayed(driver, hpoSectionLabel);
            return hpoSectionLabel.getText().contains(hpoSectionMarkedAsMandatoryToDO);
        } catch (Exception exp) {
            Debugger.println("Exception in confirmHPOPhenotypeSectionIsMarkedAsMandatory:" + exp);
            SeleniumLib.takeAScreenShot("confirmHPOPhenotypeSectionIsMarkedAsMandatory.jpg");
            return false;
        }

    }

    public boolean fillInYearsOfOnset(String years) {
        try {
            Wait.forElementToBeDisplayed(driver, ageOfOnsetYearsField);
            ageOfOnsetYearsField.clear();
            Actions.fillInValue(ageOfOnsetYearsField, years);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in fillInYearsOfOnset:" + exp);
            return false;
        }
    }

    public void clearValueFromYearsOfOnset() {
        Wait.forElementToBeDisplayed(driver, ageOfOnsetYearsField);
        Actions.clearTextField(ageOfOnsetYearsField);
    }

    public void clearValueFromMonthsOfOnset() {
        Wait.forElementToBeDisplayed(driver, ageOfOnsetMonthsField);
        Actions.clearTextField(ageOfOnsetMonthsField);
    }

    public boolean fillInMonthsOfOnset(String months) {
        try {
            Wait.forElementToBeDisplayed(driver, ageOfOnsetMonthsField);
            ageOfOnsetMonthsField.clear();
            Actions.fillInValue(ageOfOnsetMonthsField, months);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in fillInMonthsOfOnset:" + exp);
            return false;
        }
    }

    public String getErrorMessageText() {
        try {
            Wait.forElementToBeDisplayed(driver, nonNullableFieldErrorMessage);
            String actualErrorMessage = nonNullableFieldErrorMessage.getText();
            return actualErrorMessage;
        } catch (Exception exp) {
            Debugger.println("Exception from getErrorMessageText:" + exp);
            return "";
        }
    }

    public boolean checkNoErrorMessageIsDisplayed() {
        try {
            if (Wait.isElementDisplayed(driver, nonNullableFieldErrorMessage, 5)) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from checkNoErrorMessageIsDisplayed:" + exp);
            return false;
        }
    }

    //Method added by @Stag for filling the ClinicalQuestionsPage
    public boolean fillDiseaseStatusAgeOfOnsetAndHPOTerm(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        //DiseaseStatus
        String paramValue = paramNameValue.get("DiseaseStatus");
        if (paramValue != null && !paramValue.isEmpty()) {
            if(selectDiseaseStatus(paramNameValue.get("DiseaseStatus")) == null){
                return false;
            }
        }
        //AgeOfOnset
        paramValue = paramNameValue.get("AgeOfOnset");
        if (paramValue != null && !paramValue.isEmpty()) {
            String[] age_of_onsets = paramValue.split(",");
            try {
                ageOfOnsetYearsField.sendKeys(age_of_onsets[0]);
                ageOfOnsetMonthsField.sendKeys(age_of_onsets[1]);
            }catch(Exception exp){
                seleniumLib.sendValue(ageOfOnsetYearsField,age_of_onsets[0]);
                seleniumLib.sendValue(ageOfOnsetMonthsField,age_of_onsets[1]);
            }
        }
        //PhenoType
        boolean isFilled = false;
        boolean isPhenotypePresent = false;
        paramValue = paramNameValue.get("HpoPhenoType");
        if (paramValue != null && !paramValue.isEmpty()) {
            isPhenotypePresent = true;
            if (!(searchAndSelectRandomHPOPhenotype(paramValue) > 0)) {
                isFilled = isHPOAlreadyConsidered(paramValue);
            } else {
                isFilled = true;
            }
        }
        //PhenotypicSex
        paramValue = paramNameValue.get("PhenotypicSex");
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                Click.element(driver, phenotypicSexDropdown);
                Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + paramValue + "']")));
            } catch (Exception exp) {
                try{
                    seleniumLib.clickOnWebElement(phenotypicSexDropdown);
                    Wait.seconds(2);
                    seleniumLib.clickOnWebElement(dropdownValue.findElement(By.xpath("//span[text()='" + paramValue+ "']")));
                }catch(Exception exp1) {
                    Debugger.println("Exception from selecting phenotypicSexDropdown...:" + exp1);
                    return false;
                }
            }
        }
        //PhenotypicSex
        paramValue = paramNameValue.get("KaryotypicSex");
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                Click.element(driver, karyotypicSexDropdown);
                Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + paramValue + "']")));
            } catch (Exception exp) {
                try{
                    seleniumLib.clickOnWebElement(karyotypicSexDropdown);
                    Wait.seconds(2);
                    seleniumLib.clickOnWebElement(dropdownValue.findElement(By.xpath("//span[text()='" + paramValue+ "']")));
                }catch(Exception exp1) {
                    Debugger.println("Exception from selecting karyotypicSexDropdown...:" + exp1);
                    return false;
                }

            }
        }
        if (isPhenotypePresent) {
            return isFilled;
        } else {
            return true;
        }
    }//method

    public boolean fillDiseaseStatusAgeOfOnset(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "DiseaseStatus":
                    //DiseaseStatus
                    String paramValue = paramNameValue.get(key);
                    if (paramValue != null && !paramValue.isEmpty()) {
                        if (selectDiseaseStatus(paramNameValue.get("DiseaseStatus")) == null) {
                            return false;
                        }
                    }
                break;
                case "AgeOfOnset":
                    String dOBValue = paramNameValue.get(key);
                    if (dOBValue != null && !dOBValue.isEmpty()) {
                        String[] age_of_onsets = dOBValue.split(",");
                        seleniumLib.sendValue(ageOfOnsetYearsField, age_of_onsets[0]);
                        seleniumLib.sendValue(ageOfOnsetMonthsField, age_of_onsets[1]);
                    }
                break;
                case "HPOPhenoType":
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        if (!(searchAndSelectRandomHPOPhenotype(paramNameValue.get(key)) > 0)) {
                            isHPOAlreadyConsidered(paramNameValue.get(key));
                        }
                    }
                break;
            }
        }
        return true;
    }


    public boolean updateFamilyMemberClinicalDetails(String familyMemberClinicalDetails) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyMemberClinicalDetails);
        //DiseaseStatus
        String paramValue = paramNameValue.get("DiseaseStatus");
        if (paramValue != null && !paramValue.isEmpty()) {
            if (selectDiseaseStatus(paramNameValue.get("DiseaseStatus")) == null) {
                return false;
            }
        }
        //AgeOfOnset
        paramValue = paramNameValue.get("AgeOfOnset");
        if (paramValue != null && !paramValue.isEmpty()) {
            String[] age_of_onsets = paramValue.split(",");
            seleniumLib.sendValue(ageOfOnsetYearsField, age_of_onsets[0]);
            seleniumLib.sendValue(ageOfOnsetMonthsField, age_of_onsets[1]);
        }
        return true;
    }

    public boolean verifyClinicalQuestionsDetails(String clinicalInfo) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(clinicalInfo);
        Set<String> paramsKey = paramNameValue.keySet();
        String actValue = "";
        for (String key : paramsKey) {
            switch (key) {
                case "DiseaseStatus":
                    actValue = seleniumLib.getText(diseaseStatusDropdown);
                    if (!actValue.equalsIgnoreCase(paramNameValue.get(key))) {
                        Debugger.println("Expected :" + key + ": " + paramNameValue.get(key) + ", Actual:" + actValue);
                        return false;
                    }
                break;
                case "AgeOfOnset":
                    String actValueMonth = ageOfOnsetYearsField.getAttribute("value");
                    String actValueYear = ageOfOnsetMonthsField.getAttribute("value");
                    actValue = actValueMonth + "," + actValueYear;
                    if (!(actValue).equalsIgnoreCase(paramNameValue.get(key))) {
                        Debugger.println("Expected :" + paramNameValue.get(key) + ", Actual:" + actValue);
                        return false;
                    }
                break;
                case "HPOPhenoType":
                    Wait.seconds(2);
                    if (!Wait.isElementDisplayed(driver, hpoTable, 30)) {
                        return false;
                    }
                    for (WebElement hpoTerm : hpoTermNames) {
                        if (hpoTerm.getText().contains(paramNameValue.get(key))) {
                            return true;
                        }
                    }
                break;
            }//switch
        }//for
        return true;
    }
    public boolean verifyFamilyMemberClinicalQuestions(String clinicalInfo) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(clinicalInfo);
        Set<String> paramsKey = paramNameValue.keySet();
        String actValue = "";
        for (String key : paramsKey) {
            switch (key) {
                case "DiseaseStatus":
                    actValue = seleniumLib.getText(diseaseStatusDropdown);
                    if (!actValue.equalsIgnoreCase(paramNameValue.get(key))) {
                        Debugger.println("Expected :" + key + ": " + paramNameValue.get(key) + ", Actual:" + actValue);
                        return false;
                    }
                break;
                case "AgeOfOnset":
                    String actValueMonth = ageOfOnsetYearsField.getAttribute("value");
                    String actValueYear = ageOfOnsetMonthsField.getAttribute("value");
                    actValue = actValueMonth + "," + actValueYear;
                    if (!(actValue).equalsIgnoreCase(paramNameValue.get(key))) {
                        Debugger.println("Expected :" + paramNameValue.get(key) + ", Actual:" + actValue);
                        return false;
                    }
                break;
             }
        }
        return true;
    }


    public boolean isHPOAlreadyConsidered(String hpoTerm) {
        try {
            String hpoValue = "";
            boolean isExists = false;
            if (!seleniumLib.isElementPresent(hpoRows)) {
                return false;
            }
            Actions.scrollToTop(driver);
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
        } catch (Exception exp) {
            Debugger.println("Exception from checking whether the HPO is already considered or not: " + exp);
            return false;
        }
    }

    public boolean verifyMaxAllowedValuesHPOField(int maxAllowedValues) {
        try {
            Wait.seconds(5);
            if (hpoSearchField.size() < 1) {
                Debugger.println("HPO Phenotype not displayed.");
                return false;
            }
            Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(hpoSearchField.get(0), "Nephritis");
            Wait.forElementToBeDisplayed(driver, dropdownValues.get(0));
            Wait.seconds(2);
            int numberOfElements = dropdownValues.size();
            return numberOfElements <= maxAllowedValues;
        } catch (Exception exp) {
            Debugger.println("Oops unable to locate drop-down element value -> " + exp);
            return false;
        }
    }

    public boolean verifyMaxAllowedValuesOMIMField(int maxAllowedValues) {
        try {
            if (Actions.getText(diagnosisField).isEmpty()) {
                Actions.fillInValue(driver, diagnosisValue, "linear");
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Wait.seconds(10);
            }
            int numberOfElements = dropdownValues.size();
            return numberOfElements <= maxAllowedValues;
        } catch (Exception exp) {
            Debugger.println("Oops unable to locate drop-down element value -> " + exp);
            return false;
        }
    }

    public void clickCloseIcon() {
        Actions.scrollToTop(driver);//Added this line as click intercepted exception observed for closeIcon
        Wait.forElementToBeDisplayed(driver, diseaseStatusCloseIcon);
        Actions.clickElement(driver, diseaseStatusCloseIcon);
        Wait.seconds(2);
    }

    public String getDefaultValueOfDiseaseStatus() {
        if(!Wait.isElementDisplayed(driver,diseaseStatusDropdown,30)){
            Debugger.println("Disease Status drop down not displayed: "+driver.getCurrentUrl());
            return "";
        }
        return Actions.getText(diseaseStatusDropdown);
    }

    public boolean deleteHPOTerm(String hpoTerm) {
        Wait.seconds(2);
        Wait.forElementToBeDisplayed(driver, hpoTable);
        Wait.forElementToBeDisplayed(driver, hpoTermNames.get(0));
        String actualHPOTermDisplayedInTheFirstRow = hpoTermNames.get(0).getText();
        if (actualHPOTermDisplayedInTheFirstRow.contains(hpoTerm)) {
            Wait.forElementToBeDisplayed(driver, hpoTermsDeleteIcons.get(0));
            Actions.clickElement(driver, hpoTermsDeleteIcons.get(0));
            return true;
        } else {
            Debugger.println("unable to locate delete icon for the HPO terms : " + hpoTerm);
            SeleniumLib.takeAScreenShot("HPOTermsTable.jpg");
            return false;

        }
    }

    public boolean selectTermPresence(String presence) {
        boolean testResult = false;
        Wait.forElementToBeDisplayed(driver, hpoTable);
        for (int i = 0; i < radioButtons.size(); i++) {
            if (radioButtonsTexts.get(i).getText().contains(presence)) {
                Actions.clickElement(driver, radioButtons.get(i));
                testResult = true;
                break;
            }
        }
        return testResult;
    }

    public String selectRandomModifier() {
        Wait.forElementToBeDisplayed(driver, hpoModifiersDropdown);
        Actions.clickElement(driver, hpoModifiersDropdown);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Wait.seconds(1);
        Actions.selectRandomValueFromDropdown(dropdownValues);
        return Actions.getText(hpoModifierValue);
    }

    public String selectRandomPhenotypicSex() {
        Actions.clickElement(driver, phenotypicSexDropdown);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectRandomValueFromDropdown(dropdownValues);
        Wait.seconds(1);
        return Actions.getText(phenotypicSexDropdown.findElement(By.cssSelector(selectSingleValue)));
    }

    public String selectRandomKaryotypicSex() {
        Actions.clickElement(driver, karyotypicSexDropdown);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectRandomValueFromDropdown(dropdownValues);
        Wait.seconds(1);
        return Actions.getText(karyotypicSexDropdown.findElement(By.cssSelector(selectSingleValue)));
    }

    public boolean verifySpecificAgeOnSetYearsValue(String years) {
        try {
            Wait.seconds(3);
            String actual = ageOfOnsetYearsField.getAttribute("value");
            if(!actual.equalsIgnoreCase(years)){
                Debugger.println("Expected AgeOfOnset Year:"+years+", But Actual:"+actual+"\n"+driver.getCurrentUrl());
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from Validating Age Of onset Year:"+exp+"\n"+driver.getCurrentUrl());
            return false;
        }
    }

    public boolean verifySpecificAgeOnSetMonthValue(String month) {
        try {
            String actual = ageOfOnsetMonthsField.getAttribute("value");
            if(!actual.equalsIgnoreCase(month)){
                Debugger.println("Expected AgeOfOnset Month:"+month+", But Actual:"+actual+"\n"+driver.getCurrentUrl());
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from Validating Age Of onset Month:"+exp+"\n"+driver.getCurrentUrl());
            return false;
        }
    }

    public boolean verifySpecificDiseaseStatusValue(String expectedDiseaseStatus) {
        if(!Wait.isElementDisplayed(driver, diseaseStatusDropdown,30)){
            Debugger.println("Expected diseaseStatusDropdown not loaded ");
            return false;
        }
        Debugger.println("Expected diseaseStatus       : " + expectedDiseaseStatus);
        Debugger.println("Actual diseaseStatusDropdown : " + Actions.getText(diseaseStatusDropdown));
        return Actions.getText(diseaseStatusDropdown).equalsIgnoreCase(expectedDiseaseStatus);
    }

    public boolean verifySpecificRareDiseaseValue(String expectedRareDisease) {
        if(!Wait.isElementDisplayed(driver, diagnosisField,30)){
            Debugger.println("Expected diagnosisField not loaded ");
            return false;
        }
        Debugger.println("Rare disease diagnosisField: " + Actions.getText(diagnosisField));
        return Actions.getText(diagnosisField).equalsIgnoreCase(expectedRareDisease);
    }

    public String getPhenotypicSexDropdownValue() {
        if(!Wait.isElementDisplayed(driver, phenotypicSexDropdown,30)){
            Debugger.println("Expected phenotypicSexDropdown not loaded ");
            return "";
        }
        return Actions.getText(phenotypicSexDropdown);
    }

    public String getKaryotypicSexDropdownValue() {
        Wait.forElementToBeDisplayed(driver, karyotypicSexDropdown);
        return Actions.getText(karyotypicSexDropdown);
    }

    public String selectSpecificKaryotypicSexDropdownValue(String value) {
        try {
            if (!Wait.isElementDisplayed(driver, karyotypicSexDropdown, 15)) {
                Actions.scrollToTop(driver);
            }
            Actions.clickElement(driver, karyotypicSexDropdown);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, value);
            return Actions.getText(karyotypicSexDropdown.findElement(By.cssSelector(selectSingleValue)));
        } catch (Exception exp) {
            Debugger.println("Clinical Questions Page : Exception from selecting Karyotypic Sex : " + exp);
            SeleniumLib.takeAScreenShot("KaryotypicSexDropdown.jpg");
            return null;
        }
    }

    public boolean verifySpecificHPOTermDisplayed(String expectedHPOTermToBeDisplayed) {
        try {
            boolean elementFound = false;
            Wait.seconds(2);
            if(!Wait.isElementDisplayed(driver, hpoTable,30)){
                Debugger.println("hpoTable not displayed");
                return false;
            }
            for (WebElement hpoTerm : hpoTermNames) {
                if (hpoTerm.getText().contains(expectedHPOTermToBeDisplayed)) {
                    elementFound = true;
                }
            }
            return elementFound;
        } catch (Exception exp) {
            Debugger.println("Exception from verifySpecificHPOTermDisplayed:" + exp);
            return false;
        }
    }

    public boolean selectRareDiseaseDiagnosisType(String expectedDiseaseDiagnosisType) {
        try {
            String diagnosisType = null;
            Wait.forElementToBeDisplayed(driver, omimRadioButton);
            Wait.forElementToBeDisplayed(driver, orphanetRadioButton);
            Actions.scrollToTop(driver);
            if (expectedDiseaseDiagnosisType.equalsIgnoreCase("Omim")) {
                Actions.clickElement(driver, omimRadioButton);
            } else {
                Actions.clickElement(driver, orphanetRadioButton);
            }
            for (WebElement element : rareDiseaseDiagnosesRadioButtons) {
                if (element.findElement(By.tagName(tagName)).getCssValue("border-color").equals("rgb(74, 139, 202)")) {
                    diagnosisType = Actions.getText(element);
                }
            }
            if(diagnosisType != null && diagnosisType.contains(expectedDiseaseDiagnosisType)){
                return true;
            }
            Debugger.println("Expected RareDiseaseDiagnosisType:"+expectedDiseaseDiagnosisType+", Actual:"+diagnosisType);
            return false;
        }catch(Exception exp){
            Debugger.println("Exception in selectRareDiseaseDiagnosisType:"+exp);
             return false;
        }
    }

    public String selectRareDiseaseDiagnosisType(String expectedDiseaseDiagnosisType, int itemPositionInTable) {
        String diagnosisType = null;
        int index = itemPositionInTable - 1; // second text field the table is denoted by array [1] and third item is denoted by array [2]
        Wait.forElementToBeDisplayed(driver, omimRadioButtons.get(index));
        Wait.forElementToBeDisplayed(driver, orphanetRadioButtons.get(index));
        if (expectedDiseaseDiagnosisType.equalsIgnoreCase("Omim")) {
            Actions.clickElement(driver, omimRadioButtons.get(index));
        } else {
            Actions.clickElement(driver, orphanetRadioButtons.get(index));
        }
        for (WebElement element : rareDiseaseDiagnosesRadioButtons) {
            boolean check1 = element.findElement(By.tagName(tagName)).getCssValue("border-color").equals("rgb(74, 139, 202)");
            boolean check2 = Actions.getText(element).equalsIgnoreCase(expectedDiseaseDiagnosisType);
            if (check1 && check2) {
                diagnosisType = Actions.getText(element);
            }
        }
        return diagnosisType;
    }

    public boolean selectRareDiseaseStatus(String value) {
        try {
            Actions.clickElement(driver, rareDiseaseDiagnosisStatusDropdown);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, value);
            String actual = Actions.getText(rareDiseaseDiagnosisStatusDropdown.findElement(By.cssSelector(selectSingleValue)));
            if(actual.contains(value)){
                return true;
            }
            return false;
        } catch (Exception exp) {
            Debugger.println("Clinical Questions Page : Exception from selecting RareDiseaseStatus value  : " + exp);
            return false;
        }
    }

    public String selectRareDiseaseStatus(String value, int itemPositionInTable) {
        try {
            int index = itemPositionInTable - 1; // second text field the table is denoted by array [1] and third item is denoted by array [2]
            Actions.clickElement(driver, rareDiseaseDiagnosisStatusDropdowns.get(index));
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, value);
            return Actions.getText(rareDiseaseDiagnosisStatusDropdowns.get(index).findElement(By.cssSelector(selectSingleValue)));
        } catch (Exception exp) {
            Debugger.println("Clinical Questions Page : Exception from selecting RareDiseaseStatus value  : " + exp);
            SeleniumLib.takeAScreenShot("RareDiseaseStatusDropdown.jpg");
            return null;
        }
    }

    public boolean verifySpecificTermPresence(String presence) {
        boolean testResult = false;
        Wait.forElementToBeDisplayed(driver, hpoTable);
        for (int i = 0; i < getSelectedRadioButtonsOnClinicalQuestions.size(); i++) {
            //Debugger.println("TermPresence " + i + " " + Actions.getValue(getSelectedRadioButtonsOnClinicalQuestions.get(i)));
            if (Actions.getValue(getSelectedRadioButtonsOnClinicalQuestions.get(i)).contains(presence)) {
                testResult = Actions.isRadioButtonIsSelected(getSelectedRadioButtonsOnClinicalQuestions.get(i));
                break;
            }
        }
        return testResult;
    }

    public boolean verifySpecificDiagnosisType(String diagnosis) {
        boolean testResult = false;
        Wait.forElementToBeDisplayed(driver, rareDiseaseDiagnosisTable);
        for (int i = 0; i < getSelectedRadioButtonsOnClinicalQuestions.size(); i++) {
            //Debugger.println("DiagnosisType " + i + " " + Actions.getValue(getSelectedRadioButtonsOnClinicalQuestions.get(i)));
            if (Actions.getValue(getSelectedRadioButtonsOnClinicalQuestions.get(i)).contains(diagnosis)) {
                testResult = Actions.isRadioButtonIsSelected(getSelectedRadioButtonsOnClinicalQuestions.get(i));
                break;
            }
        }
        return testResult;
    }

    public boolean verifySpecificRareDiseaseDiagnosisStatusValue(String expectedStatus) {
        if (!Wait.isElementDisplayed(driver, rareDiseaseDiagnosisStatusDropdown, 10)) {
            //Debugger.println("RDDiagnosis status dropdown not present.");
            //SeleniumLib.takeAScreenShot("verifySpecificRareDiseaseDiagnosisStatusValue.jpg");
            return false;
        }
        String actText = Actions.getText(rareDiseaseDiagnosisStatusDropdown);
        if (!actText.equalsIgnoreCase(expectedStatus)) {
            //Debugger.println("Expected RDDiagnosis status :" + expectedStatus + ",Actual:" + actText);
            //SeleniumLib.takeAScreenShot("verifySpecificRareDiseaseDiagnosisStatusValue.jpg");
            return false;
        }
        return true;
    }

    public String getErrorMessageFromHPOPhenotypeSection() {
        Wait.forElementToBeDisplayed(driver, hpoErrorNotification);
        return Actions.getText(hpoErrorNotification);
    }

    public List<String> getValuesFromPhenotypicSexDropDown() {
        try {
            Actions.clickElement(driver, phenotypicSexDropdown);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            List<String> values = Actions.getValuesFromDropdown(dropdownValues);
            Wait.seconds(1);
            // this step is necessary to make the dropdown to disappear
            Actions.selectRandomValueFromDropdown(dropdownValues);
            Wait.seconds(1);
            return values;
        }catch(Exception exp){
            Debugger.println("Exception from getValuesFromPhenotypicSexDropDown: "+exp);
            //SeleniumLib.takeAScreenShot("getValuesFromPhenotypicSexDropDown.jpg");
            return null;
        }
    }

    public List<String> getValuesFromKaryotypicSexDropDown() {
        try {
            Actions.clickElement(driver, karyotypicSexDropdown);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            List<String> values = Actions.getValuesFromDropdown(dropdownValues);
            Wait.seconds(1);
            return values;
        }catch(Exception exp){
            Debugger.println("Exception from getValuesFromKaryotypicSexDropDown: "+exp);
            //SeleniumLib.takeAScreenShot("getValuesFromKaryotypicSexDropDown.jpg");
            return null;
        }
    }

    public boolean clickAddAnotherLink() {
        if(!Wait.isElementDisplayed(driver,addAnotherRareDiseaseLink,30)){
            Debugger.println("Add Another link not present in Tumour page."+driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("AddAnotherLink.jpg");
            return false;
        }
        try {
            Actions.clickElement(driver, addAnotherRareDiseaseLink);
            return true;
        }catch (Exception exp){
            try{
                seleniumLib.clickOnWebElement(addAnotherRareDiseaseLink);
                return true;
            }catch(Exception exp1){
                Debugger.println("Exception from clickAddAnotherLink."+exp+"\n"+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("AddAnotherLink.jpg");
                return false;
            }
        }
    }

    public boolean verifyTheFilledDiseaseStatusDetails(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        //DiseaseStatus handling as the first item, otherwise some overlay element visible on top of this and creating issue in clicking on the same.
        String paramValue = paramNameValue.get("DiseaseStatus");
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                if (!Wait.isElementDisplayed(driver, diseaseStatusDropdown, 30)) {
                    return false;
                }
                if (!diseaseStatusDropdown.getText().equalsIgnoreCase(paramValue)) {
                    return false;
                }
            } catch (Exception exp) {
                Debugger.println("Exception from checking disease from the disease dropdown...:" + exp);
                return false;
            }
        }
        //AgeOfOnset
        paramValue = paramNameValue.get("AgeOfOnset");
        if (paramValue != null && !paramValue.isEmpty()) {
            String[] age_of_onsets = paramValue.split(",");
            if (!(age_of_onsets[0]).contains(ageOfOnsetYearsField.getAttribute("value"))) {
                return false;
            }
            if (!(age_of_onsets[1]).contains(ageOfOnsetMonthsField.getAttribute("value"))) {
                return false;
            }
        }
        //HpoPhenoType
        paramValue = paramNameValue.get("HpoPhenoType");
        if (paramValue != null && !paramValue.isEmpty()) {
            if (!isHPOAlreadyConsidered(paramValue)) {
                return false;
            }
        }
        //PhenotypicSex
        paramValue = paramNameValue.get("PhenotypicSex");
        if (paramValue != null && !paramValue.isEmpty()) {
            if (!phenotypicSexDropdown.getText().equalsIgnoreCase(paramValue)) {
                return false;
            }
        }
        //KaryotypicSex
        paramValue = paramNameValue.get("KaryotypicSex");
        if (paramValue != null && !paramValue.isEmpty()) {
            if (!karyotypicSexDropdown.getText().equalsIgnoreCase(paramValue)) {
                return false;
            }
        }
        return true;
    }//method

    public boolean verifyTheExpectedFieldLabelsWithActualFieldLabels(List<Map<String, String>> expectedLabelList) {
        try {
            List actualFieldsLabels = getTheOptionalFieldsLabelsOnCurrentPage();
            for (int i = 0; i < expectedLabelList.size(); i++) { //i starts from 1 because i=0 represents the header;
                if (!actualFieldsLabels.contains(expectedLabelList.get(i).get("labelHeader"))) {
                    Debugger.println("Expected Label " + expectedLabelList.get(i).get("labelHeader") + " Not present in Clinical Page.\n"+driver.getCurrentUrl());
                    SeleniumLib.takeAScreenShot("ClinicalPage.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from getting field labels." + exp);
            SeleniumLib.takeAScreenShot("ClinicalPage.jpg");
            return false;
        }
    }

    public List<String> getTheOptionalFieldsLabelsOnCurrentPage() {
        List<String> actualFieldLabels = new ArrayList<>();
        for (WebElement fieldLabel : genericFieldLabels) {
            if (!fieldLabel.getText().contains("✱")) {
                actualFieldLabels.add(fieldLabel.getText().trim());
            }
        }
        Debugger.println("Actual field labels " + actualFieldLabels);
        return actualFieldLabels;
    }

    public boolean verifyThePresenceOfSpecialCharacters() {
        try {
            String year = ageOfOnsetYearsField.getAttribute("value");
            String month = ageOfOnsetMonthsField.getAttribute("value");
            if (!year.equalsIgnoreCase("")) {
                return false;
            }
            if (!month.equalsIgnoreCase("")) {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Clinical Questions Page, verifyThePresenceOfSpecialCharacters " + exp);
            return false;
        }
    }

    public boolean verifyTheHPOFieldsHintText(String hintText) {
        try {
            Wait.forElementToBeDisplayed(driver, hpoPlaceHolder, 10);
            if (!Wait.isElementDisplayed(driver, hpoPlaceHolder, 10)) {
                Debugger.println("HPO Placeholder could not locate");
                SeleniumLib.takeAScreenShot("HPOSearchFieldPlaceholder.jpg");
                return false;
            }
            if (!hpoPlaceHolder.getText().contains(hintText)) {
                Debugger.println("Placeholder text for HPO Phenotype Actual:" + hpoPlaceHolder.getText() + ",Expected:" + hintText);
                SeleniumLib.takeAScreenShot("HPOSearchFieldPlaceholder.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from the hpo hint text validation " + exp);
            SeleniumLib.takeAScreenShot("HPOSearchFieldPlaceholder.jpg");
            return false;
        }
    }

    public boolean verifyTheDropDownFieldsHintText(String hintText, String dropDownFieldLabel) {
        try {
            String dropDownHint = dropDownPlaceHolderText.replaceAll("dummyValue", dropDownFieldLabel);
            WebElement dropdownPlaceHolder = driver.findElement(By.xpath(dropDownHint));
            Wait.forElementToBeDisplayed(driver, dropdownPlaceHolder, 10);
            if (!dropdownPlaceHolder.getText().contains(hintText)) {
                Debugger.println("Filed: " + dropDownFieldLabel + "Hint Actual: " + dropdownPlaceHolder.getText() + ",Excepted:" + hintText);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from the dropdown hint text " + exp);
            return false;
        }
    }

    public boolean verifyFieldHeaders(String expectedHeader) {
        try {
            for (int i = 0; i < fieldHeaders.size(); i++) {
                if (fieldHeaders.get(i).getText().equalsIgnoreCase(expectedHeader)) {
                    return true;
                }
            }
            Debugger.println("Excepted header " + expectedHeader + " not present in the page.");
            SeleniumLib.takeAScreenShot("ExceptedHeader.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Clinical Question Page: Header Titles " + exp);
            SeleniumLib.takeAScreenShot("HeaderTitlesOnClinicalQuestionsPage.jpg");
            return false;
        }
    }

    public boolean verifyTheFieldsLeftBlankInClinicalQuestionsPage() {
        try {
            Wait.forElementToBeDisplayed(driver, diseaseStatusDropdown);
            if (!diseaseStatusDropdown.getText().equalsIgnoreCase("Select...")) {
                Debugger.println("Disease Status not blank as expected: " + diseaseStatusDropdown.getText());
                SeleniumLib.takeAScreenShot("ClinicalQuestionsPage.jpg");
                return false;
            }

            if (!ageOfOnsetYearsField.getText().isEmpty()) {
                Debugger.println("AgeOfOnsetYearsField not blank as expected: " + ageOfOnsetYearsField.getText());
                SeleniumLib.takeAScreenShot("ClinicalQuestionsPage.jpg");
                return false;
            }

            if (!ageOfOnsetMonthsField.getText().isEmpty()) {
                Debugger.println("AgeOfOnsetMonthsField not blank as expected: " + ageOfOnsetMonthsField.getText());
                SeleniumLib.takeAScreenShot("ClinicalQuestionsPage.jpg");
                return false;
            }

            if (!diagnosisField.getText().isEmpty()) {
                Debugger.println("DiagnosisField not blank as expected: " + diagnosisField.getText());
                SeleniumLib.takeAScreenShot("ClinicalQuestionsPage.jpg");
                return false;
            }

            if (!phenotypicSexDropdown.getText().equalsIgnoreCase("Select...")) {
                Debugger.println("AgeOfOnsetMonthsField not blank as expected: " + phenotypicSexDropdown.getText());
                SeleniumLib.takeAScreenShot("ClinicalQuestionsPage.jpg");
                return false;
            }

            if (!karyotypicSexDropdown.getText().equalsIgnoreCase("Select...")) {
                Debugger.println("KaryotypicSexDropdown not blank as expected: " + karyotypicSexDropdown.getText());
                SeleniumLib.takeAScreenShot("ClinicalQuestionsPage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyTheFieldsLeftBlankInClinicalQuestionsPage" + exp);
            SeleniumLib.takeAScreenShot("ClinicalQuestionsPage.jpg");
            return false;
        }
    }

        public boolean selectSpecificPhenotypicSexDropdownValue() {
        try {
            if(genderValue==null||genderValue.isEmpty()){
                Debugger.println("No Gender value to select phenotypic sex drop down");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, phenotypicSexDropdown, 15)) {
                Actions.scrollToTop(driver);
            }
            Actions.clickElement(driver, phenotypicSexDropdown);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Wait.seconds(5);//Explicitly waiting here as below element is dynamically created
            Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + genderValue + "']")));
            String selectedValue=Actions.getText(phenotypicSexDropdown.findElement(By.xpath("//span[text()='" + genderValue + "']")));
            if (selectedValue != null && selectedValue.equalsIgnoreCase(genderValue)) {
                return true;//Already Selected the Specified Value
            }
            if(!genderValue.equalsIgnoreCase(selectedValue)){
                Debugger.println("Expected value is "+genderValue+" selectedValue is "+selectedValue);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Clinical Questions Page : Exception from selecting phenotypic Sex : " + exp);
            return false;
        }
    }

    //Method added by @Stag for filling the ClinicalQuestionsPage after reading data from JSON
    public boolean fillDiseaseStatusAgeOfOnsetAndHPOTermFromJson(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParamsByDelimiter(searchParams, ";");
        //DiseaseStatus
        String paramValue = paramNameValue.get("DiseaseStatus");
        if (paramValue != null && !paramValue.isEmpty()) {
            if (selectDiseaseStatus(paramNameValue.get("DiseaseStatus")) == null) {
                return false;
            }
        }
        //AgeOfOnset
        paramValue = paramNameValue.get("AgeOfOnset");
        if (paramValue != null && !paramValue.isEmpty()) {
            String[] age_of_onsets = paramValue.split(",");
            try {
                ageOfOnsetYearsField.sendKeys(age_of_onsets[0]);
                ageOfOnsetMonthsField.sendKeys(age_of_onsets[1]);
            } catch (Exception exp) {
                seleniumLib.sendValue(ageOfOnsetYearsField, age_of_onsets[0]);
                seleniumLib.sendValue(ageOfOnsetMonthsField, age_of_onsets[1]);
            }
        }
        //PhenoType
        boolean isFilled = false;
        boolean isPhenotypePresent = false;
        paramValue = paramNameValue.get("HpoPhenoType");
        if (paramValue != null && !paramValue.isEmpty()) {
            isPhenotypePresent = true;
//            Debugger.println("The params are-" + paramValue);
            if (paramValue.contains(",")) {
                paramValue = paramValue.replace("[", "");
                paramValue = paramValue.replace("]", "");
                String[] hpoArr = paramValue.split(",");
                for (String hpoStr : hpoArr) {
                    if (hpoStr != null && !hpoStr.isEmpty())
                        Debugger.println("The HPO Data--" + hpoStr);
                    String[] hpoData = hpoStr.split("-");
                    String hpoTerm=hpoData[0];
                    String hpoPresence=hpoData[1];
//                    Debugger.println("The hpo--"+hpoTerm+";"+hpoPresence);
                    hpoSearchField.get(0).clear();
                    if (!(searchAndSelectRandomHPOPhenotype(hpoTerm) > 0)) {
                        isFilled = isHPOAlreadyConsidered(hpoTerm);
                    } else {
                        if(hpoPresence.equalsIgnoreCase("yes")){
                            selectTermPresence("Present");
                        }else if(hpoPresence.equalsIgnoreCase("no")){
                            selectTermPresence("Absent");
                        }else if(hpoPresence.equalsIgnoreCase("unknown")){
                            selectTermPresence("Unknown");
                        }
                        isFilled = true;
                    }
                }
            }
            else {
                Debugger.println("The params are-" + paramValue);
                if (!(searchAndSelectRandomHPOPhenotype(paramValue) > 0)) {
                    isFilled = isHPOAlreadyConsidered(paramValue);
                } else {
                    isFilled = true;
                }
            }
        }
        //PhenotypicSex
        paramValue = paramNameValue.get("PhenotypicSex");
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                Click.element(driver, phenotypicSexDropdown);
                Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + paramValue + "']")));
            } catch (Exception exp) {
                try {
                    seleniumLib.clickOnWebElement(phenotypicSexDropdown);
                    Wait.seconds(2);
                    seleniumLib.clickOnWebElement(dropdownValue.findElement(By.xpath("//span[text()='" + paramValue + "']")));
                } catch (Exception exp1) {
                    Debugger.println("Exception from selecting phenotypicSexDropdown...:" + exp1);
                    return false;
                }
            }
        }
        //PhenotypicSex
        paramValue = paramNameValue.get("KaryotypicSex");
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                Click.element(driver, karyotypicSexDropdown);
                Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + paramValue + "']")));
            } catch (Exception exp) {
                try {
                    seleniumLib.clickOnWebElement(karyotypicSexDropdown);
                    Wait.seconds(2);
                    seleniumLib.clickOnWebElement(dropdownValue.findElement(By.xpath("//span[text()='" + paramValue + "']")));
                } catch (Exception exp1) {
                    Debugger.println("Exception from selecting karyotypicSexDropdown...:" + exp1);
                    return false;
                }
            }
        }
        if (isPhenotypePresent) {
            return isFilled;
        } else {
            return true;
        }
    }//method

}//End
