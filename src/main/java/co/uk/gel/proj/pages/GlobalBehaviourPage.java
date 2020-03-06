package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "//h1[contains(@class,'css-')]")
    public WebElement privacyPolicyPageTitle;

    @FindBy(name = "loginfmt")
    public WebElement emailAddressField;

    @FindBy(name = "passwd")
    public WebElement passwordField;

    @FindBy(css = "input[type*='submit']")
    public WebElement nextButton;

    @FindBy(xpath = "//div[@id='referral__header']")
    public WebElement referralHeaderBanner;

    @FindBy(xpath = "//li[contains(@class,'styles_text_')]")
    List<WebElement> referralHeaderDetails;

    @FindBy(xpath = "//button[text()='Continue']")
    public WebElement continueButtonOnLandingPage;


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
            Pages.login(this.driver, emailAddressField, passwordField, nextButton);
        }
    }

    public boolean verifyTheContinueButtonOnLandingPage() {
        try {
            if (!Wait.isElementDisplayed(driver, continueButtonOnLandingPage, 30)) {
                Debugger.println("Continue button is not present on landing page");
                SeleniumLib.takeAScreenShot("ContinueButtonNotPresent.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying verifyTheContinueButtonOnLandingPage:" + exp);
            SeleniumLib.takeAScreenShot("ContinueButtonOnLandingPage");
            return false;
        }
    }

    public boolean verifyReplacedLabelsInTheCurrentPage(String shouldNotPresent, String shouldPresent) {
        try {
            //Searching in the whole page body, the presence and non-presence of earlier and replaced nhs labels
            String bodyText = driver.findElement(By.tagName("body")).getText();
            String[] notExpectedLabels = shouldNotPresent.split(",");
            String[] expectedLabels = shouldPresent.split(",");
            //Checking for Non-Presence
            for (int i = 0; i < notExpectedLabels.length; i++) {
                if (bodyText.contains(notExpectedLabels[i])) {
                    Debugger.println("Page contains Unexpected Label/Text :" + notExpectedLabels[i]);
                    SeleniumLib.takeAScreenShot("NHSLabelPresence.jpg");
                    return false;
                }
            }
            //Checking for Presence
            boolean isPresent = false;
            for (int i = 0; i < expectedLabels.length; i++) {
                if (bodyText.contains(notExpectedLabels[i])) {
                    isPresent = true;
                }
            }
            if (!isPresent) {
                Debugger.println("Page not contains Expected Label/Text :" + shouldPresent);
                SeleniumLib.takeAScreenShot("NHSLabelNonPresence.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception in verifyNHSLabelstInTheCurrentPage " + exp);
            SeleniumLib.takeAScreenShot("NHSLabelPresence.jpg");
            return false;
        }
    }

}//end
