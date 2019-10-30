package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
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

    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[1]")
    public WebElement routinePriorityButton;

    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[2]")
    public WebElement urgentPriorityButton;

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
    public List<WebElement> numberOfParticipantsDropdown;

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

    private String checkboxValue = "checked";
    private String routine = "Routine";
    private String urgent = "Urgent";

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
        boolean helpTextIsMatching = false;
        return priorityHintText.getText().contains(expectedHelpText);
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
        return numberOfParticipantsDropdown.get(0).isDisplayed();
    }

    public boolean verifyDefaultSelectedFamilyMembersInfo(String testRelationshipInfo) {
        return selectedFamilyMembers.get(0).isDisplayed();
    }

    public void selectNumberOfParticipants(int number) {
        Wait.forElementToBeDisplayed(driver, routinePriorityButton);
        Wait.forElementToBeDisplayed(driver, testCardBody);
        Actions.clickElement(driver, numberOfParticipants);
        //Wait.seconds(1);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectValueFromDropdown(dropdownValue, String.valueOf(number));
    }
    public boolean testIsSelected() {
    	Wait.forElementToBeDisplayed(driver, routinePriorityButton);
		Wait.forElementToBeDisplayed(driver, testCardBody);
		return testCheckBoxCard.getAttribute("class").contains(checkboxValue);
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

    public boolean verifyPrioritySectionHeaderText(String expectedHeaderText){
        return priorityLabel.getText().contains(expectedHeaderText);
    }

    public boolean verifyGivenPriorityIsSelected(String priority){
        if(priority.equals(routine)){
            return  routinePriorityButton.isSelected();
        }
        else if (priority.equals(urgent)){
            return urgentPriorityButton.isSelected();
        }
        else{
         new RuntimeException("Priority options should be in Routine or Urgent");
         return false;
        }
    }
}

