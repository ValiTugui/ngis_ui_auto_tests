package co.uk.gel.proj.pages;

import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import co.uk.gel.lib.Actions;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import co.uk.gel.proj.TestDataProvider.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


//public class PatientSearchPage {
public class PatientSearchPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;


    /*public PatientSearchPage(SeleniumDriver driver) {
        super(driver);
    }*/

    public PatientSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public WebElement dateDay;
    public WebElement dateMonth;
    public WebElement dateYear;
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

    @FindBy(css = "*[class*='no-results__help']")
    public WebElement noResultsHelp;

    @FindBy(xpath = "//*[contains(@class,'no-results__help-link')]//child::a")
    public WebElement noResultsHelpLink;

    @FindBy(css = "a[class*='inline-link']")
    public WebElement noResultsHelpLink2; // create a new patient link

    @FindBy(css = "p[class*='no-results__duplicate']")
    public WebElement noResultsDuplicate;

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
        searchButton.click();
    }

    public void clickSearchButtonByXpath(WebDriver driver) {
        Wait.forElementToBeClickable(driver, searchButtonByXpath);
        searchButtonByXpath.click();
    }


    public void checkThatPatientCardIsDisplayed(WebDriver driver, String badgeText) {
        Wait.forElementToBeDisplayed(driver, patientCard);
        Wait.forElementToBeDisplayed(driver, patientSearchResultsHeader);
        Debugger.println("The search result is from :" + patientCardBadge.getText());
        Assert.assertEquals(badgeText, patientCardBadge.getText().trim());
    }


    public void loginToTestOrderingSystemAsServiceDeskUser(WebDriver driver) {
        Wait.forElementToBeClickable(driver, emailAddressField);
        emailAddressField.sendKeys(AppConfig.getApp_username());
        nextButton.click();
        Wait.seconds(2);
        Wait.forElementToBeClickable(driver, passwordField);
        passwordField.sendKeys(AppConfig.getApp_password());
        nextButton.click();
    }


    public void patientDetailsAreDisplayedInTheCard(String patientSearchType) {


        switch (patientSearchType) {

            case "NHS Spine": {


                String actualFullName = patientFullName.getText().trim();
                //String expectedDateOfBirth =expectedDayOfBirth+"-" + TestUtils.convertMonthNumberToMonthForm(expectedMonthOfBirth)+ "-"+expectedYearOfBirth;
                Debugger.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + SpinePatientOne.DATE_OF_BIRTH);
                String actualFullDOB = patientDateOfBirth.getText().trim();
                String actualGender = patientGender.getText().trim();
                String actualNHSNumber = patientNSNo.getText().trim();
                String actualAddress = patientAddress.getText().trim();

                Debugger.println("Expected full name = " + SpinePatientOne.FULL_NAME + ", Actual full name " + actualFullName);
                Assert.assertEquals(SpinePatientOne.FULL_NAME, actualFullName);

                Debugger.println("Expected DOB = " + SpinePatientOne.DATE_OF_BIRTH + ", Actual DOB: " + actualFullDOB);
                Assert.assertTrue(actualFullDOB.contains("Born " + SpinePatientOne.DATE_OF_BIRTH));

                Debugger.println("Expected Gender= " + SpinePatientOne.GENDER + ", Actual Gender: " + actualGender);
                Assert.assertEquals("Gender " + SpinePatientOne.GENDER, actualGender);

                Debugger.println("Expected nhs no = " + SpinePatientOne.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);
                Assert.assertEquals("NHS No. " + SpinePatientOne.NHS_NUMBER, actualNHSNumber);

                Debugger.println("Expected address = " + SpinePatientOne.FULL_ADDRESS + ", Actual address " + actualAddress);
                Assert.assertEquals(SpinePatientOne.FULL_ADDRESS, actualAddress);

                break;
            }
            case "NHS Spine2": {
                String actualFullName = patientFullName.getText().trim();
                //String expectedDateOfBirth =expectedDayOfBirth+"-" + TestUtils.convertMonthNumberToMonthForm(expectedMonthOfBirth)+ "-"+expectedYearOfBirth;
                Debugger.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + SpinePatientTwo.DATE_OF_BIRTH);
                String actualFullDOB = patientDateOfBirth.getText().trim();
                String actualGender = patientGender.getText().trim();
                String actualNHSNumber = patientNSNo.getText().trim();
                String actualAddress = patientAddress.getText().trim();

                Debugger.println("Expected full name = " + SpinePatientTwo.FULL_NAME + ", Actual full name " + actualFullName);
                Assert.assertEquals(SpinePatientTwo.FULL_NAME, actualFullName);

                Debugger.println("Expected DOB = " + SpinePatientTwo.DATE_OF_BIRTH + ", Actual DOB: " + actualFullDOB);
                Assert.assertTrue(actualFullDOB.contains("Born " + SpinePatientTwo.DATE_OF_BIRTH));

                Debugger.println("Expected Gender= " + SpinePatientTwo.GENDER + ", Actual Gender: " + actualGender);
                Assert.assertEquals("Gender " + SpinePatientTwo.GENDER, actualGender);

                Debugger.println("Expected nhs no = " + SpinePatientTwo.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);
                Assert.assertEquals("NHS No. " + SpinePatientTwo.NHS_NUMBER, actualNHSNumber);

                Debugger.println("Expected address = " + SpinePatientTwo.FULL_ADDRESS + ", Actual address " + actualAddress);
                Assert.assertEquals(SpinePatientTwo.FULL_ADDRESS, actualAddress);


                break;
            }
            case "NGIS": {
                String actualFullName = patientFullName.getText().trim();
                Debugger.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + NgisPatientOne.DATE_OF_BIRTH);
                String actualFullDOB = patientDateOfBirth.getText().trim();
                String actualGender = patientGender.getText().trim();
                String actualNHSNumber = patientNSNo.getText().trim();
                String actualAddress = patientAddress.getText().trim();

                Debugger.println("Expected full name = " + NgisPatientOne.FULL_NAME + ", Actual full name " + actualFullName);
                Assert.assertEquals(NgisPatientOne.FULL_NAME, actualFullName);

                Debugger.println("Expected DOB = " + NgisPatientOne.DATE_OF_BIRTH + ", Actual DOB: " + actualFullDOB);
                Assert.assertTrue(actualFullDOB.contains("Born " + NgisPatientOne.DATE_OF_BIRTH));

                Debugger.println("Expected Gender= " + NgisPatientOne.GENDER + ", Actual Gender: " + actualGender);
                Assert.assertEquals("Gender " + NgisPatientOne.GENDER, actualGender);

                Debugger.println("Expected nhs no = " + NgisPatientOne.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);
                Assert.assertEquals("NHS No. " + NgisPatientOne.NHS_NUMBER, actualNHSNumber);

                Debugger.println("Expected address = " + NgisPatientOne.FULL_ADDRESS + ", Actual address " + actualAddress);
                Assert.assertEquals(NgisPatientOne.FULL_ADDRESS, actualAddress);

                break;
            }
            case "NGIS2": {
                String actualFullName = patientFullName.getText().trim();
                Debugger.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + NgisPatientTwo.DATE_OF_BIRTH);
                String actualFullDOB = patientDateOfBirth.getText().trim();
                String actualGender = patientGender.getText().trim();
                String actualNHSNumber = patientNSNo.getText().trim();
                String actualAddress = patientAddress.getText().trim();

                Debugger.println("Expected full name = " + NgisPatientTwo.FULL_NAME + ", Actual full name " + actualFullName);
                Assert.assertEquals(NgisPatientTwo.FULL_NAME, actualFullName);

                Debugger.println("Expected DOB = " + NgisPatientTwo.DATE_OF_BIRTH + ", Actual DOB: " + actualFullDOB);
                Assert.assertTrue(actualFullDOB.contains("Born " + NgisPatientTwo.DATE_OF_BIRTH));

                Debugger.println("Expected Gender= " + NgisPatientTwo.GENDER + ", Actual Gender: " + actualGender);
                Assert.assertEquals("Gender " + NgisPatientTwo.GENDER, actualGender);

                Debugger.println("Expected nhs no = " + NgisPatientTwo.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);
                Assert.assertEquals("NHS No. " + NgisPatientTwo.NHS_NUMBER, actualNHSNumber);

                Debugger.println("Expected address = " + NgisPatientTwo.FULL_ADDRESS + ", Actual address " + actualAddress);
                Assert.assertEquals(NgisPatientTwo.FULL_ADDRESS, actualAddress);
                break;
            }

            default:

                throw new IllegalArgumentException("Invalid query search parameters");

        }

    }


    public void fillInValidPatientDetailsUsingNOFields(String searchParams) {

        //DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female
        // Extract the patient details from the example-table

        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String s : paramsKey) {
            switch (s) {
                case "DOB": {
                    String dobValue = paramNameValue.get(s);
                    String[] dobSplit = dobValue.split("-");
                    dateDay.sendKeys(dobSplit[0]);
                    dateMonth.sendKeys(dobSplit[1]);
                    dateYear.sendKeys(dobSplit[2]);
                    break;
                }
                case "FirstName": {
                    firstName.sendKeys(paramNameValue.get(s));
                    break;
                }
                case "LastName": {
                    lastName.sendKeys(paramNameValue.get(s));
                    break;
                }
                case "Gender": {
                    genderButton.click();
                    genderValue.findElement(By.xpath("//span[text()='" + paramNameValue.get(s) + "']")).click();
                    break;
                }
                case "Postcode": {
                    postcode.sendKeys(paramNameValue.get(s));
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
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector("div[class*='error-message']"), 0);
        Assert.assertEquals("NHS Number is required.", getText(validationErrors.get(0)));
        Assert.assertEquals("Enter a day", getText(validationErrors.get(1)));
        Assert.assertEquals("Enter a month", getText(validationErrors.get(2)));
        Assert.assertEquals("Enter a year", getText(validationErrors.get(3)));
        Assert.assertEquals("rgba(221, 37, 9, 1)", nhsNumberLabel.getCssValue("color").toString());
        Assert.assertEquals("rgba(221, 37, 9, 1)", dateOfBirthLabel.getCssValue("color").toString());

    }


    public void validationErrorsAreDisplayedForSkippingMandatoryValuesDoYouHavePatientNHSNumberNO() {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector("div[class*='error-message']"), 0);
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

    public void clickOnFieldsAndVerifyAutoCompleteIsDisabled(/*WebElement element*/String[] textFieldElements) {

        for (String s : textFieldElements) {
            switch (s) {
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
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector("img[class*='no-results__img']"), 0);
        Wait.forElementToBeDisplayed(driver, noResultsHelpLink);
        Click.element(driver, noResultsHelpLink);
    }

    public boolean windowTitleValidation(String titleText) {
        String actual = driver.getTitle();
        if (actual.equalsIgnoreCase(titleText)) {
            return true;
        } else {
            return false;
        }
    }

}

