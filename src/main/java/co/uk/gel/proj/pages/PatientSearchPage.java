package co.uk.gel.proj.pages;

import co.uk.gel.csvmodels.SpineDataModelFromCSV;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.*;
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


//public class PatientSearchPage {
public class PatientSearchPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    public static NewPatient testData = new NewPatient();
    static Faker faker = new Faker();

    /*public PatientSearchPage(SeleniumDriver driver) {
        super(driver);
    }*/

    public PatientSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public WebElement title;
    public WebElement dateDay;
    public WebElement dateMonth;
    public WebElement dateYear;
    public WebElement dateOfBirth;
    public WebElement firstName;
    public WebElement lastName;
    public WebElement familyName;
    public WebElement postcode;

    @FindBy(css = "h1[class*='page-title']")
    public WebElement pageTitle;

    @FindBy(css = "p[class*='patient-search__intro']")
    public WebElement pageDescription;

    @FindBy(css = "h3[class*='field-label']")
    public WebElement yesNoFieldLabel;

    @FindBy(xpath = "//button[text()='Yes']")
    public WebElement yesButton;

    @FindBy(css = "legend[class*='field-label']")
    public WebElement dateLabel;

    @FindBy(name = "loginfmt")
    public WebElement emailAddressField;

    @FindBy(name = "passwd")
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

    @FindBy(css = "button[class*='search']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[contains(string(),'Search')]")
    public WebElement searchButtonByXpath;


    @FindBy(css = "a[class*='patient-card']")
    public WebElement patientCard;

    @FindBy(css = "h3[class*='results__header']")
    public WebElement patientSearchResultsHeader;

    @FindBy(css = "span[class*='badge']")
    public WebElement patientCardBadge;

    @FindBy(css = "p[class*='patient-name']")
    public WebElement patientFullName;

    @FindBy(css = "p[class*='card-line']")  // To get all details of patient at once
    public List<WebElement> patientDetails;

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
    @FindBy(css = "*[class*='no-results__help']")
    public WebElement noResultsHelp;

    @FindBy(xpath = "//*[contains(@class,'no-results__help-link')]//child::a")
    public WebElement noResultsHelpLink1;

    @FindBy(xpath = "//a[text()='create a new patient record']")
    public WebElement noResultsHelpLink2;

    @FindBy(css = "a[class*='inline-link']")
    public WebElement noResultsHelpLink; // create a new patient link

    @FindBy(css = "p[class*='no-results__duplicate']")
    public WebElement noResultsDuplicate;

    @FindBy(css = "*[class*='helix']")
    public List<WebElement> helix;

    String noResultsLocator = "img[class*='no-results__img']";
    String errorMessageLocator = "div[class*='error-message']";

    public void pageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "/patient-search");
        Wait.forElementToBeDisplayed(driver, yesButton);
    }

    public String getYesBtnSelectedAttribute() {
        String value = yesButton.getAttribute("aria-pressed");
        Debugger.println("colour is: " + value);
        return value;
    }

    public String getYesButtonColour() {
        String backGroundColour = yesButton.getCssValue("background-color");
        Debugger.println("colour is: " + backGroundColour);
        return backGroundColour;
    }

    public void fillInValidPatientDetailsUsingNHSNumberAndDOB(String nhsNo, String dayOfBirth, String monthOfBirth, String yearOfBirth) {
        Wait.forElementToBeDisplayed(driver, nhsNumber);

        nhsNumber.sendKeys(nhsNo);
        dateDay.sendKeys(dayOfBirth);
        dateMonth.sendKeys(monthOfBirth);
        dateYear.sendKeys(yearOfBirth);
    }

    public void clickNoButton() {
        Wait.forElementToBeDisplayed(driver, noButton);
        noButton.click();
    }

    public void clickSearchButton(WebDriver driver) {
        Wait.forElementToBeClickable(driver, searchButton);
        Click.element(driver, searchButton);
    }

    public void clickSearchButtonByXpath(WebDriver driver) {
        Wait.forElementToBeClickable(driver, searchButtonByXpath);
        searchButtonByXpath.click();
    }


    public String checkThatPatientCardIsDisplayed(WebDriver driver) {
        Wait.forElementToBeDisplayed(driver, patientCard);
        Wait.forElementToBeDisplayed(driver, patientSearchResultsHeader);
        Debugger.println("The search result is from :" + patientCardBadge.getText());
        //Assert.assertEquals(badgeText, patientCardBadge.getText().trim());
        return patientCardBadge.getText().trim();
    }

    public void loginToTestOrderingSystemAsServiceDeskUser(WebDriver driver) {
        Wait.forElementToBeClickable(driver, emailAddressField);
        emailAddressField.sendKeys(AppConfig.getApp_username());
        nextButton.click();
        //Wait.seconds(2);
        Wait.forElementToBeClickable(driver, passwordField);
        passwordField.sendKeys(AppConfig.getApp_password());
        nextButton.click();
    }


    public void loginToTestOrderingSystem(WebDriver driver, String userType) {

        Wait.forElementToBeClickable(driver, emailAddressField);
        if (userType.equalsIgnoreCase("GEL-normal-user")) {
            emailAddressField.sendKeys(AppConfig.getApp_username());
        } else {
            emailAddressField.sendKeys(AppConfig.getPropertyValueFromPropertyFile("SUPER_USERNAME"));
        }
        Click.element(driver, nextButton);
        Wait.forElementToBeClickable(driver, passwordField);
        if (userType.equalsIgnoreCase("GEL-normal-user")) {
            passwordField.sendKeys(AppConfig.getApp_password());
        } else {
            passwordField.sendKeys(AppConfig.getPropertyValueFromPropertyFile("SUPER_PASSWORD"));
        }
        Click.element(driver, nextButton);
    }

    public void fillInValidPatientDetailsUsingNOFields(String searchParams) {

        //DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female
        // Extract the patient details from the example-table

        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "DOB": {
                    String dobValue = paramNameValue.get(key);
                    String[] dobSplit = dobValue.split("-");
                    dateDay.sendKeys(dobSplit[0]);
                    dateMonth.sendKeys(dobSplit[1]);
                    dateYear.sendKeys(dobSplit[2]);
                    break;
                }
                case "FirstName": {
                    firstName.sendKeys(paramNameValue.get(key));
                    break;
                }
                case "LastName": {
                    lastName.sendKeys(paramNameValue.get(key));
                    break;
                }
                case "Gender": {
                    genderButton.click();
                    genderValue.findElement(By.xpath("//span[text()='" + paramNameValue.get(key) + "']")).click();
                    break;
                }
                case "Postcode": {
                    postcode.sendKeys(paramNameValue.get(key));
                    break;
                }
            }
        }


    }

    public void checkSearchResultHeaderIsDisplayed(WebDriver driver, String resultHeader) {

        Wait.forElementToBeDisplayed(driver, patientCard);
        Wait.forElementToBeDisplayed(driver, patientSearchResultsHeader);
        Debugger.println("The actual search result header is :" + patientSearchResultsHeader.getText());
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

    public void clickPatientCard() {
        Wait.forElementToBeDisplayed(driver, patientCard);
        patientCard.click();
    }

    public void fillInDifferentValidPatientDetailsUsingNHSNumberAndDOB(String nhsNo, String dayOfBirth, String monthOfBirth, String yearOfBirth) {

        Wait.forElementToBeDisplayed(driver, nhsNumber);
        Actions.clearField(nhsNumber);  //nhsNumber.clear();
        nhsNumber.sendKeys(nhsNo);
        Actions.clearField(dateDay);
        dateDay.sendKeys(dayOfBirth);
        Actions.clearField(dateMonth);
        dateMonth.sendKeys(monthOfBirth);
        Actions.clearField(dateYear);
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
        Debugger.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + expectedDateOfBirth);
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

        Debugger.println("Expected full name = " + expectedFullName + ", Actual full name " + actualFullName);
        Assert.assertEquals(expectedFullName, actualFullName);

        Debugger.println("Expected DOB = " + expectedDateOfBirth + ", Actual DOB: " + actualFullDOB);
        //Assert.assertTrue(actualFullDOB.contains("Born " + expectedDayOfBirth));
        Assert.assertTrue(actualFullDOB.contains("Born " + expectedDateOfBirth));

        Debugger.println("Expected Gender= " + expectedGender + ", Actual Gender: " + actualGender);
        Assert.assertEquals("Gender " + expectedGender, actualGender);

        Debugger.println("Expected nhs no = " + expectedNHSNumber + ", Actual nhs no: " + actualNHSNumber);
        Assert.assertEquals("NHS No. " + expectedNHSNumber, actualNHSNumber);

        Debugger.println("Expected address = " + expectedFullAddress + ", Actual address: " + actualAddress);
        Assert.assertEquals(expectedFullAddress, actualAddress);
    }


    public void checkNHSNumberAndDOBareDisplayed(String expectedNHSNumber, String expectedDOB, String expErrorText) {
        Wait.forElementToBeDisplayed(driver, nhsNumberHiddenLabel);
        Debugger.println("Actual NHS Number and DOB displayed on the page " + nhsNumberHiddenLabel.getText());
        Assert.assertTrue(nhsNumberHiddenLabel.getText().contains(expectedNHSNumber));
        String expectedDOBInYYYYDDMM = TestUtils.dateFormatReverserToYYYYMMDD(expectedDOB.trim());
        Assert.assertTrue(nhsNumberHiddenLabel.getText().contains(expectedDOBInYYYYDDMM));
        Wait.forElementToBeDisplayed(driver, noPatientFoundLabel);
        Assert.assertEquals(expErrorText, noPatientFoundLabel.getText());
    }

    public void checkCreateNewPatientLinkDisplayed(String hyperLinkText) {
        Wait.forElementToBeDisplayed(driver, createNewPatientLink);
        Assert.assertEquals(hyperLinkText, createNewPatientLink.getText());
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
        Debugger.println("EXPECTED RESULT: " + expectedFontColour);
        Debugger.println("ACTUAL RESULT  : " + nhsNumberLabel.getCssValue("color"));
        Assert.assertEquals(expectedFontColour, nhsNumberLabel.getCssValue("color"));
        Assert.assertEquals(expectedFontColour, dateOfBirthLabel.getCssValue("color"));
    }

    public void checkTheErrorMessage(String errorMessage, String fontColor) {
        Wait.forElementToBeDisplayed(driver, nHSNumberFieldValidationErrorMessageLabel);
        Debugger.println("EXPECTED RESULT: " + errorMessage);
        Debugger.println("ACTUAL RESULT  : " + nHSNumberFieldValidationErrorMessageLabel.getText());
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
        Debugger.println("EXPECTED RESULT: " + errorMessage);
        Debugger.println("ACTUAL RESULT  : " + dobFieldValidationErrorMessageLabel.getText());
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
                    Actions.clearField(dateDay);
                    dateDay.sendKeys(dobSplit[0]);
                    Actions.clearField(dateMonth);
                    dateMonth.sendKeys(dobSplit[1]);
                    Actions.clearField(dateYear);
                    dateYear.sendKeys(dobSplit[2]);
                    break;
                }
                case "FirstName": {
                    Actions.clearField(firstName);
                    firstName.sendKeys(paramNameValue.get(key));
                    break;
                }
                case "LastName": {
                    Actions.clearField(lastName);
                    lastName.sendKeys(paramNameValue.get(key));
                    break;
                }
                case "Gender": {
                    genderButton.click();
                    genderValue.findElement(By.xpath("//span[text()='" + paramNameValue.get(key) + "']")).click();
                    break;
                }
                case "Postcode": {
                    Actions.clearField(postcode);
                    postcode.sendKeys(paramNameValue.get(key));
                    break;
                }
            }
        }

    }

    public void verifyTheTitleOfThePage(String titleOfPage) {
        Wait.forElementToBeDisplayed(driver, searchButton);
        Debugger.println("The actual page title  is :" + pageTitle.getText());
        Assert.assertEquals(titleOfPage, pageTitle.getText().trim());
    }

    public void verifyTheDescriptionOfThePage(String DescriptionOfPage) {
        String actualPageDescription = pageDescription.getText();
        Debugger.println("The actual Description title  is :" + pageDescription.getText());
        Assert.assertTrue(actualPageDescription.contains(DescriptionOfPage));
    }

    public void clickOnFieldsAndVerifyAutoCompleteIsDisabled(String[] textFieldElements) {

        for (String fieldElement : textFieldElements) {
            switch (fieldElement) {
                case "nhsNumber": {
                    verifyFieldHasAutoCompleteDisabled(nhsNumber);
                    break;
                }

                case "dateDay": {
                    verifyFieldHasAutoCompleteDisabled(dateDay);
                    break;
                }

                case "dateMonth": {
                    verifyFieldHasAutoCompleteDisabled(dateMonth);
                    break;
                }

                case "dateYear": {
                    verifyFieldHasAutoCompleteDisabled(dateYear);
                    break;
                }

                case "firstName": {
                    verifyFieldHasAutoCompleteDisabled(firstName);
                    break;
                }

                case "lastName": {
                    verifyFieldHasAutoCompleteDisabled(lastName);
                    break;
                }

                case "postcode": {
                    verifyFieldHasAutoCompleteDisabled(postcode);
                    break;
                }
                default:

                    throw new IllegalArgumentException("Invalid text field name");
            }
        }
    }

    public void verifyFieldHasAutoCompleteDisabled(WebElement element) {

        Wait.forElementToBeDisplayed(driver, element);
        element.click();
        Wait.seconds(1);
        String autoCompleteValue = element.getAttribute("list");
        Debugger.println("The values for auto complete is: " + autoCompleteValue);
        Assert.assertEquals("autocompleteOff", autoCompleteValue);
        Debugger.println("Test passed for the element field" + element);
    }

    public boolean verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected() {

        // Find elements

        Wait.forElementToBeDisplayed(driver, searchButton);
        pageTitle.isDisplayed();
        pageDescription.isDisplayed();
        yesNoFieldLabel.isDisplayed();
        yesButton.isDisplayed();
        noButton.isDisplayed();
        nhsNumberLabel.isDisplayed();
        nhsNumber.isDisplayed();
        dateOfBirthLabel.isDisplayed();
        dateDay.isDisplayed();
        dateMonth.isDisplayed();
        dateYear.isDisplayed();
        searchButton.isDisplayed();

        return true;
    }


    public boolean verifyTheElementsOnPatientSearchAreDisplayedWhenNoIsSelected() {

        Wait.forElementToBeDisplayed(driver, searchButton);
        pageTitle.isDisplayed();
        pageDescription.isDisplayed();
        yesNoFieldLabel.isDisplayed();
        yesButton.isDisplayed();
        noButton.isDisplayed();
        dateOfBirthLabel.isDisplayed();
        dateDay.isDisplayed();
        dateMonth.isDisplayed();
        dateYear.isDisplayed();
        firstName.isDisplayed();
        lastName.isDisplayed();
        postcode.isDisplayed();
        searchButton.isDisplayed();

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

    public void clickCreateNewPatientLinkFromNoSearchResultsPage() {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector(noResultsLocator), 0);
        //Wait.forElementToBeDisplayed(driver, noResultsHelpLink);
        Wait.forElementToBeClickable(driver, noResultsHelpLink);
        Click.element(driver, noResultsHelpLink);
    }

    public void fillInNHSNumberAndDateOfBirth(String patientType) throws IOException {
        if (patientType.equals("NGIS")) {
            fillInValidPatientDetailsUsingNHSNumberAndDOB(NgisPatientOne.NHS_NUMBER, NgisPatientOne.DAY_OF_BIRTH, NgisPatientOne.MONTH_OF_BIRTH, NgisPatientOne.YEAR_OF_BIRTH);
        } else if (patientType.equals("NHS Spine")) {
            // used to validate the spine details in patient result card
            fillInValidPatientDetailsUsingNHSNumberAndDOB(SpinePatientOne.NHS_NUMBER, SpinePatientOne.DAY_OF_BIRTH, SpinePatientOne.MONTH_OF_BIRTH, SpinePatientOne.YEAR_OF_BIRTH);
        } else if (patientType.equals("SPINE")) {
            SpineDataModelFromCSV randomNHSDataFromSpineCSV = RandomDataCreator.getAnyNHSDataFromSpineCSV();
            ArrayList<String> dobString = TestUtils.convertDOBNumbersToStrings(randomNHSDataFromSpineCSV.getDATE_OF_BIRTH());
            String dayOfBirth = dobString.get(0);
            String monthOfBirth = dobString.get(1);
            String yearOfBirth = dobString.get(2);
            fillInValidPatientDetailsUsingNHSNumberAndDOB(randomNHSDataFromSpineCSV.getNHS_NUMBER(), dayOfBirth, monthOfBirth, yearOfBirth);
        } else {
            throw new RuntimeException(" Patient type not found -> provide either NGIS or SPINE patient");
        }
    }

    public void fillInNHSNumberAndDateOfBirthByProvidingNGISPatientOne() {
        fillInValidPatientDetailsUsingNHSNumberAndDOB(NgisPatientOne.NHS_NUMBER, NgisPatientOne.DAY_OF_BIRTH, NgisPatientOne.MONTH_OF_BIRTH, NgisPatientOne.YEAR_OF_BIRTH);
    }

    public void fillInNHSNumberAndDateOfBirthByProvidingNGISPatientTwo() {
        fillInValidPatientDetailsUsingNHSNumberAndDOB(NgisPatientTwo.NHS_NUMBER, NgisPatientTwo.DAY_OF_BIRTH, NgisPatientTwo.MONTH_OF_BIRTH, NgisPatientTwo.YEAR_OF_BIRTH);
    }

    public void fillInNHSNumberAndDateOfBirthByProvidingRandomSpinePatientRecord() throws IOException {
        SpineDataModelFromCSV randomNHSDataFromSpineCSV = RandomDataCreator.getAnyNHSDataFromSpineCSV();
        ArrayList<String> dobString = TestUtils.convertDOBNumbersToStrings(randomNHSDataFromSpineCSV.getDATE_OF_BIRTH());
        String dayOfBirth = dobString.get(0);
        String monthOfBirth = dobString.get(1);
        String yearOfBirth = dobString.get(2);
        fillInValidPatientDetailsUsingNHSNumberAndDOB(randomNHSDataFromSpineCSV.getNHS_NUMBER(), dayOfBirth, monthOfBirth, yearOfBirth);
    }

    public void fillInNonExistingPatientDetailsUsingNHSNumberAndDOB() {
        Wait.forElementToBeDisplayed(driver, nhsNumber);
        testData.setNhsNumber(RandomDataCreator.generateRandomNHSNumber());
        //testData.setNhsNumber(Actions.createValidNHSNumber());
        nhsNumber.sendKeys(testData.getNhsNumber());
        testData.setDay(String.valueOf(faker.number().numberBetween(10, 31)));
        testData.setMonth(String.valueOf(faker.number().numberBetween(10, 12)));
        testData.setYear(String.valueOf(faker.number().numberBetween(1900, 2019)));
        dateDay.sendKeys(testData.getDay());
        dateMonth.sendKeys(testData.getMonth());
        dateYear.sendKeys(testData.getYear());
//        String DOB1 = testData.getDay()  + "/" + testData.getMonth() + "/" + testData.getYear();
//        Debugger.println("Expected DOB tobe :" + DOB1);
    }

    public void nhsNumberAndDOBFieldsArePrePopulatedInNewPatientPage() {
        String DOB = testData.getDay() + "/" + testData.getMonth() + "/" + testData.getYear();
        Debugger.println("Expected DOB:" + DOB + " Actual DOB :" + Actions.getValue(dateOfBirth));
        Assert.assertEquals(DOB, Actions.getValue(dateOfBirth));
    }


    public void fillInInvalidPatientDetailsInTheNOFields() {
        Wait.forElementToBeDisplayed(driver, dateDay);
        testData.setDay(String.valueOf(faker.number().numberBetween(10, 31)));
        testData.setMonth(String.valueOf(faker.number().numberBetween(10, 12)));
        testData.setYear(String.valueOf(faker.number().numberBetween(1900, 2019)));
        dateDay.sendKeys(testData.getDay());
        dateMonth.sendKeys(testData.getMonth());
        dateYear.sendKeys(testData.getYear());
        testData.setFirstName(faker.name().firstName());
        firstName.sendKeys(testData.getFirstName());
        testData.setLastName(faker.name().lastName());
        lastName.sendKeys(testData.getLastName());
        Click.element(driver, genderButton);
        Click.element(driver, genderValue.findElement(By.xpath("//span[text()='Male']")));
        testData.setPostCode(faker.address().zipCode());
        postcode.sendKeys(testData.getPostCode());
    }

    public void noFieldsArePrePopulatedInNewPatientPage() {
        String DOB = testData.getDay() + "/" + testData.getMonth() + "/" + testData.getYear();
        Assert.assertEquals(DOB, Actions.getValue(dateOfBirth));
        Assert.assertEquals(testData.getFirstName(), Actions.getValue(firstName));
        Assert.assertEquals(testData.getLastName(), Actions.getValue(familyName));
        Assert.assertEquals("Male", Actions.getText(administrativeGenderButton));
        Assert.assertEquals(testData.getPostCode(), Actions.getValue(postcode));
    }
}
