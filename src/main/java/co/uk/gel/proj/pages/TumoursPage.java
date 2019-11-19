package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.TestDataProvider.NgisPatientOne;
import co.uk.gel.proj.TestDataProvider.SpinePatientOne;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import co.uk.gel.lib.SeleniumLib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TumoursPage {

    WebDriver driver;
    NewPatient tumourDetails = new NewPatient();
    Faker faker = new Faker();
    SeleniumLib seleniumLib;

    public TumoursPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
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
    public WebElement tumourInformationText_withNotificationSuccess;

    @FindBy(xpath = "(//div[contains(@class,'tumour-list__sub-title')])[1]")
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

    @FindBy(xpath = "//p[contains(@class,'hint__text')]")
    public List<WebElement> hintText;

    @FindBy(css = "button[class*='link-button']")
    public WebElement backLinkButton;

    @FindBy(xpath = "//th[contains(text(),'Working diagnosis/morphology')]" )
    public WebElement snomedCTWorkingDiagnosisLabel;


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

    public void answerTumourDiagnosisQuestionsBasedOnTumourType(String tumourType, String diagnosis) {

        switch (tumourType) {
            case "Solid tumour: metastatic": {
                Actions.fillInValue(topographyOfPrimaryTumourField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                Actions.fillInValue(topographyOfThisMetastaticDepositField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                Actions.fillInValue(workingDiagnosisMorphologyField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                break;
            }
            case "Solid tumour: primary": {
                Actions.fillInValue(topographyOfPrimaryTumourField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                Actions.fillInValue(workingDiagnosisMorphologyField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                break;
            }
            case "Solid tumour: unknown": {
                Actions.fillInValue(topographyOfThisMetastaticDepositField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                Actions.fillInValue(workingDiagnosisMorphologyField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                break;
            }
            case "Brain tumour": {
                Actions.fillInValue(topographyOfThisMetastaticDepositField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                Actions.fillInValue(workingDiagnosisMorphologyField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                break;
            }

            case "Haematological malignancy: liquid sample": {
                Actions.fillInValue(workingDiagnosisMorphologyField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                break;
            }

            case "Haematological malignancy: solid sample": {
                Actions.fillInValue(workingDiagnosisMorphologyField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid tumour type");
        }
    }

    public boolean newTumourIsDisplayedInLandingPage(int i) {
        Wait.forElementToBeDisplayed(driver, successNotification);
        Wait.forElementToBeDisplayed(driver, tumoursLandingPageTable);
        int numberOfTumours = tumoursLandingPageList.size() - 1;
        Assert.assertEquals(i, numberOfTumours);
        return true;
    }

    public int getTheNumbersOfTumoursDisplayedInLandingPage() {
        Wait.forElementToBeDisplayed(driver, successNotification);
        Wait.forElementToBeDisplayed(driver, tumoursLandingPageTable);
        return listOfTumoursInTheTable.size();
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


    public List<String> getTumourTableHeaders() {
        List<String> actualTableHeaders = new ArrayList<>();
        for (WebElement header : tumourTableHeaders) {
            actualTableHeaders.add(header.getText().trim());
        }
        return actualTableHeaders;
    }


    public List<String> getInformationTextOnEditTumourPage() {
        List<String> actualInformationText = new ArrayList<>();
        for (WebElement header : tumourInformationTextList) {
            actualInformationText.add(header.getText().trim());
        }
        return actualInformationText;
    }

    public void clickEditTumourArrow() {
        Wait.forElementToBeDisplayed(driver, tumoursLandingPageTable);
        Wait.seconds(3);
        Actions.clickElement(driver, editTumourArrow);
    }

    public void editTumourDescription() {
        Wait.forElementToBeDisplayed(driver, descriptiveName);
        Actions.clearTextField(descriptiveName);
        fillInTumourDescription();
    }

    public void editDateOfDiagnosis() {
        Actions.clearField(dateDay);
        Actions.clearField(dateMonth);
        Actions.clearField(dateYear);
        fillInDateOfDiagnosis();
    }

    public void editSpecimenID() {
        Actions.clearTextField(pathologyReportId);
        fillInSpecimenID();
    }

    public List<String> getExpectedTumourTestDataForAddATumourPage() {
        String dayFormatToDoubleDigit;
        List<String> expectedTumourTestData = new ArrayList<>();

        expectedTumourTestData.add(tumourDetails.getTumourDescription());
        expectedTumourTestData.add(tumourDetails.getTumourSpecimenID());

        // set day format from "d" 1 to "dd" 01
        int result = Integer.parseInt(tumourDetails.getDay());
        if (result < 10) {
            dayFormatToDoubleDigit = "0" + tumourDetails.getDay();
        } else {
            dayFormatToDoubleDigit = tumourDetails.getDay();
        }
        String expectedDateOfDiagnosed = dayFormatToDoubleDigit + "-" + TestUtils.convertMonthNumberToMonthForm(tumourDetails.getMonth()) + "-" + tumourDetails.getYear();

        expectedTumourTestData.add(expectedDateOfDiagnosed);
        expectedTumourTestData.add(tumourDetails.getTumourType());

        Debugger.println("Method Expected TumourTestData : " + expectedTumourTestData);
        return expectedTumourTestData;

    }

    public List<String> getTheTumourDetailsOnTableList() {

        Wait.forElementToBeDisplayed(driver, tumoursLandingPageTable);
        List<String> actualTumourTestData = new ArrayList<>();
        for (WebElement tumourDetails : newlyAddedTumourDetailsList) {
            actualTumourTestData.add(tumourDetails.getText().trim());
        }
        Debugger.println("Method Actual TumourTestData " + actualTumourTestData);
        return actualTumourTestData;
    }

    public List<String> getTheTumourDetailsOnEditATumourPage() {

        Wait.forElementToBeDisplayed(driver, descriptiveName);
        List<String> actualTumourDetails = new ArrayList<>();

        actualTumourDetails.add(Actions.getValue(descriptiveName));
        actualTumourDetails.add(Actions.getValue(dateDay));
        actualTumourDetails.add(Actions.getValue(dateMonth));
        actualTumourDetails.add(Actions.getValue(dateYear));
        actualTumourDetails.add(Actions.getText(tumourType));
        actualTumourDetails.add(Actions.getValue(pathologyReportId));

        Debugger.println("Actual Tumour Details on Edit a Tumour " + actualTumourDetails);
        return actualTumourDetails;
    }

    public List<String> getTheExpectedTumourDetailsForAddATumourPage() {

        List<String> expectedTumourTestData = new ArrayList<>();

        expectedTumourTestData.add(tumourDetails.getTumourDescription());
        expectedTumourTestData.add(tumourDetails.getDay());
        expectedTumourTestData.add(tumourDetails.getMonth());
        expectedTumourTestData.add(tumourDetails.getYear());
        expectedTumourTestData.add(tumourDetails.getTumourType());
        expectedTumourTestData.add(tumourDetails.getTumourSpecimenID());

        Debugger.println("Expected Test data " + expectedTumourTestData);
        return expectedTumourTestData;
    }

    public String successNotificationIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, successNotification);
        return Actions.getText(successNotification);
    }

    public List<String> getTheTumourFieldsLabelsOnAddATumourPage() {

        Wait.forElementToBeDisplayed(driver, descriptiveName);
        List<String> expectedTumourFieldsLabels = new ArrayList<>();

        expectedTumourFieldsLabels.add(Actions.getText(descriptiveNameLabel));
        expectedTumourFieldsLabels.add(Actions.getText(dateOfDiagnosisLabel));
        expectedTumourFieldsLabels.add(Actions.getText(PathologyIdOrSampleIdLabel));
        expectedTumourFieldsLabels.add(Actions.getText(tumourTypeLabel));

        Debugger.println("Expected Tumour Fields-Labels " + expectedTumourFieldsLabels);
        return expectedTumourFieldsLabels;
    }

    public String getDynamicQuestionsSnomedCTLabelText(){
        Wait.forElementToBeDisplayed(driver,snomedCTWorkingDiagnosisLabel);
        return  snomedCTWorkingDiagnosisLabel.getText();
    }


}
