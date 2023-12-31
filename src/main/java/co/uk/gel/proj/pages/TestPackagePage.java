package co.uk.gel.proj.pages;

import co.uk.gel.lib.Action;
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

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TestPackagePage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public TestPackagePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(css = "h1[class*='page-title']")
    public WebElement testPackagePageTitle;
    @FindBy(xpath = "//label[contains(@class,'field-label')]")
    public WebElement priorityLabel;
    //Urgent button displays first
    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[1]")
    public WebElement urgentPriorityButton;

    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[2]")
    public WebElement routinePriorityButton;

    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[2]//*[name()='svg']")
    public WebElement routinePriorityButtonSVGTickMark;

    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[1]//*[name()='svg']")
    public WebElement urgentPriorityButtonSVGTickMark;

    @FindBy(css = "[class*='button--selected']")
    public WebElement chosenPriorityButton;

    @FindBy(css = "p[class*='hint__text'")
    public WebElement priorityHintText;

    @FindBy(css = "*[class*='test-package__select-tests-heading']")
    public WebElement selectTestsHeader;

    @FindBy(css = "div[class*='checkbox-card']")
    public WebElement testCheckBoxCard;

    @FindBy(css = "span[class*='checkbox-card__checkmark']")
    public WebElement testCheckMark;

    @FindBy(css = "div[class*='test-card']")
    public WebElement testCardBody;

    @FindBy(css = "*[class*='test-card__test-type']")
    public List<WebElement> testCardCategory;

    @FindBy(css = "p[class*='test-list-item__description']")
    public WebElement testTargetDescription;

    @FindBy(css = "label[for*='numberOfParticipants']")
    public WebElement numberOfParticipantsLabel;

    @FindBy(id = "numberOfParticipants")
    public List<WebElement> numberOfParticipantsDropDown;

    @FindBy(xpath = "//div[@id='numberOfParticipants']")
    public WebElement numberOfParticipants;

    @FindBy(css = "div[class*='error-message']")
    public WebElement errorMessage;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(css = "*[class*='relationship-tag']")
    public List<WebElement> selectedFamilyMembers;

    private String checkboxValue = "checked";
    private String dropDownBoxValues = "";

    @FindBy(xpath = "//div[contains(@class,'test-list')]//span[contains(@class,'checkbox')]")
    WebElement testPackageCheckBox;

    @FindBy(xpath = "//p[contains(text(),'Trio')]/../*[name()='svg']")
    public WebElement trioFamilyIcon;

    @FindBy(xpath = "//span[contains(text(),'Trio')]/../*[name()='svg']")
    public WebElement trioFamilyIcon_TestOrder;

    @FindBy(xpath = "//div[contains(@class,'checkbox')]//p[contains(@class,'test-card__name')]")
    public WebElement testCardCIName;

    @FindBy(xpath = "//div[contains(@class,'checkbox')]//p[contains(@class,'test-card__title')]")
    public WebElement testCardCIId;

    public boolean verifyTestPackagePageTitle(String title) {
        Wait.forElementToBeDisplayed(driver, testPackagePageTitle);
        return testPackagePageTitle.getText().equalsIgnoreCase(title);
    }

    public boolean verifyPriorityButtonLabels(String expectedLabelTexts) {
        boolean priorityButtonLabelsAreDisplayedCorrectly = false;
        if (expectedLabelTexts != null) {
            routinePriorityButton.getText().contains(expectedLabelTexts);
            urgentPriorityButton.getText().contains(expectedLabelTexts);
            priorityButtonLabelsAreDisplayedCorrectly = true;
        }
        return priorityButtonLabelsAreDisplayedCorrectly;
    }

    public boolean verifyTheHelpText(String expectedHelpText) {
        try {
            Wait.forElementToBeDisplayed(driver, priorityHintText);
            String actualHelpText = Action.getText(priorityHintText);
            //Debugger.println("Help text info : " + actualHelpText);
            return actualHelpText.contains(expectedHelpText);
        }catch(Exception exp){
            return false;
        }
    }

    public boolean verifyTestsSection(String expectedSectionText) {
        try {
            return selectTestsHeader.getText().contains(expectedSectionText);
        }catch(Exception exp){
            return false;
        }
    }

    public boolean verifyTargetedGenes(String expectedTargetedGenesText) {
        if (expectedTargetedGenesText != null) {
            return testTargetDescription.isDisplayed();
        }
        return false;
    }

    public boolean verifyTestCardDetails(String testCardInfo) {
        boolean isPresent = false;
        try {
            // check for Default Test card details - Routine priority & Singleton test
            if (testCardInfo.contains(testCardCategory.get(0).getText()) &&
                  testCardInfo.contains(testCardCategory.get(1).getText())) {
                 isPresent = true;
            }else{
                Debugger.println("Expected:"+testCardInfo+", but Actual:"+testCardCategory.get(0).getText()+","+testCardCategory.get(1).getText());
            }
            return isPresent;
        }catch(Exception exp){
            return false;
        }
    }

    public boolean verifyTotalNumberOfParticipantsDropDownBox() {
        return numberOfParticipantsDropDown.get(0).isDisplayed();
    }

    public boolean verifyDefaultSelectedFamilyMembersInfo(String testRelationshipInfo) {
        return selectedFamilyMembers.get(0).isDisplayed();
    }

    public boolean selectNumberOfParticipants(int number) {
        try {
            if(!Wait.isElementDisplayed(driver,numberOfParticipants,30)){
                Debugger.println("TestPackage Not loaded.");
                return false;
            }
            numberOfParticipants.click();
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Action.selectValueFromDropdown(dropdownValue, String.valueOf(number));
            Wait.seconds(4);//Provided this wait, as even though the selection happened, sometimes test package not marked as completed
            //Ensure that the test package is selected
            By selectedPack = By.xpath("//div[@id='numberOfParticipants']//span[text()='" + number + "']");
            if (!seleniumLib.isElementPresent(selectedPack)) {
                //Trying with seleniumlib click which handled the javascript click also
                seleniumLib.clickOnWebElement(numberOfParticipants);
                if(!Wait.isElementDisplayed(driver,dropdownValue,5)){
                    seleniumLib.clickOnWebElement(numberOfParticipants);
                    Wait.seconds(2);
                }
                Action.selectValueFromDropdown(dropdownValue, String.valueOf(number));
                Wait.seconds(4);
            }
            //Debugger.println("Selected TestPack: "+seleniumLib.getText(selectedPack));
            return true;
        } catch (Exception exp) {
            try{
                By tpDiv = By.xpath("//div[@id='numberOfParticipants']");
                seleniumLib.clickOnElement(tpDiv);
                Wait.seconds(1);
                By tpValue = By.xpath("//div[@id='numberOfParticipants']//span[text()='" + number + "']");
                seleniumLib.clickOnElement(tpValue);
                Wait.seconds(1);
                return true;
            }catch(Exception exp1){
                Debugger.println("Exception in Selecting number of Participants in Test Package." + exp);
                return false;
            }
        }
    }

    public boolean testIsSelected() {
        try {
            Wait.forElementToBeDisplayed(driver, routinePriorityButton);
            Wait.forElementToBeDisplayed(driver, testCardBody);
            return testCheckBoxCard.getAttribute("class").contains(checkboxValue);
        }catch(Exception exp){
            return false;
        }
    }

    public boolean testIsDeselected() {
        Wait.forElementToBeDisplayed(driver, testCardBody);
        boolean testCardIsSelected = testCheckBoxCard.getAttribute("class").contains(checkboxValue);
        boolean testIsSelected = testCheckMark.getAttribute("class").contains(checkboxValue);
        // ensure test card is deselected & test is deselected
        if (!testCardIsSelected && !testIsSelected) {
            //Selected tests for pro-band section should now be updated with 0 tests
            //Debugger.println(" HEADER text info ::::  " + selectTestsHeader.getText());
            return selectTestsHeader.getText().contains("0");
        } else {
            return false;
        }
    }

    public void clickTest() {
        Wait.forElementToBeDisplayed(driver, routinePriorityButton);
        Click.element(driver, testCheckBoxCard);
    }

    public boolean clickUrgentPriority() {
        try {
            if(!Wait.isElementDisplayed(driver, urgentPriorityButton,30)){
                Debugger.println("TestPackage Urgent not displayed.");
            }
            urgentPriorityButton.click();
            return true;
        }catch(Exception exp){
            try {
                seleniumLib.clickOnWebElement(urgentPriorityButton);
                return true;
            }catch(Exception exp1){
                Debugger.println("TestPackage Urgent not displayed."+exp1);
                return false;
            }
        }
    }

    public boolean clickRoutinePriority() {
        try {
            if(!Wait.isElementDisplayed(driver, routinePriorityButton,30)){
                Debugger.println("TestPackage Routine not displayed.");
            }
            routinePriorityButton.click();
            return true;
        }catch(Exception exp){
            try {
                seleniumLib.clickOnWebElement(routinePriorityButton);
                return true;
            }catch(Exception exp1){
                Debugger.println("TestPackage Routine not displayed."+exp1);
                return false;
            }
        }
    }

    public void setTotalNumberOfParticipantsField(int number) {
        try {
            Wait.forElementToBeDisplayed(driver, numberOfParticipants);
            Action.clickElement(driver, numberOfParticipants);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Action.selectValueFromDropdown(dropdownValue, String.valueOf(number));
        }catch(Exception exp){
            Debugger.println("setTotalNumberOfParticipantsField");
        }
    }

    public boolean verifyTheTestsList(String expectedNumberOfTests) {
        Wait.forElementToBeDisplayed(driver, selectTestsHeader);
        return selectTestsHeader.getText().contains(expectedNumberOfTests);
    }

    public boolean verifyPrioritySectionHeaderText(String expectedHeaderText) {
        String actualHeaderText = Action.getText(priorityLabel);
        //Debugger.println("Actual Priority label header text : " + actualHeaderText);
        return actualHeaderText.contains(expectedHeaderText);
    }

    public boolean verifyGivenPriorityIsSelected(String expectedPriority) {
        try {
            if(!Wait.isElementDisplayed(driver, chosenPriorityButton, 30)){
                return false;
            }
            String actualText = Action.getText(chosenPriorityButton);
            return actualText.contains(expectedPriority);
        }catch(Exception exp){
            Debugger.println("Exception in verifying verifyGivenPriorityIsSelected:"+exp);
            return false;
        }
    }
    public boolean verifyGivenPriorityIsDeSelected(String unSelectedPriority) {
        try {
            if(!Wait.isElementDisplayed(driver, chosenPriorityButton, 30)){
                return false;
            }
            String actualText = Action.getText(chosenPriorityButton);
            if(actualText.contains(unSelectedPriority)) {
                return false;//Should not contain
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in verifying verifyGivenPriorityIsSelected:"+exp);
            return false;
        }
    }

    public boolean verifyNumberOfParticipantsFieldExists() {
        Wait.forElementToBeDisplayed(driver, numberOfParticipantsLabel);
        Wait.forElementToBeDisplayed(driver, numberOfParticipantsDropDown.get(0));
        return (numberOfParticipantsLabel.isDisplayed() && numberOfParticipantsDropDown.get(0).isDisplayed());
    }

    public boolean verifyNumberOfParticipantsFieldDefaultValueIsEmpty() {
        try {
            if(!Wait.isElementDisplayed(driver,numberOfParticipantsDropDown.get(0),10)){
                Debugger.println("Participant dropdown list not displayed.");
                return false;
            }
            String actValue = numberOfParticipantsDropDown.get(0).getText();
            //Debugger.println("ActValue:"+actValue+":");
            return numberOfParticipantsDropDown.get(0).getText().contains("");
        }catch(Exception exp){
            Debugger.println("Exception in verifyNumberOfParticipantsFieldDefaultValueIsEmpty:"+exp);
            return false;
        }
    }

    public boolean verifyTheValuesShownInNumberOfParticipantsField(int minExpectedValue, int maxExpectedValue) {
        Wait.forElementToBeDisplayed(driver, numberOfParticipants);
        Action.clickElement(driver, numberOfParticipants);
        dropDownBoxValues = numberOfParticipants.getText();
        return (dropDownBoxValues.contains(String.valueOf(minExpectedValue)) && dropDownBoxValues.contains(String.valueOf(maxExpectedValue)));
    }

    public boolean verifyErrorMessageInTotalNumberOfParticipants(String expectedErrorMessage) {
        try {
            if(!Wait.isElementDisplayed(driver, errorMessage,10)){
                Debugger.println("Expected TestPackage Number of Participant Error, nit displayed.");
                return false;
            }
            return errorMessage.getText().contains(expectedErrorMessage);
        }catch(Exception exp){
            Debugger.println("Exception in verifyErrorMessageInTotalNumberOfParticipants:"+exp);
            return false;
        }
    }

    public boolean clearNumberOfParticipants() {
        String currentValue = "";
        try {
            currentValue = getText(numberOfParticipants);
            if (!currentValue.contains("Select")) {
                Action.clickElement(driver, numberOfParticipants.findElement(By.tagName("svg")));
                Action.clickElement(driver, selectTestsHeader);
                if(!Wait.isElementDisplayed(driver, errorMessage,10)){
                    Debugger.println("Expected Error message to be displayed in TestPackage, but not.");
                    return false;
                }
            }
            return true;
        }catch(Exception exp){
            currentValue = getText(numberOfParticipants);
            if (!currentValue.contains("Select")) {
                Debugger.println("Exception in clearNumberOfParticipants:" + exp);
                return false;
            }
            return true;//If the value is selected as "Select", means, nothing selected
        }
    }

    public String getText(WebElement element) {
        try {
            Wait.forElementToBeDisplayed(driver, element);
            return element.getText();
        }catch(Exception exp){
            return "";
        }
    }

    public boolean verifyTheNumberOfParticipants(String expectedNumberOfParticipants) {
        Wait.forElementToBeDisplayed(driver, numberOfParticipants);
        int actualNumberOfParticipants = Integer.parseInt(numberOfParticipants.getText());
        if (actualNumberOfParticipants == Integer.parseInt(expectedNumberOfParticipants)) {
            return true;
        } else return false;

    }

    public boolean numberOfParticipantsFieldIsNotDisplayed() {
        if (numberOfParticipantsDropDown.size() == 0) {
            return true;
        } else return false;
    }

    public boolean ensureTickMarkIsDisplayedNextToRoutine() {
        //Wait.forElementToBeDisplayed(driver, routinePriorityButtonSVGTickMark);
        if (Wait.isElementDisplayed(driver, routinePriorityButtonSVGTickMark, 30)) {
            return true;
        }
        //Debugger.println("RoutineButton expected to have tick mark. but not.");
        return false;
    }

    public boolean ensureTickMarkIsDisplayedNextToUrgent() {
        //Wait.forElementToBeDisplayed(driver, urgentPriorityButtonSVGTickMark);
        if (Wait.isElementDisplayed(driver, urgentPriorityButtonSVGTickMark, 30)) {
            return true;
        }
        Debugger.println("UrgentButton expected to have tick mark. but not.");
        return false;
    }

    public boolean selectTheDeselectedTestPackage() {
        try {
            if (!Wait.isElementDisplayed(driver, testPackageCheckBox, 20)) {
                Debugger.println("Expected status of Test is deSelected , but the current status is selected.");
                return false;
            }
            try {
                Action.clickElement(driver, testPackageCheckBox);
            }catch(Exception exp1){
                seleniumLib.clickOnWebElement(testPackageCheckBox);
            }
            return true;
             }
        catch (Exception exp) {
            Debugger.println("SelectTheTest:Exception:" + exp);
            return false;
        }
    }

    public boolean verifyTrioFamilyIcon() {
        try {
            if (!Wait.isElementDisplayed(driver, trioFamilyIcon, 30)) {
                if(!Wait.isElementDisplayed(driver,trioFamilyIcon_TestOrder,30)) {
                    Debugger.println("Try family icon is not visible." + driver.getCurrentUrl());
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("try family icon is not visible : test package page : " + exp+"\n"+driver.getCurrentUrl());
            return false;
        }
    }

    public boolean verifyCINameIDPresence(){
        try{
            Wait.forElementToBeDisplayed(driver, testCardBody);
            if (!Wait.isElementDisplayed(driver,testCardCIId,10)){
                Debugger.println("Test Card CI ID not present ");
                return false;
            }
            if (!Wait.isElementDisplayed(driver,testCardCIName,10)){
                Debugger.println("Test Card CI Name not present ");
                return false;
            }
            return true;
        }catch (Exception exp){
            Debugger.println(" Exception in verifyCINameIDPresence : "+exp);
            return false;
        }
    }

    public boolean updateTestPackageDetails(String testPackageDetails) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(testPackageDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        for (String key : paramsKey) {
            switch (key) {
                case "NoOfParticipants": {
                    selectNumberOfParticipants(Integer.parseInt(paramNameValue.get(key)));
                    break;
                }
                case "Priority": {
                    String selectedPriorityButton = chosenPriorityButton.getText();
                    if (!selectedPriorityButton.equalsIgnoreCase(paramNameValue.get(key))) {
                        if (paramNameValue.get(key).equalsIgnoreCase("Urgent")){
                            clickUrgentPriority();
                        }else{
                            clickRoutinePriority();
                        }
                    }
                    break;
                }
            }
        }
        return true;
    }
    public boolean verifyTestPackageDetails(String testPackageDetails) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(testPackageDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        String actValue = "";
        String expValue = "";
        for (String key : paramsKey) {
            expValue = paramNameValue.get(key);
            switch (key) {
                case "NoOfParticipants":{
                    By noOfParticipantsPath=By.xpath("//div[@id='numberOfParticipants']");
                    actValue = seleniumLib.getText(noOfParticipantsPath);
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }
                case "Priority": {
                    actValue = chosenPriorityButton.getText();
                    if (!actValue.equalsIgnoreCase(expValue)) {
                        Debugger.println("Expected :" + key + ": " + expValue + ", Actual:" + actValue);
                        return false;
                    }
                    break;
                }

            }
        }
        return true;
    }
}//end