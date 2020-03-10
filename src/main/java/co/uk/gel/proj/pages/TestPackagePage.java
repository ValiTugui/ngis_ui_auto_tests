package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
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
    public WebElement urgentPriorityButton;

    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[1]")
    public WebElement routinePriorityButton;

    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[1]//*[name()='svg']")
    public WebElement routinePriorityButtonSVGTickMark;

    @FindBy(xpath = "//div[contains(@class,'test-package__priority')]/button[2]//*[name()='svg']")
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

    @FindBy(xpath = "//div[contains(@class,'styles_optimalFamilyStructure')]/child::*[1]")
    public WebElement trioFamilyIconInPedigree;

    @FindBy(xpath = "(//div[contains(@class,'styles_test-card__test-category__25tRP')])[2]/child::*[@fill='inherit']")
    public WebElement trioFamilyIconInTestPackage;

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
        String actualHelpText = Actions.getText(priorityHintText);
        Debugger.println("Help text info : " + actualHelpText);
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

    public boolean selectNumberOfParticipants(int number) {
        try {
            Wait.forElementToBeDisplayed(driver, routinePriorityButton);
            Wait.forElementToBeDisplayed(driver, testCardBody);
            Wait.forElementToBeDisplayed(driver, numberOfParticipants);
            if (!Wait.isElementDisplayed(driver, numberOfParticipants, 5)) {
                Debugger.println("Number of Participants element in TestPackage is not displayed:");
                Assert.assertFalse("Number of Participants element in TestPackage is not displayed:", true);
            }
            numberOfParticipants.click();
            Wait.seconds(2);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, String.valueOf(number));
            Wait.seconds(4);//Provided this wait, as even though the selection happened, sometimes test package not marked as completed
            //Ensure that the test package is selected
            WebElement selectedPack = driver.findElement(By.xpath("//div[@id='numberOfParticipants']//span[text()='" + number + "']"));
            if (!Wait.isElementDisplayed(driver, selectedPack, 10)) {
                Debugger.println("Test Package could not select with number: " + number);
                SeleniumLib.takeAScreenShot("TestPackageSelection.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in Selecting number of Participants in Test Package." + exp);
            SeleniumLib.takeAScreenShot("TestPackageNoOfPID.jpg");
            return false;
        }
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
            Debugger.println(" HEADER text info ::::  " + selectTestsHeader.getText());
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

    public void setTotalNumberOfParticipantsField(int number) {
        Wait.forElementToBeDisplayed(driver, numberOfParticipants);
        Actions.clickElement(driver, numberOfParticipants);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectValueFromDropdown(dropdownValue, String.valueOf(number));
    }

    public boolean verifyTheTestsList(String expectedNumberOfTests) {
        Wait.forElementToBeDisplayed(driver, selectTestsHeader);
        return selectTestsHeader.getText().contains(expectedNumberOfTests);
    }

    public boolean verifyPrioritySectionHeaderText(String expectedHeaderText) {
        String actualHeaderText = Actions.getText(priorityLabel);
        Debugger.println("Actual Priority label header text : " + actualHeaderText);
        return actualHeaderText.contains(expectedHeaderText);
    }

    public boolean verifyGivenPriorityIsSelected(String expectedPriority) {
        Wait.forElementToBeDisplayed(driver, chosenPriorityButton, 200);
        String actualText = Actions.getText(chosenPriorityButton);
        Debugger.println("Selected test Priority : " + actualText);
        return actualText.contains(expectedPriority);
    }

    public boolean verifyNumberOfParticipantsFieldExists() {
        Wait.forElementToBeDisplayed(driver, numberOfParticipantsLabel);
        Wait.forElementToBeDisplayed(driver, numberOfParticipantsDropDown.get(0));
        return (numberOfParticipantsLabel.isDisplayed() && numberOfParticipantsDropDown.get(0).isDisplayed());
    }

    public boolean verifyNumberOfParticipantsFieldDefaultValueIsEmpty() {
        Wait.forElementToBeDisplayed(driver, numberOfParticipantsDropDown.get(0));
        return numberOfParticipantsDropDown.get(0).getText().contains("");
    }

    public boolean verifyTheValuesShownInNumberOfParticipantsField(int minExpectedValue, int maxExpectedValue) {
        Wait.forElementToBeDisplayed(driver, numberOfParticipants);
        Actions.clickElement(driver, numberOfParticipants);
        dropDownBoxValues = numberOfParticipants.getText();
        return (dropDownBoxValues.contains(String.valueOf(minExpectedValue)) && dropDownBoxValues.contains(String.valueOf(maxExpectedValue)));
    }

    public boolean verifyErrorMessageInTotalNumberOfParticipants(String expectedErrorMessage) {
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
        Wait.forElementToBeDisplayed(driver, routinePriorityButtonSVGTickMark);
        if (Wait.isElementDisplayed(driver, routinePriorityButtonSVGTickMark, 10)) {
            return true;
        }
        return false;
    }

    public boolean ensureTickMarkIsDisplayedNextToUrgent() {
        Wait.forElementToBeDisplayed(driver, urgentPriorityButtonSVGTickMark);
        if (Wait.isElementDisplayed(driver, urgentPriorityButtonSVGTickMark, 10)) {
            return true;
        }
        return false;
    }

    public boolean selectTheDeselectedTestPackage() {
        try {
            Wait.forElementToBeDisplayed(driver, testPackageCheckBox);
            testPackageCheckBox.click();
            return true;
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("testSelect.jpg");
            Debugger.println("SelectTheTest:Exception:" + exp);
            return false;
        }
    }

    public boolean validateTrioFamilyIconInOfflineOrder() {
        try {
            if (!Wait.isElementDisplayed(driver, trioFamilyIconInPedigree, 10)) {
                Debugger.println("try family icon not visible : validateTrioFamilyIconInPedigree : in pedigree page");
                SeleniumLib.takeAScreenShot("validateTrioFamilyIconInOfflineOrder.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("TestPackage : validateTrioFamilyIconInPedigree : in pedigree page : " + exp);
            SeleniumLib.takeAScreenShot("validateTrioFamilyIconInOfflineOrder.jpg");
            return false;
        }
    }

    public boolean verifyTrioFamilyIconPresenceInTestPackage() {
        try {
            if (!Wait.isElementDisplayed(driver, trioFamilyIconInTestPackage, 10)) {
                Debugger.println("try family icon is not visible : test package page");
                SeleniumLib.takeAScreenShot("TrioFamilyIcon.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("try family icon is not visible : test package page : " + exp);
            SeleniumLib.takeAScreenShot("TrioFamilyIcon.jpg");
            return false;
        }
    }


}//end