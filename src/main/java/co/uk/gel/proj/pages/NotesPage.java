package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.security.ssl.Debug;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {

    WebDriver driver;
    SeleniumLib seleniumLib;
    Faker faker = new Faker();

    @FindBy(name = "notes")
    public WebElement addNoteField;

    @FindBy(xpath="//p[contains(text(),'Max. character count')]")
    public WebElement infoMessageForCharacters;

    public NotesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public String getText(WebElement element) {
        Wait.forElementToBeDisplayed(driver, element);
        return Actions.getText(element);
    }

    public void fillInValue(WebElement element, String value) {
        Wait.forElementToBeDisplayed(driver, element);
        element.clear();
        element.sendKeys(value);
    }

    public void fillInAddNotesField() {
        fillInValue(addNoteField, faker.chuckNorris().fact());
    }

    public void fillInAddNotesFieldWithOverThreeThousandCharacters() {
        fillInValue(addNoteField, faker.number().digits(3010) );
    }

    public WebElement getNotesFieldLocator() {
        return addNoteField;
    }

    public boolean verifyInfoMessageOnNotesPage(String infoMessage) {
        try {
            Wait.forElementToBeDisplayed(driver, addNoteField);
            if(!Wait.isElementDisplayed(driver,infoMessageForCharacters,10)){
                Debugger.println("Notes Info Message not displayed.");
                SeleniumLib.takeAScreenShot("NoteInfoMessage.jpg");
                return false;
            }
            String actualMessage = infoMessageForCharacters.getText();
            if (!actualMessage.equalsIgnoreCase(infoMessage)) {
                Debugger.println("Expected Message: " + infoMessage + ", but Actual Message is: " + actualMessage);
                SeleniumLib.takeAScreenShot("NoteInfoMessage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Notes Page: Max Characters Information Message :not found " + exp);
            SeleniumLib.takeAScreenShot("NoteInfoMessage.jpg");
            return false;
        }
    }

    public boolean checkTheErrorMessageForMaxCharacters(String errorMessage, String fontColor) {
        try {
            //Verify the Message Content
            Wait.forElementToBeDisplayed(driver, addNoteField);

            if(!Wait.isElementDisplayed(driver,infoMessageForCharacters,10)){
                Debugger.println("Notes Info Message not displayed.");
                SeleniumLib.takeAScreenShot("NoteInfoMessage.jpg");
                return false;
            }
            //Verify the Message Color
            String expectedFontColor = StylesUtils.convertFontColourStringToCSSProperty(fontColor);
            String actColor = infoMessageForCharacters.getCssValue("color");
            if (!expectedFontColor.equalsIgnoreCase(actColor)) {
                Debugger.println("Expected Notes info message color:"+expectedFontColor+" Not displayed.");
                SeleniumLib.takeAScreenShot("NoteInfoMessage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from validating Error Message " + exp);
            SeleniumLib.takeAScreenShot("NoteInfoMessage.jpg");
            return false;
        }
    }
}
