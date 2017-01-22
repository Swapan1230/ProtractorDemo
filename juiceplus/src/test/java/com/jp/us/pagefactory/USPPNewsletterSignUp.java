package com.jp.us.pagefactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.jp.datainitialization.USDataInt;

public class USPPNewsletterSignUp extends USJPNewsLetterSignUp {

	public USPPNewsletterSignUp(WebDriver driver) {
		super(driver);
		
	}

	public void usppnewsletter(USDataInt usdataInt) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
			driver.get("http://rccq.juiceplus.com/+tw25620");
			about.click();
			joinemail.click();
		emailaddress.sendKeys(addDistEmail());
		signup.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
