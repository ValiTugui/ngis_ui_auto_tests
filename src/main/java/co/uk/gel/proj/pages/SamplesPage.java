package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
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

    @FindBy(xpath = "//label[contains(@for,'sampleType')]//following::div[2]") //@FindBy(xpath = "//label[contains(@for,'sampleType')]//following::div")
    public WebElement sampleType;

    @FindBy(xpath = "//label[contains(@for,'sampleState')]//following::div[2]")  ////@FindBy(xpath = "//label[contains(@for,'sampleState')]//following::div")
    public WebElement sampleState;

    @FindBy(xpath = "//label[@for='sampleState']/..//div[contains(@class,'singleValue')]/span")
    public WebElement selectedSampleStateValue;

    @FindBy(xpath = "//*[contains(@id,'question-id-q323')]//child::input")
    public WebElement sampleTopographyField;

    @FindBy(css = "label[for*='question-id-q323']")
    public WebElement sampleTopographyFieldLabel;

    @FindBy(xpath = "//*[contains(@id,'question-id-q324')]//child::input")
    public WebElement sampleMorphologyField;

    @FindBy(css = "label[for*='question-id-q324']")
    public WebElement sampleMorphologyFieldLabel;

    @FindBy(xpath = "//*[contains(@id,'question-id-q321')]")
    public WebElement percentageOfMalignantNucleiField;

    @FindBy(xpath = "//*[contains(@id,'question-id-q326')]")
    public WebElement numberOfSlidesField;

    @FindBy(css = "label[for*='question-id-q326']")
    public WebElement numberOfSlidesFieldLabel;

//    @FindBy(xpath = "//*[contains(@id,'question-id-q328')]")
//    public WebElement sampleCollectionDateField;

