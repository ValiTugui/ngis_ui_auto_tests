package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PedigreePage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//span[@id='action-saveAndExit']")
    public WebElement saveAndExitButton;

    public PedigreePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public void saveAndExitPedigree(){
        saveAndExitButton.click();
    }
}//end
