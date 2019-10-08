package co.uk.gel.proj.pages;

import co.uk.gel.lib.Wait;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PatientDetailsPage {

	WebDriver driver;


    /*public PatientDetailsPage(SeleniumDriver driver) {
        super(driver);
    }*/

      public PatientDetailsPage (WebDriver driver) {
          this.driver = driver;
          PageFactory.initElements(driver, this);
      }




	@FindBy(xpath = "//button[contains(@class,'submit-button') and @type='button']")
	public WebElement startReferralButton;



    public void patientDetailsPageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "/patient-details");
        Wait.forElementToBeDisplayed(driver, startReferralButton);
    }


}
