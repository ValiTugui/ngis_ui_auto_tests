package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GlobalBehaviourPage {

    WebDriver driver;

    public GlobalBehaviourPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(),'NGIS TOMS')]")
    public WebElement footerText;

    @FindBy(className = "lead")
    public WebElement versionNumber;

    @FindBy(xpath = "//*[contains(@class,'styles_footerLinks')]")
    public List<WebElement> footerLinks;

    @FindBy(xpath = "//*[contains(@class,'styles_static-content__header')]")
    public WebElement privacyPolicyPageTitle;

    @FindBy(name = "loginfmt")
    public WebElement emailAddressField;

    @FindBy(name = "passwd")
    public WebElement passwordField;

    @FindBy(css = "input[type*='submit']")
    public WebElement nextButton;

    public void getNGISVersion() {
        driver.get(AppConfig.getTo_NGISVerion_url());
        String[] a = versionNumber.getText().split("-");
        String expectedVerion = "NGIS TOMS " + a[0];
        AppConfig.properties.setProperty("NGIS_Version_Number", expectedVerion);
    }

    public boolean isNGISVersionPresent() {
        String[] footer = footerText.getText().split("\\r?\\n");
        String actualVersion = footer[4];
        Debugger.println("NGIS Version from Application is " + actualVersion);
        return actualVersion.matches(AppConfig.getNGISVersion());
    }

    public boolean isPrivacyPolicyLinkPresent(String expectedText) {
        String[] footer = footerText.getText().split("\\r?\\n");
        String actualText = footer[2];
        return actualText.matches(expectedText);
    }

    public boolean checkPrivacyPolicyLinkPage(String pageTitle) {
        return privacyPolicyPageTitle.getText().matches(pageTitle);
    }

    public void clickPrivacyPolicy() {
        Click.element(driver, footerLinks.get(1));
        Actions.switchTab(driver);
        if (!(driver.getCurrentUrl().contains("privacy-policy"))) {
            Pages.login(this.driver, emailAddressField, passwordField, nextButton );
        }
    }

}
