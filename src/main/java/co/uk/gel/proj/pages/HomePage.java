package co.uk.gel.proj.pages;

import co.uk.gel.lib.Click;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String tabTitle = "NHS England | Public Genetic Test Directory";

    @FindBy(css = "div[class*='header']")
    public WebElement header;

    @FindBy(xpath = "//*[contains(@class,'searchPanel')]//child::input")
    public WebElement searchField;

    @FindBy(css = "button[class*='searchButton']")
    public WebElement searchIcon;

    @FindBy(css = "div[class*='slide-panel_closeButton']")
    public WebElement helpMenuXButton;

    @FindBy(css = "button[class*='clearButton']")
    public WebElement clearSearchFieldButton;

    @FindBy(css = "div[class*='searchCaption']")
    public WebElement searchCaptionText;

    @FindBy(css = "div[class*='noResultsContainer']")
    public WebElement noResultsPageContainer;

    @FindBy(css = "*[class*='noResultsImg']")
    public WebElement noSearchResultsImage;

    @FindBy(css = "button[class*='tooltipTrigger']")
    public WebElement questionMarkButton;

    @FindBy(css = "ul[class*='imageRadioButtons']")
    public WebElement filterButtons;

    @FindBy(xpath = "//div[contains(text(), 'Clinical Indications')]")
    public WebElement clinicalIndicationsTab;

    @FindBy(xpath = "//div[contains(text(), 'Tests')]")
    public WebElement testsTab;

    @FindBy(xpath = "//div[contains(text(), 'Clinical Indications')]/span")
    public WebElement clinicalIndicationsTabValue;

    @FindBy(xpath = "//div[contains(text(), 'Tests')]/span")
    public WebElement testsTabValue;

    @FindBy(xpath = "//div/ul/li[1]/label")
    public WebElement rareAndInheritedDiseasesChkBox;

    @FindBy(xpath = "//div/ul/li[3]/label")
    public WebElement tumorChkBox;

    @FindBy(xpath = "//div/ul/li[2]/label")
    public WebElement inheritedCancerPredispositionChkBox;

    @FindBy(xpath = "//div/ul/li[4]/label")
    public WebElement otherChkBox;

    @FindBy(className = "btn btn-secondary btn-xs")
    public WebElement clearAllButton;

    @FindBy(css = "a[class*='tab']")
    public List<WebElement> tabs;

    @FindBy(css = "a[class*='link']")
    public List<WebElement> resultsPanels;

    @FindBy(id = "Icon_Tick_Vector")
    public List<WebElement> tickIcon;

    @FindBy(css = "div[class*='appliedFilters']")
    public WebElement appliedFiltersMessage;

    @FindBy(xpath = "//*[contains(@class,'infoBanner__text')]")
    public WebElement tabMessage;

    @FindBy(css = "li[class*='previous']")
    public WebElement previousPageArrow;

    @FindBy(css = "li[class*='next']")
    public WebElement nextPageArrow;

    @FindBy(css = "a[aria-label='Page 2 is your current page']")
    public WebElement selectedSecondPage;

    @FindBy(css = "a[aria-label='Page 1 is your current page']")
    public WebElement selectedFirstPage;

    @FindBy(xpath = "//*[contains(@class,'containerInner')]//descendant::button")
    public List<WebElement> cookiesUnderstandButton;

    @FindBy(css = "div[class*='content']")
    public WebElement filterHelpContent;

    @FindBy(css = "div[class*='subHeader']")
    public List<WebElement> filterHelpSubHeaders;

    @FindBy(css = "div[class*='icon']")
    public List<WebElement> filterHelpIcons;

    @FindBy(css = "p[class*='bodyText']")
    public List<WebElement> filterHelpbodyTexts;

    @FindBy(css = "ul[class*='list']")
    public WebElement filterHelpList;

    @FindBy(css = "div[class*='slide-panel_container']")
    public List<WebElement> filterHelpMenuContainer;

    @FindBy(css = "div[class*='checkbox']")
    public WebElement checkboxForSingleVariant;

    @FindBy(css = "div[id*='select']")
    public List<WebElement> pleaseSelectDropdown;

    @FindBy(xpath = "//*[contains(@class,'imageCheckboxes')]//descendant::li")
    public WebElement ageIcon;

    @FindBy(css = "span[class*='italic']")
    public List<WebElement> numberOfResults;

    @FindBy(css = "div[class*='homeLink']")
    public WebElement genomicMedicineServiceLogo;

    @FindBy(css = "div[class*='logoContainer']")
    public WebElement nhsEnglandLogo;

    @FindBy(css = "div[class*='contentContainer']")
    public List<WebElement> footerLogo;

    @FindBy(css = "*[class*='footerLinks']")
    public List<WebElement> footerLinks;

    @FindBy(css = "h4[class*='tableLabel']")
    public List<WebElement> testPackageTitle;

    @FindBy(css = "table[class*='table']")
    public List<WebElement> testPackageDetailsTable;

    @FindBy(css = "div[class*='icon']")
    public List<WebElement> clinicalIndicationsIcons;

    @FindBy(xpath = "//*[contains(@href,'test-detail')]//descendant::svg")
    public List<WebElement> testsIcons;

    @FindBy(xpath = "//*[contains(@href,'test-detail')]//descendant::table")
    public List<WebElement> testsDetailsTable;

    @FindBy(css = "h4[class*='samplesHeader']")
    public List<WebElement> testsRequiredSamplesHeaders;

    @FindBy(css = "div[class*='samples']")
    public List<WebElement> testsRequiredSamplesValues;

    @FindBy(css = "span[class*='boldText']")
    public List<WebElement> searchCategoriesExamplesTypes;

    @FindBy(css = "span[class*='text']")
    public List<WebElement> searchCategoriesExamples;

    @FindBy(css = "div[class*='innerContainer']")
    public WebElement betaBanner;

    @FindBy(css = "*[class*='infoBanner__closeButton']")
    public WebElement infoBannerXButton;

    @FindBy(css = "div[class*='tabLabel']")
    public WebElement tabsLabel;

    @FindBy(css = "svg[class*='tabIcon']")
    public List<WebElement> tabsIcons;

    @FindBy(css = "div[class*='aside']")
    public WebElement filtersPanel;

    @FindBy(css = "div[class*='main']")
    public WebElement resultsPanel;

    @FindBy(css = "h1[class*='header']")
    public WebElement privacyPolicyHeader;

    @FindBy(id = "login-form")
    public WebElement serviceDeskLoginForm;

    public String closeCookiesButton = "//*[contains(@class,'cta__')]//descendant::button";

    @FindBy(xpath = "//div [contains (@class, 'styles_contentContainer__3xbAF')][2]")
    public WebElement NGISVersion;

    @FindBy(className = "styles_link__2terw")
    public List<WebElement> tabResults;


	public void waitUntilHomePageResultsContainerIsLoaded() {
		Wait.forElementToBeDisplayed(driver, filtersPanel);
		Wait.forElementToBeDisplayed(driver, resultsPanel);
	}

    public void typeInSearchField(String searchTerm) {
        Wait.forElementToBeDisplayed(driver, searchField);
        searchField.sendKeys(searchTerm);
    }

    public void clickSearchIconFromSearchField() {
        Click.element(driver, searchIcon);
    }

    public void closeCookiesBannerFromFooter() {
        if (cookiesUnderstandButton.size() > 0) {
            Click.element(driver, cookiesUnderstandButton.get(0));
            Wait.forNumberOfElementsToBeEqualTo(driver, (By.xpath(closeCookiesButton)), 0);
        }
    }

    public void selectFirstEntityFromResultList() {
        waitUntilHomePageResultsContainerIsLoaded();
        Click.element(driver, resultsPanels.get(0));
    }

    public void TestDirectoryHomePageIsDisplayed() {
        Wait.forURLToContainSpecificText(driver, "/clinical-tests");
        Wait.forElementToBeDisplayed(driver, searchField);
    }

    public long rareAndInheritedDiseasesSearchResult() throws InterruptedException {
        rareAndInheritedDiseasesChkBox.click();
        waitUntilHomePageResultsContainerIsLoaded();
        Wait.seconds(1);
        String a = clinicalIndicationsTabValue.getText();
        String b = testsTabValue.getText();
        a = a.replaceAll("\\(", "").replaceAll("\\)", "");
        b = b.replaceAll("\\(", "").replaceAll("\\)", "");
        Debugger.println("Rare is " + (Integer.valueOf(a) + Integer.valueOf(b)));
        rareAndInheritedDiseasesChkBox.click();
        Wait.seconds(1);
        return Integer.valueOf(a) + Integer.valueOf(b);
    }

    public long tumorSearchResult() throws InterruptedException {
        tumorChkBox.click();
        waitUntilHomePageResultsContainerIsLoaded();
        Wait.seconds(1);
        String a = clinicalIndicationsTabValue.getText();
        String b = testsTabValue.getText();
        a = a.replaceAll("\\(", "").replaceAll("\\)", "");
        b = b.replaceAll("\\(", "").replaceAll("\\)", "");
        Debugger.println("Tumor is " + (Integer.valueOf(a) + Integer.valueOf(b)));
        tumorChkBox.click();
        Wait.seconds(1);
        return Integer.valueOf(a) + Integer.valueOf(b);
    }

    public long totalSearchResult() throws InterruptedException {
        waitUntilHomePageResultsContainerIsLoaded();
        Wait.seconds(1);
        String a = clinicalIndicationsTabValue.getText();
        String b = testsTabValue.getText();
        a = a.replaceAll("\\(", "").replaceAll("\\)", "");
        b = b.replaceAll("\\(", "").replaceAll("\\)", "");
        Debugger.println("Total is " + (Integer.valueOf(a) + Integer.valueOf(b)));
        return Integer.valueOf(a) + Integer.valueOf(b);
    }

    public boolean testResultsAreLoaded() {
        if (tabResults.size() == 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNGISVersionPresent() {
        Debugger.println(NGISVersion.getText());
        if (NGISVersion.getText().contains("NGIS")) {
            return true;
        } else {
            return false;
        }
    }

}