package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import co.uk.gel.lib.SeleniumLib;

import java.util.ArrayList;
import java.util.List;

public class TumoursPage {

    WebDriver driver;
    static NewPatient tumourDetails = new NewPatient();
    Faker faker = new Faker();
    SeleniumLib seleniumLib;

    public TumoursPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//input[@id='descriptiveName']")
    public WebElement descriptiveName;
    @FindBy(xpath = "//input[@id='dateDay']")
    public WebElement dateDay;
    @FindBy(xpath = "//input[@id='dateMonth']")
    public WebElement dateMonth;
    @FindBy(xpath = "//input[@id='dateYear']")
    public WebElement dateYear;
    @FindBy(xpath = "//input[@id='pathologyReportId']")
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

    @FindBy(css = "*[data-testid*='notification-success']")
    public WebElement successNotification;

    @FindBy(xpath = "//*[contains(@id,'question-id-q151')]")
    public WebElement tumourCoreDataDropdown;

    @FindBy(xpath = "//div[contains(@id,'question-id-q155')]//input[@type!='hidden']")
    public WebElement topographyOfPrimaryTumourField;

    @FindBy(xpath = "//div[contains(@id,'question-id-q161')]//input[@type!='hidden']")
    public WebElement topographyOfThisMetastaticDepositField;

    @FindBy(xpath = "//div[contains(@id,'question-id-q160')]//input[@type!='hidden']")
    public WebElement workingDiagnosisMorphologyField;

    @FindBy(css = "*[class*='tumour-list__warning']")
    public List<WebElement> tumoursWarningMessage;

    @FindBy(xpath = "//*[contains(@class,'add-sample__confirm-table')]//child::td")
    public List<WebElement> tumourDetailsValuesFromAddSamplePage;

    @FindBy(css = "*[class*='error-message__text']")
    public List<WebElement> errorMessages;

    @FindBy(xpath = "//button/span[text()='add a new tumour']")
    public WebElement addAnotherTumourLink;

    @FindBy(xpath = "//button[text()='add a new tumour']")  //added
    public List<WebElement> addAnotherTumourLinkList;

    //@FindBy(css = "*[class*='checkbox-row__arrow']")
    @FindBy(xpath = "//button[@aria-label='Edit a tumour']")

    public WebElement editTumourArrow;

    @FindBy(xpath = "//div[contains(@class,'notification--success')]/div[2]")
    public WebElement genericSuccessNotification;

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

    @FindBy(xpath = "//th[contains(text(),'Working diagnosis/morphology')]")
    public WebElement snomedCTWorkingDiagnosisLabel;

    @FindBy(xpath = "//table//tbody/tr[not(contains(@class,'checked'))]")
    public List<WebElement> listOfUnselectedTumourList;

    @FindBy(xpath = "//tr[contains(@class,'checked')]//th/div/div|//tr[contains(@class,'checked')]//td[@class='checkbox-row__cell']")
    public List<WebElement> newlyCheckedTumourDetailsList;

    @FindBy(css = "span[class*='checkmark--checked']")
    public WebElement TumourSVGTickMark;

    @FindBy(xpath = "//tr[contains(@class,'styles_text')]")
    public List<WebElement> addedTumours;

    @FindBy(xpath = "//div[text()='Tumour updated']")
    WebElement tumourUpdatedMsg;

    @FindBy(xpath = "(//div[contains(@class,'styles_text__1aikh styles_text--5__203Ot styles_rt')])[2]")
    public WebElement labelTextInTumour;

    //List of primary tumours field
    @FindBy(xpath = "//div[contains(@id,'question-id-q155')]//input[@type!='hidden']")
    public List<WebElement> topographyOfPrimaryTumourFieldList;
    //List of secondary tumors field
    @FindBy(xpath = "//div[contains(@id,'question-id-q161')]//input[@type!='hidden']")
    public List<WebElement> topographyOfThisMetastaticDepositFieldList;

