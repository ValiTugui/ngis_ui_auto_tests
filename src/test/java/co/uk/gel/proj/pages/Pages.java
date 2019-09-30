package co.uk.gel.proj.pages;

import co.uk.gel.proj.pages.AppHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import co.uk.gel.config.SeleniumDriver;

public class Pages {

    protected WebDriver driver;

    //We have to initialize all the Pages Created in this class. AppHomePage provided as an example.
    protected AppHomePage appHomePage;
    protected PatientSearchPage patientSearchPage;

    public Pages(SeleniumDriver driver) {
        this.driver = driver;
        PageObjects();
    }

    public void PageObjects() {

        appHomePage = PageFactory.initElements(driver, AppHomePage.class);
        patientSearchPage = PageFactory.initElements(driver,PatientSearchPage.class);
    }
}//end class
