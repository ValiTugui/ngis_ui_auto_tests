package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.util.Debugger;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class TumoursPage {

    WebDriver driver;
    NewPatient tumourDetails = new NewPatient();
    Faker faker = new Faker();

    public TumoursPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement descriptiveName;
    public WebElement dateDay;
    public WebElement dateMonth;
    public WebElement dateYear;
    public WebElement pathologyReportId;

    @FindBy(css = "input[id*='descriptiveName']")  //added
    public List<WebElement> descriptiveNameList;

    @FindBy(css = "label[for*='descriptiveName']")
    public WebElement descriptiveNameLabel;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(css = "div[id*='react-select']")
    public List<WebElement> dropdownValues;

    @FindBy(xpath = "//label[contains(@for,'tumourType')]//following::div")
    public WebElement tumourType;

    @FindBy(xpath = "//table[contains(@class,'table')]//child::tbody")
    public WebElement tumoursLandingPageTable;

    @FindBy(xpath = "//table[contains(@class,'table')]//child::tr")
    public List<WebElement> tumoursLandingPageList;

    @FindBy(css = "*[class*='tumour-list__notification']")
    public WebElement successNotification;

    @FindBy(xpath = "//*[contains(@id,'question-id-q151')]")
    public WebElement tumourCoreDataDropdown;

    @FindBy(xpath = "//*[contains(@id,'question-id-q155')]//child::input")
    public WebElement topographyOfPrimaryTumourField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q161')]//child::input")
    public WebElement topographyOfThisMetastaticDepositField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q160')]//child::input")
    public WebElement workingDiagnosisMorphologyField;

    @FindBy(css = "*[class*='tumour-list__warning']")
    public List<WebElement> tumoursWarningMessage;

    @FindBy(xpath = "//*[contains(@class,'add-sample__confirm-table')]//child::td")
    public List<WebElement> tumourDetailsValuesFromAddSamplePage;

    @FindBy(css = "*[class*='error-message__text']")
    public List<WebElement> errorMessages;

    @FindBy(xpath = "//button[text()='add a new tumour']")
    public WebElement addAnotherTumourLink;

    @FindBy(xpath = "//button[text()='add a new tumour']")  //added
    public List<WebElement> addAnotherTumourLinkList;

    @FindBy(css = "*[class*='checkbox-row__arrow']")
    public WebElement editTumourArrow;



    public void navigateToAddTumourPageIfOnEditTumourPage(){

        if (descriptiveNameList.size()>0) {
            Debugger.println("User is on Add Tumour Page");
        } else if (addAnotherTumourLinkList.size() > 0) {
            Debugger.println("User is on Edit Tumour Page");
            addAnotherTumourLink.click();
            Debugger.println("User is NOW on Add Tumour Page");
        }else {
            Debugger.println("User is not on tumour page");
        }

    }

    public String fillInTumourDescription() {
        Wait.forElementToBeDisplayed(driver, descriptiveName);
        String description = faker.name().lastName();
        tumourDetails.setTumourDescription(description);
        Actions.fillInValue(descriptiveName, description);
        return description;
    }

    public void fillInDateOfDiagnosis (String dayOfDiagnosis, String monthOfDiagnosis, String yearOfDiagnosis) {

        dateDay.sendKeys(dayOfDiagnosis);
        dateMonth.sendKeys(monthOfDiagnosis);
        dateYear.sendKeys(yearOfDiagnosis);
    }

    public void fillInDateOfDiagnosis() {
        tumourDetails.setDay(String.valueOf(faker.number().numberBetween(1, 31)));
        tumourDetails.setMonth(String.valueOf(faker.number().numberBetween(1, 12)));
        tumourDetails.setYear(String.valueOf(faker.number().numberBetween(2018, 2019)));
        Actions.fillInValue(dateDay, tumourDetails.getDay());
        Actions.fillInValue(dateMonth, tumourDetails.getMonth());
        Actions.fillInValue(dateYear, tumourDetails.getYear());
    }

    public String selectTumourType(String type) {

       // Wait.forElementToBeDisplayed(driver, tumourType);
       Wait.forElementToBeClickable(driver, tumourType);
        Actions.clickElement(driver, tumourType);
       // Wait.forElementToBeDisplayed(driver, dropdownValue);
       // Wait.forElementToBeClickable(driver,dropdownValue);
        Actions.selectValueFromDropdown(dropdownValue, type);
        tumourDetails.setTumourType(type);
        return Actions.getText(tumourType);
    }

    public String fillnSpecimenID() {
        String ID = faker.numerify("N#####");
        Actions.fillInValue(pathologyReportId, ID);
        tumourDetails.setTumourSpecimenID(ID);
        return ID;
    }

    public String selectTumourFirstPresentationOrOccurrenceValue(String value) {
        Wait.forElementToBeDisplayed(driver, tumourCoreDataDropdown);
        Actions.clickElement(driver, tumourCoreDataDropdown);
        Actions.selectValueFromDropdown(dropdownValue, value);
        return value;
    }

    public void answerTumourDiagnosisQuestions(String diagnosis) {
        Actions.fillInValue(topographyOfPrimaryTumourField, diagnosis);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectRandomValueFromDropdown(dropdownValues);
        Actions.fillInValue(topographyOfThisMetastaticDepositField, diagnosis);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectRandomValueFromDropdown(dropdownValues);
        Actions.fillInValue(workingDiagnosisMorphologyField, diagnosis);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectRandomValueFromDropdown(dropdownValues);
    }

    public void newTumourIsDisplayedInLandingPage(int i) {
        Wait.forElementToBeDisplayed(driver, successNotification);
        Wait.forElementToBeDisplayed(driver, tumoursLandingPageTable);
        int numberOfTumours = tumoursLandingPageList.size() - 1;
        Assert.assertEquals(i, numberOfTumours);
    }

    public void tumourIsNotHighlighted() {
        Wait.forElementToBeDisplayed(driver, successNotification);
        Assert.assertTrue(!tumoursLandingPageList.get(1).getAttribute("class").contains("row--warning"));
    }

    public void warningMessageIsNotDisplayed() {
        Assert.assertTrue(tumoursWarningMessage.size() == 0);
    }

  /*
    public void fillInDateOfDiagnosisOnAddOrTumourPage (String dayOfDiagnosis, String monthOfDiagnosis, String yearOfDiagnosis)  {


        if (descriptiveNameList.size()>0) {
            Debugger.println("User is on Add Tumour Page");
            fillInDateOfDiagnosis(dayOfDiagnosis, monthOfDiagnosis, yearOfDiagnosis);
        } else if (addAnotherTumourLinkList.size() > 0) {
            Debugger.println("User is on Edit Tumour Page");
            addAnotherTumourLink.click();
            fillInDateOfDiagnosis(dayOfDiagnosis, monthOfDiagnosis, yearOfDiagnosis);
        }else {
            Debugger.println("User is not on tumour page");
        }
    }
*/

}
