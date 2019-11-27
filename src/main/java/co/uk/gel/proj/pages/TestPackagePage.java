package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TestPackagePage {

    WebDriver driver;

    public TestPackagePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h1[class*='page-title']")
    public WebElement testPackagePageTitle;
    @FindBy(xpath = "//label[contains(@class,'field-label')]")
    public WebElement priorityLabel;

    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[2]")
    public WebElement routinePriorityButton;

    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[1]")
    public WebElement urgentPriorityButton;

    @FindBy(css = "[class*='button--selected']")
    public WebElement chosenPriorityButton;

    @FindBy(css = "p[class*='hint__text'")
    public WebElement priorityHintText;

    @FindBy(css = "*[class*='test-package__select-tests-heading']")
    public WebElement selectTestsHeader;

    @FindBy(xpath = "//div[contains(@class,'test-package__select-tests')]//following::p")
    public WebElement selectTestsDescription;

    @FindBy(css = "div[class*='test-list-item']")
    public WebElement testCard;

    @FindBy(css = "div[class*='checkbox-card']")
    public WebElement testCheckBoxCard;

    @FindBy(css = "span[class*='checkbox-card__checkmark']")
    public WebElement testCheckMark;

    @FindBy(css = "div[class*='test-card']")
    public WebElement testCardBody;

    @FindBy(css = "p[class*='test-card__title']")
    public WebElement testCardTitle;

    @FindBy(css = "p[class*='test-card__name']")
    public WebElement testCardName;

    @FindBy(css = "*[class*='test-card__test-type']")
    public List<WebElement> testCardCategory;

    @FindBy(css = "*[class*='test-list-item__target']")
    public WebElement targetedGenesHeader;

    @FindBy(css = "[class*='test-list-item__section']")
    public WebElement targetedGenesTestInfoLabel;

    @FindBy(css = "p[class*='test-list-item__description']")
    public WebElement testTargetDescription;

    @FindBy(css = "label[for*='numberOfParticipants']")
    public WebElement numberOfParticipantsLabel;

    @FindBy(id = "numberOfParticipants")
    public List<WebElement> numberOfParticipantsDropDown;


    public WebElement numberOfParticipants;

    @FindBy(xpath = "//*[contains(@id,'numberOfParticipants')]//following::path")
    public WebElement clearNumberOfParticipantsButton;

    @FindBy(css = "div[class*='error-message']")
    public WebElement errorMessage;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(xpath = "//*[contains(@class,'selected-family-members')]//child::h4")
    public WebElement selectedFamilyMembersHeader;

    @FindBy(css = "*[class*='relationship-tag']")
    public List<WebElement> selectedFamilyMembers;

    @FindBy(css = "[class*='error-message__text'])")
    public WebElement errorMessageNumberOfParticipants;

    @FindBy(css = "[class*='css-ljit9a-placeholder']")
    public WebElement participantsFieldPlaceHolder;

    private String checkboxValue = "checked";
    private String routine = "Routine";
    private String urgent = "Urgent";
    private String dropDownBoxValues = "";

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
        Wait.forElementToBeDisplayed(driver, priorityHintText);
        String actualHelpText = priorityHintText.getText();
        return actualHelpText.contains(expectedHelpText);
    }

    public boolean verifyTestsSection(String expectedSectionText) {
        return selectTestsHeader.getText().contains(expectedSectionText);
    }

    public boolean verifyTargetedGenes(String expectedTargetedGenesText) {
        boolean targetedGenesSectionIsCorrect = false;
        if (expectedTargetedGenesText != null) {
            targetedGenesSectionIsCorrect = testTargetDescription.isDisplayed();
        }
        return targetedGenesSectionIsCorrect;
    }

    public boolean verifyTestCardDetails(String testCardInfo) {
        boolean testCardDetailsAreCorrect = false;
        if (testCardInfo != null) {
            testCardDetailsAreCorrect = true;
            // check for Default Test card details - Routine priority & Singleton test
            if (testCardCategory.get(0).getText().contains(testCardInfo) &&
                    testCardCategory.get(1).getText().contains(testCardInfo)) {
            }
        }
        return testCardDetailsAreCorrect;
    }

    public boolean verifyTotalNumberOfParticipantsDropDownBox() {
        return numberOfParticipantsDropDown.get(0).isDisplayed();
    }

    public boolean verifyDefaultSelectedFamilyMembersInfo(String testRelationshipInfo) {
        return selectedFamilyMembers.get(0).isDisplayed();
    }

    public void selectNumberOfParticipants(int number) {
        Wait.forElementToBeDisplayed(driver, routinePriorityButton);
        Wait.forElementToBeDisplayed(driver, testCardBody);
        Actions.clickElement(driver, numberOfParticipants);
        Wait.seconds(1);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectValueFromDropdown(dropdownValue, String.valueOf(number));
        Wait.seconds(5);
    }
    public boolean testIsSelected() {
    	Wait.forElementToBeDisplayed(driver, routinePriorityButton);
		Wait.forElementToBeDisplayed(driver, testCardBody);
		return testCheckBoxCard.getAttribute("class").contains(checkboxValue);
    }

    public boolean testIsDeselected() {
        Wait.forElementToBeDisplayed(driver, testCardBody);
        boolean testCardIsSelected = testCheckBoxCard.getAttribute("class").contains(checkboxValue);
        boolean testIsSelected = testCheckMark.getAttribute("class").contains(checkboxValue);

        // ensure test card is deselected & test is deselected
        if (!testCardIsSelected && !testIsSelected) {
            //Selected tests for pro-band section should now be updated with 0 tests
            Debugger.println(" HEADER text info ::::  "+ selectTestsHeader.getText());
            return selectTestsHeader.getText().contains("0");
        } else {
            return false;
        }
    }

    public void clickTest() {
        Wait.forElementToBeDisplayed(driver, routinePriorityButton);
        Click.element(driver, testCheckBoxCard);
    }

    public void clickUrgentPriority() {
        Wait.forElementToBeDisplayed(driver, urgentPriorityButton);
        urgentPriorityButton.click();
    }

    public void clickRoutinePriority() {
        Wait.forElementToBeDisplayed(driver, routinePriorityButton);
        routinePriorityButton.click();
    }

    public void setTotalNumberOfParticipantsField(int number){
            Wait.forElementToBeDisplayed(driver, numberOfParticipants);
            Actions.clickElement(driver, numberOfParticipants);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, String.valueOf(number));
        }

        public boolean verifyTheTestsList(String expectedNumberOfTests) {
            Wait.forElementToBeDisplayed(driver, selectTestsHeader);
            return selectTestsHeader.getText().contains(expectedNumberOfTests);
        }

    public boolean verifyPrioritySectionHeaderText(String expectedHeaderText){
        return priorityLabel.getText().contains(expectedHeaderText);
    }

    public boolean verifyGivenPriorityIsSelected(String expectedPriority){
        Wait.forElementToBeDisplayed(driver, chosenPriorityButton);
        return chosenPriorityButton.getText().contains(expectedPriority);
    }

    public boolean verifyNumberOfParticipantsFieldExists(){
        Wait.forElementToBeDisplayed(driver, numberOfParticipantsLabel);
        Wait.forElementToBeDisplayed(driver, numberOfParticipantsDropDown.get(0));
        return (numberOfParticipantsLabel.isDisplayed() && numberOfParticipantsDropDown.get(0).isDisplayed());
    }

    public boolean verifyNumberOfParticipantsFieldDefaultValueIsEmpty(){
        Wait.forElementToBeDisplayed(driver, numberOfParticipantsDropDown.get(0));
        return numberOfParticipantsDropDown.get(0).getText().contains("");
    }

    public boolean verifyTheValuesShownInNumberOfParticipantsField(int minExpectedValue, int maxExpectedValue){
        Wait.forElementToBeDisplayed(driver, numberOfParticipants);
        Actions.clickElement(driver, numberOfParticipants);
        dropDownBoxValues = numberOfParticipants.getText();
        return (dropDownBoxValues.contains(String.valueOf(minExpectedValue)) && dropDownBoxValues.contains(String.valueOf(maxExpectedValue)));
    }

    public boolean verifyErrorMessageInTotalNumberOfParticipants(String expectedErrorMessage){
        Wait.forElementToBeDisplayed(driver, errorMessage);
        return errorMessage.getText().contains(expectedErrorMessage);
    }

    public void clearNumberOfParticipants() {
        if (!getText(numberOfParticipants).contains("Select")) {
            Actions.clickElement(driver, numberOfParticipants.findElement(By.tagName("svg")));
            Actions.clickElement(driver, selectTestsHeader);
            Wait.forElementToBeDisplayed(driver, errorMessage);
        }
    }

    public String getText(WebElement element) {
        Wait.forElementToBeDisplayed(driver, element);
        return element.getText();
    }

    public boolean verifyTheNumberOfParticipants(String expectedNumberOfParticipants){
        Wait.forElementToBeDisplayed(driver, numberOfParticipants);
        int actualNumberOfParticipants = Integer.parseInt(numberOfParticipants.getText());
        if(actualNumberOfParticipants == Integer.parseInt(expectedNumberOfParticipants)){
            return true;
        }
        else return false;

    }

    public boolean numberOfParticipantsFieldIsNotDisplayed() {
       if(numberOfParticipantsDropDown.size() == 0) {
           return true;
       }
       else return false;
    }

}
