package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.By;
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

    @FindBy(css = "div[class*='hpo-select']")
    public WebElement hpoTermSection;

    @FindBy(css = "*[class*='hpo-term__name']")
    public List<WebElement> hpoTermNames;

    @FindBy(css = "table[class*='table--hpo']")
    public WebElement hpoTable;

    @FindBy(css = "[class*='hpo-term__name']")
    public List<WebElement> hpoTerms;

    @FindBy(xpath = "//td[contains(@class,'hpo-term__modifiers')]//child::div")
    public WebElement hpoModifiersDropdown;

    @FindBy(xpath = "//td[contains(@class,'hpo-term__modifiers')]")
    public WebElement hpoModifiersRemoval;

    @FindBy(css = "div[class*='hpo__notification-error']")
    public WebElement hpoError;

    @FindBy(xpath = "//label[contains(@class,'radio')]")
    public List<WebElement> radioButtons;

    @FindBy(xpath = "//label[contains(@class,'switchable-enum__radio')]")
    public List<WebElement> rareDiseaseDiagnosesRadioButtons;

    @FindBy(xpath = "//span[contains(@class,'radio__text')]")
    public List<WebElement> radioButtonsTexts;

    @FindBy(xpath = "//input[@id='unit-id-clinical_questions-QR06-13.answers[0].question-id-q111']")
    public WebElement diagnosisValue;

    @FindBy(xpath = "//*[contains(@id,'question-id-q111')]//child::div")
    public WebElement diagnosisField;

    @FindBy(xpath = "//div[@id='unit-id-clinical_questions-QR06-13.answers[0].question-id-q111']//*[@class='css-19bqh2r']")
    public WebElement cancelDiagnosisValue;

    @FindBy(xpath = "//*[contains(@id,'question-id-q114')]")
    public WebElement rareDiseaseStatusDropdown;

    @FindBy(xpath = "//*[contains(@id,'question-id-q90')]")
    public WebElement phenotypicSexDropdown;

    @FindBy(xpath = "//*[contains(@id,'question-id-q91')]")
    public WebElement karyotypicSexDropdown;

    @FindBy(css = "div[class*='error-message__text']")
    public WebElement nonNullableFieldErrorMessage;

    @FindBy(xpath = "//button[text()='+ Add another']")
    public WebElement addAnotherLink;

    @FindBy(xpath = "//button[text()='Delete']")
    public WebElement deleteLink;

    public boolean verifyTheCountOfHPOTerms(int minimumNumberOfHPOTerms) {
        Wait.forElementToBeDisplayed(driver, hpoTable);
        int actualNumberOfHPOTerms = hpoTerms.size();
        System.out.println("Number of Default HPO terms : " + actualNumberOfHPOTerms);
        return actualNumberOfHPOTerms >= minimumNumberOfHPOTerms;
    }

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

    public boolean confirmRareDiseaseDiagnosisFieldIsEmpty(String diagnosisValue){
        Wait.forElementToBeDisplayed(driver, diagnosisField);
        return (!diagnosisField.getText().contains(diagnosisValue));

    }
    //Method added by @Stag for filling the ClinicalQuestionsPage
    public void fillClinicalQuestionPageWithGivenParams(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "DiseaseStatus": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        Wait.forElementToBeClickable(driver,diseaseStatusDropdown);
                        Click.element(driver, diseaseStatusDropdown);
                        Click.element(driver, dropdownValue.findElement(By.xpath("//span[text()='"+paramNameValue.get(key)+"']")));
                    }
                    break;
                }
                case "AgeOfOnset": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        String[] age_of_onsets = paramNameValue.get(key).split(",");
                        Wait.forElementToBeDisplayed(driver,ageOfOnsetYearsField);
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
                        List<WebElement> hpoRows = seleniumLib.getElements(By.xpath("//table[contains(@class,'--hpo')]/tbody/tr"));
                        if(hpoRows != null && hpoRows.size() > 0){
                            for(WebElement row:hpoRows){
                                hpoValue = row.findElement(By.xpath("./td[1]")).getText();
                                if(hpoValue.equalsIgnoreCase(paramNameValue.get(key))){
                                    isExists = true;
                                    break;//for loop
                                }
                                //Ideally we need to check where Present is selected or not also.
                                ////table[contains(@class,'--hpo')]/tbody/tr[1]/td[2]//input[@value='Present']
                            }//for
                        }
                        if(!isExists) {
                            searchAndSelectRandomHPOPhenotype(paramNameValue.get(key));//Re-using existing method
                        }
                    }
                    break;
                }

            }//switch
        }//for
    }//method
}
