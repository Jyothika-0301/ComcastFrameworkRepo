package com.comcast.crm.objectrepositoryutility;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // current obj reference
	}

	@FindBy(linkText = "Organizations")
	private WebElement orglink;

	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText = "Documents")
	private WebElement documentsLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;

	@FindBy(xpath = "//img[contains(@src,'user')]")
	private WebElement adminImg;

	@FindBy(xpath = "//a[contains(@href, 'Logout')]")
	private WebElement signoutLink;

	@FindBy(linkText = "Products")
	private WebElement productLink;

	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getCampaignLink() {
		return campaignLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getProductLink() {
		return productLink;
	}

	public void navigateToCampaignPage() {
		Actions act = new Actions(driver);
		act.moveToElement(campaignLink).perform();
		campaignLink.click();
	}

	public void logout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(adminImg)).click();
		wait.until(ExpectedConditions.visibilityOf(signoutLink));
		wait.until(ExpectedConditions.elementToBeClickable(signoutLink)).click();
	}
}
