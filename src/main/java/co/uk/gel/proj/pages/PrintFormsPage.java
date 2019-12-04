package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PrintFormsPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public PrintFormsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }
}//end
