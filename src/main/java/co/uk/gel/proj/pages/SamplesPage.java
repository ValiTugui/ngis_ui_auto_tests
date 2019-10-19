package co.uk.gel.proj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SamplesPage {

	WebDriver driver;

	public SamplesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
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

}
