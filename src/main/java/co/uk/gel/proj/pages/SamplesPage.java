package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
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

import java.util.ArrayList;
import java.util.List;

public class SamplesPage {

    WebDriver driver;
    Faker faker = new Faker();
    NewPatient sampleDetails = new NewPatient();
    SeleniumLib seleniumLib;


    public SamplesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(css = "div[id*='react-select']")
    public WebElement dropdownValue;

    @FindBy(css = "div[id*='react-select']")
    public List<WebElement> dropdownValues;

    @FindBy(xpath = "//label[contains(@for,'sampleType')]//following::div")
    public WebElement sampleType;

    @FindBy(xpath = "//label[contains(@for,'sampleState')]//following::div")
    public WebElement sampleState;

    @FindBy(xpath = "//*[contains(@id,'question-id-q323')]//child::input")
    public WebElement sampleTopographyField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q324')]//child::input")
    public WebElement sampleMorphologyField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q321')]")
    public WebElement percentageOfMalignantNucleiField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q326')]")
    public WebElement numberOfSlidesField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q328')]")
    public WebElement sampleCollectionDateField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q327')]")
    public WebElement sampleCommentsField;

    public WebElement labId;

    @FindBy(css = "*[class*='sample-detail__notification']")
    public WebElement successNotification;

    @FindBy(xpath = "//table[contains(@class,'table')]//child::tbody")
    public WebElement samplesLandingPageTable;

    @FindBy(xpath = "//table[contains(@class,'table')]//child::tr")
    public List<WebElement> samplesLandingPageList;

    @FindBy(css = "*[class*='add-sample__select-error']")
    public WebElement addTumourErrorMessage;

    @FindBy(xpath = "//*[contains(@class,'add-sample__select-error')]//child::a")
    public WebElement addTumourLink;

    @FindBy(css = "*[class*='add-sample__confirm-table']")
    public WebElement tumourDetailsTable;

    @FindBy(xpath = "//*[contains(@class,'add-sample__confirm-table')]//child::td")
    public WebElement tumourDetailsValues;

    @FindBy(css = "*[class*='error-message__text']")
    public List<WebElement> errorMessages;

    @FindBy(xpath = "//*[contains(@class,'add-sample__confirm-table')]//following::a")
    public WebElement notTheRightTumourLink;

    @FindBy(css = "*[class*='sample-detail__edit-link']")
    public WebElement editSampleButton;

    @FindBy(xpath = "//button[text()='Add sample']")
    public WebElement addSampleButton;

    @FindBy(css = "*[class*='checkmark']")
    public WebElement parentSampleCheckbox;

    @FindBy(css = "*[class*='samples-banner']")
    public WebElement emptyLandingPageBanner;

    @FindBy(xpath = "//p[contains(@class,'sample-detail__sub-title')]")
    public List<WebElement> manageSamplesSubTitles;

    @FindBy(xpath = "//p[contains(@class,'styles_text--5')]")
    public WebElement addASampleOrEditASubTitle;

    @FindBy(xpath = "(//p[contains(@class,'styles_text--7')])[1]")
    public WebElement addSampleDetailsSubTitle;

    @FindBy(xpath = "//label[contains(text(),'Tumour content (percentage of malignant nuclei / b')]")
    public WebElement tumourSampleDynamicQuestionsLabel;

    @FindBy(xpath = "//label[@for='sampleType']/../div//div[text()='Select...']/../..")
    public WebElement sampleTypeDropDown;

    @FindBy(xpath = "//label[@for='sampleType']/..//div[contains(@class,'option')]/span/span")
    public List<WebElement> sampleTypesDropDownValues;

    @FindBy(xpath = "//div//div[text()='Select...']/../..")
    public List<WebElement> genericSampleDropDown;

    @FindBy(xpath = "//label[@for='sampleType']/../div//div[text()='Select...']")
    public WebElement sampleTypeDropDownPlaceHolder;

    @FindBy(xpath = "//label[@for='sampleState']/../div//div[text()='Select...']")
    public WebElement sampleStateDropDownPlaceHolder;

    @FindBy(xpath = "//label[@for='labId']/../input")
    public WebElement labIdPlaceHolder;

    @FindBy(xpath = "//label[@for='sampleType']/..//span[contains(@class,'required-icon')]")
    public WebElement SampleTypeAsterick;

    @FindBy(xpath = "//label[@for='sampleState']/..//div[contains(@class,'indicatorContainer')]//*[name()='svg']//*[name()='path']")
    public WebElement sampleStateSearchIcon;


    @FindBy(xpath = "//label[@for='sampleState']/..//div[contains(@class,'option')]/span/span")
    public List<WebElement> sampleStateDropDownValues;

	@FindBy (css = "label[for*='sampleType']")
	public WebElement sampleTypeLabel;

	@FindBy (css = "label[for*='sampleState']")
	public WebElement sampleStateLabel;

	@FindBy (css = "label[for*='labId']")
	public WebElement labIdLabel;

