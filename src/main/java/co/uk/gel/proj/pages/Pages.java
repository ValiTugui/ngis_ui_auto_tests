package co.uk.gel.proj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import co.uk.gel.config.SeleniumDriver;

public class Pages {

    protected WebDriver driver;

    //We have to initialize all the Pages Created in this class. AppHomePage provided as an example.
    protected HomePage homePage;
    protected AppHomePage appHomePage;
    protected PatientSearchPage patientSearchPage;
    protected PatientDetailsPage patientDetailsPage;

    public Pages(SeleniumDriver driver) {
        this.driver = driver;
        PageObjects();
    }

    public void PageObjects() {

        homePage= PageFactory.initElements(driver,HomePage.class);
        appHomePage = PageFactory.initElements(driver, AppHomePage.class);
        patientSearchPage = PageFactory.initElements(driver,PatientSearchPage.class);
        patientDetailsPage = PageFactory.initElements(driver,PatientDetailsPage.class);
    }
}//end class
