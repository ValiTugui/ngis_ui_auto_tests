package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
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

    public NotesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

     public boolean fillNotes(String notes) {
        try {
              notesArea.sendKeys(notes);
              return true;
        }catch(Exception exp){
            Debugger.println("Exception in Filling fillNotes: "+exp);
            SeleniumLib.takeAScreenShot("Notes.jpg");
            return false;
        }
    }
    @FindBy(xpath = "//textarea[@id='notes']")
    public WebElement notesArea;

    @FindBy(name = "notes")
    public WebElement addNoteField;

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

    public WebElement getNotesFieldLocator() {
        return addNoteField;
    }
}
