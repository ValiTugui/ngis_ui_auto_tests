package co.uk.gel.proj.pages;

import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NotesPage {

    WebDriver driver;
    SeleniumLib seleniumLib;
    Faker faker = new Faker();

    @FindBy(name = "notes")
    public WebElement addNoteField;

    @FindBy(xpath = "//p[contains(@class,'notes__count')]/span")
    public List<WebElement> infoMessages;

    @FindBy(xpath = "//p[contains(@class,'notes__count--warning')]/span")
    public List<WebElement> warningMessages;

    @FindBy(xpath = "//div[contains(@class,'paragraph')]")
    public List<WebElement> textInformation;


    public NotesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public String getText(WebElement element) {
        Wait.forElementToBeDisplayed(driver, element);
        return Action.getText(element);
    }

    public void fillInValue(WebElement element, String value) {
        Wait.forElementToBeDisplayed(driver, element);
        element.clear();
        element.sendKeys(value);
    }

    public boolean fillInAddNotesField() {
        try {
            if (!Wait.isElementDisplayed(driver, addNoteField, 30)) {
                Debugger.println("AddNoteField not displayed.\n" + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("fillInAddNotesField.jpg");
                return false;
            }
            addNoteField.clear();
            addNoteField.sendKeys(faker.chuckNorris().fact());
            return true;
        } catch (Exception exp) {
            try {
                seleniumLib.sendValue(addNoteField, faker.chuckNorris().fact());
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception from fillInAddNotesField." + exp + "\n" + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("fillInAddNotesField.jpg");
                return false;
            }
        }
    }

    public boolean fillInAddNotes(String updateNotes) {
        try {
            if (!Wait.isElementDisplayed(driver, addNoteField, 30)) {
                Debugger.println("AddNoteField not displayed.\n" + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("fillInAddNotesField.jpg");
                return false;
            }
            addNoteField.clear();
            seleniumLib.sendValue(addNoteField, updateNotes);
            return true;
        } catch (Exception exp1) {
            Debugger.println("Exception from fillInAddNotesField." + exp1 + "\n" + driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("fillInAddNotesField.jpg");
            return false;
        }
    }

    public boolean verifyNotesDetails(String expectedValue) {
        try {
            if (!Wait.isElementDisplayed(driver, addNoteField, 30)) {
                Debugger.println("AddNoteField not displayed.\n" + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("verifyAddNotesField.jpg");
                return false;
            }
            String actValue = seleniumLib.getText(addNoteField);
            if (!actValue.equalsIgnoreCase(expectedValue)) {
                Debugger.println("Expected :" + expectedValue + "Actual:" + actValue);
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyAddNotesField." + exp + "\n" + driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("verifyAddNotesField.jpg");
            return false;
        }
    }

    public void fillInAddNotesFieldWithOverThreeThousandCharacters(int dataSize) {
        fillInValue(addNoteField, faker.number().digits(dataSize));
        Wait.seconds(2);
    }

    public boolean verifyNotesPageWarningMessage(String message) {
        try {
            boolean isPresent = false;
            for (int i = 0; i < warningMessages.size(); i++) {
                if (warningMessages.get(i).getText().equalsIgnoreCase(message)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Expected Warning Message not displayed in Notes Page." + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("NotesPageWarning.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyNotesPageWarningMessage ." + driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("NotesPageWarning.jpg");
            return false;
        }
    }

    public boolean verifyInfoMessageOnNotesPage(String infoMessage) {
        try {
            boolean isPresent = false;
            for (int i = 0; i < infoMessages.size(); i++) {
                if (infoMessages.get(i).getText().equalsIgnoreCase(infoMessage)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Expected Info Message not displayed in Notes Page." + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("NotesPageInfo.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyInfoMessageOnNotesPage ." + driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("NotesPageInfo.jpg");
            return false;
        }
    }

    public boolean validateTextInformation(String textInfo){
        try{
            //1. heck the list of text information contains the given text
            String info = "";
            boolean isPresent = false;
            for (int i = 0; i < textInformation.size(); i++) {
                info = textInformation.get(i).getText();
                Debugger.println("Message: "+info);
                if (info.equalsIgnoreCase(textInfo)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                SeleniumLib.takeAScreenShot("NotesPageInfo.jpg");
                Assert.fail("Expected Message:"+textInfo+" not present in the Notes Page");
            }
            return isPresent;
        }catch(Exception exp){
            return false;
        }
    }
}