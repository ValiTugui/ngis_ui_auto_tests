package co.uk.gel.proj.pages;

import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;

public class ClinicalIndicationsTestSelectPage {

    WebDriver driver;

    public ClinicalIndicationsTestSelectPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class*='icon']")
    public WebElement clinicalIndicationIcon;

    @FindBy(css = "div[class*='mainSection']")
    public WebElement clinicalIndicationsHeader;

    @FindBy(css = "a[class*='tab']")
    public List<WebElement> clinicalIndicationTabs;

    @FindBy(css = "div[class*='eligibilityCard']")
    public List<WebElement> eligibilityCriteriaSections;

    @FindBy(css = "div[class*='markdownStyling']")
    public List<WebElement> whoToTestText;

    @FindBy(id = "Shape")
    public List<WebElement> whoToTestIcon;

    @FindBy(id = "WhenToTest_Icon")
    public List<WebElement> whenToTestIcon;

    @FindBy(css = "h3[class*='meta']")
    public WebElement clinicalIndicationSubHeader;

    @FindBy(css = ".btn-secondary")
    public List<WebElement> goToClinicalIndicationsButtonInPopup;

    @FindBy(xpath = "//*[contains(@class,'package')]//child::h1")
    public WebElement testPackageContainerHeader;

    @FindBy(css = "*[class*='subHeader']")
    public WebElement testPackageContainerDescription;

    @FindBy(css = "*[class*='testCard']")
    public List<WebElement> testsFromTestPackageList;

    @FindBy(xpath = "//*[contains(@class,'styles_infoIcon')]")
    public WebElement testInfoIcon;

    @FindBy(css = "g[id*='Illustration/blue/test']")
    public WebElement testPackagePopupIcon;

    @FindBy(css = "div[class*='turnAroundTimes']")
    public WebElement testPackagePopupTime;

    @FindBy(css = "div[class*='testPackageProps']")
    public WebElement testPackagePopupProps;

    @FindBy(css = "*[class*='styles_mainHeader']")
    public WebElement testPackagePopupTitle;

    @FindBy(xpath = "//div[contains(@class,'ctaBlock')]//child::a")
    public WebElement goToTestPageButtonFromPopup;

    @FindBy(css = "div[class*='card_']")
    public List<WebElement> furtherInfoSections;

    @FindBy(css = ".btn.btn-md.btn-primary")
    public WebElement startTestOrderButton;

    @FindBy(css = "div[class*='closeButton']")
    public WebElement closePopupButton;

    @FindBy(xpath = "//div[contains(@class,'back')]//descendant::a")
    public WebElement backToSearch;

    @FindBy(xpath = "//*[contains(@class,'styles_helix')]")
    public List<WebElement> loadingWheel;

    @FindBy(css = "h2[class*='relatedContainer__header']")
    public WebElement loadingText;

    @FindBy(xpath = "//*[contains (@class, 'styles_relatedContainer')]/ul")
    public WebElement clinicalIndicationsResultContainer;

    public void clickStartReferralButton() {
        Click.element(driver, startTestOrderButton);
    }

    public boolean validateIfLoadingWheelIsPresent() {
        return loadingWheel.size() >= 0;
    }

    public boolean validateIfCorrectTextIsDisplayed(WebElement element, String expected) {
        String actual = element.getText();
        return actual.equalsIgnoreCase(expected);
    }

    public boolean validateIfWrongTextIsNotDisplayed(WebElement element, String expected) {
        String actual = element.getText();
        return !actual.equalsIgnoreCase(expected);
    }

    public void waitUntilClinicalIndicationsResultsContainerIsLoaded() {
        Wait.forElementToBeDisplayed(driver, clinicalIndicationsResultContainer);
    }

    public boolean isTabSelected(String tabName) {
        switch (tabName) {
            case "Eligibility Criteria":
            case "Clinical Indications": {
                return clinicalIndicationTabs.get(0).getAttribute("class").contains("activeTab");
            }
            case "Test Package":
            case "Test details": {
                return clinicalIndicationTabs.get(1).getAttribute("class").contains("activeTab");
            }
            case "Further Info":
            case "Labs": {
                return clinicalIndicationTabs.get(2).getAttribute("class").contains("activeTab");
            }
            case "Order process": {
                return clinicalIndicationTabs.get(3).getAttribute("class").contains("activeTab");
            }
        }
        return false;
    }

    public void selectTab(String tabName) {
        switch (tabName) {
            case "Eligibility Criteria": {
                Click.element(driver, clinicalIndicationTabs.get(0));
                Debugger.println(clinicalIndicationTabs.get(0).getAttribute("href"));
                break;
            }
            case "Test Package": {
                Click.element(driver, clinicalIndicationTabs.get(1));
                Debugger.println(clinicalIndicationTabs.get(1).getAttribute("href"));
                break;
            }
            case "Further Info": {
                Click.element(driver, clinicalIndicationTabs.get(2));
                Debugger.println(clinicalIndicationTabs.get(2).getAttribute("href"));
                break;
            }
            case "Order process": {
                Click.element(driver, clinicalIndicationTabs.get(3));
                Debugger.println(clinicalIndicationTabs.get(3).getAttribute("href"));
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + tabName);
        }
    }

    public boolean isTabPresent(Integer tabCount, String tab1, String tab2, String tab3, String tab4) {
        return ((clinicalIndicationTabs.size() == tabCount) && (clinicalIndicationTabs.get(0).getText().matches(tab1)) && (clinicalIndicationTabs.get(1).getText().matches(tab2)) && (clinicalIndicationTabs.get(2).getText().matches(tab3)) && (clinicalIndicationTabs.get(3).getText().matches(tab4)));
    }

    public void testPackagePopUpValidations() {
        testPackagePopupIcon.isDisplayed();
        testPackagePopupProps.isDisplayed();
        testPackagePopupTime.isDisplayed();
        testPackagePopupTitle.isDisplayed();
    }

    public boolean checkTestPagePopUpTitleMatchesSearchedText() {
        return testsFromTestPackageList.get(0).getText().contains(testPackagePopupTitle.getText());
    }
}