//    @FindBy(css = "label[for*='question-id-q328']")
//    public WebElement sampleCollectionDateFieldLabel;

    @FindBy(xpath = "//input[@placeholder='DD']")
    public WebElement sampleCollectionDay;
    @FindBy(xpath = "//input[@placeholder='MM']")
    public WebElement sampleCollectionMonth;
    @FindBy(xpath = "//input[@placeholder='YYYY']")
    public WebElement sampleCollectionYear;


    @FindBy(xpath = "//*[contains(@id,'question-id-q327')]")
    public WebElement sampleCommentsField;

    @FindBy(css = "label[for*='question-id-q327']")
    public WebElement sampleCommentsFieldLabel;

    public WebElement labId;

    @FindBy(css = "*[data-testid*='notification-success']")
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

    @FindBy(css = "*[class*='error-message__text']")
    public List<WebElement> errorMessages;

    @FindBy(xpath = "//*[contains(@class,'add-sample__confirm-table')]//following::a")
    public WebElement notTheRightTumourLink;

    @FindBy(css = "*[class*='sample-detail__edit-link']")
    public WebElement editSampleButton;

    @FindBy(xpath = "//button[@type='button']/span[text()='Add sample']")
    public WebElement addSampleButton;

    @FindBy(css = "*[class*='checkmark']")
    public WebElement parentSampleCheckbox;

    @FindBy(xpath = "//p[contains(@class,'sample-detail__sub-title')]")
    public List<WebElement> manageSamplesSubTitles;

    @FindBy(xpath = "//p[contains(@class,'styles_text--5')]")
    public WebElement addASampleOrEditASubTitle;

    @FindBy(xpath = "(//p[contains(@class,'styles_text--7')])[1]")
    public WebElement addSampleDetailsSubTitle;

    @FindBy(xpath = "//label[contains(text(),'Tumour content (percentage of malignant nuclei / b')]")
    public WebElement tumourContentPercentageOfMalignantNucleiFieldLabel;

    @FindBy(xpath = "//label[@for='sampleType']/../div//div[text()='Select...']/../..")
    public WebElement sampleTypeDropDown;

    @FindBy(xpath = "//label[@for='sampleType']/..//div[contains(@class,'option')]/span/span")
    public List<WebElement> sampleTypesDropDownValues;

    @FindBy(xpath = "//label[@for='sampleType']/../div//div[text()='Select...']")
    public WebElement sampleTypeDropDownPlaceHolder;

    @FindBy(xpath = "//label[@for='sampleState']/../div//div[text()='Select...']")
    public WebElement sampleStateDropDownPlaceHolder;

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

    @FindBy(css = "*[class*='styles_field-label--error']")
    public List<WebElement> fieldsLabelErrors;

    @FindBy(xpath = "//*[contains(@class,'add-sample__confirm-table')]//child::td")
    public List<WebElement> tumourDetailsValuesFromAddSamplePage;


    @FindBy(xpath = "//table//tbody/tr[last()]/th/div/div |//table//tbody/tr[last()]/td[text()!='']")
    public List<WebElement> newlyAddedSampleDetailsList;

    @FindBy(xpath = "//table//tbody/tr")
    public List<WebElement> listOfSamplesInTheTable;

    @FindBy (css = "h6[class*='styles_text--6']")
    public WebElement infoTextForLinkingSamples;

    @FindBy(css ="span[class*='checkmark--checked']")
    public WebElement sampleTypeSVGTickMark;

    @FindBy(xpath = "//a[contains(@class,'styles_sample-detail__edit-link')]")
    List<WebElement> sampleEditButtons;

    public boolean isErrorPresent(){
        int noOfErrors = errorMessages.size();
        if(noOfErrors > 0){
            return true;
        }
        return false;
    }

    public boolean selectSampleType(String type) {
        try {
            Actions.clickElement(driver, sampleType);
            Actions.selectValueFromDropdown(dropdownValue, type);
            sampleDetails.setSampleType(type);
            Wait.seconds(1);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from selectSampleType:"+exp);
            SeleniumLib.takeAScreenShot("selectSampleType.jpg");
            return false;
        }
    }

    public void selectSampleState() {
        try {
            Wait.isElementDisplayed(driver, sampleState, 30);
            Actions.clickElement(driver, sampleState);
            // Counter for number of tries -loop for when error message is triggered upon selecting sample state
            int numberOfAttempts = 5;
            Actions.reClickDropDownFieldIfLabelErrorIsShown(driver, fieldsLabelErrors, sampleState, sampleStateLabel, numberOfAttempts);
            Actions.retrySelectRandomValueFromDropDown(dropdownValues);
            sampleDetails.setSampleState(Actions.getText(selectedSampleStateValue));
        }catch(Exception exp){

        }
    }

    public boolean selectSpecificSampleState(String sampleStateValue) {

        try {
            if (!Wait.isElementDisplayed(driver, sampleState, 30)) {
                Actions.scrollToTop(driver);
            }
            Actions.clickElement(driver, sampleState);
            Wait.seconds(2);
            if(!Wait.isElementDisplayed(driver, dropdownValue,10)){
                Debugger.println("SampleState Drop values not loaded: ");
                SeleniumLib.takeAScreenShot("SampleStateNotLoaded.jpg");
                return false;
            }
            Actions.selectValueFromDropdown(dropdownValue, sampleStateValue);
            sampleDetails.setSampleState(sampleStateValue);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from SelectSpecificSampleState : " + exp);
            SeleniumLib.takeAScreenShot("SelectSampleStateValue.jpg");
            return false;
        }
    }

    public boolean fillInSampleID() {
        try {
            Wait.forElementToBeDisplayed(driver, labId);
            String ID = faker.numerify("S#####");
            if (Actions.getValue(labId).isEmpty()) {
                Actions.fillInValue(labId, ID);
            } else {
                Actions.clearTextField(labId);
                Actions.fillInValue(labId, ID);
            }
            sampleDetails.setSampleID(ID);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in fillInSampleID:"+exp);
            SeleniumLib.takeAScreenShot("fillInSampleID.jpg");
            return false;
        }
    }

    public void answerSampleTopography(String value) {
        try {
        Wait.forElementToBeDisplayed(driver, sampleTopographyField);
        Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(sampleTopographyField, value);
        Wait.forElementToBeDisplayed(driver, dropdownValue);
        Actions.selectRandomValueFromDropdown(dropdownValues);
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot("SampleTopograhy.jpg");
            Debugger.println(" Sample Topograhy value " + value + " is not displayed in the dynamic dropdown list");
            Assert.assertFalse(true);
        }
    }

    public void answerSampleMorphology(String value) {
        try {
            Wait.forElementToBeDisplayed(driver, sampleMorphologyField);
            Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(sampleMorphologyField, value);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectRandomValueFromDropdown(dropdownValues);
        } catch (Exception exp) {
                SeleniumLib.takeAScreenShot("SampleMorphology.jpg");
                Debugger.println(" Sample Morphology value " + value + " is not displayed in the dynamic dropdown list");
                Assert.assertFalse(true);
            }
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
        sampleDetails.setDay(String.valueOf(faker.number().numberBetween(1, 31)));
        sampleDetails.setMonth(String.valueOf(faker.number().numberBetween(1, 12)));
        sampleDetails.setYear(String.valueOf(faker.number().numberBetween(1900, 2019)));
        seleniumLib.sendValue(sampleCollectionDay,sampleDetails.getDay());
        seleniumLib.sendValue(sampleCollectionMonth,sampleDetails.getMonth());
        seleniumLib.sendValue(sampleCollectionYear,sampleDetails.getYear());
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


    public boolean clickAddSampleButton() {
        try {
            if(!Wait.isElementDisplayed(driver,addSampleButton,10)){
                Debugger.println("Add sample button not displayed\n" + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("clickAddSampleButton.jpg");
                return false;
            }
        Actions.clickElement(driver, addSampleButton);
            return true;
        }catch(Exception exp){
            try{
                seleniumLib.clickOnWebElement(addSampleButton);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception in clickAddSampleButton:" + exp1 + "\n" + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("clickAddSampleButton.jpg");
                return false;
            }
        }
    }

    public String getDynamicQuestionsLabelText() {
        Wait.forElementToBeDisplayed(driver, tumourContentPercentageOfMalignantNucleiFieldLabel);
        return tumourContentPercentageOfMalignantNucleiFieldLabel.getText();
    }

    public boolean newSampleIsDisplayedInLandingPage() {
        Wait.forElementToBeDisplayed(driver, successNotification);
        Wait.forElementToBeDisplayed(driver, samplesLandingPageTable);
        return successNotification.isDisplayed() && samplesLandingPageTable.isDisplayed();
    }

    public List<String> getSampleTypesOptions() {
        Wait.forElementToBeClickable(driver, sampleTypeDropDown);
        Actions.clickElement(driver, sampleTypeDropDown);
        List<String> actualSampleTypes = new ArrayList<String>();
        for (WebElement sampleType : sampleTypesDropDownValues) {
            actualSampleTypes.add(sampleType.getText().trim());
        }
        //Debugger.println("Print sampleTypes" + actualSampleTypes);
        return actualSampleTypes;
    }

    public List<String> getTheListOfFieldsErrorLabelsOnAddASamplePage() {

        Wait.forElementToBeClickable(driver, sampleTypeDropDown);
        List<String> actualFieldErrorLabels = new ArrayList<>();
        for (WebElement fieldLabel : fieldsLabelErrors) {
            actualFieldErrorLabels.add(fieldLabel.getText().trim());
        }
       // Debugger.println("Actual-Field Labels Errors" + actualFieldErrorLabels);
        return actualFieldErrorLabels;
    }

    public List<String> getTheTumourDetailsValuesFromAddSamplePage() {
        Wait.forElementToBeDisplayed(driver, tumourDetailsTable);
        List<String> actualTumourDetailsFromAddSamplePage = new ArrayList<>();

        for (WebElement actualTumourDetail : tumourDetailsValuesFromAddSamplePage) {
            actualTumourDetailsFromAddSamplePage.add(actualTumourDetail.getText().trim());
        }
        //Debugger.println("actual tumour-details on " + actualTumourDetailsFromAddSamplePage);
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
        try {
            Wait.forElementToBeDisplayed(driver, sampleState);
            if (!Wait.isElementDisplayed(driver, sampleStateSearchIcon, 10)) {
                Debugger.println("Sample State Search Icon could not locate:");
                SeleniumLib.takeAScreenShot("SampleStateSearch.jpg");
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from verifySearchIconInsideSampleStateField:"+exp);
            SeleniumLib.takeAScreenShot("SampleStateSearch.jpg");
            return false;
        }
    }

    public List<String> getTheFieldPlaceHolderTextOnAddASamplePage() {

        Wait.forElementToBeClickable(driver, sampleTypeDropDown);
        List<String> actualFieldPlaceHolderTexts = new ArrayList<>();

        actualFieldPlaceHolderTexts.add(Actions.getText(sampleTypeDropDownPlaceHolder));
        actualFieldPlaceHolderTexts.add(Actions.getText(sampleStateDropDownPlaceHolder));
        actualFieldPlaceHolderTexts.add(labId.getAttribute("placeholder"));

        //Debugger.println("Actual PlaceHolder on Add a Sample page " + actualFieldPlaceHolderTexts);
        return actualFieldPlaceHolderTexts;
    }

    public boolean verifyTheSubPageTitle(String expectedSubPageTitle) {
        try {
            By actualSubPageTitle = By.xpath("//p[contains(text(),'" + expectedSubPageTitle + "')]");
            if (!seleniumLib.isElementPresent(actualSubPageTitle)) {
                Wait.forElementToBeDisplayed(driver, driver.findElement(actualSubPageTitle));
                if (!seleniumLib.isElementPresent(actualSubPageTitle)) {
                    Debugger.println("Expected title :" + expectedSubPageTitle + " not loaded in the page.");
                    return false;
                }
            }
            //Debugger.println("Actual Add-Sample sub-title :" + driver.findElement(actualSubPageTitle).getText());
            //Debugger.println("Expected Add-Sample sub-title :" + expectedSubPageTitle);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in verifyTheSubPageTitle:"+exp);
            SeleniumLib.takeAScreenShot("verifyTheSubPageTitle.jpg");
            return false;
        }
    }

    public List<String> getTheSampleStateDropDownValues() {
        Wait.forElementToBeClickable(driver, sampleState);
        Actions.clickElement(driver, sampleState);
        List<String> actualSampleStateValues = new ArrayList<>();

        for (WebElement actualSampleStateValue : sampleStateDropDownValues) {
            actualSampleStateValues.add(actualSampleStateValue.getText().trim());
        }
        //Debugger.println("Actual sample-state values: " + actualSampleStateValues);
        return actualSampleStateValues;
    }

    public boolean selectSampleFromLandingPage() {
        try {
            if(!Wait.isElementDisplayed(driver,editSampleButton,30)){
                Debugger.println("Edit Sample type option not present.");
                SeleniumLib.takeAScreenShot("editSample.jpg");
                return false;
            }
            Actions.retryClickAndIgnoreElementInterception(driver, editSampleButton);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from selectSampleFromLandingPage:"+exp);
            SeleniumLib.takeAScreenShot("editSample.jpg");
            return false;
        }
    }

    public List<String> getTheSampleDetailsOnEditASamplePage() {

        Wait.forElementToBeDisplayed(driver, sampleType);
        List<String> actualSampleDetails = new ArrayList<>();

        actualSampleDetails.add(Actions.getText(sampleType));
        actualSampleDetails.add(Actions.getText(sampleState));
        actualSampleDetails.add(Actions.getValue(labId));

        //Debugger.println("Actual Sample Details on Edit a Sample " + actualSampleDetails);
        return actualSampleDetails;
    }

    public List<String> getTheExpectedSampleDetails() {

        List<String> expectedSampleDetails = new ArrayList<>();

        expectedSampleDetails.add(sampleDetails.getSampleType());
        expectedSampleDetails.add(sampleDetails.getSampleState());
        expectedSampleDetails.add(sampleDetails.getSampleID());

        //Debugger.println("Expected Sample Details on Edit a Sample " + expectedSampleDetails);
        return expectedSampleDetails;
    }

    public void selectASampleAsParentSample() {
        Actions.clickElement(driver, parentSampleCheckbox);
    }

    public List<String> getTheSampleDetailsOnTableList() {

        Wait.forElementToBeDisplayed(driver, samplesLandingPageTable);
        List<String> actualSampleTestData = new ArrayList<>();
        for (WebElement sampleDetails : newlyAddedSampleDetailsList) {
            actualSampleTestData.add(sampleDetails.getText().trim());
        }
        //Debugger.println("Method Actual-SampleDetails-In List " + actualSampleTestData);
        return actualSampleTestData;
    }

    public List<String> getTheParentAndChildSampleDetailsOnTableList(int parentAndChildSampleDetails) {

        /* parentAndChildSampleDetails - index 0 is for parent and index 1 is for child */
        Wait.forElementToBeDisplayed(driver, samplesLandingPageTable);
        List<WebElement>parentSampleDetails = listOfSamplesInTheTable.get(parentAndChildSampleDetails).findElements(By.tagName("td"));

        List<String> actualSampleTestData = new ArrayList<>();
        for (WebElement sampleDetails : parentSampleDetails) {
            actualSampleTestData.add(sampleDetails.getText().trim());
        }
        //Debugger.println("Actual-SampleDetails In List " + actualSampleTestData);
        return actualSampleTestData;
    }

    public boolean verifyTumourDescriptionIsOnlyDisplayForSampleTumourType(List<String> actualSampleTestData, String expectedTumourDescription) {
        // If non-sample type is selected, Tumour Description value will be null in Sample table list, NULL value will be asserted
        boolean flag = false;
        if (expectedTumourDescription == null) {
            Debugger.println("Expected TumourDescription Step-def - empty :" + expectedTumourDescription);
            flag = actualSampleTestData.contains("-");
        }
        // if Sample type selected is of Sample-tumour type, Assert the value in the column "Tumour Description" in SampleTable list
        else if (!expectedTumourDescription.isEmpty()) {
            Debugger.println("Expected TumourDescription Step-def - not empty :" + expectedTumourDescription);
            flag = actualSampleTestData.contains(expectedTumourDescription);
        }
        return flag;
    }

    public String getTheDisplayedAddTumourErrorMessage() {
        Wait.forElementToBeDisplayed(driver, addTumourErrorMessage);
        return Actions.getText(addTumourErrorMessage);
    }

    public void clickAddTumourLinkFromErrorMessage() {
        Actions.retryClickAndIgnoreElementInterception(driver, addTumourLink);
    }

    public String getTheDisplayedTumourTextLinkOnAddASamplePage(){
        Wait.forElementToBeDisplayed(driver, notTheRightTumourLink);
        return Actions.getText(notTheRightTumourLink);
    }

    public void clickTheNotTheRightTumourLink() {
        Wait.forElementToBeDisplayed(driver, notTheRightTumourLink);
        Actions.retryClickAndIgnoreElementInterception(driver, notTheRightTumourLink);
    }

    public String getTheDisplayedSampleTextLinkOnAddASamplePage(){
        Wait.forElementToBeDisplayed(driver, infoTextForLinkingSamples);
        return Actions.getText(infoTextForLinkingSamples);
    }

    public boolean verifyTheElementsOnAddSampleDetailsForSampleNonTumourType() {
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(sampleCollectionDay);
        expElements.add(sampleCollectionMonth);
        expElements.add(sampleCollectionYear);
        expElements.add(sampleCommentsField);
        expElements.add(sampleCommentsFieldLabel);
        for(int i=0; i<expElements.size(); i++){
            if(!seleniumLib.isElementPresent(expElements.get(i))){
                return false;
            }
        }
        return true;
    }


    public boolean verifyTheElementsOnAddSampleDetailsForSampleTumourType() {
        Wait.forElementToBeDisplayed(driver, sampleTopographyField);
        List<WebElement> expElements = new ArrayList<WebElement>();
        expElements.add(sampleTopographyField);
        expElements.add(sampleTopographyFieldLabel);
        expElements.add(sampleMorphologyField);
        expElements.add(sampleMorphologyFieldLabel);
        expElements.add(percentageOfMalignantNucleiField);
        expElements.add(tumourContentPercentageOfMalignantNucleiFieldLabel);
        expElements.add(numberOfSlidesField);
        expElements.add(numberOfSlidesFieldLabel);
        expElements.add(sampleCollectionDay);
        expElements.add(sampleCollectionMonth);
        expElements.add(sampleCollectionYear);
        expElements.add(sampleCommentsField);
        expElements.add(sampleCommentsFieldLabel);
        for(int i=0; i<expElements.size(); i++){
            if(!seleniumLib.isElementPresent(expElements.get(i))){
                Debugger.println("Element: "+expElements.get(i)+" not present.");
                return false;
            }
        }
        return true;
    }

    public String getTheLabelForTumourContentPercentageField(){
        return Actions.getText(tumourContentPercentageOfMalignantNucleiFieldLabel);
    }

    public boolean ensureTickMarkIsDisplayedNextToSampleType(){
        Wait.forElementToBeDisplayed(driver, sampleTypeSVGTickMark);
        if(Wait.isElementDisplayed(driver, sampleTypeSVGTickMark, 10)){
            return true;
        }
        return false;
    }

    public boolean editSpecificSample() {
        try {
            if(!Wait.isElementDisplayed(driver, editSampleButton,10)){
                Debugger.println("Edit Sample Button Not Present ");
                SeleniumLib.takeAScreenShot("EditSampleButton.jpg");
                return false;
            }
            Actions.clickElement(driver,editSampleButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in :editSpecificSample" + exp);
            SeleniumLib.takeAScreenShot("EditSampleButton.jpg");
            return false;
        }
    }
    public boolean verifyAddSampleButtonIsDisplayed() {
        if(!Wait.isElementDisplayed(driver,addSampleButton,10)){
            Debugger.println("Add Samples Button not displayed as expected.");
            SeleniumLib.takeAScreenShot("AddSamplesButton.jpg");
            return false;
        }
        return true;
    }
    public boolean editSpecificSample(String sampleNo) {
        try {
            int noOfSamples = Integer.parseInt(sampleNo);
            if(sampleEditButtons.size() < noOfSamples){
                Debugger.println("Edit Sample Button Not Present ");
                SeleniumLib.takeAScreenShot("editSpecificSample.jpg");
                return false;
            }
            Actions.clickElement(driver,sampleEditButtons.get((noOfSamples-1)));
            return true;
        } catch (NumberFormatException exp) {
            Debugger.println("Exception in : editSpecificSample" + exp);
            SeleniumLib.takeAScreenShot("editSpecificSample.jpg");
            return false;
        }
    }
}//
