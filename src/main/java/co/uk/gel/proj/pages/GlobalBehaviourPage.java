package co.uk.gel.proj.pages;

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

    @FindBy(xpath = "//div [contains (@class, 'styles_contentContainer__3xbAF')][2]")
    public WebElement footerText;

    public boolean isNGISVersionPresent() {
        String[] footer = footerText.getText().split("\\r?\\n");
        String version= footer[4];
        Debugger.println("NGIS Version from Application is "+ version);
        return version.matches(AppConfig.getNGISVersion());
    }

}
