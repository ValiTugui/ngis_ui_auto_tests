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

public class NotesPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//textarea[@id='notes']")
    public WebElement notesArea;

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

}//end
