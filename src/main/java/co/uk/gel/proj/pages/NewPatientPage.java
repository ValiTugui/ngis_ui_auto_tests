package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class NewPatientPage {

	WebDriver driver;


	public WebElement dateOfBirth;
	public WebElement nhsNumber;

	public NewPatientPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void newPatientPageIsDisplayed() {
		Wait.forURLToContainSpecificText(driver, "/new-patient");
	}

	public void nhsNumberAndDOBFieldsArePrePopulatedInNewPatientPage() {
		String DOB = PatientSearchPage.testData.getDay()  + "/" + PatientSearchPage.testData.getMonth() + "/" + PatientSearchPage.testData.getYear();
		Debugger.println("Expected DOB : " + DOB + " : " + "Actual DOB" + Actions.getValue(dateOfBirth));
		Assert.assertEquals(DOB, Actions.getValue(dateOfBirth));
	}
}

