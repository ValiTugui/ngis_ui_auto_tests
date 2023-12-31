package co.uk.gel.proj.pages;

import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

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

    @FindBy(xpath = "//input[@id='birthDateDay']")
    public WebElement dateDay;

    @FindBy(xpath = "//input[@id='birthDateMonth']")
    public WebElement dateMonth;

    @FindBy(xpath = "//input[@id='birthDateYear']")
    public WebElement dateYear;

    @FindBy(xpath = "//button[contains(string(),'Search')]")
    public WebElement searchButton;

    @FindBy(xpath = "//h3[contains(text(),'Do you have the family member’s NHS Number?')]")
    public WebElement nhsQuestion;

    @FindBy(xpath = "//button[text()='No']")
    public WebElement noButton;

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(css = "label[for*='gender']")
    public WebElement genderLabel;

    @FindBy(id = "gender")
    public WebElement genderInput;

    @FindBy(id = "postcode")
    public WebElement postcode;

    @FindBy(xpath = "//div[@class='styles_search-terms__1Udiy']/p/strong")
    public WebElement youHaveSearchedForLabel;

    @FindBy(xpath = "//h3[contains(string(), 'No patient found')]")
    public WebElement noPatientFoundLabel;

    @FindBy(xpath = "//a[@class='styles_inline-link__3cAK2']")
    public WebElement createNewPatientLink;

    @FindBy(css = "div[class*='error-message__text']")
    public List<WebElement> validationErrors;

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

    @FindBy(xpath = "//h3[contains(@class,'styles_results__header')]")
    public WebElement familyMemeberFound;

    @FindBy(xpath="//p[@class='styles_text__1aikh styles_text--6__3mCVT styles_patient-name__2PfmN']")
    public WebElement resultCardPatientName;

    @FindBy(xpath="//p[contains(text(),'Born')]")
    public WebElement resultCardDOB;

    @FindBy(xpath="//p[contains(text(),'Gender')]")
    public WebElement resultCardGender;

    @FindBy(xpath="//p[contains(text(),'NHS No.')]")
    public WebElement resultCardNHSNo;

    @FindBy(xpath="//p[contains(text(),'Address')]")
    public WebElement resultCardAddress;

    @FindBy(xpath = "//div[@class='css-1yllhwh']/following::h2[@class='css-1ueygkf']/following::button[1]")
    public WebElement editBoxTestPackage;

    @FindBy(xpath = "//button[text()='No']/*[name()='svg']")
    public WebElement noButtonSVG;

    @FindBy(xpath = "//button[text()='Yes']/*[name()='svg']")
    public WebElement yesButtonSVG;

    @FindBy(xpath = "//a[contains(text(),'add non-tested family members')]")
    public WebElement addNonTestedFamilyMemberLink;

    @FindBy(xpath = "//div[@id='react-select-9-option-0']")
    public WebElement genderFemale;

    @FindBy(xpath = "//div[@id='react-select-9-option-1']")
    public WebElement genderMale;

    @FindBy(xpath = "//div[@id='react-select-9-option-2']")
    public WebElement genderOther;

    @FindBy(xpath = "//div[@id='react-select-9-option-3']")
    public WebElement genderUnknown;


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
            if(!expElements.get(i).isDisplayed()){
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
        Action.clickElement(driver,noButton);
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
            if(!expElements.get(i).isDisplayed()){
                return false;
            }
        }
        return true;
    }

    public void clickSearchButton() {
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

    public boolean searchFamilyMemberWithGivenParams(String searchParams) {
        try {
            if (searchParams == null || searchParams.isEmpty()) {//Search without entering any values
                seleniumLib.clickOnWebElement(searchButton);
                return true;
            }
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchParams);
            Set<String> paramsKey = paramNameValue.keySet();
            for (String key : paramsKey) {
                 switch (key) {
                    case "NHSNumber": {
                        Action.clearInputField(nhsNumber);
                        seleniumLib.sendValue(nhsNumber, paramNameValue.get(key));
                        break;
                    }
                    case "DOB": {
                        String dobValue = paramNameValue.get(key);
                        if (dobValue != null && !dobValue.isEmpty()) {
                            String[] dobSplit = dobValue.split("-");
                            seleniumLib.sendValue(dateDay,dobSplit[0]);
                            seleniumLib.sendValue(dateMonth,dobSplit[1]);
                            seleniumLib.sendValue(dateYear,dobSplit[2]);
                       }
                       break;
                    }
                    case "FirstName": {
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            firstName.clear();
                            firstName.sendKeys(paramNameValue.get(key));
                        }
                        break;
                    }
                    case "LastName": {
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            lastName.clear();
                            lastName.sendKeys(paramNameValue.get(key));
                        }
                        break;
                    }
                    case "Gender": {
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            selectFamilyMemberGender(genderButton, paramNameValue.get(key));
                        }
                        break;
                    }
                    case "Postcode": {
                        if (paramNameValue.get(key) != null && !paramNameValue.get(key).isEmpty()) {
                            postcode.clear();
                            postcode.sendKeys(paramNameValue.get(key));
                        }
                        break;
                    }
                }//switch
             }//for
             seleniumLib.clickOnWebElement(searchButton);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in searching family member: "+exp);
            return false;
        }
    }//method
    public void selectFamilyMemberGender(WebElement element, String optionValue){
        WebElement ddValue = null;
        try {
            Action.retryClickAndIgnoreElementInterception(driver, element);
            Wait.seconds(2);
            List<WebElement> ddElements = driver.findElements(By.xpath("//label[@for='gender']/..//div//span[text()='"+optionValue+"']"));
            if(ddElements.size() > 0) {
                Wait.forElementToBeClickable(driver, ddElements.get(0));
                Action.clickElement(driver, ddElements.get(0));
                Wait.seconds(2);
            }
        }catch(Exception exp){
            Debugger.println("Exception in selecting FamilyMember Gender in Search Page: "+exp);
            SeleniumLib.takeAScreenShot("FMSearchGenderDropDown.jpg");
        }
    }

    public boolean checkTheErrorMessageForInvalidField(String errorMessage, String fontColor) {
        try {
            String[] expMessages = null;
            if(errorMessage.indexOf(",") == -1){
                expMessages = new String[]{errorMessage};
            }else{
                expMessages = errorMessage.split(",");
            }
            String actualMessage = "";
            String actColor = "";
            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(fontColor);
            for(int i=0; i<expMessages.length;i++) {
                actualMessage = validationErrors.get(i).getText();
                if (!expMessages[i].equalsIgnoreCase(actualMessage)) {
                    Debugger.println("Expected Message: " + errorMessage + ", but Actual Message: " + actualMessage);
                    return false;
                }
                actColor = validationErrors.get(i).getCssValue("color");
                if (!expectedFontColor.equalsIgnoreCase(actColor)) {
                    Debugger.println("Expected Color: " + expectedFontColor + ", but Actual Color: " + actColor);
                    return false;
                }
            }
            validationErrors.clear();
            return true;

        }catch(Exception exp){
            Debugger.println("FamilyMemberSearchPage:Exception from validating Error Message "+exp);
            return false;
        }
    }

    public boolean checkTheResultMessageForFamilyMember(String resultMessage) {
        String actualMessage = "";
        try {
            if(Wait.isElementDisplayed(driver,familyMemeberFound,30)) {
                 actualMessage = familyMemeberFound.getText();
                if (resultMessage.equalsIgnoreCase(actualMessage)) {
                    return true;
                }
            }
            Debugger.println("Family member search result not displayed as expected:" + resultMessage + ", but actual: " + actualMessage);
            SeleniumLib.takeAScreenShot("FMSearchResult.jpg");
            return false;
        }catch(Exception exp){
            Debugger.println("Exception from validating family member search result:"+exp);
            return false;
        }
    }

    public boolean verifyTheFamilyMemberSearchResultDisplay() {
        Wait.forElementToBeDisplayed(driver, familyMemeberFound);
        List<WebElement> expResultElements = new ArrayList<WebElement>();
        expResultElements.add(resultCardPatientName);
        expResultElements.add(resultCardDOB);
        expResultElements.add(resultCardGender);
        expResultElements.add(resultCardNHSNo);
        expResultElements.add(resultCardAddress);
        for(int i=0; i<expResultElements.size(); i++){
            if(!expResultElements.get(i).isDisplayed()){
                return false;
            }
        }
        return true;
    }

    public boolean verifyTheDescriptionOfThePage(String DescriptionOfPage) {
        try {
        String actualPageDescription = pageDescription.getText();
           if(!actualPageDescription.contains(DescriptionOfPage)){
               return false;
           }
           return true;
        }catch(Exception exp){
            return false;
        }
    }
    public boolean verifyTheQuestionOfThePage(String searchQuestion) {
        String actualQuestion = nhsQuestion.getText();
       if(!actualQuestion.contains(searchQuestion)){
           Debugger.println("Actual Question:"+actualQuestion+",Expected Question: "+searchQuestion);
           return false;
       }
       return true;
    }
     public boolean clickOnNewPatientLink() {
        try {
            if(!Wait.isElementDisplayed(driver,createNewPatientLink,10)){
                return false;
            }
            Action.clickElement(driver,createNewPatientLink);
            return true;
        }catch(Exception exp){
            return false;
        }
    }

    public boolean checkTheErrorMessageForIncompleteDetailsForFamilyMember(String errorMessage, String fontColor) {
        try {
            //Verify the Message Content
            By errorElement = By.xpath("//span[text()='"+errorMessage+"']");
            if(!seleniumLib.isElementPresent(errorElement)){
                Debugger.println("Expected Error Message:"+errorMessage+" not displayed in family member landing page.");
                return false;
            }
            //Verify the Message Color
            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(fontColor);
            String actColor = driver.findElement(errorElement).getCssValue("color");
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

    public void verifyNoPatientFoundDetails(String expSearchString, String errorMessage, String expectedFontFace) {
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

    public boolean checkTheErrorMessageForIncompleteFamilyMember() {
        try {
            if (editBoxTestPackage.isDisplayed()) {
                return false;
            }
            return true;
        }catch (Exception exp){
            //Return true as if the element is not present, it throws NoSuchElementException.
            return true;
        }
    }
    public boolean verifyNHSFieldPlaceHolder() {
        try {
            String actStatus = nhsNumber.getAttribute("placeholder");
            if(!actStatus.equalsIgnoreCase("e.g. 1231237890")){
                return false;
            }
            return true;
        }catch(Exception exp){
            return false;
        }
    }
    public boolean verifyDOBFieldPlaceHolder() {
        try {
            String actStatus = dateDay.getAttribute("placeholder");
            if(!actStatus.equalsIgnoreCase("DD")){
                return false;
            }
            actStatus = dateMonth.getAttribute("placeholder");
            if(!actStatus.equalsIgnoreCase("MM")){
                return false;
            }
            actStatus = dateYear.getAttribute("placeholder");
            if(!actStatus.equalsIgnoreCase("YYYY")){
                return false;
            }
            return true;
        }catch(Exception exp){
            return false;
        }
    }
    public boolean verifySearchButtonClickable() {
        try {
            //Should throw exception, if not clickable
            Wait.forElementToBeClickable(driver,searchButton);
            return true;
        }catch(Exception exp){
           return false;
        }
    }
    public boolean verifySVGForTickMark() {
        try {
            Wait.forElementToBeDisplayed(driver, noButton);
            Wait.forElementToBeDisplayed(driver, yesButton);
            if (!noButton.isSelected()) {//Select No, if not selected and verify presence of SVG
                Action.clickElement(driver,noButton);
                if(!Wait.isElementDisplayed(driver,noButtonSVG,5)){
                    return false;
                }
            }
            if (!yesButton.isSelected()) {
                Action.clickElement(driver,yesButton);
                if(!Wait.isElementDisplayed(driver,yesButtonSVG,5)){
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            return false;
        }
    }
    public void clickOnAddNonTestedFamilyMemberLink() {
        Action.clickElement(driver,addNonTestedFamilyMemberLink);
    }

    public boolean checkGenderDropDownSuggestionFamilyMember(String expectedGenderValue) {
        try {
            genderInput.sendKeys(expectedGenderValue);
            Wait.seconds(1);
            String actualGender;
            if (expectedGenderValue.equalsIgnoreCase("F")) {
                actualGender = genderFemale.getText();
                genderFemale.click();
            } else if (expectedGenderValue.equalsIgnoreCase("M")) {
                actualGender = genderMale.getText();
                genderMale.click();
            } else if (expectedGenderValue.equalsIgnoreCase("U")) {
                actualGender = genderUnknown.getText();
                genderUnknown.click();
            } else if (expectedGenderValue.equalsIgnoreCase("O")){
                actualGender = genderOther.getText();
                genderOther.click();
            }else{
                Debugger.println("Please check your input..");
                return false;
            }

            Wait.seconds(1);
            String expectedGender = expectedGenderValue;

            if (actualGender.charAt(0) != expectedGender.charAt(0)) {
                System.out.println("Values are NOT matching Expected Gender= " + expectedGender + ", Actual Gender: " + actualGender);
                return false;
            }

            return true;
        } catch (Exception msg) {
            SeleniumLib.takeAScreenShot("CheckingGenderInFamilyMemberFailed.jpg");
            System.out.println("Failed to check gender in family member from clickingGenderDropdownInFamilymember " + msg);
            return false;
        }
    }

}//end