    @FindBy(xpath = "//label[@for='sampleState']/..//div[contains(@class,'option')]/span/span")
    public List <WebElement> sampleStateDropDownValues;


    @FindBy(css = "*[class*='styles_field-label--error']")
    public List<WebElement> fieldsLabelErrors;

    @FindBy(xpath = "//*[contains(@class,'add-sample__confirm-table')]//child::td")
    public List<WebElement> tumourDetailsValuesFromAddSamplePage;

    @FindBy(css = "p[class*='styles_text--5']")
    public WebElement subTitlePage;


    public void selectSampleType(String type) {
        Actions.clickElement(driver, sampleType);
        Actions.selectValueFromDropdown(dropdownValue, type);
        sampleDetails.setSampleType(type);
        Wait.seconds(1);
    }

    public void selectSampleState() {
        Actions.clickElement(driver, sampleState);
        Actions.selectRandomValueFromDropdown(dropdownValues);
        sampleDetails.setSampleState(Actions.getText(sampleState));
    }

    public void selectSampleState(String sampleStateValue) {
        Actions.clickElement(driver, sampleState);
        Actions.selectExactValueFromDropDown(dropdownValues, sampleStateValue);
        sampleDetails.setSampleState(sampleStateValue);
    }

    public void fillInSampleID() {
        Wait.forElementToBeDisplayed(driver, labId);
        String ID = faker.numerify("S#####");
        if (Actions.getValue(labId).isEmpty()) {
            Actions.fillInValue(labId, ID);
        } else {
            Actions.clearTextField(labId);
            Actions.fillInValue(labId, ID);
        }
        sampleDetails.setSampleID(ID);
    }

    public void answerSampleTopography(String value) {
        Wait.forElementToBeDisplayed(driver, sampleTopographyField);
        Actions.fillInValue(sampleTopographyField, value);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectRandomValueFromDropdown(dropdownValues);
    }

    public void answerSampleMorphology(String value) {
        Actions.fillInValue(sampleMorphologyField, value);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectRandomValueFromDropdown(dropdownValues);
    }

    public int fillInPercentageOfMalignantNuclei() {
        Wait.forElementToBeDisplayed(driver, percentageOfMalignantNucleiField);
        int percentage = faker.number().numberBetween(2, 99);
        Actions.fillInValue(percentageOfMalignantNucleiField, String.valueOf(percentage));
        return percentage;
    }

    public int fillInNumberOfSlides() {
        Wait.forElementToBeDisplayed(driver, numberOfSlidesField);
        int slides = faker.number().randomDigitNotZero();
        Actions.fillInValue(numberOfSlidesField, String.valueOf(slides));
        return slides;
    }

    public void selectSampleCollectionDate() {
        Wait.forElementToBeDisplayed(driver, sampleCollectionDateField);
        sampleDetails.setDay(String.valueOf(faker.number().numberBetween(1, 31)));
        sampleDetails.setMonth(String.valueOf(faker.number().numberBetween(1, 12)));
        sampleDetails.setYear(String.valueOf(faker.number().numberBetween(1900, 2019)));
        Actions.fillInValue(sampleCollectionDateField, sampleDetails.getDay() + "/" + sampleDetails.getMonth() + "/" + sampleDetails.getYear());
    }

    public String fillInSampleComments() {
        Wait.forElementToBeDisplayed(driver, sampleCommentsField);
        String comment = faker.chuckNorris().fact();
        Actions.fillInValue(sampleCommentsField, comment);
        return comment;
    }


    public int numberOfNewSamplesDisplayedInLandingPage() {
        Wait.forElementToBeDisplayed(driver, successNotification);
        Wait.forElementToBeDisplayed(driver, samplesLandingPageTable);
        int numberOfSamples = samplesLandingPageList.size() - 1;
        return numberOfSamples;
    }


    public void clickAddSampleButton() {
        Actions.clickElement(driver, addSampleButton);
    }

    public String getDynamicQuestionsLabelText() {
        Wait.forElementToBeDisplayed(driver, tumourSampleDynamicQuestionsLabel);
        return tumourSampleDynamicQuestionsLabel.getText();
    }

    public boolean newSampleIsDisplayedInLandingPage() {
        Wait.forElementToBeDisplayed(driver, successNotification);
        Wait.forElementToBeDisplayed(driver, samplesLandingPageTable);
        return successNotification.isDisplayed() && samplesLandingPageTable.isDisplayed();
    }

    public List<String> getSampleTypesOptions() {
        Wait.forElementToBeClickable(driver, sampleTypeDropDown);
        Actions.clickElement(driver, sampleTypeDropDown);
        List<String> actualSampleTypes = new ArrayList<>();
        for (WebElement sampleType : sampleTypesDropDownValues) {
            actualSampleTypes.add(sampleType.getText().trim());
        }
        Debugger.println("Print sampleTypes" + actualSampleTypes);
        return actualSampleTypes;
    }

