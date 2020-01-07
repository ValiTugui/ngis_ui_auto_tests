package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

    @FindBy(xpath = "//label[contains(@for,'hpo-search')]//child::span")
    public List<WebElement> hpoPhenotypeRedAsterix;

    @FindBy(css = "#react-select-3-input")
    public WebElement hpoSearchField;

    @FindBy(css = "*[class*='hpo-term__name']")
    public List<WebElement> hpoTermNames;

    @FindBy(css = "table[class*='table--hpo']")
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

    @FindBy(xpath = "//*[contains(@id,'question-id-q90')]")
    public WebElement phenotypicSexDropdown;

    @FindBy(xpath = "//*[contains(@id,'question-id-q91')]")
    public WebElement karyotypicSexDropdown;

    @FindBy(css = "[class*='hpo-term__delete']")
    public List<WebElement> hpoTermsDeleteIcons;

    @FindBy(xpath = "//input[@id='unit-id-clinical_questions-QR06-13.answers[0].question-id-q111']")
    public WebElement diagnosisValue;

    @FindBy(xpath = "//*[contains(@id,'question-id-q111')]//child::div")
    public WebElement diagnosisField;

    @FindBy(xpath = "//div[@id='unit-id-clinical_questions-QR06-13.answers[0].question-id-q111']//*[@class='css-19bqh2r']")
    public WebElement cancelDiagnosisValue;

    @FindBy(css = "div[class*='error-message__text']")
    public WebElement nonNullableFieldErrorMessage;

    @FindBy(css = "div[class*='hpo__search']")
    public WebElement hpoSectionLabel;

    String hpoSectionMarkedAsMandatoryToDO = "HPO phenotype or code ✱";
    By hpoRows = By.xpath("//table[contains(@class,'--hpo')]/tbody/tr");
    String selectSingleValue = "div[class*='singleValue']";

    public boolean verifyTheCountOfHPOTerms(int minimumNumberOfHPOTerms) {
        Wait.forElementToBeDisplayed(driver, hpoTable);
        int actualNumberOfHPOTerms = hpoTerms.size();
        System.out.println("Number of Default HPO terms : " + actualNumberOfHPOTerms);
        return actualNumberOfHPOTerms >= minimumNumberOfHPOTerms;
    }

    public int searchAndSelectRandomHPOPhenotype(String hpoTerm) {
        Wait.seconds(5);
        try {
            seleniumLib.sendValue(hpoSearchField, hpoTerm);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            if (!Wait.isElementDisplayed(driver, dropdownValue, 10)) {
                Debugger.println("HPO term " + hpoTerm + " present in the dropdown.");
                return -1;
            }
            Actions.selectByIndexFromDropDown(dropdownValues, 0);
            Debugger.println("Selected ....dropdownValues");
            // determine the total number of HPO terms
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, hpoTable);
            int numberOfHPO = hpoTerms.size();
            Debugger.println("SizeOfHPOTerms: " + numberOfHPO);
            return numberOfHPO;
        } catch (Exception exp) {
            Debugger.println("ClinicalQuestionsPage: searchAndSelectRandomHPOPhenotype: Exception " + exp);
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

    public String searchAndSelectARandomDiagnosis(String diagnosis) {
        if (Actions.getText(diagnosisField).isEmpty()) {
            Actions.fillInValue(driver, diagnosisValue, diagnosis);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectByIndexFromDropDown(dropdownValues, 0);
            Wait.seconds(10);
        }
        return Actions.getText(diagnosisField);
    }

    public void clearRareDiseaseDiagnosisFieldByPressingBackspaceKey() throws AWTException {
        if (!Actions.getText(diagnosisField).isEmpty()) {
            Debugger.println(" DIAGNOSIS FIELD 1: " + diagnosisField.getText());
            diagnosisValue.click();
            Actions.clearField(driver, diagnosisValue);
            Wait.forElementToBeDisplayed(driver, cancelDiagnosisValue);
            Wait.seconds(10);
        }
    }

    public boolean confirmRareDiseaseDiagnosisFieldIsEmpty(String diagnosisValue) {
        Wait.forElementToBeDisplayed(driver, diagnosisField);
        return (!diagnosisField.getText().equalsIgnoreCase(diagnosisValue));

    }

    public boolean selectDiseaseStatus(String diseaseStatusValue) {
        Wait.forElementToBeDisplayed(driver, diseaseStatusDropdown);
        Actions.clickElement(driver, diseaseStatusDropdown);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectValueFromDropdown(dropdownValue, diseaseStatusValue);
        return true;
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
    public void fillClinicalQuestionPageWithGivenParams(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "DiseaseStatus": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        Wait.forElementToBeClickable(driver, diseaseStatusDropdown);
                        Click.element(driver, diseaseStatusDropdown);
                        Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='" + paramNameValue.get(key) + "']")));
                    }
                    break;
                }
                case "AgeOfOnset": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        String[] age_of_onsets = paramNameValue.get(key).split(",");
                        Wait.forElementToBeDisplayed(driver, ageOfOnsetYearsField);
                        ageOfOnsetYearsField.sendKeys(age_of_onsets[0]);
                        ageOfOnsetMonthsField.sendKeys(age_of_onsets[1]);
                    }
                    break;
                }
                case "HpoPhenoType": {
                    if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        //Check whether the given Phenotype already added to the patient, if yes no need to enter again.
                        String hpoValue = "";
                        boolean isExists = false;
                        List<WebElement> rows = seleniumLib.getElements(hpoRows);
                        if (rows != null && rows.size() > 0) {
                            for (WebElement row : rows) {
                                hpoValue = row.findElement(By.xpath("./td[1]")).getText();
                                if (hpoValue.equalsIgnoreCase(paramNameValue.get(key))) {
                                    isExists = true;
                                    break;//for loop
                                }
                            }//for
                        }
                        if (!isExists) {
                            searchAndSelectRandomHPOPhenotype(paramNameValue.get(key));//Re-using existing method
                        }
                    }
                    break;
                }

            }//switch
        }//for
    }//method

    public boolean verifyMaxAllowedValuesHPOField(int maxAllowedValues) {
        try {
            Wait.seconds(5);
            seleniumLib.sendValue(hpoSearchField, "Nephritis");
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

}