    @FindBy(xpath = "(//div[contains(@class,'styles_repeating')])[1]/child::*/span[text()='+ Add another']")
    public WebElement addAnotherLinkForTumourDiagnosis;

    @FindBy(xpath = "//div[contains(@id,'question-id-q160')]//input[@type!='hidden']")
    public List<WebElement> workingDiagnosisMorphologyFieldList;

    @FindBy(xpath = "(//div[contains(@class,'styles_repeating')])[2]/child::*/span[text()='+ Add another']")
    public WebElement addAnotherLinkForWorkingDiagnosisMorphology;

    public boolean navigateToAddTumourPageIfOnEditTumourPage() {

        if (descriptiveNameList.size() > 0) {
            Debugger.println("User is on Add Tumour Page");
            return true;
        } else if (addAnotherTumourLinkList.size() > 0) {
            Debugger.println("User is on Edit Tumour Page");
            addAnotherTumourLink.click();
            Debugger.println("User is NOW on Add Tumour Page");
            return true;
        } else {
            Debugger.println("User is not on tumour page");
            SeleniumLib.takeAScreenShot("ToumourPage.jpg");
            return false;
        }
    }

    public String fillInTumourDescription() {
        try {
            Wait.forElementToBeDisplayed(driver, descriptiveName);
            String description = TestUtils.getRandomLastName();
            tumourDetails.setTumourDescription(description);
            Actions.fillInValue(descriptiveName, description);
            return description;
        } catch (Exception exp) {
            Debugger.println("Exception in fillInTumourDescription:" + exp);
            SeleniumLib.takeAScreenShot("fillInTumourDescription.jpg");
            return null;
        }
    }

    public void fillInDateOfDiagnosisInDifferentOrder(String dayOfDiagnosis, String monthOfDiagnosis, String yearOfDiagnosis) {
        dateYear.sendKeys(yearOfDiagnosis);
        dateDay.sendKeys(dayOfDiagnosis);
        dateMonth.sendKeys(monthOfDiagnosis);
    }

