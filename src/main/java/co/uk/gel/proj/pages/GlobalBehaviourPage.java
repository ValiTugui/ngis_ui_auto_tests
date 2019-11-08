package co.uk.gel.proj.pages;

import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import com.github.javafaker.App;
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


    public String getNGISVersion() {
        driver.get(AppConfig.getTo_NGISVerion_url());
        String[] a = versionNumber.getText().split("-");
        String expectedVerion = "NGIS TOMS " + a[0];
        driver.quit();
        return expectedVerion;
    }

    public boolean isNGISVersionPresent() {
        String[] footer = footerText.getText().split("\\r?\\n");
        String actualVersion = footer[4];
        Debugger.println("NGIS Version from Application is " + actualVersion);
        return actualVersion.matches(AppConfig.getNGISVersion());
    }
}
