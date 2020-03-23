package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.SeleniumLib;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MiNewReferralsPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiNewReferralsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }


    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box-header']")
    public WebElement searchBoxHeader;


}