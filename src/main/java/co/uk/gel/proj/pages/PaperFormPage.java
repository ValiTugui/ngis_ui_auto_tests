package co.uk.gel.proj.pages;

import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class PaperFormPage {

    WebDriver driver;

    public PaperFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'main')]//descendant::strong")
    public WebElement yourOrderText;

    @FindBy(css = "h2[class*='stepHeader']")
    public WebElement paperFormHeader;

    @FindBy(css = "p[class*='subHeader']")
    public WebElement orderEntitySubHeader;

    @FindBy(css = "p[class*='explanatoryText']")
    public WebElement chooseClinicalSubHeader;

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

    @FindBy(css = "p[class*='boldText']")
    public List<WebElement> selectedOrderEntityDetailsTitles;

    @FindBy(css = "p[class*='text']")
    public List<WebElement> selectedOrderEntityDetailsTexts;

    @FindBy(css = "p[class*='styles_headerText']")
    public WebElement confirmTestsSubHeader;

    @FindBy(css = "ul[class*='testPackage']")
    public WebElement confirmTestsPackage;

    @FindBy(css = "h3[class*='subCaption']")
    public WebElement confirmTestsSubCaption;

    @FindBy(css = "div[class*='card']")
    public List<WebElement> testsPackage;

    @FindBy(css = "h2[class*='name']")
    public WebElement testsNameFromConfirmTestsPage;

    @FindBy(css = "div[class*='times']")
    public List<WebElement> testsTimesFromConfirmTestsPage;

    @FindBy(xpath = "//div[contains(@class,'header')]//child::span")
    public List<WebElement> testsHeadersFromConfirmTestsPage;

    @FindBy(css = "div[class*='info']")
    public WebElement testsInfoFromConfirmTestsPage;

    @FindBy(css = "h2[class*='header']")
    public WebElement sampleTypeAndState;

    @FindBy(css = "p[class*='targetedGenes']")
    public List<WebElement> targetedGenes;

    @FindBy(css = "a[class*='btn-secondary']")
    public List<WebElement> downloadButton;

    @FindBy(css = "div[class*='alertWarning']")
    public WebElement warningBanner;

    @FindBy(xpath = "//div [contains (@class, 'styles_content')]/strong")
    public WebElement warningBannerText;

    @FindBy(css = "div[class*='body']")
    public List<WebElement> downloadSections;

    @FindBy(css = "div[class*='body']")
    public List<WebElement> downloadContainers;

    @FindBy(css = "h3[class*='header']")
    public List<WebElement> stepsTitles;

    @FindBy(css = "div[class*='main']")
    public WebElement nonRoutedNextStepsContent;

    @FindBy(xpath = "//div[contains(@class, 'sampleDescriptions')]//descendant::p")
    public WebElement nonRoutedNextStepsSampleDescription;

    @FindBy(css = "p[class*='headerText']")
    public WebElement nextStepsSubHeader;

    @FindBy(css = "div[class*='container']")
    public WebElement routedNextStepsContent;

    @FindBy(css = "*[class*='panelTitle']")
    public WebElement sendFormsAndSamplesHospitalName;

    @FindBy(css = "span[class*='addressTitleText']")
    public WebElement addressPanelTitle;

    @FindBy(xpath = "//*[contains(@class,'addressTitle')]//following::div")
    public WebElement addressPanelBody;

    @FindBy(css = "div[class*='boldText']")
    public List<WebElement> nonRoutedNextStepsRequestingOrganisation;

    @FindBy(css = "p[class*='boldText']")
    public List<WebElement> nonRoutedNextStepsTests;

    @FindBy(css = ".btn.btn-md.btn-primary")
    public WebElement printPageButton;

    @FindBy(css = "span[class*='text']")
    public WebElement canNotFindMyEntityLink;

    @FindBy(xpath = "//*[contains(@class,'subHeader')]//following-sibling::div[3]")
    public WebElement noResultsMessage;

    @FindBy(css = "div[class*='slide-panel_show']")
    public WebElement filterHelpContentPaperForm;

    @FindBy(css = "div[class*='container']")
    public List<WebElement> elementFromFilterHelpContentPaperForm;

    @FindBy(css = "div[class*='main']")
    public WebElement clinicalIndicationName;

    @FindBy(linkText = "Cancel order")
    public WebElement cancelOrderLink;

    @FindBy(css = "div[class*='card']")
    public WebElement clinicalCard;

    @FindBy(css = "div[class*='radioButton']")
    public WebElement clinicalCardCheckbox;

    @FindBy(css = "div[class*='icon']")
    public List<WebElement> iconFromClinicalCard;

    @FindBy(xpath = "//*[contains(@class,'mediaBlock')]//descendant::h3")
    public WebElement clinicalCardTitle;

    @FindBy(css = "div[class*='whoToTest']")
    public WebElement clinicalCardWhoToTest;

    @FindBy(css = "h3[class*='cellTitle']")
    public List<WebElement> routedSecondStepCellTitles;

    @FindBy(css = "div[class*='cellContent']")
    public List<WebElement> routedSecondStepCellContent;

    @FindBy(css = "span[class*='cellBoldText']")
    public List<WebElement> testDetailsOnNextPage;

    @FindBy(css = "div[class*='cellBoldText']")
    public List<WebElement> routedSecondStepCellBoldTexts;

    @FindBy(css = "div[class*='cellText']")
    public List<WebElement> routedSecondStepCellTexts;

    @FindBy(css = "button[class*='resetTestsButton']")
    public WebElement resetToDefaultSelectionButton;

    @FindBy(css = "*[class*='ctaOnlineBtn']")
    public WebElement signInToOnlineServiceButton;

    @FindBy(css = "*[class*='testCardContainer']")
    public WebElement testCardContainer;

    @FindBy(css = "*[class*='ctaPdfOrOnlineTitle']")
    public WebElement orderGenomicTestPageTitle;

    @FindBy(css = ".btn.btn-md.btn-primary")
    public WebElement usePDFOrderFormButton;

    @FindBy(css = "*[class*='tumourWarning']")
    public WebElement cancerOfflineOrderwarningBanner;

    @FindBy(xpath = "//*[contains(@class,'styles_list')]")
    public WebElement offlineOrderContainer;

    @FindBy(xpath = "//*[contains(@class,'styles_card')]/label")
    public WebElement testList;

    String entitySuggestionLocatior = "div[class*='suggestions']";

    public void fillInSpecificKeywordInSearchField(String keyword) {
        Wait.forElementToBeDisplayed(driver, orderEntitySearchField);
        orderEntitySearchField.clear();
        orderEntitySearchField.sendKeys(keyword);
    }

    public void checkThatEntitySuggestionsAreDisplayed() {
        Wait.forNumberOfElementsToBeGreaterThan(driver, By.cssSelector(entitySuggestionLocatior), 0);
    }

    public void clickSignInToTheOnlineServiceButton() {
        Click.element(driver, signInToOnlineServiceButton);
    }

    public void selectRandomEntityFromSuggestionsList() {
        Click.element(driver, orderEntitySearchSuggestionsList.get(new Random().nextInt(orderEntitySearchSuggestionsList.size())));
    }

    public void clickContinueButton() {
        Wait.forElementToBeDisplayed(driver, continueButton.get(0));
        Click.element(driver, continueButton.get(0));
    }

    public void checkThatReviewTestSelectionIsOpened() {
        Wait.forElementToBeDisplayed(driver, testCardContainer);
        Wait.forElementToBeDisplayed(driver, paperFormHeader);
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
}

