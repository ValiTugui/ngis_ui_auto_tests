package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.util.Debugger;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
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

    @FindBy(css = "h1[class*='page-title']")
    public WebElement AddATumourPageTitle;

    @FindBy(css = "p[class*='subtitle']")
    public WebElement TumourSubTitle;

    @FindBy(css = "input[id*='descriptiveName']")  //added
    public List<WebElement> descriptiveNameList;

    @FindBy(css = "label[for*='descriptiveName']")
    public WebElement descriptiveNameLabel;

    @FindBy(xpath = "//legend[text()='Date of diagnosis']")
    public WebElement dateOfDiagnosisLabel;

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(css = "div[id*='react-select']")
    public List<WebElement> dropdownValues;

    @FindBy(css = "label[for*='tumourType']")
    public WebElement tumourTypeLabel;

    @FindBy(xpath = "//label[contains(@for,'tumourType')]//following::div")
    public WebElement tumourType;

    @FindBy(css = "label[for*='pathologyReportId']")
    public WebElement PathologyIdOrSampleIdLabel;

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

    @FindBy(xpath = "//div[contains(@class,'notification--success')]/div[2]")
    public WebElement tumourSuccessNotification;

    @FindBy(xpath = "//div[contains(@class,'notification--success')]/../div[2]")
    public WebElement tumourInformationText;

    @FindBy(css = "div[class*='tumour-list__sub-title']")
    public List<WebElement> tumourInformationTextList;

    @FindBy(xpath = "(//div[contains(@class,'tumour-list__sub-title')])[3]")
    public WebElement addANewTumourTextInformation;

    @FindBy(xpath = "//table/thead/tr/th[text()!='']")
    public List<WebElement> tumourTableHeaders;

    @FindBy(xpath = "//span[contains(@class,'checkmark-text__checkmark--checked')]")
    public WebElement checkedRadioButton;

    @FindBy(xpath = "//table//tbody/tr[last()]/th/div/div |//table//tbody/tr[last()]/td[text()!='']")
    public List<WebElement> newlyAddedTumourDetailsList;

    @FindBy(xpath = "//table//tbody/tr")
    public List<WebElement> listOfTumoursInTheTable;


    public void navigateToAddTumourPageIfOnEditTumourPage() {

        if (descriptiveNameList.size() > 0) {
            Debugger.println("User is on Add Tumour Page");
        } else if (addAnotherTumourLinkList.size() > 0) {
            Debugger.println("User is on Edit Tumour Page");
            addAnotherTumourLink.click();
            Debugger.println("User is NOW on Add Tumour Page");
        } else {
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

    public void fillInDateOfDiagnosis(String dayOfDiagnosis, String monthOfDiagnosis, String yearOfDiagnosis) {

        dateDay.sendKeys(dayOfDiagnosis);
        dateMonth.sendKeys(monthOfDiagnosis);
        dateYear.sendKeys(yearOfDiagnosis);
    }

    public void clearDateOfDiagnosisFields() {
        Actions.clearField(dateDay);
        Actions.clearField(dateMonth);
        Actions.clearField(dateYear);
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
        Wait.forElementToBeClickable(driver, tumourType);
        Actions.clickElement(driver, tumourType);
        Wait.forElementToBeClickable(driver, dropdownValue);
        Actions.selectValueFromDropdown(dropdownValue, type);
        tumourDetails.setTumourType(type);
        return Actions.getText(tumourType);
    }

    public String fillInSpecimenID() {
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

    public boolean newTumourIsDisplayedInLandingPage(int i) {
        Wait.forElementToBeDisplayed(driver, successNotification);
        Wait.forElementToBeDisplayed(driver, tumoursLandingPageTable);
        int numberOfTumours = tumoursLandingPageList.size() - 1;
        Assert.assertEquals(i, numberOfTumours);
        return true;
    }

    public void tumourIsNotHighlighted() {
        Wait.forElementToBeDisplayed(driver, successNotification);
        Assert.assertTrue(!tumoursLandingPageList.get(1).getAttribute("class").contains("row--warning"));
    }

    public void warningMessageIsNotDisplayed() {
        Assert.assertTrue(tumoursWarningMessage.size() == 0);
    }

    public boolean verifyTheElementsOnAddTumoursPageAreDisplayed() {

        AddATumourPageTitle.isDisplayed();
        descriptiveName.isDisplayed();
        dateOfDiagnosisLabel.isDisplayed();
        tumourTypeLabel.isDisplayed();
        PathologyIdOrSampleIdLabel.isDisplayed();
        dateMonth.isDisplayed();
        dateYear.isDisplayed();

        return true;
    }


    public List<String> getTumourTableHeaders()
    {
        List<String> actualTableHeaders = new ArrayList<>();
        for(WebElement header : tumourTableHeaders)
        {
            actualTableHeaders.add(header.getText().trim());
        }
        return actualTableHeaders;
    }


    public List<String> getInformationTextOnEditTumourPage()
    {
        List<String> actualInformationText = new ArrayList<>();
        for(WebElement header : tumourInformationTextList)
        {
            actualInformationText.add(header.getText().trim());
        }
        return actualInformationText;
    }

}
