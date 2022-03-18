package co.uk.gel.proj.pages;

import co.uk.gel.csvmodels.SpineDataModelFromCSV;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.TestDataProvider.NgisPatientOne;
import co.uk.gel.proj.TestDataProvider.NgisPatientTwo;
import co.uk.gel.proj.TestDataProvider.SpinePatientOne;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.*;

import static co.uk.gel.proj.pages.PatientDetailsPage.newPatient;
import static co.uk.gel.proj.util.RandomDataCreator.getRandomUKPostCode;


//public class PatientSearchPage {
public class PatientSearchPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    public static NewPatient testData = new NewPatient();
    static Faker faker = new Faker();
    SeleniumLib seleniumLib;

    public PatientSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public WebElement title;
    @FindBy(xpath = "//input[@id='birthDateDay']")
    public WebElement dateDay;
    @FindBy(xpath = "//input[@id='birthDateMonth']")
    public WebElement dateMonth;
    @FindBy(xpath = "//input[@id='birthDateYear']")
    public WebElement dateYear;

    @FindBy(xpath = "//input[@id='firstName']")
    public WebElement firstName;
    @FindBy(xpath = "//input[@id='familyName']")
    public WebElement familyName;
    @FindBy(xpath = "//input[@id='lastName']")
    public WebElement lastName;
    @FindBy(xpath = "//input[@id='postcode']")
    public WebElement postcode;

    @FindBy(css = "h1[class*='page-title']")
    public WebElement pageTitle;

    @FindBy(css = "p[class*='patient-search__intro']")
    public WebElement pageDescription;

    @FindBy(css = "h3[class*='field-label']")
    public WebElement yesNoFieldLabel;

    @FindBy(xpath = "//button[text()='Yes']")
    public WebElement yesButton;

    @FindBy(xpath = "//button[text()='Yes']/*[name()='svg']")
    public WebElement yesButtonSVG;

    @FindBy(css = "legend[class*='field-label']")
    public WebElement dateLabel;

    @FindBy(name = "loginfmt")
    public WebElement emailAddressField;

    @FindBy(xpath = "//input[@name='passwd']")
    public WebElement passwordField;

    @FindBy(css = "input[type*='submit']")
    public WebElement nextButton;

    @FindBy(id = "nhsNumber")
    public WebElement nhsNumber;

    @FindBy(css = "label[for*='nhsNumber']")
    public WebElement nhsNumberLabel;

    @FindBy(xpath = "//p[contains(text(),'ve searched for')]/strong")
    public WebElement nhsNumberHiddenLabel;

    @FindBy(css = "a[href*='/test-order/new-patient']")
    public WebElement createNewPatientLink;

    @FindBy(xpath = "//legend[text()='Date of birth']")
    public WebElement dateOfBirthLabel;

    @FindBy(css = "div[class*='error-message__text']")
    public List<WebElement> validationErrors;

    @FindBy(css = "label[for*='firstName']")
    public WebElement firstNameLabel;

    @FindBy(css = "label[for*='lastName']")
    public WebElement lastNameLabel;

    @FindBy(css = "label[for*='gender']")
    public WebElement genderLabel;

    @FindBy(css = "label[for*='postcode']")
    public WebElement postcodeLabel;

    @FindBy(xpath = "//label[contains(@for,'gender')]//following::div")
    public WebElement genderButton;

    @FindBy(xpath = "//label[contains(@for,'administrativeGender')]//following::div")
    public WebElement administrativeGenderButton;

    @FindBy(css = "div[id*='react-select']")
    public WebElement genderValue;

    @FindBy(xpath = "//button[text()='No']")
    public WebElement noButton;

    @FindBy(xpath = "//button[text()='No']/*[name()='svg']")
    public WebElement noButtonSVG;

    @FindBy(css = "button[class*='search']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[@type='submit'][contains(@class,'SearchForm')]")
    public WebElement searchButtonByXpath;


    @FindBy(xpath = "//a[contains(@class,'patient-card')]")
    public WebElement patientCard;

    @FindBy(css = "h3[class*='results__header']")
    public WebElement patientSearchResultsHeader;

    @FindBy(xpath = "//a//span[contains(@class,'child-element')]")
    public WebElement patientCardBadge;

    @FindBy(css = "p[class*='patient-name']")
    public WebElement patientFullName;

    @FindBy(xpath = "//p[contains(string(),'Born')]")
    public WebElement patientDateOfBirth;

    @FindBy(xpath = "//p[contains(string(),'Gender')]")
    public WebElement patientGender;

    @FindBy(xpath = "//p[contains(string(),'NHS No. ')]")
    public WebElement patientNSNo;

    @FindBy(xpath = "//p[contains(string(),'Address')]")
    public WebElement patientAddress;

    @FindBy(xpath = "//h3[contains(string(), 'No patient found')]")
    public WebElement noPatientFoundLabel;

    @FindBy(css = "div[class*='styles_error-message']")
    public WebElement nHSNumberFieldValidationErrorMessageLabel;

    @FindBy(css = "div[class*='styles_error-message__text__1v2Kl']")
    public WebElement dobFieldValidationErrorMessageLabel;

    @FindBy(xpath = "//div[@class='styles_search-terms__1Udiy']/p/strong")
    public WebElement youHaveSearchedForLabel;

    @FindBy(xpath = "//a[text()='Log out']")
    public WebElement logout;

    @FindBy(xpath = "//a[text()='create a new patient record']")
    public WebElement createNewPatientRecordLink; // create a new patient link

    String noResultsLocator = "img[class*='no-results__img']";
    String errorMessageLocator = "div[class*='error-message']";
    String autoCompleteAttributeOff = "autoComplete_off";

    @FindBy(id = "otherTileText")
    public WebElement useAnotherAccount;

    @FindBy(xpath = "//h3[contains(@class,'results__header')]")
    public WebElement patientSearchResult;

    @FindBy(xpath = "//label[@for='gender']/..//div[contains(@class,'option')]/span/span")
    public List<WebElement> genderValues;

    //For checking Panels Search field is enabled or not, declared here to re-use the existing method of enable/disable
    @FindBy(xpath = "//input[@placeholder='e.g. Adult solid tumours for rare disease']")
    public WebElement panelsSearchFieldPlaceHolder;

    //For Spine patient Search
    @FindBy(xpath = "//div[@data-testid='notification-error']//span")
    public WebElement notificationError;

    @FindBy(xpath = "//button[@type='submit' and contains(text(),'Continue')]")
    public WebElement continueButton;

    @FindBy(xpath = "//button[@type='button']//span[contains(text(),'Edit patient details')]")
    public WebElement editPatientDetails;

    @FindBy(xpath = "//div[@id='passwordError']")
    public WebElement loginPassWordError;

    @FindBy(xpath = "//span[@data-tip]/span[@class='child-element']")
    public WebElement mergeStatusOnPatientCard;

    @FindBy(xpath = "//span[@data-tip]")
    public WebElement tooltipOnPatientCard;

    public void pageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "/patient-search");
        Wait.forElementToBeDisplayed(driver, yesButton);
    }

    public String getYesBtnSelectedAttribute() {
        String value = yesButton.getAttribute("aria-pressed");
        //Debugger.println("colour is: " + value);
        return value;
    }

    public String getYesButtonColour() {
        String backGroundColour = yesButton.getCssValue("background-color");
        //Debugger.println("colour is: " + backGroundColour);
        return backGroundColour;
    }

    public String getNoBtnSelectedAttribute() {
        String value = noButton.getAttribute("aria-pressed");
        //Debugger.println("colour is: " + value);
        return value;
    }

    public boolean fillInValidPatientDetailsUsingNHSNumberAndDOB(String nhsNo, String dayOfBirth, String monthOfBirth, String yearOfBirth) {
        try {
            //Debugger.println("SPINE SEARCH:NHS:"+nhsNo+":"+dayOfBirth+"-"+monthOfBirth+"-"+yearOfBirth);
            Wait.forElementToBeDisplayed(driver, nhsNumber);
            nhsNumber.sendKeys(nhsNo);
            dateDay.sendKeys(dayOfBirth);
            dateMonth.sendKeys(monthOfBirth);
            dateYear.sendKeys(yearOfBirth);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInValidPatientDetailsUsingNHSNumberAndDOB:" + exp);
            //SeleniumLib.takeAScreenShot("fillInValidPatientDetailsUsingNHSNumberAndDOB.jpg");
            return false;
        }

    }

    public boolean clickNoButton() {
        try {
            if (!Wait.isElementDisplayed(driver, noButton, 30)) {
                Debugger.println("No button not present:");
                return false;
            }
            noButton.click();
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in clickNoButton:" + exp);
            return false;
        }
    }

    public void clickSearchButton(WebDriver driver) {
        Wait.forElementToBeClickable(driver, searchButton);
        Click.element(driver, searchButton);
    }

    public boolean clickSearchButtonByXpath() {
        try {
            if (!Wait.isElementDisplayed(driver, searchButtonByXpath, 30)) {
                Debugger.println("Search Button could not locate on Patient Search Page.\n" + driver.getCurrentUrl());
                return false;
            }
            seleniumLib.clickOnWebElement(searchButtonByXpath);
            seleniumLib.sleepInSeconds(2);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on Search Patient Button:" + exp + "\n" + driver.getCurrentUrl());
            return false;
        }
    }


    public String checkThatPatientCardIsDisplayed() {
        String badge = "";
        try {
            if (!Wait.isElementDisplayed(driver, patientCard, 30)) {
                Debugger.println("Expected Patient card not displayed." + driver.getCurrentUrl());
                return null;
            }
            if (!Wait.isElementDisplayed(driver, patientCardBadge, 10)) {
                Debugger.println("Expected patientCardBadge displayed." + driver.getCurrentUrl());
                return null;
            }
            badge = patientCardBadge.getText();
            return badge.trim();
        } catch (Exception exp) {
            try {
                badge = seleniumLib.getText(patientCardBadge);
                return badge;
            } catch (Exception exp1) {
                Debugger.println("Exception from checkThatPatientCardIsDisplayed:" + exp1 + "\n" + driver.getCurrentUrl());
                return null;
            }
        }
    }

    public void loginToTestOrderingSystemAsStandardUser(WebDriver driver) {
        String email = AppConfig.getApp_username();
        String password = AppConfig.getApp_password();
        loginToTestOrderingSystem(email, password);
    }

    public void loginToTestOrderingSystem(String email, String password) {
//        Actions.deleteCookies(driver);
        try {
            if (Wait.isElementDisplayed(driver, useAnotherAccount, 5)) {//If the element is not displayed, even after the waiting time
                Debugger.println("Clicking on useAnotherAccount to Proceed.");
                useAnotherAccount.click();
                Wait.seconds(3);
                if (!Wait.isElementDisplayed(driver, emailAddressField, 60)) {//Click on UseAnotherAccount and Proceed.
                    Debugger.println("Email field or UseAnotherAccount option are not available. URL:" + driver.getCurrentUrl());
                    SeleniumLib.takeAScreenShot("EmailOrUserAccountNot.jpg");
                    Assert.fail("Email field or UseAnotherAccount option are not available.");
                }
            }
            try {
                emailAddressField.sendKeys(email);
            } catch (Exception exp1) {
                seleniumLib.sendValue(emailAddressField, email);
            }
            Wait.seconds(2);
            try {
                seleniumLib.clickOnWebElement(nextButton);
            } catch (Exception exp1) {
                Actions.clickElement(driver, nextButton);
            }
            Wait.seconds(2);
            try {
                passwordField.sendKeys(password);
                Debugger.println("Password provided");
            } catch (Exception exp1) {
                seleniumLib.sendValue(passwordField, password);
            }
            Wait.seconds(2);
            try {
                seleniumLib.clickOnWebElement(nextButton);
                Debugger.println("Password Next button clicked");
            } catch (Exception exp1) {
                Actions.clickElement(driver, nextButton);
            }
            Wait.seconds(2);
            try {
                if (loginPassWordError.isDisplayed()) {
//                    Debugger.println("Login Password Error.......");
                    seleniumLib.sendValue(passwordField, password);
                    seleniumLib.clickOnWebElement(nextButton);
                    Wait.seconds(3);
                }
                if (loginPassWordError.isDisplayed()) {
                    SeleniumLib.takeAScreenShot("TOMSLoginFailed.jpg");
                    Assert.fail("Could not login to Test Order System.");
                }
            } catch (Exception exp) {

            }
        } catch (Exception exp) {
            Debugger.println("PatientSearch:loginToTestOrderingSystemAsServiceDeskUser:Exception:\n" + exp);
            SeleniumLib.takeAScreenShot("TOMSLogin.jpg");
            Assert.fail("Exception from loginToTestOrderingSystemAsStandardUser " + exp);
        }
    }

    public void loginToTestOrderingSystem(WebDriver driver, String userType) {
        String email = "", password = "";
        if (userType.equalsIgnoreCase("GEL_NORMAL_USER")) {
            email = AppConfig.getApp_username();
            password = AppConfig.getApp_password();
        } else {
            email = AppConfig.getPropertyValueFromPropertyFile("SUPER_USERNAME");
            password = AppConfig.getPropertyValueFromPropertyFile("SUPER_PASSWORD");
        }
//        Debugger.println("Email:"+email+",PWD:"+password);
        loginToTestOrderingSystem(email, password);
    }

    public boolean fillInValidPatientDetailsUsingNOFields(String searchParams) {
        try {
            //DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female
            // Extract the patient details from the example-table
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
            Set<String> paramsKey = paramNameValue.keySet();
            for (String key : paramsKey) {
                switch (key) {
                    case "DOB": {
                        String dobValue = paramNameValue.get(key);
                        String[] dobSplit = dobValue.split("-");
                        seleniumLib.sendValue(dateDay, dobSplit[0]);
                        seleniumLib.sendValue(dateMonth, dobSplit[1]);
                        seleniumLib.sendValue(dateYear, dobSplit[2]);
                        break;
                    }
                    case "FirstName": {
                        seleniumLib.sendValue(firstName, paramNameValue.get(key));
                        break;
                    }
                    case "LastName": {
                        try {
                            seleniumLib.sendValue(lastName, paramNameValue.get(key));
                        } catch (Exception exp1) {
                            seleniumLib.sendValue(familyName, paramNameValue.get(key));
                        }
                        break;
                    }
                    case "Gender": {
                        if (!selectGender(genderButton, paramNameValue.get(key))) {
                            selectGender(administrativeGenderButton, paramNameValue.get(key));
                        }
                        break;
                    }
                    case "Postcode": {
                        seleniumLib.sendValue(postcode, paramNameValue.get(key));
                        break;
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in fillInValidPatientDetailsUsingNOFields:" + exp);
            return false;
        }
    }

    public void checkSearchResultHeaderIsDisplayed(WebDriver driver, String resultHeader) {
        Wait.forElementToBeDisplayed(driver, patientCard);
        Wait.forElementToBeDisplayed(driver, patientSearchResultsHeader);
        //Debugger.println("The actual search result header is :" + patientSearchResultsHeader.getText());
        Assert.assertEquals(resultHeader, patientSearchResultsHeader.getText().trim());
    }

    public String getText(WebElement element) {
        Wait.forElementToBeDisplayed(driver, element);
        return element.getText();
    }

    public void validationErrorsAreDisplayedForSkippingMandatoryValues() {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector(errorMessageLocator), 0);
        Assert.assertEquals("NHS Number is required.", getText(validationErrors.get(0)));
        Assert.assertEquals("Enter a day", getText(validationErrors.get(1)));
        Assert.assertEquals("Enter a month", getText(validationErrors.get(2)));
        Assert.assertEquals("Enter a year", getText(validationErrors.get(3)));
        Assert.assertEquals("rgba(221, 37, 9, 1)", nhsNumberLabel.getCssValue("color").toString());
        Assert.assertEquals("rgba(221, 37, 9, 1)", dateOfBirthLabel.getCssValue("color").toString());

    }


    public void validationErrorsAreDisplayedForSkippingMandatoryValuesDoYouHavePatientNHSNumberNO() {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector(errorMessageLocator), 0);
        Assert.assertEquals("Enter a day", getText(validationErrors.get(0)));
        Assert.assertEquals("Enter a month", getText(validationErrors.get(1)));
        Assert.assertEquals("Enter a year", getText(validationErrors.get(2)));
        Assert.assertEquals("First name is required.", getText(validationErrors.get(3)));
        Assert.assertEquals("Last name is required.", getText(validationErrors.get(4)));
        Assert.assertEquals("Gender is required.", getText(validationErrors.get(5)));
        Assert.assertEquals("rgba(221, 37, 9, 1)", dateOfBirthLabel.getCssValue("color").toString());
        Assert.assertEquals("rgba(221, 37, 9, 1)", firstNameLabel.getCssValue("color").toString());
        Assert.assertEquals("rgba(221, 37, 9, 1)", lastNameLabel.getCssValue("color").toString());
        Assert.assertEquals("rgba(221, 37, 9, 1)", genderLabel.getCssValue("color").toString());
    }

    public boolean clickPatientCard() {
        try {
            Actions.retryClickAndIgnoreElementInterception(driver, patientCard);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted
            // patientCard.click();
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clickPatientCard:" + exp);
            return false;
        }
    }

    public void fillInDifferentValidPatientDetailsUsingNHSNumberAndDOB(String nhsNo, String dayOfBirth, String monthOfBirth, String yearOfBirth) {

        Wait.forElementToBeDisplayed(driver, nhsNumber);
        Actions.clearInputField(nhsNumber);  //nhsNumber.clear();
        nhsNumber.sendKeys(nhsNo);
        Actions.clearInputField(dateDay);
        dateDay.sendKeys(dayOfBirth);
        Actions.clearInputField(dateMonth);
        dateMonth.sendKeys(monthOfBirth);
        Actions.clearInputField(dateYear);
        dateYear.sendKeys(yearOfBirth);
    }

    public void secondPatientDetailsAreDisplayedInTheCard() {

        String expectedFirstname = "ALEXANDRINA";
        String expectedLastname = "MCBRYDE";
        String expectedTitle = "MISS";
        String expectedFullName = expectedLastname + ", " + expectedFirstname + " (" + expectedTitle + ")";
        Wait.forElementToBeDisplayed(driver, patientFullName);
        String actualFullName = patientFullName.getText().trim();

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        String expectedDayOfBirth = "11";
        String expectedMonthOfBirth = "04";
        String expectedYearOfBirth = "1909";
        String expectedDateOfBirth = expectedDayOfBirth + "-" + months[Integer.parseInt(expectedMonthOfBirth) - 1] + "-" + expectedYearOfBirth;
        //Debugger.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + expectedDateOfBirth);
        String actualFullDOB = patientDateOfBirth.getText().trim();

        String expectedGender = "Female";
        String actualGender = patientGender.getText().trim();

        String expectedNHSNumber = "9449304580";
        String actualNHSNumber = patientNSNo.getText().trim();

        String expectedAddressLine1 = "27 KINGSTON ROAD";
        String expectedAddressLine2 = "EPSOM";
        String expectedAddressLine3 = "SURREY";
        String expectedAddressLine4 = "";
        String expectedPostcode = "KT17 2EG";
        String expectedFullAddress = "Address " + expectedAddressLine1 + ", " + expectedAddressLine2 + ", " +
                expectedAddressLine3 + ", " + expectedPostcode;
        String actualAddress = patientAddress.getText().trim();

        //Debugger.println("Expected full name = " + expectedFullName + ", Actual full name " + actualFullName);
        Assert.assertEquals(expectedFullName, actualFullName);

        //Debugger.println("Expected DOB = " + expectedDateOfBirth + ", Actual DOB: " + actualFullDOB);
        //Assert.assertTrue(actualFullDOB.contains("Born " + expectedDayOfBirth));
        Assert.assertTrue(actualFullDOB.contains("Born " + expectedDateOfBirth));

        //Debugger.println("Expected Gender= " + expectedGender + ", Actual Gender: " + actualGender);
        Assert.assertEquals("Gender " + expectedGender, actualGender);

        //Debugger.println("Expected nhs no = " + expectedNHSNumber + ", Actual nhs no: " + actualNHSNumber);
        Assert.assertEquals("NHS No. " + expectedNHSNumber, actualNHSNumber);

        //Debugger.println("Expected address = " + expectedFullAddress + ", Actual address: " + actualAddress);
        Assert.assertEquals(expectedFullAddress, actualAddress);
    }


    public void checkNHSNumberAndDOBareDisplayed(String expectedNHSNumber, String expectedDOB, String expErrorText) {
        Wait.forElementToBeDisplayed(driver, nhsNumberHiddenLabel);
        //Debugger.println("Actual NHS Number and DOB displayed on the page " + nhsNumberHiddenLabel.getText());
        Assert.assertTrue(nhsNumberHiddenLabel.getText().contains(expectedNHSNumber));
        String expectedDOBInYYYYDDMM = TestUtils.dateFormatReverserToYYYYMMDD(expectedDOB.trim());
        Assert.assertTrue(nhsNumberHiddenLabel.getText().contains(expectedDOBInYYYYDDMM));
        Wait.forElementToBeDisplayed(driver, noPatientFoundLabel);
        Assert.assertEquals(expErrorText, noPatientFoundLabel.getText());
    }

    public boolean checkCreateNewPatientLinkDisplayed(String hyperLinkText) {
        try {
            Wait.forElementToBeDisplayed(driver, createNewPatientLink);
            if (!hyperLinkText.equalsIgnoreCase(createNewPatientLink.getText())) {
                Debugger.println("Expected Message:" + hyperLinkText + ", Actual: " + createNewPatientLink.getText());
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from checkCreateNewPatientLinkDisplayed:" + exp);
            return false;
        }
    }

    public void validateFormLabelFontFace(String fontFace) {
        String expectedFontFace = StylesUtils.convertFontFaceStringToCSSProperty(fontFace);
        Assert.assertEquals(expectedFontFace, nhsNumberLabel.getCssValue("font-weight"));
        Assert.assertEquals(expectedFontFace, dateOfBirthLabel.getCssValue("font-weight"));
    }

    public void validateFormLabelSize(String fontSize) {
        Assert.assertEquals(String.valueOf(fontSize), nhsNumberLabel.getCssValue("font-size"));
        Assert.assertEquals(String.valueOf(fontSize), dateOfBirthLabel.getCssValue("font-size"));
    }

    public void validateFormLabelColour(String fontColor) {
        String expectedFontColour = StylesUtils.convertFontColourStringToCSSProperty(fontColor);
        //Debugger.println("EXPECTED RESULT: " + expectedFontColour);
        //Debugger.println("ACTUAL RESULT  : " + nhsNumberLabel.getCssValue("color"));
        Assert.assertEquals(expectedFontColour, nhsNumberLabel.getCssValue("color"));
        Assert.assertEquals(expectedFontColour, dateOfBirthLabel.getCssValue("color"));
    }

    public void checkTheErrorMessage(String errorMessage, String fontColor) {
        Wait.forElementToBeDisplayed(driver, nHSNumberFieldValidationErrorMessageLabel);
        //Debugger.println("EXPECTED RESULT: " + errorMessage);
        //Debugger.println("ACTUAL RESULT  : " + nHSNumberFieldValidationErrorMessageLabel.getText());
        Assert.assertEquals(errorMessage, nHSNumberFieldValidationErrorMessageLabel.getText());
        String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(fontColor);
        Assert.assertEquals(expectedFontColor, nHSNumberFieldValidationErrorMessageLabel.getCssValue("color"));
    }

    public void checkTheErrorMessagesInDOB(String errorMessage, String fontColor) {
        // dynamically construct the expected validation error for the future date scenario by appending today's date
        if (errorMessage.endsWith("today")) {
            errorMessage = TestUtils.removeAWord(errorMessage, "today");
            errorMessage = errorMessage + TestUtils.todayInDDMMYYYFormat();
        }
        Wait.forElementToBeDisplayed(driver, dobFieldValidationErrorMessageLabel);
        Assert.assertEquals(errorMessage, dobFieldValidationErrorMessageLabel.getText());
        String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(fontColor);
        Assert.assertEquals(expectedFontColor, dobFieldValidationErrorMessageLabel.getCssValue("color"));
    }

    public void checkThatPostcode(String postcode) {
        Wait.forElementToBeDisplayed(driver, postcodeLabel);
        Assert.assertEquals(postcode, postcodeLabel.getText());
        Assert.assertEquals(StylesUtils.convertFontColourStringToCSSProperty("#212b32"), postcodeLabel.getCssValue("color"));

    }

    public void fillInValidSecondPatientDetailsUsingNOFields(String searchParams) {
        //DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female
        // Extract the patient details from the example-table

        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "DOB": {
                    String dobValue = paramNameValue.get(key);
                    String[] dobSplit = dobValue.split("-");
                    Actions.clearInputField(dateDay);
                    Wait.seconds(1);
                    dateDay.sendKeys(dobSplit[0]);
                    Actions.clearInputField(dateMonth);
                    Wait.seconds(1);
                    dateMonth.sendKeys(dobSplit[1]);
                    Actions.clearInputField(dateYear);
                    Wait.seconds(1);
                    dateYear.sendKeys(dobSplit[2]);
                    break;
                }
                case "FirstName": {
                    Actions.clearTextField(firstName);
                    firstName.sendKeys(paramNameValue.get(key));
                    break;
                }
                case "LastName": {
                    seleniumLib.sendValue(familyName, paramNameValue.get(key));
                    break;
                }
                case "Gender": {
                    if (!selectGender(genderButton, paramNameValue.get(key))) {
                        selectGender(administrativeGenderButton, paramNameValue.get(key));
                    }
                    break;
                }
                case "Postcode": {
                    Actions.clearTextField(postcode);
                    postcode.sendKeys(paramNameValue.get(key));
                    break;
                }
            }
        }

    }

    public void verifyTheTitleOfThePage(String titleOfPage) {
        Wait.forElementToBeDisplayed(driver, searchButton);
        //Debugger.println("The actual page title  is :" + pageTitle.getText());
        Assert.assertEquals(titleOfPage, pageTitle.getText().trim());
    }

    public void verifyTheDescriptionOfThePage(String DescriptionOfPage) {
        String actualPageDescription = pageDescription.getText();
        //Debugger.println("The actual Description title  is :" + pageDescription.getText());
        Assert.assertTrue(actualPageDescription.contains(DescriptionOfPage));
    }

    public boolean clickOnFieldsAndVerifyAutoCompleteIsDisabled(String[] textFieldElements) {
        boolean isAutoComplete = false;
        for (String fieldElement : textFieldElements) {
            switch (fieldElement) {
                case "nhsNumber": {
                    isAutoComplete = verifyFieldHasAutoCompleteDisabled(nhsNumber);
                    break;
                }
                case "dateDay": {
                    isAutoComplete = verifyFieldHasAutoCompleteDisabled(dateDay);
                    break;
                }
                case "dateMonth": {
                    isAutoComplete = verifyFieldHasAutoCompleteDisabled(dateMonth);
                    break;
                }
                case "dateYear": {
                    isAutoComplete = verifyFieldHasAutoCompleteDisabled(dateYear);
                    break;
                }
                case "firstName": {
                    isAutoComplete = verifyFieldHasAutoCompleteDisabled(firstName);
                    break;
                }
                case "lastName": {
                    isAutoComplete = verifyFieldHasAutoCompleteDisabled(familyName);
                    break;
                }
                case "postcode": {
                    isAutoComplete = verifyFieldHasAutoCompleteDisabled(postcode);
                    break;
                }
                case "panelsSearchBox": {
                    isAutoComplete = verifyFieldHasAutoCompleteDisabled(panelsSearchFieldPlaceHolder);
                    break;
                }
                default:
                    throw new IllegalArgumentException("Invalid text field name");
            }
        }//for
        return isAutoComplete;
    }

    public boolean verifyFieldHasAutoCompleteDisabled(WebElement element) {
        try {
            Wait.forElementToBeDisplayed(driver, element);
            element.click();
            Wait.seconds(1);
            String autoCompleteValue = element.getAttribute("list");
            if (!"autocompleteOff".equalsIgnoreCase(autoCompleteValue)) {
                Debugger.println("Expected the element " + element.toString() + " as autocompleteOFF.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying verifyFieldHasAutoCompleteDisabled:" + exp);
            return false;
        }
    }

    public boolean verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected() {
        //if password next button click not happened. still button is still visible try again
        if (!Wait.isElementDisplayed(driver, searchButtonByXpath, 120)) {
            if (nextButton.isDisplayed()) {
                try {
                    seleniumLib.clickOnWebElement(nextButton);
                } catch (Exception exp1) {
                    Actions.clickElement(driver, nextButton);
                }
            }
        }
        // Find elements
        if (!Wait.isElementDisplayed(driver, searchButtonByXpath, 120)) {
            return false;
        }
        List<WebElement> expectedElements = new ArrayList<WebElement>();
        expectedElements.add(pageTitle);
        expectedElements.add(pageDescription);
        expectedElements.add(yesNoFieldLabel);
        expectedElements.add(yesButton);
        expectedElements.add(noButton);
        expectedElements.add(nhsNumberLabel);
        expectedElements.add(nhsNumber);
        expectedElements.add(dateOfBirthLabel);
        expectedElements.add(dateDay);
        expectedElements.add(dateMonth);
        expectedElements.add(dateYear);
        for (int i = 0; i < expectedElements.size(); i++) {
            if (!expectedElements.get(i).isDisplayed()) {
                return false;
            }
        }
        return true;
    }


    public boolean verifyTheElementsOnPatientSearchAreDisplayedWhenNoIsSelected() {

        Wait.forElementToBeDisplayed(driver, searchButtonByXpath, 120);
        List<WebElement> expectedElements = new ArrayList<WebElement>();

        expectedElements.add(pageTitle);
        expectedElements.add(pageDescription);
        expectedElements.add(yesNoFieldLabel);
        expectedElements.add(yesButton);
        expectedElements.add(noButton);
        expectedElements.add(dateOfBirthLabel);
        expectedElements.add(dateDay);
        expectedElements.add(dateMonth);
        expectedElements.add(dateYear);
        expectedElements.add(firstName);
        expectedElements.add(dateDay);
        expectedElements.add(familyName);
        expectedElements.add(postcode);
        expectedElements.add(searchButton);
        for (int i = 0; i < expectedElements.size(); i++) {
            if (!expectedElements.get(i).isDisplayed()) {
                return false;
            }
        }
        return true;

    }

    public void checkTheNoPatientFoundLabel(String expSearchString, String errorMessage, String expectedFontFace) {
        Wait.forElementToBeDisplayed(driver, youHaveSearchedForLabel);
        Map<String, String> expectedResultMap = TestUtils.splitStringIntoKeyValuePairs(expSearchString);

        Assert.assertEquals(true, youHaveSearchedForLabel.getText().contains(expectedResultMap.get("FirstName")));
        Assert.assertEquals(true, youHaveSearchedForLabel.getText().contains(expectedResultMap.get("LastName")));
        Assert.assertEquals(true, youHaveSearchedForLabel.getText().contains(expectedResultMap.get("Gender")));
        Assert.assertEquals(true, youHaveSearchedForLabel.getText().contains(TestUtils.dateFormatReverserToYYYYMMDD(expectedResultMap.get("DOB"))));

        expectedFontFace = StylesUtils.convertFontFaceStringToCSSProperty(expectedFontFace);
        Assert.assertEquals(expectedFontFace, youHaveSearchedForLabel.getCssValue("font-weight"));

        Wait.forElementToBeDisplayed(driver, noPatientFoundLabel);
        Assert.assertEquals(errorMessage, noPatientFoundLabel.getText());
        Assert.assertEquals(expectedFontFace, noPatientFoundLabel.getCssValue("font-weight"));

    }

    public boolean clickCreateNewPatientLinkFromNoSearchResultsPage() {
        try {
            Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector(noResultsLocator), 0);
            Actions.clickElement(driver, createNewPatientRecordLink);
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.clickOnWebElement(createNewPatientRecordLink);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception from verifying clickCreateNewPatientLinkFromNoSearchResultsPage: " + exp1);
                return false;
            }
        }
    }

    public boolean fillInNHSNumberAndDateOfBirth(String patientType) throws IOException {
        if (patientType.equals("NGIS")) {
            return fillInValidPatientDetailsUsingNHSNumberAndDOB(NgisPatientOne.NHS_NUMBER, NgisPatientOne.DAY_OF_BIRTH, NgisPatientOne.MONTH_OF_BIRTH, NgisPatientOne.YEAR_OF_BIRTH);
        } else if (patientType.equals("NHS Spine")) {
            // used to validate the spine details in patient result card
            return fillInValidPatientDetailsUsingNHSNumberAndDOB(SpinePatientOne.NHS_NUMBER, SpinePatientOne.DAY_OF_BIRTH, SpinePatientOne.MONTH_OF_BIRTH, SpinePatientOne.YEAR_OF_BIRTH);
        } else if (patientType.equals("SPINE")) {
            SpineDataModelFromCSV randomNHSDataFromSpineCSV = RandomDataCreator.getAnyNHSDataFromSpineCSV();
            ArrayList<String> dobString = TestUtils.convertDOBNumbersToStrings(randomNHSDataFromSpineCSV.getDATE_OF_BIRTH());
            String dayOfBirth = dobString.get(0);
            String monthOfBirth = dobString.get(1);
            String yearOfBirth = dobString.get(2);
            return fillInValidPatientDetailsUsingNHSNumberAndDOB(randomNHSDataFromSpineCSV.getNHS_NUMBER(), dayOfBirth, monthOfBirth, yearOfBirth);
        } else {
            Debugger.println(" Patient type not found -> provide either NGIS or SPINE patient");
            return false;
        }
    }

    public boolean fillInNHSNumberAndDateOfBirthByProvidingNGISPatientOne() {
        return fillInValidPatientDetailsUsingNHSNumberAndDOB(NgisPatientOne.NHS_NUMBER, NgisPatientOne.DAY_OF_BIRTH, NgisPatientOne.MONTH_OF_BIRTH, NgisPatientOne.YEAR_OF_BIRTH);
    }

    public boolean fillInNHSNumberAndDateOfBirthByProvidingNGISPatientTwo() {
        return fillInValidPatientDetailsUsingNHSNumberAndDOB(NgisPatientTwo.NHS_NUMBER, NgisPatientTwo.DAY_OF_BIRTH, NgisPatientTwo.MONTH_OF_BIRTH, NgisPatientTwo.YEAR_OF_BIRTH);
    }

    public boolean fillInNHSNumberAndDateOfBirthByProvidingRandomSpinePatientRecord() throws IOException {
        try {
            SpineDataModelFromCSV randomNHSDataFromSpineCSV = RandomDataCreator.getAnyNHSDataFromSpineCSV();
            ArrayList<String> dobString = TestUtils.convertDOBNumbersToStrings(randomNHSDataFromSpineCSV.getDATE_OF_BIRTH());
            String dayOfBirth = dobString.get(0);
            String monthOfBirth = dobString.get(1);
            String yearOfBirth = dobString.get(2);
            return fillInValidPatientDetailsUsingNHSNumberAndDOB(randomNHSDataFromSpineCSV.getNHS_NUMBER(), dayOfBirth, monthOfBirth, yearOfBirth);
        } catch (Exception exp) {
            Debugger.println("Exception from fillInNHSNumberAndDateOfBirthByProvidingRandomSpinePatientRecord:" + exp);
            SeleniumLib.takeAScreenShot("fillInNHSNumberAndDateOfBirthByProvidingRandomSpinePatientRecord.jpg");
            return false;
        }
    }

    public boolean windowTitleValidation(String titleText) {
        String actual = driver.getTitle();
        return actual.equalsIgnoreCase(titleText);
    }

    public boolean fillInNonExistingPatientDetailsUsingNHSNumberAndDOB() {
        try {
            testData.setNhsNumber(RandomDataCreator.generateRandomNHSNumber());
            testData.setDay(String.valueOf(faker.number().numberBetween(10, 31)));
            testData.setMonth(String.valueOf(faker.number().numberBetween(10, 12)));
            testData.setYear(String.valueOf(faker.number().numberBetween(1900, 2019)));
            seleniumLib.sendValue(nhsNumber, testData.getNhsNumber());
            seleniumLib.sendValue(dateDay, testData.getDay());
            seleniumLib.sendValue(dateMonth, testData.getMonth());
            seleniumLib.sendValue(dateYear, testData.getYear());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInNonExistingPatientDetailsUsingNHSNumberAndDOB:" + exp);
            return false;
        }
    }

    public boolean fillInNonExistingPatientDetailsForChildReferral() {
        try {
            Wait.forElementToBeDisplayed(driver, nhsNumber);
            testData.setNhsNumber(RandomDataCreator.generateRandomNHSNumber());
            nhsNumber.sendKeys(testData.getNhsNumber());
            testData.setDay(String.valueOf(faker.number().numberBetween(10, 31)));
            testData.setMonth(String.valueOf(faker.number().numberBetween(10, 12)));
            testData.setYear(String.valueOf(faker.number().numberBetween(2005, 2018)));//Child
            dateDay.sendKeys(testData.getDay());
            dateMonth.sendKeys(testData.getMonth());
            dateYear.sendKeys(testData.getYear());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInNonExistingPatientDetailsForChildReferral:" + exp);
            return false;
        }
    }

    public boolean fillInNonExistingPatientDetailsForAdultReferral() {
        try {
            Wait.forElementToBeDisplayed(driver, nhsNumber);
            testData.setNhsNumber(RandomDataCreator.generateRandomNHSNumber());
            //testData.setNhsNumber(Actions.createValidNHSNumber());
            nhsNumber.sendKeys(testData.getNhsNumber());
            testData.setDay(String.valueOf(faker.number().numberBetween(10, 31)));
            testData.setMonth(String.valueOf(faker.number().numberBetween(10, 12)));
            testData.setYear(String.valueOf(faker.number().numberBetween(1900, 1960)));
            dateDay.sendKeys(testData.getDay());
            dateMonth.sendKeys(testData.getMonth());
            dateYear.sendKeys(testData.getYear());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInNonExistingPatientDetailsForAdultReferral:" + exp);
            //SeleniumLib.takeAScreenShot("fillInNonExistingPatientDetailsForAdultReferral.jpg");
            return false;
        }
    }

    public boolean fillInInvalidPatientDetailsInTheNOFields() {
        try {
            Wait.forElementToBeDisplayed(driver, dateDay);
            testData.setDay(String.valueOf(faker.number().numberBetween(10, 31)));
            testData.setMonth(String.valueOf(faker.number().numberBetween(10, 12)));
            testData.setYear(String.valueOf(faker.number().numberBetween(1900, 2019)));
            testData.setLastName(TestUtils.getRandomLastName());
            testData.setFirstName(TestUtils.getRandomFirstName());
            testData.setPostCode(getRandomUKPostCode());
            seleniumLib.sendValue(dateDay, testData.getDay());
            seleniumLib.sendValue(dateMonth, testData.getMonth());
            seleniumLib.sendValue(dateYear, testData.getYear());
            seleniumLib.sendValue(firstName, testData.getFirstName());
            try {
                seleniumLib.sendValue(lastName, testData.getLastName());
            } catch (Exception exp1) {
                seleniumLib.sendValue(familyName, testData.getLastName());
            }
            if (!selectGender(genderButton, "Male")) {
                selectGender(administrativeGenderButton, "Male");
            }
            seleniumLib.sendValue(postcode, testData.getPostCode());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInInvalidPatientDetailsInTheNOFields:" + exp + "\n" + driver.getCurrentUrl());
            //SeleniumLib.takeAScreenShot("fillInInvalidPatientDetailsInTheNOFields.jpg");
            return false;
        }
    }

    public void noFieldsArePrePopulatedInNewPatientPage() {
        String DOB = testData.getDay() + "/" + testData.getMonth() + "/" + testData.getYear();
        //Assert.assertEquals(DOB, Actions.getValue(dateOfBirth));
        Assert.assertEquals(testData.getFirstName(), Actions.getValue(firstName));
        Assert.assertEquals(testData.getLastName(), Actions.getValue(familyName));
        Assert.assertEquals("Male", Actions.getText(administrativeGenderButton));
        Assert.assertEquals(testData.getPostCode(), Actions.getValue(postcode));
    }

    public boolean fillInNHSNumberAndDateOfBirth(NGISPatientModel ngisPatient) {
        try {
            if (!Wait.isElementDisplayed(driver, nhsNumber, 20)) {
                Debugger.println("NHS number field not loaded for Searching the Patient.");
                return false;
            }
            seleniumLib.sendValue(nhsNumber, ngisPatient.getNHS_NUMBER());
            seleniumLib.sendValue(dateDay, ngisPatient.getDAY_OF_BIRTH());
            seleniumLib.sendValue(dateMonth, ngisPatient.getMONTH_OF_BIRTH());
            seleniumLib.sendValue(dateYear, ngisPatient.getYEAR_OF_BIRTH());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from entering patient with NHS and DOB." + exp + "\n" + driver.getCurrentUrl());
            //SeleniumLib.takeAScreenShot("fillInNHSNumberAndDateOfBirth.jpg");
            return false;
        }
    }

    public boolean waitForPageTitleDisplayed() {
        try {
            if (!Wait.isElementDisplayed(driver, pageTitle, 30)) {
                Debugger.println("Patient Search Page is not Loaded Successfully." + "\n" + driver.getCurrentUrl());
                //SeleniumLib.takeAScreenShot("waitForPageTitleDisplayed.jpg");
                Assert.fail("Patient Search Page is not Loaded Successfully.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from loading Patient Search Page:" + exp);
            //SeleniumLib.takeAScreenShot("waitForPageTitleDisplayed.jpg");
            Assert.fail("Exception from loading Patient Search Page:");
            return false;
        }
    }

    public void logoutFromApplication() {
        try {
            if (Wait.isElementDisplayed(driver, logout, 10)) {
                Debugger.println("Already Logged in, Logging out from Application.");
                logout.click();
                Wait.seconds(10);
            }
        } catch (Exception exp) {
            Debugger.println("Exception from log out:" + exp);
            //SeleniumLib.takeAScreenShot("NotAbleToLogout.jpg");
            Assert.assertFalse("Exception from log out:", true);
        }
    }

    public String getPatientSearchNoResult() {
        String noResultText;
        try {
            if (!Wait.isElementDisplayed(driver, noPatientFoundLabel, 120)) {
                By searchResult = By.xpath("//h3[contains(@class, 'no-results')]");
                noResultText = seleniumLib.getText(searchResult);
                return noResultText;
            }
            return "No patient found";
        } catch (Exception exp) {
            Debugger.println("Oops no patient text found " + exp + "\n" + driver.getCurrentUrl());
            return null;
        }
    }

    public boolean confirmAutoCompleteOffOnNHSNumberField() {
        Wait.forElementToBeDisplayed(driver, nhsNumber);
        return Actions.getAutoCompleteAttribute(nhsNumber).equalsIgnoreCase(autoCompleteAttributeOff);

    }

    public void useTheSameTestDataUsedForCreatingReferralInUseCase29Tests(String searchParams) {
        fillInValidSecondPatientDetailsUsingNOFields(searchParams);
    }

    public boolean fillInNewPatientDetailsInTheNoFields() {
        try {
            seleniumLib.sendValue(dateDay, newPatient.getDay());
            seleniumLib.sendValue(dateMonth, newPatient.getMonth());
            seleniumLib.sendValue(dateYear, newPatient.getYear());
            seleniumLib.sendValue(firstName, newPatient.getFirstName());
            try {
                seleniumLib.sendValue(lastName, newPatient.getLastName());
            } catch (Exception exp1) {
                seleniumLib.sendValue(familyName, newPatient.getLastName());
            }
            if (!selectGender(genderButton, "Male")) {
                selectGender(administrativeGenderButton, "Male");
            }
//            seleniumLib.clickOnWebElement(genderButton);
//            seleniumLib.clickOnElement(By.xpath("//span[text()='Male']"));
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in fillInNewPatientDetailsInTheNoFields:" + exp + "\n" + driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("fillInNewPatientDetailsInTheNoFields.jpg");
            return false;
        }
    }

    public boolean fillInNewPatientDetailsInTheNoFieldsWithEditedGender(String editedGender) {
        try {

            seleniumLib.sendValue(dateDay, newPatient.getDay());
            seleniumLib.sendValue(dateMonth, newPatient.getMonth());
            seleniumLib.sendValue(dateYear, newPatient.getYear());
            seleniumLib.sendValue(firstName, newPatient.getFirstName());
            try {
                seleniumLib.sendValue(lastName, newPatient.getLastName());
            } catch (Exception exp1) {
                seleniumLib.sendValue(familyName, newPatient.getLastName());
            }
            if (!selectGender(genderButton, editedGender)) {
                selectGender(administrativeGenderButton, editedGender);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in fillInNewPatientDetailsInTheNoFieldsWithEditedGender:" + exp);
            SeleniumLib.takeAScreenShot("fillInNewPatientDetailsEditedGender.jpg");
            return false;
        }
    }


    public boolean fillInNewPatientDetailsInTheYesFields() {
        try {
            Wait.forElementToBeDisplayed(driver, nhsNumber);
            nhsNumber.sendKeys(newPatient.getNhsNumber());
            dateDay.sendKeys(newPatient.getDay());
            dateMonth.sendKeys(newPatient.getMonth());
            dateYear.sendKeys(newPatient.getYear());
            Debugger.println(" New patient search details " + newPatient.getNhsNumber() + " " + newPatient.getDay() + " " + newPatient.getMonth() + " " + newPatient.getYear());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillInNewPatientDetailsInTheYesFields:" + exp);
            SeleniumLib.takeAScreenShot("fillInNewPatientDetailsInTheYesFields.jpg");
            return false;
        }
    }

    public void fillInNewPatientDetailsWithPostCodeInTheNoFields() {
        if (fillInNewPatientDetailsInTheNoFields()) {
            seleniumLib.sendValue(postcode, newPatient.getPostCode());
        }
    }

    public String searchPatientReferral(NGISPatientModel referralDetails) {
        try {
            if (!Wait.isElementDisplayed(driver, nhsNumber, 60)) {
                return "Patient Search Page not displayed even after a minute wait..";
            }
            nhsNumber.sendKeys(referralDetails.getNHS_NUMBER());
            if (referralDetails.getDATE_OF_BIRTH() == null || referralDetails.getDATE_OF_BIRTH().isEmpty()) {
                referralDetails.setDATE_OF_BIRTH(faker.number().numberBetween(10, 31) + "-" + faker.number().numberBetween(10, 12) + "-" + faker.number().numberBetween(1900, 2019));
            }
            dateDay.sendKeys(referralDetails.getDAY_OF_BIRTH());
            dateMonth.sendKeys(referralDetails.getMONTH_OF_BIRTH());
            dateYear.sendKeys(referralDetails.getYEAR_OF_BIRTH());
            //Search for the referral
            if (!clickSearchButtonByXpath()) {
                return "Could not click on Search Button in Patient Search Page.";
            }
            if (!Wait.isElementDisplayed(driver, patientSearchResult, 120)) {
                return "Patient Search Result Page not displayed even after 2 minute wait..";
            }
            return patientSearchResult.getText();
        } catch (Exception exp) {
            Debugger.println("Exception from Search Patient Referral: " + exp);
            return "Exception from Search Patient Referral: " + exp;
        }

    }

    public boolean verifyNHSAndDOBInPatientCard(String familyDetails) {
        try {
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyDetails);
            Set<String> paramsKey = paramNameValue.keySet();
            String nhsNumber = "", dob = "";
            for (String key : paramsKey) {
                if (key.equalsIgnoreCase("NHSNumber")) {
                    nhsNumber = paramNameValue.get(key);
                } else if (key.equalsIgnoreCase("DOB")) {
                    dob = paramNameValue.get(key);
                }
            }
            //Read the search result details
            NGISPatientModel familyMember = FamilyMemberDetailsPage.getFamilyMember(familyDetails);
            if (familyMember == null) {
                Debugger.println("Family Member :" + familyDetails + " Not found in the list!.");
                return false;
            }
            String bornExpected = TestUtils.getDOBInMonthFormat(dob) + " " + TestUtils.getAgeInYearsAndMonth(dob);
            //Debugger.println("NHS Actual: "+familyMember.getNHS_NUMBER()+", Expected:"+nhsNumber);
            //Debugger.println("BORN Actual: "+familyMember.getBORN_WITH_AGE()+", Expected:"+bornExpected);
            if (familyMember.getNHS_NUMBER().equalsIgnoreCase(nhsNumber)) {
                if (familyMember.getBORN_WITH_AGE().contains(bornExpected)) {
                    return true;
                } else {
                    //Checking with the first part of the DOB as Month/Days/Hours shown based on some internal logic
                    if (familyMember.getBORN_WITH_AGE().contains(bornExpected.substring(0, 11))) {
                        return true;
                    }
                }
            }
            Debugger.println("Search Result - Patient Card does not contains the NHS and DOB as expected for :" + familyDetails);
            return false;
        } catch (Exception exp) {
            return false;
        }
    }

    public boolean fillInPatientSearchWithNoFields(NGISPatientModel searchPatient) {
        try {
            clickNoButton();
            Wait.forElementToBeDisplayed(driver, dateDay);
            dateDay.sendKeys(searchPatient.getDAY_OF_BIRTH());
            dateMonth.sendKeys(searchPatient.getMONTH_OF_BIRTH());
            dateYear.sendKeys(searchPatient.getYEAR_OF_BIRTH());
            if (searchPatient.getFIRST_NAME() == null) {
                searchPatient.setFIRST_NAME(faker.name().firstName());
            }
            if (searchPatient.getLAST_NAME() == null) {
                searchPatient.setLAST_NAME(faker.name().lastName());
            }
            firstName.sendKeys(searchPatient.getFIRST_NAME());
            familyName.sendKeys(searchPatient.getLAST_NAME());
            if (!selectGender(genderButton, searchPatient.getGENDER())) {
                selectGender(administrativeGenderButton, searchPatient.getGENDER());
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in searching patient with No Option." + exp);
            //SeleniumLib.takeAScreenShot("PatientSearchNo.jpg");
            return false;
        }
    }

    public boolean selectGender(WebElement element, String optionValue) {
        try {
            try {
                Actions.clickElement(driver, element);
            } catch (Exception exp1) {
                seleniumLib.clickOnWebElement(element);
            }
            Wait.seconds(3);
            List<WebElement> ddElements = driver.findElements(By.xpath("//label[@for='gender']/..//div//span[text()='" + optionValue + "']"));
            //Debugger.println("Size of Gender DD elements: "+ddElements.size());
            if (ddElements.size() == 0) {
                ddElements = driver.findElements(By.xpath("//label[@for='administrativeGender']/..//div//span[text()='" + optionValue + "']"));
            }
            if (ddElements.size() > 0) {
                try {
                    Wait.forElementToBeClickable(driver, ddElements.get(0));
                    Actions.clickElement(driver, ddElements.get(0));
                    Wait.seconds(2);
                } catch (Exception exp1) {
                    seleniumLib.clickOnWebElement(ddElements.get(0));
                }
            } else {
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in selecting Gender for Family Member: " + exp);
            //SeleniumLib.takeAScreenShot("FMGenderDropDown.jpg");
            return false;
        }
    }

    public List<String> getTheGenderDropDownValues() {
        try {
            Actions.clickElement(driver, genderButton);
        } catch (Exception exp) {
            Actions.clickElement(driver, administrativeGenderButton);
        }
        //selectGender(administrativeGenderButton,"Male");
        List<String> actualGenderValues = new ArrayList<String>();
        for (WebElement genderValue : genderValues) {
            actualGenderValues.add(genderValue.getText().trim());
        }
        //Debugger.println("Actual gender values: " + actualGenderValues);
        return actualGenderValues;
    }


    public boolean ensureTickMarkIsDisplayedNextToYesButton() {
        Wait.forElementToBeDisplayed(driver, yesButtonSVG);
        if (Wait.isElementDisplayed(driver, yesButtonSVG, 10)) {
            return true;
        }
        return false;
    }

    public boolean ensureTickMarkIsDisplayedNextToNoButton() {
        Wait.forElementToBeDisplayed(driver, noButtonSVG);
        if (Wait.isElementDisplayed(driver, noButtonSVG, 10)) {
            return true;
        }
        return false;
    }

    public boolean verifyTheNHSQuestionOfThePage(String nhsQuestion) {
        try {
            Wait.forElementToBeDisplayed(driver, yesNoFieldLabel, 10);
            String actualQuestion = yesNoFieldLabel.getText();
            if (!actualQuestion.equalsIgnoreCase(nhsQuestion)) {
                Debugger.println("Actual Question is " + actualQuestion + ", Expected Question is " + nhsQuestion);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from  verifyTheNHSQuestionOfThePage..." + exp);
            return false;
        }
    }

    public boolean isNotificationErrorPresent() {
        if (Wait.isElementDisplayed(driver, notificationError, 10)) {
            return true;
        }
        return false;

    }

    public boolean editPatientDetails() {
        try {
            if (!Wait.isElementDisplayed(driver, editPatientDetails, 10)) {
                Debugger.println("Edit Patient Details Link not exists.");
                return false;
            }
            Actions.clickElement(driver, editPatientDetails);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from editPatientDetails:" + exp);
            return false;
        }
    }

    public boolean fillInPatientSearchWithNoFieldsForSavedDetails() {
        try {
            NewPatient savedData = PatientDetailsPage.newPatient;
            Wait.forElementToBeDisplayed(driver, dateDay);
            dateDay.sendKeys(savedData.getDay());
            dateMonth.sendKeys(savedData.getMonth());
            dateYear.sendKeys(savedData.getYear());
            firstName.sendKeys(savedData.getFirstName());
            lastName.sendKeys(savedData.getLastName());
            if (!selectGender(genderButton, savedData.getGender())) {
                selectGender(administrativeGenderButton, savedData.getGender());
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in searching patient with No Option." + exp);
            return false;
        }
    }

    public boolean checkMergeStatusIsDisplayed(String badgeText) {
        try {
            if (!Wait.isElementDisplayed(driver, patientCard, 30)) {
                Debugger.println("Expected Patient card not displayed." + driver.getCurrentUrl());
                return false;
            } else {
                SeleniumLib.takeAScreenShot("PatientCardDisplayed");
            }
            Actions.scrollToBottom(driver);

            if (!Wait.isElementDisplayed(driver, mergeStatusOnPatientCard, 10)) {
                Debugger.println("Expected patientCardBadge not displayed." + driver.getCurrentUrl());
                return false;
            }
            String actualMergeStatus = mergeStatusOnPatientCard.getText();
            Debugger.println("The actual merge status is " + actualMergeStatus);

            if (!actualMergeStatus.equalsIgnoreCase(badgeText)) {
                Debugger.println("Actual message is:" + actualMergeStatus + " ,BUt expected:" + badgeText);
                SeleniumLib.takeAScreenShot("MergeStatusWrong.jpg");
                return false;
            }
            Debugger.println("Actual message is:" + actualMergeStatus + " ,And expected:" + badgeText);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from validateMergeStatus:" + exp);
            SeleniumLib.takeAScreenShot("MergeStatusWrong.jpg");
            return false;
        }
    }

    public boolean verifyTooltipOverTheMergeStatusBadge(String tooltipMsg) {
        try {
            if (!Wait.isElementDisplayed(driver, tooltipOnPatientCard, 3)) {
                Debugger.println("Expected tool tip onPatient card not displayed." + driver.getCurrentUrl());
                return false;
            }
            Debugger.println("Th expected tooltip " + tooltipMsg);
            String actualToolTip = tooltipOnPatientCard.getText();
            actualToolTip = actualToolTip.replaceAll("\\s+", " ");
            Debugger.println("The tool tip message is " + actualToolTip);
            if (!actualToolTip.contains(tooltipMsg)) {
                Debugger.println("Expected tooltip:" + tooltipMsg + " But the actual tooltip is:" + actualToolTip);
                return false;
            }
            return true;

        } catch (Exception exp) {
            Debugger.println("Exception from tooltip:" + exp);
            SeleniumLib.takeAScreenShot("MergeStatusToolTip.jpg");
            return false;
        }
    }

    public boolean checkMergeStatusIsNotDisplayed() {
        try {
            if (!Wait.isElementDisplayed(driver, patientCard, 30)) {
                Debugger.println("Expected Patient card not displayed." + driver.getCurrentUrl());
                return false;
            }
            Actions.scrollToBottom(driver);

            if (Wait.isElementDisplayed(driver, mergeStatusOnPatientCard, 10)) {
                Debugger.println("PatientCardBadge is displayed." + driver.getCurrentUrl());
                return false;
            }
            Debugger.println("The patientCardBadge is not displayed as expected" + driver.getCurrentUrl());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from validateMergeStatus:" + exp);
            SeleniumLib.takeAScreenShot("MergeStatusWrong.jpg");
            return false;
        }
    }

    //From JSON for JSON Framework
    public boolean searchParticipantFromJson(String nhs,String day,String month,String year) {
        try {
            Wait.forElementToBeDisplayed(driver, nhsNumber);
            testData.setNhsNumber(nhs);
            testData.setDay(day);
            testData.setMonth(month);
            testData.setYear(year);
            //Enter the values to teh search fields
            nhsNumber.sendKeys(testData.getNhsNumber());
            dateDay.sendKeys(testData.getDay());
            dateMonth.sendKeys(testData.getMonth());
            dateYear.sendKeys(testData.getYear());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from searchParticipantFromJson:" + exp);
            return false;
        }
    }

}//end

