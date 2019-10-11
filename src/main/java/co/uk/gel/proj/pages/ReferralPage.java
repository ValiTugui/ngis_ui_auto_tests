package co.uk.gel.proj.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ReferralPage {

	WebDriver driver;

	public ReferralPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement SignOutStatusMessage;

	@FindBy(css = "*[class*='referral-header']")
	public WebElement referralHeader;

	@FindBy(css = "*[class*='referral-header-details']")
	public WebElement referralHeaderRegion;

	@FindBy(css = "button[class*='referral-header__submit']")
	public WebElement referralSubmitButton;

	@FindBy(className = "todo-list")
	public WebElement toDoList;

	@FindBy(css = "div[class*='referral__main']")
	public WebElement sectionBody;

	@FindBy(css = "h1[class*='page-title']")
	public WebElement pageTitle;

	@FindBy(xpath = "//*[contains(@class,'header__content')]//child::a")
	public WebElement genomicMedicineServicelogo;

	@FindBy(css = "button[class*='referral-navigation__continue']")
	public WebElement saveAndContinueButton;

	@FindBy(css = "button[class*='referral-navigation__button--back']")
	public WebElement backLink;

	@FindBy(css = "strong[class*='header-item']")
	public List<WebElement> referralHeaderDetails;

	@FindBy(css = "strong[class*='header-item__value']")
	public List<WebElement> referralHeaderValues;

	@FindBy(css = "*[class*='referral-success-notification']")
	public WebElement referralSuccessNotification;

	@FindBy(css = "*[class*='downloads__notice']")
	public WebElement submissionConfirmationBanner;

	@FindBy(css = "*[class*='notice__title']")
	public WebElement submissionConfirmationBannerTitle;

	@FindBy(css = "*[class*='referral-header__badge']")
	public WebElement referralStatus;

	@FindBy(css = "*[class*='referral-header__cancel-reason']")
	public WebElement referralCancelReason;

	@FindBy(css = "*[class*='logout-button']")
	public WebElement logoutButton;

	@FindBy(css = "*[class*='header__user']")
	public WebElement user;

	@FindBy(css = "*[class*='header__right-area']")
	public WebElement headerRightArea;

	@FindBy(css = "*[class*='footer__nhs-logo']")
	public WebElement nhsEnglandLogoFromFooter;

	@FindBy(css = "*[class*='footer__genomics-logo']")
	public WebElement genomicsEnglandLogoFromFooter;

	@FindBy(css = "*[class*='footer__content-area']")
	public WebElement footerContentArea;

	@FindBy(css = "*[class*='referral-header__cancel']")
	public WebElement cancelReferralLink;

	@FindBy(css = "*[class*='referral-header__cancel']")
	public List<WebElement> cancelReferraButton;

	@FindBy(css = "div[id*='react-select']")
	public WebElement dropdownValue;

	@FindBy(xpath = "//label[contains(@for,'cancel-options')]//following::div")
	public WebElement cancelReasonDropdown;

	@FindBy(css = "*[class*='notification-bar--success']")
	public WebElement cancelReferralNotification;

	@FindBy(css = "button[class*='modal__action']")
	public List<WebElement> cancelReferralButtons;

	@FindBy(css = "*[class*='modal__body']")
	public WebElement postSubmissionModal;

	@FindBy(xpath = "//*[contains(@class,'stage-list-item')]//child::a")
	public List<WebElement> stagesLinks;

	@FindBy(css = "*[class*='modal__close']")
	public WebElement closeModalButton;

	@FindBy(css = "*[class*='error-message']")
	public WebElement errorMessage;

	@FindBy(css = "*[class*='footer__link']")
	public List<WebElement> footerLinks;

	@FindBy(css = "*[id*='otherTile']")
	public WebElement useAnotherAccountButton;

	@FindBy(css = "*[class*='no-access__title']")
	public WebElement cancelledReferralWarningPageTitle;

	@FindBy(xpath = "//*[contains(@class,'no-access__copy')]//child::a")
	public List<WebElement> cancelledReferralWarningPageLinks;

	@FindBy(css = "*[class*='helix']")
	public List<WebElement> helix;




}
