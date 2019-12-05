package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {

    WebDriver driver;
    Faker faker = new Faker();
    public NotesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class*='notification__text']")
    public WebElement notificationBanner;

    @FindBy(css = "p[class*='notes__helper']")
    public WebElement helpText;

    @FindBy(css = "div[class*='notes__example']")
    public WebElement notesExamples;

    @FindBy(css = "label[for*='notes']")
    public WebElement addNoteLabel;

    @FindBy(name = "notes")
    public WebElement addNoteField;

    @FindBy(css = "p[class*='notes__count']")
    public WebElement notesCountText;

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


}
