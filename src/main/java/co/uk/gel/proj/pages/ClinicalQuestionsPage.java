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

    @FindBy(xpath = "//div[contains(text(),'Select...')]")
    public WebElement diseaseStatusPlaceholderText;

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
    public WebElement hpoSearchField;

    @FindBy(css = "*[class*='hpo-term__name']")
    public List<WebElement> hpoTermNames;

    @FindBy(css = "table[class*='table--hpo']")
    public WebElement hpoTable;

    @FindBy(css = "[class*='hpo-term__name']")
    public List<WebElement> hpoTerms;

    @FindBy(xpath = "//label[contains(@class,'radio')]")
    public List<WebElement> radioButtons;

    @FindBy(xpath = "//input[contains(@class,'radio')]")
    public List<WebElement> selectedRadioButtons;

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

    public List<WebElement> rareDiseaseDiagnosisTexts;

    @FindBy(xpath = "//span[contains(text(),'Orphanet')]")
    public WebElement orphanetRadioButton;

    @FindBy(xpath = "//span[contains(text(),'Orphanet')]")
    public List<WebElement> orphanetRadioButtons;

    @FindBy(xpath = "//label[contains(@class,'switchable-enum__radio')]")
    public List<WebElement> rareDiseaseDiagnosesRadioButtons;

    @FindBy(xpath = "//label[contains(@class,'switchable-enum__radio')]//input")
    public List<WebElement> selectedRareDiseaseDiagnosesRadioButtons;

    @FindBy(xpath = "//*[contains(@id,'question-id-q114')]")
    public WebElement rareDiseaseDiagnosisStatusDropdown;

    @FindBy(xpath = "//div[contains(@id,'question-id-q114')]")
    public List<WebElement> rareDiseaseDiagnosisStatusDropdowns;

    @FindBy(css = "*[data-testid*='notification-error']")
    public WebElement hpoErrorNotification;

    @FindBy(xpath = "//button[contains(text(), '+ Add another')]")
    public WebElement addAnotherRareDiseaseLink;

    String hpoSectionMarkedAsMandatoryToDO = "HPO phenotype or code ✱";
    By hpoRows = By.xpath("//table[contains(@class,'--hpo')]/tbody/tr");

    String selectSingleValue = "div[class*='singleValue']";
    String tagName = "span";

    public boolean verifyTheCountOfHPOTerms(int minimumNumberOfHPOTerms) {
        Wait.forElementToBeDisplayed(driver, hpoTable);
        int actualNumberOfHPOTerms = hpoTerms.size();
        System.out.println("Number of Default HPO terms : " + actualNumberOfHPOTerms);
        return actualNumberOfHPOTerms >= minimumNumberOfHPOTerms;
    }

    public int searchAndSelectRandomHPOPhenotype(String hpoTerm) {
        Wait.seconds(5);
        try {
            if(!Wait.isElementDisplayed(driver,hpoSearchField,10)) {
                Debugger.println("HPO Phenotype search field is not visible.");
                SeleniumLib.takeAScreenShot("HPOPhenoTypeSearch.jpg");
                //Scroll to the element and try
                SeleniumLib.scrollToElement(hpoSearchField);
                if(!Wait.isElementDisplayed(driver,hpoSearchField,10)){
                    Debugger.println("Scrolled to HPO Phenotype search field, still not displayed.");
                    SeleniumLib.takeAScreenShot("HPOPhenoTypeSearch1.jpg");
                    return 0;
                }
            }
            hpoSearchField.sendKeys(hpoTerm);
            if(!Wait.isElementDisplayed(driver,dropdownValue,10)){
                Debugger.println("HPO Phenotype options are not loaded for search term:"+hpoTerm);
                SeleniumLib.takeAScreenShot("HPOPhenoTypeDDValues.jpg");
                return 0;
            }
            if(dropdownValues.size() < 1){
                Debugger.println("No suggested list for HPO Phenotype is loaded...");
                SeleniumLib.takeAScreenShot("HPOPhenoTypeDDValues.jpg");
                return 0;
            }
            Actions.selectByIndexFromDropDown(dropdownValues, 0);
            // determine the total number of HPO terms Loaded - If selected, it would be minimum one
            Wait.seconds(2);
            int numberOfHPO = hpoTerms.size();
            if(numberOfHPO < 1){
                Debugger.println("No HPO Phenotype has got selected..");
                SeleniumLib.takeAScreenShot("HPOTerms.jpg");
                return 0;
            }
            return numberOfHPO;
        } catch (TimeoutException exp) {
            //One reason observed is the overlay of global patient card on phenotype element...so trying to scroll down and select
            //Scroll to Top also may cause the same issue, so scrolling to previous element and trying
            SeleniumLib.scrollToElement(ageOfOnsetYearsField);
            SeleniumLib.takeAScreenShot("PhenoTypeTimeOut.jpg");
            Actions.selectByIndexFromDropDown(dropdownValues, 0);
            return 0;
        }catch (Exception exp) {
            Debugger.println("ClinicalQuestionsPage: searchAndSelectRandomHPOPhenotype: Exception " + exp);
            SeleniumLib.takeAScreenShot("HPOPhenotypeException.jpg");
            return 0;
        }
    }

    public boolean verifySpecificHPOTermDisplayedInTheFirstRow(String expectedHPOTermToBeDisplayedInTheFirstRow) {
        Wait.seconds(2);
        Wait.forElementToBeDisplayed(driver, hpoTable);
        Wait.forElementToBeDisplayed(driver, hpoTermNames.get(0));
        String actualHPOTermDisplayedInTheFirstRow = hpoTermNames.get(0).getText();
        return actualHPOTermDisplayedInTheFirstRow.contains(expectedHPOTermToBeDisplayedInTheFirstRow);
    }

    public String searchAndSelectSpecificDiagnosis(String diagnosis) {
        try {
            Wait.forElementToBeDisplayed(driver, diagnosisValue);
            Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(diagnosisValue, diagnosis);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            if (!Wait.isElementDisplayed(driver, dropdownValue, 10)) {
                Debugger.println("Diagnosis term " + diagnosis + " not present in the dropdown.");
                return null;
            }
            Actions.selectByIndexFromDropDown(dropdownValues, 0);
            return Actions.getText(diagnosisField);
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("RareDiseaseDiagnosis.jpg");
            return null;
        }
    }

    public void searchAndSelectSpecificDiagnosisSecondField(String diagnosis) {
        try {
            Wait.forElementToBeDisplayed(driver, diagnosisValueSecondField);
            Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(diagnosisValueSecondField, diagnosis);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
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
            Debugger.println(" DIAGNOSIS FIELD 1: " + diagnosisField.getText());
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
            if(!Wait.isElementDisplayed(driver, diseaseStatusDropdown,15)){
                Actions.scrollToTop(driver);
            }
            if(!Actions.getText(diseaseStatusDropdown).equalsIgnoreCase(diseaseStatusValue)) {
                Actions.clickElement(driver, diseaseStatusDropdown);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectValueFromDropdown(dropdownValue, diseaseStatusValue);
            }
            return Actions.getText(diseaseStatusDropdown);
        }catch(Exception exp){
            Debugger.println("Exception from selecting Disease Status in Clinical Page: "+exp);
            return null;
        }
    }

    public boolean confirmHPOPhenotypeSectionIsMarkedAsMandatory() {
        Wait.forElementToBeDisplayed(driver, hpoSectionLabel);
        Debugger.println(" HPO section Label :  " + hpoSectionLabel.getText());
        return hpoSectionLabel.getText().contains(hpoSectionMarkedAsMandatoryToDO);

    }

    public void fillInYearsOfOnset(String years) {
        Wait.forElementToBeDisplayed(driver, ageOfOnsetYearsField);
        Actions.fillInValue(ageOfOnsetYearsField, years);
    }
    public void clearValueFromYearsOfOnset() {
        Wait.forElementToBeDisplayed(driver, ageOfOnsetYearsField);
        Actions.clearTextField(ageOfOnsetYearsField);
    }
    public void clearValueFromMonthsOfOnset() {
        Wait.forElementToBeDisplayed(driver, ageOfOnsetMonthsField);
        Actions.clearTextField(ageOfOnsetMonthsField);
    }

    public void fillInMonthsOfOnset(String months) {
        Wait.forElementToBeDisplayed(driver, ageOfOnsetMonthsField);
        Actions.fillInValue(ageOfOnsetMonthsField, months);
    }

    public String getErrorMessageText() {
        Wait.forElementToBeDisplayed(driver, nonNullableFieldErrorMessage);
        String actualErrorMessage = nonNullableFieldErrorMessage.getText();
        return actualErrorMessage;
    }

    public boolean checkNoErrorMessageIsDisplayed() {
        try {
            return nonNullableFieldErrorMessage.isDisplayed();
        } catch (NoSuchElementException nseException) {
            Debugger.println("Web element locator for error message is not visible , hence Error message is not shown on the page");
            return false;
        }
    }

    //Method added by @Stag for filling the ClinicalQuestionsPage
    public boolean fillDiseaseStatusAgeOfOnsetAndHPOTerm(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        //DiseaseStatus handling as the first item, otherwise some overlay element visible on top of this and creating issue in clicking on the same.
        if (paramNameValue.get("DiseaseStatus") != null && !paramNameValue.get("DiseaseStatus").isEmpty()) {
            selectDiseaseStatus(paramNameValue.get("DiseaseStatus"));
        }
        boolean isFilled = false;
        boolean isPhenotypePresent = false;
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
                        isPhenotypePresent = true;
                        if(!(searchAndSelectRandomHPOPhenotype(paramNameValue.get(key))>0)){
                            isFilled = isHPOAlreadyConsidered(paramNameValue.get(key));
                        }else{
                            isFilled = true;
                        }

                    }
                    break;
                }
                case "PhenotypicSex": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        try {
                            Actions.retryClickAndIgnoreElementInterception(driver,phenotypicSexDropdown);
                            Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                            Click.element(driver, dropdownValue.findElement(By.xpath("//div[contains(@id,'answers.question-id-q90')]//span[text()='" + paramNameValue.get(key) + "']")));
                            break;
                        } catch (Exception exp) {
                            Debugger.println("Exception from selecting phenotypic sex dropdown...:" + exp);
                            SeleniumLib.takeAScreenShot("PhenotypicSexDropdown.jpg");
                        }
                    }
                }
                case "KaryotypicSex": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        try {
                            Click.element(driver, karyotypicSexDropdown);
                            Wait.seconds(3);//Explicitly waiting here as below element is dynamically created
                            Click.element(driver, dropdownValue.findElement(By.xpath("//div[contains(@id,'answers.question-id-q91')]//span[text()='" + paramNameValue.get(key) + "']")));
                            break;
                        } catch (Exception exp) {
                            Debugger.println("Exception from selecting karyotypic sex dropdown...:" + exp);
                            SeleniumLib.takeAScreenShot("KaryotypicSexDropdown.jpg");
                        }
                    }
                }
            }//switch
        }//for
        if(isPhenotypePresent) {
            return isFilled;
        }else{
            return true;
        }
    }//method
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
        }catch(Exception exp){
            Debugger.println("Exception from checking whether the HPO is already considered or not: "+exp);
            return false;
        }
    }

    public boolean verifyMaxAllowedValuesHPOField(int maxAllowedValues) {
        try {
            Wait.seconds(5);
            Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(hpoSearchField, "Nephritis");
            Wait.forElementToBeDisplayed(driver, dropdownValues.get(0));
            Wait.seconds(2);
            int i = 0;
            int numberOfElements = dropdownValues.size();
            for (WebElement element : dropdownValues) {
                Debugger.println(" HPO Phenotype value" + ++i + " : " + element.getText());
            }
            Debugger.println(" Number of items displayed in HPO Phenotype Field  : " + numberOfElements);
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
            int i = 0;
            int numberOfElements = dropdownValues.size();
            for (WebElement element : dropdownValues) {
                Debugger.println(" OMIM and Orphanet value" + ++i + " : " + element.getText());
            }
            Debugger.println(" Number of items displayed in OMIM and Orphanet Field  : " + numberOfElements);
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
        Wait.forElementToBeDisplayed(driver, diseaseStatusPlaceholderText);
        return Actions.getText(diseaseStatusPlaceholderText);
    }

    public boolean deleteHPOTerm(String hpoTerm) {
        Wait.seconds(2);
        Wait.forElementToBeDisplayed(driver, hpoTable);
        Wait.forElementToBeDisplayed(driver, hpoTermNames.get(0));
        String actualHPOTermDisplayedInTheFirstRow = hpoTermNames.get(0).getText();
        if(actualHPOTermDisplayedInTheFirstRow.contains(hpoTerm)){
            Wait.forElementToBeDisplayed(driver, hpoTermsDeleteIcons.get(0));
            Actions.clickElement(driver, hpoTermsDeleteIcons.get(0));
            return true;
        }
        else {
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

    public boolean verifySpecificAgeOnSetYearsValue(String years){
        Wait.forElementToBeDisplayed(driver, ageOfOnsetYearsField);
        Debugger.println("ageOfOnsetYearsField : " + Actions.getValue(ageOfOnsetYearsField));
        return Actions.getValue(ageOfOnsetYearsField).equalsIgnoreCase(years);
    }
    public boolean verifySpecificAgeOnSetMonthValue(String month){
        Wait.forElementToBeDisplayed(driver, ageOfOnsetMonthsField);
        Debugger.println("ageOfOnsetMonthsField : " + Actions.getValue(ageOfOnsetMonthsField));
        return Actions.getValue(ageOfOnsetMonthsField).equalsIgnoreCase(month);
    }

    public boolean verifySpecificDiseaseStatusValue(String expectedDiseaseStatus){
        Wait.forElementToBeDisplayed(driver, diseaseStatusDropdown);
        Debugger.println("Expected diseaseStatus       : " + expectedDiseaseStatus);
        Debugger.println("Actual diseaseStatusDropdown : " + Actions.getText(diseaseStatusDropdown));
        return Actions.getText(diseaseStatusDropdown).equalsIgnoreCase(expectedDiseaseStatus);
    }

    public boolean verifySpecificRareDiseaseValue(String expectedRareDisease){
        Wait.forElementToBeDisplayed(driver, diagnosisField);
        Debugger.println("Rare disease diagnosisField: " + Actions.getText(diagnosisField));
        return Actions.getText(diagnosisField).equalsIgnoreCase(expectedRareDisease);
    }

    public String getPhenotypicSexDropdownValue(){
        Wait.forElementToBeDisplayed(driver, phenotypicSexDropdown);
        return Actions.getText(phenotypicSexDropdown);
    }

    public String getKaryotypicSexDropdownValue(){
        Wait.forElementToBeDisplayed(driver, karyotypicSexDropdown);
        return Actions.getText(karyotypicSexDropdown);
    }

    public String selectSpecificKaryotypicSexDropdownValue(String value){
     try {
            if(!Wait.isElementDisplayed(driver, karyotypicSexDropdown,15)){
                Actions.scrollToTop(driver);
            }
            Actions.clickElement(driver, karyotypicSexDropdown);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, value);
            return Actions.getText(karyotypicSexDropdown.findElement(By.cssSelector(selectSingleValue)));
        }catch(Exception exp){
            Debugger.println("Clinical Questions Page : Exception from selecting Karyotypic Sex : "+exp);
            SeleniumLib.takeAScreenShot("KaryotypicSexDropdown.jpg");
            return null;
        }
    }

    public boolean verifySpecificHPOTermDisplayed(String expectedHPOTermToBeDisplayed) {
        boolean elementFound = false;
        Wait.seconds(2);
        Wait.forElementToBeDisplayed(driver, hpoTable);
        Wait.forElementToBeDisplayed(driver, hpoTermNames.get(0));
        for(WebElement hpoTerm : hpoTermNames){
            if(hpoTerm.getText().contains(expectedHPOTermToBeDisplayed)){
                elementFound = true;
            }
        }
        return elementFound;
    }

    public String selectRareDiseaseDiagnosisType(String expectedDiseaseDiagnosisType) {
        String diagnosisType = null;
        Wait.forElementToBeDisplayed(driver, omimRadioButton);
        Wait.forElementToBeDisplayed(driver, orphanetRadioButton);
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
        return diagnosisType;
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

    public String selectRareDiseaseStatus(String value) {
        try {
            Actions.clickElement(driver, rareDiseaseDiagnosisStatusDropdown);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, value);
            return Actions.getText(rareDiseaseDiagnosisStatusDropdown.findElement(By.cssSelector(selectSingleValue)));
        } catch (Exception exp) {
        Debugger.println("Clinical Questions Page : Exception from selecting RareDiseaseStatus value  : " + exp);
        SeleniumLib.takeAScreenShot("RareDiseaseStatusDropdown.jpg");
        return null;
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
            Debugger.println("TermPresence " + i+ " " + Actions.getValue(getSelectedRadioButtonsOnClinicalQuestions.get(i)));
            if (Actions.getValue(getSelectedRadioButtonsOnClinicalQuestions.get(i)).contains(presence)) {
                testResult =  Actions.isRadioButtonIsSelected(getSelectedRadioButtonsOnClinicalQuestions.get(i));
                break;
            }
        }
        return testResult;
    }
    public boolean verifySpecificDiagnosisType(String diagnosis) {
        boolean testResult = false;
        Wait.forElementToBeDisplayed(driver, rareDiseaseDiagnosisTable);
        for (int i = 0; i < getSelectedRadioButtonsOnClinicalQuestions.size(); i++) {
            Debugger.println("DiagnosisType " + i+ " " + Actions.getValue(getSelectedRadioButtonsOnClinicalQuestions.get(i)));
            if (Actions.getValue(getSelectedRadioButtonsOnClinicalQuestions.get(i)).contains(diagnosis)) {
                testResult =  Actions.isRadioButtonIsSelected(getSelectedRadioButtonsOnClinicalQuestions.get(i));
                break;
            }
        }
        return testResult;
    }

    public boolean verifySpecificRareDiseaseDiagnosisStatusValue(String expectedStatus) {
        Wait.forElementToBeDisplayed(driver, rareDiseaseDiagnosisStatusDropdown);
        Debugger.println("Expected diseaseStatus       : " + expectedStatus);
        Debugger.println("Actual diseaseStatusDropdown : " + Actions.getText(rareDiseaseDiagnosisStatusDropdown));
        return Actions.getText(rareDiseaseDiagnosisStatusDropdown).equalsIgnoreCase(expectedStatus);
    }

    public String getErrorMessageFromHPOPhenotypeSection(){
            Wait.forElementToBeDisplayed(driver, hpoErrorNotification);
            return Actions.getText(hpoErrorNotification);
        }

    public List<String> getValuesFromPhenotypicSexDropDown() {
        Actions.clickElement(driver, phenotypicSexDropdown);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        List<String> values = Actions.getValuesFromDropdown(dropdownValues);
        Wait.seconds(1);
        // this step is necessary to make the dropdown to disappear
        Actions.selectRandomValueFromDropdown(dropdownValues);
        Wait.seconds(1);
        return values;
    }

    public List<String> getValuesFromKaryotypicSexDropDown() {
        Actions.clickElement(driver, karyotypicSexDropdown);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        List<String> values = Actions.getValuesFromDropdown(dropdownValues);
        Wait.seconds(1);
        return values;
    }

    public void clickAddAnotherLink(){
        Wait.forElementToBeDisplayed(driver, addAnotherRareDiseaseLink);
        Actions.clickElement(driver, addAnotherRareDiseaseLink);
    }
}