    public List<String> getTheListOfFieldsErrorLabelsOnAddASamplePage() {

        Wait.forElementToBeClickable(driver, sampleTypeDropDown);
        List<String> actualFieldErrorLabels = new ArrayList<>();
        for (WebElement fieldLabel : fieldsLabelErrors) {
            actualFieldErrorLabels.add(fieldLabel.getText().trim());
        }
        Debugger.println("Actual-Field Labels Errors" + actualFieldErrorLabels);
        return actualFieldErrorLabels;
    }

    public List<String> getTheTumourDetailsValuesFromAddSamplePage() {
        Wait.forElementToBeDisplayed(driver, tumourDetailsTable);
        List<String> actualTumourDetailsFromAddSamplePage = new ArrayList<>();

        for (WebElement actualTumourDetail : tumourDetailsValuesFromAddSamplePage) {
            actualTumourDetailsFromAddSamplePage.add(actualTumourDetail.getText().trim());
        }
        Debugger.println("actual tumour-details on " + actualTumourDetailsFromAddSamplePage);
        return actualTumourDetailsFromAddSamplePage;
    }

    public boolean verifyTheElementsOnAddASamplePage() {

        // Find elements
        Wait.forElementToBeDisplayed(driver, sampleTypeDropDown);
        List<WebElement> expectedElements = new ArrayList<WebElement>();
        expectedElements.add(sampleTypeDropDown);
        expectedElements.add(sampleTypeLabel);
        expectedElements.add(sampleStateLabel);
        expectedElements.add(sampleState);
        expectedElements.add(labIdLabel);
        expectedElements.add(labId);

        for (int i = 0; i < expectedElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expectedElements.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean verifySearchIconInsideSampleStateField() {
        // Find elements
        Wait.forElementToBeDisplayed(driver, sampleState);
        if (!seleniumLib.isElementPresent(sampleStateSearchIcon)) {
            return false;
        }
        return true;
    }

    public List<String> getTheFieldPlaceHolderTextOnAddASamplePage() {

        Wait.forElementToBeClickable(driver, sampleTypeDropDown);
        List<String> actualFieldPlaceHolderTexts = new ArrayList<>();

        actualFieldPlaceHolderTexts.add(Actions.getText(sampleTypeDropDownPlaceHolder));
        actualFieldPlaceHolderTexts.add(Actions.getText(sampleStateDropDownPlaceHolder));
        actualFieldPlaceHolderTexts.add(labId.getAttribute("placeholder"));

        Debugger.println("Actual PlaceHolder on Add a Sample page " + actualFieldPlaceHolderTexts);
        return actualFieldPlaceHolderTexts;
    }

    public boolean verifyTheSubPageTitle(String expectedSubPageTitle) {
        By actualSubPageTitle = By.xpath("//p[contains(text(),'" + expectedSubPageTitle + "')]");
        if (!seleniumLib.isElementPresent(actualSubPageTitle)) {
            Wait.forElementToBeDisplayed(driver, driver.findElement(actualSubPageTitle));
            if (!seleniumLib.isElementPresent(actualSubPageTitle)) {
                Debugger.println("Expected title :" + expectedSubPageTitle + " not loaded in the page.");
                return false;
            }
        }
        Debugger.println("Actual Add-Sample sub-title :" + driver.findElement(actualSubPageTitle).getText());
        Debugger.println("Expected Add-Sample sub-title :" + expectedSubPageTitle);
        return true;
    }

    public List<String> getTheSampleStateDropDownValues() {
        Wait.forElementToBeClickable(driver, sampleState);
        Actions.clickElement(driver, sampleState);
        List<String> actualSampleStateValues = new ArrayList<>();

        for (WebElement actualSampleStateValue : sampleStateDropDownValues) {
            actualSampleStateValues.add(actualSampleStateValue.getText().trim());
        }
        Debugger.println("Actual sample-state values: " + actualSampleStateValues);
        return actualSampleStateValues;
    }

    public void selectSampleFromLandingPage() {
        Actions.retryClickAndIgnoreElementInterception(driver, editSampleButton);
    }

    public List<String> getTheSampleDetailsOnEditASamplePage() {

        Wait.forElementToBeDisplayed(driver, sampleType);
        List<String> actualSampleDetails = new ArrayList<>();

        actualSampleDetails.add(Actions.getText(sampleType));
        actualSampleDetails.add(Actions.getText(sampleState));
        actualSampleDetails.add(Actions.getValue(labId));

        Debugger.println("Actual Sample Details on Edit a Sample " + actualSampleDetails);
        return actualSampleDetails;
    }

    public List<String> getTheExpectedSampleDetails() {

        List<String> expectedSampleDetails = new ArrayList<>();

        expectedSampleDetails.add(sampleDetails.getSampleType());
        expectedSampleDetails.add(sampleDetails.getSampleState());
        expectedSampleDetails.add(sampleDetails.getSampleID());

        Debugger.println("Expected Sample Details on Edit a Sample " + expectedSampleDetails);
        return expectedSampleDetails;
    }

    public void selectASampleAsParentSample() {
        Actions.clickElement(driver, parentSampleCheckbox);
    }
}
