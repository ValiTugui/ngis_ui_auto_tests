package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MiSearchLSIDPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    public static NewPatient testData = new NewPatient();
    static Faker faker = new Faker();
    SeleniumLib seleniumLib;

    public MiSearchLSIDPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    // mi-portal
    @FindBy(xpath = "//a[@data-value='file_submissions']")
    public WebElement fileSubmissionLnk;

}