    public boolean fillInDateOfDiagnosis(String dayOfDiagnosis, String monthOfDiagnosis, String yearOfDiagnosis) {
        try {
            if (dayOfDiagnosis != null && !dayOfDiagnosis.isEmpty()) {
                dateDay.sendKeys(dayOfDiagnosis);
            }
            if (monthOfDiagnosis != null && !monthOfDiagnosis.isEmpty()) {
                dateMonth.sendKeys(monthOfDiagnosis);
            }
            if (yearOfDiagnosis != null && !yearOfDiagnosis.isEmpty()) {
                dateYear.sendKeys(yearOfDiagnosis);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in fillInDateOfDiagnosis:" + exp);
            SeleniumLib.takeAScreenShot("fillInDateOfDiagnosis.jpg");
            return false;
        }
    }

    public void clearDateOfDiagnosisFields() {
        Actions.clearInputField(dateDay);
        Actions.clearInputField(dateMonth);
        Actions.clearInputField(dateYear);
        Wait.seconds(2);
    }

    public boolean fillInDateOfDiagnosis() {
        try {
            tumourDetails.setDay(String.valueOf(faker.number().numberBetween(1, 31)));
            tumourDetails.setMonth(String.valueOf(faker.number().numberBetween(1, 12)));
            tumourDetails.setYear(String.valueOf(faker.number().numberBetween(2018, 2019)));
            Actions.fillInValue(dateDay, tumourDetails.getDay());
            Actions.fillInValue(dateMonth, tumourDetails.getMonth());
            Actions.fillInValue(dateYear, tumourDetails.getYear());
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in fillInDateOfDiagnosis:" + exp);
            SeleniumLib.takeAScreenShot("fillInDateOfDiagnosis.jpg");
            return false;
        }
    }

    public String selectTumourType(String type) {
        try {
            Wait.forElementToBeClickable(driver, tumourType);
            Actions.retryClickAndIgnoreElementInterception(driver, tumourType);
            Wait.forElementToBeClickable(driver, dropdownValue);
            Actions.selectValueFromDropdown(dropdownValue, type);
            tumourDetails.setTumourType(type);
            return Actions.getText(tumourType);
        } catch (Exception exp) {
            Debugger.println("Exception in selectTumourType:" + exp);
            SeleniumLib.takeAScreenShot("selectTumourType.jpg");
            return null;
        }
    }

    public String fillInSpecimenID() {
        try {
            String ID = faker.numerify("N#####");
            Actions.fillInValue(pathologyReportId, ID);
            tumourDetails.setTumourSpecimenID(ID);
            return ID;
        } catch (Exception exp) {
            Debugger.println("Exception in fillInSpecimenID:" + exp);
            SeleniumLib.takeAScreenShot("fillInSpecimenID.jpg");
            return null;
        }
    }

    public boolean selectTumourFirstPresentationOrOccurrenceValue(String value) {
        Wait.seconds(2);
        try {
            if (!Wait.isElementDisplayed(driver, tumourCoreDataDropdown, 30)) {
                Debugger.println("selectTumourFirstPresentationOrOccurrenceValue Not displayed.");
                SeleniumLib.takeAScreenShot("SelectTumourFirstPresentation.jpg");
                return false;
            }
            Actions.clickElement(driver, tumourCoreDataDropdown);
            Wait.seconds(2);
            Actions.selectValueFromDropdown(dropdownValue, value);
            return true;
        } catch (Exception exp) {
            try {
                //Trying again - added based on Jenkins run failure
                seleniumLib.clickOnWebElement(tumourCoreDataDropdown);
                Wait.seconds(2);
                Actions.selectValueFromDropdown(dropdownValue, value);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception in selectTumourFirstPresentationOrOccurrenceValue: " + exp + " : " + value);
                SeleniumLib.takeAScreenShot("SelectTumourFirstPresentation.jpg");
                return false;
            }
        }
    }

    public boolean answerTumourDiagnosisQuestions(String diagnosis) {
        try {
            Wait.forElementToBeDisplayed(driver, topographyOfPrimaryTumourField);
            Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(topographyOfPrimaryTumourField, diagnosis);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectRandomValueFromDropdown(dropdownValues);
            Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(topographyOfThisMetastaticDepositField, diagnosis);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.selectRandomValueFromDropdown(dropdownValues);
            Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(workingDiagnosisMorphologyField, diagnosis);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.retrySelectRandomValueFromDropDown(dropdownValues);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
            //Actions.selectRandomValueFromDropdown(dropdownValues);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in answerTumourDiagnosisQuestions:" + exp);
            SeleniumLib.takeAScreenShot("answerTumourDiagnosisQuestions.jpg");
            return false;
        }
    }

    public void answerTumourDiagnosisQuestionsBasedOnTumourType(String tumourType, String diagnosis) {

        switch (tumourType) {
            case "Solid tumour: metastatic": {
                Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(topographyOfPrimaryTumourField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(topographyOfThisMetastaticDepositField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(workingDiagnosisMorphologyField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                break;
            }
            case "Solid tumour: primary": {
                Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(topographyOfPrimaryTumourField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(workingDiagnosisMorphologyField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                break;
            }
            case "Solid tumour: unknown":
            case "Brain tumour": {
                Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(topographyOfThisMetastaticDepositField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(workingDiagnosisMorphologyField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                break;
            }

            case "Haematological malignancy: liquid sample":
            case "Haematological malignancy: solid sample": {
                Actions.fillInValueOneCharacterAtATimeOnTheDynamicInputField(workingDiagnosisMorphologyField, diagnosis);
                Wait.forElementToBeDisplayed(driver, dropdownValue);
                Actions.selectRandomValueFromDropdown(dropdownValues);
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid tumour type");
        }
    }

    public boolean newTumourIsDisplayedInLandingPage(int i) {
        try {
            if(!Wait.isElementDisplayed(driver, tumoursLandingPageTable,30)){
                Debugger.println("Tumour Table not displayed.");
                Debugger.println("newTumourIsDisplayedInLandingPage.jpg");
                return false;
            }
            int numberOfTumours = tumoursLandingPageList.size() - 1;
            if(numberOfTumours != i){
                Debugger.println("Expected Number of Tumours: "+i+", Actual:"+numberOfTumours);
                SeleniumLib.takeAScreenShot("newTumourIsDisplayedInLandingPage.jpg");
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in newTumourIsDisplayedInLandingPage:"+exp);
            SeleniumLib.takeAScreenShot("newTumourIsDisplayedInLandingPage.jpg");
            return false;
        }
    }

    public int getTheNumbersOfTumoursDisplayedInLandingPage() {
        try {
            if(!Wait.isElementDisplayed(driver,successNotification,30)){
                Debugger.println("TumourPage successNotification not displayed.\n"+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("TumourSuccessNotification.jpg");
                return -1;
            }
            if(!Wait.isElementDisplayed(driver,tumoursLandingPageTable,30)){
                Debugger.println("TumourPage tumoursLandingPageTable not displayed.\n"+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("tumoursLandingPageTable.jpg");
                return -1;
            }
        return listOfTumoursInTheTable.size();
        }catch(Exception exp){
            Debugger.println("Exception in getTheNumbersOfTumoursDisplayedInLandingPage:"+exp+"\n"+driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("getTheNumbersOfTumoursDisplayedInLandingPage.jpg");
            return -1;
        }
    }

    public boolean tumourIsNotHighlighted() {
        try {
            if (!Wait.isElementDisplayed(driver, successNotification, 10)) {
                Debugger.println("Tumour page successNotification not displayed:");
                SeleniumLib.takeAScreenShot("tumourIsNotHighlighted.jpg");
                return false;
            }
            if (tumoursLandingPageList.size() < 2) {
                Debugger.println("Tumour page tumoursLandingPageList not more than one as expected:");
                SeleniumLib.takeAScreenShot("tumoursLandingPageList.jpg");
                return false;
            }
            String warning = tumoursLandingPageList.get(1).getAttribute("class");
            Debugger.println("Warning: " + warning);
            if (warning.contains("row-warning")) {
                Debugger.println("Tumour page tumoursLandingPageList contains warning:");
                SeleniumLib.takeAScreenShot("tumoursLandingPageList.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from tumourIsNotHighlighted:" + exp);
            SeleniumLib.takeAScreenShot("tumourIsNotHighlighted.jpg");
            return false;
        }
    }

    public boolean warningMessageIsNotDisplayed() {
        try {
            if (tumoursWarningMessage.size() > 0) {
                Debugger.println("Warning message present in Tumours page, but not expected.");
                SeleniumLib.takeAScreenShot("warningMessageIsNotDisplayed.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from warningMessageIsNotDisplayed:" + exp);
            SeleniumLib.takeAScreenShot("warningMessageIsNotDisplayed.jpg");
            return false;
        }
    }

    public boolean verifyTheElementsOnAddTumoursPageAreDisplayed() {

        try {
            if (!Wait.isElementDisplayed(driver, AddATumourPageTitle, 30)) {
                Debugger.println("AddATumourPageTitle, not present as expected.");
                SeleniumLib.takeAScreenShot("ElementsOnAddTumoursPage.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, descriptiveName, 5)) {
                Debugger.println("descriptiveName, not present as expected.");
                SeleniumLib.takeAScreenShot("ElementsOnAddTumoursPage.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, dateOfDiagnosisLabel, 5)) {
                Debugger.println("dateOfDiagnosisLabel, not present as expected.");
                SeleniumLib.takeAScreenShot("ElementsOnAddTumoursPage.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, tumourTypeLabel, 5)) {
                Debugger.println("tumourTypeLabel, not present as expected.");
                SeleniumLib.takeAScreenShot("ElementsOnAddTumoursPage.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, PathologyIdOrSampleIdLabel, 5)) {
                Debugger.println("PathologyIdOrSampleIdLabel, not present as expected.");
                SeleniumLib.takeAScreenShot("ElementsOnAddTumoursPage.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, dateMonth, 5)) {
                Debugger.println("dateMonth, not present as expected.");
                SeleniumLib.takeAScreenShot("ElementsOnAddTumoursPage.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, dateYear, 5)) {
                Debugger.println("dateYear, not present as expected.");
                SeleniumLib.takeAScreenShot("ElementsOnAddTumoursPage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying tumour stage page layout." + exp);
            SeleniumLib.takeAScreenShot("ElementsOnAddTumoursPage.jpg");
            return false;
        }
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
           // Debugger.println("........:"+header.getText());
            actualInformationText.add(header.getText().trim());
        }
        return actualInformationText;
    }

    public boolean clickEditTumourArrow() {
        try {
            if (!Wait.isElementDisplayed(driver, tumoursLandingPageTable, 30)) {
                Debugger.println("tumoursLandingPageTable not loaded.");
                SeleniumLib.takeAScreenShot("tumoursLandingPageTable.jpg");
                return false;
            }
            Wait.seconds(3);
            Actions.clickElement(driver, editTumourArrow);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clickEditTumourArrow:" + exp);
            SeleniumLib.takeAScreenShot("clickEditTumourArrow.jpg");
            return false;
        }
    }

    public boolean editTumourDescription() {
        try {
            if (!Wait.isElementDisplayed(driver, descriptiveName, 10)) {
                Actions.scrollToTop(driver);
            }
            Actions.clearTextField(descriptiveName);
            if(fillInTumourDescription() == null){
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in editTumourDescription:" + exp);
            SeleniumLib.takeAScreenShot("editTumourDescription.jpg");
            return false;
        }
    }

    public boolean editDateOfDiagnosis() {
        try {
            Actions.clearInputField(dateDay);
            Actions.clearInputField(dateMonth);
            Actions.clearInputField(dateYear);
            return fillInDateOfDiagnosis();
        }catch(Exception exp){
            Debugger.println("Exception in editDateOfDiagnosis:"+exp);
            SeleniumLib.takeAScreenShot("editDateOfDiagnosis.jpg");
            return false;
        }
    }

    public boolean editSpecimenID() {
        try {
            Actions.clearTextField(pathologyReportId);
            if(fillInSpecimenID() == null){
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in editSpecimenID:"+exp);
            SeleniumLib.takeAScreenShot("editSpecimenID.jpg");
            return false;
        }
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

    public boolean clickOnTheAddANewTumourTextLink() {
        try {
            if(!Wait.isElementDisplayed(driver, addAnotherTumourLink,10)){
                Debugger.println("AddAnotherTumourLink not displayed..\n"+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("addAnotherTumourLink.jpg");
                return false;
            }
        Click.element(driver, addAnotherTumourLink);
            return true;
        }catch(Exception exp){
            try{
                seleniumLib.clickOnWebElement(addAnotherTumourLink);
                return true;
            }catch(Exception exp1) {
                Debugger.println("Exception from AddAnotherTumourLink not displayed.." + exp + "\n" + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("addAnotherTumourLink.jpg");
                return false;
            }
        }
    }

    public String getDynamicQuestionsSnomedCTLabelText() {
        Wait.forElementToBeDisplayed(driver, snomedCTWorkingDiagnosisLabel);
        return snomedCTWorkingDiagnosisLabel.getText();
    }

    public String getTheCurrentTumourDescription() {
        return tumourDetails.getTumourDescription();
    }

    public String resetTheCurrentTumourDescription() {
        String resetValue = null;
        tumourDetails.setTumourDescription(resetValue);
        Debugger.println("Current TumourDescription to be null: " + tumourDetails.getTumourDescription());
        return resetValue;
    }

    public void setTheTotalNumberOfUncheckedTumourList() {
        int totalListOfUnselectedTumour = listOfUnselectedTumourList.size();
        tumourDetails.setTotalNumberOfUncheckedTumourList(totalListOfUnselectedTumour);
    }

    public int getTheTotalNumberOfUncheckedTumourList() {
        int totalListOfUnselectedTumour = listOfUnselectedTumourList.size();
        tumourDetails.setTotalNumberOfUncheckedTumourList(totalListOfUnselectedTumour);
        return tumourDetails.getTotalNumberOfUncheckedTumourList();
    }

    public boolean ensureTickMarkIsDisplayedNextToSampleType() {
        Wait.forElementToBeDisplayed(driver, TumourSVGTickMark);
        if (Wait.isElementDisplayed(driver, TumourSVGTickMark, 10)) {
            return true;
        }
        return false;
    }

    public boolean clickOnTheExistingTumourBox() {
        try {
            for (WebElement element : addedTumours) {
                if (!element.isSelected()) {
                    Actions.clickElement(driver, element);
                    Actions.acceptAlert(driver);
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("TumoursPage :clickOnTheExistingTumourBox: Exception found " + exp);
            SeleniumLib.takeAScreenShot("TumourBox.jpg");
            return false;
        }
    }

    public boolean tumourSelectedWithoutAnyMessage() {
        try {
            Wait.forElementToBeDisplayed(driver, AddATumourPageTitle);
            for (WebElement element : addedTumours) {
                if (element.getTagName().contains("checked")) {
                    if (tumourUpdatedMsg.isDisplayed()) {
                        Debugger.println("Tumour update message is present, not expected.");
                        SeleniumLib.takeAScreenShot("TumourUploadMessage.jpg");
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("TumoursPage :tumourSelectedWithoutAnyMessage: exception found " + exp);
            SeleniumLib.takeAScreenShot("TumourUploadMessage.jpg");
            return false;
        }
    }

    public boolean verifyLabelTextInTumourIsNotPresent(String expected) {
        try {
            if (!Wait.isElementDisplayed(driver, labelTextInTumour, 10)) {
                Debugger.println("labelTextInTumour not present.");
                SeleniumLib.takeAScreenShot("TumourPageLabelText.jpg");
                return false;
            }
            String actualLabelName = labelTextInTumour.getText();
            if (actualLabelName == null) {
                Debugger.println("labelTextInTumour not present.");
                SeleniumLib.takeAScreenShot("TumourPageLabelText.jpg");
                return false;
            }
            if (actualLabelName.contains(expected)) {
                SeleniumLib.takeAScreenShot("TumourPageLabelText.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("TumoursPage: getLabelTextInTumour: " + exp);
            SeleniumLib.takeAScreenShot("TumourPageLabelText.jpg");
            return false;
        }
    }

    public boolean fillTumourDiagnosisTable(String primaryTumour, String tumour) {
        try {
            if (topographyOfPrimaryTumourFieldList.size() == 0) {
                Debugger.println("Topography Of Primary Tumour Field not found");
                SeleniumLib.takeAScreenShot("TopographyOfPrimaryTumourField.jpg");
                return false;
            }
            if (topographyOfThisMetastaticDepositFieldList.size() == 0) {
                Debugger.println("Topography Of This Metastatic Deposit Field not found");
                SeleniumLib.takeAScreenShot("TopographyOfThisMetastaticDepositField.jpg");
                return false;
            }
            //Getting the last field and adding the value
            int lastSearchField = topographyOfPrimaryTumourFieldList.size() - 1;
            int lastSearchField2 = topographyOfThisMetastaticDepositFieldList.size() - 1;
            Actions.fillInValue(topographyOfPrimaryTumourFieldList.get(lastSearchField), primaryTumour);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.retrySelectRandomValueFromDropDown(dropdownValues);
            Wait.seconds(3);//As observed it take time to fill the search field

            Actions.fillInValue(topographyOfThisMetastaticDepositFieldList.get(lastSearchField2), tumour);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.retrySelectRandomValueFromDropDown(dropdownValues);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillTumourDiagnosisTable" + exp);
            SeleniumLib.takeAScreenShot("TumourDiagnosisOnTumourPage.jpg");
            return false;
        }
    }

    public boolean clicksOnAddAnotherLinkForTumourDiagnosis() {
        try {
            if (!Wait.isElementDisplayed(driver, addAnotherLinkForTumourDiagnosis, 10)) {
                Debugger.println("Add Another Link For Topography Of This Tumour Link not available : Tumour page\n"+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("AddAnotherLinkForTumourDiagnosis.jpg");
                return false;
            }
            Actions.clickElement(driver, addAnotherLinkForTumourDiagnosis);
            return true;
        } catch (Exception exp) {
            Debugger.println("Add Another Link For Topography Of This Tumour Link not available : Tumour page :" + exp+"\n"+driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("AddAnotherLinkForTumourDiagnosis.jpg");
            return false;
        }
    }

    public boolean clicksOnAddAnotherLinkForWorkingDiagnosisMorphology() {
        try {
            if (!Wait.isElementDisplayed(driver, addAnotherLinkForWorkingDiagnosisMorphology, 10)) {
                Debugger.println("Add Another Link For Working Diagnosis Morphology Link not available : Tumour page");
                SeleniumLib.takeAScreenShot("AddAnotherLinkForWorkingDiagnosis.jpg");
                return false;
            }
            Actions.clickElement(driver, addAnotherLinkForWorkingDiagnosisMorphology);
            return true;
        } catch (Exception exp) {
            Debugger.println("Add Another Link For Working Diagnosis Morphology Link not available : Tumour page :" + exp);
            SeleniumLib.takeAScreenShot("AddAnotherLinkForWorkingDiagnosis.jpg");
            return false;
        }
    }

    public boolean fillWorkingDiagnosis(String morphology) {
        try {
            if (workingDiagnosisMorphologyFieldList.size() == 0) {
                Debugger.println("Working Diagnosis Morphology Field not found");
                SeleniumLib.takeAScreenShot("WorkingDiagnosisMorphologyField.jpg");
                return false;
            }
            int lastSearchField = workingDiagnosisMorphologyFieldList.size() - 1;
            Actions.fillInValue(workingDiagnosisMorphologyFieldList.get(lastSearchField), morphology);
            Wait.forElementToBeDisplayed(driver, dropdownValue);
            Actions.retrySelectRandomValueFromDropDown(dropdownValues);
            return true;
        } catch (Exception exp) {
            Debugger.println(" Exception from : TumoursPage :answerWorkingDiagnosis: Exception found " + exp);
            SeleniumLib.takeAScreenShot("WorkingDiagnosis.jpg");
            return false;
        }
    }

    public boolean verifyTumourSubTitle(String expectedSubTitle) {
        if (!Wait.isElementDisplayed(driver, TumourSubTitle, 10)) {
            Debugger.println("TumourSubTitle: element not displayed.");
            SeleniumLib.takeAScreenShot("verifyTumourSubTitle.jpg");
            return false;
        }
        String actualTumourSubTitle = TumourSubTitle.getText();
        if (!actualTumourSubTitle.contains(expectedSubTitle)){
            Debugger.println("Expected Tumour SubTitle:" + expectedSubTitle + ", But actual:" + actualTumourSubTitle);
            SeleniumLib.takeAScreenShot("verifyTumourSubTitle.jpg");
            return false;
         }
        return true;
    }
}
