package co.uk.gel.proj.pages;

import co.uk.gel.lib.Click;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	public List <WebElement> testsFromTestPackageList;

	@FindBy(xpath = "//*[contains(@class,'testCard')]//child::td[5]")
	public WebElement testInfoIcon;

	@FindBy(css = "g[id*='Illustration/blue/test']")
	public WebElement testPackagePopupIcon;

	@FindBy(css = "div[class*='turnAroundTimes']")
	public WebElement testPackagePopupTime;

	@FindBy(css = "div[class*='testPackageProps']")
	public WebElement testPackagePopupProps;

	@FindBy(xpath = "//div[contains(@class,'ctaBlock')]//child::a")
	public WebElement goToTestPageButtonFromPopup;

	@FindBy(css = "div[class*='card_']")
	public  List <WebElement> furtherInfoSections;

	@FindBy(css = ".btn.btn-md.btn-primary")
	public WebElement startTestOrderButton;

	@FindBy(css = "div[class*='closeButton']")
	public WebElement closePopupButton;

	@FindBy(xpath = "//div[contains(@class,'back')]//descendant::a")
	public WebElement backToSearch;

	public void clickStartReferralButton() {
		Click.element(driver, startTestOrderButton);
	}

}
