package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebdriverUtility;

public class LoginPage extends WebdriverUtility {
	
	WebDriver driver;
	public LoginPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);   // current obj reference
	}
	

	@FindBy(name="user_name")
	 private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;

	//object encapsulation
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	// provide Action
	public void loginToApp(String url, String username, String password) {
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		driver.get(url);
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
		
	}

}
