package com.jp.us.pagefactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.jp.datainitialization.USDataInt;

public class USJPNewsLetterSignUp extends USJPOrder {

	public USJPNewsLetterSignUp(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(how = How.LINK_TEXT, using = "About Us")
	public WebElement about;

	@FindBy(how = How.LINK_TEXT, using = "Join Email List")
	public WebElement joinemail;

	@FindBy(how = How.ID, using = "emailAddress")
	public WebElement emailaddress;
	
	@FindBy(how = How.XPATH, using = "(//a[contains(@href, '#')])[5]")
	public WebElement signup;

	
	public void usnewsletter(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
		
		juiceplus.click();
		
		about.click();
		
		joinemail.click();
		
		emailaddress.sendKeys(addDistEmail());
		
		signup.click();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}