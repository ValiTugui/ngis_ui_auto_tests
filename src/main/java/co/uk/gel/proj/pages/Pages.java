package co.uk.gel.proj.pages;

import org.openqa.selenium.WebDriver;
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
    protected NewPatientPage newPatientPage;
    protected PatientDetailsPage patientDetailsPage;
    protected RequestingOrganisationPage requestingOrganisationPage;
    protected TestPackagePage testPackagePage;
    protected ResponsibleClinicianPage responsibleClinicianPage;
    protected TumoursPage tumoursPage;
    protected SamplesPage samplesPage;

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
        newPatientPage = PageFactory.initElements(driver,NewPatientPage.class);
        patientDetailsPage = PageFactory.initElements(driver,PatientDetailsPage.class);
        requestingOrganisationPage = PageFactory.initElements(driver,RequestingOrganisationPage.class);
        testPackagePage = PageFactory.initElements(driver,TestPackagePage.class);
        responsibleClinicianPage = PageFactory.initElements(driver,ResponsibleClinicianPage.class);
        tumoursPage = PageFactory.initElements(driver,TumoursPage.class);
        samplesPage = PageFactory.initElements(driver,SamplesPage .class);
    }
}//end class
