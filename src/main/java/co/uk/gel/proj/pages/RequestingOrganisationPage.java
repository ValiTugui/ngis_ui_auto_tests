package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RequestingOrganisationPage {

    WebDriver driver;

    public RequestingOrganisationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class*='location-result']")
    public WebElement organisationDetailsContainer;

    @FindBy(css = "p[class*='location-result__detail-heading']")
    public List<WebElement> organisationDetailHeader;

    @FindBy(css = "p[class*='location-result__detail-text']")
    public List<WebElement> organisationDetailText;

    @FindBy(css = "[class*='referral-navigation__button']")
    public WebElement saveAndContinueButton;

    @FindBy(xpath = "//strong[contains(text(),'0 results')]")
    public WebElement zeroResulsFoundLabel;

    @FindBy(css = "[class*='page-title']")
    public WebElement orderingEntityPageTitle;

    @FindBy(css = "[class*='ordering-entity__sub-title']")
    public WebElement orderEntityPageSubtitle;

    @FindBy(css = "[class*='search-input__icon']")
    public WebElement orderingEntitySearchIcon;

    @FindBy(css = "h1[class*='styles_stepHeader']")
    public WebElement testDirectoryOrderingEntityPageTitle;

    @FindBy(css = "p[class*='styles_subHeader']")
    public WebElement testDirectoryOrderEntityPageSubtitle;

    @FindBy(css = "svg[class*='styles_searchIcon']")
    public WebElement testDirectoryOrderingEntitySearchIcon;

    @FindBy(css = "button[class*='styles_button']")
    public WebElement toolTipIcon;

    @FindBy(css = "span[class*='styles_text']")
    public WebElement toolTipText;

    @FindBy(xpath = "//div[contains(@class, 'styles_container')]")
    public WebElement testDirectoryOrderingEntityPanelContainer;

    @FindBy(css = "div[class*='slide-panel_container']")
    public WebElement slidePanelContainer;

    @FindBy(css = "div[class*='slide-panel_closeButton']")
    public WebElement slidePanelCloseButton;

    @FindBy(css = "h1[class*='styles_header']")
    public WebElement slidePanelHeader;

    @FindBy(css = "p[class*='styles_bodyText']")
    public WebElement slidePanelBodyText;

    @FindBy(css = "div[class*='no-results']")
    public WebElement noSearchResult;

    public boolean verifyOrganisationDetails() {
        Wait.forElementToBeDisplayed(driver, organisationDetailsContainer);
        return organisationDetailsContainer.getText().contains(organisationDetailText.get(0).getText());
    }

    public boolean checkTheContinueButtonIsClickable() {
        Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
        return saveAndContinueButton.isEnabled();
    }

    public void clickTheContinueButton() {
        Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
        saveAndContinueButton.click();
    }

    public boolean shouldSeeNoResultsOnThePage() {
        Wait.forElementToBeDisplayed(driver, zeroResulsFoundLabel);
        return zeroResulsFoundLabel.isDisplayed();
    }

    public boolean checkPageTitleInfo(String pageTitle) {
        Wait.forElementToBeDisplayed(driver, orderingEntityPageTitle);
        return orderingEntityPageTitle.getText().contains(pageTitle);
    }

    public boolean checkOrderingEntityPageLabel() {
        return orderEntityPageSubtitle.isDisplayed();
    }

    public boolean checkSearchIcon() {
        return orderingEntitySearchIcon.isDisplayed();
    }

    public boolean checkToolTipInfo(String toolTipTextValue) {
        Wait.forElementToBeDisplayed(driver, toolTipIcon);
        toolTipIcon.isDisplayed();
        toolTipText.getText().matches(toolTipTextValue);
        return true;
    }

    public void clickToolTipIcon() {
        Click.element(driver, toolTipIcon);
    }

    public boolean checkSlidePanelInfo(String headerText, String bodyText) {
        Wait.forElementToBeDisplayed(driver, slidePanelContainer);
        slidePanelContainer.isDisplayed();
        slidePanelCloseButton.isDisplayed();
        slidePanelHeader.getText().matches(headerText);
        slidePanelBodyText.getText().matches(bodyText);
        return true;
    }

    public void clickSlidePanelCloseButton() {
        Click.element(driver, slidePanelCloseButton);
    }

    public boolean checkRequestingOrganisationPageInfo(String pageTitle, String copyText) {
        try {
            Wait.forElementToBeDisplayed(driver, testDirectoryOrderingEntityPanelContainer);
            testDirectoryOrderingEntityPanelContainer.isDisplayed();
            testDirectoryOrderingEntitySearchIcon.isDisplayed();
            testDirectoryOrderingEntityPageTitle.getText().matches(pageTitle);
            testDirectoryOrderEntityPageSubtitle.getText().matches(copyText);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from verifying checkRequestingOrganisationPageInfo: "+exp);
            return false;
        }
    }

    public String getNoResultMessage(){
        try {
        Wait.forElementToBeDisplayed(driver, noSearchResult);
        return Actions.getText(noSearchResult);
        }catch(Exception exp){
            Debugger.println("Exception during RequestingOrganisationPage -> getNoResultMessage() : "+exp);
            SeleniumLib.takeAScreenShot("NoResultMessage.jpg");
            return null;
        }
    }

}
