package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NgisPatientTwo;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FamilyMemberSearchPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//h1[contains(text(),'Find a family member')]")
    public WebElement pageTitle;

    @FindBy(css = "p[class*='patient-search__intro']")
    public WebElement pageDescription;

    @FindBy(css = "h3[class*='field-label']")
    public WebElement yesNoFieldLabel;

    @FindBy(xpath = "//button[text()='Yes']")
    public WebElement yesButton;

    @FindBy(id = "nhsNumber")
    public WebElement nhsNumber;

    @FindBy(css = "label[for*='nhsNumber']")
    public WebElement nhsNumberLabel;

    @FindBy(xpath = "//legend[text()='Date of birth']")
    public WebElement dateOfBirthLabel;

    @FindBy(id = "dateDay")
    public WebElement dateDay;

    @FindBy(id = "dateMonth")
    public WebElement dateMonth;

    @FindBy(id = "dateYear")
    public WebElement dateYear;

    @FindBy(xpath = "//button[contains(string(),'Search')]")
    public WebElement searchButton;

    @FindBy(xpath = "//button[text()='No']")
    public WebElement noButton;

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(css = "label[for*='firstName']")
    public WebElement firstNameLabel;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(css = "label[for*='lastName']")
    public WebElement lastNameLabel;

    @FindBy(css = "label[for*='gender']")
    public WebElement genderLabel;

    @FindBy(id = "gender")
    public WebElement genderInput;

    @FindBy(id = "postcode")
    public WebElement postcode;

    @FindBy(css = "div[class*='error-message__text']")
    public List<WebElement> validationErrors;

    String errorMessageLocator = "div[class*='error-message']";

    @FindBy(xpath = "//h3[@class='styles_text__1aikh styles_text--3__117-L styles_no-results__header__1RMRD']")
    public WebElement errorMessage1;

    @FindBy(xpath = "//p[@class='styles_text__1aikh styles_text--7__3Mb56 styles_no-results__check-details__1PGfr']")
    public WebElement errorMessage2;

    @FindBy(xpath = "//p[@class='styles_text__1aikh styles_text--5__203Ot styles_search-terms__text__160LA']")
    public WebElement searchStringMessage;

    @FindBy(css = "div[id*='react-select']")
    public WebElement genderValue;

    @FindBy(xpath = "//label[contains(@for,'gender')]//following::div")
    public WebElement genderButton;

    static String searchString = "";

    public FamilyMemberSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public boolean verifyTheElementsOnFamilyMemberSearchPageWhenYesIsSelected() {
        Wait.forElementToBeDisplayed(driver, searchButton);
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(pageTitle);
        expElements.add(pageDescription);
        expElements.add(yesNoFieldLabel);
        expElements.add(yesButton);
        expElements.add(noButton);
        expElements.add(nhsNumberLabel);
        expElements.add(nhsNumber);
        expElements.add(dateOfBirthLabel);
        expElements.add(dateDay);
        expElements.add(dateMonth);
        expElements.add(dateYear);
        expElements.add(searchButton);
        for(int i=0; i<expElements.size(); i++){
            if(!seleniumLib.isElementPresent(expElements.get(i))){
                return false;
            }
        }
        return true;
    }

    public boolean verifyYesBtnSelectedStatus(String expStatus) {
        try {
            String actStatus = yesButton.getAttribute("aria-pressed");
            return actStatus.equalsIgnoreCase(expStatus);
        }catch(Exception exp){
            return false;
        }
    }

    public boolean verifyYesButtonBackgroundColour(String expBgColor) {
        try {
            String actBgColor = yesButton.getCssValue("background-color");
            return actBgColor.equalsIgnoreCase(expBgColor);
        }catch(Exception exp){
            return false;
        }
    }

    public void clickNoButton() {
        seleniumLib.clickOnWebElement(noButton);
    }

    public void clickYesButton() {
        seleniumLib.clickOnWebElement(yesButton);
    }

    public boolean verifyTheElementsOnPatientSearchAreDisplayedWhenNoIsSelected() {
        Wait.forElementToBeDisplayed(driver, searchButton);
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(pageTitle);
        expElements.add(pageDescription);
        expElements.add(yesNoFieldLabel);
        expElements.add(yesButton);
        expElements.add(noButton);
        expElements.add(dateOfBirthLabel);
        expElements.add(dateDay);
        expElements.add(dateMonth);
        expElements.add(dateYear);
        expElements.add(firstName);
        expElements.add(lastName);
        expElements.add(genderLabel);
        expElements.add(genderInput);
        expElements.add(postcode);
        expElements.add(searchButton);
        for(int i=0; i<expElements.size(); i++){
            if(!seleniumLib.isElementPresent(expElements.get(i))){
                return false;
            }
        }
        return true;
    }

    public void clickSearchButton() {
        seleniumLib.clickOnWebElement(searchButton);
    }

   public void validateErrorsAreDisplayedForSkippedMandatoryValuesForYes() {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector(errorMessageLocator), 0);
        Assert.assertEquals("NHS Number is required.", seleniumLib.getText(validationErrors.get(0)));
        Assert.assertEquals("Enter a day", seleniumLib.getText(validationErrors.get(1)));
        Assert.assertEquals("Enter a month", seleniumLib.getText(validationErrors.get(2)));
        Assert.assertEquals("Enter a year", seleniumLib.getText(validationErrors.get(3)));
        Assert.assertEquals("rgba(221, 37, 9, 1)", nhsNumberLabel.getCssValue("color").toString());
        Assert.assertEquals("rgba(221, 37, 9, 1)", dateOfBirthLabel.getCssValue("color").toString());
    }

    public void validateErrorsAreDisplayedForSkippingMandatoryValuesNo() {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector(errorMessageLocator), 0);
        Assert.assertEquals("Enter a day", seleniumLib.getText(validationErrors.get(0)));
        Assert.assertEquals("Enter a month", seleniumLib.getText(validationErrors.get(1)));
        Assert.assertEquals("Enter a year", seleniumLib.getText(validationErrors.get(2)));
        Assert.assertEquals("First name is required.", seleniumLib.getText(validationErrors.get(3)));
        Assert.assertEquals("Last name is required.", seleniumLib.getText(validationErrors.get(4)));
        Assert.assertEquals("Gender is required.", seleniumLib.getText(validationErrors.get(5)));
        Assert.assertEquals("rgba(221, 37, 9, 1)", dateOfBirthLabel.getCssValue("color").toString());
        Assert.assertEquals("rgba(221, 37, 9, 1)", firstNameLabel.getCssValue("color").toString());
        Assert.assertEquals("rgba(221, 37, 9, 1)", lastNameLabel.getCssValue("color").toString());
        Assert.assertEquals("rgba(221, 37, 9, 1)", genderLabel.getCssValue("color").toString());
    }
    public void searchWithAlreadyAddedPatientDetailsUsingNHSNumberAndDOB() {
        Wait.forElementToBeDisplayed(driver, nhsNumber);
        //For Rare Disease Referral, NGISPatientTwo has bee considered. So providing same details to search again
        nhsNumber.sendKeys(NgisPatientTwo.NHS_NUMBER);
        dateDay.sendKeys(NgisPatientTwo.DAY_OF_BIRTH);
        dateMonth.sendKeys(NgisPatientTwo.MONTH_OF_BIRTH);
        dateYear.sendKeys(NgisPatientTwo.YEAR_OF_BIRTH);
        searchString = "You’ve searched for "+NgisPatientTwo.NHS_NUMBER+", "+NgisPatientTwo.YEAR_OF_BIRTH+"-"+NgisPatientTwo.MONTH_OF_BIRTH+"-"+NgisPatientTwo.DAY_OF_BIRTH;
        seleniumLib.clickOnWebElement(searchButton);
    }
    public boolean verifyMessageOfExistingPatient(String expMessage1,String expMessage2) {
        Wait.forElementToBeDisplayed(driver,errorMessage1);
        String actMessage1 = errorMessage1.getText();
        Wait.forElementToBeDisplayed(driver,errorMessage2);
        String actMessage2 = errorMessage2.getText();
        Wait.forElementToBeDisplayed(driver,searchStringMessage);
        if(!searchStringMessage.getText().contains(searchString)){
            Debugger.println("Expected String: "+searchString+" not displayed.");
            return false;
        }
        //Debugger.println("SearchString Message: "+searchStringMessage.getText());
        if(expMessage1.trim().equalsIgnoreCase(actMessage1.trim())){
            if(expMessage2.trim().equalsIgnoreCase(actMessage2.trim())){
                return true;
            }
        }
        Debugger.println("Expected Message:"+expMessage1+" "+expMessage2+", but Actual:"+actMessage1+" "+actMessage2);
        return false;
    }
    public void fillInDOBFirstNameLastNameGender() {
        Wait.forElementToBeDisplayed(driver, dateDay);
        dateDay.sendKeys(NgisPatientTwo.DAY_OF_BIRTH);
        dateMonth.sendKeys(NgisPatientTwo.MONTH_OF_BIRTH);
        dateYear.sendKeys(NgisPatientTwo.YEAR_OF_BIRTH);
        firstName.sendKeys(NgisPatientTwo.FIRST_NAME);
        lastName.sendKeys(NgisPatientTwo.LAST_NAME);
        Click.element(driver, genderButton);
        Click.element(driver, genderValue.findElement(By.xpath("//span[text()='"+NgisPatientTwo.GENDER+"']")));
        seleniumLib.clickOnWebElement(genderInput);
        Actions.selectValueFromDropdown(genderInput,NgisPatientTwo.GENDER);
        searchString = "You’ve searched for "+NgisPatientTwo.FIRST_NAME+", "+NgisPatientTwo.LAST_NAME+", "+NgisPatientTwo.GENDER+", "+NgisPatientTwo.YEAR_OF_BIRTH+"-"+NgisPatientTwo.MONTH_OF_BIRTH+"-"+NgisPatientTwo.DAY_OF_BIRTH;
        seleniumLib.clickOnWebElement(searchButton);
    }

    public void searchFamilyMemberWithGivenParams(String searchParams) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "NHSNumber": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        nhsNumber.sendKeys(paramNameValue.get(key));
                    }
                    break;
                }
                case "DOB": {
                    String dobValue = paramNameValue.get(key);
                    if(dobValue != null && !dobValue.isEmpty()) {
                        String[] dobSplit = dobValue.split("-");
                        dateDay.sendKeys(dobSplit[0]);
                        dateMonth.sendKeys(dobSplit[1]);
                        dateYear.sendKeys(dobSplit[2]);
                    }
                    break;
                }
                case "FirstName": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        firstName.sendKeys(paramNameValue.get(key));
                    }
                    break;
                }
                case "LastName": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        lastName.sendKeys(paramNameValue.get(key));
                    }
                    break;
                }
                case "Gender": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        genderButton.click();
                        genderValue.findElement(By.xpath("//span[text()='" + paramNameValue.get(key) + "']")).click();
                    }
                    break;
                }
                case "Postcode": {
                    if(paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                        postcode.sendKeys(paramNameValue.get(key));
                    }
                    break;
                }
            }//switch
        }//for
        seleniumLib.clickOnWebElement(searchButton);
    }//method

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

}//end