package co.uk.gel.proj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TestPackagePage {

	WebDriver driver;

	public TestPackagePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//label[contains(@class,'field-label')]")
	public WebElement priorityLabel;

	@FindBy(xpath = "//div[contains(@class,'test-package__priority')]//following::button")
	public WebElement routinePriorityButton;

	@FindBy(xpath = "//div[contains(@class,'test-package__priority')]//following::button[2]")
	public WebElement urgentPriorityButton;

	@FindBy(xpath = "//div[contains(@class,'test-package__priority')]//following::p")
	public WebElement priorityHintText;

	@FindBy(css = "*[class*='test-package__select-tests-heading']")
	public WebElement selectTestsHeader;

	@FindBy(xpath = "//div[contains(@class,'test-package__select-tests')]//following::p")
	public WebElement selectTestsDescription;

	@FindBy(css = "div[class*='test-list-item']")
	public WebElement testCard;

	@FindBy(css = "div[class*='checkbox-card']")
	public WebElement testCheckBoxCard;

	@FindBy(css = "span[class*='checkbox-card__checkmark']")
	public WebElement testCheckMark;

	@FindBy(css = "div[class*='test-card']")
	public WebElement testCardBody;

	@FindBy(css = "p[class*='test-card__title']")
	public WebElement testCardTitle;

	@FindBy(css = "p[class*='test-card__name']")
	public WebElement testCardName;

	@FindBy(css = "*[class*='test-card__test-type']")
	public List<WebElement> testCardCategory;

	@FindBy(css = "*[class*='test-list-item__target']")
	public WebElement targetedGenesHeader;

	@FindBy(css = "p[class*='test-list-item__description']")
	public WebElement testTargetDescription;

	@FindBy(css = "label[for*='numberOfParticipants']")
	public WebElement numberOfParticipantsLabel;

	@FindBy(id = "numberOfParticipants")
	public List<WebElement> numberOfParticipantsDropdown;

	public WebElement numberOfParticipants;

	@FindBy(xpath = "//*[contains(@id,'numberOfParticipants')]//following::path")
	public WebElement clearNumberOfParticipantsButton;

	@FindBy(css = "div[class*='error-message']")
	public WebElement errorMessage;

	@FindBy(css = "div[id*='react-select']")
	public WebElement dropdownValue;

	@FindBy(xpath = "//*[contains(@class,'selected-family-members')]//child::h4")
	public WebElement selectedFamilyMembersHeader;

	@FindBy(css = "*[class*='relationship-tag']")
	public List<WebElement> selectedFamilyMembers;

}
