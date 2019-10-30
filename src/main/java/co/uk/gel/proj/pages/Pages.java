package co.uk.gel.proj.pages;

import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import co.uk.gel.config.SeleniumDriver;

public class Pages {

    protected WebDriver driver;

    //We have to initialize all the Pages Created in this class. AppHomePage provided as an example.
    protected HomePage homePage;
    protected ClinicalIndicationsTestSelectPage clinicalIndicationsTestSelect;
    protected PaperFormPage paperFormPage;
    protected ReferralPage referralPage;
    protected AppHomePage appHomePage;
    protected PatientSearchPage patientSearchPage;
    protected PatientDetailsPage patientDetailsPage;
    protected RequestingOrganisationPage requestingOrganisationPage;
    protected TestPackagePage testPackagePage;
    protected ResponsibleClinicianPage responsibleClinicianPage;
    protected TumoursPage tumoursPage;
    protected SamplesPage samplesPage;
    protected DashBoardPage dashBoardPage;

    public Pages(SeleniumDriver driver) {
        this.driver = driver;
        PageObjects();
    }

    public void PageObjects() {

        homePage= PageFactory.initElements(driver,HomePage.class);
        clinicalIndicationsTestSelect = PageFactory.initElements(driver,ClinicalIndicationsTestSelectPage.class);
        paperFormPage = PageFactory.initElements(driver,PaperFormPage.class);
        referralPage = PageFactory.initElements(driver,ReferralPage.class);
        appHomePage = PageFactory.initElements(driver, AppHomePage.class);
        patientSearchPage = PageFactory.initElements(driver,PatientSearchPage.class);
        patientDetailsPage = PageFactory.initElements(driver,PatientDetailsPage.class);
        requestingOrganisationPage = PageFactory.initElements(driver,RequestingOrganisationPage.class);
        testPackagePage = PageFactory.initElements(driver,TestPackagePage.class);
        responsibleClinicianPage = PageFactory.initElements(driver,ResponsibleClinicianPage.class);
        tumoursPage = PageFactory.initElements(driver,TumoursPage.class);
        samplesPage = PageFactory.initElements(driver,SamplesPage .class);
        dashBoardPage = PageFactory.initElements(driver,DashBoardPage .class);
    }
    public static void login(WebDriver driver, WebElement emailAddressField, WebElement passwordField, WebElement nextButton) {
        Wait.forElementToBeClickable(driver, emailAddressField);
        emailAddressField.sendKeys(AppConfig.getApp_username());
        nextButton.click();
        Wait.seconds(2);
        Wait.forElementToBeClickable(driver, passwordField);
        passwordField.sendKeys(AppConfig.getApp_password());
        nextButton.click();
    }
}//end class
