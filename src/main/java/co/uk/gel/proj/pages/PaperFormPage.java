package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class PaperFormPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public PaperFormPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h1[class*='stepHeader']")
    public WebElement paperFormHeader;

    @FindBy(xpath = "//div[contains(@class,'paragraph')]/p")
    public WebElement orderEntitySubHeader;

    @FindBy(css = "input[class*='input']")
    public WebElement orderEntitySearchField;

    @FindBy(css = "p[class*='instructions']")
    public WebElement orderEntitySearchInstructions;

    @FindBy(css = "li[class*='suggestion']")
    public List<WebElement> orderEntitySearchSuggestionsPanel;

    @FindBy(css = "li[class*='suggestion']")
    public List<WebElement> orderEntitySearchSuggestionsList;

    @FindBy(css = ".btn.btn-lg.btn-primary")
    public List<WebElement> continueButton;

    @FindBy(css = "div[class*='selected']")
    public WebElement selectedOrderEntityInformationBox;

    @FindBy(css = "h4[class*='selectedHeader']")
    public WebElement selectedOrderEntityName;

    @FindBy(css = "p[class*='styles_headerText']")
    public WebElement confirmTestsSubHeader;

    @FindBy(css = "h3[class*='subCaption']")
    public WebElement confirmTestsSubCaption;

    @FindBy(css = "div[class*='card']")
    public List<WebElement> testsPackage;

    @FindBy(css = "a[class*='btn-secondary']")
    public List<WebElement> downloadButton;

    @FindBy(css = "div[class*='alertWarning']")
    public WebElement warningBanner;

    @FindBy(xpath = "//div [contains (@class, 'styles_content')]/strong")
    public WebElement warningBannerText;

    @FindBy(css = "div[class*='body']")
    public List<WebElement> downloadSections;

    @FindBy(css = "h3[class*='header']")
    public List<WebElement> stepsTitles;

    @FindBy(css = "div[class*='container']")
    public WebElement routedNextStepsContent;

    @FindBy(css = "*[class*='panelTitle']")
    public WebElement sendFormsAndSamplesHospitalName;

    @FindBy(css = "span[class*='addressTitleText']")
    public WebElement addressPanelTitle;

    @FindBy(xpath = "//*[contains(@class,'addressTitle')]//following::div")
    public WebElement addressPanelBody;

    @FindBy(css = ".btn.btn-md.btn-primary")
    public WebElement printPageButton;

    @FindBy(linkText = "Cancel order")
    public WebElement cancelOrderLink;

    @FindBy(css = "div[class*='card']")
    public WebElement clinicalCard;

    @FindBy(css = "div[class*='radioButton']")
    public WebElement clinicalCardCheckbox;

    @FindBy(css = "*[class*='ctaOnlineBtn']")
    public WebElement signInToOnlineServiceButton;

    @FindBy(css = "*[class*='testCardContainer']")
    public WebElement testCardContainer;

    @FindBy(css = ".btn.btn-md.btn-primary")
    public WebElement usePDFOrderFormButton;

    @FindBy(xpath = "//*[contains(@class,'styles_list')]")
    public WebElement offlineOrderContainer;

    @FindBy(xpath = "//*[contains(@class,'styles_card')]/label")
    public WebElement testList;

    String entitySuggestionLocatior = "div[class*='suggestions']";

    public boolean fillInSpecificKeywordInSearchField(String keyword) {
        try {
            if (!Wait.isElementDisplayed(driver, orderEntitySearchField, 30)) {
                Debugger.println("Could not find orderEntitySearchField..Trying with SeleniumLib.");
                seleniumLib.sendValue(orderEntitySearchField,keyword);
                Wait.seconds(2);
                return false;
            }
            orderEntitySearchField.clear();
            orderEntitySearchField.sendKeys(keyword);
            Wait.seconds(2);
            return true;
        }catch(Exception exp){
            try{
                seleniumLib.sendValue(orderEntitySearchField,keyword);
                Wait.seconds(2);
                return true;
            }catch(Exception exp1){
                Debugger.println("Exception1 from orderEntitySearchField.."+exp1+"\n"+driver.getCurrentUrl());
                return false;
            }
        }
    }

    public boolean fillInSpecificValueInSearchField(String keyword) {
        try {
            if (!Wait.isElementDisplayed(driver, orderEntitySearchField, 30)) {
                Debugger.println("Could not find orderEntitySearchField..Trying with SeleniumLib.");
                seleniumLib.sendValue(orderEntitySearchField, keyword);
                Wait.seconds(2);
                return false;
            }
            orderEntitySearchField.clear();
            orderEntitySearchField.sendKeys(keyword);
            Click.element(driver, orderEntitySearchSuggestionsList.get(new Random().nextInt(orderEntitySearchSuggestionsList.size())));
            Wait.seconds(2);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception1 from orderEntitySearchField.." + exp + "\n" + driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("OrderEntity.jpg");
            return false;
        }
    }
    public boolean checkThatEntitySuggestionsAreDisplayed() {
        try {
            Wait.seconds(2);
            Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector(entitySuggestionLocatior), 0);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in Order Entity Suggestion List display."+exp+"\n"+driver.getCurrentUrl());
            return false;
        }
    }

    public boolean clickSignInToTheOnlineServiceButton() {
        try {
            //Debugger.println("clickSignInToTheOnlineServiceButton: ");
            if(!Wait.isElementDisplayed(driver,signInToOnlineServiceButton,60)){
                Debugger.println("Sign Into Online Service Button not displayed even after waiting time 60s...failing."+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("ClickSignInButton.jpg");
                return false;
            }
            Click.element(driver, signInToOnlineServiceButton);
            Wait.seconds(8);
            return true;
        } catch (Exception exp) {
            Debugger.println("PaperFormPage: Exception from login to signInToOnlineServiceButton: " + exp);
            SeleniumLib.takeAScreenShot("ClickSignInButton.jpg");
            return false;
        }
    }

    public boolean selectRandomEntityFromSuggestionsList() {
        try {
            if(orderEntitySearchSuggestionsList.size() == 0){
                Debugger.println("No Organisation list loaded for the search."+driver.getCurrentUrl());
                 return false;
            }
            Click.element(driver, orderEntitySearchSuggestionsList.get(new Random().nextInt(orderEntitySearchSuggestionsList.size())));
            return true;
        }catch(Exception exp){
            try{
              By firstElement = By.xpath("//div[contains(@class,'suggestions')]//ul/li[1]");
              seleniumLib.clickOnElement(firstElement);
              return true;
            }catch(Exception exp1) {
                Debugger.println("Exception from Selecting Requesting Organization: " + exp+"\n"+driver.getCurrentUrl());
                return false;
            }
        }
    }

    public void selectFirstEntityFromSuggestionsList() {
        Click.element(driver, orderEntitySearchSuggestionsList.get(0));
    }

    public void clickContinueButton() {
        Wait.forElementToBeDisplayed(driver, continueButton.get(0));
        Click.element(driver, continueButton.get(0));
    }

    public void clickCancelOrderLink() {
        Click.element(driver, cancelOrderLink);
    }

    public boolean checkThatReviewTestSelectionIsOpened() {
        try{
        if(!Wait.isElementDisplayed(driver,testCardContainer,30)){
            Debugger.println("The test card is not displayed."+driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("ReviewTestSelection.jpg");
            return false;
        }
        if(!Wait.isElementDisplayed(driver,paperFormHeader,30)){
            Debugger.println("The page title is not displayed."+driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("ReviewTestSelection.jpg");
            return false;
        }
        return true;
    }catch (Exception exp){
        Debugger.println("Exception from checkThatReviewTestSelectionIsOpened: "+exp+"\n"+driver.getCurrentUrl());
        SeleniumLib.takeAScreenShot("ReviewTestSelection.jpg");
        return false;
        }
    }

    public boolean checkThatTestIsSelected() {
        Wait.forElementToBeDisplayed(driver, testCardContainer);
        return (testList.getAttribute("class").contains("checked"));
    }

    public boolean checkTheWarningText(String expected) {
        return warningBannerText.getText().contentEquals(expected);
    }

    public boolean checkTheReviewSelectionPageHeaderText(String headerText) {
        return confirmTestsSubHeader.getText().contentEquals(headerText);
    }

    public boolean confirmOrderingEntityLabelText(String expectedLabelText) {
        Wait.forElementToBeDisplayed(driver, orderEntitySubHeader);
        orderEntitySubHeader.isDisplayed();
        return orderEntitySubHeader.getText().matches(expectedLabelText);
    }

    public boolean confirmOrderingEntitySearchFieldPlaceholderText(String expectedPlaceholderText) {
        Wait.forElementToBeDisplayed(driver, orderEntitySearchField);
        return orderEntitySearchField.getAttribute("placeholder").matches(expectedPlaceholderText);
    }

    public boolean checkThatStepsTitlesForRoutedClinicalIndicationAreCorrect(String buttonName, String sectionName1, String sectionName2) {
        Wait.forElementToBeDisplayed(driver, printPageButton);
        return ((printPageButton.getText().matches(buttonName)) && (stepsTitles.get(0).getText().matches(sectionName1)) && (stepsTitles.get(1).getText().matches(sectionName2)));
    }

    public boolean checkThatNameOfDowmloadSectionIsDisplayed(String sectionName1, String sectionName2, String sectionName3) {
        Wait.forElementToBeDisplayed(driver, routedNextStepsContent);
        for (int i = 0; i < downloadSections.size(); i++) {
            Wait.forElementToBeDisplayed(driver, downloadSections.get(i));
        }
        Debugger.println("No of sections for " + AppConfig.getSearchTerm() + " are " + downloadSections.size());
        switch (downloadSections.size()) {
            case 2: {
                return ((downloadSections.get(0).findElement(By.tagName("h3")).getText().matches(sectionName1)) && (downloadSections.get(1).findElement(By.tagName("h3")).getText().matches(sectionName2)));
            }
            case 3: {
                return ((downloadSections.get(0).findElement(By.tagName("h3")).getText().matches(sectionName1)) && (downloadSections.get(1).findElement(By.tagName("h3")).getText().matches(sectionName2)) && (downloadSections.get(2).findElement(By.tagName("h3")).getText().matches(sectionName3)));
            }
            default:
                throw new IllegalStateException("Section Mismatch: " + downloadSections.size());
        }
    }

    public boolean checkThataddressOfLabIsDisplayed(String placeSearchTerm) {
        Wait.forElementToBeDisplayed(driver, addressPanelTitle);
        return (!Actions.getText(sendFormsAndSamplesHospitalName).isEmpty() && (Actions.getText(addressPanelTitle).matches("Send to")) && (Actions.getText(addressPanelBody).contains(placeSearchTerm)));
    }

    public boolean checkThatDownloadButtonsAreDisplayed() {
        if(!Wait.isElementDisplayed(driver,routedNextStepsContent,30)){
            Debugger.println("routedNextStepsContent not present.");
            SeleniumLib.takeAScreenShot("DownloadButtonContainer.jpg");
            return false;
        }
        if(downloadSections.size() != downloadButton.size()){
            Wait.seconds(10);//Wait 10 seconds for all sections download buttons are displayed
        }
        return (downloadSections.size() == downloadButton.size());
    }

    public boolean verifyTheDownloadButtonLabel(String buttonName){
        for (int i = 0; i < downloadButton.size(); i++) {
            if(!downloadButton.get(i).getText().matches(buttonName)){
                Debugger.println("Download button label is not as expected:"+buttonName);
                SeleniumLib.takeAScreenShot("DownLoadButtonName.jpg");
                return false;
            }
        }
        return true;
    }

    public boolean checkCancelOrderLinkIdDisplayed(String linkName) {
        Wait.forElementToBeDisplayed(driver, cancelOrderLink);
        cancelOrderLink.isDisplayed();
        cancelOrderLink.getText().matches(linkName);
        return true;
    }

    public boolean checkThatInformationEntityIsDisplayed() {
        Wait.forElementToBeDisplayed(driver, selectedOrderEntityInformationBox);
        return selectedOrderEntityInformationBox.isDisplayed();
    }

    public boolean checkThatSelectedEntityNameIsTheSameAsTheSearchValue() {
        Wait.forElementToBeDisplayed(driver, orderEntitySearchField);
        String entitySearchValue = orderEntitySearchField.getAttribute("value");
        String selectedEntityName = selectedOrderEntityName.getText();
        return entitySearchValue.matches(selectedEntityName);
    }

    public void checkContinueIsClickable() {
        Wait.forElementToBeClickable(driver, continueButton.get(0));
    }

    public void clickOnUsePDFOrderFormButton(){
        Actions.clickElement(driver,usePDFOrderFormButton);
    }

}

